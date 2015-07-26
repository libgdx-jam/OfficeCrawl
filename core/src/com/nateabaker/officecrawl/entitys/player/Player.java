package com.nateabaker.officecrawl.entitys.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.nateabaker.officecrawl.entitys.Entity;

public class Player implements Entity {
	Vector2 position;
	Body body;
	World world;
	
	public Player(Vector2 startPosition, World world){
        this.world = world;
        this.position = startPosition;
		BodyDef def = new BodyDef();
		def.position.set(startPosition);
		def.type = BodyType.DynamicBody; 
		def.angularDamping = 1.0f;
		
		body = world.createBody(def);
		
		CircleShape cir = new CircleShape();
		cir.setRadius(0.4f);
		
		FixtureDef fDef = new FixtureDef();
		fDef.shape = cir;
		fDef.density = 0.5f;
		fDef.friction = 0.5f;
		fDef.restitution=0.6f;

		Fixture fixture = body.createFixture(fDef);
	}
	public Body getBody(){
		return body;
	}
	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(float delta) {
	
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			body.applyForce(new Vector2(0,1), body.getWorldCenter(), true);
		}
		
	}

}
