package com.example.arsua_news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class HeadlineFragment extends ListFragment {

    OnHeadlineSelectedListener callback;

    String[] headlines = {
            "VP Sara Duterte refuses to take oath, skips questions at House inquiry on fund use",
            "20 dead, over 590,000 affected due to habagat, ‘Ferdie,’ ‘Gener’",
            "Ex-PNP chief may have helped Alice Guo escape",
            "PNP intensifies manhunt for Harry Roque",
            "Media security task force chief steps down from post"
    };

    public interface OnHeadlineSelectedListener {
        void onHeadlineSelected(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(),
                R.layout.list_item_headline,
                R.id.headline_text,
                headlines);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        callback.onHeadlineSelected(position);
    }

    @Override
    public void onAttach(android.content.Context context) {
        super.onAttach(context);
        try {
            callback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
