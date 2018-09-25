package com.techexchange.mobileapps.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ImageView views[];
    private boolean rightPosition[];
    private int tiles[];
    private int emptyIndex;
    private boolean gameFinished = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiles = new int[30];
        views = new ImageView[9];
        rightPosition = new boolean[9];
        for (int i = 0; i < rightPosition.length; i++){
            rightPosition[i] = false;
        }

        tiles[0] = R.drawable.tile000;
        tiles[1] = R.drawable.tile001;
        tiles[2] = R.drawable.tile002;
        tiles[3] = R.drawable.tile003;
        tiles[4] = R.drawable.tile004;
        tiles[5] = R.drawable.tile005;
        tiles[6] = R.drawable.tile006;
        tiles[7] = R.drawable.tile007;
        tiles[8] = R.drawable.tile008;
        tiles[9] = R.drawable.tile009;
        tiles[10] = R.drawable.tile010;
        tiles[11] = R.drawable.tile011;
        tiles[12] = R.drawable.tile012;
        tiles[13] = R.drawable.tile013;
        tiles[14] = R.drawable.tile014;
        tiles[15] = R.drawable.tile015;
        tiles[16] = R.drawable.tile016;
        tiles[17] = R.drawable.tile017;
        tiles[18] = R.drawable.tile018;
        tiles[19] = R.drawable.tile019;
        tiles[20] = R.drawable.tile020;
        tiles[21] = R.drawable.tile021;
        tiles[22] = R.drawable.tile022;
        tiles[23] = R.drawable.tile023;
        tiles[24] = R.drawable.tile024;
        tiles[25] = R.drawable.tile025;
        tiles[26] = R.drawable.tile026;
        tiles[27] = R.drawable.tile027;
        tiles[28] = R.drawable.tile028;
        tiles[29] = R.drawable.tile029;

        views[0] = findViewById(R.id.num01);
        views[1] = findViewById(R.id.num02);
        views[2] = findViewById(R.id.num03);
        views[3] = findViewById(R.id.num04);
        views[4] = findViewById(R.id.num05);
        views[5] = findViewById(R.id.num06);
        views[6] = findViewById(R.id.num07);
        views[7] = findViewById(R.id.num08);
        views[8] = findViewById(R.id.num09);

        for(int i = 0; i < views.length; i++) {
            if (i <= 7) {
                views[i].setImageResource(tiles[i + 1]);
                views[i].setTag(tiles[i + 1]);
            }
            views[i].setOnClickListener(this::OnNumberMove);
        }

        emptyIndex = 8;
        views[emptyIndex].setTag(000);
        TileShuffler();
    }

    private void TileShuffler()
    {
        for(int i = 0; i < 100; i++){

            Random move = new Random();
            int index = move.nextInt(8) + 1;

            if (emptyIndex == index + 1 || emptyIndex == index - 1 || emptyIndex == index + 3 || emptyIndex == index - 3){
                LoserLocation();
                views[emptyIndex].setImageDrawable(views[index].getDrawable());
                views[emptyIndex].setTag(views[index].getTag());
                views[index].setImageDrawable(null);
                views[index].setTag(000);
                emptyIndex = index;
                WinningLocation();
            }
            else{
                i--;
            }
        }

    }

    private void OnNumberMove(View v){
        ImageView image = (ImageView) v;
        int index = -1;
        for(int i = 0; i < views.length; i++){
            if(image == views[i]){
                index = i;
            }
        }
        if((gameFinished == false) && (emptyIndex == index + 1 || emptyIndex == index - 1 || emptyIndex == index + 3 || emptyIndex == index - 3)){
            if(!((index == 2 && emptyIndex == 3)||(index == 5 && emptyIndex == 6)|| (index == 3 && emptyIndex == 2)||(index == 6 && emptyIndex == 5))){
                LoserLocation();
                views[emptyIndex].setImageDrawable(views[index].getDrawable());
                views[emptyIndex].setTag(views[index].getTag());
                views[index].setImageDrawable(null);
                views[index].setTag(000);
                emptyIndex = index;
                WinningLocation();
                TheBattleIsWon();
            }
        }
    }

    private void WinningLocation(){
        for(int i = 0; i < views.length; i++){
            if((int) views[i].getTag() == tiles[i+1]){
                views[i].setImageResource(tiles[i+21]);
                rightPosition[i] = true;
            }
        }
    }

    private void LoserLocation()
    {
        for(int i = 0; i < views.length; i++){
            if(rightPosition[i] == true && (int) views[i].getTag() != tiles[i+21]){
                views[i].setImageResource(tiles[i+1]);
                rightPosition[i] = false;
            }
        }
    }

    private void TheBattleIsWon()
    {
        for(int i = 0; i < views.length - 1; i++)
        {
            if ((int) views[i].getTag() != tiles[i+1])
            {
                return;
            }
        }
        if((int) views[8].getTag() == 000)
        {
            Toast.makeText(getApplicationContext(), "YAY! You Won!! CONGRATS!!!", Toast.LENGTH_LONG).show();
            gameFinished = true;
        }
    }
}
