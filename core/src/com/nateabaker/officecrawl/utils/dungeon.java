package com.nateabaker.officecrawl.utils;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class Dungeon {

	private int xsize = 0;
	private int ysize = 0;

	private int objects = 0;

	private int[] dungeon_map = {};

	private long oldseed = 0;

	// a list over tile types we're using
	final private int VOID_1 = 1;
	final private int VOID_2 = 2;
	final private int TOP_LEFT_INSIDE = 3;
	final private int TOP_RIGHT_INSIDE = 4;
	final private int BOTTOM_LEFT_INSIDE = 5;
	final private int BOTTOM_RIGHT_INSIDE = 6;
	final private int TOP_RIGHT_OUTSIDE = 7;
	final private int TOP_LEFT_OUTSIDE = 8;
	final private int BOTTOM_LEFT_OUTSIDE = 9;
	final private int BOTTOM_RIGHT_OUTSIDE = 10;

	final private int LEFT_WALL_1 = 11;
	final private int LEFT_WALL_2 = 12;
	final private int LEFT_WALL_3 = 13;
	final private int LEFT_WALL_4 = 14;
	final private int LEFT_WALL_5 = 15;
	final private int LEFT_WALL_6 = 16;
	final private int LEFT_WALL_7 = 17;
	final private int LEFT_WALL_8 = 18;
	final private int LEFT_WALL_9 = 19;
	final private int LEFT_WALL_10 = 20;

	final private int RIGHT_WALL_1 = 21;
	final private int RIGHT_WALL_2 = 22;
	final private int RIGHT_WALL_3 = 23;
	final private int RIGHT_WALL_4 = 24;
	final private int RIGHT_WALL_5 = 25;
	final private int RIGHT_WALL_6 = 26;
	final private int RIGHT_WALL_7 = 27;
	final private int RIGHT_WALL_8 = 28;
	final private int RIGHT_WALL_9 = 29;
	final private int RIGHT_WALL_10 = 30;

	final private int TOP_WALL_1 = 31;
	final private int TOP_WALL_2 = 32;
	final private int TOP_WALL_3 = 33;
	final private int TOP_WALL_4 = 34;
	final private int TOP_WALL_5 = 35;
	final private int TOP_WALL_6 = 36;
	final private int TOP_WALL_7 = 37;
	final private int TOP_WALL_8 = 38;
	final private int TOP_WALL_9 = 39;
	final private int TOP_WALL_10 = 40;

	final private int BOTTOM_WALL_1 = 41;
	final private int BOTTOM_WALL_2 = 42;
	final private int BOTTOM_WALL_3 = 43;
	final private int BOTTOM_WALL_4 = 54;
	final private int BOTTOM_WALL_5 = 45;
	final private int BOTTOM_WALL_6 = 46;
	final private int BOTTOM_WALL_7 = 47;
	final private int BOTTOM_WALL_8 = 48;
	final private int BOTTOM_WALL_9 = 49;
	final private int BOTTOM_WALL_10 = 50;

	final private int FLOOR_1_1 = 51;
	final private int FLOOR_1_2 = 52;
	final private int FLOOR_1_3 = 53;
	final private int FLOOR_1_4 = 54;
	final private int FLOOR_1_5 = 55;
	final private int FLOOR_1_6 = 56;
	final private int FLOOR_1_7 = 57;
	final private int FLOOR_1_8 = 58;
	final private int FLOOR_1_9 = 59;
	final private int FLOOR_1_10 = 60;

	final private int FLOOR_2_1 = 61;
	final private int FLOOR_2_2 = 62;
	final private int FLOOR_2_3 = 63;
	final private int FLOOR_2_4 = 64;
	final private int FLOOR_2_5 = 65;
	final private int FLOOR_2_6 = 66;
	final private int FLOOR_2_7 = 67;
	final private int FLOOR_2_8 = 68;
	final private int FLOOR_2_9 = 69;
	final private int FLOOR_2_10 = 70;

	final private int FLOOR_3_1 = 71;
	final private int FLOOR_3_2 = 72;
	final private int FLOOR_3_3 = 73;
	final private int FLOOR_3_4 = 74;
	final private int FLOOR_3_5 = 75;
	final private int FLOOR_3_6 = 76;
	final private int FLOOR_3_7 = 77;
	final private int FLOOR_3_8 = 78;
	final private int FLOOR_3_9 = 79;
	final private int FLOOR_3_10 = 80;

	final private int FLOOR_4_1 = 61;
	final private int FLOOR_4_2 = 62;
	final private int FLOOR_4_3 = 63;
	final private int FLOOR_4_4 = 64;
	final private int FLOOR_4_5 = 65;
	final private int FLOOR_4_6 = 66;
	final private int FLOOR_4_7 = 67;
	final private int FLOOR_4_8 = 68;
	final private int FLOOR_4_9 = 69;
	final private int FLOOR_4_10 = 70;

	final private int DOOR = 100;

	private void setCell(int x, int y, int celltype) {
		dungeon_map[x + xsize * y] = celltype;
	}

	private int getCell(int x, int y) {
		return dungeon_map[x + xsize * y];
	}

	private boolean isFloor(int x, int y) {
		if (getCell(x, y) >= 51 && getCell(x, y) <= 70)
			return true;
		else
			return false;
	}

	private boolean isWall(int x, int y) {
		if (getCell(x, y) > 2 && getCell(x, y) < 51)
			return true;
		else
			return false;
	}

	public int[] getDungeon() {
		return dungeon_map;
	}

	private int getRand(int min, int max) {
		long seed = System.currentTimeMillis() + oldseed;
		oldseed = seed;

		Random randomizer = new Random(seed);
		int n = max - min + 1;
		int i = randomizer.nextInt(n);
		if (i < 0)
			i = -i;
		return min + i;
	}
	
	private int getRightWall(){
		switch(MathUtils.random(0, 99)){
			case 0:
				return RIGHT_WALL_2;
			case 1:
				return RIGHT_WALL_3;
			case 2:
				return RIGHT_WALL_4;
			case 3:
				return RIGHT_WALL_5;
			case 4:
				return RIGHT_WALL_6;
			case 5:
				return RIGHT_WALL_7;
			case 6:
				return RIGHT_WALL_8;
			case 7:
				return RIGHT_WALL_9;
			case 8:
				return RIGHT_WALL_10;
			default:
				return RIGHT_WALL_1;
		}
	}

	void createDungeon(int inx, int iny, int inobj) {

		if (inobj < 1)
			objects = 10;
		else
			objects = inobj;

		if (inx < 3)
			xsize = 3;
		else
			xsize = inx;

		if (iny < 3)
			ysize = 3;
		else
			ysize = iny;

		dungeon_map = new int[xsize * ysize];

		for (int y = 0; y < ysize; y++) {
			for (int x = 0; x < xsize; x++) {
				if (y == 0)
					setCell(x, y, VOID_2);
				else if (y == ysize - 1)
					setCell(x, y, VOID_2);
				else if (x == 0)
					setCell(x, y, VOID_2);
				else if (x == xsize - 1)
					setCell(x, y, VOID_2);
				else
					setCell(x, y, VOID_1);
			}
		}

		makeRoom(xsize / 2, ysize / 2, 8, 6, MathUtils.random(0, 3));

		int currentFeatures = 1;

		for (int countingTries = 0; countingTries < 1000; countingTries++) {
			if (currentFeatures == objects) {
				break;
			}

			int newx = 0;
			int xmod = 0;
			int newy = 0;
			int ymod = 0;
			int validTile = -1;

			for (int testing = 0; testing < 1000; testing++) {
				newx = getRand(1, xsize - 1);
				newy = getRand(1, ysize - 1);
				validTile = -1;

				if (isWall(newx, newy)) {
					if (isFloor(newx, newy + 1)) {
						validTile = 0; //
						xmod = 0;
						ymod = -1;
					} else if (isFloor(newx - 1, newy)) {
						validTile = 1; //
						xmod = +1;
						ymod = 0;
					} else if (isFloor(newx, newy - 1)) {
						validTile = 2; //
						xmod = 0;
						ymod = +1;
					} else if (isFloor(newx + 1, newy)) {
						validTile = 3; //
						xmod = -1;
						ymod = 0;
					}
					System.out.println(""+validTile);
					if (validTile > -1) {
						if (getCell(newx, newy+1) == DOOR) //north
							validTile = -1;
						else if (getCell(newx-1, newy) == DOOR)//east
							validTile = -1;
						else if (getCell(newx, newy-1) == DOOR)//south
							validTile = -1;
						else if (getCell(newx+1, newy) == DOOR)//west
							validTile = -1;
					}
					
					if (validTile > -1)
						break;
				}
			}

			if (validTile > -1) {
				if (makeRoom((newx + xmod), (newy + ymod), MathUtils.random(6, 15), MathUtils.random(6, 15),
						validTile)) {
					currentFeatures++; // add to our quota

					switch (validTile) {
					case 0://north
						setCell(newx, newy, DOOR);
						setCell(newx, newy - 1, DOOR);
						if (getCell(newx - 1, newy) == TOP_LEFT_INSIDE)
							setCell(newx - 1, newy, MathUtils.random(LEFT_WALL_1, LEFT_WALL_10));
						else
							setCell(newx - 1, newy, BOTTOM_RIGHT_OUTSIDE);

						if (getCell(newx + 1, newy) == TOP_RIGHT_INSIDE)
							setCell(newx + 1, newy, MathUtils.random(RIGHT_WALL_1, RIGHT_WALL_10));
						else
							setCell(newx + 1, newy, BOTTOM_LEFT_OUTSIDE);
						//Other side  
						if (getCell(newx - 1, newy-1) == BOTTOM_LEFT_INSIDE)
							setCell(newx - 1, newy-1, MathUtils.random(LEFT_WALL_1, LEFT_WALL_10));
						else
							setCell(newx - 1, newy-1, TOP_LEFT_OUTSIDE);

						if (getCell(newx + 1, newy-1) == BOTTOM_RIGHT_INSIDE)
							setCell(newx + 1, newy-1, MathUtils.random(RIGHT_WALL_1, RIGHT_WALL_10));
						else
							setCell(newx + 1, newy-1, TOP_RIGHT_OUTSIDE);
						break;
					case 1://east
						setCell(newx, newy,DOOR);
						setCell(newx+1, newy,DOOR);
						if (getCell(newx, newy - 1) == TOP_RIGHT_INSIDE)
							setCell(newx, newy - 1, MathUtils.random(TOP_WALL_1, TOP_WALL_10));
						else
							setCell(newx, newy - 1, BOTTOM_LEFT_OUTSIDE);
						
						if (getCell(newx, newy + 1) == BOTTOM_RIGHT_INSIDE)
							setCell(newx, newy + 1, MathUtils.random(BOTTOM_WALL_1, BOTTOM_WALL_10));
						else
							setCell(newx, newy + 1, TOP_RIGHT_OUTSIDE);
						//Other side
						if (getCell(newx + 1, newy - 1) == TOP_LEFT_INSIDE)
							setCell(newx + 1, newy - 1, MathUtils.random(TOP_WALL_1, TOP_WALL_10));
						else
							setCell(newx + 1, newy - 1, BOTTOM_RIGHT_OUTSIDE);
						
						if (getCell(newx + 1, newy + 1) == BOTTOM_LEFT_INSIDE)
							setCell(newx + 1, newy + 1, MathUtils.random(BOTTOM_WALL_1, BOTTOM_WALL_10));
						else
							setCell(newx + 1, newy + 1, TOP_LEFT_OUTSIDE);

						break;
					case 2:
						setCell(newx, newy, DOOR);
						break;
					case 3:
						setCell(newx, newy, DOOR);
						break;
					}
				}
			}
		}
	}

	private boolean makeRoom(int x, int y, int xlength, int ylength, int direction) {
		int xlen = getRand(6, xlength);
		int ylen = getRand(6, ylength);

		int dir = 0;
		if (direction > 0 && direction < 4)
			dir = direction;

		switch (dir) {
		case 0: // north
			for (int ytemp = y; ytemp > (y - ylen); ytemp--) {
				if (ytemp < 0 || ytemp > ysize)
					return false;
				for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
					if (xtemp < 0 || xtemp > xsize)
						return false;
					if (getCell(xtemp, ytemp) != VOID_1)
						return false; // no space left...
				}
			}

			for (int ytemp = y; ytemp > (y - ylen); ytemp--) {
				for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
					if (xtemp == (x - xlen / 2)) {
						setCell(xtemp, ytemp, MathUtils.random(LEFT_WALL_1, LEFT_WALL_10));
						if (ytemp == (y - (ylen - 1)))
							setCell(xtemp, ytemp, TOP_LEFT_INSIDE);
						if (ytemp == y)
							setCell(xtemp, ytemp, BOTTOM_LEFT_INSIDE);
					} else if (xtemp == (x + (xlen - 1) / 2)) {
						setCell(xtemp, ytemp, MathUtils.random(RIGHT_WALL_1, RIGHT_WALL_10));
						if (ytemp == (y - (ylen - 1)))
							setCell(xtemp, ytemp, TOP_RIGHT_INSIDE);
						if (ytemp == y)
							setCell(xtemp, ytemp, BOTTOM_RIGHT_INSIDE);
					} else if (ytemp == y)
						setCell(xtemp, ytemp, MathUtils.random(BOTTOM_WALL_1, BOTTOM_WALL_10));
					else if (ytemp == (y - ylen + 1))
						setCell(xtemp, ytemp, MathUtils.random(TOP_WALL_1, TOP_WALL_10));
					else
						setCell(xtemp, ytemp, MathUtils.random(FLOOR_1_1, FLOOR_1_10));
				}
			}

			break;

		case 1: // east
			for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
				if (ytemp < 0 || ytemp > ysize)
					return false;
				for (int xtemp = x; xtemp < (x + xlen); xtemp++) {
					if (xtemp < 0 || xtemp > xsize)
						return false;
					if (getCell(xtemp, ytemp) != VOID_1)
						return false;
				}
			}

			for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
				for (int xtemp = x; xtemp < (x + xlen); xtemp++) {
					if (xtemp == x) {
						setCell(xtemp, ytemp, MathUtils.random(LEFT_WALL_1, LEFT_WALL_10));
						if (ytemp == y - ylen / 2)
							setCell(xtemp, ytemp, TOP_LEFT_INSIDE);
						if (ylen % 2 == 0) {
							if (ytemp == y + (ylen / 2) - 1)
								setCell(xtemp, ytemp, BOTTOM_LEFT_INSIDE);
						} else {
							if (ytemp == y + (ylen / 2))
								setCell(xtemp, ytemp, BOTTOM_LEFT_INSIDE);
						}
					} else if (xtemp == (x + xlen - 1)) {
						setCell(xtemp, ytemp, MathUtils.random(RIGHT_WALL_1, RIGHT_WALL_10));
						if (ytemp == y - ylen / 2)
							setCell(xtemp, ytemp, TOP_RIGHT_INSIDE);
						if (ylen % 2 == 0) {
							if (ytemp == y + (ylen / 2) - 1)
								setCell(xtemp, ytemp, BOTTOM_RIGHT_INSIDE);
						} else {
							if (ytemp == y + (ylen / 2))
								setCell(xtemp, ytemp, BOTTOM_RIGHT_INSIDE);
						}
					} else if (ytemp == (y - ylen / 2))
						setCell(xtemp, ytemp, MathUtils.random(TOP_WALL_1, TOP_WALL_10));
					else if (ytemp == (y + (ylen - 1) / 2))
						setCell(xtemp, ytemp, MathUtils.random(BOTTOM_WALL_1, BOTTOM_WALL_10));
					else
						setCell(xtemp, ytemp, MathUtils.random(FLOOR_2_1, FLOOR_2_10));
				}
			}

			break;

		case 2: // south

			for (int ytemp = y; ytemp < (y + ylen); ytemp++) {
				if (ytemp < 0 || ytemp > ysize)
					return false;
				for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
					if (xtemp < 0 || xtemp > xsize)
						return false;
					if (getCell(xtemp, ytemp) != VOID_1)
						return false;
				}
			}

			for (int ytemp = y; ytemp < (y + ylen); ytemp++) {
				for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
					if (xtemp == (x - xlen / 2)) {
						setCell(xtemp, ytemp, MathUtils.random(LEFT_WALL_1, LEFT_WALL_10));
						if (ytemp == y)
							setCell(xtemp, ytemp, TOP_LEFT_INSIDE);
						if (ytemp == (y + (ylen - 1)))
							setCell(xtemp, ytemp, BOTTOM_LEFT_INSIDE);
					} else if (xtemp == (x + (xlen - 1) / 2)) {
						setCell(xtemp, ytemp, MathUtils.random(RIGHT_WALL_1, RIGHT_WALL_10));
						if (ytemp == y)
							setCell(xtemp, ytemp, TOP_RIGHT_INSIDE);
						if (ytemp == (y + (ylen - 1)))
							setCell(xtemp, ytemp, BOTTOM_RIGHT_INSIDE);
					} else if (ytemp == y)
						setCell(xtemp, ytemp, MathUtils.random(TOP_WALL_1, TOP_WALL_10));
					else if (ytemp == (y + ylen - 1))
						setCell(xtemp, ytemp, MathUtils.random(BOTTOM_WALL_1, BOTTOM_WALL_10));
					else
						setCell(xtemp, ytemp, MathUtils.random(FLOOR_3_1, FLOOR_3_10));
				}
			}

			break;

		case 3: // west

			for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
				if (ytemp < 0 || ytemp > ysize)
					return false;
				for (int xtemp = x; xtemp > (x - xlen); xtemp--) {
					if (xtemp < 0 || xtemp > xsize)
						return false;
					if (getCell(xtemp, ytemp) != VOID_1)
						return false;
				}
			}

			for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
				for (int xtemp = x; xtemp > (x - xlen); xtemp--) {
					if (xtemp == x) {
						setCell(xtemp, ytemp, MathUtils.random(RIGHT_WALL_1, RIGHT_WALL_10));
						if (ytemp == y - ylen / 2)
							setCell(xtemp, ytemp, TOP_RIGHT_INSIDE);
						if (ylen % 2 == 0) {
							if (ytemp == y + (ylen / 2) - 1)
								setCell(xtemp, ytemp, BOTTOM_RIGHT_INSIDE);
						} else {
							if (ytemp == y + (ylen / 2))
								setCell(xtemp, ytemp, BOTTOM_RIGHT_INSIDE);
						}
					} else if (xtemp == (x - xlen + 1)) {
						setCell(xtemp, ytemp, MathUtils.random(LEFT_WALL_1, LEFT_WALL_10));
						if (ytemp == y - ylen / 2)
							setCell(xtemp, ytemp, TOP_LEFT_INSIDE);
						if (ylen % 2 == 0) {
							if (ytemp == y + (ylen / 2) - 1)
								setCell(xtemp, ytemp, BOTTOM_LEFT_INSIDE);
						} else {
							if (ytemp == y + (ylen / 2))
								setCell(xtemp, ytemp, BOTTOM_LEFT_INSIDE);
						}
					} else if (ytemp == (y - ylen / 2))
						setCell(xtemp, ytemp, MathUtils.random(TOP_WALL_1, TOP_WALL_10));
					else if (ytemp == (y + (ylen - 1) / 2))
						setCell(xtemp, ytemp, MathUtils.random(BOTTOM_WALL_1, BOTTOM_WALL_10));
					else
						setCell(xtemp, ytemp, MathUtils.random(FLOOR_4_1, FLOOR_4_10));
				}
			}

			break;
		}
		return true;
	}
}
