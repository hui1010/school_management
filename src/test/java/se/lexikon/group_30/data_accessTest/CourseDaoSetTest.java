package se.lexikon.group_30.data_accessTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.lexikon.group_30.data_access.CourseDao;
import se.lexikon.group_30.data_access.CourseDaoSet;
import se.lexikon.group_30.data_access.StudentDaoSet;
import se.lexikon.group_30.model.Course;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourseDaoSetTest {
    private CourseDao testObject;

    private Course c1 = new Course(1, "Math", LocalDate.parse("2020-08-24"), 16);
    private Course c2 = new Course(2, "Swedish", LocalDate.parse("2020-08-21"), 16);
    private Course c3 = new Course(3, "Music", LocalDate.parse("2020-09-01"), 8);

    Set<Course> courses = new HashSet<>(0);

    @Before
    public void setUp() throws Exception {
        testObject = CourseDaoSet.getInstance();

        courses.add(c1);
        courses.add(c2);

        testObject.setCourses(courses);
    }

    @Test
    public void test_saveCourse_method_and_findAll_method() {
        testObject.saveCourse(c3);
        assertEquals(3, testObject.findAll().size());
    }

    @Test
    public void test_findById_method_using_c2() {
        //c2 = new Course(2, "Swedish", LocalDate.parse("2020-08-21"), 16);
        assertEquals("Swedish", testObject.findById(2).getCourseName());
        assertEquals(16, testObject.findById(2).getWeekDuration());
        assertEquals(LocalDate.parse("2020-08-21"), testObject.findById(2).getStartDate());
    }

    @Test
    public void test_findByName_method() {
        //c1 = new Course(1, "Math", LocalDate.parse("2020-08-24"), 16);
        // change c2's name to Math and search for Math, should find 2
        c2.setCourseName("Math");
        assertEquals(2, testObject.findByName("Math").size());
    }

    @Test
    public void test_findByDate_method() {
        //c3 = new Course(3, "Music", LocalDate.parse("2020-09-01"), 8);
        testObject.saveCourse(c3);
        //add one more course "Dance" that has the same start date
        testObject.saveCourse(new Course(4,
                "Dance",
                LocalDate.parse("2020-09-01"),
                7));
        assertEquals(2, testObject.findByDate(LocalDate.parse("2020-09-01")).size());
    }

    @Test
    public void test_removeCourse_method() {
        //remove c2 the length should be 1
        assertTrue(testObject.removeCourse(c2));
        assertEquals(1, testObject.findAll().size());

    }

    @After
    public void tearDown() throws Exception {
        testObject.clear();
    }
}
