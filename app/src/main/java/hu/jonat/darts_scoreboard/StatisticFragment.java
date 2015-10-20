package hu.jonat.darts_scoreboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jonat on 2015. 09. 30..
 */
public class StatisticFragment extends Fragment {

    public static final String TAG = "StatisticFragment";

    Player player1, player2;
    int countOk;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.statistic_fragment, null);

        //Toast.makeText(getActivity(), player1.getName().toString(), Toast.LENGTH_LONG).show();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    // Adtatok megkapása a ScoreFragment-ből
    public void getPlayers(Player playerOne, Player playerTwo, int coiuntOk){
        player1 = playerOne;
        player2 = playerTwo;
        this.countOk = coiuntOk;
    }
}
