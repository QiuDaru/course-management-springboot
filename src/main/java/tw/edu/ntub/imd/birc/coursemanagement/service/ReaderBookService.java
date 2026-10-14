package tw.edu.ntub.imd.birc.coursemanagement.service;

import tw.edu.ntub.imd.birc.coursemanagement.Bean.ReaderBookOrderBean;
import tw.edu.ntub.imd.birc.coursemanagement.Bean.ReaderBorrowedBookBean;

import java.util.List;

public interface ReaderBookService {
    /** 借書:建立一筆讀者-書的關聯 */
    public ReaderBookOrderBean borrow(String readerId,String bookId);

    /** 查某位讀者借過的所有書;讀者不存在 → 404 */
    List<ReaderBorrowedBookBean> searchBorrowedBooks(String readerId);

}
