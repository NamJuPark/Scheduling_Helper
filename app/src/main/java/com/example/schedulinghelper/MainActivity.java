package com.example.schedulinghelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int PICK_CONTACT_REQUEST = 0;
    static final int PICK_CONTACT_REQUEST2 = 1;
    Intent intent;
    ToDo changedtodo;
    TabHost tabHost;
    int changeDay, changeIndex;
    ListView lvMon, lvTue, lvWed, lvThu, lvFri, lvSat, lvSun;
    ArrayList<ToDo> mon, tue, wed, thu, fri, sat, sun;
    WeekAdapter adapterMon, adapterTue, adapterWed, adapterThu, adapterFri, adapterSat, adapterSun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        tabHost = (TabHost) findViewById(R.id.tabHost);

        mon = new ArrayList<ToDo>();
        tue = new ArrayList<ToDo>();
        wed = new ArrayList<ToDo>();
        thu = new ArrayList<ToDo>();
        fri = new ArrayList<ToDo>();
        sat = new ArrayList<ToDo>();
        sun = new ArrayList<ToDo>();

        lvMon = (ListView) findViewById(R.id.lvMon);
        lvTue = (ListView) findViewById(R.id.lvTue);
        lvWed = (ListView) findViewById(R.id.lvWed);
        lvThu = (ListView) findViewById(R.id.lvThu);
        lvFri = (ListView) findViewById(R.id.lvFri);
        lvSat = (ListView) findViewById(R.id.lvSat);
        lvSun = (ListView) findViewById(R.id.lvSun);

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

        lvMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {//수정
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                changeDay = 0;
                changeIndex = i;
                intent.putExtra("state", true);
                intent.putExtra("chagetodo", mon.get(i));
                startActivityForResult(intent, PICK_CONTACT_REQUEST2);

            }
        });

        lvMon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapterMon.delToDo(i);
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
                intent.putExtra("chagetodo", tue.get(i));
                startActivityForResult(intent, PICK_CONTACT_REQUEST2);
            }
        });
        lvTue.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapterTue.delToDo(i);
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
                intent.putExtra("chagetodo", wed.get(i));
                startActivityForResult(intent, PICK_CONTACT_REQUEST2);
            }
        });
        lvWed.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapterWed.delToDo(i);
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
                intent.putExtra("chagetodo", thu.get(i));
                startActivityForResult(intent, PICK_CONTACT_REQUEST2);
            }
        });
        lvThu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapterThu.delToDo(i);
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
                intent.putExtra("chagetodo", fri.get(i));
                startActivityForResult(intent, PICK_CONTACT_REQUEST2);
            }
        });
        lvFri.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapterFri.delToDo(i);
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
                intent.putExtra("chagetodo", sat.get(i));
                startActivityForResult(intent, PICK_CONTACT_REQUEST2);
            }
        });
        lvSat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapterMon.delToDo(i);
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
                intent.putExtra("chagetodo", sun.get(i));
                startActivityForResult(intent, PICK_CONTACT_REQUEST2);
            }
        });
        lvSun.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("할 일 삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapterMon.delToDo(i);
                            }
                        })
                        .setNegativeButton("N", null)
                        .show();
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 1, 0, "중요도 정렬");
        menu.add(0, 2, 0, "Go To Blog");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                break;
            case 2:
                showMyBlog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addData(ToDo todo) {
        if (todo.week[0] == true) adapterMon.addToDO(todo);
        if (todo.week[1] == true) adapterTue.addToDO(todo);
        if (todo.week[2] == true) adapterWed.addToDO(todo);
        if (todo.week[3] == true) adapterThu.addToDO(todo);
        if (todo.week[4] == true) adapterFri.addToDO(todo);
        if (todo.week[5] == true) adapterSat.addToDO(todo);
        if (todo.week[6] == true) adapterSun.addToDO(todo);

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
            }
        }
        if (requestCode == PICK_CONTACT_REQUEST2) {
            if (resultCode == RESULT_OK) {
                changedtodo = data.getParcelableExtra("changedtodo");
                if (changeDay == 0) {
                    adapterMon.changeToDo(changeIndex, changedtodo);
                } else if (changeDay == 1) {
                    adapterTue.changeToDo(changeIndex, changedtodo);
                } else if (changeDay == 2) {
                    adapterWed.changeToDo(changeIndex, changedtodo);
                } else if (changeDay == 3) {
                    adapterThu.changeToDo(changeIndex, changedtodo);
                } else if (changeDay == 4) {
                    adapterFri.changeToDo(changeIndex, changedtodo);
                } else if (changeDay == 5) {
                    adapterSat.changeToDo(changeIndex, changedtodo);
                } else if (changeDay == 6) {
                    adapterSun.changeToDo(changeIndex, changedtodo);
                }
            }
        }
    }


    public void onClick(View v) {
        ToDo todo = new ToDo("", "", 0, new boolean[7]);
        intent = new Intent(MainActivity.this, MakeToDOActivity.class);
        intent.putExtra("maketodo", todo);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }
}
