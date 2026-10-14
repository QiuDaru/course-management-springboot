package tw.edu.ntub.imd.birc.coursemanagement.service.transformer.Impl;

import org.springframework.stereotype.Component;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Course;
import tw.edu.ntub.imd.birc.coursemanagement.service.transformer.CourseTransformer;

@Component
public class CourseTransformerImpl implements CourseTransformer {

    @Override
    public Course transferToEntity(CourseBean bean) {
        Course course = new Course();
        course.setCourseId(bean.getId());       // id → courseId
        course.setCourseName(bean.getName());   // name → courseName
        course.setCredit(bean.getCredit());
        return course;
    }

    @Override
    public CourseBean transferToBean(Course course) {
        CourseBean bean = new CourseBean();
        bean.setId(course.getCourseId());
        bean.setName(course.getCourseName());
        bean.setCredit(course.getCredit());
        return bean;
    }
}