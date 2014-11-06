package kg.aknet.inventory.storage;

import com.orm.query.Select;

import static com.orm.SugarRecord.save;

/**
 * Created by tima on 19.10.14.
 */
public class InventoryItemDao {
    public long create(long inventory_id) {

        InventoryItem last = Select.from(InventoryItem.class).orderBy("ID DESC").first();

        long lastId = 0;
        if (last != null) {
            lastId = last.id;
        }

        InventoryItem inventoryItem = new InventoryItem(inventory_id);
        inventoryItem.id = lastId + 1;
        return save(inventoryItem);
    }

}
