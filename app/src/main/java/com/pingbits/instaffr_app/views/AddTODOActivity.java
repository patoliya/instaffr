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
import com.pingbits.instaffr_app.R;
import com.pingbits.instaffr_app.adapters.AddTODOAdapter;
import com.pingbits.instaffr_app.utils.CircleTransform;
import com.squareup.picasso.Picasso;

public class AddTODOActivity extends AppCompatActivity {

    private AddTODOAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

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
        TextView name = (TextView) findViewById(R.id.name);

        Picasso.with(this).load(R.drawable.profile)
                .transform(new CircleTransform())
                .into(dp);

        final ListView listView = (ListView) findViewById(R.id.lst);
        adapter = new AddTODOAdapter(this);
        listView.setAdapter(adapter);
        setListFooter(listView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
            adapter.save();
            finish();
        }
    }
}
