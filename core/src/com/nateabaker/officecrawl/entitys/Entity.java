package com.nateabaker.officecrawl.entitys;

import com.badlogic.gdx.math.Vector2;

public interface Entity {
	public Vector2 getPosition();
	public void update(float delta);
	public void disposeable();
}
