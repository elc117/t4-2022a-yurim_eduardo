package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.Hashtable;

public class Player {
    private Hashtable<Integer, TextureRegion> sprites;
    private Rectangle player;
    private Texture baseRunner;
    private Integer nSprites = 0;

    public Player(){
        this.sprites = new Hashtable<Integer,TextureRegion>();
        this.player = new Rectangle(0, 0, 32, 32);
        this.baseRunner = new Texture(Gdx.files.internal("run.png"));
        spritesCreate();
    }
    private void spritesCreate(){
        Integer id = 0;
        TextureRegion tr;
        for (int x = 0; x < 8; x++){
            tr = new TextureRegion(baseRunner, x * 231, 0, 231, 190);
            sprites.put(id, tr);
            id++;
        }
    }
    public void render(SpriteBatch batch) {
        TextureRegion playerImage = sprites.get(nSprites);
        batch.draw(playerImage, player.x, player.y);
        
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if (nSprites>0){
                nSprites--;
            }
            else{
                nSprites = 0;
            }
            player.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            nSprites++;
            if (nSprites>=8){
                nSprites=0;
            }
            player.x += 200 * Gdx.graphics.getDeltaTime();
        }
    }
}