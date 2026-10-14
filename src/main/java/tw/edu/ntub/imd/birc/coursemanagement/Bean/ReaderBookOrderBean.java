package tw.edu.ntub.imd.birc.coursemanagement.Bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class ReaderBookOrderBean {
    private String readerId;
    private String bookId;
    private LocalDateTime borrowDate;
}
