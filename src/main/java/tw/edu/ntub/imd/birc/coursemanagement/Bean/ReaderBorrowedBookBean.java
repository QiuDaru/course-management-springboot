package tw.edu.ntub.imd.birc.coursemanagement.Bean;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 「查某讀者借了哪些書」的回傳格式。
 * 欄位名稱照題目規格:bookId / bookTitle / bookAuthor / borrowDate。
 * 和 Entity 的欄位名(id / title / author)不同,所以要在 Transformer/Service 手動對拷,不能用 JavaBeanUtils.copy。
 */
@Data
public class ReaderBorrowedBookBean {
    private String bookId;
    private String bookTitle;
    private String bookAuthor;
    private LocalDateTime borrowDate;
}
