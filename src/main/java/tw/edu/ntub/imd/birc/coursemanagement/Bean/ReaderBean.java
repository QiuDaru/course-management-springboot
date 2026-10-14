package tw.edu.ntub.imd.birc.coursemanagement.Bean;

import lombok.Data;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ReaderBean {
    @NotBlank
    private String id;

    @NotBlank
    private  String name;

    @Email
    @NotBlank
    private String email;
}
