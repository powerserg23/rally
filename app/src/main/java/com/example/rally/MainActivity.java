package com.example.rally;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.rally.models.Purchase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    ImageButton btAddPurchase;
    FirebaseAuth mAuth;
    FirebaseUser user;
    RecyclerView rvPurchases;
    EditText etDate;
    EditText etDescription;
    EditText etCost;
    List<Purchase> purchases;
    PurchasesAdapter purchasesAdapter;

    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        btAddPurchase = (ImageButton) findViewById(R.id.btAddPurchase);
        etDate = (EditText) findViewById(R.id.etDate);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etCost = (EditText) findViewById(R.id.etCost);
        purchases =  new ArrayList<Purchase>();
        rvPurchases = findViewById(R.id.rvPurchases);
        if(user == null)
        {
            login();
        }


        /*
        btSignOut = findViewById(R.id.btSignOut);
        btSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        });*/


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        purchasesAdapter = new PurchasesAdapter(purchases);
        rvPurchases.setAdapter(purchasesAdapter);
        rvPurchases.setLayoutManager(new LinearLayoutManager(this));
        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        purchasesAdapter.setOnItemClickListener(new PurchasesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                purchases.remove(position);
                purchasesAdapter.notifyItemRemoved(position);
            }
        });

    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        btAddPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });
    }

    private void submitData() {
        String date = etDate.getText().toString();
        Calendar cal = Calendar.getInstance();
        String description = etDescription.getText().toString();
        Double cost = Double.parseDouble(etCost.getText().toString());

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
        try {
            cal.setTime(formatter.parse(date));
        } catch (ParseException e) {
            Log.d(TAG, "Wrong date format");
            Toast.makeText(this, "You have entered a wrong date format", Toast.LENGTH_SHORT).show();
        }
        Purchase newPurch = new Purchase(description,cost,cal);
        purchases.add(newPurch);
        purchasesAdapter.notifyItemInserted(purchases.size()-1);
        clearInput();

    }

    private void clearInput() {
        etCost.setText("");
        etDate.setText("");
        etDescription.setText("");
    }

    private void login() {
        Intent i = new Intent(MainActivity.this,Login.class);
        startActivity(i);
        finish();
    }

}
