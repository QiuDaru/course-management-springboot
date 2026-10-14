package tw.edu.ntub.imd.birc.coursemanagement.service.transformer.Impl;

import org.springframework.stereotype.Component;
import tw.edu.ntub.imd.birc.coursemanagement.bean.StudentBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Student;
import tw.edu.ntub.imd.birc.coursemanagement.service.transformer.StudentTransformer;

@Component
public class StudentTransformerImpl implements StudentTransformer {

    @Override
    public Student transferToEntity(StudentBean bean) {
        Student student = new Student();
        student.setStudentId(bean.getId());
        student.setName(bean.getName());
        student.setEmail(bean.getEmail());
        return student;
    }

    @Override
    public StudentBean transferToBean(Student student) {
        StudentBean bean = new StudentBean();
        bean.setId(student.getStudentId());
        bean.setName(student.getName());
        bean.setEmail(student.getEmail());
        return bean;
    }
}