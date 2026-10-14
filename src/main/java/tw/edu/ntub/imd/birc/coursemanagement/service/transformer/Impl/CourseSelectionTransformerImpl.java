package tw.edu.ntub.imd.birc.coursemanagement.service.transformer.Impl;

import org.springframework.stereotype.Component;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseSelectionBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.CourseSelection;
import tw.edu.ntub.imd.birc.coursemanagement.service.transformer.CourseSelectionTransformer;

@Component
public class CourseSelectionTransformerImpl implements CourseSelectionTransformer {
    @Override
    public CourseSelectionBean transferToBean(CourseSelection cs) {
        CourseSelectionBean bean = new CourseSelectionBean();
        bean.setCourseId(cs.getCourse().getCourseId());      // join course 拿
        bean.setCourseName(cs.getCourse().getCourseName());
        bean.setCourseCredit(cs.getCourse().getCredit());
        bean.setGrade(cs.getGrade());
        bean.setRemark(cs.getRemark());
        bean.setCreateDate(cs.getCreateTime());              // entity 是 createTime
        return bean;
    }
}