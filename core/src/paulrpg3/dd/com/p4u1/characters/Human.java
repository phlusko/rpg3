package paulrpg3.dd.com.p4u1.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import paulrpg3.dd.com.p4u1.rooms.Map;
import paulrpg3.dd.com.p4u1.rooms.Room;
import paulrpg3.dd.com.p4u1.rooms.Spot;
import paulrpg3.dd.com.p4u1.things.Exit;
import paulrpg3.dd.com.p4u1.things.Thing;
import paulrpg3.dd.com.p4u1.utils.Duple;
import paulrpg3.dd.com.p4u1.utils.JazzHands;
import paulrpg3.dd.com.p4u1.utils.PaulGraphics;
import paulrpg3.dd.com.p4u1.utils.Walker;


/**
 * Created by Paul on 8/29/2017.
 */

public class Human extends Character {
    Walker walker;
    JazzHands jazzhands;

    Duple destination;
    Map map;

    boolean walking;
    int walkingSpeed = 333;
    int jazzHandsSpeed = 75;
    int animSpeed = 150;
    int direction = 0;
    int frame = 0;
    long lastAnim;
    long lastStepTime;
    Spot[] path;
    int spotIndex;

    boolean toThing = false;
    boolean interacting;
    Thing target;
    public boolean exiting;
    public Exit exit;

    public Human() {
        super();
        interacting = false;
        walker = new Walker();
        jazzhands = new JazzHands();
        frame = 0;
        direction = 0;
    }

    public void stop(){
        exiting = false;
        interacting = false;
        toThing = false;
        walking = false;
    }

    public void handleClick(Duple click, Room room) {
        if (click.same(location)) {
            interacting = false;
            return;
        }
        moveTo(click, room);
    }

    @Override
    public void moveTo(Duple moveTo, Room room) {
        //destination = moveTo;
        Map arg0 = room.getMap();

        if (room.isDupleInThing(moveTo)) {
            toThing = true;
            if (target != null && !target.equals(room.getThingAtDuple(moveTo))) {
                interacting = false;
            }
            target = room.getThingAtDuple(moveTo);
            destination = target.getAccessSpot(arg0).location;
        } else {
            if (!arg0.getSpot(moveTo).ground) {
                return;
            }
            destination = moveTo;
            toThing = false;
            target = null;
            interacting = false;
        }
        if (destination.same(location)) {
            interacting = true;
            lastAnim = TimeUtils.millis();
            if (target.getClass().getSuperclass() == Exit.class) {
                exiting = true;
                exit = (Exit)target;
            }
            return;
        }
        walking = true;
        map = arg0;
        path = map.getPath(location, destination);
        if (path == null) {
            walking = false;
            return;
        }
        spotIndex = 0;
        //System.out.println(Map.pathToString(path));
        //System.out.println(path.length);
        lastStepTime = TimeUtils.millis();
        lastAnim = TimeUtils.millis();
    }

    public void updateDirection(Duple a, Duple b) {
        Duple diff = Duple.subtract(b, a);
        if (diff.x > 0){
            direction = 2;
        } else if (diff.x < 0){
            direction = 3;
        } else if (diff.y > 0) {
            direction = 1;
        }else {
            direction = 0;
        }
    }

    @Override
    public void update(Room room) {
        if (walking && (TimeUtils.millis() - lastStepTime > walkingSpeed)) {
            lastStepTime = TimeUtils.millis();
            spotIndex++;
            updateDirection(location, path[spotIndex].location);
            previous_location = location.clone();
            location = path[spotIndex].location;
            if (destination.same(location)) {
                walking = false;
                frame = 0;
                if (toThing) {
                    direction = target.engageDirection;
                    toThing = false;
                    interacting = true;
                    lastAnim = TimeUtils.millis();
                    if (target.getClass().getSuperclass() == Exit.class) {
                        this.exiting = true;
                        exit = (Exit)target;
                    }
                }
            }
        }
        if (walking && (TimeUtils.millis() - lastAnim > animSpeed)) {
            frame++;
            if (frame == 4) {
                frame = 0;
            }
            lastAnim = TimeUtils.millis();
        }
        if(interacting && TimeUtils.millis() - lastAnim > jazzHandsSpeed){
            frame++;
            if (frame == 4) {
                frame = 0;
            }
            lastAnim = TimeUtils.millis();
        }

    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public void drawMe(SpriteBatch batch) {
        Vector2 coord = PaulGraphics.dupleToCoord(location);
        if (interacting) {
            coord.add(target.usingOffset);
            jazzhands.drawMe(batch, coord, frame, target.engageDirection);
        } else {
            walker.drawMe(batch, coord, frame,direction);
        }

    }

    @Override
    public void dispose() {

    }
}
