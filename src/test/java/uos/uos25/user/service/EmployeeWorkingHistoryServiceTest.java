package uos.uos25.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import uos.uos25.user.dto.EmployeeWorkingHistoryDTO;
import uos.uos25.user.entity.Employee;
import uos.uos25.user.entity.EmployeeWorkingHistory;
import uos.uos25.user.repository.EmployeeWorkingHistoryRepository;

@SpringBootTest
@Transactional
class EmployeeWorkingHistoryServiceTest {
    @Autowired EmployeeWorkingHistoryService employeeWorkingHistoryService;
    @Autowired EmployeeWorkingHistoryRepository employeeWorkingHistoryRepository;
    @Autowired EmployeeService employeeService;

    @Test
    void findAllEmployeeWorkingHistories() {
        // given
        Employee employee = employeeService.findById(1L);

        EmployeeWorkingHistory employeeWorkingHistory =
                EmployeeWorkingHistory.builder()
                        .employee(employee)
                        .workingHour(8L)
                        .startDateTime(LocalDateTime.now())
                        .endDateTime(LocalDateTime.now())
                        .build();

        EmployeeWorkingHistory savedEmployeeWorkingHistory =
                employeeWorkingHistoryRepository.save(employeeWorkingHistory);

        // when
        List<EmployeeWorkingHistoryDTO> allEmployeeWorkingHistories =
                employeeWorkingHistoryService.findAllEmployeeWorkingHistories(1L);

        // then
        assertThat(allEmployeeWorkingHistories.size()).isEqualTo(1);
    }

    @Test
    void startWorking() {
        // given
        Long employeeId = 1L;

        // when
        EmployeeWorkingHistory employeeWorkingHistory =
                employeeWorkingHistoryService.startWorking(employeeId);

        // then
        assertThat(employeeWorkingHistory.getStartDateTime()).isNotNull();
        assertThat(employeeWorkingHistory.getEndDateTime()).isNotNull();
        assertThat(employeeWorkingHistory.getWorkingHour()).isEqualTo(8);
    }

    @Test
    void endWorking() {
        // given



        // when

        // then
    }

    @Test
    void addWorkingHistory() {}

    @Test
    void deleteEmployeeWorkingHistory() {}

    @Test
    void findByShopId() {}

    @Test
    void findWorkingHistoriesInMonth() {}
}
