package com.example.schedulinghelper;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
        intent = new Intent(MainActivity.this, MakeToDOActivity.class);
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
                                mDbOpenHelper.deleteInMon(mon.get(item).getId());
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
                                mDbOpenHelper.deleteInTue(tue.get(item).getId());
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
                                mDbOpenHelper.deleteInWed(wed.get(item).getId());
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
                                mDbOpenHelper.deleteInThu(thu.get(item).getId());
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
                                mDbOpenHelper.deleteInFri(fri.get(item).getId());
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
                                mDbOpenHelper.deleteInSat(sat.get(item).getId());
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
                                mDbOpenHelper.deleteInSun(sun.get(item).getId());
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

        mDbOpenHelper = new DbOpenHelper(this);
        try {
            mDbOpenHelper = mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mon = new ArrayList<ToDo>();
        tue = new ArrayList<ToDo>();
        wed = new ArrayList<ToDo>();
        thu = new ArrayList<ToDo>();
        fri = new ArrayList<ToDo>();
        sat = new ArrayList<ToDo>();
        sun = new ArrayList<ToDo>();

        adapterMon = new WeekAdapter(mon, this, mDbOpenHelper);
        adapterTue = new WeekAdapter(tue, this, mDbOpenHelper);
        adapterWed = new WeekAdapter(wed, this, mDbOpenHelper);
        adapterThu = new WeekAdapter(thu, this, mDbOpenHelper);
        adapterFri = new WeekAdapter(fri, this, mDbOpenHelper);
        adapterSat = new WeekAdapter(sat, this, mDbOpenHelper);
        adapterSun = new WeekAdapter(sun, this, mDbOpenHelper);

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

        setListView();
    }

    private void setListView() {
        setmCursor(0);
        setmCursor(1);
        setmCursor(2);
        setmCursor(3);
        setmCursor(4);
        setmCursor(5);
        setmCursor(6);

    }

    public void setmCursor(int day) {
        mCursor = null;
        if (day == 0) {
            mCursor = mDbOpenHelper.getAllMon();
            try {
                while (mCursor.moveToNext()) {
                    ToDo todo = new ToDo("", "", "", 0, 0, 0);
                    todo.setId(mCursor.getString(0));
                    todo.setTitle(mCursor.getString(1));
                    todo.setMemo(mCursor.getString(2));
                    todo.setPriority(mCursor.getInt(3));
                    todo.setDone(mCursor.getInt(4));
                    adapterMon.addToDO(todo);
                }
                mCursor.close();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        } else if (day == 1) {
            mCursor = mDbOpenHelper.getAllTue();
            try {
                while (mCursor.moveToNext()) {
                    ToDo todo = new ToDo("", "", "", 0, 0, 1);
                    todo.setId(mCursor.getString(0));
                    todo.setTitle(mCursor.getString(1));
                    todo.setMemo(mCursor.getString(2));
                    todo.setPriority(mCursor.getInt(3));
                    todo.setDone(mCursor.getInt(4));
                    adapterTue.addToDO(todo);
                }
                mCursor.close();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }

        } else if (day == 2) {
            mCursor = mDbOpenHelper.getAllWed();
            try {
                wed.clear();
                while (mCursor.moveToNext()) {
                    ToDo todo = new ToDo("", "", "", 0, 0, 2);
                    todo.setId(mCursor.getString(0));
                    todo.setTitle(mCursor.getString(1));
                    todo.setMemo(mCursor.getString(2));
                    todo.setPriority(mCursor.getInt(3));
                    todo.setDone(mCursor.getInt(4));
                    adapterWed.addToDO(todo);
                }
                mCursor.close();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        } else if (day == 3) {
            mCursor = mDbOpenHelper.getAllThu();
            try {
                while (mCursor.moveToNext()) {
                    ToDo todo = new ToDo("", "", "", 0, 0, 3);
                    todo.setId(mCursor.getString(0));
                    todo.setTitle(mCursor.getString(1));
                    todo.setMemo(mCursor.getString(2));
                    todo.setPriority(mCursor.getInt(3));
                    todo.setDone(mCursor.getInt(4));
                    adapterThu.addToDO(todo);
                }
                mCursor.close();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        } else if (day == 4) {
            mCursor = mDbOpenHelper.getAllFri();
            try {
                while (mCursor.moveToNext()) {
                    ToDo todo = new ToDo("", "", "", 0, 0, 4);
                    todo.setId(mCursor.getString(0));
                    todo.setTitle(mCursor.getString(1));
                    todo.setMemo(mCursor.getString(2));
                    todo.setPriority(mCursor.getInt(3));
                    todo.setDone(mCursor.getInt(4));
                    adapterFri.addToDO(todo);
                }
                mCursor.close();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        } else if (day == 5) {
            mCursor = mDbOpenHelper.getAllSat();
            try {
                while (mCursor.moveToNext()) {
                    ToDo todo = new ToDo("", "", "", 0, 0, 5);
                    todo.setId(mCursor.getString(0));
                    todo.setTitle(mCursor.getString(1));
                    todo.setMemo(mCursor.getString(2));
                    todo.setPriority(mCursor.getInt(3));
                    todo.setDone(mCursor.getInt(4));
                    adapterSat.addToDO(todo);
                }
                mCursor.close();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        } else if (day == 6) {
            mCursor = mDbOpenHelper.getAllSun();
            try {
                while (mCursor.moveToNext()) {
                    ToDo todo = new ToDo("", "", "", 0, 0, 6);
                    todo.setId(mCursor.getString(0));
                    todo.setTitle(mCursor.getString(1));
                    todo.setMemo(mCursor.getString(2));
                    todo.setPriority(mCursor.getInt(3));
                    todo.setDone(mCursor.getInt(4));
                    adapterSun.addToDO(todo);
                }
                mCursor.close();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 1, 0, "완료 일정 정리");
        menu.add(0, 2, 0, "Go To Blog");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                ArrayList<String> endTodoArr = makeEndList();
                writeFile(endTodoArr);
                Intent intent2 = new Intent(MainActivity.this, showEndToDoActivity.class);
                intent2.putExtra("filename", filename);
                startActivity(intent2);
                break;
            case 2:
                showMyBlog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkpermission() {
        int permissioninfo = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissioninfo == PackageManager.PERMISSION_GRANTED) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "어플리케이션 설정에서 저장소 사용 권한을 허용해주세요", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            } else {
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
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename + ".txt", true));
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date).toString();
    }

    private ArrayList<String> makeEndList() {
        ArrayList<String> endTodoArr = new ArrayList<String>();
        String str = "";
        if (mon != null) {
            ToDo endTodo;
            ArrayList<ToDo> checkedItems = new ArrayList<ToDo>();
            int count = lvMon.getCount();

            for (int j = 0; j < count; j++) {
                if (mon.get(j).getDone() == 1) {
                    checkedItems.add(mon.get(j));
                }
            }

            for (int i = 0; i < checkedItems.size(); i++) {
                endTodo = checkedItems.get(i);
                str += endTodo.getTitle() + " / " + endTodo.getMemo();

                if (endTodo.getPriority() == 0) str += " / 상 / ";
                else if (endTodo.getPriority() == 1) str += " / 중 / ";
                else if (endTodo.getPriority() == 2) str += " / 하 / ";

                str += "월요일\n";
                endTodoArr.add(str);
                str = "";
            }
        }
        if (tue != null) {
            ToDo endTodo;
            ArrayList<ToDo> checkedItems = new ArrayList<ToDo>();

            for (int j = 0; j < tue.size(); j++) {
                if (tue.get(j).getDone() == 1) {
                    checkedItems.add(tue.get(j));
                }
            }

            for (int i = 0; i < checkedItems.size(); i++) {
                endTodo = checkedItems.get(i);
                str += endTodo.getTitle() + " / " + endTodo.getMemo();

                if (endTodo.getPriority() == 0) str += " / 상 / ";
                else if (endTodo.getPriority() == 1) str += " / 중 / ";
                else if (endTodo.getPriority() == 2) str += " / 하 / ";

                str += "화요일\n";
                endTodoArr.add(str);
                str = "";
            }
        }
        if (wed != null) {
            ToDo endTodo;
            ArrayList<ToDo> checkedItems = new ArrayList<ToDo>();

            for (int j = 0; j < wed.size(); j++) {
                if (wed.get(j).getDone() == 1) {
                    checkedItems.add(wed.get(j));
                }
            }

            for (int i = 0; i < checkedItems.size(); i++) {
                endTodo = checkedItems.get(i);
                str += endTodo.getTitle() + " / " + endTodo.getMemo();

                if (endTodo.getPriority() == 0) str += " / 상 / ";
                else if (endTodo.getPriority() == 1) str += " / 중 / ";
                else if (endTodo.getPriority() == 2) str += " / 하 / ";

                str += "수요일\n";
                endTodoArr.add(str);
                str = "";
            }
        }
        if (thu != null) {
            ToDo endTodo;
            ArrayList<ToDo> checkedItems = new ArrayList<ToDo>();

            for (int j = 0; j < thu.size(); j++) {
                if (thu.get(j).getDone() == 1) {
                    checkedItems.add(thu.get(j));
                }
            }

            for (int i = 0; i < checkedItems.size(); i++) {
                endTodo = checkedItems.get(i);
                str += endTodo.getTitle() + " / " + endTodo.getMemo();

                if (endTodo.getPriority() == 0) str += " / 상 / ";
                else if (endTodo.getPriority() == 1) str += " / 중 / ";
                else if (endTodo.getPriority() == 2) str += " / 하 / ";

                str += "목요일\n";
                endTodoArr.add(str);
                str = "";
            }
        }
        if (fri != null) {
            ToDo endTodo;
            ArrayList<ToDo> checkedItems = new ArrayList<ToDo>();

            for (int j = 0; j < fri.size(); j++) {
                if (fri.get(j).getDone() == 1) {
                    checkedItems.add(fri.get(j));
                }
            }

            for (int i = 0; i < checkedItems.size(); i++) {
                endTodo = checkedItems.get(i);
                str += endTodo.getTitle() + " / " + endTodo.getMemo();

                if (endTodo.getPriority() == 0) str += " / 상 / ";
                else if (endTodo.getPriority() == 1) str += " / 중 / ";
                else if (endTodo.getPriority() == 2) str += " / 하 / ";

                str += "금요일\n";
                endTodoArr.add(str);
                str = "";
            }
        }
        if (sat != null) {
            ToDo endTodo;
            ArrayList<ToDo> checkedItems = new ArrayList<ToDo>();

            for (int j = 0; j < sat.size(); j++) {
                if (sat.get(j).getDone() == 1) {
                    checkedItems.add(sat.get(j));
                }
            }

            for (int i = 0; i < checkedItems.size(); i++) {
                endTodo = checkedItems.get(i);
                str += endTodo.getTitle() + " / " + endTodo.getMemo();

                if (endTodo.getPriority() == 0) str += " / 상 / ";
                else if (endTodo.getPriority() == 1) str += " / 중 / ";
                else if (endTodo.getPriority() == 2) str += " / 하 / ";

                str += "토요일\n";
                endTodoArr.add(str);
                str = "";
            }
        }
        if (sun != null) {
            ToDo endTodo;
            ArrayList<ToDo> checkedItems = new ArrayList<ToDo>();

            for (int j = 0; j < sun.size(); j++) {
                if (sun.get(j).getDone() == 1) {
                    checkedItems.add(sun.get(j));
                }
            }

            for (int i = 0; i < checkedItems.size(); i++) {
                endTodo = checkedItems.get(i);
                str += endTodo.getTitle() + " / " + endTodo.getMemo();

                if (endTodo.getPriority() == 0) str += " / 상 / ";
                else if (endTodo.getPriority() == 1) str += " / 중 / ";
                else if (endTodo.getPriority() == 2) str += " / 하 / ";

                str += "일요일\n";
                endTodoArr.add(str);
                str = "";
            }
        }

        return endTodoArr;
    }

    private void addData(ToDo todo) {
        if (addDay == 0) {
            adapterMon.addToDO(todo);
            mDbOpenHelper.INSERTInMon(todo.getId(), todo.getTitle(), todo.getMemo(), todo.getPriority(), todo.getDone());
        }
        if (addDay == 1) {
            adapterTue.addToDO(todo);
            mDbOpenHelper.INSERTInTue(todo.getId(), todo.getTitle(), todo.getMemo(), todo.getPriority(), todo.getDone());
        }
        if (addDay == 2) {
            adapterWed.addToDO(todo);
            mDbOpenHelper.INSERTInWed(todo.getId(), todo.getTitle(), todo.getMemo(), todo.getPriority(), todo.getDone());
        }
        if (addDay == 3) {
            adapterThu.addToDO(todo);
            mDbOpenHelper.INSERTInThu(todo.getId(), todo.getTitle(), todo.getMemo(), todo.getPriority(), todo.getDone());
        }
        if (addDay == 4) {
            adapterFri.addToDO(todo);
            mDbOpenHelper.INSERTInFri(todo.getId(), todo.getTitle(), todo.getMemo(), todo.getPriority(), todo.getDone());
        }

        if (addDay == 5) {
            adapterSat.addToDO(todo);
            mDbOpenHelper.INSERTInSat(todo.getId(), todo.getTitle(), todo.getMemo(), todo.getPriority(), todo.getDone());
        }
        if (addDay == 6) {
            adapterSun.addToDO(todo);
            mDbOpenHelper.INSERTInSun(todo.getId(), todo.getTitle(), todo.getMemo(), todo.getPriority(), todo.getDone());
        }
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
                    mDbOpenHelper.deleteInMon(changedtodo.getId());
                    mDbOpenHelper.INSERTInMon(filename(), changedtodo.getTitle(), changedtodo.getMemo(), changedtodo.getPriority(), changedtodo.getDone());
                    adapterMon.changeToDo(changeIndex, changedtodo);
                } else if (changeDay == 1) {
                    mDbOpenHelper.deleteInTue(changedtodo.getId());
                    mDbOpenHelper.INSERTInTue(changedtodo.getId(), changedtodo.getTitle(), changedtodo.getMemo(), changedtodo.getPriority(), changedtodo.getDone());
                    adapterTue.changeToDo(changeIndex, changedtodo);
                } else if (changeDay == 2) {
                    adapterWed.changeToDo(changeIndex, changedtodo);
                    mDbOpenHelper.deleteInWed(changedtodo.getId());
                    mDbOpenHelper.INSERTInWed(changedtodo.getId(), changedtodo.getTitle(), changedtodo.getMemo(), changedtodo.getPriority(), changedtodo.getDone());
                } else if (changeDay == 3) {
                    adapterThu.changeToDo(changeIndex, changedtodo);
                    mDbOpenHelper.deleteInThu(changedtodo.getId());
                    mDbOpenHelper.INSERTInThu(changedtodo.getId(), changedtodo.getTitle(), changedtodo.getMemo(), changedtodo.getPriority(), changedtodo.getDone());
                } else if (changeDay == 4) {
                    adapterFri.changeToDo(changeIndex, changedtodo);
                    mDbOpenHelper.deleteInFri(changedtodo.getId());
                    mDbOpenHelper.INSERTInFri(changedtodo.getId(), changedtodo.getTitle(), changedtodo.getMemo(), changedtodo.getPriority(), changedtodo.getDone());
                } else if (changeDay == 5) {
                    adapterSat.changeToDo(changeIndex, changedtodo);
                    mDbOpenHelper.deleteInSat(changedtodo.getId());
                    mDbOpenHelper.INSERTInSat(changedtodo.getId(), changedtodo.getTitle(), changedtodo.getMemo(), changedtodo.getPriority(), changedtodo.getDone());
                } else if (changeDay == 6) {
                    adapterSun.changeToDo(changeIndex, changedtodo);
                    mDbOpenHelper.deleteInSun(changedtodo.getId());
                    mDbOpenHelper.INSERTInSun(changedtodo.getId(), changedtodo.getTitle(), changedtodo.getMemo(), changedtodo.getPriority(), changedtodo.getDone());
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

        ToDo todo = new ToDo(filename(), "", "", 0, 0, 0);
        ;

        String path = getExternalPath();
        File file = new File(path + "Scheduling Helper");
        file.mkdir();

        if (v.getId() == R.id.btMon) {
            addDay = 0;
        } else if (v.getId() == R.id.btTue) {
            addDay = 1;
            todo.setDay(1);
        } else if (v.getId() == R.id.btWed) {
            addDay = 2;
            todo.setDay(2);
        } else if (v.getId() == R.id.btThu) {
            addDay = 3;
            todo.setDay(3);
        } else if (v.getId() == R.id.btFri) {
            addDay = 4;
            todo.setDay(4);
        } else if (v.getId() == R.id.btSat) {
            addDay = 5;
            todo.setDay(5);
        } else if (v.getId() == R.id.btSun) {
            addDay = 6;
            todo.setDay(6);
        }

        intent = new Intent(MainActivity.this, MakeToDOActivity.class);
        intent.putExtra("maketodo", todo);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }

}
