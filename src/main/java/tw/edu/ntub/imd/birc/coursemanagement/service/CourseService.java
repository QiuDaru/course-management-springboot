package tw.edu.ntub.imd.birc.coursemanagement.service;

import java.util.List;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseBean;

public interface CourseService {
    List<CourseBean> searchAll();
    CourseBean create(CourseBean bean);
}
