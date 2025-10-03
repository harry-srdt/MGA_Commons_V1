package in.co.srdt.mgacommonsv1.dto.assessmentApis;

import java.math.BigDecimal;

public record TopRankerDto(
        Integer rank,
        String name,
        BigDecimal percentage,
        String avatar) {}