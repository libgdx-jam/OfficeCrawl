package com.nateabaker.officecrawl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.nateabaker.officecrawl.items.Inventory;

public class GameManager {
	private static Game game;

	private InventoryManager inventory;

	public static void init(Game game) {
		GameManager.game = game;
	}

	public static void setScreen(Screen screen) {
		game.setScreen(screen);
	}

	public InventoryManager getInventory() {
		return inventory;
	}

}

class InventoryManager {
	Inventory inventory;

	public InventoryManager() {
		inventory = new Inventory();
	}

}
