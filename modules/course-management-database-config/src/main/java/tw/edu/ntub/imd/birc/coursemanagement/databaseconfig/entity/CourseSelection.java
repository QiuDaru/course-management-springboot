package tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course_selection")
@EntityListeners(AuditingEntityListener.class)
public class CourseSelection {

    @EmbeddedId
    private CourseSelectionID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentID")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseID")
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "remark")
    private String remark;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "Asia/Taipei")
    @Column(name = "create_time", nullable = false, updatable = false)
    private ZonedDateTime createTime;
}