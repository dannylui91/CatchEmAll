package nyc.c4q.jonathancolon.catchemall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import nyc.c4q.jonathancolon.catchemall.models.prisoner.Prisoner;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerAttributes;
import nyc.c4q.jonathancolon.catchemall.models.prisoner.PrisonerHelper;

import static android.view.View.GONE;

public class SecondActivity extends AppCompatActivity {
    public static final String PRISONER_KEY = "prisoner_key";
    private ImageView eyeColorLayer;
    private ImageView skinToneLayer;
    private ImageView hairStyleLayer;
    private ImageView accessoryLayer;
    private ImageView beardLayer;
    private Button createPrisonerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initViews();

        Intent intent = getIntent();
        final Prisoner prisoner = (Prisoner) intent.getExtras().getSerializable(PRISONER_KEY);

        displayPrisoner(prisoner);

        createPrisonerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call main activity
                MainActivity.onPrisonerCapture(prisoner);
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        eyeColorLayer = (ImageView) findViewById(R.id.eyes);
        skinToneLayer = (ImageView) findViewById(R.id.skintone);
        hairStyleLayer = (ImageView) findViewById(R.id.hair);
        beardLayer = (ImageView) findViewById(R.id.beard);
        accessoryLayer = (ImageView) findViewById(R.id.accessory);
        createPrisonerButton = (Button) findViewById(R.id.prisoner_button);
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
