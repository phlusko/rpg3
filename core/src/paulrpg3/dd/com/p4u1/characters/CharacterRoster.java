package paulrpg3.dd.com.p4u1.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Paul on 1/4/2018.
 */

public class CharacterRoster {
    ArrayList<Character> characters;

    public CharacterRoster(){
        characters = new ArrayList<Character>();
    }

    public void drawMe(SpriteBatch batch) {
        for (Iterator<Character> iter = characters.iterator(); iter.hasNext();) {
            iter.next().drawMe(batch);
        }
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public JSONArray getRosterJSON() {
        JSONArray roster = new JSONArray();
        try {
            for (Iterator<Character> iter = characters.iterator(); iter.hasNext();) {
                Character next = iter.next();
                JSONObject data = new JSONObject();
                data.put("id", next.id);
                data.put("x", next.location.x);
                data.put("y", next.location.y);
                roster.put(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return roster;
    }
}
