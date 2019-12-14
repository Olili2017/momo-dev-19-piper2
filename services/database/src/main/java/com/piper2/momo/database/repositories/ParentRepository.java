package com.piper2.momo.database.repositories;

import com.piper2.momo.database.models.StudentParent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<StudentParent, Integer> {
}
