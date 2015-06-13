package com.pingbits.instaffr_app.views;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;
import com.pingbits.greendao.Connection;
import com.pingbits.instaffr_app.BuildConfig;
import com.pingbits.instaffr_app.DbUtils;
import com.pingbits.instaffr_app.R;
import com.pingbits.instaffr_app.adapters.AddTODOAdapter;
import com.pingbits.instaffr_app.server.TodoServer;
import com.pingbits.instaffr_app.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AddTODOActivity extends AppCompatActivity {

    private AddTODOAdapter adapter;

    private int indx = 100;

    private List<Connection> conns = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        indx = getIntent().getIntExtra("index", 100);
        initGUI();
    }


    private void initGUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_clear_mtrl_alpha);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        ImageView dp = (ImageView) findViewById(R.id.dp);
        TextView name_view = (TextView) findViewById(R.id.name);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        int dp_res;
        String name;
        if (indx == 100) {
            dp_res = BuildConfig.CONNECTIONSET.equals("0") ? R.drawable.profile : R.drawable.parth;
            name = BuildConfig.CONNECTIONSET.equals("0") ? "Jaydeep" : "Parth";
            fab.setImageResource(R.drawable.ic_done_white_24dp);
        } else {
            conns = DbUtils.mConnectionDao.loadAll();
            dp_res = conns.get(indx).getDpResource();
            name = conns.get(indx).getName();
            fab.setImageResource(R.drawable.ic_send_white_24dp);
        }

        Picasso.with(this).load(dp_res)
                .transform(new CircleTransform())
                .into(dp);
        name_view.setText(name);

        final ListView listView = (ListView) findViewById(R.id.lst);
        adapter = new AddTODOAdapter(this);
        listView.setAdapter(adapter);
        setListFooter(listView);


        fab.setOnClickListener(new FabClickListener());
    }

    private void setListFooter(final ListView list) {
        View footer = getLayoutInflater().inflate(R.layout.footer_add_todo_list, list, false);
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.add("");
                list.setSelection(adapter.getCount() - 1);
            }
        });
        list.addFooterView(footer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private class FabClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (indx == 100) {
                adapter.save();
            } else {
//                TodoServer.getInstance(AddTODOActivity.this).postTodo(conns.get(indx).getName(), );
            }
            finish();
        }
    }
}
