package nyc.c4q.jonathancolon.catchemall.models.prisoner;

import java.io.Serializable;

/**
 * Created by jonathancolon on 12/12/16.
 */

public class Prisoner implements Serializable {
    private Long _id;
    private String firstName;
    private String lastName;
    private int eyeColor;
    private int skintone;
    private int hairStyle;
    private int beard;
    private boolean hasBeard;
    private boolean hasGlasses;
    private Long lastInspected;

    public Prisoner() { // Default empty constructor needed for Sqlite
        this.firstName = "noname";
        this.lastName = "noname";
        this.eyeColor = -1;
        this.skintone = -1;
        this.hairStyle = -1;
        this.beard = -1;
        this.hasBeard = false;
        this.hasGlasses = false;
        this.lastInspected = System.currentTimeMillis();

    }

    public Prisoner(String firstName, String lastName, int eyeColor, int skintone, int hairStyle, boolean hasBeard, boolean hasGlasses, Long lastInspected) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eyeColor = eyeColor;
        this.skintone = skintone;
        this.hairStyle = hairStyle;
        this.hasBeard = hasBeard;
        this.hasGlasses = hasGlasses;
        this.lastInspected = lastInspected;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Long getLastInspected() {
        return lastInspected;
    }

    public void setLastInspected(Long lastInspected) {
        this.lastInspected = lastInspected;
    }

    public boolean getHasGlasses() {
        return hasGlasses;
    }

    public void setHasGlasses(boolean hasGlasses) {
        this.hasGlasses = hasGlasses;
    }
}
