package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;

import static com.mygdx.game.Constants.*;

public class PPJogo extends ApplicationAdapter {
    private OrthographicCamera camera;
    private Player player;
    private GameMap gMap;
    private GameInput gInput;

    @Override
    public void create() {
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.setToOrtho(false, 32, 16);//teste
        gMap = new GameMap(camera);
        player = new Player(gMap.getWorld());
        gInput = new GameInput(gMap.getWorld(), player);
    }

    @Override
    public void render() {
        cameraUpdate();
        ScreenUtils.clear(0.4f, 0.71f, 1, 1);
        gInput.inputUpdate(player);
        gMap.update(camera);
        gMap.renderGameMap(camera);
    }

    private void cameraUpdate() {
        Vector3 v3 = new Vector3();
        v3.x = player.getBody().getPosition().x;
        v3.y = player.getBody().getPosition().y;
        camera.position.set(v3);
        camera.update();
    }

    @Override
    public void dispose() {
    }
}