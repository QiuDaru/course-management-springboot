package tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @NotBlank
    @Column(name = "course_id", nullable = false)
    private String courseId;

    @NotBlank
    @Column(name = "course_name", nullable = false)
    private  String courseName;

    @Min(0)
    @NotNull
    @Column(name = "credit",nullable = false)
    private Integer credit;





}
