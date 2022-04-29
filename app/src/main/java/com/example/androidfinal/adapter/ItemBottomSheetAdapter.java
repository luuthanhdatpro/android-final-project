package com.example.androidfinal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfinal.R;
import com.example.androidfinal.inteface.OnclickListener;
import com.example.androidfinal.model.Question;

import java.util.ArrayList;
import java.util.List;

public class ItemBottomSheetAdapter extends  RecyclerView.Adapter<ItemBottomSheetAdapter.ItemViewHolder>{

    private ArrayList<Question> questionList;
    private OnclickListener onclickListener;

    public ItemBottomSheetAdapter(ArrayList<Question> questionList, OnclickListener onclickListener) {
        this.questionList = questionList;
        this.onclickListener = onclickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_in_list_view,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Question question = questionList.get(position);
        if(question == null)
            return;
        holder.tvId.setText("Câu"+question.getId()+"/"+questionList.size());
        if(question.isLearned()==true)
            holder.tvId.setText("Câu"+question.getId()+"/"+questionList.size()+"|đã học");
        holder.tvQuestion.setText(question.getTitle());
        if(question.isEssential() == true)
            holder.imageEssen.setVisibility(View.VISIBLE);
        if (question.getImage() != null)
            holder.imageQues.setVisibility(View.VISIBLE);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickListener.clickItem(question);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (questionList != null)
            return questionList.size();
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView tvId, tvQuestion;
        private ImageView imageEssen,imageQues;
        private CardView cardView;
        public ItemViewHolder(@NonNull View itemView){
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_question_id_bt);
            tvQuestion = itemView.findViewById(R.id.tv_question_bt);
            imageEssen = itemView.findViewById(R.id.image_is_essential);
            imageQues = itemView.findViewById(R.id.image_question);
            cardView = itemView.findViewById(R.id.cardview_bottom_item);
        }
    }
}
