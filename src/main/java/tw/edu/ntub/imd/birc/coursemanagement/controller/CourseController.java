package tw.edu.ntub.imd.birc.coursemanagement.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseBean;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseSelectionRequestBean;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseSelectionResultBean;
import tw.edu.ntub.imd.birc.coursemanagement.service.CourseSelectionService;
import tw.edu.ntub.imd.birc.coursemanagement.service.CourseService;
import tw.edu.ntub.imd.birc.coursemanagement.util.http.ResponseEntityBuilder;
import tw.edu.ntub.imd.birc.coursemanagement.util.json.array.ArrayData;
import tw.edu.ntub.imd.birc.coursemanagement.util.json.object.ObjectData;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseSelectionService courseSelectionService;

    public CourseController(CourseService courseService,
                            CourseSelectionService courseSelectionService) {
        this.courseService = courseService;
        this.courseSelectionService = courseSelectionService;
    }

    @GetMapping
    public ResponseEntity<String> searchAll() {
        List<CourseBean> courses = courseService.searchAll();
        ArrayData array = new ArrayData();
        for (CourseBean c : courses) {
            ObjectData obj = new ObjectData();
            obj.add("id", c.getId());
            obj.add("name", c.getName());
            obj.add("credit", c.getCredit());
            array.add(obj);
        }
        return ResponseEntityBuilder.success()
                .message("查詢成功")
                .data(array)
                .build();
    }

    @PostMapping
    public ResponseEntity<String> addCourse(@Valid @RequestBody CourseBean course) {
        CourseBean saved = courseService.create(course);
        ObjectData data = new ObjectData()
                .add("id", saved.getId())
                .add("name", saved.getName())
                .add("credit", saved.getCredit());
        return ResponseEntityBuilder.success()
                .message("新增成功")
                .data(data)
                .build();
    }

    @PostMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<String> enroll(@PathVariable String courseId,
                                         @PathVariable String studentId,
                                         @RequestBody(required = false) CourseSelectionRequestBean body) {
        CourseSelectionResultBean r = courseSelectionService.enroll(courseId, studentId, body);
        ObjectData data = new ObjectData()
                .add("studentId", r.getStudentId())
                .add("courseId", r.getCourseId())
                .add("grade", r.getGrade())
                .add("remark", r.getRemark())
                .add("createDate", formatDate(r.getCreateDate()));
        return ResponseEntityBuilder.success()
                .message("選課成功")
                .data(data)
                .build();
    }

    private static String formatDate(ZonedDateTime dt) {
        return dt == null ? null : dt.withZoneSameInstant(ZoneId.of("Asia/Taipei"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
    }
}
