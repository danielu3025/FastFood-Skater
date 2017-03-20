package com.fastfoodskater.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

import static com.fastfoodskater.game.Game.gameHendler;



public class FatBoy {
    float timeStete = Gdx.graphics.getDeltaTime();
    Texture [] sprite;
    Texture [] fat;
    Texture [] skinny;
    Texture [] spriteGameover;
    Texture jumpImg,doudgeImg,doudgeImgS,jumpImgS,doudgeImgF,jumpImgF,ambulanceImg;
    float yPosition = 0;
    float velocity = 50;
    float regularGravity = 25;
    float gravity= regularGravity;
    float superGarvity = (float) (regularGravity*3);
    int status =0;
    float width,hieght,dodgeW,dodgeH,jumpW,jumpH,runW,rectangleXpos;
    Animation<Texture> animation;
    Texture currentFrame;
    float stateTime;
    float dodgeTime = 0;
    boolean inMaxHiget = false;
    boolean jump = false;
    Rectangle rectangle = new Rectangle();
    float startingX;
    float startingY;
    boolean isLending = false;
    float dt ;
    int ambolancePosX = -800;
    int temp =0;
    boolean ambulanceIn = false;
    public class SpriteSize{
        float w;float h;

        public SpriteSize(float w, float h) {
            this.w = w;
            this.h = h;
        }
    }

    SpriteSize[] sizes;
    public FatBoy() {
        sprite = new Texture[4];
        fat = new Texture[4];
        skinny = new Texture[4];

        spriteGameover = new Texture[7];
        fat[0] = new Texture("img/fn1.png");
        fat[1] = new Texture("img/fn2.png");
        fat[2] = new Texture("img/fn3.png");
        fat[3] = new Texture("img/fn4.png");

        skinny[0] = new Texture("img/sn1.png");
        skinny[1] = new Texture("img/sn2.png");
        skinny[2] = new Texture("img/sn3.png");
        skinny[3] = new Texture("img/sn4.png");

        sprite[0] = fat[0];
        sprite[1] = fat[1];
        sprite[2] = fat[2];
        sprite[3] = fat[3];

        jumpImgF = new Texture("img/fn5.png");
        doudgeImgF = new Texture("img/fn6.png");
        jumpImgS = new Texture("img/sn5.png");
        doudgeImgS = new Texture("img/sn6.png");

        jumpImg = jumpImgF;
        doudgeImg = doudgeImgF;

        ambulanceImg = new Texture("img/ambulance.png");
        for(int i =0 ; i<7;i++){
            temp = i+7;
            spriteGameover[i] = new Texture("img/fn"+temp+".png");
        }
        width = Gdx.graphics.getWidth()/6;
        hieght = Gdx.graphics.getHeight()/2.5f;
        startingX = 70;
        rectangleXpos = startingX + width/2;
        startingY = 0;
        dodgeH = hieght/1.4f;
        dodgeW = width/3;
        jumpH  = hieght-yPosition;
        jumpW = width/3;
        runW = width/3;
        animation = new Animation<Texture>(0.1f, sprite);
        stateTime =0f;
        rectangle.set(startingX,yPosition,width,hieght);
        sizes = new SpriteSize[6];
        sizes[0] = new SpriteSize(width*1.1f,hieght*1.1f);
        sizes[1] = new SpriteSize(width*1.25f,hieght*1.1f);
        sizes[2] = new SpriteSize(width*2,hieght/1.27f);
        sizes[3] = new SpriteSize(width*2.7f,hieght/1.9f);
        sizes[4] = new SpriteSize(width*2.5f,hieght/1.7f);
        sizes[5] = new SpriteSize(width*1.8f,hieght/2.2f);
        //sizes[6] = new SpriteSize(width*2.61f,hieght/2.2f);
        temp =0;

    }

    public float getWidth() {

        return width;
    }
    public float getHieght() {
        return hieght;
    }


    //calc the postion of the fat boy  in the y axe
    public void jumpLogic(){
        if (jump){
            if (gameHendler.fatBoy.yPosition  >= -100 + gameHendler.screenHight/2+gameHendler.fatBoy.getHieght()/2){
                inMaxHiget =true;
                isLending = true;
                yPosition = yPosition-gravity-20;

            }
            if (inMaxHiget){
                yPosition = yPosition-gravity;
                if (yPosition<=0){
                    status = 0;
                    yPosition= 0;
                    jump =false;
                    isLending =false;
                    inMaxHiget= false;
                    gameHendler.fatBoy.gravity = gameHendler.fatBoy.regularGravity;
                    dt = 0;
                }
            }
            else {
                yPosition = yPosition+gravity;
            }

        }
    }
    //set a diffrent "hitbox" by the fat boy mode (jump/run/dodge)
    public void rectanglePicker(){
        if (status == 0) {
            rectangle.set(rectangleXpos,startingY,runW,hieght);
        }
        else if ( status==1){
             rectangle.set(rectangleXpos,yPosition,jumpW,jumpH);
        }
        else {
            rectangle.set(rectangleXpos,startingY,dodgeW,dodgeH);
        }
    }
    //drow a diffrent Texture by the fat boy mode (jump/run/dodge)
    public void drow(){
        if (!gameHendler.gameOverAniem) {
            if (gameHendler.gameState != 0) {
                if (status == 0) {
                    gameHendler.batch.draw(currentFrame, startingX, startingY, width, hieght);
                } else if (status == 1) {
                    gameHendler.batch.draw(jumpImg, startingX, yPosition, width + width / 4, jumpH);
                } else {
                    gameHendler.batch.draw(doudgeImg, startingX, startingY, width, dodgeH);
                    dodgeTime++;
                    if (dodgeTime >= 70) {
                        dodgeTime = 0;
                        status = 0;
                    }
                }
            }
        }
        else if (!ambulanceIn){
            if (temp>1){
                gameHendler.batch.draw(spriteGameover[gameOverAnimePromoter()], startingX, startingY,sizes[gameOverAnimePromoter()].w,sizes[gameOverAnimePromoter()].h);
            }
            else {
                gameHendler.batch.draw(spriteGameover[gameOverAnimePromoter()], startingX, startingY,sizes[gameOverAnimePromoter()].w,sizes[gameOverAnimePromoter()].h);
            }
        }
        else {
            ambualncePromoter();
            gameHendler.batch.draw(ambulanceImg,ambolancePosX,0,800,500);
        }

    }
    //animation of running
    public void spriteChanger(){
        gameHendler.fatBoy.stateTime += Gdx.graphics.getDeltaTime();
        gameHendler.fatBoy.currentFrame = gameHendler.fatBoy.animation.getKeyFrame(gameHendler.fatBoy.stateTime, true);
    }
    public int gameOverAnimePromoter() {
        timeStete +=Gdx.graphics.getDeltaTime();
        if (timeStete >= 0.17f) {
            timeStete = 0f;
            temp++;
        }
        if (temp >5){
            temp=5;
            ambulanceIn  =true;
        }
        return temp;
    }
    public void ambualncePromoter() {
        timeStete +=Gdx.graphics.getDeltaTime();
        if (timeStete >= 0.001f && ambolancePosX <5) {
            timeStete = 0f;
            ambolancePosX +=5;
            gameHendler.batch.draw(spriteGameover[5], startingX, startingY,sizes[5].w,sizes[5].h);
        }
        if (ambolancePosX >=0 && ambolancePosX<=5){
            ambolancePosX +=6;
        }
        if (ambolancePosX >=6 && ambolancePosX <=Gdx.graphics.getWidth()){
            ambolancePosX += 15;
        }
        if (ambolancePosX>Gdx.graphics.getWidth()){
            gameHendler.gameState =3;
        }
    }
    public  void boyChanger(int s){
        if (s == 1){
            sprite[0] = fat[0];
            sprite[1] = fat[1];
            sprite[2] = fat[2];
            sprite[3] = fat[3];
            jumpImg = jumpImgF;
            doudgeImg = doudgeImgF;
        }
        else {
            sprite[0] = skinny[0];
            sprite[1] = skinny[1];
            sprite[2] = skinny[2];
            sprite[3] = skinny[3];
            jumpImg = jumpImgS;
            doudgeImg = doudgeImgS;
        }
    }
}

