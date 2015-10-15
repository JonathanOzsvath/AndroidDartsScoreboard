package hu.jonat.darts_scoreboard;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    String actTag;
    ScoreFragment scoreFragment;
    StatisticFragment statisticFragment;

    Player player1;
    Player player2;

    int countOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = new Player("player1");
        player2 = new Player("player2");
        countOk = 0;

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        if (savedInstanceState != null) {
            actTag = savedInstanceState.getString("TAG");
            countOk = savedInstanceState.getInt("countOk");
            player1 = savedInstanceState.getParcelable("playerOne");
            player2 = savedInstanceState.getParcelable("playerTwo");
        }
        if (actTag == null || actTag == ScoreFragment.TAG) {
            scoreFragment = new ScoreFragment();
            ft.replace(R.id.layoutFragment, scoreFragment, ScoreFragment.TAG);
            actTag = ScoreFragment.TAG;
        } else {
            statisticFragment = new StatisticFragment();
            ft.replace(R.id.layoutFragment, statisticFragment, StatisticFragment.TAG);
            actTag = StatisticFragment.TAG;
        }
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        refresh();
    }

    public void onClick(View v) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        if (v.getId() == R.id.Statistic) {
            ft.setCustomAnimations(android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right);

            statisticFragment = (StatisticFragment) fm.findFragmentByTag(StatisticFragment.TAG);
            if (statisticFragment == null) {
                statisticFragment = new StatisticFragment();
            }
            ft.replace(R.id.layoutFragment, statisticFragment, StatisticFragment.TAG);
            //ft.addToBackStack(StatisticFragment.TAG);
            ft.commit();
            actTag = StatisticFragment.TAG;
        } else if (v.getId() == R.id.Score) {
            ft.setCustomAnimations(android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right);

            scoreFragment = (ScoreFragment) fm.findFragmentByTag(ScoreFragment.TAG);
            if (scoreFragment == null) {
                scoreFragment = new ScoreFragment();
            }
            ft.replace(R.id.layoutFragment, scoreFragment, ScoreFragment.TAG);
            //ft.addToBackStack(ScoreFragment.TAG);
            ft.commit();
            actTag = ScoreFragment.TAG;
        }
    }

    public void onClickOk(View v) {

        EditText editText = (EditText) findViewById(R.id.etScore);
        TextView playerOneName = (TextView) findViewById(R.id.playerOneName);
        TextView playerTwoName = (TextView) findViewById(R.id.playerTwoeName);
        TextView playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        TextView playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);

        if (v.getId() == R.id.btnOk) {
            if (countOk == 0 && editText.getText() != null) {
                player1.setName(editText.getText().toString());
                playerOneName.setText(editText.getText());
                editText.getText().clear();
                editText.setHint("Adja meg a 2. játékos nevét");
            }
            if (countOk == 1 && editText.getText() != null) {
                player2.setName(editText.getText().toString());
                playerTwoName.setText(editText.getText());
                editText.getText().clear();
                editText.setHint("Az első játékos dob!");
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
            if ((countOk % 2) == 0 && countOk != 0 && editText.getText() != null) {
                int score = player1.getScore() - Integer.parseInt(editText.getText().toString());
                player1.setScore(score);
                editText.getText().clear();
                editText.setHint("A második játékos dob!");
                playerOneScore.setText(String.valueOf(player1.getScore()));
            }
            if ((countOk % 2) == 1 && countOk != 1 && editText.getText() != null) {
                int score = player2.getScore() - Integer.parseInt(editText.getText().toString());
                player2.setScore(score);
                editText.getText().clear();
                editText.setHint("Az elso játékos dob!");
                playerTwoScore.setText(String.valueOf(player2.getScore()));
            }
        }
        countOk++;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TAG", actTag);
        outState.putInt("countOk", countOk);
        outState.putParcelable("playerOne", player1);
        outState.putParcelable("playerTwo", player2);
    }

    public void refresh(){
        EditText editText = (EditText) findViewById(R.id.etScore);
        TextView playerOneName = (TextView) findViewById(R.id.playerOneName);
        TextView playerTwoName = (TextView) findViewById(R.id.playerTwoeName);
        TextView playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        TextView playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);

        playerOneName.setText(player1.getName().toString());
        playerTwoName.setText(player2.getName().toString());
        playerOneScore.setText(String.valueOf(player1.getScore()));
        playerTwoScore.setText(String.valueOf(player2.getScore()));
        if (countOk == 1){
            editText.setHint("Adja meg a 2. játékos nevét");
        }else if(countOk %2 == 0 && countOk != 0){
            editText.setHint("Az elso játékos dob!");
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }else if (countOk %2 == 1 && countOk != 1){
            editText.setHint("A második játékos dob!");
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }
}
