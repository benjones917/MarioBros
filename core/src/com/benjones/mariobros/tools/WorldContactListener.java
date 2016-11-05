package com.benjones.mariobros.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.benjones.mariobros.MarioBros;
import com.benjones.mariobros.sprites.Enemy;
import com.benjones.mariobros.sprites.InteractiveTileObject;

public class WorldContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		int cdef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
		
//		if (fixA.getUserData() == "head" || fixB.getUserData() == "head") {
//			Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
//			Fixture object = head == fixA ? fixB : fixA;
//			
//			if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
//				((InteractiveTileObject) object.getUserData()).onHeadHit();
//			}
//		}
		
		switch (cdef) {
			case MarioBros.MARIO_HEAD_BIT | MarioBros.BRICK_BIT:
				if(fixA.getUserData() == "head") 
					((InteractiveTileObject) fixB.getUserData()).onHeadHit();
				else
					((InteractiveTileObject) fixA.getUserData()).onHeadHit();
				break;
			case MarioBros.MARIO_HEAD_BIT | MarioBros.COIN_BIT:
				if(fixA.getUserData() == "head")
					((InteractiveTileObject) fixB.getUserData()).onHeadHit();
				else
					((InteractiveTileObject) fixA.getUserData()).onHeadHit();
				break;
			case MarioBros.ENEMY_HEAD_BIT | MarioBros.MARIO_BIT:
				if(fixA.getFilterData().categoryBits == MarioBros.ENEMY_HEAD_BIT)
					((Enemy)fixA.getUserData()).hitOnHead();
				else 
					((Enemy)fixB.getUserData()).hitOnHead();
				break;
			case MarioBros.ENEMY_BIT | MarioBros.OBJECT_BIT:
				if(fixA.getFilterData().categoryBits == MarioBros.ENEMY_BIT)
					((Enemy)fixA.getUserData()).reverseVelocity(true, false);
				else 
					((Enemy)fixB.getUserData()).reverseVelocity(true, false);
				break;
			case MarioBros.ENEMY_BIT | MarioBros.ENEMY_BIT:
				((Enemy)fixA.getUserData()).reverseVelocity(true, false);
				((Enemy)fixB.getUserData()).reverseVelocity(true, false);
				break;
			case MarioBros.MARIO_BIT | MarioBros.ENEMY_BIT:
				Gdx.app.log("Mario", "Died");
		}
		
	}

	@Override
	public void endContact(Contact contact) {
		
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
