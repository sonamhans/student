package com.lloyds.student_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lloyds.student_service.dto.StudentFindByClassroomIdDto;
import com.lloyds.student_service.model.Student;
import com.lloyds.student_service.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // Setup mock behavior for studentService if needed
    }

    @Test
    public void testGetAllStudent() throws Exception {
        // Given
        Student student1 = new Student(1, "John Doe", 1, "john.doe@asd.com", 1);
        Student student2 = new Student(2, "Jane Doe", 2, "jane.doe@asd.com", 1);
        List<Student> students = Arrays.asList(student1, student2);

        when(studentService.getAllStudent()).thenReturn(students);

        // When & Then
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[0].email", is("john.doe@asd.com")))
                .andExpect(jsonPath("$[0].classroomId", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Jane Doe")))
                .andExpect(jsonPath("$[1].email", is("jane.doe@asd.com")))
                .andExpect(jsonPath("$[1].classroomId", is(1)));
    }

    @Test
    public void testGetStudent() throws Exception {
        // Given
        Student student = new Student(1, "John Doe", 1, "john.doe@asd.com", 1);

        when(studentService.getStudentById(anyInt())).thenReturn(student);

        // When & Then
        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john.doe@asd.com")))
                .andExpect(jsonPath("$.classroomId", is(1)));
    }

    @Test
    public void testSaveStudent() throws Exception {
        // Given
        Student student = new Student(1, "John Doe", 1, "john.doe@asd.com", 1);

        when(studentService.save(any(Student.class))).thenReturn(student);

        // When & Then
        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john.doe@asd.com")))
                .andExpect(jsonPath("$.classroomId", is(1)));
    }

    @Test
    public void testGetStudentByClassroomIds() throws Exception {
        // Given
        Student student1 = new Student(1, "John Doe", 1, "john.doe@asd.com", 1);
        Student student2 = new Student(2, "Jane Doe", 2, "jane.doe@asd.com", 1);
        List<Student> students = Arrays.asList(student1, student2);
        StudentFindByClassroomIdDto dto = new StudentFindByClassroomIdDto();
        dto.setClassroomIds(Arrays.asList(1, 2));
        when(studentService.getStudentByClassroomIds(any(StudentFindByClassroomIdDto.class))).thenReturn(students);

        // When & Then
        mockMvc.perform(post("/student/find-by-classroom-ids")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[0].email", is("john.doe@asd.com")))
                .andExpect(jsonPath("$[0].classroomId", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Jane Doe")))
                .andExpect(jsonPath("$[1].email", is("jane.doe@asd.com")))
                .andExpect(jsonPath("$[1].classroomId", is(1)));;
    }
}