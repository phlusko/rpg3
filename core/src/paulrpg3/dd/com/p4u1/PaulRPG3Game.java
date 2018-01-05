package paulrpg3.dd.com.p4u1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import paulrpg3.dd.com.p4u1.network.ClientWorkhorse;
import paulrpg3.dd.com.p4u1.network.PaulServer;
import paulrpg3.dd.com.p4u1.ui.ConnectionScreen;

public class PaulRPG3Game extends Game {
	PaulServer server;
	public ClientWorkhorse client;
	public BitmapFont font;
	public BitmapFont smallfont;
	public RPGWorld world;
	public String name = "Paul";
	public ShapeRenderer shapes;
	public SpriteBatch batch;

	@Override
	public void create () {
		font = new BitmapFont(Gdx.files.internal("img/headline.fnt"),
				false);
		smallfont = new BitmapFont(Gdx.files.internal("img/ak_med.fnt"),
				false);
		shapes = new ShapeRenderer();
		batch = new SpriteBatch();
		this.setScreen(new ConnectionScreen(this));
		world = new RPGWorld();
	}

	public void setGameServer(PaulServer arg0){
		this.server = arg0;
	}

	public PaulServer getServer() {
		return server;
	}
}
