package com.mygdx.game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.*;

import java.awt.*;

public class GameMap {
    private final World world;
    private final Box2DDebugRenderer debugRenderer;
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private GamePhysics gPhysics;

    public GameMap (OrthographicCamera camera) {
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();
        gPhysics = new GamePhysics();
        map = new TmxMapLoader().load("tileset/ground.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1f / Constants.TILE_SIZE);

        embodyTiles(world, map);

        mapRenderer.setView(camera);
    }
    public World getWorld () {
        return world;
    }
    public void renderGameMap (OrthographicCamera camera) {
        debugRenderer.render(world, camera.combined);
        mapRenderer.render();

        world.step(1/60f, 6, 2);
    }
    private void embodyTiles (World world, TiledMap map) {
        MapObjects objects = map.getLayers().get("ground").getObjects();

        for (int i = 0; i < objects.getCount(); i++) {
            RectangleMapObject obj = (RectangleMapObject)objects.get(i);
            Rectangle rect = obj.getRectangle();
            gPhysics.rectangleCreate(world, rect, false);
        }
    }
}