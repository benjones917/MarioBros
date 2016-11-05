package com.benjones.mariobros.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.benjones.mariobros.MarioBros;
import com.benjones.mariobros.screens.PlayScreen;
import com.benjones.mariobros.sprites.Brick;
import com.benjones.mariobros.sprites.Coin;
import com.benjones.mariobros.sprites.Goomba;

public class B2WorldCreator {
	
	private Array<Goomba> goombas;

	public B2WorldCreator(PlayScreen screen) {
		World world = screen.getWorld();
		TiledMap map = screen.getMap();
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;

		// floor
		for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();

			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM, (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

			body = world.createBody(bdef);

			shape.setAsBox(rect.getWidth() / 2 / MarioBros.PPM, rect.getHeight() / 2 / MarioBros.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}

		// pipes
		for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();

			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM, (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

			body = world.createBody(bdef);

			shape.setAsBox(rect.getWidth() / 2 / MarioBros.PPM, rect.getHeight() / 2 / MarioBros.PPM);
			fdef.shape = shape;
			fdef.filter.categoryBits = MarioBros.OBJECT_BIT;
			body.createFixture(fdef);
		}

		// bricks
		for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Brick(screen, rect);
		}

		// coins
		for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Coin(screen, rect);
		}
		
		goombas = new Array<Goomba>();
		for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			goombas.add(new Goomba(screen, rect.getX()/MarioBros.PPM, rect.getY()/MarioBros.PPM));
		}
	}
	
	public Array<Goomba> getGoombas() {
		return goombas;
	}
}
