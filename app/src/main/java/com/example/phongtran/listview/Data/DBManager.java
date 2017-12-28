package com.example.phongtran.listview.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by phong tran on 12/27/2017.
 */

public class DBManager extends SQLiteOpenHelper {
    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //private static final String DATABASE_NAME="contact_manager";
    //private static final String TABLE_NAME="contact";
    //private static final String HOTEN="hoten";
    //private static final String SDT="sdt";
    //private static final int VERSION=1;

    //private Context context;
    //public DBManager(Context context) {
       // super(context, DATABASE_NAME, null,VERSION);
      //  this.context=context;
   // }
    //private String SQLQuery="CREATE TABLE "+TABLE_NAME+"("+
    //        HOTEN +" TEXT primary key, "+
    //        SDT +" INTEGER)";
    //truy van khog tra kq: create, insert, update
    public void QueryData(String sql)
    {
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }

    //truy van ko tra kq
    public Cursor getData(String sql)
    {
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL(SQLQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //public void hello()
    //{
     //   Toast.makeText(context,"hello", Toast.LENGTH_LONG).show();
  //  }
}
