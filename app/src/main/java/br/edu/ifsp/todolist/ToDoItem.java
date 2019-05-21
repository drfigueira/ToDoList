package br.edu.ifsp.todolist;

import android.os.Parcel;
import android.os.Parcelable;

public class ToDoItem implements Parcelable {

    private String name;

    private int quantity;

    public ToDoItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    protected ToDoItem(Parcel in) {
        name = in.readString();
        quantity = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(quantity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ToDoItem> CREATOR = new Creator<ToDoItem>() {
        @Override
        public ToDoItem createFromParcel(Parcel in) {
            return new ToDoItem(in);
        }

        @Override
        public ToDoItem[] newArray(int size) {
            return new ToDoItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

}
