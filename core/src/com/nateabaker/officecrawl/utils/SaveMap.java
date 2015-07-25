package com.nateabaker.officecrawl.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class SaveMap {
	public static void saveDungeon(Dungeon dungeon, String fileName){
		String dungeonFile = ""
				+"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+"	<map version=\"1.0\" orientation=\"orthogonal\" renderorder=\"right-down\" width=\"100\" height=\"100\" tilewidth=\"32\" tileheight=\"32\" nextobjectid=\"22\">\n"
				+"		<tileset firstgid=\"1\" name=\"Office01\" tilewidth=\"32\" tileheight=\"32\">\n"
		  		+"			<image source=\"TitleSheet/"+dungeon.getTitleSheet()+"\" trans=\"00ff7f\" width=\"320\" height=\"320\"/>\n"
				+"		 </tileset>\n"
				+ "	<layer name=\"tileLayer\" width=\"100\" height=\"100\">\n"
				+ "		<data encoding=\"csv\">\n";

		for (int i = 0; i < dungeon.getTileLayer().length; i++) {
			if (i == 0)
				dungeonFile += "			";
			if (i % 100 == 0 && i != 0)
				dungeonFile += "\n 			";

			dungeonFile += dungeon.getTileLayer()[i];

			if (i != dungeon.getTileLayer().length - 1)
				dungeonFile += ",";
		}
		dungeonFile += "\n		</data>\n" + "	</layer>\n";
		int objectID = 0;
		dungeonFile += "	<objectgroup name=\"collisionLayer\">\n";
		int x = 0;
		int y = 0;
		for (int i = 1; i < dungeon.getCollisionLayer().length; i++) {
			x = i % 100;
			if (x == 0) {
				y++;
			}
			if (dungeon.getCollisionLayer()[i] == true) {
				dungeonFile += "		<object id=\"" + objectID + "\" name=\"CollsionBox\" type=\"Corridor\" x=\""
						+ x * 32 + "\" y=\"" + y * 32 + "\" width=\"32\" height=\"32\"/>\n";
				objectID++;
			}
		}
		dungeonFile += "	</objectgroup>\n";

		dungeonFile += "</map>";
		FileHandle file = Gdx.files.local("Maps/"+fileName);
		file.writeString(dungeonFile, false);
	}
}
