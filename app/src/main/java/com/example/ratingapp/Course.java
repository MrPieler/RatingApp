package com.example.ratingapp;

import android.os.Parcel;
import android.os.Parcelable;


class Course implements Parcelable
{
    private String name, rating, email;
    private int subjectRelevans, overallPerformance, preparation,
            feedback, examples, jobOpportunities;

    public Course(String name, String email) {
        this.name = name;
        this.rating = "N/A";
        this.email = email;
        this.subjectRelevans = 50;
        this.overallPerformance = 50;
        this.preparation = 50;
        this.feedback = 50;
        this.examples = 50;
        this.jobOpportunities = 50;
    }

    protected Course(Parcel parcel)
    {
        this.name = parcel.readString();
        this.rating = parcel.readString();
        this.email = parcel.readString();
        this.subjectRelevans = parcel.readInt();
        this.overallPerformance = parcel.readInt();
        this.preparation = parcel.readInt();
        this.feedback = parcel.readInt();
        this.examples = parcel.readInt();
        this.jobOpportunities = parcel.readInt();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.name);
        dest.writeString(this.rating);
        dest.writeString((this.email));
        dest.writeInt(this.subjectRelevans);
        dest.writeInt(this.overallPerformance);
        dest.writeInt(this.preparation);
        dest.writeInt(this.feedback);
        dest.writeInt(this.examples);
        dest.writeInt(this.jobOpportunities);
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void updateRating() {
        int average = (this.overallPerformance + this.subjectRelevans + this.preparation +
                        this.feedback + this.examples + this.jobOpportunities)/6;
        if (average>90)
        {
            this.rating = "A";
        }
        else if (average>80)
        {
            this.rating = "B";
        }
        else if (average>70)
        {
            this.rating = "C";
        }
        else if (average>60)
        {
            this.rating = "D";
        }
        else if (average>50)
        {
            this.rating = "E";
        }
        else
        {
            this.rating = "Get a new job";
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubjectRelevans() {
        return subjectRelevans+"";
    }

    public void setSubjectRelevans(int subjectRelevans) {
        this.subjectRelevans = subjectRelevans;
    }

    public String getOverallPerformance() {
        return overallPerformance+"";
    }

    public void setOverallPerformance(int overallPerformance) {
        this.overallPerformance = overallPerformance;
    }

    public String getPreparation() {
        return preparation+"";
    }

    public void setPreparation(int preparation) {
        this.preparation = preparation;
    }

    public String getFeedback() {
        return feedback+"";
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public String getExamples() {
        return examples+"";
    }

    public void setExamples(int examples) {
        this.examples = examples;
    }

    public String getJobOpportunities() {
        return jobOpportunities+"";
    }

    public void setJobOpportunities(int jobOpportunities) {
        this.jobOpportunities = jobOpportunities;
    }

    @Override
    public String toString() {
        return this.name;
    }


}
