package tw.edu.ntub.imd.birc.coursemanagement.service;

import java.util.List;
import tw.edu.ntub.imd.birc.coursemanagement.bean.StudentBean;

public interface StudentService {
    List<StudentBean> searchAll();
    void create(StudentBean bean);
}