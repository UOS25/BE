package uos.uos25.user.receipt.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;
import uos.uos25.user.entity.Customer;
import uos.uos25.user.entity.Employee;
import uos.uos25.user.receipt.exception.ReceiptCannotBeCanceledException;

@Entity
@Table(indexes = {@Index(name = "I_RECEIPT_01", columnList = "phone_number")})
@Getter
@NoArgsConstructor
public class Receipt extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long receiptId;

    @Column(length = 50, nullable = false)
    private String purchaseStatus;

    @Column(nullable = false)
    private Integer age;

    @Column(columnDefinition = "CHAR(6)", nullable = false)
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
        this.purchaseStatus = ReceiptStatus.CANCELED.getStatus();
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

    public void removeCustomer() {
        this.customer = null;
    }

    public void validateCanBeCanceled() {
        if (!purchaseStatus.equals(ReceiptStatus.COMPLETED.getStatus()))
            throw new ReceiptCannotBeCanceledException();
    }
}
