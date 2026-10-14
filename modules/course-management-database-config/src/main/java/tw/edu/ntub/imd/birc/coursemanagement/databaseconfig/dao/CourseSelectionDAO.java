package tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.CourseSelection;
import tw.edu.ntub.imd.birc.coursemanagement.databaseconfig.entity.CourseSelectionID;

@Repository
public interface CourseSelectionDAO extends JpaRepository<CourseSelection, CourseSelectionID> {

    List<CourseSelection> findByStudent_StudentId(String studentId);
}