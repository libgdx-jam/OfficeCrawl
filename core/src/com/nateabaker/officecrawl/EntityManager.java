package com.nateabaker.officecrawl;

import com.badlogic.gdx.utils.Array;
import com.nateabaker.officecrawl.entitys.Entity;

public class EntityManager {
	private Array<Entity> eititys = new Array<Entity>();
	
	public Array<Entity> getEntitys(){
		return eititys;
	}

}
