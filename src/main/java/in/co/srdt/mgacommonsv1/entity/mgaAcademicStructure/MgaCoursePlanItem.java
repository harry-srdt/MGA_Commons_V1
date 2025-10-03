package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaCoursePlanItem extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaCoursePlanItemId;

    @ManyToOne
    @JoinColumn(name = "mga_course_plan_id", nullable = false)
    private MgaCoursePlan mgaCoursePlan;

    @ManyToOne
    @JoinColumn(name = "mga_topic_id", nullable = false)
    private MgaTopic mgaTopic;

    @Column(nullable = false)
    private Integer sequence; // ordering within plan

    // For faculty-driven schedules
    private LocalDate scheduledDate;
    private Integer classSession;

    // For self-paced availability windows
    private LocalDate availableFrom;
    private LocalDate dueDate;
}
