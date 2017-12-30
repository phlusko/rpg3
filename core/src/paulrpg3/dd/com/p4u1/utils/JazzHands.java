package paulrpg3.dd.com.p4u1.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Paul on 8/29/2017.
 */

public class JazzHands {
    Sprite[][] sprites = new Sprite[4][2];
    Texture sprite_texture;
    TextureRegion sprite_region;

    public JazzHands(){
        this.sprite_texture = new Texture("characters/paul_jazz_hands.png");
        for(int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                this.sprite_region = new TextureRegion(this.sprite_texture, 0 + (j * 256), 0 + (i * 256), 256, 256);
                this.sprites[j][i] = new Sprite(sprite_region);
            }
        }
    }
    public void drawMe (SpriteBatch batch, Vector2 loc, int frame, int direction) {
        this.sprites[frame][direction].setSize(128, 128);
        this.sprites[frame][direction].setPosition(loc.x - 64, loc.y);
        this.sprites[frame][direction].draw(batch);
    }
}
