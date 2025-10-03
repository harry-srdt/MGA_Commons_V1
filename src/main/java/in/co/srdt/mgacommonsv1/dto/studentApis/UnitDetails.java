package in.co.srdt.mgacommonsv1.dto.studentApis;

import java.util.List;

public record UnitDetails(
        Long unitId,
        Integer displaySequence,
        String unitName,
        String description,
        Integer estimatedMinutes,
        String estimatedTime,
        Integer lecturesCount,
        List<TopicDetails> topics,
        Integer unitCompletionPercent,
        Integer unitCompletedTopics,
        Integer unitTotalTopics
) {}
