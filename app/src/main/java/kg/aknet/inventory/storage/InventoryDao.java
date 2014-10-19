package kg.aknet.inventory.storage;

import com.orm.query.Select;

import kg.aknet.inventory.storage.Inventory;

import static com.orm.SugarRecord.save;

/**
 * Created by tima on 19.10.14.
 */
public class InventoryDao {
    public long create() {

        Inventory last = Select.from(Inventory.class).orderBy("ID DESC").first();

        long lastId = 0;
        if (last != null) {
            lastId = last.id;
        }

        Inventory inventory = new Inventory("");
        inventory.id = lastId + 1;
        return save(inventory);
    }

}
