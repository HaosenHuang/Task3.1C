package com.example.sit305quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Questions extends AppCompatActivity {

    TextView welcome;
    TextView title;
    TextView description;
    Button answer1;
    Button answer2;
    Button answer3;
    Button submit;
    public Integer selectedAns = 0;
    public Integer questionNum = 0;
    public Integer questionCount = 5;
    public Integer correctAns = 0;
    public Boolean submitted = false;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        progress = findViewById(R.id.progress_bar);
        welcome = findViewById(R.id.welcomeLabel);
        String text = welcome.getText().toString();
        welcome.setText(text + ' ' + name.toString());
        title = findViewById(R.id.questionTitle);
        description = findViewById(R.id.questionDesc);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        Question[] questionsArr = getQuestions();
        title.setText(questionsArr[questionNum].title.toString());
        answer1.setText(questionsArr[questionNum].answer1.toString());
        answer2.setText(questionsArr[questionNum].answer2.toString());
        answer3.setText(questionsArr[questionNum].answer3.toString());
        submit = findViewById(R.id.submitBottom);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!submitted) {
                    Integer answer = questionsArr[questionNum].answer;
                    String msg = "";
                    if (selectedAns == 0) {
                        msg = "Please select an answer";
                    } else if (selectedAns == answer) {
                        msg = "Correct";
                        correctAns ++;
                    }
                    else {
                        msg = "False";
                    }

                    if (selectedAns > 0) {
                        if (selectedAns == 1) {
                            answer1.setBackgroundColor(getResources().getColor(R.color.red));
                        } else if (selectedAns == 2) {
                            answer2.setBackgroundColor(getResources().getColor(R.color.red));
                        } else {
                            answer3.setBackgroundColor(getResources().getColor(R.color.red));
                        }

                        if (answer == 1) {
                            answer1.setBackgroundColor(getResources().getColor(R.color.teal_700));
                        } else if (answer == 2) {
                            answer2.setBackgroundColor(getResources().getColor(R.color.teal_700));
                        } else {
                            answer3.setBackgroundColor(getResources().getColor(R.color.teal_700));
                        }
                        submitted = true;
                        progress.incrementProgressBy(20);
                        submit.setText(R.string.next_button_label);
                        description.setText(questionsArr[questionNum].desc.toString());
                    }
                    Toast.makeText(Questions.this, msg, Toast.LENGTH_SHORT ).show();
                } else {
                    selectedAns = 0;
                    questionNum++;
                    if (questionNum >= questionCount) {
                        Intent intent = new Intent(getApplicationContext(), Result.class);
                        intent.putExtra("answers", correctAns.toString());
                        intent.putExtra("name", name);
                        startActivityForResult(intent, 1);
                        finish();
                    } else {
                        submitted = false;
                        description.setText(R.string.empty_string);
                        title.setText(questionsArr[questionNum].title.toString());
                        answer1.setText(questionsArr[questionNum].answer1.toString());
                        answer1.setBackgroundColor(getResources().getColor(R.color.white));
                        answer2.setText(questionsArr[questionNum].answer2.toString());
                        answer2.setBackgroundColor(getResources().getColor(R.color.white));
                        answer3.setText(questionsArr[questionNum].answer3.toString());
                        answer3.setBackgroundColor(getResources().getColor(R.color.white));
                        submit.setText(getResources().getText(R.string.submit_button_label));
                    }
                }
            }
        });
    }

    public void answerOneClick(View view) {
        if (!submitted) {
            if (selectedAns == 1) {
                selectedAns = 0;
                answer1.setBackgroundColor(getResources().getColor(R.color.white));
            } else {
                selectedAns = 1;
                answer1.setBackgroundColor(getResources().getColor(R.color.purple_500));
                answer2.setBackgroundColor(getResources().getColor(R.color.white));
                answer3.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
    }

    public void answerTwoClick(View view) {
        if (!submitted) {
            if (selectedAns == 2) {
                selectedAns = 0;
                answer2.setBackgroundColor(getResources().getColor(R.color.white));
            } else {
                selectedAns = 2;
                answer1.setBackgroundColor(getResources().getColor(R.color.white));
                answer2.setBackgroundColor(getResources().getColor(R.color.purple_500));
                answer3.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
    }

    public void answerThreeClick(View view) {
        if (!submitted) {
            if (selectedAns == 3) {
                selectedAns = 0;
                answer3.setBackgroundColor(getResources().getColor(R.color.white));
            } else {
                selectedAns = 3;
                answer1.setBackgroundColor(getResources().getColor(R.color.white));
                answer2.setBackgroundColor(getResources().getColor(R.color.white));
                answer3.setBackgroundColor(getResources().getColor(R.color.purple_500));
            }
        }
    }

    public Question[] getQuestions() {
        Question[] questionsArr;
        questionsArr = new Question[questionCount];

        questionsArr[0] = new Question(
                "In which year was Android beta released?",
                " ",
                "2006",
                "2007",
                "2008",
                2
        );

        questionsArr[1] = new Question(
                "Android programs are written in __?",
                " ",
                "C/C++",
                "Python",
                "Java",
                3
        );

        questionsArr[2] = new Question(
                "Android has ite own libraries is written in __?",
                " ",
                "C/C++",
                "Python",
                "Java",
                1
        );

        questionsArr[3] = new Question(
                "Dose TextView allow editing in itself?",
                " ",
                "No",
                "Yes",
                "It depends",
                1
        );

        questionsArr[4] = new Question(
                "The View objects are usually called '__' and can be one of many subclasses?",
                " ",
                "layouts",
                " containers",
                "widgets",
                3
        );
        return questionsArr;
    }

    class Question {
        public String title;
        public String desc;
        public String answer1;
        public String answer2;
        public String answer3;
        public Integer answer;

        Question(String title, String desc, String answerOne,
                 String answerTwo, String answerThree,
                 Integer answer)
        {
            this.title = title;
            this.desc = desc;
            this.answer1 = answerOne;
            this.answer2 = answerTwo;
            this.answer3 = answerThree;
            this.answer = answer;
        }
    }
}

