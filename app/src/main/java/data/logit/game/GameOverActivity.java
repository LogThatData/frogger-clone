package data.logit.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by AdminProgram on 6/10/2015.
 */
public class GameOverActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(null);
            setContentView(R.layout.game_over_layout);
            final Button restart = (Button) findViewById(R.id.restart);
            restart.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent restart = new Intent(GameOverActivity.this, GameActivity.class);
                    startActivity(restart);
                    GameOverActivity.this.finish();
                }
            }
            );
        }

}
