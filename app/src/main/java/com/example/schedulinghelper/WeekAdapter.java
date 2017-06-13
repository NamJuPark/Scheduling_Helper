package com.example.schedulinghelper;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 박남주 on 2017-06-12.
 */

public class WeekAdapter extends BaseAdapter{
    ArrayList<ToDo> todo;
    Context c;

    WeekAdapter(ArrayList<ToDo> todo, Context c){
        this.todo = todo;
        this.c = c;
    }

    @Override
    public Object getItem(int i) {
        return todo.get(i);
    }

    @Override
    public int getCount() {
        return todo.size();
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(c);
        if(view == null){
            view = inflater.inflate(R.layout.listview_item, null);
        }
        final TextView title = (TextView)view.findViewById(R.id.title);
        TextView memo = (TextView)view.findViewById(R.id.memo);
        CheckBox cb = (CheckBox)view.findViewById(R.id.checkBox);

        title.setText(todo.get(i).getTitle());
        memo.setText(todo.get(i).getMemo());

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                        title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else{
                    title.setPaintFlags(title.getPaintFlags() ^ Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });
        return view;
    }
    public void addToDO(ToDo todoitem){
        todo.add(todoitem);
        notifyDataSetChanged();
    }

    public void delToDo(int i){
        todo.remove(i);
        notifyDataSetChanged();
    }

    public void changeToDo(int changeIndex, ToDo changedtodo){
        todo.get(changeIndex).setTitle(changedtodo.getTitle());
        todo.get(changeIndex).setMemo(changedtodo.getMemo());
        todo.get(changeIndex).setPriority(changedtodo.getPriority());
        notifyDataSetChanged();
    }

}
