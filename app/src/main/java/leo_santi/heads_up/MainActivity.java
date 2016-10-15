package leo_santi.heads_up;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.os.CountDownTimer;
import android.util.Log;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private Queue<String> words;
    private int playerOneScore, playerTwoScore;
    private boolean isPlayerOneTurn = true, isActiveTurn = false, isStop = false;
    private String currWord;
    private final String PLAYER_1 = "Player 1";
    private final String PLAYER_2 = "Player 2";
    private CountDownTimer turnTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTurnTimer(5);
        setDefaultGame();
    }

    private void setTurnTimer(int s) { // seconds
        turnTimer =  new CountDownTimer((s * 1000), 1000) {
                TextView timerLabel = (TextView) findViewById(R.id.timer);
                Button startButton = (Button) findViewById(R.id.start);

                public void onTick(long millisUntilFinished) {
                    timerLabel.setText("" + millisUntilFinished / 1000 + " seconds");
                    if (!isStop){
                        startButton.setText("STOP");
                        isStop = true;
                    }
                }

                public void onFinish() {
                    timerLabel.setText("OUT OF TIME!");
                    isActiveTurn = false;
                    isStop = false;
                    startButton.setText("START");
                    changeTurn();
                }
            };
    }

    private void setDefaultGame() {
        words = new LinkedList<>(Arrays.asList("cat", "dog", "cow", "jabberwocky", "chicken", "google", "please", "hire", "us"));
        words = new LinkedList<String>();
        currWord = words.peek();
        playerOneScore = 0;
        playerTwoScore = 0;
        resetTimer();
    }

    public void startTurn(View v) {
        Button startButton = (Button) findViewById(R.id.start);

        if (!isActiveTurn) {
            if (isStop) {
                startButton.setText("RESET");
                isStop = false;
                isActiveTurn = true;
            } else {
                isActiveTurn = true;
                displayNewWord();
                turnTimer.start();
            }
        } else {
            isActiveTurn = false;
            startButton.setText("STOP");
            isStop = true;
        }
    }

    private void resetTimer() {
        turnTimer.cancel();
    }


    private void changeTurn() {
        isPlayerOneTurn = isPlayerOneTurn ? false : true;
        Integer score = isPlayerOneTurn ? playerOneScore : playerTwoScore;
        String player = isPlayerOneTurn ? PLAYER_1 : PLAYER_2;

        TextView scoreLabel = (TextView) findViewById(R.id.score);
        TextView playerLabel = (TextView) findViewById(R.id.playerName);
        scoreLabel.setText("Score: " + score.toString());
        playerLabel.setText(player);
    }


    private void displayWord(String word) {
        TextView wordLabel = (TextView) findViewById(R.id.word);
        wordLabel.setText(word);

    }
    private void displayNewWord() {
        if ((currWord != null) && (words.peek() != null)) {
            currWord = words.poll();
            displayWord(currWord);
        } else {
            displayWord("Out of words!");
            isActiveTurn = false;
            // turnTimer.cancel();
        }
    }

    public void playerCorrect(View v) {
        if (isActiveTurn) {
            TextView scoreLabel = (TextView) findViewById(R.id.score);
            Integer score = isPlayerOneTurn ? playerOneScore : playerTwoScore;

            score++;
            scoreLabel.setText("Score: " + score.toString());

            if (isPlayerOneTurn)
                playerOneScore = score;
            else
                playerTwoScore = score;

            displayNewWord();
        }
    }

/**
 * Skip a word i.e. don't use it now, but save it for later.
 * Put word at the front of the queue at the end.
 *
 */
    private void skipWord() {
        words.offer(currWord);
        currWord = words.poll();
    }

    public void playerSkip(View v) {
        if (isActiveTurn) {
            skipWord();
            displayWord(currWord);
        }
    }
}
