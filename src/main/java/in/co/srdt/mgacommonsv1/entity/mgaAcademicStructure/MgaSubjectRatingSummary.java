package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MgaSubjectRatingSummary {
    @Id
    private Long mgaSubjectId;

    private java.math.BigDecimal avgRating;
    private Integer ratingsCount;
    private java.time.LocalDateTime lastRatingAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mga_subject_id", insertable = false, updatable = false)
    private MgaSubject subject;
}
