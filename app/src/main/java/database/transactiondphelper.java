package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class transactiondphelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "transaction.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.*/
    private static final int DATABASE_VERSION = 1;

    public transactiondphelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_TRANSACTION_TABLE =  "CREATE TABLE " + transactiondb.TransactionEntry.TABLE_NAME + " ("
                + transactiondb.TransactionEntry.COLUMN_FROM_NAME + " VARCHAR, "
                + transactiondb.TransactionEntry.COLUMN_TO_NAME + " VARCHAR, "
                + transactiondb.TransactionEntry.COLUMN_AMOUNT + " INTEGER, "
                + transactiondb.TransactionEntry.COLUMN_STATUS + " INTEGER);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_TRANSACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + transactiondb.TransactionEntry.TABLE_NAME);
            onCreate(db);
        }
    }

    public boolean insertTransferData (String fromName, String toName, String amount, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(transactiondb.TransactionEntry.COLUMN_FROM_NAME, fromName);
        contentValues.put(transactiondb.TransactionEntry.COLUMN_TO_NAME, toName);
        contentValues.put(transactiondb.TransactionEntry.COLUMN_AMOUNT, amount);
        contentValues.put(transactiondb.TransactionEntry.COLUMN_STATUS, status);
        Long result = db.insert(transactiondb.TransactionEntry.TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }
}
