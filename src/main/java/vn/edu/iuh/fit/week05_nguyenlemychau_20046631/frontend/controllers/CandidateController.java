package vn.edu.iuh.fit.week05_nguyenlemychau_20046631.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.services.CandidateServices;

@Controller
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateServices candidateServices;
    @GetMapping("/list")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/candidates";
    }
}


