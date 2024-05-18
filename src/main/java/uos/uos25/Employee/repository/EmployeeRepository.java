package uos.uos25.Employee.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uos.uos25.Employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // hire: 새 직원을 등록
    public void hire(Employee employee);

    // fire: 직원을 해고
    /**
     * 고려해야 할 점
     * employee와 receipt 테이블은 OneToMany
     * 따라서 삭제할 때 receipt의 employee_id 부분을 어떻게 할 것인지..
     * 1. 널값 처리
     * 2. cascade -> 영수증을 지움(이러면 안 되겠죠?)
     * 3. 좋은 방법이 있나요..?
     */
    public void fire(Employee employee);

    // partTime 값을 수정
    public void updatepartTime(Employee employee);

}
