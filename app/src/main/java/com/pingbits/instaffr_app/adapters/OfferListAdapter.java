package com.pingbits.instaffr_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pingbits.instaffr_app.Model.Offer;
import com.pingbits.instaffr_app.R;

import java.util.List;

/**
 * Created by parth on 13/6/15.
 */

public class OfferListAdapter extends BaseAdapter{

    List<Offer> offer_list;
    Context context;

    public OfferListAdapter(List<Offer> offer_list,Context context) {
        this.offer_list = offer_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return offer_list.size();
    }

    @Override
    public Object getItem(int i) {
        return offer_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);

            view = inflater.inflate(R.layout.offer_item_view,viewGroup,false);
        }

        TextView shop_name_view = (TextView)view.findViewById(R.id.shop_name);
        //TextView offer_title_view = (TextView)view.findViewById(R.id.offer_title);
        TextView shop_distance_view = (TextView)view.findViewById(R.id.shop_distance);

        shop_name_view.setText(offer_list.get(i).shop_name);
        //offer_title_view.setText(offer_list.get(i).offer_title);
        shop_distance_view.setText(Double.toString(offer_list.get(i).dist)+"m");


        return view;
    }
}
