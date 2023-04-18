package com.james.demo.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.james.demo.dao.StudentRepo;
import com.james.demo.model.Student;

@Service
public class StudentService {
	
	private final StudentRepo studentRepo;
	
	
	@Autowired
	public StudentService(StudentRepo studentRepo) {
		this.studentRepo = studentRepo;
	}

	public List<Student> getStudent(){
		return studentRepo.findAll();
		
	}
	public void addNewStudent(Student student) {
		Optional<Student> studentByEmail = studentRepo.findStudentByEmail(student.getEmail());
		if(studentByEmail.isPresent()) {
			throw new IllegalStateException("email is taken");
		}
		studentRepo.save(student);
		
	}
	public void deleteStudent(long studentId) {
		boolean exist = studentRepo.existsById(studentId);
		if(!exist) {
			throw new IllegalStateException("student id " + studentId + " does not exist" );
		}
		studentRepo.deleteById(studentId);
	}
	
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new IllegalStateException("studentid " + studentId + " does not exist"));
		if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptional = studentRepo.findStudentByEmail(email);
			if(studentOptional.isPresent()) throw new IllegalStateException("email taken");
			student.setEmail(email);
		}
		
		
	}
	
}
