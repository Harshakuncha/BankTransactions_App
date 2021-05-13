package com.example.mybankproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.mybankproject.R;

import java.util.ArrayList;

import Adapters.userlist_adapter;
import data.user;
import database.userdb;
import database.userdbhelper;

public class users_list extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<user> userArrayList;

    // Database
    private userdbhelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        getSupportActionBar().hide();

        // Create ArrayList of Users
        userArrayList = new ArrayList<user>();

        // Create Table in the Database
        dbHelper = new userdbhelper(this);

        // Read Data from DataBase
        displayDatabaseInfo();

        // Show list of items
        recyclerView = findViewById(R.id.all_users_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new userlist_adapter(this, userArrayList);
        recyclerView.setAdapter(myAdapter);
    }

    private void displayDatabaseInfo() {
        userArrayList.clear();

        Cursor cursor = new userdbhelper(this).readAllData();

        int phoneNoColumnIndex = cursor.getColumnIndex(userdb.UserEntry.COLUMN_USER_PHONE_NO);
        int emailColumnIndex = cursor.getColumnIndex(userdb.UserEntry.COLUMN_USER_EMAIL);
        int ifscCodeColumnIndex = cursor.getColumnIndex(userdb.UserEntry.COLUMN_USER_IFSC_CODE);
        int accountNumberColumnIndex = cursor.getColumnIndex(userdb.UserEntry.COLUMN_USER_ACCOUNT_NUMBER);
        int nameColumnIndex = cursor.getColumnIndex(userdb.UserEntry.COLUMN_USER_NAME);
        int accountBalanceColumnIndex = cursor.getColumnIndex(userdb.UserEntry.COLUMN_USER_ACCOUNT_BALANCE);

        while (cursor.moveToNext()){
            String currentName = cursor.getString(nameColumnIndex);
            int accountNumber = cursor.getInt(accountNumberColumnIndex);
            String email = cursor.getString(emailColumnIndex);
            String phoneNumber = cursor.getString(phoneNoColumnIndex);
            String ifscCode = cursor.getString(ifscCodeColumnIndex);
            int accountBalance = cursor.getInt(accountBalanceColumnIndex);

            // Display the values from each column of the current row in the cursor in the TextView
            userArrayList.add(new user(currentName, accountNumber, phoneNumber, ifscCode, accountBalance, email));
        }
    }


    }
