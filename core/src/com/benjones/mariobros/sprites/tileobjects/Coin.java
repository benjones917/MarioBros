package com.benjones.mariobros.sprites.tileobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.benjones.mariobros.MarioBros;
import com.benjones.mariobros.scenes.Hud;
import com.benjones.mariobros.screens.PlayScreen;
import com.benjones.mariobros.sprites.Mario;
import com.benjones.mariobros.sprites.items.ItemDef;
import com.benjones.mariobros.sprites.items.Mushroom;

public class Coin extends InteractiveTileObject {
	
	private static TiledMapTileSet tileSet;
	private final int BLANK_COIN = 28;

	public Coin(PlayScreen screen, MapObject object) {
		super(screen, object);
		tileSet = map.getTileSets().getTileSet("tileset_gutter");
		fixture.setUserData(this);
		setCategoryFilter(MarioBros.COIN_BIT);
	}

	@Override
	public void onHeadHit(Mario mario) {
		if(getCell().getTile().getId() == BLANK_COIN) {
			MarioBros.manager.get("audio/sounds/bump.wav", Sound.class).play();
		} else {
			if(object.getProperties().containsKey("mushroom")) {
				MarioBros.manager.get("audio/sounds/powerup_spawn.wav", Sound.class).play();
				screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16 / MarioBros.PPM),
						Mushroom.class)); 
			} else {
				MarioBros.manager.get("audio/sounds/coin.wav", Sound.class).play();
			}
		}
		getCell().setTile(tileSet.getTile(BLANK_COIN));
		Hud.addScore(100);
	}

}
