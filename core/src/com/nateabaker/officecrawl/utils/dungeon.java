package com.nateabaker.officecrawl.utils;

public class Dungeon {
	private int[] tileLayer;
	private boolean[] collisionLayer;
	private int[] entityLayer;
	
	int xSize;
	int ySize;
	int objects;
	
	public Dungeon(int xSize, int ySize, int objects){
		if (objects < 1)
			this.objects = 10;
		else
			this.objects = objects;

		if (xSize < 3)
			this.xSize = 3;
		else
			this.xSize = xSize;

		if (ySize < 3)
			this.ySize = 3;
		else
			this.ySize = ySize;
		
		tileLayer = new int[xSize * ySize];
		collisionLayer = new boolean[xSize * ySize];
		entityLayer = new int[xSize * ySize];
		
	}
	public void setTile(int x, int y, int celltype) {
		tileLayer[x + xSize * y] = celltype;
	}

	public int getTile(int x, int y) {
		return tileLayer[x + xSize * y];
	}

	public boolean isFloor(int x, int y) {
		if (getTile(x, y) >= 51 && getTile(x, y) <= 70)
			return true;
		else
			return false;
	}

	public boolean isWall(int x, int y) {
		if (getTile(x, y) > 2 && getTile(x, y) < 51)
			return true;
		else
			return false;
	}
	
	public void setCollision(int x, int y, boolean i){
		collisionLayer[x + xSize * y] = i;
	}
	
	public int getxSize() {
		return xSize;
	}
	public int getySize() {
		return ySize;
	}
	public int getObjects() {
		return objects;
	}
	public int[] getTileLayer() {
		return tileLayer;
	}
	public boolean[] getCollisionLayer() {
		return collisionLayer;
	}
	public int[] getEntityLayer() {
		return entityLayer;
	}

}
