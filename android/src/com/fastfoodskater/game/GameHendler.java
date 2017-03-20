package com.fastfoodskater.game;
import android.os.Bundle;
import android.os.Looper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.google.example.games.basegameutils.GameHelper;

import java.util.Random;

import static com.fastfoodskater.game.Game.gameHendler;
import static com.fastfoodskater.game.Game.playServices;


public class GameHendler {
    Bundle bundle = new Bundle();
    PlayServiceHendler playServiceHendler;
    GameHelper gameHelper;
    Preferences prefs;
    int hightScore =0;
    int personalRecordBreakCounter = 0;
    int firstGme = 0;
    FatBoy fatBoy;
    Food food;
    SpriteBatch batch;
    FreeTypeFontGenerator.FreeTypeFontParameter params, params2,params3;
    FreeTypeFontGenerator generator;
    Texture sky, road, skyLineClose, skyLineFar, trees, bar, head,headF,headS, stop, stop2, play, st, resume, startbg, gameover,gameover2,gameover3, settingsPage, timeOut,timeOut2,timeOut3,
            backIcon,sounOn,soundOff,fxOn,fxOff,about,best,aboutBg,guide,skyLvl1,skyLineFar1,skyLineClose1,trees1,road1,
            skyLvl2,skyLvl3,skyLineFar2,skyLineFar3,skyLineClose2,skyLineClose3,trees3,road3;
    int gameState = 0;
    Random randdomGenerator;
    boolean isPaused = false;
    public int level = 0;
    int score = 0;
    String lvelText = "level: 1";
    BitmapFont font;
    BitmapFont font2;
    BitmapFont font3;
    float screenHight = Gdx.graphics.getHeight();
    float screenWidth = Gdx.graphics.getWidth();
    boolean boom = false;
    float skyX = 0, roadX = 0, treesX = 0, closeX = 0, farX = 0;
    float skyV = 2, roadV = 4, treesV = 3, closeV = 2, farV = 1f;
    float barLen, headPos;
    Rectangle fatboyRect;
    float timeStete = 0f;
    int scoreHit = 5;
    int record = 0;
    String musicIsOn = "1";
    String fxIsOn = "1'";
    MusicGame musicGame;
    boolean isFirst = true;
    boolean gameOverAniem = false;
    boolean connected;
    float halfScreenHight;

    BottonImg sounBt,fxBt,aboutBt,startPlayBt,settingsButton,backButton,pauseButton,resumeButton,
              retryBotton,quitButton,connect,achivment,liderboard,rate,tutorial;


    public GameHendler(final GameHelper gh) {
        gameHelper = gh;
        Thread  t = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                playServiceHendler = new PlayServiceHendler(gameHelper);
                batch = new SpriteBatch();
                sky = new Texture("img/sky.png");
                skyLvl1 = sky;
                skyLvl2 = new Texture("img/sky2.png");
                skyLvl3 = new Texture("img/sky3.png");
                skyLineClose = new Texture("img/skyLineCloser.png");
                skyLineClose1=skyLineClose;
                skyLineClose2 = new Texture("img/skyLineCloser2.png");
                skyLineClose3 = new Texture("img/skyLineCloser3.png");
                skyLineFar = new Texture("img/skyLineFar.png");
                skyLineFar1 = skyLineFar;
                skyLineFar2 = new Texture("img/skyLineFar2.png");
                skyLineFar3 = new Texture("img/skyLineFar3.png");
                road = new Texture("img/road.png");
                road1 = road;
                road3 = new Texture("img/road3.png");
                trees = new Texture("img/trees.png");
                trees1 =trees;
                trees3 = new Texture("img/trees3.png");
                bar = new Texture("img/bar.png");
                head = new Texture("img/head.png");
                headF = new Texture("img/head.png");
                headS = new Texture("img/head2.png");
                head= headF;
                stop = new Texture("img/stop.png");
                stop2 = new Texture("img/stopbt.png");
                play = new Texture("img/play.png");
                resume = new Texture("img/resume.png");
                startbg = new Texture("img/startBg.png");
                gameover = new Texture("img/gameover.png");
                gameover2 = new Texture("img/gameover2.png");
                gameover3 = new Texture("img/gameover3.png");
                st = new Texture("img/setting.png");
                settingsPage = new Texture("img/settingsPage.png");
                timeOut = new Texture("img/timeOut.png");
                timeOut2 = new Texture("img/timeOut2.png");
                timeOut3 = new Texture("img/timeOut3.png");
                backIcon = new Texture("img/backIcon.png");
                sounOn  = new Texture("img/soundOn.png");
                soundOff  = new Texture("img/soundOff.png");
                fxOn = new Texture("img/fxOn.png");
                fxOff = new Texture("img/fxOff.png");
                about = new Texture("img/fxOff.png");
                best = new Texture("img/best.png");
                aboutBg = new Texture("img/aboutBG.png");
                guide = new Texture("img/guide.png");

                sounBt = new BottonImg("img/soundOn.png",screenWidth/4,screenHight/3f,screenWidth/5,screenHight/6);
                fxBt = new BottonImg("img/fxOn.png",screenWidth/1.7f,screenHight/3f,screenWidth/5,screenHight/6);
                aboutBt = new BottonImg("img/about.png",screenWidth/1.2f,screenHight/1.2f,screenWidth/6,screenHight/7);
                startPlayBt = new BottonImg("img/playbutton.png",screenWidth/2.4f,screenHight/3.5f,screenWidth/4,screenHight/5);
                settingsButton = new BottonImg("img/setting.png",0,screenHight/1.2f,screenHight/7,screenHight/7);
                backButton= new BottonImg("img/backIcon.png",0,screenHight/1.2f,screenHight/7,screenHight/7);
                pauseButton= new BottonImg("img/stop.png",screenWidth/1.2f,screenHight/1.3f,screenWidth/8.3f,screenHight/6.5f);

                resumeButton = new BottonImg("img/resume.png",screenWidth/1.65f,screenHight/3f,screenWidth/5,screenHight/6);
                retryBotton = new BottonImg("img/retry.png",screenWidth/2.5f,screenHight/3f,screenWidth/5,screenHight/6);
                quitButton = new BottonImg("img/quit.png",screenWidth/5.2f,screenHight/3f,screenWidth/5,screenHight/6);
                connect = new BottonImg("img/connect.png",screenWidth/1.26f,screenHight/1.2f,screenWidth/5.0f,screenHight/6.7f);
                achivment = new BottonImg("img/achviment.png",screenWidth/1.11f,screenHight/1.2f,screenHight/7,screenHight/7);
                liderboard = new BottonImg("img/liderboard.png",screenWidth/1.26f,screenHight/1.2f,screenHight/7,screenHight/7);
                liderboard = new BottonImg("img/liderboard.png",screenWidth/1.26f,screenHight/1.2f,screenHight/7,screenHight/7);
                rate = new BottonImg("img/rate.png",screenWidth/9.5f,screenHight/1.2f,screenHight/7,screenHight/7);
                tutorial = new BottonImg("img/tutorial.png",screenWidth/4.7f,screenHight/1.2f,screenHight/7,screenHight/7);




                randdomGenerator = new Random();
                fatBoy = new FatBoy();
                food = new Food();
                fatboyRect = new Rectangle(70 + fatBoy.width / 2, fatBoy.yPosition, fatBoy.width, fatBoy.hieght * 2.5f);

                barLen = screenWidth / 2;
                headPos = screenWidth / 4f + screenWidth / 5;
                playServiceHendler = new PlayServiceHendler( gh);
                musicGame = new MusicGame();
                // check if thre is a seved preferance allredy
                try {
                    prefs = Gdx.app.getPreferences("gamePreferences");
                    musicIsOn =  prefs.getString("music");
                    fxIsOn =  prefs.getString("fx");
                    personalRecordBreakCounter = prefs.getInteger("personalRecordBreakCounter");
                    firstGme = prefs.getInteger("firstGme");
                    if (musicIsOn == ""){
                        musicIsOn = "1";
                    }
                    if (fxIsOn == ""){
                        fxIsOn = "1";
                    }

                }catch (Exception e){
                    System.out.println("no saved prefernces");
                    musicIsOn = "1";
                    fxIsOn = "1";
                }
                System.out.println("sund:"+ musicIsOn);
                System.out.println("fx:"+ fxIsOn);

                halfScreenHight = screenHight / 2;

            }
        });
        t.run();
    }


    //sets all game parameters for a new game
    public void initGame() {
        level = 0;
        score = 0;
        boom = false;
        skyX = 0;
        roadX = 0;
        treesX = 0;
        closeX = 0;
        farX = 0;
        skyV = 2;
        roadV = 4;
        treesV = 3;
        closeV = 2;
        farV = 1f;
        timeStete = 0f;
        scoreHit = 5;
        //init boy
        fatBoy.yPosition = 0;
        fatBoy.status = 0;
        fatBoy.stateTime = 0f;
        headPos = screenWidth / 4f + screenWidth / 5;
        gameHendler.fatBoy.dt = 0;
        sky = skyLvl1;
        trees = trees1;
        road = road1;
        skyLineClose = skyLineClose1;
        skyLineFar = skyLineFar1;
        lvelText = "level: 1";
        gameOverAniem = false;
        fatBoy.ambolancePosX = -800;
        fatBoy.ambulanceIn = false;

    }

    //return the batch of the game
    public SpriteBatch getBatch() {
        return batch;
    }

    //check the input method swipe down or up
    public void inputChaking() {
        if (Gdx.input.getDeltaY() < 0) {
            if (gameHendler.fatBoy.yPosition <= 0) {
                gameHendler.fatBoy.status = 1;
                gameHendler.fatBoy.jump = true;

            }
        } else if (Gdx.input.getDeltaY() > 0) {
            if (gameHendler.fatBoy.yPosition == 0) {
                gameHendler.fatBoy.status = 2;
            }
            if (gameHendler.fatBoy.jump) {
                gameHendler.fatBoy.gravity = gameHendler.fatBoy.superGarvity;
            }
        }
    }

    //check if there is a colison between the boy and the food
    public void colison() {
        if (food.rectangle.getX()<screenWidth/4.5f){
            if (Intersector.overlaps(gameHendler.fatBoy.rectangle, gameHendler.food.rectangle)) {
                if (food.sprite == food.food1 ){
                    playServiceHendler.achievementUnlociker(7,2);
                }
                if (food.sprite == food.food2 ){
                    playServiceHendler.achievementUnlociker(8,2);
                }
                if (!gameOverAniem){
                    biteSound();
                }
                food.xPos = screenWidth+screenWidth/2;
                food.yPostion = food.randY();
                food.spritPicker();
                if (headPos >= screenWidth / 2 + screenWidth / 5.8f) {
                    gameOverAniem = true;
                    //gameState = 3;
                    playServiceHendler.achievementUnlociker(0,1);
                } else {
                    headPos += 30;
                }
            }
        }

    }

    //sets the fonts
    public void initFont() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font.ttf"));
        params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params3 = new FreeTypeFontGenerator.FreeTypeFontParameter();


        params.size = 100;
        params2.size = 100;
        params3.size = 40;
        params.color = Color.DARK_GRAY;
        params2.color = Color.YELLOW;
        params3.color = Color.WHITE;
        font = generator.generateFont(params);
        font2 = generator.generateFont(params2);
        font3 = generator.generateFont(params3);
    }

    //count the score of the boy
    public void ScoreCounter() {
        timeStete += Gdx.graphics.getDeltaTime();
        if (timeStete >= 1f) {
            gameHendler.score++;
            headPos -= 5;
            if (headPos <= screenWidth / 4) {
                headPos = screenWidth / 4;
                if (head == headF){
                    fatBoy.boyChanger(2);
                    if (!musicGame.powerUp.isPlaying() && fxIsOn == "1"){
                        musicGame.powerUp.play();
                    }
                }
                head = headS;
                //Games.Achievements.unlock(gameHelper.getApiClient(), "CgkIgui43PQeEAIQCw");
                playServiceHendler.achievementUnlociker(9,1);
            }
            if (headPos>=screenWidth / 4f + screenWidth / 5){
                if (head == headS){
                    fatBoy.boyChanger(1);
                    if (!musicGame.powerUp.isPlaying() && fxIsOn == "1"){
                        musicGame.powerUp.play();
                    }
                }
                head = headF;
            }
            timeStete = 0f;
        }
    }

    //draw shade
    public void drowFont() {
        font.draw(gameHendler.batch, String.valueOf(gameHendler.score) , screenWidth / 23, gameHendler.screenHight - (gameHendler.screenHight * 1 / 10));
        font3.draw(gameHendler.batch, lvelText , screenWidth / 2.3f, gameHendler.screenHight - (gameHendler.screenHight * 1 / 25));    }

    //draw score shade
    public void drowFont2() {
        font2.draw(gameHendler.batch, String.valueOf(gameHendler.score), screenWidth / 25, gameHendler.screenHight - (gameHendler.screenHight * 1 / 12f));

    }

    //draw game's background
    public void backgroundDrow() {
        if (score >=60){
            sky = skyLvl2;
            skyLineClose = skyLineClose2;
            skyLineFar = skyLineFar2;
        }
        if (score >=150){
            sky = skyLvl3;
            skyLineClose = skyLineClose3;
            skyLineFar = skyLineFar3;
            trees = trees3;
            road = road3;
        }
            gameHendler.getBatch().draw(sky, skyX, 0, gameHendler.screenWidth, gameHendler.screenHight);
            gameHendler.getBatch().draw(sky, gameHendler.skyX + gameHendler.screenWidth, 0, gameHendler.screenWidth, gameHendler.screenHight);

            gameHendler.getBatch().draw(skyLineFar, farX, screenHight / 6, gameHendler.screenWidth, halfScreenHight);
            gameHendler.getBatch().draw(skyLineFar, farX + gameHendler.screenWidth, screenHight / 6, gameHendler.screenWidth, halfScreenHight);

            gameHendler.getBatch().draw(skyLineClose, closeX, screenHight / 6, gameHendler.screenWidth, halfScreenHight - screenHight / 5);
            gameHendler.getBatch().draw(skyLineClose, closeX + gameHendler.screenWidth, screenHight / 6, gameHendler.screenWidth, halfScreenHight - screenHight / 5);

            gameHendler.getBatch().draw(trees, roadX, screenHight / 6, gameHendler.screenWidth, gameHendler.screenHight / 10);
            gameHendler.getBatch().draw(trees, roadX + gameHendler.screenWidth, screenHight / 6, gameHendler.screenWidth, gameHendler.screenHight / 10);
            gameHendler.getBatch().draw(trees, roadX + gameHendler.screenWidth*2, screenHight / 6, gameHendler.screenWidth, gameHendler.screenHight / 10);


            gameHendler.getBatch().draw(road, roadX, 0, gameHendler.screenWidth, gameHendler.screenHight / 6);
            gameHendler.getBatch().draw(road, roadX + gameHendler.screenWidth, 0, gameHendler.screenWidth, gameHendler.screenHight / 6);
            gameHendler.getBatch().draw(road, roadX + gameHendler.screenWidth*2, 0, gameHendler.screenWidth, gameHendler.screenHight / 6);


        gameHendler.getBatch().draw(bar, screenWidth / 4f, gameHendler.screenHight - (gameHendler.screenHight * 1 / 5f), barLen, screenHight / 8);
            gameHendler.getBatch().draw(head, headPos, gameHendler.screenHight - (gameHendler.screenHight * 1 / 5f), screenWidth / 13, screenHight / 10);

    }

    //calc the position of all the objects on the background
    public void backgroundCalcPostion() {
            if (gameHendler.skyX == -(gameHendler.screenWidth)) {
                gameHendler.skyX = 0;
                roadX = 0;
            }
            if (roadX == -(screenWidth)) {
                roadX = 0;
                treesX = 0;
            }

//        if (treesX == -(screenWidth)) {
//            treesX = 0;
//        }
            if (closeX == -(screenWidth)) {
                closeX = 0;
            }
            if (farX == -(screenWidth)) {
                farX = 0;
            }
    }

    //game promoter
    public void gameRuner() {
        //gameing state
        if (gameState == 0) {
        }
        if (!gameOverAniem){
            if (gameHendler.gameState == 1) {
                gameHendler.skyX -= gameHendler.skyV;
                roadX -= roadV;
                //treesX -= treesV;
                closeX -= closeV;
                farX -= farV;
                gameHendler.ScoreCounter();
                //input checking
                gameHendler.inputChaking();

                //food logic
                gameHendler.food.foodMovment();
                gameHendler.food.calcReectangle();
//           gameHendler.food.drow();
                //jump
                gameHendler.fatBoy.jumpLogic();
            }
        }
        //state pause
//        else if (gameHendler.gameState == 2) {
//
//
//        }
        //game over
//        else if (gameHendler.gameState == 3) {
//        }
//        //game menu
//        else if (gameState == 4) {
//
//        }
    }



    //calc food's speed
    public void speedGame() {
        float l1 = food.speed, l2 = food.speed * 1.3f, l3 = food.speed * 1.7f, l4 = food.speed * 2.2f,l5 = food.speed*3f ,l6 =food.speed *4 , l7 = food.speed*4.5f;
        if (score <= 15) {
            food.velocity = l1;
        } else if (score <= 30) {
            food.velocity = l2;
            lvelText = "LEVEL: 2";
        } else if (score <= 60) {
            lvelText = "LEVEL: 3";
            food.velocity = l3;
        } else if (score <=100){
            lvelText = "LEVEL: 4";
            food.velocity = l4;
        } else if (score <=150){
            lvelText = "LEVEL: 5";
            food.velocity = l5;
        } else if (score <=200){
            lvelText = "LEVEL: 6";
            food.velocity = l6;
        }
        else {
            lvelText = "LEVEL: 7";
            food.velocity = l7;
        }

        if (score >120){
            // Games.Achievements.unlock(gameHelper.getApiClient(),"CgkIgui43PQeEAIQAw");
            playServiceHendler.achievementUnlociker(1,1);
        }
        if (score > 200){
            //Games.Achievements.unlock(gameHelper.getApiClient(),"CgkIgui43PQeEAIQBA");
            playServiceHendler.achievementUnlociker(2,1);

        }
        if (score > 200){
            playServiceHendler.achievementUnlociker(6,1);
        }

    }

    //manage the draw on the screen
    public void drowRuner() {
        //start page
        if (gameState == 0) {
            gameHendler.getBatch().draw(startbg, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            startPlayAction();
            batch.draw(startPlayBt.texture,startPlayBt.x,startPlayBt.y,startPlayBt.widt,startPlayBt.hight);
            goToSettings();
            batch.draw(settingsButton.texture,settingsButton.x,settingsButton.y,settingsButton.widt,settingsButton.hight);
            if (!playServices.isSignedIn()){
                batch.draw(connect.texture,connect.x,connect.y,connect.widt,connect.hight);
                connectToPlay();
            }
            else {
                batch.draw(achivment.texture,achivment.x,achivment.y,achivment.widt,achivment.hight);
                batch.draw(liderboard.texture,liderboard.x,liderboard.y,liderboard.widt,liderboard.hight);
                achivmentBt();
                liderboardBt();
            }
            batch.draw(rate.texture,rate.x,rate.y,rate.widt,rate.hight);
            rateBt();
            batch.draw(tutorial.texture,tutorial.x,tutorial.y,tutorial.widt,tutorial.hight);
            tutorialBt();
            try {
                int temp  =prefs.getInteger("hightScore");
                if (temp>0){
                    font.draw(gameHendler.batch,String.valueOf(temp)+" M", screenWidth /1.7f,screenHight/9f);
                    font2.draw(gameHendler.batch,String.valueOf(temp)+" M", screenWidth / 1.68f,screenHight/8.5f);
                    batch.draw(best,screenWidth/1.2f,screenHight/20,screenWidth/6,screenHight/5);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        //game
       else if (gameState == 1) {
            backgroundDrow();
            food.drow();
            fatBoy.drow();
            drowFont();
            drowFont2();
            goToPause();
            batch.draw(pauseButton.texture,pauseButton.x,pauseButton.y,pauseButton.widt,pauseButton.hight);



        }
        //pause
         else if (gameState == 2) {
            if (score < 60){
                gameHendler.getBatch().draw(timeOut, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            }
            else if (score < 150){
                gameHendler.getBatch().draw(timeOut2, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            }
            else if (score>=150){
                gameHendler.getBatch().draw(timeOut3, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            }
            resumeGame();
            batch.draw(resumeButton.texture,resumeButton.x,resumeButton.y,resumeButton.widt,resumeButton.hight);
            quitGame();
            batch.draw(quitButton.texture,quitButton.x,quitButton.y,quitButton.widt,quitButton.hight);
            retryGame();
            batch.draw(retryBotton.texture,retryBotton.x,retryBotton.y,retryBotton.widt,retryBotton.hight);


        }
        //gameover
       else if (gameState == 3) {
            if (score < 60){
                gameHendler.getBatch().draw(gameover, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            }
            else if (score < 150){
                gameHendler.getBatch().draw(gameover2, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            }
            else if (score>=150){
                gameHendler.getBatch().draw(gameover3, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            }

            quitGame();
            batch.draw(quitButton.texture,quitButton.x,quitButton.y,quitButton.widt,quitButton.hight);
            retryGame();
            batch.draw(retryBotton.texture,retryBotton.x,retryBotton.y,retryBotton.widt,retryBotton.hight);

            saveScore(score);

            if (record == 1){
                score = prefs.getInteger("hightScore");
                batch.draw(best,screenWidth/1.39f,screenHight/5,screenWidth/6,screenHight/5);
            }


            font.draw(gameHendler.batch, String.valueOf(gameHendler.score) + " M" , screenWidth / 1.4f, gameHendler.screenHight - (gameHendler.screenHight * 1 / 1.96f));
            font2.draw(gameHendler.batch, String.valueOf(gameHendler.score) +" M" , screenWidth / 1.422f, gameHendler.screenHight - (gameHendler.screenHight * 1 / 2f));

        }
        //settings
       else if (gameState == 4) {

            gameHendler.getBatch().draw(settingsPage, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            backToMenu();
            batch.draw(backButton.texture,backButton.x,backButton.y,backButton.widt,backButton.hight);
            musicButton();
            if (Integer.parseInt(musicIsOn) == 1){
                sounBt.texture = sounOn;
            }
            else if (Integer.parseInt(musicIsOn) == 0){
                sounBt.texture = soundOff;
            }

            if (Integer.parseInt(fxIsOn) == 1){
                fxBt.texture = fxOn;
            }
            else if (Integer.parseInt(fxIsOn) == 0){
                fxBt.texture = fxOff;
            }
            batch.draw(sounBt.texture,sounBt.x,sounBt.y,sounBt.widt,sounBt.hight);
            fxButton();
            batch.draw(fxBt.texture, fxBt.x, fxBt.y, fxBt.widt, fxBt.hight);
            goToAbout();
            batch.draw(aboutBt.texture, aboutBt.x, aboutBt.y, aboutBt.widt, aboutBt.hight);

        }
        //about
        else if (gameState == 5){
            gameHendler.getBatch().draw(aboutBg, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            backToSettings();
            batch.draw(backButton.texture,backButton.x,backButton.y,backButton.widt,backButton.hight);
        }
        //tutorialImg
        else  if (gameState ==6){
            gameHendler.getBatch().draw(guide, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            if (Gdx.input.justTouched()){
                isFirst = false;
                gameState = 1;
            }
        }
    }

    //music button action
    public void musicButton(){
        if (sounBt.checkIfClicked()){
            if (Integer.parseInt(musicIsOn) == 1 ){
                musicIsOn = "0";
                //sounBt.texture = soundOff;
                prefs.putString("music",musicIsOn);
                prefs.flush();

            }
            else if (Integer.parseInt(musicIsOn) == 0){
                musicIsOn = "1";
                //sounBt.texture = sounOn;
                prefs.putString("music",musicIsOn);
                prefs.flush();
            }
            System.out.println(musicIsOn);

        }
    }

    //sound effects button action
    public void fxButton(){
        if (fxBt.checkIfClicked()){
            if (Integer.parseInt(fxIsOn) == 1 ){
                fxIsOn = "0";
                //sounBt.texture = soundOff;
                prefs.putString("fx",fxIsOn);
                prefs.flush();

            }
            else if (Integer.parseInt(fxIsOn) == 0){
                fxIsOn = "1";
                //sounBt.texture = sounOn;
                prefs.putString("fx",fxIsOn);
                prefs.flush();
            }
            System.out.println(fxIsOn);
        }
    }

    //start the game
    public void startPlayAction(){
        if (startPlayBt.checkIfClicked()){
            playServiceHendler.achievementUnlociker(10,2);
            initGame();
            if (isFirst){
                gameState = 6;
            }
            else {
                gameState = 1;
            }
        }
    }

    //buttons
    public  void goToSettings(){
        if (settingsButton.checkIfClicked()){
            initGame();
            gameState = 4;
            if (playServiceHendler.isSignedIn()){
                System.out.println("zibi");
                playServices.rateGame();
            }
        }
    }
    public  void backToMenu(){
        if (backButton.checkIfClicked()){
            initGame();
            gameState = 0;
        }
    }
    public  void goToPause(){
        if (pauseButton.checkIfClicked()){
            gameState = 2;
        }
    }
    public  void quitGame(){
        if (quitButton.checkIfClicked()){
            initGame();
            gameState = 0;
            record = 0;
        }
    }
    public  void retryGame(){
        if (retryBotton.checkIfClicked()){
            initGame();
            gameState = 1;
            record = 0;
            playServiceHendler.achievementUnlociker(10,2);
        }
    }
    public  void resumeGame(){
        if (resumeButton.checkIfClicked()){
            gameState = 1;
        }
    }

    public void backToSettings(){
        if (backButton.checkIfClicked()){
            gameState = 4;
        }
    }
    public void goToAbout(){
        if (aboutBt.checkIfClicked()){
            gameState = 5;
        }
    }
    public void connectToPlay(){
        if (aboutBt.checkIfClicked()){
            playServiceHendler.signIn();
            if (playServiceHendler.isSignedIn()){

            }
            else {
                System.out.println("im out");
                playServices.connectToService();
            }
        }
    }
    public void achivmentBt(){
        if (achivment.checkIfClicked()){
            if (playServices.isSignedIn()){
                playServices.showAchievement();
            }
        }
    }
    public void liderboardBt(){
        if (liderboard.checkIfClicked()){
            if (playServices.isSignedIn()){
                playServices.showScore();
            }
        }
    }
    public void rateBt(){
        if (rate.checkIfClicked()){
                playServices.rateGame();
        }
    }
    public void tutorialBt(){
        if (tutorial.checkIfClicked()){
            playServices.showtutorial();
        }
    }

    //save the highest score
    public void   saveScore( int s){
        int temp;
        try {
            temp = prefs.getInteger("hightScore");
        }catch (Exception e){
            temp = 0;
        }
        if (s > temp){
            prefs = Gdx.app.getPreferences("gamePreferences");
            record = 1;
            prefs.putInteger("hightScore",score);
            prefs.putInteger("firstGme",firstGme);
            if (firstGme !=0){
                personalRecordBreakCounter++;
            }
            prefs.putInteger("personalRecordBreakCounter",personalRecordBreakCounter);
            if (personalRecordBreakCounter==1){
                //Games.Achievements.unlock(gameHelper.getApiClient(),"CgkIgui43PQeEAIQBQ");
                playServiceHendler.achievementUnlociker(3,1);
            }else if (personalRecordBreakCounter==2){
                //Games.Achievements.unlock(gameHelper.getApiClient(),"CgkIgui43PQeEAIQBg");
                playServiceHendler.achievementUnlociker(4,1);
            }
            else if (personalRecordBreakCounter==3){
                //Games.Achievements.unlock(gameHelper.getApiClient(),"CgkIgui43PQeEAIQBw");
                playServiceHendler.achievementUnlociker(5,1);
            }
            //Games.Leaderboards.submitScore(gameHelper.getApiClient(),"CgkIgui43PQeEAIQAQ", score);
            playServiceHendler.submitToliderBoard(score);
            prefs.flush();
            firstGme++;
        }
    }

    //play jump sound
    public void playJumpSound(){
        if ((fatBoy.yPosition >0 && fatBoy.yPosition <30) && fatBoy.isLending == false && Integer.parseInt(fxIsOn) ==1){
            musicGame.bendSound.play();
        }
    }

    //play dodge sound
    public void dodheJumpSound(){
        if (fatBoy.dodgeTime ==1 && Integer.parseInt(fxIsOn) ==1){
            musicGame.bendSound.play();
        }
    }

    //play bite sound
    public void biteSound(){
        if (Integer.parseInt(fxIsOn) ==1){
            musicGame.bite.play();
        }
    }

    //play background music
    public void bgMusicPlayer(){
        if (Integer.parseInt(musicIsOn)==1){
            if (!musicGame.bgMusic.isPlaying()){
                musicGame.bgMusic.play();
            }
        }
        if (Integer.parseInt(musicIsOn)==0){
            if (musicGame.bgMusic.isPlaying()){
                musicGame.bgMusic.stop();
            }
        }

    }

}




