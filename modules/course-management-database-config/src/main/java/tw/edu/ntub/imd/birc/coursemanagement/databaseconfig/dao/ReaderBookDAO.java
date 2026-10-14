package tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.ReaderBook;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.ReaderBookId;

import java.util.List;

public interface ReaderBookDAO extends BaseDAO<ReaderBook, ReaderBookId> {

    /**
     * 查某位讀者的所有借閱紀錄。
     * 衍生查詢(Derived Query):Spring Data 會解析方法名稱自動產生 SQL,不用自己寫。
     *   findBy  → SELECT ... WHERE
     *   Reader  → ReaderBook 裡的 reader 欄位(關聯物件)
     *   _Id     → 再往下取 Reader 的 id 欄位
     * 等同:SELECT * FROM ReaderBook WHERE readerId = ?
     *
     * @EntityGraph:book 是 LAZY,預設不會一起撈。這裡要用到書名/作者,
     * 所以叫 JPA 用 JOIN 一次把 book 一起查出來,避免 N+1 查詢與 LazyInitializationException。
     */
    @EntityGraph(attributePaths = "book")
    List<ReaderBook> findByReader_Id(String readerId);
}
