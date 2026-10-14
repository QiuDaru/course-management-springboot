package tw.edu.ntub.imd.birc.coursemanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.edu.ntub.imd.birc.coursemanagement.bean.CourseBean;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.dao.CourseDAO;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Course;
import tw.edu.ntub.imd.birc.coursemanagement.exception.ApiException;
import tw.edu.ntub.imd.birc.coursemanagement.service.CourseService;
import tw.edu.ntub.imd.birc.coursemanagement.service.transformer.CourseTransformer;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDAO courseDAO;
    private final CourseTransformer transformer;

    public CourseServiceImpl(CourseDAO courseDAO, CourseTransformer transformer) {
        this.courseDAO = courseDAO;
        this.transformer = transformer;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseBean> searchAll() {
        return courseDAO.findAll().stream()
                .map(transformer::transferToBean)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CourseBean create(CourseBean bean) {
        if (courseDAO.existsById(bean.getId())) {
            throw new ApiException(HttpStatus.CONFLICT, "Create - Duplicate", "課程代碼重複");
        }
        Course saved = courseDAO.save(transformer.transferToEntity(bean));
        return transformer.transferToBean(saved);
    }
}
