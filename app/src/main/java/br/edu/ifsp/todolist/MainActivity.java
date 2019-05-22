package br.edu.ifsp.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private static final int TEXT_REQUEST = 1;

    public static final int TODO_REQUEST = 2;

    public static final int RESULT_EDITING = 3;

    private final LinkedList<ToDo> mToDoList = new LinkedList<>();

    private RecyclerView mRecyclerView;

    private ToDoListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::lauchSecondActivty);

        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new ToDoListAdapter(this, mToDoList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == TEXT_REQUEST) {
                int toDoListSize = mToDoList.size();
                String result = data.getStringExtra("todo");
                if (!result.isEmpty()) {
                    mToDoList.add(new ToDo(result));
                    mRecyclerView.getAdapter().notifyItemInserted(toDoListSize);
                    mRecyclerView.smoothScrollToPosition(toDoListSize);
                }
            }
        } else if (resultCode == RESULT_EDITING) {
            int position = data.getIntExtra("position", -1);
            String elementName = data.getStringExtra("todo");
            mToDoList.get(position).setName(elementName);
            mRecyclerView.getAdapter().notifyItemChanged(position);
            mRecyclerView.smoothScrollToPosition(position);
        }

        if (resultCode == RESULT_OK && requestCode == TODO_REQUEST) {
            int position = data.getIntExtra("todoposition", -1);
            if (position != -1) {
                ArrayList<ToDoItem> mToDoItems = data.getParcelableArrayListExtra("todoitems");
                mToDoList.get(position).setToDoItems(mToDoItems);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void lauchSecondActivty(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

}
