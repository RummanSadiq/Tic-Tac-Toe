package sample;

import java.util.HashMap;

/**
 * Created by Rumman Sadiq on 4/12/2017.
 */
public class Shot {
    public String pos;
    public int flag;
    public HashMap<String, String> against;

    public Shot() {
        this.pos = "";
        this.flag = 0;
        against = new HashMap<String, String>();
    }
}
