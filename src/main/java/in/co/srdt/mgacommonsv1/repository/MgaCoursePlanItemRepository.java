package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaCoursePlanItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MgaCoursePlanItemRepository extends JpaRepository<MgaCoursePlanItem, Long> {
    List<MgaCoursePlanItem> findByMgaCoursePlan_MgaCoursePlanIdOrderBySequenceAsc(Long coursePlanId);
}