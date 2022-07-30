package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.Hashtable;

public class Player {
  private Hashtable<Integer, TextureRegion> running;
  private Hashtable<Integer, TextureRegion> idle;
  private Rectangle player;
  private TextureRegion playerImage;
  private Integer nSprites = 0;
  private Integer frames = 0;
  private Integer handside = 0;

  public Player(){
    this.running = new Hashtable<Integer, TextureRegion>();
    this.idle = new Hashtable<Integer, TextureRegion>();
    this.player = new Rectangle(0, 64, 32, 32);
    spritesCreate(running, new Texture(Gdx.files.internal("run.png")));
    spritesCreate(idle, new Texture(Gdx.files.internal("idle.png")));
  }
  private void spritesCreate(Hashtable<Integer, TextureRegion> ht, Texture img){
    Integer id = 0;
    TextureRegion tr;
    for (int x = 0; x < 8; x++){
      tr = new TextureRegion(img, x * 231 + 72, 52, 86, 86);

      /* CADA SPRITE TEM 86x86. ENTÃO SOBRE 145 HORIZONTALMENTE (72 DE CADA LADO)
	 E 104 VERTICALMENTE (52 DE CADA LADO). */
      
      ht.put(id, tr);
      id++;
    }
  }
  public void render(SpriteBatch batch) {

    /* TEMOS QUE REFATORAR: 
       1) SEPARAR A RENDERIZAÇÃO DA ATUALIZAÇÃO DAS IMAGENS 
       E DA ATUALIZAÇÃO DA DIREÇÃO DAS IMAGENS. PODEMOS RELACIONAR
       UMA COISA COM A OUTRA ATRAVÉS DE VARIÁVEIS DE ESTADO. EXEMPLO:
       UMA VARIÁVEL BOOLEANA QUE DIZ SE O PERSONAGEM ESTÁ CORRENDO
       NO MOMENTO, OU SE ELE ESTÁ PARADO ETC.
       2) QUANDO FOR NECESSÁRIO ALTERAR A DIREÇÃO DAS IMAGENS,
       TALVEZ SEJA MELHOR PERCORRER A HASHTABLE MODIFICANDO
       TODAS AS IMAGENS EM VEZ DE SEMPRE INVERTER DUAS VEZES.
       3) PODEMOS TER UMA VARIÁVEL DE ESTADO GERAL QUE DIZ SE 
       HOUVE MUDANÇA DO ESTADO DO PERSONAGEM. EXEMPLO: SE O
       O PERSONAGEM MUDOU DE DIREÇÃO, ENTÃO TEMOS QUE FAZER ALGUMAS
       ALTERAÇÕES.*/
    
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      handside = 0;
      if (nSprites >= 8) {
	nSprites = 0;
      }
      player.x -= 200 * Gdx.graphics.getDeltaTime();
      running.get(nSprites).flip(true, false);
      batch.draw(running.get(nSprites), player.x, player.y);
      running.get(nSprites).flip(true, false);
    } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      handside = 1;
      if (nSprites >= 8) {
	nSprites = 0;
      }
      player.x += 200 * Gdx.graphics.getDeltaTime();
      batch.draw(running.get(nSprites), player.x, player.y);      
    } else {
      if (nSprites >= 6) {
	nSprites = 0;
      }
      if (handside == 0) { // SE FOR PARA A ESQUERDA
	idle.get(nSprites).flip(true, false);
	batch.draw(idle.get(nSprites), player.x, player.y);
	idle.get(nSprites).flip(true, false);
      } else {
	batch.draw(idle.get(nSprites), player.x, player.y);
      }
    }
    
    if (frames % 5 == 0) {
      nSprites++;
    }
    frames++;
    
    if (frames > 60) {
      frames = 0;
    }
  }
}
