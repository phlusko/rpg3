package paulrpg3.dd.com.p4u1.rooms;

import paulrpg3.dd.com.p4u1.utils.Duple;

/**
 * Created by Paul on 8/29/2017.
 */

public class Spot {
    public Duple location;
    public boolean ground;
    public Spot north;
    public Spot east;
    public Spot south;
    public Spot west;

    public Spot() {
        north = null;
        south = null;
        east = null;
        west = null;
        location = new Duple();
    }

    public Spot(Duple origin, boolean isGround) {
        this();
        location = origin;
        ground = isGround;
    }

    public static boolean same(Spot a, Spot b) {
        return a.location.same(b.location);
    }
}
