package uos.uos25.disposal.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DisposalListRequestDTO {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
}
