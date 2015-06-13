package com.pingbits.instaffr_app.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.pingbits.greendao.Todo;
import com.pingbits.instaffr_app.DbUtils;
import com.pingbits.instaffr_app.R;
import com.pingbits.instaffr_app.adapters.BuyListAdapter;
import com.pingbits.instaffr_app.server.TodoServer;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, TodoServer.TodoCallback {

    ListView listView;
    BuyListAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for navigation drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavDrawer drawer = new NavDrawer(this, toolbar);


        // for list of buying items
        listView = (ListView) findViewById(R.id.item_list);
        listView.setOnItemClickListener(this);

        // floating button at the bottom
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(listView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddTODOActivity.class);
                startActivity(intent);
            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
//                Log.d("TIMER", "calling . . .");
                TodoServer.getInstance(MainActivity.this).getLatestTodos(MainActivity.this);
            }
        }, 500, 500);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter = new BuyListAdapter(DbUtils.getAllTodos(), this);
        listView.setAdapter(adapter);
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
        if (id == R.id.action_navigate) {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr=48.74040,2.60932&daddr=48.74091,2.60728+to:48.73919,2.60781"));
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapter != null) {
            adapter.toggle(i);
        }
    }

    @Override
    public void newTodos(List<Todo> todos) {
        adapter.addAll(todos);
    }
}
