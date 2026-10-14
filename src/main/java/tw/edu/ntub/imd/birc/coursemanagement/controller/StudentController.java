package tw.edu.ntub.imd.birc.coursemanagement.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseSelectionBean;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseSelectionRequestBean;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseSelectionResultBean;
import tw.edu.ntub.imd.birc.coursemanagement.bean.StudentBean;
import tw.edu.ntub.imd.birc.coursemanagement.service.CourseSelectionService;
import tw.edu.ntub.imd.birc.coursemanagement.service.StudentService;
import tw.edu.ntub.imd.birc.coursemanagement.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.coursemanagement.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.coursemanagement.util.json.object.ObjectData;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;
    private final CourseSelectionService courseSelectionService;

    public StudentController(StudentService studentService,
                             CourseSelectionService courseSelectionService) {
        this.studentService = studentService;
        this.courseSelectionService = courseSelectionService;
    }

    @GetMapping
    public ResponseEntity<String> searchAll() {
        List<StudentBean> students = studentService.searchAll();
        ArrayData array = new ArrayData();
        for (StudentBean s : students) {
            ObjectData obj = new ObjectData();
            obj.add("id", s.getId());
            obj.add("name", s.getName());
            obj.add("email", s.getEmail());
            array.add(obj);
        }
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(array)
                .build();
    }

    @PostMapping
    public ResponseEntity<String> addStudent(@Valid @RequestBody StudentBean student) {
        studentService.create(student);
        return ResponseEntityBuilder.success()
                .status(HttpStatus.CREATED)
                .message("新增成功")
                .build();
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<String> getStudentCourses(@PathVariable String id) {
        List<CourseSelectionBean> list = courseSelectionService.getStudentCourses(id);
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(list, (obj, c) -> obj
                        .add("courseId", c.getCourseId())
                        .add("courseName", c.getCourseName())
                        .add("courseCredit", c.getCourseCredit())
                        .add("grade", c.getGrade())
                        .add("remark", c.getRemark())
                        .add("createDate", formatDate(c.getCreateDate())))
                .build();
    }

    @PatchMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<String> updateGrade(@PathVariable String studentId,
                                              @PathVariable String courseId,
                                              @RequestBody CourseSelectionRequestBean body) {
        CourseSelectionResultBean r = courseSelectionService.updateGrade(studentId, courseId, body);
        ObjectData data = new ObjectData()
                .add("studentId", r.getStudentId())
                .add("courseId", r.getCourseId())
                .add("grade", r.getGrade())
                .add("remark", r.getRemark());
        return ResponseEntityBuilder.success()
                .message("修改成功")
                .data(data)
                .build();
    }

    private static String formatDate(ZonedDateTime dt) {
        return dt == null ? null : dt.withZoneSameInstant(ZoneId.of("Asia/Taipei"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
    }
}
