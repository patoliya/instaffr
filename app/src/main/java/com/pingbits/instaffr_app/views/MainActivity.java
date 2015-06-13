package com.pingbits.instaffr_app.views;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.pingbits.instaffr_app.Model.Offer;
import com.pingbits.instaffr_app.R;
import com.pingbits.instaffr_app.adapters.OfferListAdapter;


public class MainActivity extends AppCompatActivity {

    ListView offer_list_view;
    OfferListAdapter offer_list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        offer_list_view = (ListView)findViewById(R.id.offer_list);
        offer_list_adapter = new OfferListAdapter( (new Offer().getDumyList(20)) ,this );

        offer_list_view.setAdapter(offer_list_adapter);
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
