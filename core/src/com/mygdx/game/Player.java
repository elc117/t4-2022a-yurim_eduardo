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
    private ArrayList<TextureRegion> idleRight;
    private ArrayList<TextureRegion> idleLeft;
    private ArrayList<TextureRegion> runningRight;
    private ArrayList<TextureRegion> runningLeft;
    private int spriteIndex = 0;
    private Body body;
    private TextureRegion trPlayer;
    private State currentState = State.IDLE;
    private State previousState = State.IDLE;

    public Player(World world) {
        PolygonShape shape = new PolygonShape();
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();

        Texture texture;
        texture = new Texture(Gdx.files.internal("Blue_witch/B_witch_idle.png"));
        idleRight = arrayListInitialize(texture, 6, false);
        texture = new Texture(Gdx.files.internal("Blue_witch/B_witch_idle.png"));
        idleLeft = arrayListInitialize(texture, 6, true);
        texture = new Texture(Gdx.files.internal("Blue_witch/B_witch_run.png"));
        runningRight = arrayListInitialize(texture, 8, false);
        texture = new Texture(Gdx.files.internal("Blue_witch/B_witch_run.png"));
        runningLeft = arrayListInitialize(texture, 8, true);

        bdef.position.set(16 + PLAYER_WIDTH / 64f, 10 + PLAYER_HEIGHT / 64f);
		//bdef.position.set(490 + PLAYER_WIDTH / 64f, 10 + PLAYER_HEIGHT / 64f);//teste posicao fim do mapa

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

    private ArrayList<TextureRegion> arrayListInitialize(Texture texture, int size, boolean flip) {
        // podemos retornar o array
        TextureRegion region;
        ArrayList<TextureRegion> regions = new ArrayList<TextureRegion>();
        for (int i = 0; i < size; i++) {
            region = new TextureRegion(texture,
                    0,
                    i * SPRITE_HEIGHT,
                    SPRITE_WIDTH,
                    SPRITE_HEIGHT);
            if (flip) {
                region.flip(true, false);
                regions.add(region);
            } else {
                regions.add(region);
            }
        }
        return regions;
    }

    public void render(SpriteBatch batch, OrthographicCamera camera, int frameCount) {
        ArrayList<TextureRegion> currentRegion;

        if (currentState != previousState) {
            spriteIndex = 0;
        }

        if (currentState == State.RUNNING_RIGHT) {
            currentRegion = runningRight;
        } else if (currentState == State.RUNNING_LEFT) {
            currentRegion = runningLeft;
        } else if (rightHandSide) {
            currentRegion = idleRight;
        } else {
            currentRegion = idleLeft;
        }

        if (frameCount % 5 == 0) {
            spriteIndex++;
            if (spriteIndex >= currentRegion.size()) {
                spriteIndex = 0;
            }
        }

        batch.draw(currentRegion.get(spriteIndex),
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
        previousState = this.currentState;
        this.currentState = currentState;
    }
}
