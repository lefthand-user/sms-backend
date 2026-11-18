package net.javaguides.sms.service;

import net.javaguides.sms.dto.StudentDto;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);

    StudentDto getStudentBtId(Long studentId);
}
