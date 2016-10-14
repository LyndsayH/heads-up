package leo_santi.heads_up;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private Queue<String> words;
    private int playerOneScore;
    private int playerTwoScore;
    private boolean playerOneTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        words = new LinkedList<String>(Arrays.asList("cat","dog","cow","Donald Trump",
                "","","","","","","","",""));
        playerOneScore = 0;
        playerTwoScore = 0;
    }

    private void playerCorrect(){
        TextView label = (TextView) findViewById(R.id.correctScore);
        Integer score = playerOneTurn ? playerOneScore : playerTwoScore;

        score++;
        label.setText(score);


        if (playerOneTurn)
            playerOneScore = score;
        else
            playerTwoScore = score;
    }

    private void playerSkip(){

    }
}
