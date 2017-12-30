package paulrpg3.dd.com.p4u1.things;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import paulrpg3.dd.com.p4u1.rooms.Map;
import paulrpg3.dd.com.p4u1.rooms.Spot;
import paulrpg3.dd.com.p4u1.utils.Duple;


/**
 * Created by Paul on 8/29/2017.
 */

public abstract class Thing {
    public Duple location;
    public int width;
    public int height;
    public Vector2 usingOffset;
    public Duple accessPoint;
    public boolean clip = true;
    public int engageDirection = 0;

    public void setLocation(Duple arg0){
        location = arg0;
    }
    public boolean isDupleInThing(Duple arg0) {
        return (arg0.x < location.x + width
                && arg0.x >= location.x
                && arg0.y < location.y + height
                && arg0.y >= location.y);
    }
    public Spot getAccessSpot(Map map) {
        Duple yo = Duple.add(location, accessPoint);
        //System.out.println(yo);
        return map.getSpot(yo);
    }

    abstract public void update();
    abstract public void drawMe(SpriteBatch arg0);
}
