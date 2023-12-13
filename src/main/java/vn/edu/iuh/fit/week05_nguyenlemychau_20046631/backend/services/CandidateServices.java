package vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.models.Address;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.models.Candidate;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.models.CandidateSkill;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.models.Skill;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.repositories.CandidateSkillRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServices {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CandidateSkillRepository skillRepository;


    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy,
                                   String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);//findFirst.../findTop...
    }

    @Transactional
    public void saveCandidate(Candidate candidate) {
        addressRepository.save(candidate.getAddress());
        candidateRepository.save(candidate);
    }

    public void deleteCandidate(long id){
        candidateRepository.deleteById(id);
    }

    public Optional<Candidate> findById(long id){
        return candidateRepository.findById(id);
    }

    @Transactional
    public void updateCandidate(Long id, Candidate updatedCandidate) {
        Candidate existingCandidate = candidateRepository.findById(id).orElse(null);
        if (existingCandidate != null) {

            existingCandidate.setFullName(updatedCandidate.getFullName());
            existingCandidate.setPhone(updatedCandidate.getPhone());
            existingCandidate.setAddress(updatedCandidate.getAddress());
            existingCandidate.setDob(updatedCandidate.getDob());
            existingCandidate.setEmail(updatedCandidate.getEmail());

            addressRepository.save(existingCandidate.getAddress());

            candidateRepository.save(existingCandidate);
        }
    }

    public List<Candidate> findCandidateByFullName(String keyword){
        return candidateRepository.findCandidateByFullName(keyword);
    }

    public List<CandidateSkill> findCandidateSkillById(Long canId){
        return skillRepository.findCandidateSkillsByCanId(canId);
    }

    public List<Candidate> findCandidateBySkill(List<Long> listSkillId, Long numSkills){
        return candidateRepository.findCandidateBySkill(listSkillId, numSkills);
    }
}
