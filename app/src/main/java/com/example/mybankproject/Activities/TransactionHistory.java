package com.example.mybankproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mybankproject.R;

import java.util.ArrayList;

import Adapters.TransactionHistoryAdapter;
import data.transaction;
import database.transactiondb;
import database.transactiondphelper;

public class TransactionHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<transaction> transactionArrayList;

    // Database
    private transactiondphelper dbHelper;

    TextView emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        getSupportActionBar().hide();

        // Get TextView
        emptyList = findViewById(R.id.empty_text);

        // Create Transaction History List
        transactionArrayList = new ArrayList<transaction>();

        // Create Table in the Database
        dbHelper = new transactiondphelper(this);

        // Display database info
        displayDatabaseInfo();

        recyclerView = findViewById(R.id.transactions_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new TransactionHistoryAdapter(this, transactionArrayList);
        recyclerView.setAdapter(myAdapter);
    }

    private void displayDatabaseInfo() {
        Log.d("TAG", "displayDataBaseInfo()");

        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Log.d("TAG", "displayDataBaseInfo()1");

        String[] projection = {
                transactiondb.TransactionEntry.COLUMN_FROM_NAME,
                transactiondb.TransactionEntry.COLUMN_TO_NAME,
                transactiondb.TransactionEntry.COLUMN_AMOUNT,
                transactiondb.TransactionEntry.COLUMN_STATUS
        };

        Log.d("TAG", "displayDataBaseInfo()2");

        Cursor cursor = db.query(
                transactiondb.TransactionEntry.TABLE_NAME,   // The table to query
                projection,                          // The columns to return
                null,                        // The columns for the WHERE clause
                null,                     // The values for the WHERE clause
                null,                        // Don't group the rows
                null,                         // Don't filter by row groups
                null);                       // The sort order

        try {
            // Figure out the index of each column
            int fromNameColumnIndex = cursor.getColumnIndex(transactiondb.TransactionEntry.COLUMN_FROM_NAME);
            int ToNameColumnIndex = cursor.getColumnIndex(transactiondb.TransactionEntry.COLUMN_TO_NAME);
            int amountColumnIndex = cursor.getColumnIndex(transactiondb.TransactionEntry.COLUMN_AMOUNT);
            int statusColumnIndex = cursor.getColumnIndex(transactiondb.TransactionEntry.COLUMN_STATUS);

            Log.d("TAG", "displayDataBaseInfo()3");

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                String fromName = cursor.getString(fromNameColumnIndex);
                String ToName = cursor.getString(ToNameColumnIndex);
                int accountBalance = cursor.getInt(amountColumnIndex);
                int status = cursor.getInt(statusColumnIndex);


                //Log.d("TAG", "displayDataBaseInfo()4");

                // Display the values from each column of the current row in the cursor in the TextView
                transactionArrayList.add(new transaction(fromName, ToName, accountBalance, status));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

        if (transactionArrayList.isEmpty()) {
            emptyList.setVisibility(View.VISIBLE);
        } else {
            emptyList.setVisibility(View.GONE);
        }
    }
}