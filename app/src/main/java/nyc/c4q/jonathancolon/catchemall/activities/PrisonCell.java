package nyc.c4q.jonathancolon.catchemall.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nyc.c4q.jonathancolon.catchemall.R;
import nyc.c4q.jonathancolon.catchemall.Views.PrisonerAttributeLayers;

public class PrisonCell extends Activity {
    public Button createPrisonerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prison_cell);

        final PrisonerAttributeLayers prisonerViews = new PrisonerAttributeLayers(this);

        createPrisonerButton = (Button) findViewById(R.id.prisoner_button);

        createPrisonerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prisonerViews.generatePrisonerSprite();
            }
        });


    }
}
