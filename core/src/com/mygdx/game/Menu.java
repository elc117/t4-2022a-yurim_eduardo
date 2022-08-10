package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import static com.mygdx.game.Constants.*;

public class Menu {
    private final BitmapFont bitFont, titleFont;

    public Menu() {
        titleFont = bitmapCreate(32);
        bitFont = bitmapCreate(18);
    }

    public void render(Batch batch) {
        if (GlobalState.MENU == globalState) {
            titleFont.draw(batch, "A Bruxa Judite!", -52, 30);
            bitFont.draw(batch, "Pressione ENTER para continuar.", -72, -14);

        } else if (GlobalState.STORY == globalState) {
            bitFont.draw(batch, "Judite saiu para colher cogumelos,\nmas acabou se perdendo.\nAjude-a a encontrar sua casa!", -76, 40);
            bitFont.draw(batch, "Pressione -> para continuar.", -64, -28);
        } else if (GlobalState.END == globalState) {
            bitFont.draw(batch, "Judite se perdeu!", -56, 40);
        }
    }

    private BitmapFont bitmapCreate(int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/fonte.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }
    public void dispose() {
        titleFont.dispose();
        bitFont.dispose();
    }
}