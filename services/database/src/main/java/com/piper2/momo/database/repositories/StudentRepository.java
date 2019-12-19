package com.piper2.momo.database.repositories;

import com.piper2.momo.database.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
