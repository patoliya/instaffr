package com.pingbits.instaffr_app.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parth on 13/6/15.
 */
public class Item {


    public String _id;

    public double shop_lat;
    public double shop_lng;

    // 0 = Food
    // 1 = Apparel
    public int type;

    public String shop_name;
    public String item_name;
    public String item_addedby;


    public double dist;

    // Just gives dumy list of offer to test the app
    public List<Item> getDumyList(int number_of_item){

        List<Item> dumy_list = new ArrayList<>();

        for (int i=0;i<number_of_item;i++){

            Item dumy_item =new Item();
            dumy_item.shop_name = "Buy Item :"+i;
            dumy_item.item_name = "item_name :"+i;
            dumy_item.item_addedby = "pingbits";

            dumy_list.add(dumy_item);
        }
    return dumy_list;
    }

}
