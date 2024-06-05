package uos.uos25.returns.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import uos.uos25.returns.entity.Returns;

@Data
@AllArgsConstructor
public class ReturnsResponseDTO {
    private final Long returnsId;
    private final Integer ea;
    private final String returnsStatus;

    public static ReturnsResponseDTO fromEntity(Returns returns) {
        ReturnsResponseDTO returnsResponseDTO =
                new ReturnsResponseDTO(
                        returns.getReturnsId(), returns.getEa(), returns.getReturnsStatus());

        return returnsResponseDTO;
    }
}
