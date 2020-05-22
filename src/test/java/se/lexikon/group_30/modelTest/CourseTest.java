package se.lexikon.group_30.modelTest;

import org.junit.Before;
import se.lexikon.group_30.model.Course;
import se.lexikon.group_30.model.Student;

import java.time.LocalDate;

public class CourseTest {

    private Course c1 = new Course(1, "Math", LocalDate.parse("2020-08-24"), 16);
    private Course c2 = new Course(2, "Swedish", LocalDate.parse("2020-08-21"), 16);
    private Course c3 = new Course(3, "Music", LocalDate.parse("2020-09-01"), 8);

    private Student stu1 = new Student(1, "Tom", "tom@test.com", "växjö");
    private Student stu2 = new Student(2,"Jerry", "jerry@test.com", "växjö");
    private Student stu3 = new Student(3, "Elsa", "elsa@test.com", "kalmar");

    @Before
    public void setUp() throws Exception {

    }
}
