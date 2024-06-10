package uos.uos25.receipt.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import uos.uos25.employee.dto.EmployeeInfo;
import uos.uos25.purchase.dto.ItemInfo;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.shop.dto.ShopInfo;

@Data
public class ReceiptGetResponseDTO {
    private final Long receiptId;
    private final String shopName;
    private final String purchaseStatus;
    private final List<ItemInfo> itemInfos;
    private final EmployeeInfo employeeInfo;
    private final ShopInfo shopInfo;
    private final LocalDateTime purchaseDate;

    @Builder
    public ReceiptGetResponseDTO(
            Long receiptId,
            String shopName,
            String purchaseStatus,
            List<ItemInfo> itemInfos,
            EmployeeInfo employeeInfo,
            ShopInfo shopInfo,
            LocalDateTime purchaseDate) {
        this.receiptId = receiptId;
        this.shopName = shopName;
        this.purchaseStatus = purchaseStatus;
        this.itemInfos = itemInfos;
        this.employeeInfo = employeeInfo;
        this.shopInfo = shopInfo;
        this.purchaseDate = purchaseDate;
    }

    public static ReceiptGetResponseDTO fromEntity(Receipt receipt) {
        List<ItemInfo> itemInfos =
                receipt.getReceiptDetails().stream()
                        .map(receiptDetail -> ItemInfo.fromReceiptDetail(receiptDetail))
                        .toList();

        return ReceiptGetResponseDTO.builder()
                .receiptId(receipt.getReceiptId())
                .shopName(receipt.getEmployee().getShop().getShopName())
                .purchaseStatus(receipt.getPurchaseStatus())
                .itemInfos(itemInfos)
                .employeeInfo(
                        new EmployeeInfo(
                                receipt.getEmployee().getEmployeeId(),
                                receipt.getEmployee().getEmployeeName()))
                .shopInfo(
                        new ShopInfo(
                                receipt.getEmployee().getShop().getShopId(),
                                receipt.getEmployee().getShop().getShopName()))
                .purchaseDate(receipt.getCreatedAt())
                .build();
    }
}
