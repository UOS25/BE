package uos.uos25.employee.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.employee.DTO.EmployeeWorkingHistoryDTO;
import uos.uos25.employee.entity.Employee;
import uos.uos25.employee.entity.EmployeeWorkingHistory;
import uos.uos25.employee.repository.EmployeeRepository;
import uos.uos25.employee.repository.EmployeeWorkingHistoryRepository;

@Service
@RequiredArgsConstructor
public class EmployeeWorkingHistoryService {
    private final EmployeeWorkingHistoryRepository employeeWorkingHistoryRepository;
    private final EmployeeRepository employeeRepository;

    // read
    // 직원 아이디로 출퇴근 히스토리를 모두 불러옵니다.
    @Transactional
    public List<EmployeeWorkingHistoryDTO> findAllEmployeeWorkingHistories(Long employeeId) {
        List<EmployeeWorkingHistory> histories =
                employeeWorkingHistoryRepository
                        .findAllByEmployeeEmployeeId(employeeId)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                (("다음의 Id를 가진 employee가 없습니다: " + employeeId))));

        // employeeId 안넘어감
        return histories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private EmployeeWorkingHistoryDTO convertToDTO(EmployeeWorkingHistory history) {
        EmployeeWorkingHistoryDTO historyDTO = new EmployeeWorkingHistoryDTO();
        historyDTO.setEmployeeWorkingHistoryId(history.getEmployeeWorkingHistoryId());
        historyDTO.setEmployeeId(history.getEmployee().getEmployeeId());
        historyDTO.setStartDateTime(history.getStartDateTime());
        historyDTO.setEndDateTime(history.getEndDateTime());
        return historyDTO;
    }

    // create
    // 직원의 출근 처리를 완료합니다. 퇴근 시간 디폴트값은 출근 시간 + 8시간입니다.
    @Transactional
    public void startWorking(Long employeeId) {
        // 오늘의 날짜를 불러옵니다.
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);

        // 이미 오늘 출근기록이 있으면 출퇴근 기록을 만들 수 없습니다.
        if (employeeWorkingHistoryRepository
                .findTodayWorkingHistory(employeeId, startOfDay, endOfDay)
                .isPresent()) {
            throw new IllegalArgumentException("이미 오늘 출근하셨습니다.");
        }

        EmployeeWorkingHistory employeeWorkingHistory = new EmployeeWorkingHistory();
        // employeeWork에 employeeId를 세팅합니다.
        employeeWorkingHistory.setEmployee(
                employeeRepository
                        .findById(employeeId)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                (("다음의 Id를 가진 employee가 없습니다: " + employeeId)))));
        // 출근 시간에 현재 시간을 세팅합니다.
        employeeWorkingHistory.setStartDateTime(LocalDateTime.now().withNano(0));
        // 퇴근 시간에 현재로부터 8시간을 세팅합니다.
        employeeWorkingHistory.setEndDateTime(LocalDateTime.now().plusHours(8).withNano(0));
        // 일한 시간에 디폴트값인 8시간을 세팅합니다.
        employeeWorkingHistory.setWorkingHour(8L);
        // DB에 employeeWork를 저장합니다.
        employeeWorkingHistoryRepository.save(employeeWorkingHistory);
    }

    // update
    // 직원의 퇴근 처리를 완료합니다. 퇴근 시간 디폴트값을 수정합니다.
    // 예외 처리: 가져온 start 날짜가 당일이 아닌 경우.
    // 두 번 연속 퇴근처리 하는경우. -> 이 경우 퇴근시간을 늦추는 것이므로 상관 없을듯?
    @Transactional
    public void endWorking(Long employeeId) {
        // employeeId로 출퇴근 목록을 불러옵니다.
        List<EmployeeWorkingHistory> recentHistories =
                employeeWorkingHistoryRepository
                        .findHistoriesByEmployeeId(employeeId)
                        .orElseThrow(() -> new IllegalArgumentException("출근 기록이 없습니다."));

        // 빈 리스트 예외처리 (주어진 아이디의 출근 내역이 없는 경우)
        if (recentHistories.isEmpty()) {
            throw new IllegalArgumentException("주어진 직원 아이디가 존재하지 않거나, 해당 직원의 출근 기록이 없습니다.");
        }

        // 가장 최근의 출퇴근 히스토리를 선택합니다.
        EmployeeWorkingHistory recentHistory = recentHistories.get(0);
        // 가장 최근에 기록된 히스토리의 출근 시간이 지금으로부터 24시간이 경과하였다면 해당 내역을 수정할 수 없습니다.
        if (recentHistory.getStartDateTime().plusDays(1).isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("24시간 내에 출근 내역이 없습니다.");
        }

        // 퇴근 시간을 현재 시간으로 설정합니다.
        recentHistory.setEndDateTime(LocalDateTime.now().withNano(0));
        // 출근 시간부터 퇴근 시간까지 경과한 시간을 계산합니다.
        Duration workingHour =
                Duration.between(recentHistory.getStartDateTime(), LocalDateTime.now().withNano(0));
        // 일한 시간을 수정합니다.
        recentHistory.setWorkingHour(workingHour.toHours());
    }

    // create
    // 임의로 직원의 출퇴근 기록을 추가합니다.
    // 필요 필드: employeeId, startDateTime, endDateTime
    @Transactional
    public void addWorkingHistory(EmployeeWorkingHistoryDTO employeeWorkingHistoryDTO) {
        // DTO로부터 새 EmployeeWorkingHistory를 만듭니다.
        EmployeeWorkingHistory employeeWorkingHistory = new EmployeeWorkingHistory();

        Long employeeId = employeeWorkingHistoryDTO.getEmployeeId();

        // 해당 아이디를 가진 employee가 없다면 예외가 발생합니다.
        Employee employee =
                employeeRepository
                        .findById(employeeId)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "다음의 아이디를 가진 employee가 없습니다: " + employeeId));
        // employee를 입력합니다.
        employeeWorkingHistory.setEmployee(employee);

        // start, end 시간을 입력합니다.
        employeeWorkingHistory.setStartDateTime(employeeWorkingHistoryDTO.getStartDateTime());
        employeeWorkingHistory.setEndDateTime(employeeWorkingHistoryDTO.getEndDateTime());
        // start, end 사이의 시간을 계산합니다.
        Duration duration =
                Duration.between(
                        employeeWorkingHistory.getStartDateTime(),
                        employeeWorkingHistory.getEndDateTime());
        // 음수 시간 예외처리
        if (duration.isNegative()) {
            throw new IllegalArgumentException("퇴근 시간은 출근 시간보다 빠를 수 없습니다.");
        }
        // Duration 타입을 Long 타입으로 변환하고 시간만 가져옵니다.
        Long workingHour = duration.toHours();
        if (workingHour >= 24) {
            throw new IllegalArgumentException("근무 시간은 24시간을 넘을 수 없습니다.");
        }
        // 일한 시간을 입력합니다.
        employeeWorkingHistory.setWorkingHour(workingHour);
        // DB에 저장합니다.
        employeeWorkingHistoryRepository.save(employeeWorkingHistory);
    }

    // delete
    // 선택한 history를 삭제합니다.
    @Transactional
    public void deleteEmployeeWorkingHistory(Long employeeWorkingHistoryId) {
        EmployeeWorkingHistory findEmployeeWorkingHistory =
                employeeWorkingHistoryRepository
                        .findById(employeeWorkingHistoryId)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "다음의 Id를 가진 employeeWorkingHistory가 없습니다: "
                                                        + employeeWorkingHistoryId));

        employeeWorkingHistoryRepository.delete(findEmployeeWorkingHistory);
    }
}
