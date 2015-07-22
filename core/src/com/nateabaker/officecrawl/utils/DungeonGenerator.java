package com.nateabaker.officecrawl.utils;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class DungeonGenerator {

	private int xsize = 0;
	private int ysize = 0;

	private int objects = 0;
	
	TileSet t = new TileSet();

	private int[] dungeon_map = {};

	private long oldseed = 0;

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
					setCell(x, y, t.VOID_2);
				else if (y == ysize - 1)
					setCell(x, y, t.VOID_2);
				else if (x == 0)
					setCell(x, y, t.VOID_2);
				else if (x == xsize - 1)
					setCell(x, y, t.VOID_2);
				else
					setCell(x, y, t.VOID_1);
			}
		}

		makeRoom(xsize / 2, ysize / 2, 8, 6, 0);

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
					System.out.println("A: "+validTile);
					if (validTile > -1) {
						if (getCell(newx, newy+1) == t.DOOR) //north
							validTile = -1;
						else if (getCell(newx-1, newy) == t.DOOR)//east
							validTile = -1;
						else if (getCell(newx, newy-1) == t.DOOR)//south
							validTile = -1;
						else if (getCell(newx+1, newy) == t.DOOR)//west
							validTile = -1;
					}
					System.out.println("B: "+validTile);
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
						setCell(newx, newy, t.DOOR);
						setCell(newx, newy - 1, t.DOOR);
						if (getCell(newx - 1, newy) == t.TOP_LEFT_INSIDE)
							setCell(newx - 1, newy, t.getLeftWall());
						else
							setCell(newx - 1, newy, t.BOTTOM_RIGHT_OUTSIDE);

						if (getCell(newx + 1, newy) == t.TOP_RIGHT_INSIDE)
							setCell(newx + 1, newy, t.getRightWall());
						else
							setCell(newx + 1, newy, t.BOTTOM_LEFT_OUTSIDE);
						//Other side  
						if (getCell(newx - 1, newy-1) == t.BOTTOM_LEFT_INSIDE)
							setCell(newx - 1, newy-1, t.getLeftWall());
						else
							setCell(newx - 1, newy-1, t.TOP_LEFT_OUTSIDE);

						if (getCell(newx + 1, newy-1) == t.BOTTOM_RIGHT_INSIDE)
							setCell(newx + 1, newy-1, t.getRightWall());
						else
							setCell(newx + 1, newy-1, t.TOP_RIGHT_OUTSIDE);
						break;
					case 1://east
						setCell(newx, newy,t.DOOR);
						setCell(newx+1, newy,t.DOOR);
						if (getCell(newx, newy - 1) == t.TOP_RIGHT_INSIDE)
							setCell(newx, newy - 1, t.getTopWall());
						else
							setCell(newx, newy - 1, t.BOTTOM_LEFT_OUTSIDE);
						
						if (getCell(newx, newy + 1) == t.BOTTOM_RIGHT_INSIDE)
							setCell(newx, newy + 1, t.getBottomWall());
						else
							setCell(newx, newy + 1, t.TOP_RIGHT_OUTSIDE);
						//Other side
						if (getCell(newx + 1, newy - 1) == t.TOP_LEFT_INSIDE)
							setCell(newx + 1, newy - 1, t.getTopWall());
						else
							setCell(newx + 1, newy - 1, t.BOTTOM_RIGHT_OUTSIDE);
						
						if (getCell(newx + 1, newy + 1) == t.BOTTOM_LEFT_INSIDE)
							setCell(newx + 1, newy + 1, t.getBottomWall());
						else
							setCell(newx + 1, newy + 1, t.TOP_LEFT_OUTSIDE);

						break;
					case 2:
						setCell(newx, newy, t.DOOR);
						setCell(newx, newy + 1, t.DOOR);
						if (getCell(newx + 1, newy) == t.BOTTOM_RIGHT_INSIDE)//RIGHT
							setCell(newx + 1, newy, t.getRightWall());
						else
							setCell(newx + 1, newy, t.TOP_RIGHT_OUTSIDE);

						if (getCell(newx - 1, newy) == t.BOTTOM_LEFT_INSIDE)//LEFT
							setCell(newx - 1, newy,t.getLeftWall());
						else
							setCell(newx - 1, newy, t.TOP_LEFT_OUTSIDE);
						//Other side  
						if (getCell(newx - 1, newy+1) == t.TOP_RIGHT_INSIDE)//RIGHT
							setCell(newx - 1, newy+1, t.getRightWall());
						else
							setCell(newx - 1, newy+1,t.BOTTOM_RIGHT_OUTSIDE );
						
						if (getCell(newx + 1, newy+1) == t.TOP_LEFT_INSIDE)//LEFT
							setCell(newx + 1, newy+1, t.getLeftWall());
						else
							setCell(newx + 1, newy+1, t.BOTTOM_LEFT_OUTSIDE);
						break;
					case 3://west
						setCell(newx, newy,t.DOOR);
						setCell(newx-1, newy,t.DOOR);
						if (getCell(newx, newy + 1) == t.BOTTOM_LEFT_INSIDE)
							setCell(newx, newy + 1, t.getBottomWall());
						else
							setCell(newx, newy + 1, t.TOP_LEFT_OUTSIDE);
						
						if (getCell(newx, newy - 1) == t.TOP_LEFT_INSIDE)
							setCell(newx, newy - 1, t.getTopWall());
						else
							setCell(newx, newy - 1, t.BOTTOM_RIGHT_OUTSIDE);
						//Other side
						if (getCell(newx - 1, newy + 1) == t.TOP_RIGHT_INSIDE)
							setCell(newx - 1, newy + 1, t.getTopWall());
						else
							setCell(newx - 1, newy + 1, t.TOP_RIGHT_OUTSIDE);
						
						if (getCell(newx - 1, newy - 1) == t.BOTTOM_RIGHT_INSIDE)
							setCell(newx - 1, newy - 1, t.getBottomWall());
						else
							setCell(newx - 1, newy - 1, t.BOTTOM_LEFT_OUTSIDE);

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
					if (getCell(xtemp, ytemp) != t.VOID_1)
						return false;
				}
			}

			for (int ytemp = y; ytemp > (y - ylen); ytemp--) {
				for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
					if (xtemp == (x - xlen / 2)) {
						setCell(xtemp, ytemp, MathUtils.random(t.LEFT_WALL_1, t.LEFT_WALL_10));
						if (ytemp == (y - (ylen - 1)))
							setCell(xtemp, ytemp, t.TOP_LEFT_INSIDE);
						if (ytemp == y)
							setCell(xtemp, ytemp, t.BOTTOM_LEFT_INSIDE);
					} else if (xtemp == (x + (xlen - 1) / 2)) {
						setCell(xtemp, ytemp, t.getRightWall());
						if (ytemp == (y - (ylen - 1)))
							setCell(xtemp, ytemp, t.TOP_RIGHT_INSIDE);
						if (ytemp == y)
							setCell(xtemp, ytemp, t.BOTTOM_RIGHT_INSIDE);
					} else if (ytemp == y)
						setCell(xtemp, ytemp, t.getBottomWall());
					else if (ytemp == (y - ylen + 1))
						setCell(xtemp, ytemp, t.getTopWall());
					else
						setCell(xtemp, ytemp, t.getFloor1Wall());
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
					if (getCell(xtemp, ytemp) != t.VOID_1)
						return false;
				}
			}

			for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
				for (int xtemp = x; xtemp < (x + xlen); xtemp++) {
					if (xtemp == x) {
						setCell(xtemp, ytemp, t.getLeftWall());
						if (ytemp == y - ylen / 2)
							setCell(xtemp, ytemp, t.TOP_LEFT_INSIDE);
						if (ylen % 2 == 0) {
							if (ytemp == y + (ylen / 2) - 1)
								setCell(xtemp, ytemp, t.BOTTOM_LEFT_INSIDE);
						} else {
							if (ytemp == y + (ylen / 2))
								setCell(xtemp, ytemp, t.BOTTOM_LEFT_INSIDE);
						}
					} else if (xtemp == (x + xlen - 1)) {
						setCell(xtemp, ytemp, t.getRightWall());
						if (ytemp == y - ylen / 2)
							setCell(xtemp, ytemp, t.TOP_RIGHT_INSIDE);
						if (ylen % 2 == 0) {
							if (ytemp == y + (ylen / 2) - 1)
								setCell(xtemp, ytemp, t.BOTTOM_RIGHT_INSIDE);
						} else {
							if (ytemp == y + (ylen / 2))
								setCell(xtemp, ytemp, t.BOTTOM_RIGHT_INSIDE);
						}
					} else if (ytemp == (y - ylen / 2))
						setCell(xtemp, ytemp, t.getTopWall());
					else if (ytemp == (y + (ylen - 1) / 2))
						setCell(xtemp, ytemp, t.getBottomWall());
					else
						setCell(xtemp, ytemp, t.getFloor2Wall());
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
					if (getCell(xtemp, ytemp) != t.VOID_1)
						return false;
				}
			}

			for (int ytemp = y; ytemp < (y + ylen); ytemp++) {
				for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
					if (xtemp == (x - xlen / 2)) {
						setCell(xtemp, ytemp, t.getLeftWall());
						if (ytemp == y)
							setCell(xtemp, ytemp, t.TOP_LEFT_INSIDE);
						if (ytemp == (y + (ylen - 1)))
							setCell(xtemp, ytemp, t.BOTTOM_LEFT_INSIDE);
					} else if (xtemp == (x + (xlen - 1) / 2)) {
						setCell(xtemp, ytemp, t.getRightWall());
						if (ytemp == y)
							setCell(xtemp, ytemp, t.TOP_RIGHT_INSIDE);
						if (ytemp == (y + (ylen - 1)))
							setCell(xtemp, ytemp, t.BOTTOM_RIGHT_INSIDE);
					} else if (ytemp == y)
						setCell(xtemp, ytemp, t.getTopWall());
					else if (ytemp == (y + ylen - 1))
						setCell(xtemp, ytemp, t.getBottomWall());
					else
						setCell(xtemp, ytemp, t.getFloor3Wall());
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
					if (getCell(xtemp, ytemp) != t.VOID_1)
						return false;
				}
			}

			for (int ytemp = (y - ylen / 2); ytemp < (y + (ylen + 1) / 2); ytemp++) {
				for (int xtemp = x; xtemp > (x - xlen); xtemp--) {
					if (xtemp == x) {
						setCell(xtemp, ytemp, t.getRightWall());
						if (ytemp == y - ylen / 2)
							setCell(xtemp, ytemp, t.TOP_RIGHT_INSIDE);
						if (ylen % 2 == 0) {
							if (ytemp == y + (ylen / 2) - 1)
								setCell(xtemp, ytemp, t.BOTTOM_RIGHT_INSIDE);
						} else {
							if (ytemp == y + (ylen / 2))
								setCell(xtemp, ytemp, t.BOTTOM_RIGHT_INSIDE);
						}
					} else if (xtemp == (x - xlen + 1)) {
						setCell(xtemp, ytemp, t.getLeftWall());
						if (ytemp == y - ylen / 2)
							setCell(xtemp, ytemp, t.TOP_LEFT_INSIDE);
						if (ylen % 2 == 0) {
							if (ytemp == y + (ylen / 2) - 1)
								setCell(xtemp, ytemp, t.BOTTOM_LEFT_INSIDE);
						} else {
							if (ytemp == y + (ylen / 2))
								setCell(xtemp, ytemp, t.BOTTOM_LEFT_INSIDE);
						}
					} else if (ytemp == (y - ylen / 2))
						setCell(xtemp, ytemp, t.getTopWall());
					else if (ytemp == (y + (ylen - 1) / 2))
						setCell(xtemp, ytemp, t.getBottomWall());
					else
						setCell(xtemp, ytemp, t.getFloor4Wall());
				}
			}

			break;
		}
		return true;
	}
}
