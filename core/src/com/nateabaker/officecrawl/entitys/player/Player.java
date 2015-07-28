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
	private Vector2 position;
	private Body body;
	private World world;
	private float speed = 5;
	private float rotation = 0;
	private Vector2 zero = new Vector2();
	private String sprite = "entitys'player.png";

	public Player(Vector2 startPosition, World world) {
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
		fDef.density = 0.25f;
		fDef.friction = 0.5f;
		fDef.restitution = 0.01f;

		Fixture fixture = body.createFixture(fDef);
	}
	@Override
	public void update(float delta) {

	}

	public Body getBody() {
		return body;
	}

	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	public float getSpeed() {
		return speed;
	}

	@Override
	public void disposeable() {
		// TODO Auto-generated method stub

	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}


}
