package uos.uos25.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uos.uos25.inventory.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    public Optional<Inventory> findByShopShopIdAndProductBarcode(Long shopId, String barcode);

    public Optional<List<Inventory>> findAllByShopShopId(Long shopId);
}
