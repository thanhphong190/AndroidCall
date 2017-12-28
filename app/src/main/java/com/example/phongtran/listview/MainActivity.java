package com.example.phongtran.listview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import android.database.sqlite.SQLiteDatabase;

import com.example.phongtran.listview.Data.DBManager;

public class MainActivity extends AppCompatActivity {
    DBManager dbManager;
    private static final int REQUEST_CODE_EDIT =123 ;
    ListView lstct;
    EditText ht,sdt;
    int vitri=-1;
    Contact contact;
    public static final String PHONE="phone";
    public static final String NAME="name";
    public static final String VITRI="";
    Button btnluu, btntim, btnupdate;
    ArrayList<Contact> arrayct;
    ArrayAdapter<Contact>arrct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dong_sinh);
        lstct=(ListView)findViewById(R.id.lstcontact);
        ht=(EditText)findViewById(R.id.editten);
        sdt=(EditText)findViewById(R.id.editsdt);
        btnluu=(Button)findViewById(R.id.btnsave);
        btntim=(Button)findViewById(R.id.btnfind);
        btnupdate= (Button)findViewById(R.id.btnUpdate);
        //DBManager dbManager=new DBManager(this);
        //dbManager.hello();
        dbManager=new DBManager(this, "contact.sqlite",null,1);
        dbManager.QueryData("CREATE TABLE IF NOT EXISTS Contact(hoten nvarchar(50) primary key, sdt integer)");
        //insert
        //dbManager.QueryData("insert into Contact values('hiep', 016847654453)");

        registerForContextMenu(lstct);
        arrayct=new ArrayList<Contact>();
        getData();
        //arrayct.add(new Contact("Phong", "0168475131"));
        //arrayct.add(new Contact("Trần", "0168475131"));
        arrct=new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, arrayct);
        lstct.setAdapter(arrct);
        arrct.notifyDataSetChanged();
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luu1();
            }
        });
        btntim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tim();
            }
        });
        lstct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String st=parent.getItemAtPosition(position).toString();
                contact= (Contact) parent.getItemAtPosition(position);
                ht.setText(contact.getHoTen()+"");
                sdt.setText(contact.getSdt()+"");
               vitri=position;
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        //niblestomno1@gmail.com
    }
    private void getData()
    {
        Cursor dataContact= dbManager.getData("select * from Contact");
        arrayct.clear();
        while (dataContact.moveToNext())
        {
            String ten=dataContact.getString(0);
            int sdt=dataContact.getInt(1);
            arrayct.add(new Contact(ten, sdt));
            //Toast.makeText(this,ten, Toast.LENGTH_LONG).show();
        }
    }
    public void update()
    {
        String ten = ht.getText().toString();
        int so = Integer.parseInt(sdt.getText().toString());
            dbManager.QueryData("Update Contact set hoten='"+ten+"' where sdt='"+so+"' ");
            Toast.makeText(this, "đã sua", Toast.LENGTH_LONG).show();
            getData();
            ht.setText("");
            sdt.setText("");
            ht.requestFocus();
    }
    public void luu1()
    {
        String ten = ht.getText().toString();
        int so = Integer.parseInt(sdt.getText().toString());
        if(ten.length()>4 && so>8)
        {
            dbManager.QueryData("insert into Contact values('"+ten+"','"+so+"')");
            Toast.makeText(this, "đã thêm", Toast.LENGTH_LONG).show();
            getData();
            ht.setText("");
            sdt.setText("");
            ht.requestFocus();
        }
        else
        {
            Toast.makeText(this, "Bạn chưa nhập thông tin", Toast.LENGTH_LONG).show();
        }
    }
    public void luu() {
        String ten = ht.getText().toString();
        int so = Integer.parseInt(sdt.getText().toString());
        if (ten.length() > 3 && so >= 9) {
            Contact contact = new Contact(ten, so);
                if (arrayct.contains(contact)) {
                    Toast.makeText(MainActivity.this, "Trùng thông tin", Toast.LENGTH_LONG).show();
                } else {
                          arrayct.add(new Contact(ten, so));
                         arrct.notifyDataSetChanged();
                       ht.setText("");
                     sdt.setText("");
                       Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                }
        }
        else
        {
            Toast.makeText(MainActivity.this, "Tên hoặc số chưa đủ", Toast.LENGTH_LONG).show();
        }
    }
    public void tim()
    {
        String t=ht.getText().toString();
        int i;
        if(!arrayct.isEmpty())
        {
            for (i=0;i<arrayct.size();i++){
                Contact ct = arrayct.get(i);
                if (t.equals(ct.getHoTen())) {
                    Toast.makeText(this, "da tìm thấy", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    arrct.remove(arrayct.get(i));
                    lstct.setAdapter(arrct);
                }
            }
            Toast.makeText(this,"Không tìm thấy", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this,"Danh sách đang trống", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save:
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        menu.setHeaderTitle("OK");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    public void send(String name, String phone)
    {
        Intent inte=new Intent(this, Main2Activity.class);
       inte.putExtra(NAME, name);
       inte.putExtra(PHONE,phone);
        startActivity(inte);
        //startActivity(inte);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.edit:
                //Intent inten=new Intent(MainActivity.this, Main2Activity.class);
                //startActivityForResult(inten, REQUEST_CODE_EDIT);
                String name=contact.getHoTen()+"";
                String phone=contact.getSdt()+"";
                send(name,phone);
                break;
            case R.id.Delete:
                Toast.makeText(MainActivity.this, "Da xoa ", Toast.LENGTH_LONG).show();
                arrayct.remove(vitri);
                arrct.notifyDataSetChanged();
                break;
            case R.id.Call:
                //String phon=sdt.getText().toString();
                String sodt=contact.getSdt()+"";
                if(!TextUtils.isEmpty(sodt)) {
                    Intent in = new Intent();
                    String dial="tel:"+sodt;
                    in.setAction(Intent.ACTION_CALL);
                    in.setData(Uri.parse(dial));
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Enter a phone number", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Sendsms:
                String sodt1=contact.getSdt()+"";
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body", "Chào bạn...");
                intent.setData(Uri.parse("sms:"+sodt1));
                startActivity(intent);
                break;

        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      if(requestCode== REQUEST_CODE_EDIT && resultCode==RESULT_OK && data !=null)
        {
          //  String t="";
           String ten=data.getStringExtra("name")+"";
            int so=data.getIntExtra("phone", 011);
            ht.setText(ten);
            sdt.setText(so);
           // t=ten+ "" + so;
            arrayct.set(vitri,new Contact(ten, so));
            arrct.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void doCreateDb()
    {
        SQLiteDatabase database;
        database = openOrCreateDatabase("qlsinhvien.db",MODE_PRIVATE,null);


    }

   // @Override
    //protected void onResume() {
     //   Connectivity mReceiver = new Connectivity();
     //   registerReceiver(mReceiver, new IntentFilter(Connectivity));
     //   super.onResume();
  //  }

  //  @Override
  //  protected void onPause() {
   //     super.onPause();
   //     unregisterReceiver();
  //  }
//}
//class Connectivity extends BroadcastReceiver
//{
  //  @Override
  //  public void onReceive(Context context, Intent intent) {
   //     Toast.makeText(context, "Connectivity", Toast.LENGTH_LONG).show();
   // }
}
