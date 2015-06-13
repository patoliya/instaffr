package com.pingbits.instaffr_app.adapters;


import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pingbits.instaffr_app.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

public class AddTODOAdapter extends BaseAdapter {

    private final Context context;

    private ArrayList<String> mItems;

    public AddTODOAdapter(Context context) {
        this.context = context;
        mItems = new ArrayList<>();
        mItems.add("");
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public String getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_add_todo, viewGroup, false);
        }
        MaterialEditText editText = (MaterialEditText) view.findViewById(R.id.title);
        return view;
    }


}
