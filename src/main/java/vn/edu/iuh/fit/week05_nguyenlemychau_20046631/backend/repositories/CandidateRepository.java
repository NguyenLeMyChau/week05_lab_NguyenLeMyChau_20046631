package vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.models.Candidate;
import vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.models.Skill;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

     @Query(value = "SELECT * FROM candidate WHERE full_name LIKE %:keyword%", nativeQuery = true)
     List<Candidate> findCandidateByFullName(@Param("keyword") String keyword);

     @Query(value = "select distinct c.can_id, full_name, email, phone, address, dob \n" +
             "    from candidate c join candidate_skill ck ON c.can_id = ck.can_id \n" +
             "    where skill_id IN :listSkillId\n" +
             "    group by  c.can_id, full_name, email, phone, address, dob\n" +
             "    HAVING COUNT(DISTINCT ck.skill_id) = :numSkills", nativeQuery = true)
     List<Candidate> findCandidateBySkill(@Param("listSkillId") List<Long> listSkillId, @Param("numSkills") Long numSkills);


}