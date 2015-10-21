package hu.jonat.darts_scoreboard;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import hu.jonat.Data.ListViewItem;
import hu.jonat.hu.jonat.Adapter.ListViewAdapter;

/**
 * Created by jonat on 2015. 09. 30..
 */
public class StatisticFragment extends ListFragment {

    public static final String TAG = "StatisticFragment";

    private List<ListViewItem> mItems;

    public Player player1, player2;
    String actTag;
    int countOk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.score_fragment, null);
        return v;
    }*/

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            actTag = savedInstanceState.getString("TAG");
            player1 = savedInstanceState.getParcelable("playerOne");
            player2 = savedInstanceState.getParcelable("playerTwo");
            countOk = savedInstanceState.getInt("countOk");
        }

        mItems = new ArrayList<ListViewItem>();

        Resources resources = getResources();

        mItems.add(new ListViewItem("Név", player1.getName(), player2.getName()));
        mItems.add(new ListViewItem("Sets",String.valueOf(player1.getSets()),String.valueOf(player2.getSets())));
        mItems.add(new ListViewItem("Legs",String.valueOf(player1.getLegs()),String.valueOf(player2.getLegs())));

        setListAdapter(new ListViewAdapter(getActivity(), mItems));


        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    // Adtatok megkapása a ScoreFragment-ből és MainActivity-ből
    public void getPlayers(Player playerOne, Player playerTwo, int coiuntOk){
        player1 = playerOne;
        player2 = playerTwo;
        this.countOk = coiuntOk;
    }

    public interface getPlayersFromActivity{
        public void getPlayerFromActivity(Player playerOne, Player playerTwo, int countOk);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TAG", TAG);
        outState.putInt("countOk", countOk);
        outState.putParcelable("playerOne", player1);
        outState.putParcelable("playerTwo", player2);
    }
}
