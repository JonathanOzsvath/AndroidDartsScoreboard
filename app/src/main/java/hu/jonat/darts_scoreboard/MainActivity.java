package hu.jonat.darts_scoreboard;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class MainActivity extends FragmentActivity implements ScoreFragment.Text{

    String actTag;
    ScoreFragment scoreFragment;
    StatisticFragment statisticFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        if (savedInstanceState != null) {
            actTag = savedInstanceState.getString("TAG");
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

    public void onClick(View v) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        if (v.getId() == R.id.Statistic) {
            ft.setCustomAnimations(android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right);

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

            if (scoreFragment == null) {
                scoreFragment = new ScoreFragment();
            }
            ft.replace(R.id.layoutFragment, scoreFragment, ScoreFragment.TAG);
            //ft.addToBackStack(ScoreF
            ft.commit();
            actTag = ScoreFragment.TAG;
        }
    }

    public void onClickOk(View v){
        scoreFragment.onClickOk(v);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TAG", actTag);
    }

    @Override
    public void sendText(String text) {
        if (statisticFragment == null) {
            statisticFragment = new StatisticFragment();
        }

        statisticFragment.updateText(text);
    }
}
