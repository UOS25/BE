package uos.uos25.statistics.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class StatisticsGenderGetResponseDTO {
    private final Integer maleTotalPrice;
    private final Integer femaleTotalPrice;

    @Builder
    public StatisticsGenderGetResponseDTO(Integer maleTotalPrice, Integer femaleTotalPrice) {
        this.maleTotalPrice = maleTotalPrice;
        this.femaleTotalPrice = femaleTotalPrice;
    }
}
