package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.Constants.*;

public class Player {
    private Body body;
    private Texture tPlayer;
    private TextureRegion trPlayer;
    private State currentState = State.IDLE;

    public Player(World world) {
        PolygonShape shape = new PolygonShape();
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();

        tPlayer = new Texture(Gdx.files.internal("warrior/_Idle.png"));
        trPlayer = new TextureRegion(tPlayer, 47, 0, 25, 40);

        bdef.position.set(16 + PLAYER_WIDTH / 64f, 10 + PLAYER_HEIGHT / 64f);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);
        MassData md = new MassData();
        md.mass = 2;
        body.setMassData(md);

        shape.setAsBox((float) PLAYER_WIDTH / (TILE_SIZE * 2), (float) PLAYER_HEIGHT / (TILE_SIZE * 2));
        fdef.shape = shape;
        body.createFixture(fdef).setUserData("player");

        float xSensor = (float) PLAYER_WIDTH / (TILE_SIZE * 2);
        float ySensor = (float) PLAYER_HEIGHT / (TILE_SIZE * 2);

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

    public void render(SpriteBatch batch, OrthographicCamera camera) {
//      batch.draw(trPlayer, body.getPosition().x, body.getPosition().y);
        batch.draw(trPlayer, body.getPosition().x, body.getPosition().y, 32, 16);
    }

    public Body getBody() {
        return body;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}
