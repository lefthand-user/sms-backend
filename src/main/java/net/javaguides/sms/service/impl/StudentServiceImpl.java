package net.javaguides.sms.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.sms.dto.StudentDto;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.exception.ResourceNotFoundException;
import net.javaguides.sms.mapper.StudentMapper;
import net.javaguides.sms.repository.StudentRepository;
import net.javaguides.sms.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student does not exist with given id : " + studentId));
        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(StudentMapper::mapToStudentDto)
                        .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudent) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student does not exist by the given id: " + studentId)
        );

        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());

        Student updatedStudentObj =  studentRepository.save(student);

        return StudentMapper.mapToStudentDto(updatedStudentObj);
    }

    @Override
    public void deleteStudentByID(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student does not exist by the given id: " + studentId)
        );

        studentRepository.deleteById(studentId);
    }

    @Override
    public StudentDto getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student does not exist with email: " + email
                        )
                );

        return StudentMapper.mapToStudentDto(student);
    }
}
