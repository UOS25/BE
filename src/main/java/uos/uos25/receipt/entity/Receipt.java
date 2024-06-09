package uos.uos25.receipt.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;
import uos.uos25.customer.entity.Customer;
import uos.uos25.employee.entity.Employee;

@Entity
@Getter
@NoArgsConstructor
public class Receipt extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long receiptId;

    @Column(nullable = false)
    private String purchaseStatus;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phoneNumber", nullable = true)
    private Customer customer;

    @OneToMany(mappedBy = "receipt", fetch = FetchType.LAZY)
    private List<ReceiptDetail> receiptDetails = new ArrayList<>();

    @Builder
    public Receipt(
            Long receiptId,
            String purchaseStatus,
            Integer age,
            String gender,
            Employee employee,
            Customer customer,
            List<ReceiptDetail> receiptDetails) {
        this.receiptId = receiptId;
        this.purchaseStatus = purchaseStatus;
        this.age = age;
        this.gender = gender;
        this.employee = employee;
        this.customer = customer;
        this.receiptDetails = receiptDetails;
    }

    public void cancelReceipt() {
        this.purchaseStatus = "구매포기";
    }

    public Integer getPrice() {
        return receiptDetails.stream()
                .map(
                        receiptDetail ->
                                receiptDetail.getEa()
                                        * receiptDetail.getProduct().getCustomerPrice())
                .mapToInt(Integer::intValue)
                .sum();
    }
}
