package com.mygdx.game;
import com.badlogic.gdx.physics.box2d.*;
import static com.mygdx.game.Constants.*;

public class ListenerClass implements ContactListener {
    private Player player;
    private Fixture fA, fB;

    public ListenerClass(Player player) {
        this.player = player;
    }
    @Override
    public void beginContact(Contact contact) {
        fA = contact.getFixtureA();
        fB = contact.getFixtureB();

        if (fA.getUserData() != null && fA.getUserData().equals("foot")){
            playerOnGround = true;
            player.setCurrentState(State.IDLE);
        }
        if (fB.getUserData() != null && fB.getUserData().equals("foot")){
            playerOnGround = true;
            player.setCurrentState(State.IDLE);
        }
        if (fA.getUserData() != null && fA.getUserData().equals("head")){
            playerOnGround = false;
            player.setCurrentState(State.FALLING);
        }
        if (fB.getUserData() != null && fB.getUserData().equals("head")){
            playerOnGround = false;
            player.setCurrentState(State.FALLING);
        }
        if (fA.getUserData() != null && fA.getUserData().equals("left")){
            System.out.println("fa left begin");
            sideContact = LEFT_CONTACT;
        }
        if (fB.getUserData() != null && fB.getUserData().equals("left")){
            System.out.println("fb left begin");
            sideContact = LEFT_CONTACT;
        }
        if (fA.getUserData() != null && fA.getUserData().equals("right")){
            sideContact = RIGHT_CONTACT;
        }
        if (fB.getUserData() != null && fB.getUserData().equals("right")){
            sideContact = RIGHT_CONTACT;
        }
    }
    @Override
    public void endContact(Contact contact) {
        fA = contact.getFixtureA();
        fB = contact.getFixtureB();

        if (fA.getUserData() != null && fA.getUserData().equals("foot")){
            playerOnGround = false;
            player.setCurrentState(State.JUMPING);
        }
        if (fB.getUserData() != null && fB.getUserData().equals("foot")){
            playerOnGround = false;
            player.setCurrentState(State.JUMPING);
        }
        if (fA.getUserData() != null && fA.getUserData().equals("head")){
            player.setCurrentState(State.FALLING);
        }
        if (fB.getUserData() != null && fB.getUserData().equals("head")){
            player.setCurrentState(State.FALLING);
        }
        if (fA.getUserData() != null && fA.getUserData().equals("left")){
            System.out.println("fa left end");
            sideContact = NO_CONTACT;
        }
        if (fB.getUserData() != null && fB.getUserData().equals("left")){
            System.out.println("fb left end");
            sideContact = NO_CONTACT;
        }
        if (fA.getUserData() != null && fA.getUserData().equals("right")){
            sideContact = NO_CONTACT;
        }
        if (fB.getUserData() != null && fB.getUserData().equals("right")){
            sideContact = NO_CONTACT;
        }
    }
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}