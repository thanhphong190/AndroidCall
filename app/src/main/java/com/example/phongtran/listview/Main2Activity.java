package com.example.phongtran.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {
    private EditText ht,sdt;
    private Button btnluu, btntthoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ht=(EditText)findViewById(R.id.editten);
        sdt=(EditText)findViewById(R.id.editsdt);
        btnluu=(Button)findViewById(R.id.btnsave);
        btntthoat=(Button)findViewById(R.id.btnthoat);
        send();
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String t=ht.getText().toString();
                int s=Integer.parseInt(sdt.getText().toString());
                Intent intent=new Intent();
               // ht.setText(intent.getStringExtra(MainActivity.NAME));
               // sdt.setText(intent.getStringExtra(MainActivity.PHONE));
                //intent.putExtra("tenmoi", t);
                //intent.putExtra("so",s);
                intent.putExtra("name", t);
                intent.putExtra("phone", s);
               setResult(RESULT_OK, intent);
                finish();
            }
        });
        btntthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void send()
    {
        Intent intent=getIntent();
        ht.setText(intent.getStringExtra(MainActivity.NAME));
        sdt.setText(intent.getStringExtra(MainActivity.PHONE));
    }
}
