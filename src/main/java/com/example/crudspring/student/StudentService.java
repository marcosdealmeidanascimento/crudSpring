package com.example.crudspring.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student Id " + studentId + " does not exist"));

        if (name == null || name.isBlank()) throw new IllegalStateException("Name must not be empty");
        student.setName(name);

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
        if (studentOptional.isPresent()) throw new IllegalStateException("Email already taken");

        if (email != null && !email.isBlank()) {
            student.setEmail(email);
        }


    }

    public void deleteStudent(Long studentId) {
        boolean exits = studentRepository.existsById(studentId);
        if (!exits) {
            throw new IllegalStateException("Student Id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }
}
