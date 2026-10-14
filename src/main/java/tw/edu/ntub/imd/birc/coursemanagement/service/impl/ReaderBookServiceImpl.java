package tw.edu.ntub.imd.birc.coursemanagement.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tw.edu.ntub.imd.birc.coursemanagement.Bean.ReaderBookOrderBean;
import tw.edu.ntub.imd.birc.coursemanagement.Bean.ReaderBorrowedBookBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.dao.BookDAO;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.dao.ReaderBookDAO;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.dao.ReaderDAO;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Book;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Reader;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.ReaderBook;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.ReaderBookId;
import tw.edu.ntub.imd.birc.coursemanagement.exception.ApiException;
import tw.edu.ntub.imd.birc.coursemanagement.exception.DuplicateCreateException;
import tw.edu.ntub.imd.birc.coursemanagement.exception.NotFoundException;
import tw.edu.ntub.imd.birc.coursemanagement.service.ReaderBookService;
import tw.edu.ntub.imd.birc.coursemanagement.service.ReaderService;
import tw.edu.ntub.imd.birc.coursemanagement.service.transformer.ReaderBookTransformer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderBookServiceImpl implements ReaderBookService {
    private final ReaderBookDAO readerBookDAO;
    private final BookDAO bookDAO;
    private final ReaderDAO readerDAO;
    private final ReaderBookTransformer readerBookTransformer;

    public ReaderBookServiceImpl(ReaderBookDAO readerBookDAO,
                                 ReaderBookTransformer readerBookTransformer,
                                 ReaderDAO readerDAO,
                                 BookDAO bookDAO) {
        this.readerBookDAO = readerBookDAO;
        this.readerBookTransformer = readerBookTransformer;
        this.readerDAO = readerDAO;
        this.bookDAO = bookDAO;
    }

    @Override
    public ReaderBookOrderBean borrow(String readerId,String bookId){
        Reader reader = readerDAO.findById(readerId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,"NotFound","找不到讀者"));

        Book book = bookDAO.findById(bookId)
                .orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"NotFound","找不到書"));

        ReaderBookId readerBookId = new ReaderBookId(readerId,bookId);


        ReaderBook readerBook = new ReaderBook();
        readerBook.setReaderBookId(readerBookId);

        readerBook.setBook(book);
        readerBook.setReader(reader);

        if (readerBookDAO.existsById(readerBookId)){
            throw new ApiException(HttpStatus.CONFLICT,"Conflict","書本已存在");
        }

        readerBookDAO.save(readerBook);
        return readerBookTransformer.transferToBean(readerBook);
    }

    /**
     * 查某位讀者借過的所有書。
     * 流程:1. 先確認讀者存在(不存在 → 404)
     *       2. 用衍生查詢撈出這位讀者的所有 ReaderBook
     *       3. 把每一筆 ReaderBook 轉成回傳用的 Bean
     */
    @Override
    public List<ReaderBorrowedBookBean> searchBorrowedBooks(String readerId) {
        // 1. 讀者不存在要回 404,而不是回一個空陣列(空陣列代表「讀者在,但沒借書」)
        if (!readerDAO.existsById(readerId)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "NotFound", "找不到讀者");
        }

        // 2. 撈出借閱紀錄(DAO 上有 @EntityGraph,book 會一起 JOIN 出來)
        List<ReaderBook> records = readerBookDAO.findByReader_Id(readerId);

        // 3. stream 逐筆轉換:ReaderBook → ReaderBorrowedBookBean
        //    書的資料從關聯物件 rb.getBook() 拿;借閱時間在關聯表本身 rb.getBorrowDate()
        return records.stream()
                .map(rb -> {
                    ReaderBorrowedBookBean bean = new ReaderBorrowedBookBean();
                    bean.setBookId(rb.getBook().getId());
                    bean.setBookTitle(rb.getBook().getTitle());
                    bean.setBookAuthor(rb.getBook().getAuthor());
                    bean.setBorrowDate(rb.getBorrowDate());
                    return bean;
                })
                .collect(Collectors.toList());
    }
}
