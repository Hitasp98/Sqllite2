package com.example.ar.sqllite3;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.content.ContextCompat;

public class myDbAdaptor {
    myDbhelper myHelper;
    public myDbAdaptor(Context context)
    {
        myHelper=new myDbhelper(context);
    }
    public  String inserData(int id,String Name,String Family)
    {
        SQLiteDatabase dbb=myHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        String s="";
        contentValues.put("id",id);
        contentValues.put("Name",Name);
        contentValues.put("Family",Family);
        try{
         long idd=dbb.insert("T",null,contentValues); }
        catch (Exception e) {s=e.getMessage();

        }return s;
    }
    public String getData()
    {
        SQLiteDatabase sqLiteDatabasea=myHelper.getWritableDatabase();
        String[] columns={"id","Name","Family"};
        Cursor cursor=sqLiteDatabasea.query("T",columns,null,null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String Name=cursor.getString(cursor.getColumnIndex("Name"));
            String Family=cursor.getString(cursor.getColumnIndex("Family"));
            buffer.append(id+" "+Name+" "+Family+"\n");
        }
        return buffer.toString();
    }
    static class myDbhelper extends SQLiteOpenHelper {
        private Context context;
        public myDbhelper(Context context) {
            super(context,"MYDB4",null,1);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try {
                String crate = "create table T(id integer primary key,Name varchar(50),Family varchar(50)";
                sqLiteDatabase.execSQL(crate);
            } catch (Exception e){
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
try{
    Message.message(context,"OnUpgrade");
    String drop="DROP TABLE IF EXISTS T";
    sqLiteDatabase.execSQL(drop);
    onCreate(sqLiteDatabase);
}catch(Exception e)
{Message.message(context,""+e);
}
        }
    }
}
