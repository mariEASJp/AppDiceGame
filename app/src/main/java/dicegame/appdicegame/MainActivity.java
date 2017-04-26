package dicegame.appdicegame;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Widgets
    TextView txtResult;
  TextView txtScore;

    //Field for die-roll
    Random ran;

    //Field to hold the dice values
    int[] dice;
    //Field to hold each die score
    int[] dieScores;

    //Field to hold final score
    int score;

    ArrayList<ImageView> dieImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rollDice(view);

            }
        });

        ran = new Random();


        txtResult = (TextView) findViewById(R.id.txtResult);
        txtScore = (TextView) findViewById(R.id.txtScore);

        dice = new int[3];
        dieScores= new int [3];


        ImageView die1Image = (ImageView) findViewById(R.id.imgDie1);
        ImageView die2Image = (ImageView) findViewById(R.id.imgDie2);
        ImageView die3Image = (ImageView) findViewById(R.id.imgDie3);

        dieImages.add(die1Image);
        dieImages.add(die2Image);
        dieImages.add(die3Image);

        Toast.makeText(getApplicationContext(),"Welcome to the game!", Toast.LENGTH_SHORT).show();

    }


    public void rollDice(View v){
        txtResult.setText("Clicked!");



        //roll dice
        //  for (int i = 0; i < dice.length; i++){
        //      dice[i] = ran.nextInt(6) + 1;
        //      score [i] = dice[i];
        //  }


        for (int dieOfSet = 0; dieOfSet < 3; dieOfSet++) {
            String imageName = "die_" + dice[dieOfSet] + ".png";

            try{

                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                 dieImages.get(dieOfSet).setImageDrawable(d);

              } catch (IOException e){

                 e.printStackTrace();
              }
        }



        //Build message with result
       // String msg = "You threw: " + dice[0] + ", " + dice[1] + " and " + dice[2];
        String msg = "";

        if ((dice[0] == dice[1]) && dice[1] == dice[2]) {
            //Triples the score
            int scoreDelta = dice[0] *100;
            msg = "You rolled a triple " + dice[0] + "! You score " + scoreDelta + "points!";
            score = score + scoreDelta;
        } else if ( dice[0] == dice[1] || dice[1] == dice[2]){
            //Doubles
            score = score + 50;
        } else {
            msg = "You didn't score in this roll - try again!";
        }

        //Update app to display result message
        txtResult.setText(msg);

       /* int num = ran.nextInt(6) + 1;
        String ranValue = "Number thrown: " + num;
        Toast.makeText(getApplicationContext(), ranValue,Toast.LENGTH_SHORT).show(); */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
