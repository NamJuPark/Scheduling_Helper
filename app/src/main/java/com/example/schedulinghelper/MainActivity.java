package com.example.schedulinghelper;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final int PICK_CONTACT_REQUEST = 0;
    static final int PICK_CONTACT_REQUEST2 = 1;

    Intent intent;
    ToDo changedtodo;
    TabHost tabHost;
    int addDay;
    int changeDay, changeIndex;
    String filename;
    DbOpenHelper mDbOpenHelper;
    Cursor mCursor;
    ListView lvMon, lvTue, lvWed, lvThu, lvFri, lvSat, lvSun;
    ArrayList<ToDo> mon, tue, wed, thu, fri, sat, sun;
    WeekAdapter adapterMon, adapterTue, adapterWed, adapterThu, adapterFri, adapterSat, adapterSun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost) findViewById(R.id.tabHost);

        lvMon = (ListView) findViewById(R.id.lvMon);
        lvTue = (ListView) findViewById(R.id.lvTue);
        lvWed = (ListView) findViewById(R.id.lvWed);
        lvThu = (ListView) findViewById(R.id.lvThu);
        lvFri = (ListView) findViewById(R.id.lvFri);
        lvSat = (ListView) findViewById(R.id.lvSat);
        lvSun = (ListView) findViewById(R.id.lvSun);

        init();

        lvClickEvent();
    }

     private void lvClickEvent() {
        lvMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {//수정
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                changeDay = 0;
                changeIndex = i;
                intent.putExtra("state", true);
                intent.putExtra("changetodo", mon.get(i));
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });
        lvMon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int item, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mDbOpenHelper.deleteInMon(mon.get(item).getTitle());
                                adapterMon.delToDo(item);
                            }
                        })
                        .setNegativeButton("N", null)
                        .show();
                return true;
            }
        });
        lvTue.setOnItemClickListener(new AdapterView.OnItemClickListener() {//수정
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                changeDay = 1;
                changeIndex = i;
                intent.putExtra("state", true);
                intent.putExtra("changetodo", tue.get(i));

                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });
        lvTue.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int item, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mDbOpenHelper.deleteInTue(tue.get(item).getTitle());
                                adapterTue.delToDo(item);
                            }
                        })
                        .setNegativeButton("N", null)
                        .show();
                return true;
            }
        });
        lvWed.setOnItemClickListener(new AdapterView.OnItemClickListener() {//수정
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                changeDay = 2;
                changeIndex = i;
                intent.putExtra("state", true);
                intent.putExtra("changetodo", wed.get(i));

                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });
        lvWed.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int item, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mDbOpenHelper.deleteInWed(wed.get(item).getTitle());
                                adapterWed.delToDo(item);
                            }
                        })
                        .setNegativeButton("N", null)
                        .show();
                return true;
            }
        });
        lvThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {//수정
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                changeDay = 3;
                changeIndex = i;
                intent.putExtra("state", true);
                intent.putExtra("changetodo", thu.get(i));

                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });
        lvThu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int item, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mDbOpenHelper.deleteInThu(thu.get(item).getTitle());
                                adapterThu.delToDo(item);
                            }
                        })
                        .setNegativeButton("N", null)
                        .show();
                return true;
            }
        });
        lvFri.setOnItemClickListener(new AdapterView.OnItemClickListener() {//수정
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                changeDay = 4;
                changeIndex = i;
                intent.putExtra("state", true);
                intent.putExtra("changetodo", fri.get(i));

                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });
        lvFri.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int item, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mDbOpenHelper.deleteInFri(fri.get(item).getTitle());
                                adapterFri.delToDo(item);
                            }
                        })
                        .setNegativeButton("N", null)
                        .show();
                return true;
            }
        });
        lvSat.setOnItemClickListener(new AdapterView.OnItemClickListener() {//수정
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                changeDay = 5;
                changeIndex = i;
                intent.putExtra("state", true);
                intent.putExtra("changetodo", sat.get(i));

                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });
        lvSat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int item, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mDbOpenHelper.deleteInSat(sat.get(item).getTitle());
                                adapterSat.delToDo(item);
                            }
                        })
                        .setNegativeButton("N", null)
                        .show();
                return true;
            }
        });
        lvSun.setOnItemClickListener(new AdapterView.OnItemClickListener() {//수정
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                changeDay = 6;
                changeIndex = i;
                intent.putExtra("state", true);
                intent.putExtra("changetodo", sun.get(i));

                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });
        lvSun.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int item, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mDbOpenHelper.deleteInSun(sun.get(item).getTitle());
                                adapterSun.delToDo(item);
                            }
                        })
                        .setNegativeButton("N", null)
                        .show();
                return true;
            }
        });
    }

    private void init() {
        filename = "";

        checkpermission();

        mon = new ArrayList<ToDo>();
        tue = new ArrayList<ToDo>();
        wed = new ArrayList<ToDo>();
        thu = new ArrayList<ToDo>();
        fri = new ArrayList<ToDo>();
        sat = new ArrayList<ToDo>();
        sun = new ArrayList<ToDo>();

        adapterMon = new WeekAdapter(mon, this);
        adapterTue = new WeekAdapter(tue, this);
        adapterWed = new WeekAdapter(wed, this);
        adapterThu = new WeekAdapter(thu, this);
        adapterFri = new WeekAdapter(fri, this);
        adapterSat = new WeekAdapter(sat, this);
        adapterSun = new WeekAdapter(sun, this);

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

        String path = getExternalPath();
        File file = new File(path + "Scheduling Helper");
        file.mkdir();

        mDbOpenHelper = new DbOpenHelper(this);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setListView();
    }

    private void setListView() {
        ToDo todo = new ToDo("","",0);
        mCursor = null;
        mCursor = mDbOpenHelper.getAllMon();
        while (mCursor.moveToNext())
        {
            todo.setTitle(mCursor);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 1, 0, "중요도 정렬");
        menu.add(0, 2, 0, "완료 일정 정리");
        menu.add(0, 3, 0, "Go To Blog");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                break;
            case 2:
                ArrayList<String> endTodoArr = makeEndList();
                writeFile(endTodoArr);
                Intent intent2 = new Intent(MainActivity.this,showEndToDoActivity.class);
                intent2.putExtra("filename",filename);
                startActivity(intent2);
                break;
            case 3:
                showMyBlog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkpermission() {
        int permissioninfo = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissioninfo == PackageManager.PERMISSION_GRANTED){}
        else {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(this, "어플리케이션 설정에서 저장소 사용 권한을 허용해주세요",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }
    }

    public String getExternalPath() {
        String sdPath = "";
        String ext = Environment.getExternalStorageState();
        if (ext.equals(Environment.MEDIA_MOUNTED)) {
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        } else
            sdPath = getFilesDir() + "";
        return sdPath;
    }

    private void writeFile(ArrayList<String> endTodoArr) {
        try {
            String path = getExternalPath();
            filename = path + "Scheduling Helper/" + filename();
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            for (int i = 0; i < endTodoArr.size(); i++) {
                bw.write(endTodoArr.get(i));
            }
            bw.close();
            Toast.makeText(this, "저장완료", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String filename() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        return dateFormat.format(date).toString() + ".txt";
    }

    private ArrayList<String> makeEndList() {
        ArrayList<String> endTodoArr = new ArrayList<String>();
        String str = "";
        if (mon != null) {
            ToDo endTodo;
            SparseBooleanArray checkedItems = lvMon.getCheckedItemPositions();
            int count = lvMon.getCheckedItemCount();

            for (int i = 0; i < count; i++) {
                if (checkedItems.get(i)) {
                    endTodo = mon.get(i);
                    str += endTodo.getTitle() + " / " + endTodo.getMemo();

                    if (endTodo.getPriority() == 0) str += " / 상 / ";
                    else if (endTodo.getPriority() == 1) str += " / 중 / ";
                    else if (endTodo.getPriority() == 2) str += " / 하 / ";

                    str += "월요일\n";
                    endTodoArr.add(str);
                    str = "";
                }
            }
        }
        if (tue != null) {
            ToDo endTodo;
            SparseBooleanArray checkedItems = lvTue.getCheckedItemPositions();
            int count = lvTue.getCheckedItemCount();

            for (int i = 0; i < count; i++) {
                if (checkedItems.get(i)) {
                    endTodo = tue.get(i);
                    str += endTodo.getTitle() + " / " + endTodo.getMemo();
                    if (endTodo.getPriority() == 0) str += " / 상 / ";
                    else if (endTodo.getPriority() == 1) str += " / 중 / ";
                    else if (endTodo.getPriority() == 2) str += " / 하 / ";
                    str += "화요일\n";
                    endTodoArr.add(str);
                    str = "";
                }
            }
        }
        if (wed != null) {
            ToDo endTodo;
            SparseBooleanArray checkedItems = lvWed.getCheckedItemPositions();
            int count = lvWed.getCheckedItemCount();

            for (int i = 0; i < count; i++) {
                if (checkedItems.get(i)) {
                    endTodo = wed.get(i);
                    str += endTodo.getTitle() + " / " + endTodo.getMemo();
                    if (endTodo.getPriority() == 0) str += " / 상 / ";
                    else if (endTodo.getPriority() == 1) str += " / 중 / ";
                    else if (endTodo.getPriority() == 2) str += " / 하 / ";
                    str += "수요일\n";
                    endTodoArr.add(str);
                    str = "";
                }
            }
        }
        if (thu != null) {
            ToDo endTodo;
            SparseBooleanArray checkedItems = lvThu.getCheckedItemPositions();
            int count = lvThu.getCheckedItemCount();

            for (int i = 0; i < count; i++) {
                if (checkedItems.get(i)) {
                    endTodo = thu.get(i);
                    str += endTodo.getTitle() + " / " + endTodo.getMemo();
                    if (endTodo.getPriority() == 0) str += " / 상 / ";
                    else if (endTodo.getPriority() == 1) str += " / 중 / ";
                    else if (endTodo.getPriority() == 2) str += " / 하 / ";
                    str += "목요일\n";
                    endTodoArr.add(str);
                    str = "";
                }
            }
        }
        if (fri != null) {
            ToDo endTodo;
            SparseBooleanArray checkedItems = lvFri.getCheckedItemPositions();
            int count = lvFri.getCheckedItemCount();

            for (int i = 0; i < count; i++) {
                if (checkedItems.get(i)) {
                    endTodo = fri.get(i);
                    str += endTodo.getTitle() + " / " + endTodo.getMemo();
                    if (endTodo.getPriority() == 0) str += " / 상 / ";
                    else if (endTodo.getPriority() == 1) str += " / 중 / ";
                    else if (endTodo.getPriority() == 2) str += " / 하 / ";
                    str += "금요일\n";
                    endTodoArr.add(str);
                    str = "";
                }
            }
        }
        if (sat != null) {
            ToDo endTodo;
            SparseBooleanArray checkedItems = lvSat.getCheckedItemPositions();
            int count = lvSat.getCheckedItemCount();

            for (int i = 0; i < count; i++) {
                if (checkedItems.get(i)) {
                    endTodo = sat.get(i);
                    str += endTodo.getTitle() + " / " + endTodo.getMemo();
                    if (endTodo.getPriority() == 0) str += " / 상 / ";
                    else if (endTodo.getPriority() == 1) str += " / 중 / ";
                    else if (endTodo.getPriority() == 2) str += " / 하 / ";
                    str += "토요일\n";
                    endTodoArr.add(str);
                    str = "";
                }
            }
        }
        if (sun != null) {
            ToDo endTodo;
            SparseBooleanArray checkedItems = lvSun.getCheckedItemPositions();
            int count = lvSun.getCheckedItemCount();

            for (int i = 0; i < count; i++) {
                if (checkedItems.get(i)) {
                    endTodo = sun.get(i);
                    str += endTodo.getTitle() + " / " + endTodo.getMemo();
                    if (endTodo.getPriority() == 0) str += " / 상 / ";
                    else if (endTodo.getPriority() == 1) str += " / 중 / ";
                    else if (endTodo.getPriority() == 2) str += " / 하 / ";
                    str += "일요일\n";
                    endTodoArr.add(str);
                    str = "";
                }
            }
        }

        return endTodoArr;
    }

    private void addData(ToDo todo) {
        if (addDay == 0) {
            if(checkTitle(mon, todo.getTitle())){
                mDbOpenHelper.INSERTInMon(todo.getTitle(),todo.getMemo(),todo.getPriority());
                adapterMon.addToDO(todo);
            }
            else{
                Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
            }
        }
        if (addDay == 1) {
            if(checkTitle(tue, todo.getTitle())){
                mDbOpenHelper.INSERTInTue(todo.getTitle(),todo.getMemo(),todo.getPriority());
                adapterTue.addToDO(todo);
            }
            else{
                Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
            }

        }
        if (addDay == 2) {
            if(checkTitle(tue, todo.getTitle())){
                mDbOpenHelper.INSERTInWed(todo.getTitle(),todo.getMemo(),todo.getPriority());
                adapterWed.addToDO(todo);
            }
            else{
                Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
            }

        }
        if (addDay == 3) {
            if(checkTitle(tue, todo.getTitle())){
                mDbOpenHelper.INSERTInThu(todo.getTitle(),todo.getMemo(),todo.getPriority());
                adapterThu.addToDO(todo);
            }
            else{
                Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
            }
        }
        if (addDay == 4) {
            if(checkTitle(tue, todo.getTitle())){
                mDbOpenHelper.INSERTInFri(todo.getTitle(),todo.getMemo(),todo.getPriority());
                adapterFri.addToDO(todo);
            }
            else{
                Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
            }
        }

        if (addDay == 5) {
            if(checkTitle(tue, todo.getTitle())){
                mDbOpenHelper.INSERTInSat(todo.getTitle(),todo.getMemo(),todo.getPriority());
                adapterSat.addToDO(todo);
            }
            else{
                Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
            }
        }
        if (addDay == 6) {
            if(checkTitle(tue, todo.getTitle())){
            }
            mDbOpenHelper.INSERTInSun(todo.getTitle(),todo.getMemo(),todo.getPriority());
            adapterSun.addToDO(todo);
            }
            else{
                Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
        }
    }

    //true면 생성 가능 false면 생성 불가능
    public boolean checkTitle(ArrayList<ToDo> arr, String title){
        for(int i = 0; i < arr.size(); i++){
            if(arr.get(i).getTitle() == title){
                return false;
            }
        }
        return true;
    }

    private void showMyBlog() {
        Intent intent = new Intent(MainActivity.this, ShowMyBlogActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ToDo todo;
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                todo = data.getParcelableExtra("madetodo");
                addData(todo);
                notifyAllLv();
            }

            if (resultCode == 100) {
                changedtodo = data.getParcelableExtra("changedtodo");
                if (changeDay == 0) {
                    if(checkTitle(mon,changedtodo.getTitle())){
                        mDbOpenHelper.deleteInMon(changedtodo.getTitle());
                        mDbOpenHelper.INSERTInMon(changedtodo.getTitle(),changedtodo.getMemo(),changedtodo.getPriority());
                        adapterMon.changeToDo(changeIndex, changedtodo);
                    }
                    else{
                        Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
                    }

                } else if (changeDay == 1) {
                    if(checkTitle(mon,changedtodo.getTitle())){
                        mDbOpenHelper.deleteInTue(changedtodo.getTitle());
                        mDbOpenHelper.INSERTInTue(changedtodo.getTitle(),changedtodo.getMemo(),changedtodo.getPriority());
                        adapterTue.changeToDo(changeIndex, changedtodo);
                    }
                    else{
                        Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
                    }

                } else if (changeDay == 2) {
                    if(checkTitle(mon,changedtodo.getTitle())){
                        mDbOpenHelper.deleteInWed(changedtodo.getTitle());
                        mDbOpenHelper.INSERTInWed(changedtodo.getTitle(),changedtodo.getMemo(),changedtodo.getPriority());
                        adapterWed.changeToDo(changeIndex, changedtodo);
                    }
                    else{
                        Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
                    }

                } else if (changeDay == 3) {
                    if(checkTitle(mon,changedtodo.getTitle())){
                        mDbOpenHelper.deleteInThu(changedtodo.getTitle());
                        mDbOpenHelper.INSERTInThu(changedtodo.getTitle(),changedtodo.getMemo(),changedtodo.getPriority());
                        adapterThu.changeToDo(changeIndex, changedtodo);
                    }
                    else{
                        Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
                    }

                } else if (changeDay == 4) {
                    if(checkTitle(mon,changedtodo.getTitle())){
                        mDbOpenHelper.deleteInFri(changedtodo.getTitle());
                        mDbOpenHelper.INSERTInFri(changedtodo.getTitle(),changedtodo.getMemo(),changedtodo.getPriority());
                        adapterFri.changeToDo(changeIndex, changedtodo);
                    }
                    else{
                        Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
                    }

                } else if (changeDay == 5) {
                    if(checkTitle(mon,changedtodo.getTitle())){
                        mDbOpenHelper.deleteInSat(changedtodo.getTitle());
                        mDbOpenHelper.INSERTInSat(changedtodo.getTitle(),changedtodo.getMemo(),changedtodo.getPriority());
                        adapterSat.changeToDo(changeIndex, changedtodo);
                    }
                    else{
                        Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
                    }

                } else if (changeDay == 6) {
                    if(checkTitle(mon,changedtodo.getTitle())){
                        mDbOpenHelper.deleteInSun(changedtodo.getTitle());
                        mDbOpenHelper.INSERTInSun(changedtodo.getTitle(),changedtodo.getMemo(),changedtodo.getPriority());
                        adapterSun.changeToDo(changeIndex, changedtodo);
                    }
                    else{
                        Toast.makeText(this,"Title이 중복됩니다.",Toast.LENGTH_SHORT);
                    }

                }
                notifyAllLv();
            }
        }
    }

    private void notifyAllLv() {
        adapterMon.notifyDataSetChanged();
        adapterTue.notifyDataSetChanged();
        adapterWed.notifyDataSetChanged();
        adapterThu.notifyDataSetChanged();
        adapterFri.notifyDataSetChanged();
        adapterSat.notifyDataSetChanged();
        adapterSun.notifyDataSetChanged();
    }


    public void onClick(View v) {
        if (v.getId() == R.id.btMon) addDay = 0;
        else if (v.getId() == R.id.btTue) addDay = 1;
        else if (v.getId() == R.id.btWed) addDay = 2;
        else if (v.getId() == R.id.btThu) addDay = 3;
        else if (v.getId() == R.id.btFri) addDay = 4;
        else if (v.getId() == R.id.btSat) addDay = 5;
        else if (v.getId() == R.id.btSun) addDay = 6;

        ToDo todo = new ToDo("", "", 0);
        intent = new Intent(MainActivity.this, MakeToDOActivity.class);
        intent.putExtra("maketodo", todo);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }
}
