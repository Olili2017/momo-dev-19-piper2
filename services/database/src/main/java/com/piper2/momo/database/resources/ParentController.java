package com.piper2.momo.database.resources;

import com.piper2.momo.database.models.StudentParent;
import com.piper2.momo.database.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/momo/parent")
public class ParentController {

    @Autowired
    ParentRepository parentRepository;

    @GetMapping("/{id}")
    public StudentParent getParent(@PathVariable ("id") int id){
        return parentRepository.findById(id).isPresent() ? parentRepository.findById(id).get() : null;
    }
}
