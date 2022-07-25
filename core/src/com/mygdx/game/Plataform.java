package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Plataform{
    private int map[][];
    private int X_SIZE = 32;
    private int Y_SIZE = 16;
    private int TILE_SIZE = 32;
    private Texture baseTexture; // pra mostrar na tela temos que fazer isso
    private TextureRegion region;
    private Block ground;
    private Block ground0;

    public Plataform () {
        map = new int[X_SIZE][Y_SIZE];
        baseTexture = new Texture(Gdx.files.internal("map.png"));
        region = new TextureRegion(baseTexture, 0, 0, TILE_SIZE, TILE_SIZE);
        ground = new Block(1, new TextureRegion(baseTexture, 16, 0, 32, 16));
        ground0 = new Block(2, new TextureRegion(baseTexture, 16, 16, 32, 32));

        for (int i = 0; i < X_SIZE; i++) {
            map[i][1] = 1;
        }
        for (int i = 0; i < X_SIZE; i++) {
            map[i][0] = 2;
        }
    }
    public void render (SpriteBatch batch) {
        for (int x = 0; x < X_SIZE; x++) {
            for (int y = 0; y < Y_SIZE; y++) {
                if (map[x][y] == 1) {
                    ground.render(batch, x, y);
                } else if (map[x][y] == 2) {
                    ground0.render(batch, x, y);
                }
            }
        }
    }
}