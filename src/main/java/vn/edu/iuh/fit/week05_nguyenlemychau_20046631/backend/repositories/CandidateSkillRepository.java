package vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.ids.CandidateSkillID;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.models.CandidateSkill;

import java.util.List;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillID> {

    @Query(value = "SELECT * FROM candidate_skill WHERE can_id = :canId", nativeQuery = true)
    List<CandidateSkill> findCandidateSkillsByCanId(@Param("canId") Long canId);
}