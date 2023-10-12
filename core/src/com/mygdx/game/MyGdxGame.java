package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.MapGroupLayer;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture tBackground;
	private float posX, posY;
	Texture tWalkingCharacter;
	private static final float FRAME_TIME = 1/8f;
	private Animation<TextureRegion> runningLeftAnimation;
	private Animation<TextureRegion> runningRightAnimation;
	private Animation<TextureRegion> runningUpAnimation;
	private Animation<TextureRegion> runningDownAnimation;
	private Animation<TextureRegion> idleAnimation;
	private boolean walking = false;
	private float elapsed_time;
	public enum dir
	{
		left,
		right,
		up,
		down
	}
	dir d = dir.left;
	TextureRegion currentFrame;
	enum Screen {
		TITLE, MAIN_GAME, GOBLIN_FIGHT, SKELETON_FIGHT, MINOTAUR_FIGHT, GAME_OVER;
	}
	Screen currentScreen = Screen.TITLE;

	private boolean golin_fight = false;
	private boolean skeleton_fight = false;
	private boolean minotaur_fight = false;

	@Override
	public void create () {
		batch = new SpriteBatch();
		tBackground = new Texture("background.png");
		tWalkingCharacter = new Texture("WalkingSprite.png");

		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyDown (int keyCode) {

				if(currentScreen == Screen.TITLE && keyCode == Input.Keys.SPACE){
					currentScreen = Screen.MAIN_GAME;
				}

				return true;
			}
		});

		main_game_create();
	}

	@Override
	public void render () {

		switch ( currentScreen )
		{
			case TITLE:
				title_render();
				break;
			case MAIN_GAME:
				main_game_render();
				break;
			case GOBLIN_FIGHT:
				goblin_fight_render();
				break;
			case SKELETON_FIGHT:
				skeleton_fight_render();
				break;
			case MINOTAUR_FIGHT:
				minotaur_fight_render();
				break;
			case GAME_OVER:
				game_over_render();
				break;
		}
	}

	public void game_over_render()
	{
		batch.begin();
		batch.draw(tBackground, 0, 0);
		batch.end();
	}

	public void title_render()
	{
		batch.begin();
		batch.draw(tBackground, 0, 0);
		batch.end();
	}

	public void goblin_fight_render()
	{
		batch.begin();
		batch.draw(tBackground, 0, 0);
		batch.end();
	}
	public void skeleton_fight_render()
	{
		batch.begin();
		batch.draw(tBackground, 0, 0);
		batch.end();
	}
	public void minotaur_fight_render()
	{
		batch.begin();
		batch.draw(tBackground, 0, 0);
		batch.end();
	}
	public void main_game_create()
	{
		TextureRegion[][] tmp = TextureRegion.split(tWalkingCharacter,
				tWalkingCharacter.getWidth() / 3,
				tWalkingCharacter.getHeight() / 4);

		TextureRegion[] walkLeft = new TextureRegion[3];
		TextureRegion[] walkRight = new TextureRegion[3];
		TextureRegion[] walkUp = new TextureRegion[3];
		TextureRegion[] walkDown = new TextureRegion[3];

		int index = 0;
		for (int j = 0; j < 3; j++) {
			walkLeft[index++] = tmp[0][j];
		}
		runningLeftAnimation = new Animation<TextureRegion>(FRAME_TIME, walkLeft);

		index = 0;
		for (int j = 0; j < 3; j++) {
			walkRight[index++] = tmp[1][j];
		}
		runningRightAnimation = new Animation<TextureRegion>(FRAME_TIME, walkRight);

		index = 0;
		for (int j = 0; j < 3; j++) {
			walkUp[index++] = tmp[2][j];
		}
		runningUpAnimation = new Animation<TextureRegion>(FRAME_TIME, walkUp);

		index = 0;
		for (int j = 0; j < 3; j++) {
			walkDown[index++] = tmp[3][j];
		}
		runningDownAnimation = new Animation<TextureRegion>(FRAME_TIME, walkDown);

		idleAnimation = new Animation<TextureRegion>(FRAME_TIME, tmp[3][1]);

		posX = 0;
		posY = 300;
	}
	public void main_game_render()
	{
		this.move();
		elapsed_time += Gdx.graphics.getDeltaTime();

		if ( walking )
		{
			switch (d)
			{
				case up:
					currentFrame = runningUpAnimation.getKeyFrame(elapsed_time, true);
					break;
				case down:
					currentFrame = runningDownAnimation.getKeyFrame(elapsed_time, true);
					break;
				case left:
					currentFrame = runningLeftAnimation.getKeyFrame(elapsed_time, true);
					break;
				case right:
					currentFrame = runningRightAnimation.getKeyFrame(elapsed_time, true);
					break;
			}
		}
		else
		{
			currentFrame = idleAnimation.getKeyFrame(elapsed_time, true);
		}

		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(tBackground, 0, 0);
		batch.draw(currentFrame, posX, posY);
		batch.end();

		if ( posX >= 300 && !golin_fight )
		{
			currentScreen = Screen.GOBLIN_FIGHT;
		}

		if ( posX >= 600 && !skeleton_fight )
		{
			currentScreen = Screen.SKELETON_FIGHT;
		}

		if ( posX >= 1000 && !minotaur_fight )
		{
			currentScreen = Screen.MINOTAUR_FIGHT;
		}
	}
	@Override
	public void dispose () {
		batch.dispose();
		tBackground.dispose();
	}

	private void move()
	{
		if ( Gdx.input.isKeyPressed(Input.Keys.W) )
		{
			posY += 3;
			d = dir.up;
		}
		if ( Gdx.input.isKeyPressed(Input.Keys.A) )
		{
			posX -= 3;
			d = dir.left;
		}
		if ( Gdx.input.isKeyPressed(Input.Keys.S) )
		{
			posY -= 3;
			d = dir.down;
		}
		if ( Gdx.input.isKeyPressed(Input.Keys.D) )
		{
			posX += 3;
			d = dir.right;
		}

        walking = Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D);
	}
}
