package paulrpg3.dd.com.p4u1.characters;

import com.thegame.dd.p4u1.rooms.Room;
import com.thegame.dd.p4u1.utils.Walker;

/**
 * Created by Paul on 9/3/2017.
 */
public class Krystal extends Human {
    Human paul;
    public Krystal (Human paul) {
        this.paul = paul;
        this.location = paul.location.clone();
        this.previous_location = paul.location.clone();
        this.walker = new Walker(1);
    }

    @Override
    public void update(Room room) {
       super.update(room);
        if (!paul.previous_location.same(this.location) && !walking) {
            this.moveTo(paul.previous_location.clone(), room);
        } else {
           // System.out.println("same");
        }
    }
}
