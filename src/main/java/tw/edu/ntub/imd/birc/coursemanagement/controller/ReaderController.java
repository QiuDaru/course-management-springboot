package tw.edu.ntub.imd.birc.coursemanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.edu.ntub.imd.birc.coursemanagement.Bean.ReaderBean;
import tw.edu.ntub.imd.birc.coursemanagement.Bean.ReaderBookOrderBean;
import tw.edu.ntub.imd.birc.coursemanagement.Bean.ReaderBorrowedBookBean;
import tw.edu.ntub.imd.birc.coursemanagement.service.ReaderBookService;
import tw.edu.ntub.imd.birc.coursemanagement.service.ReaderService;
import tw.edu.ntub.imd.birc.coursemanagement.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.coursemanagement.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.coursemanagement.util.json.object.ObjectData;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/readers")
public class ReaderController {
    private final ReaderService readerService;
    private final ReaderBookService readerBookService;

    public ReaderController(ReaderService readerService, ReaderBookService readerBookService) {
        this.readerService = readerService;
        this.readerBookService = readerBookService;
    }

    @GetMapping()
    public ResponseEntity<String> serchAllReader(){
        List<ReaderBean> reader = readerService.searchAll();
        ArrayData arrayData = new ArrayData();
        for (ReaderBean readerBean : reader) {
            ObjectData obj = new ObjectData();
            obj.add("id",readerBean.getId());
            obj.add("name",readerBean.getName());
            obj.add("email",readerBean.getEmail());
            arrayData.add(obj);
        }
        return ResponseEntityBuilder.success()
                .data(arrayData)
                .message("查詢成功")
                .build();
    }

    @PostMapping
    public ResponseEntity<String> addReader(@Valid @RequestBody ReaderBean readerBean){

        readerService.save(readerBean);

        return ResponseEntityBuilder.success()
                .message("新增成功")
                .build();
    }

    @PostMapping("{readersId}/book/{bookId}")
    public ResponseEntity<String> borrow(@PathVariable String readersId , @PathVariable String bookId){
        ReaderBookOrderBean readerBookOrderBean = readerBookService.borrow(readersId,bookId);

        return ResponseEntityBuilder.success()
                .data(new ObjectData()
                        .add("readerId", readerBookOrderBean.getReaderId())
                        .add("bookId", readerBookOrderBean.getBookId())
                        .add("borrowDate", readerBookOrderBean.getBorrowDate()))
                .message("成功")
                .build();
    }

    /**
     * GET /api/v1/readers/{readerId}/books  查某位讀者借了哪些書
     * 成功:200,data 為陣列,每筆 { bookId, bookTitle, bookAuthor, borrowDate }
     * 讀者不存在:Service 丟 ApiException → ExceptionHandleController 轉成 404
     */
    @GetMapping("/{readerId}/books")
    public ResponseEntity<String> searchBorrowedBooks(@PathVariable String readerId) {
        List<ReaderBorrowedBookBean> books = readerBookService.searchBorrowedBooks(readerId);

        // 多筆資料 → ArrayData;每一筆用 ObjectData 組成一個 JSON 物件
        ArrayData arrayData = new ArrayData();
        for (ReaderBorrowedBookBean b : books) {
            arrayData.add(new ObjectData()
                    .add("bookId", b.getBookId())
                    .add("bookTitle", b.getBookTitle())
                    .add("bookAuthor", b.getBookAuthor())
                    .add("borrowDate", b.getBorrowDate()));
        }

        return ResponseEntityBuilder.success()
                .data(arrayData)
                .message("查詢成功")
                .build();
    }
}
