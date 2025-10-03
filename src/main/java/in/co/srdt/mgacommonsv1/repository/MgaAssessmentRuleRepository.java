package in.co.srdt.mgacommonsv1.repository;

import in.co.srdt.mgacommonsv1.entity.mgaAssessment.MgaAssessmentRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MgaAssessmentRuleRepository extends JpaRepository<MgaAssessmentRule, Long> {
    List<MgaAssessmentRule> findAllByMgaAssessmentTemplate_MgaAssessmentTemplateId(Long templateId);
}