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

    public ScoreFragment() {
        setArguments(new Bundle());
    }

    public static final String TAG = "ScoreFragment";

    public Player player1;
    Player player2;
    String actTag;
    int countOk;
    //EditText editText;
    TextView playerOneName, playerTwoName, playerOneScore, playerTwoScore;
    Text mCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (Text) activity;
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

        countOk = 0;

        if (player1 == null) {
            player1 = new Player("player1");
        }

        //editText = (EditText) v.findViewById(R.id.etScore);
        playerOneName = (TextView) v.findViewById(R.id.playerOneName);
        playerTwoName = (TextView) v.findViewById(R.id.playerTwoeName);
        playerOneScore = (TextView) v.findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) v.findViewById(R.id.playerTwoScore);

        //Toast.makeText(getActivity(),s, Toast.LENGTH_LONG).show();

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        String s = "nyer";
        mCallback.sendText(s);
    }

    public void onClickOk(View v) {

        EditText editText = (EditText) getView().findViewById(R.id.etScore);

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

    public interface Text {
        public void sendText(String text);
    }
}
