package uos.uos25.statistics.dto.response;

import lombok.Data;

@Data
public class StatisticsAgeGetResponseDTO {
    private Integer totalPrice10s = 0;
    private Integer totalPrice20s = 0;
    private Integer totalPrice30s = 0;
    private Integer totalPrice40s = 0;
    private Integer totalPrice50s = 0;
    private Integer totalPrice60s = 0;
    private Integer totalPrice70s = 0;
    private Integer totalPrice80s = 0;
    private Integer totalPrice90s = 0;

    public void matchAges(Integer age, Integer price) {
        switch (age / 10) {
            case 1:
                totalPrice10s += price;
                break;
            case 2:
                totalPrice20s += price;
                break;
            case 3:
                totalPrice30s += price;
                break;
            case 4:
                totalPrice40s += price;
                break;
            case 5:
                totalPrice50s += price;
                break;
            case 6:
                totalPrice60s += price;
                break;
            case 7:
                totalPrice70s += price;
                break;
            case 8:
                totalPrice80s += price;
                break;
            case 9:
                totalPrice90s += price;
                break;
        }
    }
}
