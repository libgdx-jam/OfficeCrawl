package com.nateabaker.officecrawl;

import com.badlogic.gdx.Game;
import com.nateabaker.officecrawl.screens.GameScreen;

public class OfficeCrawl extends Game {
	
	@Override
	public void create () {
		Assets.load();
		Assets.create();
		GameManager.init(this);
		GameManager.setScreen(new GameScreen());
	}

}
