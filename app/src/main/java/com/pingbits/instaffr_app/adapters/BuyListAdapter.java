package com.pingbits.instaffr_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pingbits.instaffr_app.Model.Item;
import com.pingbits.instaffr_app.R;

import java.util.List;

/**
 * Created by parth on 13/6/15.
 */

public class BuyListAdapter extends BaseAdapter{

    List<Item> item_list;
    Context context;

    public BuyListAdapter(List<Item> item_list, Context context) {
        this.item_list = item_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return item_list.size();
    }

    @Override
    public Object getItem(int i) {
        return item_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);

            view = inflater.inflate(R.layout.buy_item_view,viewGroup,false);
        }

        TextView item_name_view = (TextView)view.findViewById(R.id.item_name);
        //TextView offer_title_view = (TextView)view.findViewById(R.id.offer_title);
        TextView item_addedby_view = (TextView)view.findViewById(R.id.item_addedby);

        item_name_view.setText(item_list.get(i).item_name);
        //offer_title_view.setText(item_list.get(i).offer_title);
        item_addedby_view.setText(item_list.get(i).item_addedby);


        return view;
    }
}
