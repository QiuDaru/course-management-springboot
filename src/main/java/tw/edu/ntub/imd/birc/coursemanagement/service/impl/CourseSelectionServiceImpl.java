package tw.edu.ntub.imd.birc.coursemanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseSelectionBean;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseSelectionRequestBean;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseSelectionResultBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.dao.CourseDAO;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.dao.CourseSelectionDAO;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.dao.StudentDAO;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Course;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.CourseSelection;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.CourseSelectionID;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Student;
import tw.edu.ntub.imd.birc.coursemanagement.exception.ApiException;
import tw.edu.ntub.imd.birc.coursemanagement.service.CourseSelectionService;
import tw.edu.ntub.imd.birc.coursemanagement.service.transformer.CourseSelectionTransformer;

@Service
public class CourseSelectionServiceImpl implements CourseSelectionService {

    private final StudentDAO studentDAO;
    private final CourseDAO courseDAO;
    private final CourseSelectionDAO courseSelectionDAO;
    private final CourseSelectionTransformer transformer;

    public CourseSelectionServiceImpl(StudentDAO studentDAO,
                                      CourseDAO courseDAO,
                                      CourseSelectionDAO courseSelectionDAO,
                                      CourseSelectionTransformer transformer) {
        this.studentDAO = studentDAO;
        this.courseDAO = courseDAO;
        this.courseSelectionDAO = courseSelectionDAO;
        this.transformer = transformer;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseSelectionBean> getStudentCourses(String studentId) {
        if (!studentDAO.existsById(studentId)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "NotFound", "找不到學生");
        }
        return courseSelectionDAO.findByStudent_StudentId(studentId).stream()
                .map(transformer::transferToBean)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CourseSelectionResultBean enroll(String courseId, String studentId, CourseSelectionRequestBean body) {
        Student student = studentDAO.findById(studentId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "NotFound", "學生不存在"));
        Course course = courseDAO.findById(courseId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "NotFound", "找不到課程"));

        CourseSelectionID pk = new CourseSelectionID();
        pk.setStudentID(studentId);
        pk.setCourseID(courseId);
        if (courseSelectionDAO.existsById(pk)) {
            throw new ApiException(HttpStatus.CONFLICT, "Conflict", "學生已選修該課程，請勿重複選課");
        }

        CourseSelection cs = new CourseSelection();
        cs.setId(pk);              // @MapsId 安全起見先給非 null 的主鍵物件
        cs.setStudent(student);
        cs.setCourse(course);
        if (body != null) {
            cs.setGrade(body.getGrade());
            cs.setRemark(body.getRemark());
        }
        CourseSelection saved = courseSelectionDAO.save(cs);
        return toResult(studentId, courseId, saved);
    }

    @Override
    @Transactional
    public CourseSelectionResultBean updateGrade(String studentId, String courseId, CourseSelectionRequestBean body) {
        if (!studentDAO.existsById(studentId)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "NotFound", "找不到學生");
        }
        CourseSelectionID pk = new CourseSelectionID();
        pk.setStudentID(studentId);
        pk.setCourseID(courseId);
        CourseSelection cs = courseSelectionDAO.findById(pk)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "NotFound", "學生未選該課"));

        if (body != null) {
            cs.setGrade(body.getGrade());
            cs.setRemark(body.getRemark());
        }
        CourseSelection saved = courseSelectionDAO.save(cs);
        return toResult(studentId, courseId, saved);
    }

    private CourseSelectionResultBean toResult(String studentId, String courseId, CourseSelection cs) {
        CourseSelectionResultBean r = new CourseSelectionResultBean();
        r.setStudentId(studentId);
        r.setCourseId(courseId);
        r.setGrade(cs.getGrade());
        r.setRemark(cs.getRemark());
        r.setCreateDate(cs.getCreateTime());
        return r;
    }
}
