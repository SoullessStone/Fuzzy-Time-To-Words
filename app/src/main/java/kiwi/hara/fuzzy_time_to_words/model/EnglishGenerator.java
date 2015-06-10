package kiwi.hara.fuzzy_time_to_words.model;

/**
 * Created by SoullessStone on 10.06.2015.
 */
import java.util.HashMap;

public class EnglishGenerator extends AbstractLanguage {
    @Override
    public HashMap<String, String> getHours() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        map.put("4", "four");
        map.put("5", "five");
        map.put("6", "six");
        map.put("7", "seven");
        map.put("8", "eight");
        map.put("9", "nine");
        map.put("10", "ten");
        map.put("11", "eleven");
        map.put("12", "twelve");
        return map;
    }

    @Override
    public HashMap<String, String> getMinutes() {
        HashMap<String, String> map = new HashMap<>();
        map.put("5", "five");
        map.put("10", "ten");
        map.put("15", "quarter");
        map.put("20", "twenty");
        return map;
    }

    @Override
    public String getPast() {
        return "past";
    }

    @Override
    public String getBefore() {
        return "to";
    }

    // TODO (erfasst von Michel): Es ist "half to eight"
    @Override
    public String getHalf() {
        return "half";
    }

    @Override
    public String getFront() {
        return "Hey there! It's";
    }
}