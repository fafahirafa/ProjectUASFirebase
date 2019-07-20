package id.ac.pnj.projectuasfirebase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import id.ac.pnj.projectuasfirebase.ToDoList;

public class AdapterToDoList extends ArrayAdapter {
    Activity context;
    int resource;
    ArrayList<ToDoList> arrayList;

    public AdapterToDoList(Activity context, ArrayList<ToDoList> arrayList) {
        super(context, R.layout.item_list, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list, null,true);
        TextView text_task_name = view.findViewById(R.id.txt_task_name);
        TextView text_task_date = view.findViewById(R.id.txt_task_date);
        TextView text_task_note = view.findViewById(R.id.txt_task_note);


        ToDoList toDoList = (ToDoList) getItem(position);

        text_task_name.setText(toDoList.getTask_name());
        text_task_date.setText(toDoList.getDate());
        text_task_note.setText(toDoList.getNotes());



        return view;
    }
}
