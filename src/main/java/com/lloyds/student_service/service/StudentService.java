package com.lloyds.student_service.service;

import com.lloyds.student_service.converter.StudentConverter;
import com.lloyds.student_service.dto.StudentFindByClassroomIdDto;
import com.lloyds.student_service.exception.StudentNotFoundException;
import com.lloyds.student_service.model.Student;
import com.lloyds.student_service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentConverter studentConverter;

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) throws StudentNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public int delete(int id) {
        studentRepository.deleteById(id);
        return id;
    }
    public List<Student> getStudentByClassroomIds(StudentFindByClassroomIdDto dto) {
        return studentRepository.findStudentsByClassroomIds(dto.getClassroomIds());
    }

    public Student update(Student newStudent, Integer id) throws StudentNotFoundException {
        Optional<Student> opt = studentRepository.findById(id);
        Student student = studentConverter.convert(newStudent,
                opt.orElseThrow(() -> new StudentNotFoundException("Student not found")));
        return studentRepository.save(student);
    }


}
