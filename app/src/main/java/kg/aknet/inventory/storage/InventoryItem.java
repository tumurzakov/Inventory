package kg.aknet.inventory.storage;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

/**
 * Created by tima on 19.10.14.
 */
@Table(name="inventory_items")
public class InventoryItem {
    public long id;

    @Column(name="INVENTORY_ID")
    private long inventory_id;

    public long item_id;
    public int inventory_number;
    public String serial_number;
    public int quantity;

    public InventoryItem() {
    }

    public InventoryItem(long inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String toString() {
        return String.format("%d", this.id);
    }
}
