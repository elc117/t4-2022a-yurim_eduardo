package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Block {
    private int BLOCK_SIZE = 32;
    private TextureRegion region;

    public Block (TextureRegion region) {
        this.region = region;
    }
    public void render (SpriteBatch batch, int x, int y) {
        batch.draw(region, x * BLOCK_SIZE, y * BLOCK_SIZE);
    }
}