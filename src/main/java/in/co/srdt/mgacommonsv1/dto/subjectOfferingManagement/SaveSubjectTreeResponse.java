package in.co.srdt.mgacommonsv1.dto.subjectOfferingManagement;

import java.util.List;

public record SaveSubjectTreeResponse(
        Long subjectId,
        String subjectCode,
        List<UnitOut> units
) {
    public record UnitOut(
            Long unitId,
            Integer displaySequence,
            String name,
            List<TopicOut> topics
    ) {}
    public record TopicOut(
            Long topicId,
            Integer displaySequence,
            String title,
            Integer estimatedMinutes,
            String summaryText,
            String summaryFormat
    ) {}
}