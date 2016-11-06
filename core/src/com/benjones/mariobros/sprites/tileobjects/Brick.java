package com.benjones.mariobros.sprites.tileobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.benjones.mariobros.MarioBros;
import com.benjones.mariobros.scenes.Hud;
import com.benjones.mariobros.screens.PlayScreen;
import com.benjones.mariobros.sprites.Mario;

public class Brick extends InteractiveTileObject {

	public Brick(PlayScreen screen, MapObject object) {
		super(screen, object);
		fixture.setUserData(this);
		setCategoryFilter(MarioBros.BRICK_BIT);
	}

	@Override
	public void onHeadHit(Mario mario) {
		if(mario.isBig()) {
			setCategoryFilter(MarioBros.DESTROYED_BIT);
			getCell().setTile(null);
			Hud.addScore(200);
			MarioBros.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
		} else {
			MarioBros.manager.get("audio/sounds/bump.wav", Sound.class).play();
		}
		
	}

}	
