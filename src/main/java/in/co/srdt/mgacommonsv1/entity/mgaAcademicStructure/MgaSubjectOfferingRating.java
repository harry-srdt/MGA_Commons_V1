package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaSubjectOfferingRating extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectOfferingRatingId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mga_student_id")
    private MgaStudent mgaStudent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mga_subject_offering_id")
    private MgaSubjectOffering mgaSubjectOffering;

    @Min(1) @Max(5)
    @Column(nullable = false)
    private Integer ratingValue;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(columnDefinition = "bit(1) default 0")
    private Boolean isAnonymous = false;
}
