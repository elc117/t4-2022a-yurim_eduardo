package com.mygdx.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mygdx.game.PPJogo;


public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        // Resizable application, uses available space in browser
        //return new GwtApplicationConfiguration(true);
        // Fixed size application:
        return new GwtApplicationConfiguration(true);
        //return new GwtApplicationConfiguration(1024, 512);
    }
    @Override
    public ApplicationListener createApplicationListener() {
        return new PPJogo();
    }

}
