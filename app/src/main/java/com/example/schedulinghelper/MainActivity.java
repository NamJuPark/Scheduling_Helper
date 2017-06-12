package com.example.schedulinghelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //static final int PICK_CONTACT_REQUEST = 0;
    ToDo todo;
    TabHost tabHost;
    ListView lvMon,lvTue,lvWed,lvThu,lvFri,lvSat,lvSun;
    ArrayList<ToDo> mon,tue,wed,thu,fri,sat,sun;
    WeekAdapter adapterMon,adapterTue,adapterWed,adapterThu,adapterFri,adapterSat,adapterSun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        tabHost = (TabHost)findViewById(R.id.tabHost);

        mon = new ArrayList<ToDo>();
        tue = new ArrayList<ToDo>();
        wed = new ArrayList<ToDo>();
        thu = new ArrayList<ToDo>();
        fri = new ArrayList<ToDo>();
        sat = new ArrayList<ToDo>();
        sun = new ArrayList<ToDo>();

        lvMon = (ListView)findViewById(R.id.lvMon);
        lvTue = (ListView)findViewById(R.id.lvTue);
        lvWed = (ListView)findViewById(R.id.lvWed);
        lvThu = (ListView)findViewById(R.id.lvThu);
        lvFri = (ListView)findViewById(R.id.lvFri);
        lvSat = (ListView)findViewById(R.id.lvSat);
        lvSun = (ListView)findViewById(R.id.lvSun);

        adapterMon = new WeekAdapter(mon,this);
        adapterTue = new WeekAdapter(tue,this);
        adapterWed = new WeekAdapter(wed,this);
        adapterThu = new WeekAdapter(thu,this);
        adapterFri = new WeekAdapter(fri,this);
        adapterSat = new WeekAdapter(sat,this);
        adapterSun = new WeekAdapter(sun,this);

        lvMon.setAdapter(adapterMon);
        lvTue.setAdapter(adapterTue);
        lvWed.setAdapter(adapterWed);
        lvThu.setAdapter(adapterThu);
        lvFri.setAdapter(adapterFri);
        lvSat.setAdapter(adapterSat);
        lvSun.setAdapter(adapterSun);

        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("Mon").setContent(R.id.tabMon).setIndicator("월"));
        tabHost.addTab(tabHost.newTabSpec("Tue").setContent(R.id.tabTue).setIndicator("화"));
        tabHost.addTab(tabHost.newTabSpec("Wed").setContent(R.id.tabWed).setIndicator("수"));
        tabHost.addTab(tabHost.newTabSpec("Thu").setContent(R.id.tabThu).setIndicator("목"));
        tabHost.addTab(tabHost.newTabSpec("Fri").setContent(R.id.tabFri).setIndicator("금"));
        tabHost.addTab(tabHost.newTabSpec("Sat").setContent(R.id.tabSat).setIndicator("토"));
        tabHost.addTab(tabHost.newTabSpec("Sun").setContent(R.id.tabSun).setIndicator("일"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0,1,0,"중요도 정렬");
        menu.add(0,2,0,"Go To Blog");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 1:
                break;
            case 2:
                showMyBlog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addData(ToDo todo) {
        if(todo.week[0] == true) {
            adapterMon.addToDO(todo);
        }
        if(todo.week[1] == true) adapterTue.addToDO(todo);
        if(todo.week[2] == true) adapterWed.addToDO(todo);
        if(todo.week[3] == true) adapterThu.addToDO(todo);
        if(todo.week[4] == true) adapterFri.addToDO(todo);
        if(todo.week[5] == true) adapterSat.addToDO(todo);
        if(todo.week[6] == true) adapterSun.addToDO(todo);

    }

    private void showMyBlog() {
        Intent intent = new Intent(MainActivity.this,ShowMyBlogActivity.class);
        //intent.putExtra("todo",todo);
        startActivity(intent);
    }

//    @Override
//    public void startActivityForResult(Intent intent, int requestCode) {
//        super.startActivityForResult(intent, requestCode);
//    }

    public void onClick(View v){
        Intent intent = new Intent(MainActivity.this,MakeToDOActivity.class);
        startActivity(intent);

        Log.d()
        Intent intent2 = getIntent();
        todo = intent2.getParcelableExtra("todo");

        addData(todo);
    }
}
