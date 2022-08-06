package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class PPJogo extends ApplicationAdapter {
	static final int WORLD_WIDTH = 32;
	static final int WORLD_HEIGHT = 16;

	private OrthographicCamera camera;

//	private OrthogonalTiledMapRenderer tmr;
//	private TiledMap map;
//
//	private World world;
//	private Box2DDebugRenderer debugRenderer;

	private Body player;
	private Body platform;
	private GameMap gMap;
	private boolean playerJumping = false;

	private float ppt = 32;


//     private Texture texture;
//     private Sprite sprite;
//     private SpriteBatch batch;

	@Override
	public void create () {
		cameraCreate();

		//world = new World(new Vector2(0, -10), true);

		//debugRenderer = new Box2DDebugRenderer();
		gMap = new GameMap(camera);
		player = boxCreate(16, 8, 1, 1, true);

		//map = new TmxMapLoader().load("tileset/ground.tmx");
		//tmr = new OrthogonalTiledMapRenderer(map, 1 / 32f);
		//tmr.setView(camera);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		inputUpdate();
		gMap.renderGameMap(camera);
		//tmr.render();
//		debugRenderer.render(world, camera.combined);
//		world.step(1/60f, 6, 2);
	}

	@Override
	public void dispose () {

	}

	private void cameraCreate () {
		camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();
	}

	private Body boxCreate (int x, int y, float w, float h, boolean isDynamic) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(x, y);

		if (isDynamic) {
			bodyDef.type = BodyDef.BodyType.DynamicBody;
		} else {
			bodyDef.type = BodyDef.BodyType.StaticBody;
		}

		Body body = gMap.getWorld().createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(w, h);
		body.createFixture(shape, 1);

		shape.dispose();
		return body;
	}

	private void inputUpdate () {
		int horizontalVelocity = 0;

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			horizontalVelocity--;
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			horizontalVelocity++;
		}

		if (Gdx.input.isKeyPressed(Keys.UP) && !playerJumping) {
			player.applyForceToCenter(0, 2000, false);
			playerJumping = true;
		}

		player.setLinearVelocity(horizontalVelocity * 5, player.getLinearVelocity().y);
	}
}