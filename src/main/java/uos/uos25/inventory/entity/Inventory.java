package uos.uos25.inventory.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;
import uos.uos25.inventory.exception.InventoryEaCannotBeLowerThanDisplayEa;
import uos.uos25.inventory.exception.InventoryEaNotEnoughException;
import uos.uos25.inventory.exception.InventoryTooManyDisplayException;
import uos.uos25.inventory.exception.TooManyProductPurchaseException;
import uos.uos25.product.entity.Product;
import uos.uos25.shop.entity.Shop;

@Entity
@NoArgsConstructor
@Getter
public class Inventory extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long inventoryId;

    @Column(nullable = false)
    private Integer ea;

    @Column(nullable = false)
    private LocalDateTime warehousingDate;

    @Column(nullable = false)
    private Integer display;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barcode", nullable = false)
    private Product product;

    @Builder
    public Inventory(
            Long inventoryId,
            Integer ea,
            LocalDateTime warehousingDate,
            Integer display,
            LocalDateTime expirationDate,
            Shop shop,
            Product product) {
        this.inventoryId = inventoryId;
        this.ea = ea;
        this.warehousingDate = warehousingDate;
        this.display = display;
        this.expirationDate = expirationDate;
        this.shop = shop;
        this.product = product;
    }

    public void display(Integer ea) {
        if (this.ea < this.display + ea) throw new InventoryTooManyDisplayException();

        this.display += ea;
    }

    public void addEa(Integer ea) {
        this.ea += ea;
    }

    public void sold(Integer ea) {
        if (this.ea < ea) throw new TooManyProductPurchaseException();
        if (this.display < ea) throw new TooManyProductPurchaseException();
        this.ea -= ea;
        this.display -= ea;
    }

    public void subtractEa(Integer ea) {
        if (this.ea < ea) throw new InventoryEaNotEnoughException();
        if (this.ea - ea < display) throw new InventoryEaCannotBeLowerThanDisplayEa();
        this.ea -= ea;
    }
}
