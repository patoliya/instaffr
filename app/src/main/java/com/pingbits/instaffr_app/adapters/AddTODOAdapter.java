package com.pingbits.instaffr_app.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pingbits.greendao.Todo;
import com.pingbits.instaffr_app.DbUtils;
import com.pingbits.instaffr_app.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

public class AddTODOAdapter extends BaseAdapter {

    private final Context context;

    private ArrayList<String> mItems;

    private boolean focus = false;

    public AddTODOAdapter(Context context) {
        this.context = context;
        mItems = new ArrayList<>();
        mItems.add("");
    }

    public void add(String item) {
        mItems.add(item);
        focus = true;
        notifyDataSetChanged();
    }

    public void add(int index, String item) {
        mItems.add(index, item);
        notifyDataSetChanged();
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
    public View getView(final int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_add_todo, viewGroup, false);
        }
        final MaterialEditText editText = (MaterialEditText) view.findViewById(R.id.title);
        editText.setText(mItems.get(position));
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    mItems.set(position, editText.getText().toString());
                }
            }
        });
        if (focus && position == (mItems.size() - 1)) {
            editText.requestFocus();
        }
        return view;
    }


    /**
     * Writes to database
     */
    public void save() {
        ArrayList<Todo> todos = new ArrayList<>(mItems.size());
        for (int i = 0; i < mItems.size(); ++i) {
            String title = mItems.get(i);
            if (title == null || title.trim().isEmpty()) {
                continue;
            }
            todos.add(new Todo(null, title, "me"));
        }
        DbUtils.mTodoDao.insertInTx(todos);
    }
}
