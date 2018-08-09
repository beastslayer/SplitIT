package zerocoms.splitit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.ColorSpace;
import android.os.strictmode.SqliteObjectLeakedViolation;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String Database_name="Splitit.db";

    public DatabaseHelper(Context context)
    {
        super(context, Database_name, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Query_Trip_list, Query_friend_list;
        //friend_list is the name of the godDamn friendList Table

        Query_friend_list="create table friend_list (name text, age integer, sex tinytext)";
        sqLiteDatabase.execSQL(Query_friend_list);

        //trip_list is name of table for trip_list
        Query_Trip_list="create table trip_list (SR integer primary key, name text, duration integer, Friends text)";
        sqLiteDatabase.execSQL(Query_Trip_list);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists friend_list");
        sqLiteDatabase.execSQL("drop table if exists trip_list");
        onCreate(sqLiteDatabase);

    }

    public void onCreateNewTrip(String Trip_name, List<String> friends)
    {
       SQLiteDatabase db=this.getWritableDatabase();
       String Query_trip_settlement=" create table "+Trip_name+"_settlement (spender text unique, amount int default 0)";
       db.execSQL(Query_trip_settlement);

      for (int i=0;i<friends.size();i++)
        {
            String friend_name=friends.get(i);
            String query_add_friend="alter table "+Trip_name+"_settlement add column "+friend_name+" integer";
            db.execSQL(Query_trip_settlement);
        }
      String Query_trip_summary=" create table "+Trip_name+"_summary (spender text, amount integer, purpose text, friends text)";
      db.execSQL(Query_trip_summary);
    }

    public void OnCreateNewExpense(String  Trip_name, String spender, int amount, List<String> friends,boolean newspender)
    {   SQLiteDatabase db=this.getWritableDatabase();
        int amt=amount/friends.size();
        int friendNumber=0;
        String Query_add_expense_existing_amount="update table "+ Trip_name+"_settlement set amount = amount+"+amount+" where spender like "+spender;
        String Query_add_expense_existing_friend=" update table "+Trip_name+"_settlement set "+friends.get(friendNumber)+"="+Integer.parseInt(friends.get(friendNumber).toString())+amt+" where spender ="+spender;
        String Query_add_expense_new_amount="insert into "+Trip_name+"_settlement (spender,amount) values ("+spender+','+amount+")";
        String Query_add_expense_new_friend="update table "+Trip_name+"_settlement set "+friends.get(friendNumber)+"="+ friends.get(friendNumber)+amt;
        if(newspender)
        {

            db.execSQL(Query_add_expense_new_amount);
        }
        else
        {
            db.execSQL(Query_add_expense_existing_amount);
        }
        for (friendNumber=0;friendNumber<friends.size();friendNumber++)
        {
            db.execSQL(Query_add_expense_existing_friend);
        }

    }

    public void makefriend(String name,String sex,int age)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query_make_friends="insert into friend_list values ("+name+","+age+","+sex+")";
        db.execSQL(query_make_friends);
    }

    public void deleteFriend(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String deleteFrnd="delete from friend_list where name="+name;
        db.execSQL(deleteFrnd);
    }

}
