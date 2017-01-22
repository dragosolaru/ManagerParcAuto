package com.example.android.managerparcauto1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyAutoActivity extends Activity implements OnClickListener {

    private Button updateBtn, deleteBtn, sendBtn;
    private EditText nrText;
    private EditText marcaText;
    private EditText tipText;
    private EditText dataText;
    private EditText soferText;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_auto);

        dbManager = new DBManager(this);
        dbManager.open();

        nrText = (EditText) findViewById(R.id.numar);
        marcaText = (EditText) findViewById(R.id.marca);
        tipText = (EditText) findViewById(R.id.tipul);
        dataText = (EditText) findViewById(R.id.data);
        soferText = (EditText) findViewById(R.id.sofer);


        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
        sendBtn = (Button) findViewById(R.id.btn_send);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String numar = intent.getStringExtra("numar");
        String marca = intent.getStringExtra("marca");
        String type = intent.getStringExtra("tipul");
        String data = intent.getStringExtra("data");
        String sofer = intent.getStringExtra("sofer");


        _id = Long.parseLong(id);

        nrText.setText(numar);
        marcaText.setText(marca);
        tipText.setText(type);
        dataText.setText(data);
        soferText.setText(sofer);


        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        sendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String nr = nrText.getText().toString();
        String marca = marcaText.getText().toString();
        String type = tipText.getText().toString();
        String data = dataText.getText().toString();
        String sofer = soferText.getText().toString();
        String msgEmail="Autoturismul cu numarul:"+nr
                +"\nmarca: "+marca
                +"\nde tipul: "+type
                +"\ninmatriculata in data: "+data
                +"\nutilizata de: "+sofer.toUpperCase();

        switch (v.getId()) {
            case R.id.btn_update:

               dbManager.update(_id, nr, marca,type,data,sofer);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;

            case R.id.btn_send:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"Introduceti adresa de email"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Date despre autoturismul: "+nr.toUpperCase());
                i.putExtra(Intent.EXTRA_TEXT   ,msgEmail );
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), AutoturismListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}