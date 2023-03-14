package com.lesson.listviewadddelete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> users=new ArrayList<>();
    ArrayList<String> selectedUser=new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView usersList;
    private Button add;
    private Button remove;
    private EditText userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Collections.addAll(users,"Tom","Jerry","Bob","Jorn");
        usersList=findViewById(R.id.usersList);
        add=findViewById(R.id.add);
        remove=findViewById(R.id.remove);
        userName=findViewById(R.id.userName);
        adapter=new ArrayAdapter(this,
                android.R.layout.simple_list_item_multiple_choice,users);
        usersList.setAdapter(adapter);
        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String user=adapter.getItem(position);
                if(usersList.isItemChecked(position))
                {
                    selectedUser.add(user);
                }
                else
                {
                    selectedUser.remove(user);
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=userName.getText().toString();
                if(!name.isEmpty())
                {
                    adapter.add(name);
                    userName.setText("");
                    adapter.notifyDataSetChanged();
                }
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < selectedUser.size(); i++) {
                    adapter.remove(selectedUser.get(i));
                }
                usersList.clearChoices();
                selectedUser.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
}