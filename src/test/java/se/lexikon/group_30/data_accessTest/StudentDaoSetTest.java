package se.lexikon.group_30.data_accessTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.lexikon.group_30.data_access.StudentDao;
import se.lexikon.group_30.data_access.StudentDaoSet;
import se.lexikon.group_30.model.Student;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentDaoSetTest {
    // actually don't know why create this one
    //todo ask Erik
    private StudentDao testObj;

    private Student stu1 = new Student(1, "Tom", "tom@test.com", "växjö");
    private Student stu2 = new Student(2,"Jerry", "jerry@test.com", "växjö");
    private Student stu3 = new Student(3, "Elsa", "elsa@test.com", "kalmar");
    private Student stu4 = new Student(4, "Anna", "anna@test.com", "kalmar");

    Set<Student> students = new HashSet<>(0);

    @Before
    public void setUp() throws Exception {
        testObj = StudentDaoSet.getInstance();

        students.add(stu1);
        students.add(stu2);
        students.add(stu3);

        testObj.setStudents(students);

    }

    @Test
    public void test_saveStudent_method_and_findAll_method() {
        testObj.saveStudent(stu4);
        assertEquals(4, testObj.findAll().size());
    }

    @Test
    public void test_findByEmail_method_using_stu2_email() {
        //Student stu2 = new Student(2,"Jerry", "jerry@test.com", "växjö");
        String expectedName = "Jerry";
        int expectedId = 2;
        String expectedAddress = "växjö";

        assertEquals(expectedAddress, testObj.findByEmail("jerry@test.com").getAddress());
        assertEquals(expectedName, testObj.findByEmail("jerry@test.com").getName());
        assertEquals(expectedId, testObj.findByEmail("jerry@test.com").getStudentId());
    }

    @Test
    public void test_findByName_method_using_stu3_name() {
        //change the stu4's name to Elsa and find by name Elsa, should find 2 students
        stu4.setName("Elsa");
        assertEquals(2, testObj.findByName("Elsa").size());
    }

    @Test
    public void test_findById_method_using_stu1() {
        //stu1 = new Student(1, "Tom", "tom@test.com", "växjö")
        String expectedName = "Tom";
        String expectedEmail = "tom@test.com";
        String expectedAddress = "växjö";
        assertEquals(expectedAddress, testObj.findById(1).getAddress());
        assertEquals(expectedEmail, testObj.findById(1).getEmail());
        assertEquals(expectedName, testObj.findById(1).getName());
    }

    @Test
    public void test_deleteStudent_method() {
        //delete stu4 then the size should be 3
        assertTrue(testObj.deleteStudent(stu4));
        assertEquals(3,testObj.findAll().size());
    }

    @After
    public void tearDown() throws Exception {
        testObj.clear();
    }
}
