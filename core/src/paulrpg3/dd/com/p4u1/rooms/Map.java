package paulrpg3.dd.com.p4u1.rooms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import paulrpg3.dd.com.p4u1.utils.Duple;

/**
 * Created by Paul on 8/29/2017.
 */

public class Map {


    public Spot[][] spots;

    public Spot origin;
    public Map(boolean[][] ground) {
        spots = new Spot[ground.length][ground[0].length];
        ArrayList<Spot> grounds = new ArrayList();
        for (int x = 0; x < ground.length; x++) {
            for (int y = 0; y < ground[x].length; y++) {
                spots[x][y] = new Spot(new Duple(x,y), ground[x][y]);
                if (spots[x][y].ground) {
                    grounds.add(spots[x][y]);
                }
            }
        }
        for (Iterator<Spot> iter = grounds.iterator(); iter.hasNext();) {
            Spot curr = iter.next();
            setSpotPaths(curr);
        }
        origin = grounds.get(0);
    }

    public static String pathToString(Spot[] arg0) {
        String result = "";
        if (arg0 != null) {
            for (int x = 0; x < arg0.length; x++) {
                result += arg0[0].location.toString() + " ==> ";
            }
        }
        return result;
    }

    public Spot[] getPath(Duple arg0, Duple arg1) {
        return Map.getPath(this.getSpot(arg0), this.getSpot(arg1));
    }

    public static Spot[] getPath(Spot a, Spot b) {
        if (Spot.same(a, b)) {
            return new Spot[]{a};
        }
        //System.out.println(a.location + ", " + b.location);
        ArrayList<Spot> visited = new ArrayList<Spot>();
        ArrayList<Spot> needsVisit = new ArrayList<Spot>();
        needsVisit.add(a);
        HashMap<Spot, Spot> lasts = new HashMap<Spot, Spot>();

        while (needsVisit.size() > 0) {
            Spot curr = needsVisit.get(0);
            needsVisit.remove(0);
            visited.add(curr);
            if (Spot.same(b, curr)) {
                int pathLength = 1;
                Spot backPath = curr;
                do {
                    pathLength++;
                    backPath = lasts.get(backPath);
                } while (!Spot.same(backPath, a));
                Spot[] path = new Spot[pathLength];

                int count = 0;
                backPath = curr;
                path[count] = backPath;
                do {
                    count++;
                    backPath = lasts.get(backPath);
                    path[count] = backPath;
                } while (!Spot.same(backPath, a));
                Spot[] pathCopy = path.clone();
                for(int x = 0; x < path.length; x++) {
                    path[x] = pathCopy[path.length - 1 - x];
                }
                return path;
            } else {
                //System.out.println(b.location + " is not " + curr.l)
            }
            if (curr.north != null && !visited.contains(curr.north) && !needsVisit.contains(curr.north) && curr.north.ground) {
                needsVisit.add(curr.north);
                lasts.put(curr.north, curr);
            }
            if (curr.south != null && !visited.contains(curr.south) && !needsVisit.contains(curr.south) && curr.south.ground) {
                needsVisit.add(curr.south);
                lasts.put(curr.south, curr);
            }
            if (curr.east != null && !visited.contains(curr.east) && !needsVisit.contains(curr.east) && curr.east.ground) {
                needsVisit.add(curr.east);
                lasts.put(curr.east, curr);
            }
            if (curr.west != null && !visited.contains(curr.west) && !needsVisit.contains(curr.west) && curr.west.ground) {
                needsVisit.add(curr.west);
                lasts.put(curr.west, curr);
            }
            //System.out.println(needsVisit.size());
        }

        System.out.println("really?");
        return null;
    }



    private void setSpotPaths(Spot arg0) {
        if (isDupleGround(new Duple(arg0.location.x, arg0.location.y + 1))) {
            arg0.north = spots[arg0.location.x][arg0.location.y + 1];
        }

        if (isDupleGround(new Duple(arg0.location.x, arg0.location.y - 1))) {
            arg0.south = spots[arg0.location.x][arg0.location.y - 1];
        }

        if (isDupleGround(new Duple(arg0.location.x + 1, arg0.location.y))) {
            arg0.east = spots[arg0.location.x + 1][arg0.location.y];
        }

        if (isDupleGround(new Duple(arg0.location.x - 1, arg0.location.y))) {
            arg0.west = spots[arg0.location.x - 1][arg0.location.y];
        }
    }

    public Spot getSpot(Duple arg0) {
        //System.out.println(spots[arg0.x][arg0.y].location + "+++");
        return spots[arg0.x][arg0.y];
    }

    private boolean isDupleGround(Duple checker) {
        if (checker.x < 0
                || checker.y < 0
                || checker.x >= spots.length
                || checker.y >= spots[0].length) {
            return false;
        } else {
            return spots[checker.x][checker.y].ground;
        }
    }
}
