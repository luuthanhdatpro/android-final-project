package com.example.androidfinal.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfinal.R;
import com.example.androidfinal.adapter.ItemBottomSheetAdapter;
import com.example.androidfinal.inteface.OnclickListener;
import com.example.androidfinal.model.Question;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    private ArrayList<Question> questionList;
    private OnclickListener onclickListener;

    public BottomSheetFragment(ArrayList<Question> questionList, OnclickListener onclickListener) {
        this.questionList = questionList;
        this.onclickListener = onclickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_question, null);
        bottomSheetDialog.setContentView(view);

        RecyclerView recyclerViewData = view.findViewById(R.id.rcv_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewData.setLayoutManager(linearLayoutManager);

        ItemBottomSheetAdapter itemBottomSheetAdapter = new ItemBottomSheetAdapter(questionList, new OnclickListener() {
            @Override
            public void clickItem(Question question) {
                onclickListener.clickItem(question);
            }
        });
        recyclerViewData.setAdapter(itemBottomSheetAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewData.addItemDecoration(itemDecoration);
        return bottomSheetDialog;
    }
}
