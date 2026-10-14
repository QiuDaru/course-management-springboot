package tw.edu.ntub.imd.birc.coursemanagement.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class CourseSelectionResultBean {

    private String studentId;
    private String courseId;
    private Integer grade;
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "Asia/Taipei")
    private ZonedDateTime createDate;
}