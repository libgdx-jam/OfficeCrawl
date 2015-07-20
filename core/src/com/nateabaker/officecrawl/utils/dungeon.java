package com.nateabaker.officecrawl.utils;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class Dungeon {

	// size of the map
	private int xsize = 0;
	private int ysize = 0;

	// number of "objects" to generate on the map
	private int objects = 0;

	// define the %chance to generate either a room or a corridor on the map
	// BTW, rooms are 1st priority so actually it's enough to just define the
	// chance of generating a room
	private int chanceRoom = 100;
	private int chanceCorridor = 0;

	// our map
	private int[] dungeon_map = {};

	// the old seed from the RNG is saved in this one
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
	
	final private int tileDoor = 100;
	
	// misc. messages to print
	private String msgXSize = "X size of dungeon: \t";
	private String msgYSize = "Y size of dungeon: \t";
	private String msgMaxObjects = "max # of objects: \t";
	private String msgNumObjects = "# of objects made: \t";
	private String msgHelp = "";
	private String msgDetailedHelp = "";

	private int roomList[][];
	private int currentRoom = 0;
	
	private int currentDirection = 0;
	
	// setting a tile's type
	private void setCell(int x, int y, int celltype) {
		dungeon_map[x + xsize * y] = celltype;
	}

	// returns the type of a tile
	private int getCell(int x, int y) {
		return dungeon_map[x + xsize * y];
	}
	
	private boolean isFloor(int x, int y){
		if(getCell(x, y) >= 51 && getCell(x, y) <= 70)
			return true;
		else
			return false;
	}
	private boolean isWall(int x, int y){
		if(getCell(x, y) > 2 && getCell(x, y) < 51)
			return true;
		else
			return false;
	}

	public int[] getDungeon() {
		return dungeon_map;
	}
	
	void createDungeon(int inx, int iny, int inobj) {
		/*******************************************************************************/
		// Here's the one generating the whole map
		if (inobj < 1)
			objects = 10;
		else
			objects = inobj;

		// Adjust the size of the map if it's too small
		if (inx < 3)
			xsize = 3;
		else
			xsize = inx;

		if (iny < 3)
			ysize = 3;
		else
			ysize = iny;

		//System.out.println(msgXSize + xsize);
		//System.out.println(msgYSize + ysize);
		//System.out.println(msgMaxObjects + objects);

		// redefine the map var, so it's adjusted to our new map size
		dungeon_map = new int[xsize * ysize];
		
		
		// start with making the "standard stuff" on the map
		for (int y = 0; y < ysize; y++) {
			for (int x = 0; x < xsize; x++) {
				// ie, making the borders of unwalkable walls
				if (y == 0)
					setCell(x, y, VOID_2);
				else if (y == ysize - 1)
					setCell(x, y, VOID_2);
				else if (x == 0)
					setCell(x, y, VOID_2);
				else if (x == xsize - 1)
					setCell(x, y, VOID_2);

				// and fill the rest with dirt
				else
					setCell(x, y, VOID_1);
			}
		}

		/*******************************************************************************
		 * 
		 * And now the code of the random-map-generation-algorithm begins!
		 * 
		 *******************************************************************************/

		// start with making a room in the middle, which we can start building
		// upon
			makeRoom(xsize / 2, ysize / 2, 8, 6, MathUtils.random(0,3));

		// keep count of the number of "objects" we've made
			int currentFeatures = 1; // +1 for the first room we just made

		// then we start the main loop
			for (int countingTries = 0; countingTries < 1000; countingTries++) {


			// check if we've reached our quota
				if (currentFeatures == objects) {
					break;
				}

			// start with a random wall
				int newx = 0;
				int xmod = 0;
				int newy = 0;
				int ymod = 0;
				int validTile = -1;

			// 1000 chances to find a suitable object (room or corridor)..
			// (yea, i know it's kinda ugly with a for-loop... -_-')

				for (int testing = 0; testing < 1000; testing++) {
					newx = getRand(1, xsize - 1);
					newy = getRand(1, ysize - 1);
					validTile = -1;

				if (isWall(newx, newy)) {
					// check if we can reach the place
					if (isFloor(newx, newy + 1)) {
						validTile = 0; //
						xmod = 0;
						ymod = -1;
					} else if (isFloor(newx - 1, newy)) {
						validTile = 1; //
						xmod = +1;
						ymod = 0;
					}

					else if (isFloor(newx, newy - 1)) {
						validTile = 2; //
						xmod = 0;
						ymod = +1;
					}

					else if (isFloor(newx + 1, newy)) {
						validTile = 3; //
						xmod = -1;
						ymod = 0;
					}

					// check that we haven't got another door nearby, so we
					// won't get alot of openings besides each other

					if (validTile > -1) {
						if (getCell(newx, newy+1) == tileDoor) // north
							validTile = -1;
						else if (getCell(newx, newy+1) == tileDoor) // east
							validTile = -1;
						else if (getCell(newx, newy+1) == tileDoor) // south
							validTile = -1;
						else if (getCell(newx, newy+1) == tileDoor) // west
							validTile = -1;
					}

					// if we can, jump out of the loop and continue with the
					// rest
					if (validTile > -1)
						break;
				}
			}

			if (validTile > -1) {

				// choose what to build now at our newly found place, and at
				// what direction
				int feature = getRand(0, 100);
				if (feature <= chanceRoom) { // a new room
					if (makeRoom((newx + xmod), (newy + ymod), MathUtils.random(6, 15), MathUtils.random(6, 15), validTile)) {
						currentFeatures++; // add to our quota

						switch(validTile){
						case 0:
							 setCell(newx, newy, tileDoor);
							 setCell(newx, newy-1, tileDoor);
							 if(getCell(newx+1, newy) == TOP_RIGHT_INSIDE)
								 setCell(newx+1, newy, MathUtils.random(RIGHT_WALL_1, RIGHT_WALL_10));
							 else
								 setCell(newx+1, newy, BOTTOM_LEFT_OUTSIDE);
							 
							 if(getCell(newx-1, newy) == TOP_RIGHT_INSIDE)
								 setCell(newx-1, newy, MathUtils.random(LEFT_WALL_1, LEFT_WALL_10));
							 else
								 setCell(newx-1, newy, BOTTOM_RIGHT_OUTSIDE);
							//OTHER SIDE
							 if(getCell(newx+1, newy-1) == BOTTOM_RIGHT_INSIDE)
								 setCell(newx+1, newy-1, MathUtils.random(RIGHT_WALL_1, RIGHT_WALL_10));
							 else
								 setCell(newx+1, newy-1, TOP_RIGHT_OUTSIDE);
							 
							 if(getCell(newx-1, newy-1) == BOTTOM_RIGHT_INSIDE)
								 setCell(newx-1, newy-1, MathUtils.random(LEFT_WALL_1, LEFT_WALL_10));
							 else
								 setCell(newx-1, newy-1, TOP_LEFT_OUTSIDE);
							break;
						case 1:
							 setCell(newx, newy, tileDoor);
							 setCell(newx+1, newy, tileDoor);
							break;							
						case 2:
							 setCell(newx, newy, tileDoor);
							 setCell(newx, newy+1, tileDoor);
							break;
						case 3:
							 setCell(newx, newy, tileDoor);
							 setCell(newx-1, newy, tileDoor);
							break;
						}


					}
				}

				else if (feature >= chanceRoom) { // new corridor
					if (makeCorridor((newx + xmod), (newy + ymod), 6, validTile)) {
						// same thing here, add to the quota and a door
						currentFeatures++;
						setCell(newx, newy, tileDoor);
					}
				}
			}
		}
	}

		/*******************************************************************************
		 * 
		 * All done with the building, let's finish this one off
		 * 
		 *******************************************************************************/
		/* -nate
		// sprinkle out the bonusstuff (stairs, chests etc.) over the map
		int newx = 0;
		int newy = 0;
		int ways = 0; // from how many directions we can reach the random spot
						// from
		int state = 0; // the state the loop is in, start with the stairs

		while (state != 10) {
			for (int testing = 0; testing < 1000; testing++) {
				newx = getRand(1, xsize - 1);
				newy = getRand(1, ysize - 2); // cheap bugfix, pulls down newy
												// to 0<y<24, from 0<y<25
				// System.out.println("x: " + newx + "\ty: " + newy);
				ways = 4; // the lower the better

				// check if we can reach the spot
				if (getCell(newx, newy + 1) == tileDirtFloor || getCell(newx, newy + 1) == tileCorridor) {
					// north
					if (getCell(newx, newy + 1) != tileDoor)
						ways--;
				}

				if (getCell(newx - 1, newy) == tileDirtFloor || getCell(newx - 1, newy) == tileCorridor) {
					// east
					if (getCell(newx - 1, newy) != tileDoor)
						ways--;
				}

				if (getCell(newx, newy - 1) == tileDirtFloor || getCell(newx, newy - 1) == tileCorridor) {
					// south
					if (getCell(newx, newy - 1) != tileDoor)
						ways--;
				}

				if (getCell(newx + 1, newy) == tileDirtFloor || getCell(newx + 1, newy) == tileCorridor) {
					// west
					if (getCell(newx + 1, newy) != tileDoor)
						ways--;
				}

				if (state == 0) {
					if (ways == 0) {
						// we're in state 0, let's place a "upstairs" thing
						setCell(newx, newy, tileUpStairs);
						state = 1;
						break;
					}
				}

				else if (state == 1) {
					if (ways == 0) {
						// state 1, place a "downstairs"
						setCell(newx, newy, tileDownStairs);
						state = 10;
						break;
					}
				}
				
			}
		
		}
		

		// all done with the map generation, tell the user about it and finish
		System.out.println(msgNumObjects + currentFeatures);

	}
	*/

	// The RNG. the seed is based on seconds from the "java epoch" ( I think..)
	// perhaps it's the same date as the unix epoch
	private int getRand(int min, int max) {

		// the seed is based on current date and the old, already used seed
		long seed = System.currentTimeMillis() + oldseed;
		oldseed = seed;

		Random randomizer = new Random(seed);
		int n = max - min + 1;
		int i = randomizer.nextInt(n);
		if (i < 0)
			i = -i;

		// System.out.println("seed: " + seed + "\tnum: " + (min + i));
		return min + i;
	}
//Not use right now - nate
	private boolean makeCorridor(int x, int y, int lenght, int direction) { 
		/*******************************************************************************/
		// define the dimensions of the corridor (er.. only the width and
		// height..)
		int len = getRand(2, lenght);
		int floor = 71; //Blank title - nate
		int dir = 0;
		if (direction > 0 && direction < 4)
			dir = direction;

		int xtemp = 0;
		int ytemp = 0;

		// reject corridors that are out of bounds
		if (x < 0 || x > xsize)
			return false;
		if (y < 0 || y > ysize)
			return false;

		switch (dir) {

		case 0: // north
			xtemp = x;

			// make sure it's not out of the boundaries
			for (ytemp = y; ytemp > (y - len); ytemp--) {
				if (ytemp < 0 || ytemp > ysize)
					return false; // oh boho, it was!
				if (getCell(xtemp, ytemp) != VOID_1)
					return false;
			}
			// Check if it ever hits wall or other Corridor.
			for (ytemp = y; ytemp > (y - len); ytemp--) {
				if (ytemp < 0 || ytemp > ysize)
					return false; // oh boho, it was!
				if (getCell(xtemp, ytemp) != VOID_1)
					return false;
			}

			// if we're still here, let's start building
			for (ytemp = y; ytemp > (y - len); ytemp--) {
				setCell(xtemp, ytemp, floor);
			}
			break;

		case 1: // east
			ytemp = y;

			for (xtemp = x; xtemp < (x + len); xtemp++) {
				if (xtemp < 0 || xtemp > xsize)
					return false;
				if (getCell(xtemp, ytemp) != VOID_1)
					return false;
			}

			for (xtemp = x; xtemp < (x + len); xtemp++) {
				setCell(xtemp, ytemp, floor);
			}
			break;

		case 2: // south
			xtemp = x;

			for (ytemp = y; ytemp < (y + len); ytemp++) {
				if (ytemp < 0 || ytemp > ysize)
					return false;
				if (getCell(xtemp, ytemp) != VOID_1)
					return false;
			}

			for (ytemp = y; ytemp < (y + len); ytemp++) {
				setCell(xtemp, ytemp, floor);
			}
			break;

		case 3: // west
			ytemp = y;

			for (xtemp = x; xtemp > (x - len); xtemp--) {
				if (xtemp < 0 || xtemp > xsize)
					return false;
				if (getCell(xtemp, ytemp) != VOID_1)
					return false;
			}

			for (xtemp = x; xtemp > (x - len); xtemp--) {
				setCell(xtemp, ytemp, floor);
			}
			break;
		}

		// woot, we're still here! let's tell the other guys we're done!!
		return true;
	}

	private boolean makeRoom(int x, int y, int xlength, int ylength, int direction) {
		/*******************************************************************************/

		// define the dimensions of the room, it should be at least 6x6 tiles
		// (2x2 for walking on, the rest is walls)
		int xlen = getRand(6, xlength);
		int ylen = getRand(6, ylength);

		// the tile type it's going to be filled with
		//int floor = tileDirtFloor;
		//int wall = tileDirtWall;

		// choose the way it's pointing at
		int dir = 0;
		if (direction > 0 && direction < 4)
			dir = direction;

		switch (dir) {

		case 0: // north

			// Check if there's enough space left for it
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

			// we're still here, build
			for (int ytemp = y; ytemp > (y - ylen); ytemp--) {
				for (int xtemp = (x - xlen / 2); xtemp < (x + (xlen + 1) / 2); xtemp++) {
					// start with the walls
					if (xtemp == (x - xlen / 2)){
						setCell(xtemp, ytemp, MathUtils.random(LEFT_WALL_1, LEFT_WALL_10));
						if(ytemp == (y - (ylen-1)))
							setCell(xtemp, ytemp,TOP_LEFT_INSIDE);
						if(ytemp == y)
							setCell(xtemp, ytemp,BOTTOM_LEFT_INSIDE);
					}
					else if (xtemp == (x + (xlen - 1) / 2)){
						setCell(xtemp, ytemp, MathUtils.random(RIGHT_WALL_1, RIGHT_WALL_10));
						if(ytemp == (y - (ylen-1)))
							setCell(xtemp, ytemp,TOP_RIGHT_INSIDE);
						if(ytemp == y)
							setCell(xtemp, ytemp,BOTTOM_RIGHT_INSIDE);
					}
					else if (ytemp == y)
						setCell(xtemp, ytemp, MathUtils.random(BOTTOM_WALL_1, BOTTOM_WALL_10));
					else if (ytemp == (y - ylen + 1))
						setCell(xtemp, ytemp, MathUtils.random(TOP_WALL_1, TOP_WALL_10));
					// and then fill with the floor
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
					if (xtemp == x){
						setCell(xtemp, ytemp, MathUtils.random(LEFT_WALL_1, LEFT_WALL_10));
						if(ytemp == y - ylen / 2)
							setCell(xtemp, ytemp,TOP_LEFT_INSIDE);
						if(ylen % 2 == 0){
							if(ytemp == y + (ylen / 2)-1)
								setCell(xtemp, ytemp,BOTTOM_LEFT_INSIDE);
						}else{
							if(ytemp == y + (ylen / 2))
								setCell(xtemp, ytemp,BOTTOM_LEFT_INSIDE);
						}
					}
					else if (xtemp == (x + xlen - 1)){
						setCell(xtemp, ytemp, MathUtils.random(RIGHT_WALL_1, RIGHT_WALL_10));
							if(ytemp == y - ylen / 2)
								setCell(xtemp, ytemp,TOP_RIGHT_INSIDE);
							if(ylen % 2 == 0){
								if(ytemp == y + (ylen / 2)-1)
									setCell(xtemp, ytemp,BOTTOM_RIGHT_INSIDE);
							}else{
								if(ytemp == y + (ylen / 2))
									setCell(xtemp, ytemp,BOTTOM_RIGHT_INSIDE);
							}
					}
					else if (ytemp == (y - ylen / 2))
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
					if (xtemp == (x - xlen / 2)){
						setCell(xtemp, ytemp, MathUtils.random(LEFT_WALL_1, LEFT_WALL_10));
						if(ytemp == y)
							setCell(xtemp, ytemp, TOP_LEFT_INSIDE);
						if(ytemp == (y + (ylen-1)))
							setCell(xtemp, ytemp, BOTTOM_LEFT_INSIDE);
					}
					else if (xtemp == (x + (xlen - 1) / 2)){
						setCell(xtemp, ytemp, MathUtils.random(RIGHT_WALL_1, RIGHT_WALL_10));
						if(ytemp == y)
							setCell(xtemp, ytemp, TOP_RIGHT_INSIDE);
						if(ytemp == (y + (ylen-1)))
							setCell(xtemp, ytemp, BOTTOM_RIGHT_INSIDE);
					}
					else if (ytemp == y)
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
					if (xtemp == x){
						setCell(xtemp, ytemp, MathUtils.random(RIGHT_WALL_1, RIGHT_WALL_10));
						if(ytemp == y - ylen / 2)
							setCell(xtemp, ytemp,TOP_RIGHT_INSIDE);
						if(ylen % 2 == 0){
							if(ytemp == y + (ylen / 2)-1)
								setCell(xtemp, ytemp,BOTTOM_RIGHT_INSIDE);
						}else{
							if(ytemp == y + (ylen / 2))
								setCell(xtemp, ytemp,BOTTOM_RIGHT_INSIDE);
						}
					}
					else if (xtemp == (x - xlen + 1)){
						setCell(xtemp, ytemp, MathUtils.random(LEFT_WALL_1, LEFT_WALL_10));
						if(ytemp == y - ylen / 2)
							setCell(xtemp, ytemp,TOP_LEFT_INSIDE);
						if(ylen % 2 == 0){
							if(ytemp == y + (ylen / 2)-1)
								setCell(xtemp, ytemp,BOTTOM_LEFT_INSIDE);
						}else{
							if(ytemp == y + (ylen / 2))
								setCell(xtemp, ytemp,BOTTOM_LEFT_INSIDE);
						}
					}
					else if (ytemp == (y - ylen / 2))
						setCell(xtemp, ytemp, MathUtils.random(TOP_WALL_1, TOP_WALL_10)); 
					else if (ytemp == (y + (ylen - 1) / 2))
						setCell(xtemp, ytemp, MathUtils.random(BOTTOM_WALL_1, BOTTOM_WALL_10));
					else
						setCell(xtemp, ytemp, MathUtils.random(FLOOR_4_1, FLOOR_4_10));
				}
			}

			break;
		}

		// yay, all done
		return true;
	}
}
