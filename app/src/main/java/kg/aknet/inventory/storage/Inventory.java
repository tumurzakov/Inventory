package kg.aknet.inventory.storage;

import com.orm.dsl.Table;

/**
 * Created by tima on 19.10.14.
 */
@Table(name="inventories")
public class Inventory {
    public long id;
    public String warehouse;

    public Inventory() {
    }

    public Inventory(String warehouse) {
        this.warehouse = warehouse;
    }

    public String toString() {
        return String.format("%d - %s", this.id, this.warehouse);
    }
}
