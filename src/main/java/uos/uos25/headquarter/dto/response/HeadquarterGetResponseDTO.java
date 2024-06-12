package uos.uos25.headquarter.dto.response;

import lombok.Builder;
import lombok.Data;
import uos.uos25.headquarter.entity.HeadQuarter;

@Data
public class HeadquarterGetResponseDTO {
    private final Long headquarterId;
    private final String headquarterEmployeeName;
    private final String headquarterEmployeePhoneNumber;

    @Builder
    public HeadquarterGetResponseDTO(
            Long headquarterId,
            String headquarterEmployeeName,
            String headquarterEmployeePhoneNumber) {
        this.headquarterId = headquarterId;
        this.headquarterEmployeeName = headquarterEmployeeName;
        this.headquarterEmployeePhoneNumber = headquarterEmployeePhoneNumber;
    }

    public static HeadquarterGetResponseDTO fromEntity(HeadQuarter headQuarter) {
        return HeadquarterGetResponseDTO.builder()
                .headquarterId(headQuarter.getHqEmpId())
                .headquarterEmployeeName(headQuarter.getHqEmpName())
                .headquarterEmployeePhoneNumber(headQuarter.getHqEmpHp())
                .build();
    }
}
