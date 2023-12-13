package vn.edu.iuh.fit.week05_nguyenlemychau_20046631.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.models.Candidate;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.models.Skill;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.services.CandidateServices;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.services.SkillServices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateServices candidateServices;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private SkillServices skillServices;

    @GetMapping("/list")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/candidates";
    }

    @GetMapping("/candidates")
    public String showCandidateListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Candidate> candidatePage= candidateServices.findAll(
                currentPage - 1,pageSize,"id","asc");
        model.addAttribute("candidatePage", candidatePage);
        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        List<Skill> skills = skillServices.findALl();
        model.addAttribute("skills", skills);

        return "candidates/candidates-paging";
    }

    @PostMapping("/candidates/byName")
    public String findCandidateByName(Model model, @RequestParam String keyword) {
        model.addAttribute("candidates", candidateServices.findCandidateByFullName(keyword));
        return "candidates/candidates";
    }

    @PostMapping("/candidates/bySkill")
    public String showFindCandidateBySkill(@RequestParam("skills") List<Long> listSkillId, Model model){
        model.addAttribute("candidates", candidateServices.findCandidateBySkill(listSkillId, (long) listSkillId.size()));
        return "candidates/candidates";
    }


    @GetMapping("/addCandidate")
    public String showFromSaveCandidate(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "candidates/addCandidate";
    }

    @PostMapping("/addCandidate")
    public String saveCandidate(@ModelAttribute("candidate") Candidate candidate) {
        candidateServices.saveCandidate(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/delete/{id}")
    public String deleteCandidateById(@PathVariable Long id) {
        candidateServices.deleteCandidate(id);
        return "redirect:/candidates";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateCandidateForm(HttpSession session, @PathVariable Long id, Model model) {
        Optional<Candidate> candidate = candidateServices.findById(id);

        candidate.ifPresent(value -> model.addAttribute("candidate", value));

        session.setAttribute("canId", id);

        return "candidates/updateCandidate";
    }

    @PostMapping("/edit")
    public String updateCandidate(HttpSession session, @ModelAttribute Candidate updateCandidate) {
        long id = (Long) session.getAttribute("canId");
        candidateServices.updateCandidate(id, updateCandidate);
        return "redirect:/candidates";
    }

    @GetMapping("/view/{id}")
    public String showInformationCandidate(@PathVariable Long id, Model model){
        Candidate candidate = candidateServices.findById(id).orElse(null);
        model.addAttribute("candidate", candidate);

        model.addAttribute("canSkill", candidateServices.findCandidateSkillById(id));

        return "candidates/informationCandidate";
    }




}


