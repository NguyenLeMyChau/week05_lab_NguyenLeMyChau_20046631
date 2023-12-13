package vn.edu.iuh.fit.week05_nguyenlemychau_20046631.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.models.Skill;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.services.SkillServices;

import java.util.List;

@Controller
public class SkillController {
    @Autowired
    private SkillServices skillServices;

    @GetMapping("/skills")
    public String showListSkill(Model model){
        List<Skill> skills = skillServices.findALl();
        model.addAttribute("skills", skills);
        return "skill/skills";
    }
}
