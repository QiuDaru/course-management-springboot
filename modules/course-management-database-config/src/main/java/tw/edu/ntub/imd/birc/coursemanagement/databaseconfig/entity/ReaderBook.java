package tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ReaderBook")
public class ReaderBook {
    @EmbeddedId
    private ReaderBookId readerBookId;

    @ManyToOne(fetch = FetchType.LAZY )
    @MapsId("bookId")
    @JoinColumn(name = "bookId")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY )
    @MapsId("readerId")
    @JoinColumn(name = "readerId")
    private Reader reader;

    @CreatedDate
    @Column(name = "borrowDate",updatable = false)
    private LocalDateTime borrowDate;
}
