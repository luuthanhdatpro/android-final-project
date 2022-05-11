package com.example.androidfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfinal.R;
import com.example.androidfinal.inteface.ExamListOnClickListener;
import com.example.androidfinal.model.Exam;

import java.util.ArrayList;

public class ExamListAdapter extends RecyclerView.Adapter<ExamListAdapter.ItemExamViewHolder> {
    private ArrayList<Exam> examsList;
    private ExamListOnClickListener examListOnClickListener;
    private Context context;
    public ExamListAdapter(ArrayList<Exam> examsList, ExamListOnClickListener examListOnClickListener) {
        this.examsList = examsList;
        this.examListOnClickListener = examListOnClickListener;
    }

    public ExamListAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Exam> list){
        this.examsList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_list_item,parent,false);
        return new ItemExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemExamViewHolder holder, int position) {
        Exam exam = examsList.get(position);
        if(exam == null)
            return;
        holder.tvExamName.setText(exam.getTitle());
        holder.image.setBackgroundResource(R.drawable.exam1);
        if(exam.getTitle().equals("Đề ngẫu nhiên"))
            holder.image.setBackgroundResource(R.drawable.random);
        holder.cardView.setOnClickListener(view -> examListOnClickListener.clickItem(exam));
    }

    @Override
    public int getItemCount() {
        if(examsList != null)
            return examsList.size();
        return 0;
    }

    public class ItemExamViewHolder extends RecyclerView.ViewHolder{
        private TextView tvExamName;
        private ImageView image;
        private CardView cardView;
        public ItemExamViewHolder(@NonNull View itemView){
            super(itemView);

            tvExamName = itemView.findViewById(R.id.tv_exam_name);
            image = itemView.findViewById(R.id.image_exam);
            cardView = itemView.findViewById(R.id.cardview_exam_list);
        }
    }
}
