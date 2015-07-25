package com.nateabaker.officecrawl.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nateabaker.officecrawl.utils.Dungeon;
import com.nateabaker.officecrawl.utils.DungeonGenerator;
import com.nateabaker.officecrawl.utils.SaveMap;

public class GameScreen implements Screen {
    private Texture img;
    
    private OrthographicCamera camera;
    
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    
    private World world;
    private Body body;

    
    private Box2DDebugRenderer renderer;

	@Override
	public void show() {
        float w = 20;
        float h = 15;

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.translate(50,50);
        camera.update();
        

        
        Dungeon dungeon = new Dungeon(100, 100, MathUtils.random(150, 200), "Office01.png");
        DungeonGenerator.createDungeon(dungeon);
        SaveMap.saveDungeon(dungeon,"test.tmx");
        
        tiledMap = new TmxMapLoader().load("Maps/test.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 0.03125f);
     
        world = new World(new Vector2(0, 0), true);
		
        BodyDef def = new BodyDef();
		def.position.set(new Vector2(1, 1));
		def.type = BodyType.DynamicBody; 
		def.angularDamping = 1.0f;
		
		body = world.createBody(def);
		
		CircleShape cir = new CircleShape();
		cir.setRadius(0.5f);
		
		FixtureDef fDef = new FixtureDef();
		fDef.shape = cir;
		fDef.density = 0.5f;
		fDef.friction = 0.5f;
		fDef.restitution=0.6f;

		Fixture fixture = body.createFixture(fDef);
		
		renderer = new Box2DDebugRenderer();
		renderer.setDrawBodies(true);
	}

	@Override
	public void render(float delta) {
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
			 camera.translate(-1,0);
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			 camera.translate(+1,0);
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
			 camera.translate(0,+1);
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
			 camera.translate(0,-1);
		
		if(Gdx.input.isKeyPressed(Input.Keys.W))
			body.applyForce(new Vector2(0,10), body.getLocalCenter(), true);
		if(Gdx.input.isKeyPressed(Input.Keys.A))
			body.applyForce(new Vector2(0,-10), body.getLocalCenter(), true);
		if(Gdx.input.isKeyPressed(Input.Keys.S))
			body.applyForce(new Vector2(-10, 0), body.getLocalCenter(), true);
		if(Gdx.input.isKeyPressed(Input.Keys.D))
			body.applyForce(new Vector2(10,0), body.getLocalCenter(), true);
		
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.update();
        
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        
        world.step(1/60f, 6, 2);
        
        renderer.render(world, camera.combined);
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

}
