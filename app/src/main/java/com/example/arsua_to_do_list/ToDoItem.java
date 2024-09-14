package com.example.arsua_to_do_list;

public class ToDoItem {
    private String description;
    private boolean isChecked;

    public ToDoItem(String description, boolean isChecked) {
        this.description = description;
        this.isChecked = isChecked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}