package leo_santi.heads_up;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private Queue<String> words;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        words = new LinkedList<String>(Arrays.asList("cat","dog","cow","Donald Trump","","","","","","","","",""));
    }

    private void playerCorrect(){

    }

    private void playerWrong(){

    }
}
