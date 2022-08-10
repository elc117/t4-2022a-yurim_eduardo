package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import static com.mygdx.game.Constants.*;

public class Menu {
    private TextureRegion buttonBackground;
    private TextureRegion button;
    private Texture texture;
    private Texture title;
    private BitmapFont bitFont, titleFont;

    public Menu() {
        texture = new Texture(Gdx.files.internal("menu.png"));
        buttonBackground = new TextureRegion(texture, 9, 190, 45, 67);
        title = new Texture(Gdx.files.internal("titulo.png"));

        //teste abaixo
        titleFont = bitmapCreate(32);
        bitFont = bitmapCreate(18);
    }

    public void render(Batch batch) {
        if (GlobalState.MENU == globalState) {
            titleFont.draw(batch, "A Bruxa Judite!", 0, 0);
            //batch.draw(title, 13, 12, 58/6f,14/6f);
        } else if (GlobalState.STORY == globalState) {
            bitFont.draw(batch, "Judite saiu para colher cogumelos,\n   mas acabou se perdendo.\nAjude-a a encontrar a sua casa!", -86, 40);
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
}