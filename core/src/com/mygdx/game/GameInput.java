package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;

public class GameInput {
     public GameInput() {
     }
     public void inputUpdate (Player player) {
	  Body pBody = player.getBody();
	  int horizontalVelocity = 0;

	  if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
	       horizontalVelocity--;
	  }
	  if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
	       horizontalVelocity++;
	  }
	  if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
	       pBody.applyForceToCenter(0, 300, false);
	  }
	
	  pBody.setLinearVelocity(horizontalVelocity * 5,
				  pBody.getLinearVelocity().y);
     }
}
