package com.nateabaker.officecrawl.utils;

public class Dungeon {
	private int[] tileLayer;
	private boolean[] collisionLayer;
	private int[] objectLayer;
	
	int xSize;
	int ySize;
	
	public Dungeon(int xSize, int YSize){
		
	}
	public void setCell(int x, int y, int celltype) {
		tileLayer[x + xSize * y] = celltype;
	}

	public int getCell(int x, int y) {
		return tileLayer[x + xSize * y];
	}

	public boolean isFloor(int x, int y) {
		if (getCell(x, y) >= 51 && getCell(x, y) <= 70)
			return true;
		else
			return false;
	}

	public boolean isWall(int x, int y) {
		if (getCell(x, y) > 2 && getCell(x, y) < 51)
			return true;
		else
			return false;
	}
}
