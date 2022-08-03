package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;


public class PPJogo extends ApplicationAdapter {
     private OrthographicCamera camera;
     private Box2DDebugRenderer b2dr;
     private OrthogonalTiledMapRenderer tmr;
     private TiledMap map;
     private World world;
     private Body player, platform;
     private SpriteBatch batch;
     private Texture texture;

     private float PPM = 32; // pixels per meter

     @Override
     public void create () {
	  camera = new OrthographicCamera();
	  camera.setToOrtho(false, 1024, 512);
	  
	  world = new World(new Vector2(0, -9.8f), false);
	  b2dr = new Box2DDebugRenderer();
	  batch = new SpriteBatch();

	  map = new TmxMapLoader().load("maps/map.tmx");
	  tmr = new OrthogonalTiledMapRenderer(map);
	  
	  player = createBox(86f, 86f, 512, 512, true);
     }

     @Override
     public void render () {
	  ScreenUtils.clear(0f, 0f, 0f, 1f);	  
	  update(Gdx.graphics.getDeltaTime());

	  tmr.render();
	  b2dr.render(world, camera.combined.scl(PPM));
     }

     private void update (float delta) {

	  world.step(1/60f, 6, 2);
	  inputUpdate(delta);
	  cameraUpdate(delta);
	  tmr.setView(camera);	  
	  batch.setProjectionMatrix(camera.combined);
     }

     private void inputUpdate (float delta) {
	  int horizontalForce = 0;
	  
	  if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
	       horizontalForce -= 1;
	  }

	  if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
	       horizontalForce += 1;
	  }

	  if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
	       player.applyForceToCenter(0, 300, false);
	  }

	  player.setLinearVelocity(horizontalForce * 5, player.getLinearVelocity().y); 
     }
     
     private void cameraUpdate (float delta) {
	  camera.update();
     }

     private Body createBox (float w, float h, float x, float y, boolean isDynamic) {
	  Body pBody;

	  BodyDef def = new BodyDef();
	  
	  if (isDynamic) {
	       def.type = BodyDef.BodyType.DynamicBody;
	  } else {
	       def.type = BodyDef.BodyType.StaticBody;
	  }
	  
	  def.position.set(x / PPM, y / PPM);
//	  def.fixedRotation(true);

	  PolygonShape shape = new PolygonShape();
	  shape.setAsBox(w / 2 / PPM, h / 2 / PPM);
	  
	  pBody = world.createBody(def);
	  pBody.createFixture(shape, 1f);
	  
	  shape.dispose();
	  return pBody;
     }
     
     @Override
     public void dispose () {
	  b2dr.dispose();
	  world.dispose();
	  batch.dispose();
	  tmr.dispose();
	  map.dispose();
     }
}
