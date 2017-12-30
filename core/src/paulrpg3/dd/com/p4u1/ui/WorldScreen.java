package paulrpg3.dd.com.p4u1.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import paulrpg3.dd.com.p4u1.PaulRPG3Game;
import paulrpg3.dd.com.p4u1.network.MessageAdapter;
import paulrpg3.dd.com.p4u1.utils.PaulGraphics;

/**
 * Created by Paul on 12/29/2017.
 */

public class WorldScreen implements Screen {
    PaulRPG3Game game;
    MessageAdapter adapter;
    OrthographicCamera camera;

    public WorldScreen(PaulRPG3Game game, MessageAdapter adapter) {
        this(game);
        this.adapter = adapter;
        MessageAdapter.Companion.sendMessage(adapter, "type", "hmm");
        Gdx.app.log("phlusko", "Sending Hmm - World Screen Created");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, PaulGraphics.GAME_WIDTH, PaulGraphics.GAME_HEIGHT);
    }

    public WorldScreen(PaulRPG3Game game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        logic();
        game.shapes.setProjectionMatrix(camera.combined);
        game.shapes.begin(ShapeRenderer.ShapeType.Line);
        game.shapes.setColor(Color.BLACK);
        for(int xizer = 0; xizer < PaulGraphics.GAME_WIDTH; xizer+= 50) {
            game.shapes.line(xizer, 0, xizer, PaulGraphics.GAME_HEIGHT);
        }
        for(int yizer = 0; yizer < PaulGraphics.GAME_WIDTH; yizer+= 50) {
            game.shapes.line(0, yizer, PaulGraphics.GAME_WIDTH, yizer);
        }
        game.shapes.end();
    }

    public void logic() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
