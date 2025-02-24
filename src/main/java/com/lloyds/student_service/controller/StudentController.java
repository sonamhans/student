package com.lloyds.student_service.controller;

import com.lloyds.student_service.dto.StudentFindByClassroomIdDto;
import com.lloyds.student_service.exception.StudentNotFoundException;
import com.lloyds.student_service.model.Student;
import com.lloyds.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;
    @GetMapping ("/students")
    public List<Student> getAllStudent()
    {
        return studentService.getAllStudent();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) throws StudentNotFoundException {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @PostMapping("/student")
    private ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.OK);
    }

    @PostMapping("/student/find-by-classroom-ids")
    public List<Student> getStudentByClassroomIds(@RequestBody StudentFindByClassroomIdDto dto) {
        return studentService.getStudentByClassroomIds(dto);
    }

}
