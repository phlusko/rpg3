package paulrpg3.dd.com.p4u1.network;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Paul on 1/10/2017.
 */

public interface MessageAdapter {
    public int type = 0;
    public static int SERVER_MESSAGES = 1;
    public static int CLIENT_MESSAGES = 2;
    abstract MessageHolder getMessageHolder();
    abstract public void sendMessage(String arg0);
    abstract void setOrder(int arg0);
    abstract int getOrder();

    class Companion {
        public static void sendMessage(MessageAdapter arg1,HashMap<String, String> arg0) {
            JSONObject data = new JSONObject();
            try {
                for(Map.Entry<String, String> entry : arg0.entrySet()) {
                    data.put(entry.getKey(), entry.getValue());
                }
                arg1.sendMessage(data.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public static void sendMessage(MessageAdapter arg0, String arg1, String arg2) {
            JSONObject data = new JSONObject();
            try {
                data.put(arg1, arg2);
                arg0.sendMessage(data.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
