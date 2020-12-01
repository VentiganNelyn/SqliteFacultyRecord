package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {

    OpenHelper openHelper;
    String stID;
    String stName;
    String stAddress;
    String stDegree;
    SQLiteDatabase sqLiteDatabase;

    EditText eID;
    EditText eName;
    EditText eAddress;
    EditText eDegree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        openHelper = new OpenHelper(this);
        sqLiteDatabase = openHelper.getWritableDatabase();

        eID = findViewById(R.id.idno_add);
        eName = findViewById(R.id.name_add);
        eAddress = findViewById(R.id.address_add);
        eDegree = findViewById(R.id.degree_add);
    }
    public void clickAdd(View view) {
        stID=eID.getText().toString();
        stName=eName.getText().toString();
        stAddress=eAddress.getText().toString();
        stDegree=eDegree.getText().toString();

        if(TextUtils.isEmpty(stID) || TextUtils.isEmpty(stName) || TextUtils.isEmpty(stAddress) || TextUtils.isEmpty(stDegree)) {
            Toast.makeText(this, "Check the Empty Fields", Toast.LENGTH_SHORT).show();
        }
        else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBInfo._ID, (byte[]) null);
            contentValues.put(DBInfo.IDNO, stID);
            contentValues.put(DBInfo.FrName, stName);
            contentValues.put(DBInfo.FrAddress, stAddress);
            contentValues.put(DBInfo.HDegree,stDegree);
            long rowId = sqLiteDatabase.insert(DBInfo.TABLE_NAME, null, contentValues);

            if (rowId != -1) {
                Toast.makeText(this, "Added",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
            else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy(){
        sqLiteDatabase.close();
        super.onDestroy();
    }
}