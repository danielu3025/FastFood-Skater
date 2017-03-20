package com.fastfoodskater.game;
import android.app.Activity;
import android.os.Bundle;
import com.badlogic.gdx.Gdx;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import java.util.ArrayList;

public class PlayServiceHendler extends Activity implements PlayServices {
    private GameHelper gameHelper;
    private final static int requestCode = 1;
    ArrayList<String> achievements = new ArrayList<String>();
    Bundle bundle  =new Bundle();
    public PlayServiceHendler(GameHelper gh) {
        gameHelper = gh;
        achievements.add("CgkIgui43PQeEAIQAg");
        achievements.add("CgkIgui43PQeEAIQAw");
        achievements.add("CgkIgui43PQeEAIQBA");
        achievements.add("CgkIgui43PQeEAIQBQ");
        achievements.add("CgkIgui43PQeEAIQBg");
        achievements.add("CgkIgui43PQeEAIQBw");
        achievements.add("CgkIgui43PQeEAIQCA");
        achievements.add("CgkIgui43PQeEAIQCQ");
        achievements.add("CgkIgui43PQeEAIQCg");
        achievements.add("CgkIgui43PQeEAIQCw");
        achievements.add("CgkIgui43PQeEAIQDQ");
    }

    @Override
    public void signIn()
    {
        try
        {
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        }
        catch (Exception e)
        {
            Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void signOut()
    {
        try
        {
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    gameHelper.signOut();
                }
            });
        }
        catch (Exception e)
        {
            Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }
    @Override
    public void rateGame() {}

    @Override
    public void unlockAchievement() {}
    public void achievementUnlociker(int id,int kind) {
        try{
            if (gameHelper !=null){
                if (gameHelper.isSignedIn()){
                    //unlock
                    if (kind == 1){
                        Games.Achievements.unlock(gameHelper.getApiClient(),achievements.get(id));
                    }
                    //incremnt
                    else{
                        Games.Achievements.increment(gameHelper.getApiClient(),achievements.get(id), 1);
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void submitToliderBoard(int score) {
        try {
            if (gameHelper != null){
                if (gameHelper.isSignedIn()){
                    Games.Leaderboards.submitScore(gameHelper.getApiClient(),"CgkIgui43PQeEAIQDA", score);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void submitScore(int highScore) {}

    @Override
    public void showAchievement() {}

    @Override
    public void showScore() {}

    @Override
    public boolean isSignedIn() {
        return gameHelper.isSignedIn();
    }

    @Override
    public void connectToService() {

    }

    @Override
    public void showtutorial() {

    }

}
