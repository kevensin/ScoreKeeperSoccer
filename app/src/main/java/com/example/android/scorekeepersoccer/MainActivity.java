package com.example.android.scorekeepersoccer;

/*
Stopwatch could not have been done without the help of android-examples.com
Stopwatch code came from site below:
https://www.android-examples.com/android-create-stopwatch-example-tutorial-in-android-studio/
*/

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TextView team1Text, team2Text, timeText;
    public Button team1DecrementBtn, team1IncrementBtn, team2DecrementBtn, team2IncrementBtn,
                    timeStartBtn, timeStopBtn, timeResetBtn;
    public boolean decrementButton1, decrementButton2 = false;
    public int team1Score, team2Score = 0;

    public String plus = "+" , minus = "-", start = "start", stop = "stop", reset = "reset"
                        , baseTimeStopWatch = "00:00:000";

    //_______________________________________________________________________________________

    public long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    public Handler handler;
    public int Seconds, Minutes, MilliSeconds;
    public String[] ListElements = new String[] {};
    public List<String> ListElementsArrayList;
    public ArrayAdapter<String> adapter;

    //_______________________________________________________________________________________

    public Button[] incrementButton = new Button[8];
    public Button[] decrementButton = new Button[8];
    public TextView[] teamStatusText = new TextView[8];
    public int[] teamStatusInt = {0, 0, 0, 0, 0, 0, 0, 0};

    public TextView[] nameStatusText = new TextView[4];
    public String[] nameStatusString = {"fouls", "yellows", "reds", "off-sides"};

    //decrement method decreases input int value then returns the same int value but updated
    public int decrement(int score)
    {
        score--;
        if(score <= 0)score = 0;
        return score;
    }

    /*
    score uses the decrement method to decrease or increase
    the int values team1Score and team2Score when buttons
    team1DecrementBtn, team1IncrementBtn, team2DecrementBtn or team2IncrementBtn
    are clicked then its set to display through a .setText()
    */
    public void score(View v)
    {
        if(v.getId() == R.id.buttonIncrementTeam1)team1Score++;
        if(v.getId() == R.id.buttonDecrementTeam1)team1Score = decrement(team1Score);
        if(team1Score >= 1)decrementButton1 = true;
        if(team1Score <= 0)decrementButton1 = false;
        team1DecrementBtn.setEnabled(decrementButton1);
        team1Text.setText("Team 1\n" + team1Score);

        if(v.getId() == R.id.buttonIncrementTeam2)team2Score++;
        if(v.getId() == R.id.buttonDecrementTeam2)team2Score = decrement(team2Score);
        if(team2Score >= 1)decrementButton2 = true;
        if(team2Score <= 0)decrementButton2 = false;
        team2DecrementBtn.setEnabled(decrementButton2);
        team2Text.setText("Team 2\n" + team2Score);
    }

    /*
    like the method score the statusTeams method is used to increase or decrease
    any of int values within the array teamStatusInt when any of the of the buttons
    within the array of buttons named incrementButton or decrementButton are touched
     */
    public void statusTeams(View v)
    {
        if(v.getId() == R.id.buttonIncrementTeam1Fouls)teamStatusInt[0]++;
        if(v.getId() == R.id.buttonDecrementTeam1Fouls)
        {
            teamStatusInt[0]--;
            if(teamStatusInt[0]<=0)teamStatusInt[0]= 0;
        }
            //teamStatusText[0].setText(teamStatusInt[0] + "");

        if(v.getId() == R.id.buttonIncrementTeam2Fouls)teamStatusInt[1]++;
        if(v.getId() == R.id.buttonDecrementTeam2Fouls)
        {
            teamStatusInt[1]--;
            if(teamStatusInt[1]<=0)teamStatusInt[1]= 0;
        }

        if(v.getId() == R.id.buttonIncrementTeam1Yellows)teamStatusInt[2]++;
        if(v.getId() == R.id.buttonDecrementTeam1Yellows)
        {
            teamStatusInt[2]--;
            if(teamStatusInt[2]<=0)teamStatusInt[2]= 0;
        }

        if(v.getId() == R.id.buttonIncrementTeam2Yellows)teamStatusInt[3]++;
        if(v.getId() == R.id.buttonDecrementTeam2Yellows)
        {
            teamStatusInt[3]--;
            if(teamStatusInt[3]<=0)teamStatusInt[3]= 0;
        }

        if(v.getId() == R.id.buttonIncrementTeam1Reds)teamStatusInt[4]++;
        if(v.getId() == R.id.buttonDecrementTeam1Reds)
        {
            teamStatusInt[4]--;
            if(teamStatusInt[4]<=0)teamStatusInt[4]= 0;
        }

        if(v.getId() == R.id.buttonIncrementTeam2Reds)teamStatusInt[5]++;
        if(v.getId() == R.id.buttonDecrementTeam2Reds)
        {
            teamStatusInt[5]--;
            if(teamStatusInt[5]<=0)teamStatusInt[5]= 0;
        }

        if(v.getId() == R.id.buttonIncrementTeam1OffSides)teamStatusInt[6]++;
        if(v.getId() == R.id.buttonDecrementTeam1OffSides)
        {
            teamStatusInt[6]--;
            if(teamStatusInt[6]<=0)teamStatusInt[6]= 0;
        }

        if(v.getId() == R.id.buttonIncrementTeam2OffSides)teamStatusInt[7]++;
        if(v.getId() == R.id.buttonDecrementTeam2OffSides)
        {
            teamStatusInt[7]--;
            if(teamStatusInt[7]<=0)teamStatusInt[7]= 0;
        }

        for(int i = 0; i<teamStatusInt.length; i++)
        {
            if(teamStatusInt[i]<=0)decrementButton[i].setEnabled(false);
            if(teamStatusInt[i]>=1)decrementButton[i].setEnabled(true);
            teamStatusText[i].setText("" + teamStatusInt[i]);
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        team1Text = findViewById(R.id.textTeam1);
        team1Text.setText("Team 1\n" + team1Score);
        team1IncrementBtn = findViewById(R.id.buttonIncrementTeam1);
        team1IncrementBtn.setText(plus);
        team1DecrementBtn = findViewById(R.id.buttonDecrementTeam1);
        team1DecrementBtn.setEnabled(decrementButton1);
        team1DecrementBtn.setText(minus);

        team2Text = findViewById(R.id.textTeam2);
        team2Text.setText("Team 2\n" + team2Score);
        team2IncrementBtn = findViewById(R.id.buttonIncrementTeam2);
        team2IncrementBtn.setText(plus);
        team2DecrementBtn = findViewById(R.id.buttonDecrementTeam2);
        team2DecrementBtn.setEnabled(decrementButton2);
        team2DecrementBtn.setText(minus);

        timeText = findViewById(R.id.textStopWatch);
        timeText.setText(baseTimeStopWatch);
        timeStartBtn = findViewById(R.id.buttonStart);
        timeStartBtn.setText(start);
        timeStartBtn.setEnabled(true);
        timeStopBtn = findViewById(R.id.buttonStop);
        timeStopBtn.setText(stop);
        timeStopBtn.setEnabled(false);
        timeResetBtn = findViewById(R.id.buttonReset);
        timeResetBtn.setText(reset);
        timeResetBtn.setEnabled(false);

        //___________________________________________________________________________


        handler = new Handler();
        ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));

        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                ListElementsArrayList);

        timeStartBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                timeStartBtn.setEnabled(false);
                timeStopBtn.setEnabled(true);
                timeResetBtn.setEnabled(false);

            }
        });

        timeStopBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                TimeBuff += MillisecondTime;
                handler.removeCallbacks(runnable);

                timeStartBtn.setEnabled(true);
                timeStopBtn.setEnabled(false);
                timeResetBtn.setEnabled(true);
            }
        });

        timeResetBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                MillisecondTime = 0L;
                StartTime = 0L;
                TimeBuff = 0L;
                UpdateTime = 0L;
                Seconds = 0;
                Minutes = 0;
                MilliSeconds = 0;

                timeText.setText("00:00:00");
                ListElementsArrayList.clear();
                adapter.notifyDataSetChanged();

                timeStartBtn.setEnabled(true);
                timeStopBtn.setEnabled(false);
                timeResetBtn.setEnabled(false);
            }
        });
        //___________________________________________________________________________

        incrementButton[0] = findViewById(R.id.buttonIncrementTeam1Fouls);
        incrementButton[1] = findViewById(R.id.buttonIncrementTeam2Fouls);
        incrementButton[2] = findViewById(R.id.buttonIncrementTeam1Yellows);
        incrementButton[3] = findViewById(R.id.buttonIncrementTeam2Yellows);
        incrementButton[4] = findViewById(R.id.buttonIncrementTeam1Reds);
        incrementButton[5] = findViewById(R.id.buttonIncrementTeam2Reds);
        incrementButton[6] = findViewById(R.id.buttonIncrementTeam1OffSides);
        incrementButton[7] = findViewById(R.id.buttonIncrementTeam2OffSides);

        decrementButton[0] = findViewById(R.id.buttonDecrementTeam1Fouls);
        decrementButton[1] = findViewById(R.id.buttonDecrementTeam2Fouls);
        decrementButton[2] = findViewById(R.id.buttonDecrementTeam1Yellows);
        decrementButton[3] = findViewById(R.id.buttonDecrementTeam2Yellows);
        decrementButton[4] = findViewById(R.id.buttonDecrementTeam1Reds);
        decrementButton[5] = findViewById(R.id.buttonDecrementTeam2Reds);
        decrementButton[6] = findViewById(R.id.buttonDecrementTeam1OffSides);
        decrementButton[7] = findViewById(R.id.buttonDecrementTeam2OffSides);

        teamStatusText[0] = findViewById(R.id.textTeam1Fouls);
        teamStatusText[1] = findViewById(R.id.textTeam2Fouls);
        teamStatusText[2] = findViewById(R.id.textTeam1Yellows);
        teamStatusText[3] = findViewById(R.id.textTeam2Yellows);
        teamStatusText[4] = findViewById(R.id.textTeam1Reds);
        teamStatusText[5] = findViewById(R.id.textTeam2Reds);
        teamStatusText[6] = findViewById(R.id.textTeam1OffSides);
        teamStatusText[7] = findViewById(R.id.textTeam2OffSides);

        for(int i = 0; i < incrementButton.length; i++)
        {
            incrementButton[i].setText(plus);
            decrementButton[i].setText(minus);
            if(teamStatusInt[i]<=0)decrementButton[i].setEnabled(false);
            if(teamStatusInt[i]>=1)decrementButton[i].setEnabled(true);
            teamStatusText[i].setText("" + teamStatusInt[i]);
        }

        //_______________________________________________________________________

        nameStatusText[0] = findViewById(R.id.textFouls);
        nameStatusText[1] = findViewById(R.id.textYellows);
        nameStatusText[2] = findViewById(R.id.textReds);
        nameStatusText[3] = findViewById(R.id.textOffSides);
        for(int i = 0; i<nameStatusText.length; i++)
        {
            nameStatusText[i].setText(nameStatusString[i]);
            nameStatusText[i].setAllCaps(true);
        }

    }


    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime/1000);
            Minutes = Seconds/60;
            Seconds = Seconds % 60;
            MilliSeconds = (int) (UpdateTime % 1000);

            String overallTime = "";
            if(Minutes < 10) overallTime+= "0";
            overallTime += Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds);
            timeText.setText(overallTime);
            handler.postDelayed(this, 0);
        }
    };


}
