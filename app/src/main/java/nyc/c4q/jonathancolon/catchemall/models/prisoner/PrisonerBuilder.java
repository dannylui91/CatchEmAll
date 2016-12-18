package nyc.c4q.jonathancolon.catchemall.models.prisoner;

import java.util.Random;

/**
 * Created by jonathancolon on 12/12/16.
 */

public class PrisonerBuilder {
    private final static Random random = new Random();
    private static final int EYE_RANGE = 3;
    private static final int HAIR_RANGE = 36;
    private static final int SKINTONE_RANGE = 5;
    private static final int BEARD_RANGE = 6;

    public static Prisoner createPrisoner(){
        Prisoner prisoner = new Prisoner();
        prisoner.setSkintone(randomSkinTone());
        prisoner.setEyeColor(randomEyeColor());
        prisoner.setHairStyle(randomHairColor());
        prisoner.setBeard(randomBeard());
        prisoner.setHasGlasses(hasGlasses());
        prisoner.setLastInspected(System.currentTimeMillis());

        return prisoner;
    }


    private static int randomEyeColor(){
        int eyeColor = random.nextInt(EYE_RANGE) + 1;
        return eyeColor;
    }

    private static int randomHairColor(){
        int hairColor = random.nextInt(HAIR_RANGE) + 1;
        return hairColor;
    }

    private static int randomSkinTone(){
        int skinTone = random.nextInt(SKINTONE_RANGE) + 1;
        return skinTone;
    }

    private static int randomBeard(){
        int beard = random.nextInt(BEARD_RANGE);
        return beard;
    }

    private static boolean hasGlasses(){
        boolean hasGlasses = random.nextBoolean();
        return hasGlasses;
    }
}
