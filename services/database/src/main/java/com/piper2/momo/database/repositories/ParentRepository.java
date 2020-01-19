package com.piper2.momo.database.repositories;

import com.piper2.momo.database.models.core.StudentParent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<StudentParent, Integer> {
    Optional<StudentParent> findByTelephone(String telephone);
}
