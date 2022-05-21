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
import com.example.androidfinal.inteface.SignListOnClickListener;
import com.example.androidfinal.model.Exam;
import com.example.androidfinal.model.Sign;

import java.util.ArrayList;

public class SignListAdapter extends RecyclerView.Adapter<SignListAdapter.ItemSignViewHolder> {
    private ArrayList<Sign> signList;
    private SignListOnClickListener signListOnClickListener;
    private Context context;
    public SignListAdapter(ArrayList<Sign> signList, SignListOnClickListener signListOnClickListener, Context context) {
        this.signList = signList;
        this.signListOnClickListener = signListOnClickListener;
        this.context = context;
    }

    public SignListAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Sign> list){
        this.signList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemSignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sign_item,parent,false);
        return new ItemSignViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSignViewHolder holder, int position) {
        Sign sign = signList.get(position);
        String image;
        if(sign == null)
            return;
        holder.tvSignTitle.setText(sign.getTitle());
        holder.tvSignName.setText(sign.getName());
        holder.tvSignDes.setText(sign.getMeaning());
        if(sign.getImage()!=null){
            image = "@drawable/" + sign.getImage();
            int imageResource = context.getResources().getIdentifier(image.substring(0,image.indexOf(".")), null, context.getPackageName());
            holder.image.setImageResource(imageResource);
        }
        holder.cardView.setOnClickListener(view -> signListOnClickListener.clickItem(sign));
    }

    @Override
    public int getItemCount() {
        if(signList != null)
            return signList.size();
        return 0;
    }

    public class ItemSignViewHolder extends RecyclerView.ViewHolder{
        private TextView tvSignTitle, tvSignName, tvSignDes;
        private ImageView image;
        private CardView cardView;
        public ItemSignViewHolder(@NonNull View itemView){
            super(itemView);

            tvSignTitle = itemView.findViewById(R.id.tv_sign_title);
            tvSignName = itemView.findViewById(R.id.tv_sign_name);
            tvSignDes = itemView.findViewById(R.id.tv_sign_description);
            image = itemView.findViewById(R.id.image_sign);
            cardView = itemView.findViewById(R.id.cardview_sign_item);
        }
    }
}
