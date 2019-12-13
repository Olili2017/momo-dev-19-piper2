package com.piper2.momo.database.repositories;

import com.piper2.momo.database.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
}
