package uos.uos25.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import uos.uos25.user.dto.EmployeeWorkingHistoryDTO;
import uos.uos25.user.entity.EmployeeWorkingHistory;

@SpringBootTest
@Transactional
class EmployeeWorkingHistoryServiceTest {
    @Autowired EmployeeWorkingHistoryService employeeWorkingHistoryService;

    @Test
    void findAllEmployeeWorkingHistories() {
        List<EmployeeWorkingHistoryDTO> allEmployeeWorkingHistories =
                employeeWorkingHistoryService.findAllEmployeeWorkingHistories(1L);

        assertThat(allEmployeeWorkingHistories.size()).isEqualTo(1);
    }

    @Test
    void startWorking() {
        // given
        Long employeeId = 1L;

        // when
        EmployeeWorkingHistory employeeWorkingHistory = employeeWorkingHistoryService.startWorking(employeeId);


    }

    @Test
    void endWorking() {}

    @Test
    void addWorkingHistory() {}

    @Test
    void deleteEmployeeWorkingHistory() {}

    @Test
    void findByShopId() {}

    @Test
    void findWorkingHistoriesInMonth() {}
}
