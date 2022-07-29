package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Hashtable;

public class Plataform{
    private Hashtable<Integer, Block> mapping;
    private int map[][];
    private int X_SIZE = 32;
    private int Y_SIZE = 16;
    private int TILE_SIZE = 32;
    private Texture baseTexture; // pra mostrar na tela temos que fazer isso
    private TextureRegion region;

    public Plataform () {
        map = new int[X_SIZE][Y_SIZE];
        baseTexture = new Texture(Gdx.files.internal("map.png"));
        this.mapping = new Hashtable<Integer, Block>();
        mappingCreate();
        for (int i = 0; i < X_SIZE; i++) {
            map[i][1] = 4;
        }
        for (int i = 0; i < X_SIZE; i++) {
            map[i][0] = 5;
        }
    }
    private void mappingCreate(){
        Integer id = 1;
        region = new TextureRegion(baseTexture, 0, 0, TILE_SIZE, TILE_SIZE);
        Block block;
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                block = new Block(new TextureRegion(baseTexture, x * 32, y * 32, 32, 32));
                mapping.put(id, block);
                id++;
            }
        }
    }

    public void render (SpriteBatch batch) {
        for (int x = 0; x < X_SIZE; x++) {
            for (int y = 0; y < Y_SIZE; y++) {
                if (map[x][y] != 0){
                    mapping.get(map[x][y]).render(batch, x, y);
                }
            }
        }
    }
}