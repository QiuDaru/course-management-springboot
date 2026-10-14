package tw.edu.ntub.imd.birc.coursemanagement.service;

import java.util.List;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseSelectionBean;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseSelectionRequestBean;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseSelectionResultBean;

public interface CourseSelectionService {
    List<CourseSelectionBean> getStudentCourses(String studentId);

    CourseSelectionResultBean enroll(String courseId, String studentId, CourseSelectionRequestBean body);

    CourseSelectionResultBean updateGrade(String studentId, String courseId, CourseSelectionRequestBean body);
}
