package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;

public class GameInput {
    public GameInput() {
    }
    public void inputUpdate (Body player) {
        int horizontalVelocity = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            horizontalVelocity--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            horizontalVelocity++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.applyForceToCenter(0, 300, false);
        }
        player.setLinearVelocity(horizontalVelocity * 5, player.getLinearVelocity().y);
    }
}