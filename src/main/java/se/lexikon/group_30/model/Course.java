package se.lexikon.group_30.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

public class Course {
    private int courseId;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private HashSet<Student> students;

    public Course(int courseId, String courseName, LocalDate startDate, int weekDuration) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
        this.students = new HashSet<>(0);
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(HashSet<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId == course.courseId &&
                weekDuration == course.weekDuration &&
                Objects.equals(courseName, course.courseName) &&
                Objects.equals(startDate, course.startDate) &&
                Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, startDate, weekDuration, students);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", weekDuration=" + weekDuration +
                //", students=" + students +
                '}' +'\n';
    }

    public void register(Student student){

        if (!(studentExist(student)) || students.size() == 0){
            students.add(student);
        }else throw new IllegalArgumentException("Already registered");
    }

    private boolean studentExist(Student student) {
        boolean exist = false;
        if (students.size() != 0) {
            for (Object stu : students){
                if (stu.equals(student)){
                    exist = true;
                    break;
                }
            }
        }
        return exist;
    }

    public void unregister(Student student){
        if (studentExist(student)){
            students.remove(student);
        }else throw new NoSuchElementException(student.getName() + " has not registered yet");
    }
}
