package tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CourseSelectionID implements Serializable {
    private String courseID;
    private String studentID;
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        CourseSelectionID that = (CourseSelectionID) o;
        return Objects.equals(this.courseID,that.getCourseID()) &&
                Objects.equals(this.studentID,that.getStudentID());
    }
    @Override
    public int hashCode() {
        return Objects.hash(courseID, studentID);
    }
}
