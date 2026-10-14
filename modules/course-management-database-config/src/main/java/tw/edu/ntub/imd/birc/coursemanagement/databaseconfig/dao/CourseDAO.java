package tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Course;

@Repository
public interface CourseDAO extends JpaRepository<Course, String> {
}