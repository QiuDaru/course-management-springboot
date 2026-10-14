package tw.edu.ntub.imd.birc.coursemanagement.service.transformer;

import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseSelectionBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.CourseSelection;

public interface CourseSelectionTransformer {
    CourseSelectionBean transferToBean(CourseSelection cs);
}
