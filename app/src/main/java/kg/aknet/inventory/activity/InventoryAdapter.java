package kg.aknet.inventory.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import kg.aknet.inventory.R;
import kg.aknet.inventory.storage.Inventory;

/**
 * Created by tima on 19.10.14.
 */
public class InventoryAdapter extends BaseAdapter {
    private Context context;
    private List<Inventory> data;
    private LayoutInflater mInflater;

    public InventoryAdapter(Context context, List<Inventory> data) {
        this.context = context;
        this.data = data;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.rowlayout, null);
            holder = new ViewHolder();
            holder.textView = (TextView)convertView.findViewById(R.id.label);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textView.setText(data.get(i).toString());
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }
}
