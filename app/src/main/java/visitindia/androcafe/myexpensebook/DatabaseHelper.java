package visitindia.androcafe.myexpensebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import visitindia.androcafe.vj.model.ListModel;
import visitindia.androcafe.vj.model.StatusDataModel;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDatabase.db";
    public static final String TABLE_NAME = "Expend_table";
    public static final String TABLE_NAME1 = "Status_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "Date";
    public static final String COL_3 = "ExpendOn";
    public static final String COL_4 = "ExpendMoney";

    Context context;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);

        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Date TEXT,ExpendOn TEXT,ExpendMoney TEXT)");
        db.execSQL("create table "+TABLE_NAME1+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Month text,Home text,Friends text,Ele text,groc text,med text,hot text,edu text,fit text,tra text,shop text,ins text,rec text,ent text,loan text,tax text,other text)");

        Toast.makeText(context,"table is created",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);

    }
    public boolean insertData(String Date,String ExpendOn,String ExpendMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,Date);
        contentValues.put(COL_3,ExpendOn);
        contentValues.put(COL_4,ExpendMoney);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public List<ListModel> getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);

        List<ListModel> list=new ArrayList<>();
        if(res!=null)
        {
            if(res.moveToFirst())
            {
                do {
                    int id=res.getInt(0);
                    String Date=res.getString(1);
                    String ExpendOn=res.getString(2);
                    String ExpendMoney=res.getString(3);

                    System.out.println("date"+Date+" "+ExpendOn);
                    ListModel model=new ListModel(id,Date,ExpendOn,ExpendMoney);

                    list.add(model);

                }while(res.moveToNext());
            }
        }
        return list;
    }

    public boolean updateData(String id,String Date,String ExpendOn,String ExpendMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,Date);
        contentValues.put(COL_3,ExpendOn);
        contentValues.put(COL_4,ExpendMoney);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public int deleteData (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {String.valueOf(id)});
    }

    public boolean insertStatusData(String month,String home,String friends,String ele,String groc,String med,String hot,String edu,String fit,String tra,String shop,String ins,String rec,String ent,String loan,String tax,String other ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Month",month);
        contentValues.put("Home",home);
        contentValues.put("Friends",friends);
        contentValues.put("ele",ele);
        contentValues.put("groc",groc);
        contentValues.put("med",med);
        contentValues.put("hot",hot);
        contentValues.put("edu",edu);
        contentValues.put("fit",fit);
        contentValues.put("tra",tra);
        contentValues.put("shop",shop);
        contentValues.put("ins",ins);
        contentValues.put("rec",rec);
        contentValues.put("ent",ent);
        contentValues.put("loan",loan);
        contentValues.put("tax",tax);
        contentValues.put("other",other);

        long result = db.insert(TABLE_NAME1,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public void deleteStatusData(String month)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME1,"Month = ?",new String[]{month});
        System.out.println("value deleted");
    }

    public List<StatusDataModel> getAllStatusData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME1,null);

        List<StatusDataModel> list=new ArrayList<>();
        if(res!=null)
        {
            if(res.moveToFirst())
            {
                do {
                    int id=res.getInt(0);
                    String Date=res.getString(1);


                    System.out.println("date"+Date);
                    StatusDataModel model=new StatusDataModel(id,Date);

                    list.add(model);

                }while(res.moveToNext());
            }
        }
        return list;
    }

    public List<StatusDataModel> getAllStatus() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME1,null);

        List<StatusDataModel> list=new ArrayList<>();
        if(res!=null)
        {
            if(res.moveToFirst())
            {
                do {
                    int id=res.getInt(0);
                    String Date=res.getString(1);
                    String Home=res.getString(2);
                    String Friends=res.getString(3);
                    String ele=res.getString(4);
                    String groc=res.getString(5);
                    String med=res.getString(6);
                    String hot=res.getString(7);
                    String edu=res.getString(8);
                    String fit=res.getString(9);
                    String tra=res.getString(10);
                    String shop=res.getString(11);
                    String ins=res.getString(12);
                    String rec=res.getString(13);
                    String ent=res.getString(14);
                    String loan=res.getString(15);
                    String tax=res.getString(16);
                    String othe=res.getString(17);

                    System.out.println(" "+id+"Month "+Date+" "+Home+" "+Friends+" "+ele+" "+groc+" "+med+" "+hot);
                    StatusDataModel model=new StatusDataModel(id,Date,Home,Friends,ele,groc,med,hot,edu,fit,tra,shop,ins,rec,ent,loan,tax,othe);

                    list.add(model);

                }while(res.moveToNext());
            }
        }
        return list;
    }

    public void clearAllata() {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,null,null);
        sqLiteDatabase.execSQL("delete from "+TABLE_NAME);
        sqLiteDatabase.delete(TABLE_NAME1,null,null);
        sqLiteDatabase.execSQL("delete from "+TABLE_NAME1);
        sqLiteDatabase.close();
    }

    public void deleteallprevmonthdata(int id) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME1,"ID = ?",new String[]{String.valueOf(id)});
        System.out.println(id+" value deleted");
    }
}
