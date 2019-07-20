package id.ac.pnj.projectuasfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormActivity extends AppCompatActivity {
    EditText edt_task_name, edt_notes;
    DatePicker datePicker;
    Button btn_create_list;
    String toDoListId, method="create";

    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        edt_task_name = (EditText) findViewById(R.id.edt_task_name);
        edt_notes = (EditText)findViewById (R.id.edt_notes);
        datePicker = (DatePicker) findViewById(R.id.edt_date);
        btn_create_list= (Button) findViewById(R.id.btn_create_list);

        final String taskName = edt_task_name.getText().toString();
        final String notes = edt_notes.getText().toString();
        Date date = new Date(datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
        final String dateTask = simpleDateFormat.format(date);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        Intent intent = getIntent();
        if(intent.hasExtra("id")){
            toDoListId = intent.getStringExtra("id");
            edt_task_name.setText(intent.getStringExtra("taskname"));
            edt_notes.setText(intent.getStringExtra("tasknote"));

            method = "update";
        }
        btn_create_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!taskName.isEmpty() && !notes.isEmpty() && !dateTask.isEmpty()){
                    if (method.equals("create")) {

                        String id = reference.push().getKey();
                        ToDoList toDoList = new ToDoList(taskName, dateTask, notes, id);
                        reference.child(id).setValue(toDoList);
                    }else{
                        reference = database.getReference("task").child(toDoListId);
                        ToDoList toDoList = new ToDoList(taskName, dateTask, notes, toDoListId);
                        reference.setValue(toDoList);
                    }
                    Intent intent = new Intent(FormActivity.this, ListActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(FormActivity.this, "Fill all input first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
