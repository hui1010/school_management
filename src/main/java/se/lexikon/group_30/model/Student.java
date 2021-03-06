package se.lexikon.group_30.model;

import java.util.*;

public class Student {
    private int studentId;
    private String name;
    private String email;
    private String address;
    private HashSet<Course> courses;

    public Student(int studentId, String name, String email, String address) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.courses = new HashSet<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashSet<Course> getCourses() {
        if (this.courses == null)
            this.courses = new HashSet<>();
        return courses;
    }

    public void setCourses(HashSet<Course> courses) {
        if (this.courses == null || this.courses.isEmpty()){
            this.courses = new HashSet<>();
            for (Course c : getCourses()){
                c.setStudents(null);
            }
            this.courses = courses;
        }else{
            /* //this trigger Concurrent Modification Exception
            for (Iterator it = getCourses().iterator(); it.hasNext();){
                it.remove();
            }

            for (Iterator it = courses.iterator(); it.hasNext();){

                addCourse((Course)it.next());
            }
            // this trigger Concurrent Modification Exception as well
            for (Object c : getCourses()){
                removeCourse((Course)c);
            }
            for (Object c : courses){
                addCourse((Course)c);
            }*/

            //this.courses.removeAll(getCourses());  todo ask Erik why don't the remove and removeAll method work
            this.courses.clear();

            for (Course c : courses){
                addCourse(c);
            }
        }
    }

    public void addCourse(Course course) {
        if (courses == null){
            courses = new HashSet<>();
        }else {
            if (courses.add(course)){
                course.register(this);
            }
        }
    }

    public void removeCourse(Course course) {
        if (courseExist(course)){
            courses.remove(course);
            course.setStudents(null);
        }else throw new NoSuchElementException(course.getCourseName() + " is not a valid course.");

        /*if (course != null){
            if (courses.remove(course)){
                course.setStudents(null);
            }
        }*/
    }

    private boolean courseExist(Course course) {
        boolean exist = false;
        if (courses.size() != 0) {
            for (Object c : courses){
                if (c.equals(course)){
                    exist = true;
                    break;
                }
            }
        }
        return exist;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId &&
                Objects.equals(name, student.name) &&
                Objects.equals(email, student.email) &&
                Objects.equals(address, student.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, name, email, address);
    }

}
