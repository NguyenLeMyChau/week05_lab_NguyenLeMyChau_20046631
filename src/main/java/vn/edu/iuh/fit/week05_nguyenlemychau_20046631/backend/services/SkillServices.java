package vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.models.Skill;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.repositories.SkillRepository;

import java.util.List;

@Service
public class SkillServices {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> findALl(){
        return skillRepository.findAll();
    }

}
