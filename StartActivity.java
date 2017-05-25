package com.kan_inc.alternateuniverse;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class StartActivity extends ActionBarActivity
{
    public static final String EXTRA_MESSAGE = "com.kan_inc.AlternateUniverse.NAME";
    static String charName = "newblet";
    static int level = 1;
    static int exp = 0;
    static int location = 0;
    static int armor = 0;
    static int weapon = 0;
    static int HPMax = 100;
    static int HPC = 100;
    static int HeroHit = 5;
    static int HeroMxDmg = 10;
    static int HeroMnDmg = 2;
    static int HeroDef = 5;
    static int HeroRcv = 10;
    static boolean GateC = true;
    static boolean MaxNew = true;
    static boolean newGame = true;
    static boolean secondGlance = false;
    boolean GDogRoomOneFirst = true;
    boolean GDogRoomTwoFirst = true;
    boolean GuardBossFirst = true;
    boolean eqRackFirstLook = true;
    static int maxEXP = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button loadB = (Button)findViewById(R.id.load_button);
        Button newGameB = (Button)findViewById(R.id.new_game_button);
        Button creditsB = (Button)findViewById(R.id.credits_button);
        Button guideB = (Button)findViewById(R.id.guide_button);

        loadB.setTextSize(20);
        newGameB.setTextSize(20);
        creditsB.setTextSize(20);
        guideB.setTextSize(20);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchCredits(View view)
    {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
    }

    public void launchGuide(View view)
    {
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
    }

    public void launchLoad(View view)
    {
        Intent intent = new Intent(this, LoadActivity.class);
        startActivity(intent);
    }

    public void startNewGame(View view)
    {
        Intent intent = new Intent(this, GameActivity.class);
        String gameData = popGameData();
        intent.putExtra(EXTRA_MESSAGE, gameData);
        startActivity(intent);
    }

    public String popGameData()
    {
        String gameData = charName + "<>" + level + "<>" + exp + "<>" + location + "<>" + armor
                + "<>" + weapon + "<>" + HPMax + "<>" + HPC + "<>" + HeroHit + "<>" + HeroMxDmg
                + "<>" + HeroMnDmg + "<>" + HeroDef + "<>" + HeroRcv + "<>" + GateC + "<>" + MaxNew
                + "<>" + newGame + "<>" + secondGlance + "<>" + GDogRoomOneFirst
                + "<>" + GDogRoomTwoFirst + "<>" + GuardBossFirst + "<>" + eqRackFirstLook + "<>" +
                maxEXP;
        return gameData;

    }
}
