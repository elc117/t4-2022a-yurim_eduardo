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
	private Rectangle player;
	private TextureRegion playerImage;
	private Texture baseRunner;

	@Override
	public void create () {
		baseRunner = new Texture(Gdx.files.internal("run.png"));
		playerImage = new TextureRegion(baseRunner, 0, 0, 231, 190);
		//teste acima
		map = new Plataform();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 512);
		//player abaixo
		player = new Rectangle();
		player.x = 0;
		player.y = 0;
		player.width = 32;
		player.height = 32;
	}

	@Override
	public void render () {
		ScreenUtils.clear(255, 0, 0, 1);
		camera.update();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		map.render(batch);
		batch.draw(playerImage, player.x, player.y);
		//batch.draw(baseRunner, player.x, player.y);
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.x += 200 * Gdx.graphics.getDeltaTime();
		batch.end();
	}

	@Override
	public void dispose () { //nao esquecer de dar dispose em tudo
		batch.dispose();
	}
}