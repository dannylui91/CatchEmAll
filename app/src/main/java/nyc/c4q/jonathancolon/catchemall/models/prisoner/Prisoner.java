package nyc.c4q.jonathancolon.catchemall.models.prisoner;

import java.util.Date;

/**
 * Created by jonathancolon on 12/12/16.
 */

public class Prisoner {

    private int eyeColor;
    private int skintone;
    private int hairStyle;
    private int beard;
    private boolean hasGlasses;
    private Date lastInspected;

    public Prisoner(int eyeColor, int skintone, int hairStyle, boolean hasBeard, boolean hasGlasses) {
        this.eyeColor = eyeColor;
        this.skintone = skintone;
        this.hairStyle = hairStyle;

        this.hasGlasses = hasGlasses;
    }

    public Prisoner() {
        this.eyeColor = -1;
        this.skintone = -1;
        this.hairStyle = -1;
        this.beard = -1;
    }

    public int getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
    }

    public int getSkintone() {
        return skintone;
    }

    public void setSkintone(int skintone) {
        this.skintone = skintone;
    }

    public int getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(int hairStyle) {
        this.hairStyle = hairStyle;
    }

    public int getBeard() {
        return beard;
    }

    public void setBeard(int beard) {
        this.beard = beard;
    }

    public Date getLastInspected() {
        return lastInspected;
    }

    public void setLastInspected(Date lastInspected) {
        this.lastInspected = lastInspected;
    }
}
