package in.co.srdt.mgacommonsv1.entity.mgaAcademicStructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class MgaTopicSummary extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mgaTopicSummaryId;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "mga_topic_id", unique = true)
    private MgaTopic mgaTopic;

    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String summaryText;

    @Column(length = 30)
    private String summaryFormat = "MARKDOWN";
}
