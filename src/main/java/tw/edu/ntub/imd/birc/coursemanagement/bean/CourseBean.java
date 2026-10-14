package tw.edu.ntub.imd.birc.coursemanagement.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseBean {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    private Integer credit;
}