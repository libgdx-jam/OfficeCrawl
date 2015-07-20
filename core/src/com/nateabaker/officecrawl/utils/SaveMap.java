package com.nateabaker.officecrawl.utils;

import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
//Don't touch this!!!!
//Don't touch this!!!!
//Don't touch this!!!!
//Don't touch this!!!!
public class SaveMap {
	private static String titleSheet = "Office01.png";
	private static String header = ""
			+"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+"	<map version=\"1.0\" orientation=\"orthogonal\" renderorder=\"right-down\" width=\"100\" height=\"100\" tilewidth=\"32\" tileheight=\"32\" nextobjectid=\"22\">\n"
			+"		<tileset firstgid=\"1\" name=\"Office01\" tilewidth=\"32\" tileheight=\"32\">\n"
	  		+"			<image source=\"TitleSheet/"+titleSheet+"\" trans=\"00ff7f\" width=\"320\" height=\"320\"/>\n"
			+"		 </tileset>\n"
			+ "	<layer name=\"Tile Layer 1\" width=\"100\" height=\"100\">\n"
			+ "		<data encoding=\"csv\">\n";

 	
  	public static void makeDungeon(String titleSheet){
  		SaveMap.titleSheet = titleSheet;
		Dungeon dungeon = new Dungeon();
		dungeon.createDungeon(100, 100, MathUtils.random(150, 200));
		saveDungeon(dungeon.getDungeon());
	}

	private static void saveDungeon(int[] dungeonMap){
		String dungeonFile = "";
		dungeonFile += header;
		for(int i = 0; i < dungeonMap.length; i++){
			if(i == 0) dungeonFile += "			";
			if(i % 100 == 0 && i != 0){dungeonFile += "\n 			";}
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
		//Gdx.app.log("saveDungeon", "\n"+dungeonFile);


		FileHandle file = Gdx.files.local("Maps/test.tmx");
		file.writeString(dungeonFile, false);
	}
}
