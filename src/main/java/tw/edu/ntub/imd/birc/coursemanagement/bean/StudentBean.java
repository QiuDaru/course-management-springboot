package tw.edu.ntub.imd.birc.coursemanagement.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentBean {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
}