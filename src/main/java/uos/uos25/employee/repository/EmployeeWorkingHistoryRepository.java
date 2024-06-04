package uos.uos25.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uos.uos25.employee.entity.EmployeeWorkingHistory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmployeeWorkingHistoryRepository extends JpaRepository<EmployeeWorkingHistory, Long> {

    // 직원 아이디로 출퇴근 히스토리를 불러오는 메소드
    @Query("SELECT e FROM EmployeeWorkingHistory e WHERE e.employee.employeeId = :employeeId ORDER BY e.startDateTime DESC")
    Optional<List<EmployeeWorkingHistory>> findHistoriesByEmployeeId(@Param("employeeId") Long employeeId);

    // 직원 아이디로 출퇴근 히스토리를 가져오는 메소드
    Optional<List<EmployeeWorkingHistory>> findAllByEmployeeEmployeeId(Long employeeId);

    // 해당 직원이 오늘 출근 기록이 있는지 체크하는 메소드
    @Query("SELECT e FROM EmployeeWorkingHistory e WHERE e.employee.employeeId = :employeeId AND e.startDateTime >= :startOfDay AND e.startDateTime < :endOfDay")
    Optional<EmployeeWorkingHistory> findTodayWorkingHistory(@Param("employeeId") Long employeeId, @Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
