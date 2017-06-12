package com.example.schedulinghelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MakeToDOActivity extends AppCompatActivity {
    EditText title, memo;
    RadioButton high,mid,low;
    CheckBox mon,tue,wed,thu,fri,sat,sun;

    String title_,memo_;
    int priority;
    boolean week [];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_to_do);
        init();
    }

    private void init() {
        title = (EditText)findViewById(R.id.title);
        memo = (EditText)findViewById(R.id.memo);
        high = (RadioButton)findViewById(R.id.rbHigh);
        mid = (RadioButton)findViewById(R.id.rbMid);
        low = (RadioButton)findViewById(R.id.rbLow);
        mon = (CheckBox)findViewById(R.id.cbMon);
        tue = (CheckBox)findViewById(R.id.cbTue);
        wed = (CheckBox)findViewById(R.id.cbWed);
        thu = (CheckBox)findViewById(R.id.cbThu);
        fri = (CheckBox)findViewById(R.id.cbFri);
        sat = (CheckBox)findViewById(R.id.cbSat);
        sun = (CheckBox)findViewById(R.id.cbSun);

        week = new boolean[7];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_make_to_to, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save){
            save();
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {//인텐트로 정보 보내기

        Intent intent  = getIntent();
        ToDo todo = intent.getParcelableExtra("maketodo");

        title_ = title.getText().toString();
        memo_ = memo.getText().toString();
        if(title_.equals("")){
            Toast.makeText(this,"Title을 입력해 주세요.(필수항목)",Toast.LENGTH_SHORT).show();
            return;
        }
        if(high.isChecked()){
            priority = 0;
        }else if(mid.isChecked()){
            priority = 1;
        }else if(low.isChecked()){
            priority = 2;
        }
        checkWeek();
        if(week[0] == false && week[1] == false && week[2] == false && week[3] == false && week[4] == false && week[5] == false && week[6] == false){
            Toast.makeText(this, "요일을 체크해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        todo.setTitle(title_);
        todo.setMemo(memo_);
        todo.setPriority(priority);
        for(int i= 0; i < 7; i++){
            todo.setWeek(i,week[i]);
        }

        intent.putExtra("madetodo",todo);
        setResult(RESULT_OK,intent);
        finish();

    }

    private void checkWeek() {
        if(mon.isChecked()) week[0] = true;
        else week[0] = false;

        if(tue.isChecked()) week[1] = true;
        else week[1] = false;

        if(wed.isChecked()) week[2] = true;
        else week[2] = false;

        if(thu.isChecked()) week[3] = true;
        else week[3] = false;

        if(fri.isChecked()) week[4] = true;
        else week[4] = false;

        if(sat.isChecked()) week[5] = true;
        else week[5] = false;

        if(sun.isChecked()) week[6] = true;
        else week[6] = false;

    }
}
