package in.co.srdt.mgacommonsv1.dto.assessmentApis;

import java.math.BigDecimal;

public record TopPerformerDto(
        Integer rank,
        String name,
        BigDecimal percentage,
        String avatarUrl
) {}