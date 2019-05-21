package br.edu.ifsp.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static br.edu.ifsp.todolist.Main3Activity.TODO_REQUEST;

public class ToDoItemsAdapter extends RecyclerView.Adapter<ToDoItemsAdapter.ViewHolder> {

    private final ArrayList<ToDoItem> mToDoItems;

    private final LayoutInflater mInflater;

    private final Main4Activity context;

    public ToDoItemsAdapter(Context context, ArrayList<ToDoItem> toDoItems) {
        mInflater = LayoutInflater.from(context);
        this.mToDoItems = toDoItems;
        this.context = (Main4Activity) context;
    }

    @NonNull
    @Override
    public ToDoItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.todoitems_item, parent, false);
        return new ToDoItemsAdapter.ViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoItemsAdapter.ViewHolder holder, int position) {
        String mCurrent = mToDoItems.get(position).getName() + " x" + mToDoItems.get(position).getQuantity();
        holder.toDoItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mToDoItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView toDoItemView;

        final ToDoItemsAdapter mAdapter;

        public ViewHolder(View itemView, ToDoItemsAdapter mAdapter) {
            super(itemView);
            this.toDoItemView = itemView.findViewById(R.id.todoitem);
            this.mAdapter = mAdapter;
            itemView.setOnLongClickListener(view -> {
                int mPosition = getLayoutPosition();
                String elementName = mToDoItems.get(mPosition).getName();
                int quantity = mToDoItems.get(mPosition).getQuantity();
                Intent intent = new Intent(context, Main4Activity.class);

                intent.putExtra("position", mPosition);
                intent.putExtra("elementName", elementName);
                intent.putExtra("quantity", quantity);
                intent.putExtra("isEditing", true);
                context.startActivityForResult(intent, TODO_REQUEST);
                return true;
            });
        }

//        @Override
//        public void onClick(View view) {
//            int mPosition = getLayoutPosition();
//            ToDoItem element = mToDoItems.get(mPosition);
//            Intent intent = new Intent(context, Main3Activity.class);
////            intent.putParcelableArrayListExtra("todoitem", element);
//            context.startActivityForResult(intent, TODO_REQUEST);
//        }
    }
}
