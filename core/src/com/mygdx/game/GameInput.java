package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static com.mygdx.game.Constants.*;

public class GameInput {

     public GameInput(World world, Player player) {
	  world.setContactListener(new ListenerClass(player));
     }

     public void inputUpdate (Player player) {
	  Body pBody = player.getBody();
	  int horizontalVelocity = 0;

	  if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && sideContact != LEFT_CONTACT) {
	       horizontalVelocity--;
	       player.setCurrentState(State.RUNNING_LEFT);
	       rightHandSide = false;
	  }
	  if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && sideContact != RIGHT_CONTACT) {
	       horizontalVelocity++;
	       player.setCurrentState(State.RUNNING_RIGHT);
	       rightHandSide = true;
	  }
	  if (horizontalVelocity == 0) {
	       player.setCurrentState(State.IDLE);
	  }
	  if (Gdx.input.isKeyPressed(Input.Keys.UP) && playerOnGround) {
	       pBody.applyForceToCenter(0, 600, false);
	  }

	  pBody.setLinearVelocity(horizontalVelocity * 5,
				  pBody.getLinearVelocity().y);
     }
}
