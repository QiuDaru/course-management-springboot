package tw.edu.ntub.imd.birc.coursemanagement.service.transformer;

import tw.edu.ntub.imd.birc.coursemanagement.bean.StudentBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Student;

public interface StudentTransformer {
    Student transferToEntity(StudentBean bean);
    StudentBean transferToBean(Student student);
}