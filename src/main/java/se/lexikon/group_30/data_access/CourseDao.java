package se.lexikon.group_30.data_access;

import se.lexikon.group_30.model.Course;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface CourseDao {
    void setCourses(Set<Course> courses);
    Set<Course> getCourses();
    Course saveCourse(Course course);
    Course findById(int id);
    Set<Course> findByName(String name);
    Set<Course> findByDate(LocalDate date);
    Set<Course> findAll();
    boolean removeCourse(Course course);
    void clear();
}
