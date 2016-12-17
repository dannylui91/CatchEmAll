package nyc.c4q.jonathancolon.catchemall.models.prisoner;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nyc.c4q.jonathancolon.catchemall.R;

import static android.view.View.GONE;

/**
 * Created by Danny on 12/14/2016.
 */

public class PrisonerHelper {

    private ImageView eyeColorLayer;
    private ImageView skinToneLayer;
    private ImageView hairStyleLayer;
    private ImageView accessoryLayer;
    private ImageView beardLayer;
    private TextView nameLayer;

    Context context;

    public PrisonerHelper(Context context) {
        this.context = context;
    }



    public void updatePrisonerSpriteView(Prisoner prisoner) {
        eyeColorLayer = (ImageView) ((Activity) context).findViewById(R.id.eyes);
        skinToneLayer = (ImageView) ((Activity) context).findViewById(R.id.skintone);
        hairStyleLayer = (ImageView) ((Activity) context).findViewById(R.id.hair);
        beardLayer = (ImageView) ((Activity) context).findViewById(R.id.beard);
        accessoryLayer = (ImageView) ((Activity) context).findViewById(R.id.accessory);

        //prisoner = PrisonerBuilder.createPrisoner();
        setNameLayer(prisoner);
        setEyeLayer(prisoner.getEyeColor());
        setSkintoneLayer(prisoner.getSkintone());
        setHairLayer(prisoner.getHairStyle());
        setBeardLayer(prisoner.getBeard());
    }

    private void setNameLayer(Prisoner prisoner) {
    }

    public void generateViewHolderPrisonerSprite(Prisoner prisoner, View itemView) {
        eyeColorLayer = (ImageView) itemView.findViewById(R.id.vh_eyes);
        skinToneLayer = (ImageView) itemView.findViewById(R.id.vh_skintone);
        hairStyleLayer = (ImageView) itemView.findViewById(R.id.vh_hair);
        beardLayer = (ImageView) itemView.findViewById(R.id.vh_beard);
        accessoryLayer = (ImageView) itemView.findViewById(R.id.vh_accessory);

        //prisoner = PrisonerBuilder.createPrisoner();

        setEyeLayer(prisoner.getEyeColor());
        setSkintoneLayer(prisoner.getSkintone());
        setHairLayer(prisoner.getHairStyle());
        setBeardLayer(prisoner.getBeard());
    }

    public void generateNewPrisonerSprite() {
        eyeColorLayer = (ImageView) ((Activity) context).findViewById(R.id.eyes);
        skinToneLayer = (ImageView) ((Activity) context).findViewById(R.id.skintone);
        hairStyleLayer = (ImageView) ((Activity) context).findViewById(R.id.hair);
        beardLayer = (ImageView) ((Activity) context).findViewById(R.id.beard);
        accessoryLayer = (ImageView) ((Activity) context).findViewById(R.id.accessory);


        Prisoner prisoner = PrisonerBuilder.createPrisoner();

        setEyeLayer(prisoner.getEyeColor());
        setSkintoneLayer(prisoner.getSkintone());
        setHairLayer(prisoner.getHairStyle());
        setBeardLayer(prisoner.getBeard());
        setAccessoryLayer(prisoner.getHasGlasses());
    }

    public void setBeardLayer(int beard) {
        if (beard == 1) {
            beardLayer.setImageResource(PrisonerAttributes.BLACK_BEARD);
        }
        if (beard == 2) {
            beardLayer.setImageResource(PrisonerAttributes.BROWN_BEARD);
        }
        if (beard == 3) {
            beardLayer.setImageResource(PrisonerAttributes.GREY_BEARD);
        } else {
            beardLayer.setVisibility(GONE);
        }
    }

    public void setAccessoryLayer(boolean hasGlasses) {
        if (hasGlasses == true) accessoryLayer.setImageResource(PrisonerAttributes.GREY_GLASSES);
    }

    public void setEyeLayer(int eyeColor) {

        switch (eyeColor) {
            case 1:
                eyeColorLayer.setImageResource(PrisonerAttributes.BLUE);
                break;
            case 2:
                eyeColorLayer.setImageResource(PrisonerAttributes.BROWN);
                break;
            case 3:
                eyeColorLayer.setImageResource(PrisonerAttributes.GREEN);
        }
    }

    public void setSkintoneLayer(int skintone) {

        switch (skintone) {
            case 1:
                skinToneLayer.setImageResource(PrisonerAttributes.LIGHTEST);
                break;
            case 2:
                skinToneLayer.setImageResource(PrisonerAttributes.LIGHTER);
                break;
            case 3:
                skinToneLayer.setImageResource(PrisonerAttributes.LIGHT);
                break;
            case 4:
                skinToneLayer.setImageResource(PrisonerAttributes.TAN);
                break;
            case 5:
                skinToneLayer.setImageResource(PrisonerAttributes.DARK);
        }
    }

    public void setHairLayer(int hairStyle) {

        switch (hairStyle) {
            case 1:
                hairStyleLayer.setImageResource(PrisonerAttributes.BALD_BLACK);
                break;
            case 2:
                hairStyleLayer.setImageResource(PrisonerAttributes.BALD_BLONDE);
                break;
            case 3:
                hairStyleLayer.setImageResource(PrisonerAttributes.BALD_BROWN);
                break;
            case 4:
                hairStyleLayer.setImageResource(PrisonerAttributes.BALD_GREY);
                break;
            case 5:
                hairStyleLayer.setImageResource(PrisonerAttributes.PONYTAIL_BLACK);
                break;
            case 6:
                hairStyleLayer.setImageResource(PrisonerAttributes.PONYTAIL_BLONDE);
                break;
            case 7:
                hairStyleLayer.setImageResource(PrisonerAttributes.PONYTAIL_BROWN);
                break;
            case 8:
                hairStyleLayer.setImageResource(PrisonerAttributes.PONYTAIL_GREY);
                break;
            case 9:
                hairStyleLayer.setImageResource(PrisonerAttributes.COMBOVER_BLACK);
                break;
            case 10:
                hairStyleLayer.setImageResource(PrisonerAttributes.COMBOVER_BLONDE);
                break;
            case 11:
                hairStyleLayer.setImageResource(PrisonerAttributes.COMBOVER_BROWN);
                break;
            case 12:
                hairStyleLayer.setImageResource(PrisonerAttributes.COMBOVER_GREY);
                break;
            case 13:
                hairStyleLayer.setImageResource(PrisonerAttributes.CREW_BLACK);
                break;
            case 14:
                hairStyleLayer.setImageResource(PrisonerAttributes.CREW_BLONDE);
                break;
            case 15:
                hairStyleLayer.setImageResource(PrisonerAttributes.CREW_BROWN);
                break;
            case 16:
                hairStyleLayer.setImageResource(PrisonerAttributes.CREW_GREY);
                break;
            case 17:
                hairStyleLayer.setImageResource(PrisonerAttributes.MANBUN_BLACK);
                break;
            case 18:
                hairStyleLayer.setImageResource(PrisonerAttributes.MANBUN_BLONDE);
                break;
            case 19:
                hairStyleLayer.setImageResource(PrisonerAttributes.MANBUN_BROWN);
                break;
            case 20:
                hairStyleLayer.setImageResource(PrisonerAttributes.MANBUN_GREY);
                break;
            case 21:
                hairStyleLayer.setImageResource(PrisonerAttributes.PULLED_BLACK);
                break;
            case 22:
                hairStyleLayer.setImageResource(PrisonerAttributes.PULLED_BLONDE);
                break;
            case 23:
                hairStyleLayer.setImageResource(PrisonerAttributes.PULLED_BROWN);
                break;
            case 24:
                hairStyleLayer.setImageResource(PrisonerAttributes.PULLED_GREY);
                break;
            case 25:
                hairStyleLayer.setImageResource(PrisonerAttributes.SLICKED_BLACK);
                break;
            case 26:
                hairStyleLayer.setImageResource(PrisonerAttributes.SLICKED_BLONDE);
                break;
            case 27:
                hairStyleLayer.setImageResource(PrisonerAttributes.SLICKED_BROWN);
                break;
            case 28:
                hairStyleLayer.setImageResource(PrisonerAttributes.SLICKED_GREY);
                break;
            case 29:
                hairStyleLayer.setImageResource(PrisonerAttributes.SPIKEY_BLACK);
                break;
            case 30:
                hairStyleLayer.setImageResource(PrisonerAttributes.SPIKEY_BLONDE);
                break;
            case 31:
                hairStyleLayer.setImageResource(PrisonerAttributes.SPIKEY_BROWN);
                break;
            case 32:
                hairStyleLayer.setImageResource(PrisonerAttributes.SPIKEY_GREY);
                break;
            case 33:
                hairStyleLayer.setImageResource(PrisonerAttributes.FRO_BLACK);
                break;
            case 34:
                hairStyleLayer.setImageResource(PrisonerAttributes.FRO_BLONDE);
                break;
            case 35:
                hairStyleLayer.setImageResource(PrisonerAttributes.FRO_BROWN);
                break;
            case 36:
                hairStyleLayer.setImageResource(PrisonerAttributes.FRO_GREY);
                break;
        }
    }
}
