package com.mygdx.game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.maps.objects.*;

public class GameMap {

    private final int TILE_SIZE = 32;
    private final int WORLD_WIDTH = 32;
    private final int WORLD_HEIGHT = 16;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public GameMap (OrthographicCamera camera) {
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        map = new TmxMapLoader().load("tileset/ground.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1f / TILE_SIZE);

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
            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(rect.getX() / 32 + (rect.getWidth() / 64), rect.getY() / 32 + (rect.getHeight() / 64));
            bodyDef.type = BodyDef.BodyType.StaticBody;

            Body body = world.createBody(bodyDef);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(rect.getWidth() / 64, rect.getHeight() / 64);
            body.createFixture(shape, 1);

            shape.dispose();
        }
    }
}