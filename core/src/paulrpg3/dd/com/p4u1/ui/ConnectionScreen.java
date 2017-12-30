package paulrpg3.dd.com.p4u1.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;
import java.net.Socket;

import paulrpg3.dd.com.p4u1.PaulRPG3Game;
import paulrpg3.dd.com.p4u1.network.BroadcastClient;
import paulrpg3.dd.com.p4u1.network.BroadcastServer;
import paulrpg3.dd.com.p4u1.network.ClientMessages;
import paulrpg3.dd.com.p4u1.network.ClientWorkhorse;
import paulrpg3.dd.com.p4u1.network.PaulServer;
import paulrpg3.dd.com.p4u1.network.ServerMessages;
import paulrpg3.dd.com.p4u1.utils.PaulGraphics;

/**
 * Created by Paul on 12/29/2017.
 */

public class ConnectionScreen implements Screen {

    public static int port = 6969;
    OrthographicCamera camera;
    PaulRPG3Game game;
    SpriteBatch batch;
    Texture clock;

    boolean is_waiting = true;
    boolean searching = false;
    boolean amServer;
    BroadcastClient broadcastClient;
    long searchStart;
    int lineHeight;
    boolean hasName = false;

    public ConnectionScreen(PaulRPG3Game arg0) {
        this.game = arg0;
        batch = new SpriteBatch();
        clock = new Texture("img/clock.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, PaulGraphics.GAME_WIDTH, PaulGraphics.GAME_HEIGHT);
    }

    @Override
    public void show() {
        findServer();
    }

    public void findServer() {
        searching = true;
        broadcastClient = new BroadcastClient();
        new Thread(broadcastClient).start();
        searchStart = System.currentTimeMillis();
        Gdx.app.log("phlusko", "Finding Server");
    }

    @Override
    public void render(float delta) {
        logic();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(clock, (PaulGraphics.GAME_WIDTH/ 2) - 64, (PaulGraphics.GAME_HEIGHT / 2) - 64);
        batch.end();
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
        batch.dispose();
    }

    public void logic () {
        if (searching) {
            long currentTime = System.currentTimeMillis();
            //Gdx.app.log("phlusko", "No Servers Found . " + (currentTime - searchStart));
            if (currentTime - searchStart > 5000) {
                broadcastClient.stopSearch();
                Gdx.app.log("phlusko", "No Servers Found");
                searching = false;
                searchStart = 0;
                Thread thread = new Thread(new BroadcastServer());
                thread.start();
                game.setGameServer(new PaulServer());
                if (game.getServer().serverStarted) {
                    amServer = true;
                    Gdx.app.log("phlusko", "Start Server");
                    game.setScreen(new WorldScreen(game, new ServerMessages(game.getServer())));
                    //game.setScreen(new p4u1.dd.phone_lan_frame.demos.Blah());
                    searching = false;
                    amServer = true;
                    is_waiting = true;
                } else {
                    searching = true;
                    searchStart = System.currentTimeMillis();
                }
            } else {
                String serverIP = broadcastClient.getServerIP();
                if (serverIP != null) {
                    //Gdx.app.log("phlusko", "Found Server at: " + serverIP);
                    searchStart = 0;
                    try {
                        game.client = new ClientWorkhorse(new Socket(serverIP, port));
                        new Thread(game.client).start();
                        game.setScreen(new WorldScreen(game, new ClientMessages(game.client)));
                        searching = false;
                        amServer = false;
                    } catch (IOException e) {
                        //e.printStackTrace();
                        Gdx.app.log("phlusko", "Bad ju ju");
                    }
                }
            }
        }
    }
}
