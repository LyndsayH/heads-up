package leo_santi.heads_up;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private Queue<String> words;
    private int playerOneScore, playerTwoScore;
    private boolean playerOneTurn = true;
    private String currWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        words = new LinkedList<String>(Arrays.asList("cat","dog","cow","Donald Trump",
                "google","hire","us","please","","","","",""));
        playerOneScore = 0;
        playerTwoScore = 0;
    }

    private void displayNextWord(){
        TextView wordLabel = (TextView) findViewById(R.id.word);

        if (words.peek() != null) {
            currWord = words.poll();
            wordLabel.setText(currWord);
        } else
            wordLabel.setText("Out of Words");
    }

    private void playerCorrect(){
        TextView scoreLabel = (TextView) findViewById(R.id.score);
        Integer score = playerOneTurn ? playerOneScore : playerTwoScore;

        score++;
        scoreLabel.setText(score);
        displayNextWord();

        if (playerOneTurn)
            playerOneScore = score;
        else
            playerTwoScore = score;
    }

    private void playerSkip() {
        words.offer(currWord);
        TextView displayWord = (TextView) findViewById(R.id.word);
        currWord = words.poll();
        displayWord.setText(currWord);
    }
}
