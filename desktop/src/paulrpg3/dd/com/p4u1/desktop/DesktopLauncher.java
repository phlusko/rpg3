package paulrpg3.dd.com.p4u1.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import paulrpg3.dd.com.p4u1.PaulRPG3Game;
import paulrpg3.dd.com.p4u1.utils.PaulGraphics;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "The Game";
		config.height = (int) PaulGraphics.GAME_HEIGHT;
		config.width = (int)PaulGraphics.GAME_WIDTH;
		new LwjglApplication(new PaulRPG3Game(), config);
	}
}
