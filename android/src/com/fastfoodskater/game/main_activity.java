package com.fastfoodskater.game;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.samples.quickstart.analytics.AnalyticsApplication;

import io.fabric.sdk.android.Fabric;

public class main_activity extends AndroidApplication implements PlayServices {
	private  GameHelper gameHelper;
	private final static int requestCode = 1;
	GameHelper.GameHelperListener gameHelperListener;
	private Tracker mTracker;
	Game game ;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fabric.with(this, new Crashlytics());
		//google analytics connection
		try {
			AnalyticsApplication application = (AnalyticsApplication) getApplication();
			mTracker = application.getDefaultTracker();
			mTracker.send(new HitBuilders.EventBuilder()
					.setCategory("Action")
					.setAction("Share")
					.build());
		}
		catch (Exception e){
			e.printStackTrace();
		}
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		GLProfiler.enable();
		//game services login
		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.enableDebugLog(false);

		gameHelperListener = new GameHelper.GameHelperListener()
		{
			@Override
			public void onSignInFailed(){ }

			@Override
			public void onSignInSucceeded(){ }
		};
		gameHelper.setup(gameHelperListener);
		//RUN THE GAME
		initialize(game = new Game(this,gameHelper), config);

	}
	@Override
	protected void onStart()
	{
		super.onStart();
		//gameHelper.onStart(this);
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		gameHelper.onStop();
		game.gameHendler.gameState =2;

	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	//overides methods
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
	public void rateGame()
	{

		String str = "https://play.google.com/store/apps/details?id=com.fastfoodskater.game";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

	@Override
	public void unlockAchievement(){


	}

	@Override
	public void submitScore(int highScore)
	{


	}

	@Override
	public void showAchievement()
	{
		if (isSignedIn() == true)
		{
			startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()),2);
		}
		else
		{
			signIn();
		}
	}

	@Override
	public void showScore()
	{
		gameHelper.getApiClient().connect();
		if (isSignedIn() == true)
		{
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), "CgkIgui43PQeEAIQDA"), requestCode);
		}
		else
		{
			signIn();
		}
	}

	@Override
	public boolean isSignedIn()
	{

		return gameHelper.isSignedIn();
	}

	@Override
	public void connectToService() {
		gameHelper.onStart(this);
	}

	@Override
	public void showtutorial() {
		String str = "https://www.youtube.com/watch?v=VFHCF_nxNpk";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

}
