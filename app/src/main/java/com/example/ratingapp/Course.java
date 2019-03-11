package com.example.ratingapp;

import android.os.Parcel;
import android.os.Parcelable;

class Course implements Parcelable
{
    private String name, rating;
    private int subjectRelevans, overallPerformance, preparation,
            feedback, examples, jobOpportunities;

    public Course(String name) {
        this.name = name;
        this.rating = "N/A";
        this.subjectRelevans = 50;
        this.overallPerformance = 50;
        this.preparation = 50;
        this.feedback = 50;
        this.examples = 50;
        this.jobOpportunities = 50;
    }

    protected Course(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getSubjectRelevans() {
        return subjectRelevans;
    }

    public void setSubjectRelevans(int subjectRelevans) {
        this.subjectRelevans = subjectRelevans;
    }

    public int getOverallPerformance() {
        return overallPerformance;
    }

    public void setOverallPerformance(int overallPerformance) {
        this.overallPerformance = overallPerformance;
    }

    public int getPreparation() {
        return preparation;
    }

    public void setPreparation(int preparation) {
        this.preparation = preparation;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public int getExamples() {
        return examples;
    }

    public void setExamples(int examples) {
        this.examples = examples;
    }

    public int getJobOpportunities() {
        return jobOpportunities;
    }

    public void setJobOpportunities(int jobOpportunities) {
        this.jobOpportunities = jobOpportunities;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
