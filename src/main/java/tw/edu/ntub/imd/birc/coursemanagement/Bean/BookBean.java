package tw.edu.ntub.imd.birc.coursemanagement.Bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;


@Data
public class BookBean {
    @NotBlank
    private String id;

    @NotBlank
    private String title;
    @NotBlank
    private String author;
}
