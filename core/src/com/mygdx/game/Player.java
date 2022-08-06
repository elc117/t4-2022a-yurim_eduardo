package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Player {
     private Body body;
     private GamePhysics gPhysics;
     
     public Player (World world) {
	  gPhysics = new GamePhysics();
	  Rectangle rect = new Rectangle(0, 2 * Constants.TILE_SIZE, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
	  body = gPhysics.rectangularBodyCreate(world, rect, true);
     }

     public Body getBody () {
	  return body;
     }
}
