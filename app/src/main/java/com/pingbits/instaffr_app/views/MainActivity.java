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

import java.util.ArrayList;
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

            List<String> waypoints = new ArrayList<>();
            String source_addr = "12.931106,77.623755";
            String destination_addr = "12.985756,77.603538";
            waypoints.add("12.936323,77.602465");
            waypoints.add("12.962141,77.595116");
            waypoints.add("12.971572,77.594322");
            waypoints.add("12.984138, 77.597798");

            StringBuilder sb = new StringBuilder();
            sb.append("http://maps.google.com/maps?saddr=");
            sb.append(source_addr);
            for (int i = 0; i < adapter.getCount(); ++i) {
                if (i == 0) {
                    sb.append("&daddr=");
                } else {
                    sb.append("+to:");
                }
                sb.append(waypoints.get(i));
            }
            sb.append("+to:");
            sb.append(destination_addr);

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,

//                    Uri.parse("http://maps.google.com/maps?saddr=12.931106,77.623755&daddr=12.936323,77.602465+to:12.985756,77.603538"));
                    Uri.parse(sb.toString()));
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
