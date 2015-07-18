package com.nateabaker.officecrawl.utils;

import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class SaveMap {
	private static String header = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+"	<map version=\"1.0\" orientation=\"orthogonal\" renderorder=\"right-down\" width=\"100\" height=\"100\" tilewidth=\"32\" tileheight=\"32\" nextobjectid=\"22\">"
			+" 		<tileset firstgid=\"1\" name=\"TileSheet\" tilewidth=\"32\" tileheight=\"32\">\n"
			+"			<image source=\"TitleSheet/TileSheet.png\" trans=\"ff00ff\" width=\"288\" height=\"32\"/>\n"
			+"		 </tileset>\n"
			+ "	<layer name=\"Tile Layer 1\" width=\"100\" height=\"100\">\n"
			+ "		<data encoding=\"csv\">\n";

 	
  	public static void makeDungeon(){
		dungeon dungeon = new dungeon();
		dungeon.createDungeon(100, 100, MathUtils.random(150, 200));
		System.out.print(dungeon.showDungeon());
		saveDungeon(dungeon.getDungeon());
	}

	private static void saveDungeon(int[] dungeonMap){
		String dungeonFile = " ";
		System.out.println(dungeonMap.length);
		dungeonFile += header;
		for(int i = 0; i < dungeonMap.length; i++){
			if(i == 0) dungeonFile += "			";
			if(i % 100 == 0 && i !=0){dungeonFile += "\n 			";}
			dungeonFile += dungeonMap[i];
			if(i != dungeonMap.length-1)dungeonFile += ",";
		}
		dungeonFile += "\n		</data>\n"
					 + "	</layer>\n";
		int objectID = 0;
		dungeonFile += "	<objectgroup name=\"Object Layer 1\">\n";
		int x = 0;
		int y = 0;
		for(int i = 1; i < dungeonMap.length; i++){
			if(dungeonMap[i] == 1 || dungeonMap[i] == 2 || dungeonMap[i] == 4){
				x = i%100;
				if(x == 0){y++;}
				
				dungeonFile += "		<object id=\""+objectID+"\" name=\"CollsionBox\" type=\"Corridor\" x=\""+x*32+"\" y=\""+y*32+"\" width=\"32\" height=\"32\"/>\n";
				objectID ++;
			}
		}
		dungeonFile += "	</objectgroup>\n";
		
		dungeonFile += "</map>";
		Gdx.app.log("saveDungeon", "\n"+dungeonFile);


		FileHandle file = Gdx.files.local("maps/text.tmx");
		file.writeString(dungeonFile, false);
	}
}
