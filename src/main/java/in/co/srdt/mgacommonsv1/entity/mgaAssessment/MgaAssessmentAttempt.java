package in.co.srdt.mgacommonsv1.entity.mgaAssessment;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.AuditableBase;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaStudent;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaSubjectOffering;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaTopic;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaAssessmentAttempt extends AuditableBase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaAssessmentAttemptId;

    @ManyToOne @JoinColumn(name = "mga_student_id", nullable = false)
    private MgaStudent mgaStudent;

    @ManyToOne @JoinColumn(name = "mga_subject_offering_id", nullable = false)
    private MgaSubjectOffering mgaSubjectOffering;

    @ManyToOne
    @JoinColumn(name = "mga_topic_id", nullable = false)
    private MgaTopic mgaTopic;

    @ManyToOne @JoinColumn(name = "mga_assessment_template_id", nullable = false)
    private MgaAssessmentTemplate mgaAssessmentTemplate;

    @ManyToOne @JoinColumn(name = "core_assessment_attempt_status_id", nullable = false)
    private CoreAssessmentAttemptStatus status;

    private java.time.LocalDateTime startedAt;
    private java.time.LocalDateTime submittedAt;
    private java.time.LocalDateTime evaluatedAt;

    private Integer durationSeconds;

    private java.math.BigDecimal totalMarks;
    private java.math.BigDecimal obtainedMarks;
}