package com.example.androidfinal.adapter;

import android.content.Context;
import android.graphics.Color;
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
    private int[] index;
    private boolean[] isLearned;
    private ArrayList<Question> questionList;
    private LayoutInflater inflater;
    public ExamGridViewAdapter(Context context, int[] index) {
        this.context = context;
        this.index = index;
        this.inflater = (LayoutInflater.from(context));
    }

    public ExamGridViewAdapter(Context context, int[] index, boolean[] isLearned) {
        this.context = context;
        this.index = index;
        this.isLearned = isLearned;
        this.inflater = (LayoutInflater.from(context));
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
        if(view == null){
            view = inflater.inflate(R.layout.gridview_row, viewGroup, false);
        }
        TextView textView = view.findViewById(R.id.tv_id_grid);
        if (isLearned[i]==true)
            textView.setBackgroundColor(Color.YELLOW);
        textView.setText(String.valueOf(index[i]));

        return view;
    }
}
