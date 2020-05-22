package se.lexikon.group_30.data_access;

import se.lexikon.group_30.model.Student;

import java.util.List;
import java.util.Set;

public interface StudentDao {
    Student saveStudent(Student student);
    Student findByEmail(String email);
    Set<Student> findByName(String name);
    Student findById(int id);
    Set<Student> findAll();
    boolean deleteStudent(Student student);
}
