package com.example.ratingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RateCourse extends AppCompatActivity {

    TextView name, overallText, relavansText, preparationText, feedbackText, exampleText, jobText;
    EditText overall, relavans, preparation, feedback, example, job;
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_course);

        init();

        if (savedInstanceState != null)
        {
            overall.setText(savedInstanceState.getString("overall"));
            relavans.setText(savedInstanceState.getString("relavans"));
            preparation.setText(savedInstanceState.getString("preparation"));
            feedback.setText(savedInstanceState.getString("feedback"));
            example.setText(savedInstanceState.getString("example"));
            job.setText(savedInstanceState.getString("job"));
        }
    }

    private void init()
    {
        Intent intent = getIntent();

        Bundle data = getIntent().getExtras();
        course = (Course) data.getParcelable(MainActivity.SOURCE+ "-Course");

        name = findViewById(R.id.nameText);
        name.setText(course.getName());

        overallText = findViewById(R.id.overallText);
        overall = findViewById(R.id.overallEdit);
        overall.setText(course.getOverallPerformance());
        overall.setFilters(new InputFilter[] {new InputFilterMinMax(1, 100)});

        relavansText = findViewById(R.id.SubjectText);
        relavans = findViewById(R.id.SubjectEdit);
        relavans.setText(course.getSubjectRelevans());
        relavans.setFilters(new InputFilter[] {new InputFilterMinMax(1, 100)});

        preparationText = findViewById(R.id.preparationText);
        preparation = findViewById(R.id.preparationEdit);
        preparation.setText(course.getPreparation());
        preparation.setFilters(new InputFilter[] {new InputFilterMinMax(1, 100)});

        feedbackText = findViewById(R.id.feedbackText);
        feedback = findViewById(R.id.feedbackEdit);
        feedback.setText(course.getFeedback());
        feedback.setFilters(new InputFilter[] {new InputFilterMinMax(1, 100)});

        exampleText = findViewById(R.id.examplesText);
        example = findViewById(R.id.examplesEdit);
        example.setText(course.getExamples());
        example.setFilters(new InputFilter[] {new InputFilterMinMax(1, 100)});

        jobText = findViewById(R.id.jobText);
        job = findViewById(R.id.jobEdit);
        job.setText(course.getJobOpportunities());
        job.setFilters(new InputFilter[] {new InputFilterMinMax(1, 100)});
    }

    public void rateCourse(View view)
    {
        updateCourse();
        showInputDialog();
    }

    protected void updateCourse()
    {
        for (Course c:MainActivity.courses)
        {
            if (c.getName().equals(course.getName()))
            {
                course = c;
                course.setOverallPerformance(Integer.parseInt(overall.getText().toString()));
                course.setSubjectRelevans(Integer.parseInt(relavans.getText().toString()));
                course.setPreparation(Integer.parseInt(preparation.getText().toString()));
                course.setFeedback(Integer.parseInt(feedback.getText().toString()));
                course.setExamples(Integer.parseInt(example.getText().toString()));
                course.setJobOpportunities(Integer.parseInt(job.getText().toString()));
                course.updateRating();
                break;
            }
        }
    }

    protected void showInputDialog()
    {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(RateCourse.this);
        View promptView = layoutInflater.inflate(R.layout.send_mail_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RateCourse.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.nameEdit);
        final EditText emailText = (EditText) promptView.findViewById(R.id.emailEdit);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        sendMail();
                        endActivity();
                    }
                })
                .setNegativeButton("No, just submit ratings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                endActivity();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    protected void endActivity()
    {
        this.finish();
    }

    protected void sendMail()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);

        String[] email = course.getEmail().split(",");
        String text = "";
        text += "Rating score: " + course.getRating();
        text += "\nOverall Performance: " + course.getOverallPerformance();
        text += "\nSubject Relavans: " + course.getSubjectRelevans();
        text += "\nPreparation: " + course.getPreparation();
        text += "\nAmount of Feedback: " + course.getFeedback();
        text += "\nExamples: " + course.getExamples();
        text += "\nJob Opportunities: " + course.getJobOpportunities();

        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Rating results - " + course.getName());
        intent.putExtra(Intent.EXTRA_TEXT, text);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("overall", overall.getText().toString());
        outState.putString("relavans", relavans.getText().toString());
        outState.putString("preparation", preparation.getText().toString());
        outState.putString("feedback", feedback.getText().toString());
        outState.putString("example", example.getText().toString());
        outState.putString("job", job.getText().toString());


    }
}
