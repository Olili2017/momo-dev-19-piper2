package com.piper2.momo.database.repositories;

import com.piper2.momo.database.models.core.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<List<Student>> findAllByParent(String parentId);
    Optional<Student> findByAccountNumber(long accountNumber);
}
