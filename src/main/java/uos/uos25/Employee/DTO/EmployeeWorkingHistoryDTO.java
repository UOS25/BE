package uos.uos25.Employee.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
* 서비스 메소드 별 요구 필드
*
* findAllEmployeeWorkingHistories: 해당 직원의 출퇴근 기록을 모두 불러오는 메소드
* BE 단에서 네 가지 필드 모두 채워서 보내드립니다.
*
* addWorkingHistory: 임의로 직원의 출퇴근 기록을 추가하는 메소드
* 필요 필드: employeeId, startDateTime, endDateTime
*/

@Getter @Setter
public class EmployeeWorkingHistoryDTO {

    private Long employeeWorkingHistoryId;
    @NotNull(message = "직원 아이디를 입력하세요.")
    private Long employeeId;
    @NotNull(message = "출근 시간을 입력하세요.")
    private LocalDateTime startDateTime;
    @NotNull(message = "퇴근 시간을 입력하세요.")
    private LocalDateTime endDateTime;

}
