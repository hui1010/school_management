package se.lexikon.group_30.data_access;

import se.lexikon.group_30.model.Student;

import java.util.HashSet;

import java.util.Set;


public class StudentDaoSet implements StudentDao{

    private Set<Student> students;
    private static final StudentDao STUDENT_MANAGE;

    static {
        STUDENT_MANAGE = new StudentDaoSet();
    }

    private StudentDaoSet() {
        this.students = new HashSet<>();
    }

    public static StudentDao getInstance(){
        return STUDENT_MANAGE;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public Student saveStudent(Student student) {
        if (student == null) return null;

        students.add(student);
        return student;
    }

    @Override
    public Student findByEmail(String email) {
        if (email == null) return null;
        for (Student stu : students){
            if (stu.getEmail() == email){
                return stu;
            }
        }
        return null;
    }

    @Override
    public Set<Student> findByName(String name) {
        Set<Student> result = new HashSet<>(0);
        if (name == null) return null;
        for (Student stu : students){
            if (stu.getName().equalsIgnoreCase(name)){
                result.add(stu);
            }
        }
        return result;
    }

    @Override
    public Student findById(int id) {
        if (id < 0 || id > students.size())
            throw new IndexOutOfBoundsException("Invalid ID");
        for (Student stu : students){
            if (stu.getStudentId() == id)
                return stu;
        }
        return null;
    }

    @Override
    public Set<Student> findAll() {

        return students;
    }

    @Override
    public boolean deleteStudent(Student student) {

        return students.remove(student);
    }

    public void clear(){
        this.students = new HashSet<>();
    }
}
