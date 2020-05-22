package se.lexikon.group_30.modelTest;

import com.sun.org.apache.xml.internal.utils.Hashtree2Node;
import org.junit.Before;
import org.junit.Test;
import se.lexikon.group_30.model.Course;
import se.lexikon.group_30.model.Student;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class StudentTest {
    private Student stu1 = new Student(1, "Tom", "tom@test.com", "växjö");
    private Student stu2 = new Student(2,"Jerry", "jerry@test.com", "växjö");
    private Student stu3 = new Student(3, "Elsa", "elsa@test.com", "kalmar");
    private Course c1 = new Course(1, "Math", LocalDate.parse("2020-08-24"), 16);
    private Course c2 = new Course(2, "Swedish", LocalDate.parse("2020-08-21"), 16);
    private Course c3 = new Course(3, "Music", LocalDate.parse("2020-09-01"), 8);

    @Before
    public void setUp() throws Exception {

    stu1.addCourse(c1); //Tom Math
    stu1.addCourse(c2); //Tom Swedish
    stu1.addCourse(c3); //Tom Music
        
    stu2.addCourse(c1); //Jerry Math
    stu2.addCourse(c2); //Jerry Swedish

    stu3.addCourse(c3); //Elsa Music

   }

    @Test
    public void test_if_three_students_are_successfully_created() {
        Collection<Student> stu = new HashSet<>();
        stu.add(stu1);
        stu.add(stu2);
        stu.add(stu3);

        assertEquals(3, stu.size());
    }

    @Test
    public void test_addCourse_method() {
        assertEquals(3, stu1.getCourses().size());
        assertEquals(2, stu2.getCourses().size());
        assertEquals(1, stu3.getCourses().size());
    }

    @Test
    public void test_set_and_get_methods() {
        assertEquals(1, stu1.getStudentId());
        stu2.setName("John");
        assertFalse(stu2.getName().equals("Jerry"));
        assertTrue(stu2.getName().equals("John"));
        assertEquals("elsa@test.com", stu3.getEmail());
        assertEquals("växjö", stu1.getAddress());

        //try to set more courses for stu3
        HashSet<Course> testCourses = new HashSet<>(0);
        testCourses.add(c1);
        testCourses.add(c2);

        stu3.setCourses(testCourses);

    }

    @Test
    public void test_removeCourse_method() {
        stu1.removeCourse(c1);
        // remove failed, really can't figure out why
        assertEquals(2, stu1.getCourses().size());
    }
}
