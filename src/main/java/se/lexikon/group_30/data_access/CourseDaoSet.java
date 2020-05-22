package se.lexikon.group_30.data_access;

import se.lexikon.group_30.model.Course;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CourseDaoSet implements CourseDao {

    private static final CourseDao COURSE_MANAGE;

    static {
        COURSE_MANAGE = new CourseDaoSet();
    }

    private CourseDaoSet(){
        this.courses = new HashSet<>();
    }

    private static Set<Course> courses;

    public static CourseDao getInstance(){
        return COURSE_MANAGE;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        CourseDaoSet.courses = courses;
    }

    @Override
    public Course saveCourse(Course course) {
        if (course == null) return null;
        if (!courseExists(course)){
            courses.add(course);
        }
        return course;
    }

    private boolean courseExists(Course course) {
        for (Course c : courses){
            if (c.equals(course))
                return true;
        }
        return false;
    }

    @Override
    public Course findById(int id) {
        if (id < 0 || id > courses.size())
            throw new IllegalArgumentException("Invalid course ID");
        for (Course c : courses){
            if (c.getCourseId() == id){
                return c;
            }
        }
        return null;
    }

    @Override
    public Set<Course> findByName(String name) {
        Set<Course> result = new HashSet<>(0);
        if (name == null) return null;
        for (Course c : courses){
            if (c.getCourseName().equalsIgnoreCase(name))
                result.add(c);
        }
        return result;
    }

    @Override
    public Set<Course> findByDate(LocalDate date) {
        Set<Course> result = new HashSet<>(0);
        for (Course c : courses){
            if (c.getStartDate().isEqual(date))
                result.add(c);
        }
        return result;
    }

    @Override
    public Set<Course> findAll() {
        return courses;
    }

    @Override
    public boolean removeCourse(Course course) {
        return courses.remove(course);
    }

    @Override
    public void clear(){
        this.courses = new HashSet<>();
    }
}
