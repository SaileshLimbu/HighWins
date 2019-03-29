package np.com.softwarica.highwins.activities;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import np.com.softwarica.highwins.R;
import np.com.softwarica.highwins.hwo.HwOperation;
import np.com.softwarica.highwins.models.ScoreBoard;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnFirst, btnSecond, btnPlayAgain;
    private TextView tvStatus, tvCorrect, tvWrong;
    private View viewResult, viewButton, viewGameOver;
    private ScoreBoard scoreBoard;
    private HwOperation hwOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFirst = findViewById(R.id.btnFirst);
        btnSecond = findViewById(R.id.btnSecond);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        tvStatus = findViewById(R.id.tvStatus);
        tvCorrect = findViewById(R.id.tvCorrect);
        tvWrong = findViewById(R.id.tvWrong);
        viewResult = findViewById(R.id.viewResult);
        viewButton = findViewById(R.id.viewButton);
        viewGameOver = findViewById(R.id.viewGameOver);

        btnFirst.setOnClickListener(this);
        btnSecond.setOnClickListener(this);
        btnPlayAgain.setOnClickListener(this);

        scoreBoard = new ScoreBoard();
        hwOperation = new HwOperation();

        hwOperation.shuffleNumber();
        updateQue();
        updateScoreBoard();
    }

    @Override
    public void onClick(View view) {
        int firstNumber = Integer.parseInt(btnFirst.getText().toString());
        int secondNumber = Integer.parseInt(btnSecond.getText().toString());
        if (view.getId() == R.id.btnFirst) {
            if (firstNumber > secondNumber) {
                triggerCorrect();
                animate(btnFirst, true);
            } else {
                triggerWrong();
                animate(btnFirst, false);
            }
        } else if (view.getId() == R.id.btnSecond) {
            if (secondNumber > firstNumber) {
                triggerCorrect();
                animate(btnSecond, true);
            } else {
                triggerWrong();
                animate(btnSecond, false);
            }
        } else if (view.getId() == R.id.btnPlayAgain) {
            viewGameOver.setVisibility(View.GONE);
            viewButton.setVisibility(View.VISIBLE);
            viewResult.setVisibility(View.INVISIBLE);
            scoreBoard.resetScoreBoard();
            hwOperation.shuffleNumber();
            updateQue();
        }

        updateScoreBoard();
        hwOperation.shuffleNumber();
        updateQue();
    }

    private void updateScoreBoard() {
        tvStatus.setText("Score : " + scoreBoard.getTotalScore());
        tvCorrect.setText("Correct : " + scoreBoard.getCorrectAnswer());
        tvWrong.setText("Wrong : " + scoreBoard.getWrongAnswer());
    }

    private void updateQue() {
        btnFirst.setText(Integer.toString(hwOperation.getFirstNumber()));
        btnSecond.setText(Integer.toString(hwOperation.getSecondNumber()));
    }

    private void triggerCorrect() {
        scoreBoard.increaseCorrectAnswer();
        scoreBoard.increaseTotalScore();
        if (scoreBoard.getTotalScore() >= 10) {
            viewResult.setVisibility(View.VISIBLE);
            viewGameOver.setVisibility(View.VISIBLE);
            viewButton.setVisibility(View.GONE);
        }
    }

    private void triggerWrong() {
        scoreBoard.decreaseTotalScore();
        scoreBoard.increaseWrongAnswer();
    }

    private void animate(Button button, boolean isCorrect) {
        ObjectAnimator animator;
        animator = isCorrect ? ObjectAnimator.ofInt(button,
                "backgroundColor",
                getResources().getColor(R.color.colorGreen),
                getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(R.color.colorPrimaryDark)) :
                ObjectAnimator.ofInt(button,
                        "backgroundColor",
                        getResources().getColor(R.color.colorRed),
                        getResources().getColor(R.color.colorPrimaryDark),
                        getResources().getColor(R.color.colorPrimaryDark));

        animator.setDuration(50);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(4);
        animator.start();
    }
}
