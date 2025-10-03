package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MgaSubjectOfferingRatingSummary {
    @Id
    private Long mgaSubjectOfferingId;

    private java.math.BigDecimal avgRating;
    private Integer ratingsCount;

    private Integer star1Count;
    private Integer star2Count;
    private Integer star3Count;
    private Integer star4Count;
    private Integer star5Count;

    private java.time.LocalDateTime lastRatingAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mga_subject_offering_id", insertable = false, updatable = false)
    private MgaSubjectOffering subjectOffering;
}
