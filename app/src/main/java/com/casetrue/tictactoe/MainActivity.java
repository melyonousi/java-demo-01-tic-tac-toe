package com.casetrue.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtMyScore;
    TextView txtRobot;
    List<Button> buttons = new ArrayList<>();

    Random random;

    int myWins = 0;
    int robotWins = 0;
    int n = 0;
    String x = "X";
    String o = "O";

    boolean isWin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRobot = findViewById(R.id.txtRobotScore);
        txtMyScore = findViewById(R.id.txtMyScore);

        int[] ids = {R.id.btn_0_0, R.id.btn_0_1, R.id.btn_0_2, R.id.btn_1_0, R.id.btn_1_1, R.id.btn_1_2, R.id.btn_2_0, R.id.btn_2_1, R.id.btn_2_2};

        for (int id : ids)
            buttons.add(findViewById(id));

        random = new Random();

        txtMyScore.setText(String.format("%s:: %s", getResources().getText(R.string.my_score), 0));
        txtRobot.setText(String.format("%s:: %s", getResources().getText(R.string.robot_score), 0));
    }

    /**
     * user turn
     */
    public void runGame(View view)
    {
        if (isWin)
        {
            Toast.makeText(getApplicationContext(), "Restart the Game", Toast.LENGTH_LONG).show();
            return;
        }

        Button button = (Button) view;
        button.setText(o);
        button.setEnabled(false);

        new Handler().postDelayed(this::robot, 800);

        if (myWin())
        {
            myWins++;
            txtMyScore.setText(String.format("%s:: %s, (%s)", getResources().getText(R.string.my_score), "You Win", myWins));
            Toast.makeText(getApplicationContext(), "you are the winer", Toast.LENGTH_LONG).show();
            isWin = true;
        }
    }

    /**
     * this function is multi use to check if button click
     */
    private boolean checkButton(int first, int second, int third)
    {
        if ((buttons.get(first).getText() == x && buttons.get(second).getText() == x && buttons.get(third).isEnabled()) ||
                buttons.get(first).getText() == o && buttons.get(second).getText() == o && buttons.get(third).isEnabled())
        {
            buttons.get(third).setText(x);
            buttons.get(third).setEnabled(false);
            n++;
            return true;
        }
        return false;
    }

    /**
     * robot turn
     */
    private void robot()
    {
        if (isWin)
        {
            Toast.makeText(getApplicationContext(), "Restart the Game", Toast.LENGTH_LONG).show();
            return;
        }

        while (true)
        {


            if (checkButton(0, 1, 2)) break;
            if (checkButton(2, 1, 0)) break;
            if (checkButton(2, 0, 1)) break;
            if (checkButton(3, 4, 5)) break;
            if (checkButton(5, 4, 3)) break;
            if (checkButton(3, 5, 4)) break;
            if (checkButton(6, 7, 8)) break;
            if (checkButton(8, 7, 6)) break;
            if (checkButton(6, 8, 7)) break;
            if (checkButton(0, 3, 6)) break;
            if (checkButton(6, 3, 0)) break;
            if (checkButton(0, 6, 3)) break;
            if (checkButton(1, 4, 7)) break;
            if (checkButton(7, 4, 1)) break;
            if (checkButton(1, 7, 4)) break;
            if (checkButton(2, 5, 8)) break;
            if (checkButton(8, 5, 2)) break;
            if (checkButton(2, 8, 5)) break;
            if (checkButton(0, 4, 8)) break;
            if (checkButton(8, 4, 0)) break;
            if (checkButton(0, 8, 4)) break;
            if (checkButton(2, 4, 6)) break;
            if (checkButton(6, 4, 2)) break;
            if (checkButton(2, 6, 4)) break;

            int robot = random.nextInt(9);
            if (buttons.get(robot).isEnabled())
            {
                buttons.get(robot).setText(x);
                buttons.get(robot).setEnabled(false);
                n++;
                break;
            }

            if (n >= 4)
                break;
        }

        if (robotWin())
        {
            robotWins++;
            txtRobot.setText(String.format("%s:: %s, (%s)", getResources().getText(R.string.robot_score), "Robot win", robotWins));
            Toast.makeText(getApplicationContext(), "the robot is wining", Toast.LENGTH_LONG).show();
            isWin = true;
        }
    }

    /**
     * this method multi use to color the line win
     */
    private boolean checkButtonWin(int first, int second, int third, String symbole)
    {
        if (buttons.get(first).getText() == symbole && buttons.get(second).getText() == symbole && buttons.get(third).getText() == symbole)
        {
            buttons.get(first).setBackgroundColor(getResources().getColor(R.color.purple_500));
            buttons.get(second).setBackgroundColor(getResources().getColor(R.color.purple_500));
            buttons.get(third).setBackgroundColor(getResources().getColor(R.color.purple_500));
            return true;
        }
        return false;
    }

    /**
     * check if user win
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private boolean myWin()
    {
        for (int i = 0; i < 9; i++)
        {
            if (checkButtonWin(0, 1, 2, o)) return true;
            if (checkButtonWin(3, 4, 5, o)) return true;
            if (checkButtonWin(6, 7, 8, o)) return true;
            if (checkButtonWin(0, 3, 6, o)) return true;
            if (checkButtonWin(1, 4, 7, o)) return true;
            if (checkButtonWin(2, 5, 8, o)) return true;
            if (checkButtonWin(0, 4, 8, o)) return true;
            if (checkButtonWin(2, 4, 6, o)) return true;
        }
        return false;
    }

    /**
     * check if robot win
     */
    private boolean robotWin()
    {
        for (int i = 0; i < 9; i++)
        {
            if (checkButtonWin(0, 1, 2, x)) return true;
            if (checkButtonWin(3, 4, 5, x)) return true;
            if (checkButtonWin(6, 7, 8, x)) return true;
            if (checkButtonWin(0, 3, 6, x)) return true;
            if (checkButtonWin(1, 4, 7, x)) return true;
            if (checkButtonWin(2, 5, 8, x)) return true;
            if (checkButtonWin(0, 4, 8, x)) return true;
            if (checkButtonWin(2, 4, 6, x)) return true;
        }
        return false;
    }

    boolean turn = true;

    /**
     * restart the game
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public void clear(View view)
    {
        for (int i = 0; i < 9; i++)
        {
            buttons.get(i).setEnabled(true);
            buttons.get(i).setText("");
            buttons.get(i).setBackgroundColor(getResources().getColor(R.color.purple_200));
            buttons.get(i).setBackground(getResources().getDrawable(R.drawable.rounded_btn));
        }

        n = 0;
        isWin = false;

        if (turn)
        {
            new Handler().postDelayed(this::robot, 800);
            turn = false;
        }
        else
            turn = true;
    }
}