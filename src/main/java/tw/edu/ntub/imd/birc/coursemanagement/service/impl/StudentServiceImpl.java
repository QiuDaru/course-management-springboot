package tw.edu.ntub.imd.birc.coursemanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.edu.ntub.imd.birc.coursemanagement.bean.StudentBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.dao.StudentDAO;
import tw.edu.ntub.imd.birc.coursemanagement.exception.ApiException;
import tw.edu.ntub.imd.birc.coursemanagement.service.StudentService;
import tw.edu.ntub.imd.birc.coursemanagement.service.transformer.StudentTransformer;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;
    private final StudentTransformer transformer;

    public StudentServiceImpl(StudentDAO studentDAO, StudentTransformer transformer) {
        this.studentDAO = studentDAO;
        this.transformer = transformer;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentBean> searchAll() {
        return studentDAO.findAll().stream()
                .map(transformer::transferToBean)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void create(StudentBean bean) {
        if (studentDAO.existsById(bean.getId())) {
            throw new ApiException(HttpStatus.CONFLICT, "Create - Duplicate", "學號重複");
        }
        studentDAO.save(transformer.transferToEntity(bean));
    }

}