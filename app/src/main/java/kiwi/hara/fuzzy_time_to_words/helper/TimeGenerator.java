package kiwi.hara.fuzzy_time_to_words.helper;

/**
 * Created by SoullessStone on 10.06.2015.
 */

import java.util.Calendar;

import kiwi.hara.fuzzy_time_to_words.model.AbstractLanguage;
import kiwi.hara.fuzzy_time_to_words.model.EnglishGenerator;

public class TimeGenerator {
    private int hour;
    private int minutes;
    private boolean past;
    private boolean before;
    private boolean half;
    private AbstractLanguage language;

    public TimeGenerator(Calendar c, AbstractLanguage language) {
        this.language = language;
        setValues(c.get(Calendar.HOUR), c.get(Calendar.MINUTE));
    }

    public void setValues(int hour, int minutes) {
        minutes = roundToNearestFive(minutes).intValue();

        this.hour = hour;
        if (minutes > 27)
            this.minutes = minutes - 30;
        else
            this.minutes = minutes;

        if ((minutes > 32 && minutes < 38) || (minutes < 23 && minutes > 2))
            this.past = true;

        if (minutes > 22 && minutes < 28) {
            this.minutes = 5;
            this.before = true;
        }

        if (minutes > 37) {
            this.minutes = 30 - this.minutes;
            if (this.minutes > 0)
                this.before = true;
        }

        if (minutes > 22 && minutes < 38)
            this.half = true;

        if (minutes > 23)
            this.hour++;
        if (this.hour > 12)
            this.hour = this.hour - 12;
        if (this.hour == 0)
            this.hour = 12;

    }

    public Double roundToNearestFive(int number) {
        Double num = Double.parseDouble(number + "");
        if (num % 5 == 0)
            return num;
        else if (num % 5 < 2.5)
            num = num - num % 5;
        else
            num = num + (5 - num % 5);
        return num;
    }

    @Override
    public String toString() {
        String out = language.getFront() + " ";
        if (minutes != 0)
            out += language.getMinutes().get("" + this.minutes) + " ";
        if (past)
            out += language.getPast() + " ";
        if (before)
            out += language.getBefore() + " ";
        if (half)
            out += language.getHalf() + " ";
        out += language.getHours().get("" + this.hour);
        return out;
    }
}

