package com.lloyds.student_service.service;

import com.lloyds.student_service.converter.StudentConverter;
import com.lloyds.student_service.dto.StudentFindByClassroomIdDto;
import com.lloyds.student_service.exception.StudentNotFoundException;
import com.lloyds.student_service.model.Student;
import com.lloyds.student_service.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentConverter studentConverter;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllStudent() {
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAllStudent();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetStudentById() throws StudentNotFoundException {
        Student student = new Student();
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(1);
        assertNotNull(result);
    }

    @Test
    public void testGetStudentByIdNotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.getStudentById(1));
    }

    @Test
    public void testSave() {
        Student student = new Student();
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student result = studentService.save(student);
        assertNotNull(result);
    }

    @Test
    public void testDelete() {
        int id = 1;
        doNothing().when(studentRepository).deleteById(id);

        int result = studentService.delete(id);
        assertEquals(id, result);
    }

    @Test
    public void testGetStudentByClassroomIds() {
        List<Student> students = Arrays.asList(new Student(), new Student());
        StudentFindByClassroomIdDto dto = new StudentFindByClassroomIdDto();
        dto.setClassroomIds(Arrays.asList(1, 2));

        when(studentRepository.findStudentsByClassroomIds(dto.getClassroomIds())).thenReturn(students);

        List<Student> result = studentService.getStudentByClassroomIds(dto);
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdate() throws StudentNotFoundException {
        Student newStudent = new Student();
        Student existingStudent = new Student();
        when(studentRepository.findById(1)).thenReturn(Optional.of(existingStudent));
        when(studentConverter.convert(newStudent, existingStudent)).thenReturn(existingStudent);
        when(studentRepository.save(existingStudent)).thenReturn(existingStudent);

        Student result = studentService.update(newStudent, 1);
        assertNotNull(result);
    }

    @Test
    public void testUpdateNotFound() {
        Student newStudent = new Student();
        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.update(newStudent, 1));
    }
}