package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import static com.mygdx.game.Constants.*;

public class PPJogo extends ApplicationAdapter {
     private OrthographicCamera camera;
     private Player player;
     private GameMap gMap;
     private GameInput gInput;
     private SpriteBatch batch;
     private int frameCount = 0;
     private BitmapFont silver;

     @Override
     public void create() {
	  camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
	  camera.setToOrtho(false, 32, 16);
	  gMap = new GameMap(camera);
	  player = new Player(gMap.getWorld());
	  gInput = new GameInput(gMap.getWorld(), player);
	  batch = new SpriteBatch();
     }

     @Override
     public void render() {
	  frameCount++;
	  if (frameCount >= 60) {
	       frameCount = 0;
	  }

	  cameraUpdate();
	  ScreenUtils.clear(0.4f, 0.71f, 1, 1);
	  gInput.inputUpdate(player);
	  gMap.update(camera);
	  gMap.renderGameMap(camera);
	  batch.setProjectionMatrix(camera.combined);
	  batch.begin();

	  player.render(batch, camera, frameCount);
	  
	  batch.end();
     }

     private void cameraUpdate() {
	  Vector3 v3 = new Vector3();

	  if (player.getBody().getPosition().x > 16 && player.getBody().getPosition().x < 496) {
	       v3.x = player.getBody().getPosition().x;
	  } else if (player.getBody().getPosition().x >= 496) {
	       v3.x = 496;
	  } else {
	       v3.x = 16;
	  }

	  v3.y = 8;
	  camera.position.set(v3);
	  camera.update();
     }

     @Override
     public void dispose() {
     }

     private BitmapFont bitmapCreate (int size) {
	  FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Silver.ttf"));
	  FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	  parameter.size = size;
	  BitmapFont font = generator.generateFont(parameter);
	  generator.dispose();
	  return font;
     }
}
