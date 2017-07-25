package com.github.gopnick.testapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gopnick.testapp.database.Database;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtCounter, txtEmail;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        database = new Database(this);
        database.openDB();
        database.insert(1);

        txtCounter.setText("" + showCounter());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.txtEmail:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"anna.krepchenko@chisw.com"});
                intent.setType("message/rfc822");
                startActivity(intent);
                break;
        }
    }

    private void initView() {

        txtCounter = (TextView) findViewById(R.id.txtCounter);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtEmail.setOnClickListener(this);
    }


    private int showCounter() {
        Cursor cursor = database.showCounter();
        StringBuilder sb = new StringBuilder();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            sb.append(cursor.getInt(cursor.getColumnIndex(Database.COUNTER)));
        }

        return sb.length();
    }

    @Override
    protected void onStop() {
        super.onStop();
        database.closeDB();
    }
}
