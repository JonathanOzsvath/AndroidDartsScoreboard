package hu.jonat.darts_scoreboard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by jonat on 2015. 09. 30..
 */
public class ScoreFragment extends Fragment {

    //????
    public ScoreFragment() {
        setArguments(new Bundle());
    }

    public static final String TAG = "ScoreFragment";

    public Player player1;
    public Player player2;
    String actTag, s;
    int countOk;
    EditText editText;
    TextView playerOneName, playerTwoName, playerOneScore, playerTwoScore;
    SendPlayers mCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (SendPlayers) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement TextClicked");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.score_fragment, null);

        if (savedInstanceState != null) {
            actTag = savedInstanceState.getString("TAG");
            player1 = savedInstanceState.getParcelable("playerOne");
            player2 = savedInstanceState.getParcelable("playerTwo");
            countOk = savedInstanceState.getInt("countOk");
        }

        if (countOk == 0) {
            player1 = new Player("player1");
            player2 = new Player("player2");
        }

        editText = (EditText) v.findViewById(R.id.etScore);
        playerOneName = (TextView) v.findViewById(R.id.playerOneName);
        playerTwoName = (TextView) v.findViewById(R.id.playerTwoName);
        playerOneScore = (TextView) v.findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) v.findViewById(R.id.playerTwoScore);

        //Toast.makeText(getActivity(), player1.getName().toString(), Toast.LENGTH_LONG).show();

        update();

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        mCallback.sendPlayer(player1, player2, countOk);
    }

    public void onClickOk(View v) {

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

    protected void update(){
        playerOneName.setText(player1.getName().toString());
        playerTwoName.setText(player2.getName().toString());
        playerOneScore.setText(String.valueOf(player1.getScore()));
        playerTwoScore.setText(String.valueOf(player2.getScore()));
        if (countOk == 1){
            editText.setHint("Az első játékos dob!");
        }
        if ((countOk % 2) == 0 && countOk != 0 ){
            editText.setHint("Az elso játékos dob!");
        }
        if ((countOk % 2) == 1 && countOk != 1){
            editText.setHint("A második játékos dob!");
        }
        if (countOk > 1){
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TAG", TAG);
        outState.putInt("countOk", countOk);
        outState.putParcelable("playerOne", player1);
        outState.putParcelable("playerTwo", player2);
    }

    // Adatok küldése a StatisticFragmentnek
    public interface SendPlayers {
        public void sendPlayer(Player playerOne, Player playerTwo, int countOk);
    }

    // Adtatok megkapása a MainActivity-ből
    public interface ActivityToFragment {
        public void sendToFragment(Player playerOne, Player playerTwo, int countOk);
    }

    // Adtatok megkapása a MainActivity-ből
    public void getMessage(Player playerOne, Player playerTwo, int countOk) {
        player1 = playerOne;
        player2 = playerTwo;
        this.countOk = countOk;
    }
}