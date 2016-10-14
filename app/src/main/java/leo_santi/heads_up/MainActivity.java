package leo_santi.heads_up;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private Queue<String> words;
    private int playerOneScore, playerTwoScore;
    private boolean playerOneTurn = true;
    private String currWord;
    private final String PLAYER1 = "Player 1";
    private final String PLAYER2 = "Player 2";
    private CountDownTimer turnTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        words = new LinkedList<String>(Arrays.asList("cat","dog","cow","Donald Trump",
                "google","hire","us","please","","","","",""));
        playerOneScore = 0;
        playerTwoScore = 0;
        turnTimer =  new CountDownTimer(60000, 1000) {
            TextView timeLabel = (TextView) findViewById(R.id.timer);
            public void onTick(long millisUntilFinished) {
              timeLabel.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                changeTurn();
            }
        };
    }

    private  void startTurn(View view){
        turnTimer.start();
    }

    private void displayNextWord(){
        TextView wordLabel = (TextView) findViewById(R.id.word);

        if (words.peek() != null) {
            currWord = words.poll();
            wordLabel.setText(currWord);
        } else
            wordLabel.setText("Out of Words");
    }

    public void playerCorrect(View v){
        Log.d("playerCorrect", "Very start");
        TextView scoreLabel = (TextView) findViewById(R.id.score);
        Integer score = playerOneTurn ? playerOneScore : playerTwoScore;
        Log.d("playerCorrect", "Score set");

        score++;
        Log.d("playerCorrect", "Score++");
        scoreLabel.setText(score.toString());
        Log.d("playerCorrect", "Display score");

        if (playerOneTurn)
            playerOneScore = score;
        else
            playerTwoScore = score;

        displayNextWord();

    }

    public void playerSkip(View v) {
        words.offer(currWord);
        TextView displayWord = (TextView) findViewById(R.id.word);
        currWord = words.poll();
        displayWord.setText(currWord);
    }

    private  void changeTurn(){
        playerOneTurn = playerOneTurn ? false : true;
        Integer score = playerOneTurn ? playerOneScore : playerTwoScore;
        String player = playerOneTurn ? PLAYER1 : PLAYER2;

        TextView scoreLabel = (TextView) findViewById(R.id.score);
        TextView playerLabel = (TextView) findViewById(R.id.score);
        scoreLabel.setText(score.toString());
        playerLabel.setText(player);
    }

    private void resetTimer(){
        turnTimer =  new CountDownTimer(30000, 1000) {
            TextView timeLabel = (TextView) findViewById(R.id.timer);
            public void onTick(long millisUntilFinished) {
                timeLabel.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                changeTurn();
            }
        };
    }
}
