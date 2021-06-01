package com.example.gradecalculator.management;

import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gradecalculator.R;

import java.text.BreakIterator;

public class Management_ViewHolder extends RecyclerView.ViewHolder {

    public TextView name; // 과목 이름
    public TextView kind; // 전공/교양
    public TextView kind_major; // 전공 분류
    public TextView kind_domain; // 과목 영역
    public TextView credit; // 학점
    public TextView grade; // 성적
    public TextView retake; // 재수강

    public Management_ViewHolder(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        name.setMovementMethod(new ScrollingMovementMethod());
        name.setSingleLine(true); name.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        kind = itemView.findViewById(R.id.kind);
        kind_major = itemView.findViewById(R.id.kind_major);
        kind_domain = itemView.findViewById(R.id.kind_domain);
        credit = itemView.findViewById(R.id.credit);
        grade = itemView.findViewById(R.id.grade);
        retake = itemView.findViewById(R.id.retake);
    }
}

