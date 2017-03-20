package com.fastfoodskater.game;


public interface PlayServices {
    public void signIn();
    public void signOut();
    public void rateGame();
    public void unlockAchievement();
    public void submitScore(int highScore);
    public void showAchievement();
    public void showScore();
    public boolean isSignedIn();
    public void connectToService();
    public void showtutorial();
}

