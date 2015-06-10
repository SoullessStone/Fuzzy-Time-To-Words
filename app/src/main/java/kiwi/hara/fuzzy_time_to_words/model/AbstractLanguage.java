package kiwi.hara.fuzzy_time_to_words.model;

import java.util.HashMap;

/**
 * Created by SoullessStone on 10.06.2015.
 */
public abstract class AbstractLanguage {
    public abstract HashMap<String, String> getHours();

    public abstract HashMap<String, String> getMinutes();

    public abstract String getPast();

    public abstract String getBefore();

    public abstract String getHalf();

    public abstract String getFront();
}
