package in.co.srdt.mgacommonsv1.entity.mgaAssessment;

import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.AuditableBase;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaSubjectOffering;
import in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure.MgaTopic;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaAssessmentTemplate extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaAssessmentTemplateId;

    @ManyToOne
    @JoinColumn(name = "mga_topic_id", nullable = false)
    private MgaTopic mgaTopic;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    private Integer timeLimitMinutes;
    private Integer maxAttempts = 1;
    private Integer coolDownMinutes;

    private java.math.BigDecimal totalMarks;
    private java.math.BigDecimal passMarks;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    @Column(precision = 5, scale = 2)
    private java.math.BigDecimal passPercent;
}