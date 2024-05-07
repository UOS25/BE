package uos.uos25.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Inventory {
    @Id
    private Integer inventoryId;

    private Integer ea;
    private LocalDateTime warehousingDate;
    private Integer display;
    private LocalDateTime expirationDate;
}
