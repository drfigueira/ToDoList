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

public class Main3Activity extends AppCompatActivity {

    private static final int TEXT_REQUEST = 1;

    public static final int TODO_REQUEST = 2;

    private ArrayList<ToDoItem> mToDoItems = new ArrayList<>();

    private RecyclerView mRecyclerView;

    private ToDoItemsAdapter mAdapter;

    private int toDoPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toDoPosition = getIntent().getIntExtra("position", -1);
        mToDoItems = getIntent().getParcelableArrayListExtra("todoitems");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::lauchFourthActivty);

        mRecyclerView = findViewById(R.id.recyclerview2);
        mAdapter = new ToDoItemsAdapter(this, mToDoItems);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            int toDoListSize = mToDoItems.size();
            String result = data.getStringExtra("todoitem");
            int quantity = data.getIntExtra("quantity", -1);
            if (!result.isEmpty() && quantity != -1) {
                mToDoItems.add(new ToDoItem(result,quantity));
                mRecyclerView.getAdapter().notifyItemInserted(toDoListSize);
                mRecyclerView.smoothScrollToPosition(toDoListSize);
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

    public void lauchFourthActivty(View view) {
        Intent intent = new Intent(this, Main4Activity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("todoposition", toDoPosition);
        intent.putParcelableArrayListExtra("todoitems", mToDoItems);
        setResult(TODO_REQUEST, intent);
        finish();
        super.onBackPressed();
    }
}
