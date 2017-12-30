package paulrpg3.dd.com.p4u1.things;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import paulrpg3.dd.com.p4u1.rooms.Room;
import paulrpg3.dd.com.p4u1.utils.Duple;

/**
 * Created by Paul on 8/29/2017.
 */

public class Exit extends Thing{

    public Room newRoom;
    public Duple newLocation;

    public Exit(Room arg0) {
        clip = false;
        width = 1;
        height = 1;
        accessPoint = new Duple(0,0);
        usingOffset = new Vector2(0,0);
        newRoom = arg0;
    }



    @Override
    public void update() {

    }

    @Override
    public void drawMe(SpriteBatch arg0) {
        //bg.draw(batch);
    }
}
