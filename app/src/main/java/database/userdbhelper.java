package database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class userdbhelper extends SQLiteOpenHelper {
    String TABLE_NAME = userdb.UserEntry.TABLE_NAME;

    /** Name of the database file */
    private static final String DATABASE_NAME = "User.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.*/
    private static final int DATABASE_VERSION = 1;

    public userdbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_USER_TABLE =  "CREATE TABLE " + userdb.UserEntry.TABLE_NAME + " ("
                + userdb.UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " INTEGER, "
                + userdb.UserEntry.COLUMN_USER_NAME + " VARCHAR, "
                + userdb.UserEntry.COLUMN_USER_EMAIL + " VARCHAR, "
                + userdb.UserEntry.COLUMN_USER_IFSC_CODE + " VARCHAR, "
                + userdb.UserEntry.COLUMN_USER_PHONE_NO + " VARCHAR, "
                + userdb.UserEntry.COLUMN_USER_ACCOUNT_BALANCE + " INTEGER NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USER_TABLE);

        // Insert Into Table
        db.execSQL("insert into " + TABLE_NAME + " values(7860,'Viratkohli', 'virat@gmail.com','7584','7895641238', 15000)");
        db.execSQL("insert into " + TABLE_NAME + " values(5862,'Suryakumar yadav', 'sky@gmail.com','1258','8995641238', 5000)");
        db.execSQL("insert into " + TABLE_NAME + " values(7895,'Shardultakur', 'lord@gmail.com','8896','7595645896', 1000)");
        db.execSQL("insert into " + TABLE_NAME + " values(1258,'Jadeja', 'jaddu@gmail.com','7752','9995640038', 8000)");
        db.execSQL("insert into " + TABLE_NAME + " values(7410,'Rohit sharma', 'hitman@gmail.com','3669','9095648962', 7500)");
        db.execSQL("insert into " + TABLE_NAME + " values(8529,'Rishab pant', 'spiderman@gmail.com','9985','8855640238', 6500)");
        db.execSQL("insert into " + TABLE_NAME + " values(3698,'Suresh Raina', 'chinnathala@gmail.com','1207','8895640215', 4500)");
        db.execSQL("insert into " + TABLE_NAME + " values(7853,'Deepak chahar', 'deepu@gmail.com','4522','9985021539', 2500)");
        db.execSQL("insert into " + TABLE_NAME + " values(4562,'Dhawan', 'gabbar@gmail.com','6582','9309565238', 10500)");
        db.execSQL("insert into " + TABLE_NAME + " values(2365,'Kl rahuk', 'klassy@gmail.com','5450','8292591201', 9900)");
        db.execSQL("insert into " + TABLE_NAME + " values(7854,'Hardik pandya', 'kungfu@gmail.com','2656','9015641200', 9800)");
        db.execSQL("insert into " + TABLE_NAME + " values(3621,'Bhuvaneshwar kumar', 'bhuvi@gmail.com','1203','9995641999', 11000)");
        db.execSQL("insert into " + TABLE_NAME + " values(1122,'Bumrah', 'boomboom@gmail.com','5566','9119541001', 5800)");
        db.execSQL("insert into " + TABLE_NAME + " values(9512,'Shami', 'shami@gmail.com','2236','6254642205', 3500)");
        db.execSQL("insert into " + TABLE_NAME + " values(7530,'Dhoni', 'thala@gmail.com','6692','6893641266', 1010)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + userdb.UserEntry.TABLE_NAME);
            onCreate(db);
        }
    }

    public Cursor readAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + userdb.UserEntry.TABLE_NAME, null);
        return cursor;
    }

    public Cursor readParticularData (int accountNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + userdb.UserEntry.TABLE_NAME + " where " +
                userdb.UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " = " + accountNo, null);
        return cursor;
    }

    public void updateAmount(int accountNo, int amount) {
        Log.d ("TAG", "update Amount");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update " + userdb.UserEntry.TABLE_NAME + " set " + userdb.UserEntry.COLUMN_USER_ACCOUNT_BALANCE + " = " + amount + " where " +
                userdb.UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " = " + accountNo);
    }


}
