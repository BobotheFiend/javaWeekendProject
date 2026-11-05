package cohortHub.data.repositories;

import cohortHub.data.models.SemicolonStudent;
import cohortHub.data.models.enums.Cohort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SemicolonStudentRepositoryImplTest {

    SemicolonStudentRepositoryImpl semicolonStudentRepository;

    @BeforeEach
    public void setUp() {
        semicolonStudentRepository = new SemicolonStudentRepositoryImpl();
    }

    @AfterEach
    public void tearDown() {
        semicolonStudentRepository.clearAll();
    }
    @Test
    public void repositoryIsEmpty_lengthIsZeroTest() {
        assertEquals(0, semicolonStudentRepository.length());
    }
    @Test
    public void createSemicolonStudentAndSave_lengthIsOneTest(){
        SemicolonStudent student = new SemicolonStudent();
        student.setName("nnamdi");
        student.setCohort(Cohort.THIRTY);
        semicolonStudentRepository.save(student);
        assertEquals(1, semicolonStudentRepository.length());
    }

    @Test
    public void createTwoSemicolonStudentAndSave_lengthIsOneTest(){
        SemicolonStudent student = new SemicolonStudent();
        student.setName("Nnamdi");
        student.setCohort(Cohort.THIRTY);
        semicolonStudentRepository.save(student);

        SemicolonStudent student2 = new SemicolonStudent();
        student2.setName("Praise");
        student2.setCohort(Cohort.THIRTY);
        semicolonStudentRepository.save(student2);

        System.out.println(student);
        System.out.println(student2);

        assertEquals(2, semicolonStudentRepository.length());
    }

    @Test
    public void createOneStudent_findThatStudentWithId_checkThatNameIsEqualsTest(){
        SemicolonStudent student = new SemicolonStudent();
        student.setName("Benjamin");
        student.setCohort(Cohort.THIRTY);
        semicolonStudentRepository.save(student);

        SemicolonStudent foundStudent = semicolonStudentRepository.findById(student.getId());

        System.out.println(foundStudent);
        assertEquals(student.getName(), foundStudent.getName());
    }

    @Test
    public void createOneStudent_lengthIsOne_deleteStudent_lenghtIsZeroTest(){
        SemicolonStudent student = new SemicolonStudent();
        student.setName("Cj");
        student.setCohort(Cohort.THIRTY);
        semicolonStudentRepository.save(student);
        assertEquals(1, semicolonStudentRepository.length());

        semicolonStudentRepository.delete(student.getId());
        assertEquals(0, semicolonStudentRepository.length());
    }

    @Test
    public void createOneStudent_updateName_lenghtIsStillOneTest(){
        SemicolonStudent student = new SemicolonStudent();
        student.setName("Prosper");
        student.setCohort(Cohort.THIRTY);
        semicolonStudentRepository.save(student);
        assertEquals(1, semicolonStudentRepository.length());

        SemicolonStudent  foundStudent = semicolonStudentRepository.findById(student.getId());
        assertEquals(student.getName(), foundStudent.getName());

        foundStudent.setName("IsOver");
        semicolonStudentRepository.update(foundStudent);

        SemicolonStudent foundUpdatedStudent = semicolonStudentRepository.findById(student.getId());

        System.out.println(foundStudent);
        System.out.println(foundUpdatedStudent);

        assertEquals(1, semicolonStudentRepository.length());
        assertEquals("IsOver", foundUpdatedStudent.getName());
    }
}