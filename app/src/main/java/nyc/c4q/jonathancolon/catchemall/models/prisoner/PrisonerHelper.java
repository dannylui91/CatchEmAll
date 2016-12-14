package nyc.c4q.jonathancolon.catchemall.models.prisoner;

/**
 * Created by Danny on 12/14/2016.
 */

public class PrisonerHelper {

    public static int getEyeLayer(int eyeColor){

        switch (eyeColor){
            case 1 :
                return PrisonerAttributes.BLUE;
            case 2 :
                return PrisonerAttributes.BROWN;
            default:
                return PrisonerAttributes.GREEN;
        }
    }

    public static int getSkintoneLayer(int skintone) {
        switch (skintone) {
            case 1:
                return PrisonerAttributes.LIGHTEST;
            case 2:
                return PrisonerAttributes.LIGHTER;
            case 3:
                return PrisonerAttributes.LIGHT;
            case 4:
                return PrisonerAttributes.TAN;
            default:
                return PrisonerAttributes.DARK;
        }
    }

    public static int getBeardLayer(int beard) {
        switch(beard) {
            case 1:
                return PrisonerAttributes.BLACK_BEARD;
            case 2:
                return PrisonerAttributes.BROWN_BEARD;
            case 3:
                return PrisonerAttributes.GREY_BEARD;
            default:
                return -1;
        }
    }

    public static int getHairLayer(int hairStyle) {

        switch (hairStyle) {
            case 1:
                return PrisonerAttributes.BALD_BLACK;
            case 2:
                return PrisonerAttributes.BALD_BLONDE;
            case 3:
                return PrisonerAttributes.BALD_BROWN;
            case 4:
                return PrisonerAttributes.BALD_GREY;
            case 5:
                return PrisonerAttributes.PONYTAIL_BLACK;
            case 6:
                return PrisonerAttributes.PONYTAIL_BLONDE;
            case 7:
                return PrisonerAttributes.PONYTAIL_BROWN;
            case 8:
                return PrisonerAttributes.PONYTAIL_GREY;
            case 9:
                return PrisonerAttributes.COMBOVER_BLACK;
            case 10:
                return PrisonerAttributes.COMBOVER_BLONDE;
            case 11:
                return PrisonerAttributes.COMBOVER_BROWN;
            case 12:
                return PrisonerAttributes.COMBOVER_GREY;
            case 13:
                return PrisonerAttributes.CREW_BLACK;
            case 14:
                return PrisonerAttributes.CREW_BLONDE;
            case 15:
                return PrisonerAttributes.CREW_BROWN;
            case 16:
                return PrisonerAttributes.CREW_GREY;
            case 17:
                return PrisonerAttributes.MANBUN_BLACK;
            case 18:
                return PrisonerAttributes.MANBUN_BLONDE;
            case 19:
                return PrisonerAttributes.MANBUN_BROWN;
            case 20:
                return PrisonerAttributes.MANBUN_GREY;
            case 21:
                return PrisonerAttributes.PULLED_BLACK;
            case 22:
                return PrisonerAttributes.PULLED_BLONDE;
            case 23:
                return PrisonerAttributes.PULLED_BROWN;
            case 24:
                return PrisonerAttributes.PULLED_GREY;
            case 25:
                return PrisonerAttributes.SLICKED_BLACK;
            case 26:
                return PrisonerAttributes.SLICKED_BLONDE;
            case 27:
                return PrisonerAttributes.SLICKED_BROWN;
            case 28:
                return PrisonerAttributes.SLICKED_GREY;
            case 29:
                return PrisonerAttributes.SPIKEY_BLACK;
            case 30:
                return PrisonerAttributes.SPIKEY_BLONDE;
            case 31:
                return PrisonerAttributes.SPIKEY_BROWN;
            case 32:
                return PrisonerAttributes.SPIKEY_GREY;
            case 33:
                return PrisonerAttributes.FRO_BLACK;
            case 34:
                return PrisonerAttributes.FRO_BLONDE;
            case 35:
                return PrisonerAttributes.FRO_BROWN;
            default:
                return PrisonerAttributes.FRO_GREY;
        }
    }
}
