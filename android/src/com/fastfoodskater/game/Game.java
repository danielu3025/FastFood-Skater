package com.fastfoodskater.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.google.example.games.basegameutils.GameHelper;

import static com.fastfoodskater.game.Game.gameHendler;


public class Game extends ApplicationAdapter {
    static GameHelper gh;
    static GameHendler gameHendler ;
    public static PlayServices playServices;
    Thread gameThread   = new Thread(new GameRunnable());
    Thread musicThread = new Thread( new SoundRunnable());

    public Game(PlayServices ps,GameHelper gameHelper) {
        gh = gameHelper;
        playServices =ps;
    }

    @Override
	public void create() {
        gameHendler = new GameHendler(gh);
        gameHendler.fatBoy = new FatBoy();
        gameHendler.food = new Food();
		gameHendler.initFont();
        gameThread.start();
        musicThread.start();
    }
	@Override
	 public void render() {
        gameHendler.batch.begin();
        gameHendler.drowRuner();
        gameHendler.batch.end();
        gameThread.run();
        musicThread.run();
    }
	@Override
	public void dispose() {
		gameHendler.batch.dispose();
		gameHendler.font.dispose();

	}
}

class  GameRunnable implements Runnable{

    @Override
    public void run() {
        if (gameHendler.gameState == 1) {
            gameHendler.backgroundCalcPostion();
            gameHendler.gameRuner();
            gameHendler.fatBoy.rectanglePicker();
            gameHendler.colison();
            gameHendler.speedGame();
            gameHendler.fatBoy.spriteChanger();
            gameHendler.playJumpSound();
            gameHendler.dodheJumpSound();
        }
    }
}
class  SoundRunnable implements  Runnable{

    @Override
    public void run() {
        gameHendler.bgMusicPlayer();
    }
}
