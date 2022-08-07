package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;
import static com.mygdx.game.Constants.*;

public class PPJogo extends ApplicationAdapter {
	private OrthographicCamera camera;
	private Player player;     
	private GameMap gMap;
	private GameInput gInput;
     
	@Override
	public void create () {
		cameraCreate();
		gMap = new GameMap(camera);
		player = new Player(gMap.getWorld());
		gInput = new GameInput(gMap.getWorld(), player);
	}
	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		gInput.inputUpdate(player);
		gMap.renderGameMap(camera);
	}
	private void cameraCreate () {
		camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();
	}
	@Override
	public void dispose () {
	}
}