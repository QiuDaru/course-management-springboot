package tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column(name = "student_id", nullable = false,length = 8)
    private String studentId;


    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Email
    @Column(name = "email" , nullable = false)
    private String email;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseSelection> courseSelections = new HashSet<>();

    public void addCourseSelection(CourseSelection selection) {
        this.courseSelections.add(selection);
        selection.setStudent(this);
    }

    public void removeCourseSelection(CourseSelection selection) {
        this.courseSelections.remove(selection);
        selection.setStudent(null);
    }
}
