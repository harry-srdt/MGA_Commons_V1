package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaCoursePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MgaCoursePlanRepository extends JpaRepository<MgaCoursePlan, Long> {
    List<MgaCoursePlan> findByMgaSubjectOffering_MgaSubjectOfferingId(Long offeringId);
}