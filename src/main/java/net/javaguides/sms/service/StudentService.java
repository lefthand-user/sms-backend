package net.javaguides.sms.service;

import net.javaguides.sms.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    StudentDto getStudentBtId(Long studentId);
    List<StudentDto> getAllStudents();
}
