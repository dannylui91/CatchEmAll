package nyc.c4q.jonathancolon.catchemall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerAttributes;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerHelper;

import static android.view.View.GONE;

public class SecondActivity extends AppCompatActivity {
    private ImageView eyeColorLayer;
    private ImageView skinToneLayer;
    private ImageView hairStyleLayer;
    private ImageView accessoryLayer;
    private ImageView beardLayer;
    public Button createPrisonerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initViews();

        Intent intent = getIntent();
        Prisoner prisoner = (Prisoner) intent.getExtras().getSerializable("PRISONER_KEY");

        displayPrisoner(prisoner);
    }

    private void initViews() {
        eyeColorLayer = (ImageView) findViewById(R.id.eyes);
        skinToneLayer = (ImageView) findViewById(R.id.skintone);
        hairStyleLayer = (ImageView) findViewById(R.id.hair);
        beardLayer = (ImageView) findViewById(R.id.beard);
        accessoryLayer = (ImageView) findViewById(R.id.accessory);
    }

    private void displayPrisoner(Prisoner prisoner) {
        eyeColorLayer.setImageResource(PrisonerHelper.getEyeLayer(prisoner.getEyeColor()));
        skinToneLayer.setImageResource(PrisonerHelper.getSkintoneLayer(prisoner.getSkintone()));
        hairStyleLayer.setImageResource(PrisonerHelper.getHairLayer(prisoner.getHairStyle()));

        if (!(prisoner.getBeard() > 3)) {
            beardLayer.setImageResource(PrisonerHelper.getBeardLayer(prisoner.getBeard()));
        } else{
            beardLayer.setVisibility(GONE);
        }
    }

    //unused, could be added to PrisonerHelper
    private void setAccessoryLayer(boolean hasGlasses) {
        if (hasGlasses == true)
            accessoryLayer.setImageResource(PrisonerAttributes.GREY_GLASSES);
    }

}
