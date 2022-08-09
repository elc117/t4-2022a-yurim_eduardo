package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import java.util.ArrayList;

import static com.mygdx.game.Constants.*;

public class Player {
     private ArrayList<TextureRegion> regions;
     private int spriteIndex = 0;
     private int spriteSize = 6;
     private Body body;
     private Texture tPlayer;
     private TextureRegion trPlayer;
     private State currentState = State.IDLE;

     public Player(World world) {
	  PolygonShape shape = new PolygonShape();
	  BodyDef bdef = new BodyDef();
	  FixtureDef fdef = new FixtureDef();

	  tPlayer = new Texture(Gdx.files.internal("Blue_witch/B_witch_idle.png"));
	  regions = arrayListInitialize(tPlayer, spriteSize);

	  bdef.position.set(16 + PLAYER_WIDTH / 64f, 10 + PLAYER_HEIGHT / 64f);
	  bdef.type = BodyDef.BodyType.DynamicBody;
	  body = world.createBody(bdef);
	  MassData md = new MassData();
	  md.mass = 2;
	  body.setMassData(md);

	  shape.setAsBox((float) PLAYER_WIDTH / (TILE_SIZE * 2), (float) SPRITE_HEIGHT / (TILE_SIZE * 2));
	  fdef.shape = shape;
	  body.createFixture(fdef).setUserData("player");

	  float xSensor = (float) PLAYER_WIDTH / (TILE_SIZE * 2);
	  float ySensor = (float) SPRITE_HEIGHT / (TILE_SIZE * 2);

	  shape.setAsBox(xSensor - 0.1f, 0, new Vector2(0, -ySensor), 0);
	  fdef.shape = shape;
	  fdef.isSensor = true;
	  body.createFixture(fdef).setUserData("foot");

	  shape.setAsBox(xSensor - 0.1f, 0, new Vector2(0, ySensor), 0);
	  fdef.shape = shape;
	  fdef.isSensor = true;
	  body.createFixture(fdef).setUserData("head");

	  shape.setAsBox(0, ySensor - 0.1f, new Vector2(-xSensor, 0), 0);
	  fdef.shape = shape;
	  fdef.isSensor = true;
	  body.createFixture(fdef).setUserData("left");

	  shape.setAsBox(0, ySensor - 0.1f, new Vector2(xSensor, 0), 0);
	  fdef.shape = shape;
	  fdef.isSensor = true;
	  body.createFixture(fdef).setUserData("right");

     }

     private ArrayList<TextureRegion> arrayListInitialize (Texture texture, int size) {
	  // podemos retornar o array
	  TextureRegion region;
	  ArrayList<TextureRegion> regions = new ArrayList<TextureRegion>();
	  for (int i = 0; i < size; i++) {
	       region = new TextureRegion(texture,
					  0,
					  i * SPRITE_HEIGHT,
					  SPRITE_WIDTH,
					  SPRITE_HEIGHT);
	       regions.add(region);				  
	  }
	  return regions;
     }
     
     public void render(SpriteBatch batch, OrthographicCamera camera, int frameCount) {
	  if (frameCount % 5 == 0) {
	       spriteIndex++;	       
	       if (spriteIndex >= spriteSize) {
		    spriteIndex = 0;
	       }
	  }
	  
	  batch.draw(regions.get(spriteIndex),
		     body.getPosition().x - SPRITE_WIDTH / TILE_SIZE,
		     body.getPosition().y - SPRITE_HEIGHT / TILE_SIZE - 0.3f,
		     SPRITE_WIDTH / (TILE_SIZE / 2),
		     SPRITE_HEIGHT / (TILE_SIZE / 2));	  

     }

     public Body getBody() {
	  return body;
     }

     public State getCurrentState() {
	  return currentState;
     }

     public void setCurrentState(State currentState) {
	  this.currentState = currentState;
     }
}
