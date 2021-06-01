package com.example.gradecalculator.management;

import java.util.ArrayList;

public class ManageClass {

    private String name; //과목명
    private String kind; //전공/교양
    private String kind_domain; //영역
    private String kind_major; //전공분류
    private String credit;//학점
    private String grade;//성적
    private String retake;//재수강


    public ManageClass(String name, String kind, String kind_major, String kind_domain, String credit, String grade, String retake) {
        this.name = name;
        this.kind = kind;
        this.kind_major = kind_major;
        this.kind_domain = kind_domain;
        this.credit = credit;
        this.grade = grade;
        this.retake = retake;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKindMajor() {
        return kind_major;
    }
    public void setKindMajor(String kind_major) {
        this.kind_major = kind_major;
    }

    public String getKindDomain() {
        return kind_domain;
    }
    public void setKindDomain(String kind_domain) {
        this.kind_domain = kind_domain;
    }

    public String getCredit() {
        return credit;
    }
    public void setCredit(String credit) { this.credit = credit; }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRetake() {
        return retake;
    }
    public void setRetake(String retake) {
        this.retake = retake;
    }

}
