package se.lexikon.group_30.modelTest;

import org.junit.Before;
import org.junit.Test;
import se.lexikon.group_30.model.Course;
import se.lexikon.group_30.model.Student;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class CourseTest {

    private Course c1 = new Course(1, "Math", LocalDate.parse("2020-08-24"), 16);
    private Course c2 = new Course(2, "Swedish", LocalDate.parse("2020-08-21"), 16);
    private Course c3 = new Course(3, "Music", LocalDate.parse("2020-09-01"), 8);

    private Student stu1 = new Student(1, "Tom", "tom@test.com", "växjö");
    private Student stu2 = new Student(2,"Jerry", "jerry@test.com", "växjö");
    private Student stu3 = new Student(3, "Elsa", "elsa@test.com", "kalmar");
    Student stu4 = new Student(4, "Anna", "anna@test.com", "kalmar");

    HashSet<Student> students1 = new HashSet<>();
    HashSet<Student> students2 = new HashSet<>();
    HashSet<Student> students3 = new HashSet<>();

    @Before
    public void setUp() throws Exception {
        students1.add(stu1);
        students1.add(stu2);
        students1.add(stu3);

        students2.add(stu2);
        students2.add(stu1);

        students3.add(stu3);

        c1.setStudents(students1);
        c2.setStudents(students2);
        c3.setStudents(students3);
    }


    @Test
    public void test_setStudents() {

        assertEquals(3, c1.getStudents().size());
        assertEquals(2, c2.getStudents().size());
        assertEquals(1, c3.getStudents().size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_register_method() {

        c1.register(stu4);
        assertEquals(4, c1.getStudents().size());
        c2.register(stu4);
        assertEquals(3, c2.getStudents().size());

        //see if I can add the same student
        c3.register(stu3);
        assertEquals(1, c3.getStudents().size()); // answer is no
    }

    @Test (expected = NoSuchElementException.class)
    public void test_unregister_method() {
        c1.unregister(stu2);
        assertEquals(2, c1.getStudents().size());

        //see if I can remove student that doesn't exist
        c1.unregister(stu4);
    }
}
