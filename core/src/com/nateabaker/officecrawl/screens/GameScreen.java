package com.nateabaker.officecrawl.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.nateabaker.officecrawl.EntityManager;
import com.nateabaker.officecrawl.entitys.Entity;
import com.nateabaker.officecrawl.entitys.player.Player;
import com.nateabaker.officecrawl.gui.GUI;
import com.nateabaker.officecrawl.utils.Dungeon;
import com.nateabaker.officecrawl.utils.DungeonGenerator;
import com.nateabaker.officecrawl.utils.MapBodyBuilder;
import com.nateabaker.officecrawl.utils.SaveMap;

public class GameScreen implements Screen {
	private Texture img;

	private OrthographicCamera camera;

	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;

	private World world;
	private Body body;

	private EntityManager entityManager = new EntityManager();

	private Box2DDebugRenderer renderer;

	private GUI gui = new GUI();

	private Player player;

	@Override
	public void show() {
		float w = 20;
		float h = 15;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.translate(50, 50);
		camera.update();

		Dungeon dungeon = new Dungeon(100, 100, MathUtils.random(150, 200),
				"Office01.png");
		DungeonGenerator.createDungeon(dungeon);
		SaveMap.saveDungeon(dungeon, "test.tmx");

		tiledMap = new TmxMapLoader().load("Maps/test.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 0.03125f);

		world = new World(new Vector2(0, 0), true);

		Array<Body> bodies = MapBodyBuilder.buildShapes(tiledMap, 32, world);
		player = new Player(new Vector2(50, 50), world);
		entityManager.getEntitys().add(player);

		renderer = new Box2DDebugRenderer();
		renderer.setDrawBodies(true);
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			camera.translate(-1, 0);
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			camera.translate(+1, 0);
		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			camera.translate(0, +1);
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			camera.translate(0, -1);

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.position.set(player.getBody().getPosition(), 0);
		camera.update();

		tiledMapRenderer.setView(camera);
	//	tiledMapRenderer.render();

		world.step(1 / 60f, 6, 2);
		player.update(delta);
		for (Entity e : entityManager.getEntitys()) {
			if (!(e instanceof Player)) {
				e.update(delta);
			}
		}
		renderer.render(world, camera.combined);

		gui.update(delta);

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			gui.debug = !gui.debug;
		}

		if (Gdx.input.isKeyJustPressed(Keys.G)) {
			for (int i = 0; i < player.getBody().getFixtureList().size; i++) {
				player.getBody()
						.getFixtureList()
						.get(i)
						.setSensor(
								!player.getBody().getFixtureList().get(i)
										.isSensor());
			}
		}

	}

	@Override
	public void resize(int width, int height) {
		gui.resize(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		gui.dispose();
	}

}
