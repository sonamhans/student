package com.lloyds.student_service.converter;

import com.lloyds.student_service.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

    public Student convert(Student newStudent, Student student) {
        if (newStudent.getName() != null) {
            student.setName(newStudent.getName());
        }
        if (newStudent.getEmail() != null) {
            student.setEmail(newStudent.getEmail());
        }
        if (newStudent.getClassroomId() != null) {
            student.setClassroomId(newStudent.getClassroomId());
        }
        if (newStudent.getAge() != null) {
            student.setAge(newStudent.getAge());
        }
        return student;
    }

}
