package com.example.demodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lvTask;
    ArrayList<Task> alTask;
    CustomAdapter caTask;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.buttonInsert);
        btnGetTasks = findViewById(R.id.buttonGetTasks);
        tvResults = findViewById(R.id.textViewResults);
        lvTask = findViewById(R.id.lv);
        myDB = new DBHelper(this);
        alTask = new ArrayList<>();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDB.insertTasks("Submit RJ", "25 April 2016");
                myDB.close();

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ArrayList<String> data = myDB.getTaskContent();
                String text = "";
                for (int i = 0; i < data.size(); i++){
                    Log.d("Database Content", i + ". " + data.get(i));
                    text += i + ". " + data.get(i) + "\n";
                }

                alTask = myDB.getTasks();
                myDB.close();
                caTask = new CustomAdapter(MainActivity.this, R.layout.custom_listview, alTask);
                lvTask.setAdapter(caTask);
                caTask.notifyDataSetChanged();

                tvResults.setText(text);
            }
        });
    }
}
