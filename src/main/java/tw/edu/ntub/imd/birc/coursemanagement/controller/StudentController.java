package tw.edu.ntub.imd.birc.coursemanagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tw.edu.ntub.imd.birc.coursemanagement.util.http.ResponseEntityBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @GetMapping
    public ResponseEntity<String> searchAll() {
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .build();
    }
}