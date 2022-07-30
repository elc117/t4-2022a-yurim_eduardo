package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Plataform map;
	private OrthographicCamera camera;
	private Player player;

	@Override
	public void create () {
		map = new Plataform();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 512);
		this.player = new Player();
	}

	@Override
	public void render () {
		ScreenUtils.clear(255, 0, 0, 1);
		camera.update();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		map.render(batch);
		player.render(batch);
		batch.end();
	}

	@Override
	public void dispose () { //nao esquecer de dar dispose em tudo
		batch.dispose();
	}
}