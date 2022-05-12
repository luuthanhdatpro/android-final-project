package com.example.androidfinal.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfinal.R;
import com.example.androidfinal.inteface.ExamResultOnClickListener;
import com.example.androidfinal.model.Exam;
import com.example.androidfinal.model.Question;

import java.util.ArrayList;

public class ExamResultAdapter extends RecyclerView.Adapter<ExamResultAdapter.ExamResultViewHolder> {
    private ArrayList<Question> questionList;
    private ExamResultOnClickListener examResultOnClickListener;
    private String name;
    private int[] doneQues;
    public ExamResultAdapter(ArrayList<Question> questionList, ExamResultOnClickListener examResultOnClickListener) {
        this.questionList = questionList;
        this.examResultOnClickListener = examResultOnClickListener;
    }

    public ExamResultAdapter(ArrayList<Question> questionList, ExamResultOnClickListener examResultOnClickListener, int[] doneQues) {
        this.questionList = questionList;
        this.examResultOnClickListener = examResultOnClickListener;
        this.doneQues = doneQues;
    }

    public void setData(ArrayList<Question> list){
        this.questionList = list;
        notifyDataSetChanged();
    }
    public void setData(ArrayList<Question> list, int[] doneQues){
        this.questionList = list;
        this.doneQues = doneQues;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ExamResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_in_result,parent,false);
        return new ExamResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamResultViewHolder holder, int position) {
        Question question = questionList.get(position);
        if(question == null)
            return;
        holder.tvQuestion.setText(question.getTitle());
        holder.tvQuestionID.setText("Câu "+String.valueOf(question.getId())+"|35");
        if(doneQues[position]==0)
            holder.imageType.setBackgroundResource(R.drawable.warning);
        if(doneQues[position]==1)
            holder.imageType.setBackgroundResource(R.drawable.check);
        if (doneQues[position]==2)
            holder.imageType.setBackgroundResource(R.drawable.ic_baseline_clear_24);
        Log.i("pos", String.valueOf(doneQues[position]));
        if(question.isEssential()==true)
            holder.imageQuestionEss.setVisibility(View.VISIBLE);
        if(question.getImage()!=null)
            holder.imageQuestion.setVisibility(View.VISIBLE);
        holder.cardViewResult.setOnClickListener(view ->  examResultOnClickListener.clickItem(question));
    }

    @Override
    public int getItemCount() {
        if(questionList != null)
            return questionList.size();
        return 0;
    }

    public class ExamResultViewHolder extends RecyclerView.ViewHolder{
        private TextView tvQuestion, tvQuestionID;
        private ImageView imageType, imageQuestion, imageQuestionEss;
        private CardView cardViewResult;
        public ExamResultViewHolder(@NonNull View itemView){
            super(itemView);

            tvQuestion = itemView.findViewById(R.id.tv_question_result);
            tvQuestionID = itemView.findViewById(R.id.tv_question_id_result);
            imageQuestion = itemView.findViewById(R.id.image_question_result);
            imageQuestionEss = itemView.findViewById(R.id.image_is_essential_result);
            imageType = itemView.findViewById(R.id.image_question_done);
            cardViewResult = itemView.findViewById(R.id.cardview_result_item);
        }
    }
}
