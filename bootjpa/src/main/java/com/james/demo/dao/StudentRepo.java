package com.james.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.james.demo.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{
	
	//SELECT * FROM student WHERE email = ?
	//JPQL @Query("SELECT s FROM Student s WHERE s.email =?1")
	Optional<Student> findStudentByEmail(String email);

}
