package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture tCharacter;
	private Sprite Character;
	int sourceX = 0;

	private float posX, posY;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("background.png");
		tCharacter = new Texture("MainCharacterSprite.png");
		Character = new Sprite(tCharacter);
		posX = 0;
		posY = 0;
	}

	@Override
	public void render () {
		this.move();
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		//img.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		batch.draw(img, 0, 0);
		batch.draw(Character, posX, posY, 39, 59);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	private void move()
	{
		if ( Gdx.input.isKeyPressed(Input.Keys.W) )
			posY += 3;
		if ( Gdx.input.isKeyPressed(Input.Keys.A) )
			posX -= 3;
		if ( Gdx.input.isKeyPressed(Input.Keys.S) )
			posY -= 3;
		if ( Gdx.input.isKeyPressed(Input.Keys.D) )
			posX += 3;
	}
}
