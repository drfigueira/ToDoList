package br.edu.ifsp.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import static br.edu.ifsp.todolist.MainActivity.TODO_REQUEST;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private final LinkedList<ToDo> mToDoList;

    private final LayoutInflater mInflater;

    private final MainActivity context;

    public ToDoListAdapter(Context context, LinkedList<ToDo> toDoList) {
        mInflater = LayoutInflater.from(context);
        this.context = (MainActivity) context;
        this.mToDoList = toDoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.todolist_item, parent, false);
        return new ViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String mCurrent = mToDoList.get(position).getName();
        holder.toDoItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView toDoItemView;

        final ToDoListAdapter mAdapter;

        public ViewHolder(View itemView, ToDoListAdapter mtoDoListAdapter) {
            super(itemView);
            this.toDoItemView = itemView.findViewById(R.id.todo);
            this.mAdapter = mtoDoListAdapter;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(view -> {
                int mPosition = getLayoutPosition();
                String elementName = mToDoList.get(mPosition).getName();
                Intent intent = new Intent(context, Main2Activity.class);
                intent.putExtra("position", mPosition);
                intent.putExtra("elementName", elementName);
                intent.putExtra("isEditing", true);
                context.startActivityForResult(intent, TODO_REQUEST);
                return true;
            });
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            ToDo element = mToDoList.get(mPosition);
            Intent intent = new Intent(context, Main3Activity.class);
            intent.putParcelableArrayListExtra("todoitems", element.getToDoItems());
            intent.putExtra("position", mPosition);
            context.startActivityForResult(intent, TODO_REQUEST);
        }
    }
}
