package tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.Student;

@Repository
public interface StudentDAO extends JpaRepository<Student, String> {
}