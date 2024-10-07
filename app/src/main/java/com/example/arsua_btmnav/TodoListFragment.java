package com.example.arsua_btmnav;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class TodoListFragment extends Fragment {

    private ArrayList<ToDoItem> toDoItems;
    private CustomAdapter adapter;
    private ListView listView;
    private EditText editText;
    private Button addButton;
    private GestureDetector gestureDetector;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_todo_list, container, false);

        listView = rootView.findViewById(R.id.list_view);
        editText = rootView.findViewById(R.id.edit_text);
        addButton = rootView.findViewById(R.id.add_button);

        toDoItems = new ArrayList<>();
        adapter = new CustomAdapter(getActivity(), toDoItems);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String description = editText.getText().toString();
            if (!description.isEmpty()) {
                toDoItems.add(new ToDoItem(description, false));
                adapter.notifyDataSetChanged();
                editText.setText("");

                // Hide the keyboard after adding the task
                hideKeyboard();
            }
        });

        gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                int position = listView.pointToPosition((int) e.getX(), (int) e.getY());
                if (position != ListView.INVALID_POSITION) {
                    showCustomDialog(position);
                    return true;
                }
                return false;
            }
        });

        listView.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));

        return rootView;
    }

    private void showCustomDialog(int position) {
        ToDoItem selectedItem = toDoItems.get(position);

        final Dialog dialog = new Dialog(getActivity());
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

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
