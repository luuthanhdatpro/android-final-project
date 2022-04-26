package com.example.androidfinal.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidfinal.R;
import com.example.androidfinal.model.Question;

import java.util.ArrayList;

public class ExamGridViewAdapter extends BaseAdapter {
    private Context context;
    private String[] index;
    private ArrayList<Question> questionList;
    private LayoutInflater inflater;
    public ExamGridViewAdapter(Context context, String[] index) {
        this.context = context;
        this.index = index;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return index.length;
    }

    @Override
    public Object getItem(int i) {
        return index[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.gridview_row, viewGroup, false);
        Button button = (Button) view.findViewById(R.id.button_id);

        button.setText(index[i]);

        return view;
    }
}
