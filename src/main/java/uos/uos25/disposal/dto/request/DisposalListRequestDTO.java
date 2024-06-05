package uos.uos25.disposal.dto.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DisposalListRequestDTO {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
}
