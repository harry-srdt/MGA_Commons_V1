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
public class MgaStudentTopicProgress extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaStudentTopicProgressId;

    @ManyToOne @JoinColumn(name = "mga_student_id", nullable = false)
    private MgaStudent mgaStudent;

    @ManyToOne
    @JoinColumn(name = "mga_subject_offering_id", nullable = false)
    private MgaSubjectOffering mgaSubjectOffering;

    @ManyToOne @JoinColumn(name = "mga_topic_id", nullable = false)
    private MgaTopic mgaTopic;

    @Column(length = 30)
    private String progressStatus;

    private java.time.LocalDateTime completedAt;

    @ManyToOne @JoinColumn(name = "mga_assessment_attempt_id")
    private MgaAssessmentAttempt completionAttempt; // the attempt that satisfied “attended”
}