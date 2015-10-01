package hu.jonat.darts_scoreboard;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends FragmentActivity {

    private static int PORTRAIT = 1;
    private static int LANDSCAPE = 0;
    private ViewGroup fragmentContainer;
    private ScoreFragment scoreFragment;
    private StatisticFragment statisticFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        if(findViewById(R.id.layoutFragment) != null){
            scoreFragment = new ScoreFragment();
            statisticFragment = new StatisticFragment();
            scoreFragment.setArguments(getIntent().getExtras());
            ft.add(R.id.layoutFragment,scoreFragment).commit();
        }
    }

    public void onClick(View v) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        if (v.getId() == R.id.Score) {
            scoreFragment = (ScoreFragment) fm.findFragmentByTag(ScoreFragment.TAG);
            if (scoreFragment == null){
                scoreFragment = new ScoreFragment();
            }
            ft.replace(R.id.layoutFragment, scoreFragment, ScoreFragment.TAG);
            ft.addToBackStack(null);
            ft.commit();
        } else if (v.getId() == R.id.Statistic) {
            statisticFragment = (StatisticFragment) fm.findFragmentByTag(StatisticFragment.TAG);
            if (statisticFragment == null) {
                statisticFragment = new StatisticFragment();
            }
            ft.replace(R.id.layoutFragment, statisticFragment, StatisticFragment.TAG);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
