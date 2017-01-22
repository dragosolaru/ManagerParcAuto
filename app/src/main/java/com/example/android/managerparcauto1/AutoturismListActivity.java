package com.example.android.managerparcauto1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class AutoturismListActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NR_INM, DatabaseHelper.MARCA, DatabaseHelper.TIP, DatabaseHelper.DATA, DatabaseHelper.SOFER};

    final int[] to = new int[] { R.id.id, R.id.numar, R.id.marca, R.id.type, R.id.data, R.id.sofer };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();

        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView numarTextView = (TextView) view.findViewById(R.id.numar);
                TextView marcaTextView = (TextView) view.findViewById(R.id.marca);
                TextView typeTextView = (TextView) view.findViewById(R.id.type);
                TextView dataTextView = (TextView) view.findViewById(R.id.data);
                TextView soferTextView = (TextView) view.findViewById(R.id.sofer);


                String id = idTextView.getText().toString();
                String numar = numarTextView.getText().toString();
                String marca = marcaTextView.getText().toString();
                String tip = typeTextView.getText().toString();
                String data = dataTextView.getText().toString();
                String sofer = soferTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyAutoActivity.class);
                modify_intent.putExtra("numar", numar);
                modify_intent.putExtra("marca", marca);
                modify_intent.putExtra("tipul", tip);
                modify_intent.putExtra("data", data);
                modify_intent.putExtra("sofer", sofer);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddAutoActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

}

