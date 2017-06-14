package com.example.schedulinghelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class showEndToDoActivity extends AppCompatActivity {
    TextView et;
    Intent intent;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_end_to_do);
        et = (TextView) findViewById(R.id.editText);
        intent = getIntent();
        filename = intent.getStringExtra("filename");
        readFile();

    }

    private void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename + ".txt"));
            String readStr = "";
            String str = null;
            while ((str = br.readLine()) != null) {
                readStr += str + "\n";
            }
            et.setText(readStr);
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
