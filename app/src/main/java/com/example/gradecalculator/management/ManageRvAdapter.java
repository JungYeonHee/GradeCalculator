package com.example.gradecalculator.management;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gradecalculator.R;

import java.util.ArrayList;

public class ManageRvAdapter extends RecyclerView.Adapter<Management_ViewHolder> {

    private ArrayList<ManageClass> manageClasses;

    public ManageRvAdapter(ArrayList<ManageClass> manageClasses) {
        this.manageClasses = manageClasses;
    }

    @Override
    public Management_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 사용할 아이템의 뷰를 생성해준다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_manage_item, parent, false);

        Management_ViewHolder holder = new Management_ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Management_ViewHolder holder, int position) {
        ManageClass aManageClass = manageClasses.get(position);

        holder.name.setText(aManageClass.getName());
        holder.kind.setText(aManageClass.getKind());
        holder.kind_major.setText(aManageClass.getKindMajor());
        holder.kind_domain.setText(aManageClass.getKindDomain());
        holder.credit.setText(aManageClass.getCredit());
        holder.grade.setText(aManageClass.getGrade());
        holder.retake.setText(aManageClass.getRetake());
    }

    @Override
    public int getItemCount() {
        return manageClasses.size();
    }

}
