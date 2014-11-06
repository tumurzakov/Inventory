package kg.aknet.inventory.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

import kg.aknet.inventory.R;
import kg.aknet.inventory.storage.InventoryDao;
import kg.aknet.inventory.storage.InventoryItem;
import kg.aknet.inventory.storage.InventoryItemDao;


public class InventoryActivity extends Activity {

    ListView lvItems;
    TextView tvInventoryId;
    long inventory_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        inventory_id = getIntent().getLongExtra("kg.aknet.inventory.inventory_id", 0);
        tvInventoryId = (TextView) findViewById(R.id.lblInventoryId);
        tvInventoryId.setText(String.valueOf(inventory_id));

        updateItems(inventory_id);
    }

    private void updateItems(long inventory_id) {
        lvItems = (ListView) findViewById(R.id.lvItems);
        List<InventoryItem> list = Select.from(InventoryItem.class)
                .where(Condition.prop("INVENTORY_ID").eq(inventory_id))
                .orderBy("ID DESC").list();
        InventoryItemAdapter adapter = new InventoryItemAdapter(getApplicationContext(), list);
        lvItems.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inventory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_inventory_add_item) {
            Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
            intent.putExtra("kg.aknet.inventory.inventory_id", inventory_id);
            startActivityForResult(intent, 1);
            return true;
        }

        if (id == R.id.action_inventory_save) {
            if (inventory_id == 0) {
                InventoryDao inventoryDao = new InventoryDao();
                inventory_id = inventoryDao.create();
                tvInventoryId.setText(String.valueOf(inventory_id));
            } else {

            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateItems(inventory_id);
    }
}
