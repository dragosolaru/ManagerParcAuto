package com.example.android.managerparcauto1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class AddAutoActivity extends AppCompatActivity implements OnClickListener {
    // private Button addTodoBtn;
    private EditText numarEt;
    private EditText marcaEt;
    private EditText tipEt;
    private EditText dataEt;
    private EditText soferEt;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Record");

        setContentView(R.layout.activity_add_modify_auto);

        numarEt = (EditText) findViewById(R.id.numar);
        marcaEt = (EditText) findViewById(R.id.marca);
        tipEt = (EditText) findViewById(R.id.tipul);
        dataEt = (EditText) findViewById(R.id.data);
        soferEt = (EditText) findViewById(R.id.sofer);

        // addTodoBtn = (Button) findViewById(R.id.add_record);

        //cream dinamic butonul "ADD"
        LinearLayout ll = (LinearLayout) findViewById(R.id.layout_button_add);
        ViewGroup.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button addBtn = new Button(this);
        addBtn.setText("ADD");
        addBtn.setId(R.id.add_record);
        ll.addView(addBtn, lp);

        dbManager = new DBManager(this);
        dbManager.open();

        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_record:

                final String numar = numarEt.getText().toString();
                final String marca = marcaEt.getText().toString();
                final String tip = tipEt.getText().toString();
                final String data = dataEt.getText().toString();
                final String sofer = soferEt.getText().toString();

                //creeam un obiect de tip auto
                Auto auto = new Auto(numar, marca, tip, data, sofer);

                dbManager.insertAuto(auto);

                // dbManager.insert(numar, marca, tip, data, sofer);

                Intent main = new Intent(AddAutoActivity.this, AutoturismListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}