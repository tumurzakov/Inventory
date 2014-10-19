package kg.aknet.inventory.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.view.View;

import com.orm.SugarDb;

import kg.aknet.inventory.storage.InventoryDao;
import kg.aknet.inventory.R;


public class MainActivity extends Activity {

    final String LOG_TAG = "InventoryLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SugarDb db = new SugarDb(getApplicationContext());
        db.getDB();

        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.main_menu_items, android.R.layout.simple_list_item_1);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = null;

                if (id == 0) {
                    intent = new Intent(getApplicationContext(), InventoryActivity.class);
                    InventoryDao inventoryDao = new InventoryDao();
                    intent.putExtra("kg.aknet.inventory.inventory_id", inventoryDao.create());
                } else if (id == 1) {
                    intent = new Intent(getApplicationContext(), InventoriesActivity.class);
                } else if (id == 2) {
                    intent = new Intent(getApplicationContext(), SyncActivity.class);
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
