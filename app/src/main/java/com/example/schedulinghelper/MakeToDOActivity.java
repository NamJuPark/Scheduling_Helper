package com.example.schedulinghelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MakeToDOActivity extends AppCompatActivity {
    EditText title, memo;
    RadioButton high, mid, low;
    CheckBox mon, tue, wed, thu, fri, sat, sun;
    LinearLayout linearCB;
    ToDo todo;
    Intent intent;
    boolean changestate = false;

    String title_, memo_;
    int priority;
    boolean week[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_to_do);
        init();
    }

    private void init() {
        title = (EditText) findViewById(R.id.title);
        memo = (EditText) findViewById(R.id.memo);
        high = (RadioButton) findViewById(R.id.rbHigh);
        mid = (RadioButton) findViewById(R.id.rbMid);
        low = (RadioButton) findViewById(R.id.rbLow);

        intent = getIntent();
        changestate = intent.getBooleanExtra("state", false);

        if (changestate) {
            intent = getIntent();
            todo = intent.getParcelableExtra("changetodo");

            title.setText(todo.getTitle());
            memo.setText(todo.getMemo());

            if (todo.getPriority() == 0) high.setChecked(true);
            else if (todo.getPriority() == 1) mid.setChecked(true);
            else if (todo.getPriority() == 2) low.setChecked(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_make_to_to, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            if (changestate) {
                change();
            } else save();
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {//인텐트로 정보 보내기

        intent = getIntent();
        todo = intent.getParcelableExtra("maketodo");

        title_ = title.getText().toString();
        memo_ = memo.getText().toString();

        if (title_.equals("")) {
            Toast.makeText(this, "Title을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (memo_.equals("")) {
            Toast.makeText(this, "Memo을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (high.isChecked()) {
            priority = 0;
        } else if (mid.isChecked()) {
            priority = 1;
        } else if (low.isChecked()) {
            priority = 2;
        }

        todo.setTitle(title_);
        todo.setMemo(memo_);
        todo.setPriority(priority);

        intent.putExtra("madetodo", todo);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void change() {

        title_ = title.getText().toString();
        memo_ = memo.getText().toString();

        if (title_.equals("")) {
            Toast.makeText(this, "Title을 입력해 주세요.(필수항목)", Toast.LENGTH_SHORT).show();
            return;
        }
        if (memo_.equals("")) {
            Toast.makeText(this, "Memo을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (high.isChecked()) {
            priority = 0;
        } else if (mid.isChecked()) {
            priority = 1;
        } else if (low.isChecked()) {
            priority = 2;
        }

        todo.setTitle(title_);
        todo.setMemo(memo_);
        todo.setPriority(priority);

        intent.putExtra("changedtodo", todo);
        setResult(100, intent);
        finish();
    }

}
