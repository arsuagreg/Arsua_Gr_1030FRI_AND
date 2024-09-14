package com.example.arsua_to_do_list;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ToDoItem> toDoItems;
    private CustomAdapter adapter;
    private ListView listView;
    private EditText editText;
    private Button addButton;
    private GestureDetector gestureDetector;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        editText = findViewById(R.id.edit_text);
        addButton = findViewById(R.id.add_button);

        toDoItems = new ArrayList<>();
        adapter = new CustomAdapter(this, toDoItems);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String description = editText.getText().toString();
            if (!description.isEmpty()) {
                toDoItems.add(new ToDoItem(description, false));
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                int position = listView.pointToPosition((int) e.getX(), (int) e.getY());
                if (position != ListView.INVALID_POSITION) {
                    showCustomDialog(position);  // Show dialog on double-tap
                    return true;
                }
                return false;
            }
        });

        listView.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
    }
    private void showCustomDialog(int position) {
        ToDoItem selectedItem = toDoItems.get(position);

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog);

        EditText editText = dialog.findViewById(R.id.edit_item_text);
        Button editButton = dialog.findViewById(R.id.button_edit);
        Button deleteButton = dialog.findViewById(R.id.button_delete);
        Button cancelButton = dialog.findViewById(R.id.button_cancel);

        editText.setText(selectedItem.getDescription());

        editButton.setOnClickListener(v -> {
            String newDescription = editText.getText().toString();
            if (!newDescription.isEmpty()) {
                selectedItem.setDescription(newDescription);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        deleteButton.setOnClickListener(v -> {
            toDoItems.remove(position);
            adapter.notifyDataSetChanged();
            dialog.dismiss();
        });

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
