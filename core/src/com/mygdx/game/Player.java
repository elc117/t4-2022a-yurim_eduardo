package com.mygdx.game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import static com.mygdx.game.Constants.*;

public class Player {
    private Body body;
    private GamePhysics gPhysics;
    private State currentState = State.IDLE;

    public Player (World world) {
        PolygonShape shape = new PolygonShape();
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();

        bdef.position.set(1+PLAYER_WIDTH/64f, 2+PLAYER_HEIGHT/64f);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);
        MassData md = new MassData();
        md.mass = 2;
        body.setMassData(md);

        shape.setAsBox((float) PLAYER_WIDTH / (TILE_SIZE * 2), (float) PLAYER_HEIGHT / (TILE_SIZE * 2));
        fdef.shape = shape;
        body.createFixture(fdef).setUserData("player");

        shape.setAsBox(1, 1, new Vector2(0, -1), 0);
        fdef.shape = shape;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("foot");

        shape.setAsBox(1, 1, new Vector2(0, 1), 0);
        fdef.shape = shape;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("head");

        shape.setAsBox(1, 1, new Vector2(-1, 0), 0);
        fdef.shape = shape;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("left");

        shape.setAsBox(1, 1, new Vector2(1, 0), 0);
        fdef.shape = shape;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("right");
    }
    public Body getBody () {
        return body;
    }
    public State getCurrentState() {
        return currentState;
    }
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}