package com.pingbits.instaffr_app.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.pingbits.instaffr_app.Model.Item;
import com.pingbits.instaffr_app.R;
import com.pingbits.instaffr_app.adapters.BuyListAdapter;


public class MainActivity extends AppCompatActivity {

    ListView item_list_view;
    BuyListAdapter buy_list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavDrawer drawer = new NavDrawer(this, toolbar);

        item_list_view = (ListView) findViewById(R.id.item_list);
        buy_list_adapter = new BuyListAdapter((new Item().getDumyList(20)), this);

        item_list_view.setAdapter(buy_list_adapter);
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
