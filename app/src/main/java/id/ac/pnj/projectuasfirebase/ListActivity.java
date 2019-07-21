package id.ac.pnj.projectuasfirebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    Button btn_tambahData;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<ToDoList> arrayList = new ArrayList<>();
    AdapterToDoList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        btn_tambahData = (Button)findViewById(R.id.btn_tambahData);
        listView = (ListView) findViewById(R.id.listview);
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("task");
        getData();

        btn_tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToDoList toDoList = arrayList.get(position);

                Intent intent = new Intent(ListActivity.this, FormActivity.class);
                intent.putExtra("id", toDoList.getId());
                intent.putExtra("taskname", toDoList.getTask_name());
                intent.putExtra("taskdate", toDoList.getDate());
                intent.putExtra("tasknote", toDoList.getNotes());

                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final ToDoList toDoList = arrayList.get(position);

                new AlertDialog.Builder(ListActivity.this)
                        .setTitle("Delete")
                        .setMessage("Are you sure want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                reference = database.getReference("task").child(toDoList.getId());
                                reference.removeValue();
                                arrayList.clear();
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .show();

                return true;
            }
        });

    }
    //fungsi untuk ambil isi reference
    public void getData(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0){
                    for (DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){
                        ToDoList toDoList = myDataSnapshot.getValue(ToDoList.class);
                        arrayList.add(toDoList);
                    }
                    adapter = new AdapterToDoList(ListActivity.this, arrayList);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
