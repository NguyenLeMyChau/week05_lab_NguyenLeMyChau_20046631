package vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.ids.CandidateSkillID;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.models.CandidateSkill;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillID> {
}