package br.edu.ifsp.todolist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ToDo implements Parcelable {

    private String name;

    private ArrayList<ToDoItem> toDoItems = new ArrayList<>();

    public ToDo(String name) {
        this.name = name;
    }

    protected ToDo(Parcel in) {
        name = in.readString();
    }

    public void setToDoItems(ArrayList<ToDoItem> toDoItems) {
        this.toDoItems = toDoItems;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ToDoItem> getToDoItems() {
        return toDoItems;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Creator<ToDo> CREATOR = new Creator<ToDo>() {
        @Override
        public ToDo createFromParcel(Parcel in) {
            return new ToDo(in);
        }

        @Override
        public ToDo[] newArray(int size) {
            return new ToDo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
