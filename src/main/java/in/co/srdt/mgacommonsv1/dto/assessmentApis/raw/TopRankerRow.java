package in.co.srdt.mgacommonsv1.dto.assessmentApis.raw;

import java.math.BigDecimal;

public interface TopRankerRow {
    Integer getRank();
    String getName();
    BigDecimal getPercentage();
    String getAvatar();
}