package hu.jonat.darts_scoreboard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jonat on 2015. 10. 14..
 */
public class Player implements Parcelable {

    private String name;
    private int score;
    private int sets;
    private int legs;

    public Player(String name) {
        this.name = name;
        score = 501;
        sets = 0;
        legs = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(score);
        dest.writeInt(sets);
        dest.writeInt(legs);
    }

    private Player(Parcel in){
        this.name = in.readString();
        this.score = in.readInt();
        this.sets = in.readInt();
        this.legs = in.readInt();
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>(){
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        public Player[] newArray(int size){
            return new Player[size];
        }
    };
}
