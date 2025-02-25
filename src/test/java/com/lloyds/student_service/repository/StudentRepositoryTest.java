package com.lloyds.student_service.repository;

import com.lloyds.student_service.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testFindStudentsByClassroomIds() {
        // Given
        Student student1 = new Student(1, "John Doe", 1, "john.doe@asd.com", 1);
        Student student2 = new Student(2, "Jane Doe", 2, "jane.doe@asd.com", 1);
        studentRepository.save(student1);
        studentRepository.save(student2);

        // When
        List<Integer> classroomIds = Arrays.asList(1, 2);
        List<Student> students = studentRepository.findStudentsByClassroomIds(classroomIds);

        // Then
        assertThat(students).hasSize(2).extracting(Student::getId).containsExactlyInAnyOrder(1, 2);
    }
}