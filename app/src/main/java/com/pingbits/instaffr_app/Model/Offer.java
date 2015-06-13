package com.pingbits.instaffr_app.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parth on 13/6/15.
 */
public class Offer {


    public String _id;

    public double lat;
    public double lng;

    // 0 = Food
    // 1 = Apparel
    public int type;

    public String shop_name;
    public String offer_title;
    public String offer_desc;

    public double dist;

    // Just gives dumy list of offer to test the app
    public List<Offer> getDumyList(int number_of_item){

        List<Offer> dumy_list = new ArrayList<>();

        for (int i=0;i<number_of_item;i++){

            Offer dumy_offer =new Offer();
            dumy_offer.shop_name = "Buy Item :"+i;
            dumy_offer.offer_title = "offer title :"+i;
            dumy_offer.offer_desc = "offer description here available Just filling up the space:"+i;
            dumy_offer.dist = i*100;

            dumy_list.add(dumy_offer);
        }
    return dumy_list;
    }

}
