package com.example.mybankproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mybankproject.R;

public class HomeActivity extends AppCompatActivity {

    Button viewCust,viewTrans;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        viewCust=findViewById(R.id.viewcustomers);
        viewTrans=findViewById(R.id.viewTransactions);

    }
    public void showallusers(View view)
    {
        Intent it=new Intent(this,users_list.class);
        startActivity(it);


    }

    public void showtransactions(View view)
    {
        Intent it1=new Intent(this,TransactionHistory.class);
        startActivity(it1);
    }
}