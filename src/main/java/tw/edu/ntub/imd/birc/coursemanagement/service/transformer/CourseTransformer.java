package tw.edu.ntub.imd.birc.coursemanagement.service.transformer;

import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Course;

public interface CourseTransformer {
    Course transferToEntity(CourseBean bean);
    CourseBean transferToBean(Course course);
}