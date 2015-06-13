package com.pingbits.instaffr_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pingbits.greendao.Todo;
import com.pingbits.instaffr_app.R;

import java.util.List;

/**
 * Created by parth on 13/6/15.
 */

public class BuyListAdapter extends BaseAdapter {

    List<Todo> mItems;
    Context context;

    public BuyListAdapter(List<Todo> items, Context context) {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Todo getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

            view = inflater.inflate(R.layout.item_todo, viewGroup, false);
        }

        TextView item_name_view = (TextView) view.findViewById(R.id.item_name);
        //TextView offer_title_view = (TextView)view.findViewById(R.id.offer_title);
        TextView item_addedby_view = (TextView) view.findViewById(R.id.item_addedby);

        item_name_view.setText(mItems.get(i).getTitle());
        //offer_title_view.setText(mItems.get(i).offer_title);
        String addeBy = mItems.get(i).getAddedBy();
        if (addeBy.equals("me"))
            addeBy = "";
        item_addedby_view.setText(addeBy);


        return view;
    }
}
