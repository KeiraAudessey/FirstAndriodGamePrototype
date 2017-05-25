package com.kan_inc.alternateuniverse;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.Random;


public class GameActivity extends ActionBarActivity
{

    public final static String EXTRA_MESSAGE = "com.kan_inc.AlternateUniverse.NAME";

    /*************************************************************************************
     *************************************************************************************
                        Read in from primary save file
     *************************************************************************************
     *     These are all the global variables that are loaded when a new game is started
     *     or a saved game is loaded.  They include character information and world
     *     state information.
     ************************************************************************************/

    int RFoeCount = 3; //current number of random foes to chose from




    String [] LocationNames = {"Safe Room", "Smithy", " Hall", "Lounge", "Store Room", "War Room",
                                "Treasury", "Hall", "Sitting Room", "Courtyard", "Gatehouse",
                                "the Wilds"};

    /********************************************************************************************
     ********************************************************************************************
                                    Pre-set game values
     ********************************************************************************************
     *******************************************************************************************/

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                                Game Items data
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/




    String bread = "bread_pic";


    /*******************************************************************************************
     *                          Unsaved Global Variables
     ******************************************************************************************/

    int FoeID;
    NPCActor cFoe;


    boolean FoeDefends = false;
    boolean HeroDefends = false;
    static boolean combat = false;
    static boolean journal = false;
    static boolean clickView = false;
    static boolean charView = false;
    static boolean packView = false;
    boolean playerTurn = true;
    String combatText = "";
    boolean Victory = false;
    boolean Defeat = false;
    boolean foeChosen = false;
    PlayerCharacter Hero;
    int location;



    private final Random generator = new Random();




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        String gameData = intent.getStringExtra(EXTRA_MESSAGE);

        Hero = new PlayerCharacter(gameData);
        location = Hero.getLocation();

        setContentView(R.layout.activity_game);

        currentLook();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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



    public void currentLook()
    {

        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        Button ngB = (Button) findViewById(R.id.ngbutton);
        ngB.setVisibility(View.INVISIBLE);

        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.INVISIBLE);

        B1.setTextSize(20);
        B2.setTextSize(20);
        B3.setTextSize(20);
        B4.setTextSize(20);
        B5.setTextSize(20);
        B6.setTextSize(20);
        B7.setTextSize(20);
        B8.setTextSize(20);
        BU.setTextSize(20);
        story.setTextSize(20);

        pIn.setVisibility(View.INVISIBLE);

        if(Hero.getMaxNew() && Hero.getNewGame())
        {

            pIn.setVisibility(View.VISIBLE);
            B1.setVisibility(View.INVISIBLE);
            B2.setVisibility(View.INVISIBLE);
            B3.setVisibility(View.INVISIBLE);
            B4.setVisibility(View.INVISIBLE);
            B5.setVisibility(View.INVISIBLE);
            B6.setVisibility(View.INVISIBLE);
            B7.setVisibility(View.INVISIBLE);
            B8.setVisibility(View.INVISIBLE);
            BU.setVisibility(View.INVISIBLE);
            miniPic.setVisibility(View.INVISIBLE);





            ngB.setVisibility(View.VISIBLE);
            story.setText(R.string.NewGameGreetings);
            pIn.setHint("Enter Name");

        }

        else if(Hero.getNewGame() && !packView && !charView && !journal)
        {
            pIn.setVisibility(View.INVISIBLE);

            B1.setVisibility(View.VISIBLE);
            B2.setVisibility(View.VISIBLE);
            B3.setVisibility(View.VISIBLE);
            B4.setVisibility(View.VISIBLE);
            B5.setVisibility(View.VISIBLE);
            B6.setVisibility(View.VISIBLE);
            B7.setVisibility(View.VISIBLE);
            B8.setVisibility(View.VISIBLE);
            BU.setVisibility(View.VISIBLE);
            miniPic.setVisibility(View.VISIBLE);
            ngB.setVisibility(View.INVISIBLE);

            if(!Hero.getSecondGlance())
            {
                String upsies =
                        String.format(getResources().getString(R.string.WakeUp),
                                Hero.getPlayerName());

                story.setText(upsies);
            }
            else
            {
                story.setText(R.string.SafeRoom);
            }


            miniPic.setImageResource(R.drawable.safe_room);

            B1.setText("Character");
            B2.setText("Inventory");
            B3.setText("Journal");

            B4.setText("Window");
            B5.setText("Bed");
            B6.setText("Fire Pit");
            B7.setText("Basin");
            B8.setText("North");
            BU.setText("Save");


        }

        else if(charView)
        {
            CharacterViewUI();
        }
        else if(journal)
        {
            JournalViewUI();
        }
        else if(packView)
        {
            InventoryViewUI();
        }
        else if(combat)
        {
            combatInterface();
        }

        else if(!Hero.getNewGame())
        {
            HHP.setVisibility(View.VISIBLE);
            HHP.setText(Hero.getStatsString());

            if (location == 0)//safeRoom
            {

                StrongHoldSafeRoomUI();

            } else if (location == 1)//Smithy
            {
                StrongHoldSmithyUI();
            }
            else if (location == 2)//HallOne
            {
                StrongHoldHallOneUI();
            }
            else if (location == 3)//Lounge
            {
                StrongHoldLoungeUI();
            } else if (location == 4)//StoreRoom
            {
                StrongHoldStoreRoomUI();
            }
            else if (location == 5)//WarRoom
            {
                StrongHoldWarRoomUI();
            }
            else if (location == 6)//Treasury
            {
                StrongHoldTreasuryUI();
            }
            else if (location == 7)//SecondHall
            {
                StrongHoldHallTwoUI();

            } else if (location == 8)//SittingRoom
            {
                StrongHoldSittingRoomUI();
            } else if (location == 9)//CourtYard
            {
                StrongHoldCourtYardUI();
            } else if (location == 10)//GateHouse
            {
                StrongHoldGateHouseUI();
            }
            else if(location == 11)
            {
                FreedomCrossroadsUI();
            }
        }


    }



    public void onClick(View view)
    {

        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        HHP.setVisibility(View.INVISIBLE);
        FHP.setVisibility(View.INVISIBLE);

        Button ngB = (Button) findViewById(R.id.ngbutton);

        int bID = view.getId();

        if(Hero.getMaxNew())
        {
            if(bID == ngB.getId())
            {
                    String charName = pIn.getText().toString();
                    Hero.setPlayerName(charName);
                    Hero.setMaxNew();
                    currentLook();
            }
        }

        else if(Hero.getNewGame() && !packView && !charView && !journal)
        {
            Hero.setSecondGlance();
            if(bID == B1.getId())//view character sheet
            {
                characterView(view);
            }
            else if(bID == B2.getId())//view inventory
            {
                inventoryView(view);
            }
            else if(bID == B3.getId())//view journal
            {
                journalView(view);
            }
            else if(bID == B4.getId())//look out the window
            {
                story.setText("You peer out the window.");
                clickView = true;
                BU.setText("Back");
            }
            else if (bID == B5.getId())//look at the bed
            {
                story.setText("There's a bed here. Perhaps you could use it to rest.");
                clickView = true;
                BU.setText("Back");
            }
            else if(bID == B6.getId())//look at the fire pit and such
            {
                story.setText("There's a fire pit, cooking tools, and logs  near the window.");
                clickView = true;
                BU.setText("Back");
            }
            else if(bID == B7.getId())//look at the basin
            {
                story.setText("There's a large basin with running water. It looks pristine.");
                clickView = true;
                BU.setText("Back");
            }
            else if(bID == B8.getId())//move to the smithy - First battle
            {
                clickView = false;
                location = 1;//move to the Smithy
                Hero.setNewGame();
                combat = true;
                combatInterface();

            }

            else if(bID == BU.getId())//utility button
            {
                    if(!clickView)//if the view is appropriate for saving
                    {
                        launchSaveAct(view);//launch save activity
                    }
                    else//otherwise go back to the main view for current data
                    {
                        clickView = false;
                        currentLook();
                    }
            }

        }

        else if(charView)
        {
            if(bID == BU.getId())//return to main view
            {
                clickView = false;
                charView = false;
                currentLook();
            }


        }

        else if(journal)
        {
            if(bID == BU.getId())//return to main view
            {
                clickView = false;
                journal = false;
                currentLook();
            }

        }

        else if(packView)
        {
            if(bID == BU.getId())//return to main view
            {
                clickView = false;
                packView = false;
                currentLook();
            }

        }

        else if(combat)//if in combat go to the combat listener function
        {
            combatListener(view);
        }

        else
        {

            if(location == 0)//SafeRoom
            {
                StrongHoldSafeRoomOnClick(view);
            }
            else if(location ==1)//Smithy
            {
                StrongHoldSmithyOnClick(view);
            }
            else if(location ==2)//HallOne
            {
                StrongHoldHallOneOnClick(view);
            }
            else if(location == 3)//Lounge
            {
                StrongHoldLoungeOnClick(view);
            }
            else if(location == 4)//StoreRoom
            {
                StrongHoldStoreRoomOnClick(view);
            }
            else if(location == 5)//WarRoom
            {
                StrongholdWarRoomOnClick(view);
            }
            else if(location ==6)//Treasury
            {
                StrongHoldTreasuryOnClick(view);
            }
            else if(location == 7)//HallTwo
            {
                StrongHoldHallTwoOnClick(view);
            }
            else if(location == 8)//SittingRoom
            {
                StrongHoldSittingRoomOnClick(view);
            }
            else if(location == 9)//Courtyard
            {
                StrongHoldCourtYardOnClick(view);
            }
            else if(location == 10)//Gatehouse
            {
                StrongHoldGateHouseOnClick(view);
            }
            else if(location == 11)//Freedom!
            {
                FreedomCrossRoadsOnClick(view);
            }

        }

    }



    public void characterView(View view)
    {
        clickView = true;
        charView = true;


    }
    public void CharacterViewUI()
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        Button ngB = (Button) findViewById(R.id.ngbutton);

        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.INVISIBLE);
        B2.setVisibility(View.INVISIBLE);
        B3.setVisibility(View.INVISIBLE);
        B4.setVisibility(View.INVISIBLE);
        B5.setVisibility(View.INVISIBLE);
        B6.setVisibility(View.INVISIBLE);
        B7.setVisibility(View.INVISIBLE);
        B8.setVisibility(View.INVISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);

        ngB.setVisibility(View.INVISIBLE);


        BU.setText("Back");
        miniPic.setImageResource(R.drawable.char_stat_view);
        story.setText(R.string.HeroDesc);
    }



    public void journalView(View view)
    {
        clickView = true;
        journal = true;
        currentLook();

    }

    public void JournalViewUI()
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        Button ngB = (Button) findViewById(R.id.ngbutton);

        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.INVISIBLE);
        B2.setVisibility(View.INVISIBLE);
        B3.setVisibility(View.INVISIBLE);
        B4.setVisibility(View.INVISIBLE);
        B5.setVisibility(View.INVISIBLE);
        B6.setVisibility(View.INVISIBLE);
        B7.setVisibility(View.INVISIBLE);
        B8.setVisibility(View.INVISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);

        ngB.setVisibility(View.INVISIBLE);


        BU.setText("Back");
        miniPic.setImageResource(R.drawable.journal_pic);//replace w/ pic of open tome
        story.setText(R.string.JournalEntryOne);
    }


    public void inventoryView(View view)
    {
        clickView = true;
        packView = true;
        currentLook();
    }

    public void InventoryViewUI()
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        Button ngB = (Button) findViewById(R.id.ngbutton);

        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.INVISIBLE);
        B2.setVisibility(View.INVISIBLE);
        B3.setVisibility(View.INVISIBLE);
        B4.setVisibility(View.INVISIBLE);
        B5.setVisibility(View.INVISIBLE);
        B6.setVisibility(View.INVISIBLE);
        B7.setVisibility(View.INVISIBLE);
        B8.setVisibility(View.INVISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);

        ngB.setVisibility(View.INVISIBLE);

        BU.setText("Back");

        int itemIm = getResources().getIdentifier(bread, "drawable", getPackageName());
        miniPic.setImageResource(itemIm);//replace w/ pic of an open pack
        story.setText(R.string.InventoryContents);
    }


    public void combatInterface()
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        Button ngB = (Button) findViewById(R.id.ngbutton);

        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.VISIBLE);




        String ID;

        if(!foeChosen)
        {
            FHP.setText("");
            if(location == 1 && Hero.getGDogRoomOneFirst())
            {
                ID = getString(R.string.GuardDogOneData);
                Hero.setGDogRoomOneFirst();
            }
            else if(location == 4 && Hero.getGDogRoomTwoFirst())
            {
                ID = getString(R.string.GuardDogTwoData);
                Hero.setGDogRoomTwoFirst();
            }
            else if(location == 9 && Hero.getGuardBossFirst())
            {
                ID = getString(R.string.SHGuardData);
                Hero.setGuardBossFirst();
            }
            else
            {
                int who = generator.nextInt(RFoeCount);

                if(who == 1)
                {
                    ID = getString(R.string.SpiderData);
                }
                else if(who == 2)
                {
                    ID = getString(R.string.SlimeData);
                }
                else
                {
                    ID = getString(R.string.RatData);
                }
            }

            cFoe = new NPCActor(ID);
            foeChosen = true;

            FoeID = getFoeImageId();
            int TextID = getFoeText("Appears");
            combatText += getCombatStartText();
            combatText += getString(TextID);
            story.setText(combatText + "\n\n");
            HHP.setText(Hero.getPlayerName() + "HP: " + Hero.getHPC() + "/" + Hero.getHPMax());

            FHP.setText(cFoe.getName() + "HP: " + cFoe.getHPC() + "/" + cFoe.getHPMax());

        }
        HHP.setText(Hero.getPlayerName() + "HP: " + Hero.getHPC() + "/" + Hero.getHPMax());

        FHP.setText(cFoe.getName() + "HP: " + cFoe.getHPC() + "/" + cFoe.getHPMax());


        if(playerTurn && !Victory && !Defeat)
        {
            pIn.setVisibility(View.INVISIBLE);

            B1.setVisibility(View.INVISIBLE);
            B2.setVisibility(View.INVISIBLE);
            B3.setVisibility(View.INVISIBLE);
            B4.setVisibility(View.VISIBLE);
            B5.setVisibility(View.VISIBLE);
            B6.setVisibility(View.VISIBLE);
            B7.setVisibility(View.VISIBLE);
            B8.setVisibility(View.INVISIBLE);
            BU.setVisibility(View.INVISIBLE);
            miniPic.setVisibility(View.VISIBLE);

            ngB.setVisibility(View.INVISIBLE);

            B4.setText("Attack");
            B5.setText("Defend");
            B6.setText("Inventory");
            B7.setText("Flee");
            miniPic.setImageResource(FoeID);

            HHP.setText(Hero.getPlayerName() + "HP: " + Hero.getHPC() + "/" + Hero.getHPMax());

            FHP.setText(cFoe.getName() + "HP: " + cFoe.getHPC() + "/" + cFoe.getHPMax());

            story.setText(combatText + "\n\n");

        }
        else if(!playerTurn && !Victory && !Defeat)
        {
            pIn.setVisibility(View.INVISIBLE);

            B1.setVisibility(View.INVISIBLE);
            B2.setVisibility(View.INVISIBLE);
            B3.setVisibility(View.INVISIBLE);
            B4.setVisibility(View.VISIBLE);
            B5.setVisibility(View.INVISIBLE);
            B6.setVisibility(View.INVISIBLE);
            B7.setVisibility(View.INVISIBLE);
            B8.setVisibility(View.INVISIBLE);
            BU.setVisibility(View.INVISIBLE);
            miniPic.setVisibility(View.VISIBLE);

            ngB.setVisibility(View.INVISIBLE);
            miniPic.setImageResource(FoeID);

            HHP.setText(Hero.getPlayerName() + "HP: " + Hero.getHPC() + "/" + Hero.getHPMax());

            FHP.setText(cFoe.getName() + "HP: " + cFoe.getHPC() + "/" + cFoe.getHPMax());

            B4.setText("Next");

            story.setText(combatText + "\n\n");
        }
        else if(Victory)
        {
            pIn.setVisibility(View.INVISIBLE);

            B1.setVisibility(View.INVISIBLE);
            B2.setVisibility(View.INVISIBLE);
            B3.setVisibility(View.INVISIBLE);
            B4.setVisibility(View.INVISIBLE);
            B5.setVisibility(View.VISIBLE);
            B6.setVisibility(View.INVISIBLE);
            B7.setVisibility(View.INVISIBLE);
            B8.setVisibility(View.INVISIBLE);
            BU.setVisibility(View.INVISIBLE);
            miniPic.setVisibility(View.VISIBLE);

            ngB.setVisibility(View.INVISIBLE);
            HHP.setText(Hero.getPlayerName() + "HP: " + Hero.getHPC() + "/" + Hero.getHPMax());

            FHP.setText(cFoe.getName() + "HP: " + "0/" + cFoe.getHPMax());

            miniPic.setImageResource(R.drawable.hero_wins);


            story.setText(combatText + "\n\n");
            B5.setText("Continue");
        }
        else if(Defeat)
        {
            pIn.setVisibility(View.INVISIBLE);

            B1.setVisibility(View.INVISIBLE);
            B2.setVisibility(View.INVISIBLE);
            B3.setVisibility(View.INVISIBLE);
            B4.setVisibility(View.INVISIBLE);
            B5.setVisibility(View.VISIBLE);
            B6.setVisibility(View.INVISIBLE);
            B7.setVisibility(View.INVISIBLE);
            B8.setVisibility(View.INVISIBLE);
            BU.setVisibility(View.VISIBLE);
            miniPic.setVisibility(View.VISIBLE);

            ngB.setVisibility(View.INVISIBLE);

            miniPic.setImageResource(R.drawable.hero_rip);

            HHP.setText(Hero.getPlayerName() + "HP: " + Hero.getHPC() + "/" + Hero.getHPMax());

            FHP.setText(cFoe.getName() + "HP: " + cFoe.getHPC() + "/" + cFoe.getHPMax());

            story.setText(combatText + "\n\n");
            B5.setText("Quit");
            BU.setText("Load");

        }
        else
        {
            story.setText("No, you can't be here. Go back!");
        }


        ScrollView storyScroller = (ScrollView)findViewById(R.id.storyScroll);
        storyScroller.fullScroll(View.FOCUS_DOWN);


    }

    public void combatListener(View view)
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);
        Button ngB = (Button) findViewById(R.id.ngbutton);

        int bID = view.getId();
        int attack;
        int dmg;
        int defRoll;
        int heal;
        int flee;
        int foeAct;


        if(playerTurn && !Victory && !Defeat)
        {
            playerTurn = false;
            if(bID == B4.getId())//attack
            {
                attack = (generator.nextInt(20))+Hero.getPhysHit();
                dmg = generator.nextInt((Hero.getMxPhysDmg() - Hero.getMnPhysDmg()) + 1) +
                        Hero.getMnPhysDmg();
                if(!FoeDefends)
                    defRoll = (generator.nextInt(20))+ (cFoe.getDefense()/4);
                else
                {
                    defRoll = (generator.nextInt(20))+ cFoe.getDefense();
                }



                if(attack > defRoll)//attack hits
                {
                    cFoe.setHPC(-dmg);
                    if(cFoe.getHPC() <= 0)
                    {
                        combatText += "\n\n" + String.format(getString(getHeroText("Strikes")), dmg)
                                + "\n\n" + getString(getFoeText("Dies"));

                        combatText += "\n\nYou gain " + cFoe.getEXPReward() + " exp." ;
                        int PlatR = 0, GoldR = 0, SilverR = 0, CopperR = 0;
                        if(cFoe.getCopperR() > -1)
                        {

                            if(cFoe.getSilverR() > -1)
                            {
                                if(cFoe.getGoldR() > -1)
                                {
                                    if(cFoe.getPlatinumR() > -1)
                                    {
                                        PlatR = generator.nextInt(cFoe.getPlatinumR());

                                    }
                                    GoldR = generator.nextInt(cFoe.getGoldR());

                                }
                                SilverR = generator.nextInt(cFoe.getSilverR());

                            }
                            CopperR = generator.nextInt(cFoe.getCopperR());

                        }
                        if(PlatR > 0 || GoldR > 0 || SilverR > 0 || CopperR >0)
                        {
                            combatText += "You also found ";

                            String PlatRS = "";
                            String GoldRS = "";
                            String SilverRS = "";
                            String CopperRS = "";

                            if (PlatR > 0)
                            {
                                PlatRS += PlatR + "p ";
                            }
                            if (GoldR > 0)
                            {
                                GoldRS += GoldR + "g ";

                            }
                            if (SilverR > 0)
                            {
                                SilverRS += SilverR + "s ";
                            }
                            if (CopperR > 0)
                            {
                                CopperRS += CopperR + "c ";
                            }
                            combatText += PlatRS + GoldRS + SilverRS + CopperRS;

                        }

                        Victory = true;
                        Hero.setHPC(Hero.getRcv());

                        Hero.setExp(cFoe.getEXPReward());
                        if(Hero.getExp() >= Hero.getMaxEXP())
                        {
                            Hero.LevelUp();
                        }
                        combatInterface();
                    }
                    else
                    {
                        combatText += "\n\n" + String.format(getString(getHeroText("Strikes")), dmg)
                                + "\n\n" + getString(getFoeText("Injured"));
                    }
                }
                else//attack misses
                {
                    if(FoeDefends)
                    {
                        heal = (generator.nextInt(cFoe.getRcvrMax()));
                        if(heal < cFoe.getRcvrMin())
                        {
                            heal = cFoe.getRcvrMin();
                        }
                        cFoe.setHPC(heal);

                        combatText += "\n\n" + getString(getHeroText("Misses")) + "\n\n" +
                                String.format(getString(getFoeText("Recovers")), heal);
                    }
                    else
                    {
                        combatText += "\n\n" + getString(getHeroText("Misses")) + "\n\n" +
                                    getString(getFoeText("Waits"));
                    }
                }

            }
            else if(bID == B5.getId())//defend
            {
                HeroDefends = true;
                combatText += "\n\n" + getString(getHeroText("Defends"));

            }
            else if(bID == B6.getId())//Inventory
            {
                combatText += "\n\n You sense doing that just now might get you killed . . .";
                playerTurn = true;
                combatInterface();
            }
            else if(bID == B7.getId())//flee
            {
                flee = (generator.nextInt(20));
                if(flee > 10)
                {
                    combat = false;
                    playerTurn = true;
                    foeChosen = false;
                    FoeDefends = false;
                    HeroDefends = false;

                    combatText = "";
                    Victory = false;
                    Defeat = false;
                    currentLook();
                }
                else
                {
                    combatText += "\n\nYou tried to escape, but your foe won't let you get away.";
                }

            }

            //determine foe's action

            if(!Victory)
            {
                foeAct = (generator.nextInt(15));
                if(cFoe.getHPC() >= (cFoe.getWhatIsHurt() *1.5) )
                {
                    foeAct += 10 - cFoe.getDefPrefBase();
                }
                else if(cFoe.getHPC() <= cFoe.getWhatIsHurt())
                {
                    foeAct = foeAct - cFoe.getDefPrefHurt();
                }
                else
                {
                    foeAct -= cFoe.getDefPrefBase();
                }
                if(foeAct > 10)//foe will attack
                {
                    attack = generator.nextInt(20)+cFoe.getPHit();
                    dmg =  generator.nextInt((cFoe.getPhysMaxDmg() - cFoe.getPhysMinDmg()) + 1) +
                            cFoe.getPhysMinDmg();
                    if(HeroDefends)
                    {
                        defRoll = (generator.nextInt(20))+ (Hero.getDef()/4);
                    }
                    else
                    {
                        defRoll = (generator.nextInt(20))+ Hero.getDef();
                    }
                    if(attack > defRoll)//attack was successful
                    {
                        Hero.setHPC(-dmg);
                        if(Hero.getHPC() <= 0)
                        {
                            combatText += "\n\n" + String.format(getString(getFoeText("Strikes")), dmg)
                                    + getString(getHeroText("Dies"));
                            Defeat = true;
                            combatInterface();

                        }
                        else
                        {
                            combatText += "\n\n" + String.format(getString(getFoeText("Strikes")), dmg);
                        }
                    }
                    else//attack missed
                    {
                        if(HeroDefends)//hero was defending and will recover some health
                        {
                            heal = (generator.nextInt(Hero.getRcv()));
                            Hero.setHPC(heal);

                            combatText += "\n\n" + getString(getFoeText("Misses")) + "\n\n" +
                                    String.format(getString(getHeroText("Recovers"), heal));
                        }
                        else //here was not defending
                        {
                            combatText += "\n\n" + getString(getFoeText("Misses"));
                        }
                    }

                }
                else//foe will defend
                {
                    FoeDefends = true;
                    combatText += "\n\n" + getString(getFoeText("Defends"));

                }
            }

            combatInterface();
        }


        else if(Victory)
        {
            if(bID == B5.getId())
            {
                combat = false;
                clickView = false;
                playerTurn = true;
                foeChosen = false;
                FoeDefends = false;
                HeroDefends = false;
                combatText = "";
                Victory = false;
                Defeat = false;
                currentLook();
            }
        }
        else if(Defeat)
        {
            combatText = "";
            if(bID == BU.getId())
            {
                launchLoadAct(view);
            }
            else if(bID == B5.getId());
            {
                launchStartAct(view);
            }

        }
        else
        {
            if(bID == B4.getId())
            {
                playerTurn = true;
                combatInterface();
            }

        }



    }

    public int getFoeText(String fetching)
    {
        String pass = cFoe.getStringID() + fetching;

        int chatID = getResources().getIdentifier(pass, "string", this.getPackageName());

        return chatID;
    }

    public int getHeroText(String fetching)
    {
        String pass = "Hero" + fetching;

        int chatID = getResources().getIdentifier(pass, "string", this.getPackageName());

        return chatID;
    }


    public int getFoeImageId()
    {
        int imgID = getResources().getIdentifier(cFoe.getPicID(), "drawable", this.getPackageName());
        return imgID;

    }


    public String getCombatStartText()
    {
        String cStartText;
        if(!clickView)
        {
            if(location == 1)
            {
                cStartText = getString(R.string.SmithyCombatText);
            }
            else if(location == 9)
            {
                cStartText = getString(R.string.CourtYardCombatText);
            }
            else
            {
                cStartText = String.format(getString(R.string.GenericEnterRoomCombatText),
                        LocationNames[location]);
            }
        }
        else
        {
            cStartText = String.format
                    (getString(R.string.GenericSearchCombatStart), LocationNames[location]);

        }

        return cStartText;

    }




    public void launchStartAct(View view)
    {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void launchLoadAct(View view)
    {
        Intent intent = new Intent(this, LoadActivity.class);
        startActivity(intent);
    }


    public void launchSaveAct(View view)
    {
        Intent intent = new Intent(this, SaveActivity.class);
        Hero.setLocation(location);
        String gameData = Hero.characterToString();
        intent.putExtra(EXTRA_MESSAGE, gameData);
        startActivity(intent);
    }


    public void StrongHoldSafeRoomUI()//0
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);




        Button ngB = (Button) findViewById(R.id.ngbutton);


        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.INVISIBLE);


        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.VISIBLE);
        B2.setVisibility(View.VISIBLE);
        B3.setVisibility(View.VISIBLE);
        B4.setVisibility(View.VISIBLE);
        B5.setVisibility(View.VISIBLE);
        B6.setVisibility(View.VISIBLE);
        B7.setVisibility(View.VISIBLE);
        B8.setVisibility(View.VISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);


        ngB.setVisibility(View.INVISIBLE);
        story.setText(R.string.SafeRoom);

        miniPic.setImageResource(R.drawable.safe_room);

        B1.setText("Character");
        B2.setText("Inventory");
        B3.setText("Journal");

        B4.setText("Window");
        B5.setText("Rest");
        B6.setText("Eat");
        B7.setText("Refresh");
        B8.setText("North");
        BU.setText("Save");

    }

    public void StrongHoldSafeRoomOnClick(View view)
    {
        int bID = view.getId();

        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        if(bID == B1.getId())//view character sheet
        {
            characterView(view);
        }
        else if(bID == B2.getId())//view inventory
        {
            inventoryView(view);
        }
        else if(bID == B3.getId())//view journal
        {
            journalView(view);
        }
        else if(bID == B4.getId())//look out the window
        {
            story.setText("You look out the window.");
            clickView = true;
            BU.setText("Back");
        }
        else if(bID == B5.getId())//sleep
        {
            story.setText("You close the door and lay down to sleep on the bed...\n\n" +
                    "When you wake up you feel rested and refreshed.");
            Hero.setHPC(Hero.getHPMax());
            clickView = true;
            BU.setText("Back");
        }
        else if(bID == B6.getId())//eat, later will recover energy and hunger
        {
            int heal = (Hero.getRcv()*2);
            Hero.setHPC(heal);

            story.setText("You prepare some food and eat, you recover " + heal + " health");

            clickView = true;
            BU.setText("Back");
        }
        else if(bID == B7.getId())//get a drink, use the loo, wash the blood off.
        {//later this will remove the debuff to appeal and charm incurred by killing foes.
            story.setText("You freshen up a bit, gratefully taking advantage of the " +
                    "facilities present.");
            clickView = true;
            BU.setText("Back");
        }
        else if(bID == B8.getId())//move to the smithy
        {
            clickView = false;
            location = 1;
            currentLook();
        }
        else if(bID == BU.getId())
        {
            if(!clickView)
            {
                launchSaveAct(view);
            }
            else
            {
                clickView = false;
                currentLook();
            }

        }
    }



    public void StrongHoldSmithyUI()//1
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.INVISIBLE);

        Button ngB = (Button) findViewById(R.id.ngbutton);

        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.VISIBLE);
        B2.setVisibility(View.VISIBLE);
        B3.setVisibility(View.VISIBLE);
        B4.setVisibility(View.VISIBLE);
        B5.setVisibility(View.VISIBLE);
        B6.setVisibility(View.VISIBLE);
        B7.setVisibility(View.VISIBLE);
        B8.setVisibility(View.VISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);


        story.setText(R.string.Smithy);
        ngB.setVisibility(View.INVISIBLE);

        miniPic.setImageResource(R.drawable.smithy);

        B1.setText("Character");
        B2.setText("Inventory");
        B3.setText("Journal");

        B4.setText("Smithy");
        B5.setText("Eq.Racks");
        B6.setText("Storage");
        B7.setText("North");
        B8.setText("South");
        BU.setText("Save");

    }

    public void StrongHoldSmithyOnClick(View view)
    {

        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        Button ngB = (Button) findViewById(R.id.ngbutton);

        int bID = view.getId();


        if(bID == B1.getId())
        {
            characterView(view);
        }
        else if(bID == B2.getId())
        {
            inventoryView(view);
        }
        else if(bID == B3.getId())
        {
            journalView(view);
        }
        else if(bID == B4.getId())//look at smithing tools
        {
            story.setText(R.string.SmithyText);
            clickView = true;
            BU.setText("Back");
        }

        else if(bID == B5.getId())//look at EqRack
        {
            if(Hero.getEQRackFirstLook())
            {
                eqRackFirstLook = false;
                story.setText(R.string.EqRackIniText);
                HeroDef += 3;
                HeroHit += 3;
                HeroMnDmg += 2;
                HeroMxDmg += 3;

            }
            else
            {
                story.setText(R.string.EqRacksEvermore);
            }
            clickView = true;
            BU.setText("Back");
        }

        else if(bID == B6.getId())
        {
            story.setText(R.string.MaterialChest);
            clickView = true;
            BU.setText("Back");
        }

        else if(bID == B7.getId())//move to Hall One
        {
            location = 2;
            clickView = false;
            int combatChance = (generator.nextInt(100));
            combatChance = combatChance + 15;
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }
        }
        else if(bID == B8.getId())//Move to the Safe Room
        {
            clickView = false;
            location = 0;
            currentLook();
        }

        else if(bID == BU.getId())
        {
            if(!clickView)
            {
                launchSaveAct(view);
            }
            else
            {
                clickView = false;
                currentLook();
            }

        }
    }



    public void StrongHoldHallOneUI()//2
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.INVISIBLE);

        Button ngB = (Button) findViewById(R.id.ngbutton);

        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.VISIBLE);
        B2.setVisibility(View.VISIBLE);
        B3.setVisibility(View.VISIBLE);
        B4.setVisibility(View.VISIBLE);
        B5.setVisibility(View.VISIBLE);
        B6.setVisibility(View.VISIBLE);
        B7.setVisibility(View.VISIBLE);
        B8.setVisibility(View.VISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);

        ngB.setVisibility(View.INVISIBLE);

        story.setText("Hall One");

        miniPic.setImageResource(R.drawable.hall_one);

        B1.setText("Character");
        B2.setText("Inventory");
        B3.setText("Journal");

        B4.setText("Search");
        B5.setText("N. Room");
        B6.setText("E. Room");
        B7.setText("W. Room");
        B8.setText("S. Room");
        BU.setText("Save");
    }
    public void StrongHoldHallOneOnClick(View view)
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        Button ngB = (Button) findViewById(R.id.ngbutton);

        int bID = view.getId();

        if(bID == B1.getId())
        {
            characterView(view);
        }
        else if(bID == B2.getId())
        {
            inventoryView(view);
        }
        else if(bID == B3.getId())
        {
            journalView(view);
        }
        else if(bID == B4.getId())//search
        {
            clickView = true;
            int combatChance = (generator.nextInt(100));
            combatChance = combatChance + 15;
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            story.setText(R.string.HallOneSearch);
            BU.setText("Back");
        }

        else if(bID == B5.getId())//move to war room
        {
            location = 5;
            clickView = false;
            int combatChance = (generator.nextInt(100));
            combatChance = combatChance + 15;
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }
        }

        else if(bID == B6.getId())//move to store room
        {
            clickView = false;
            location = 4;//StoreRoom
            int combatChance = (generator.nextInt(100));
            if(GDogRoomTwoFirst)
            {
                combat = true;
                combatInterface();
            }
            else if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }
        }
        else if(bID == B7.getId())//move to lounge
        {
            location = 3;//lounge
            clickView = false;
            int combatChance = (generator.nextInt(100));
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }
        }
        else if(bID == B8.getId())//move to smithy
        {
            clickView = false;
            location = 1;
            currentLook();
        }

        else if(bID == BU.getId())
        {
            if(!clickView)
            {
                launchSaveAct(view);
            }
            else
            {
                clickView = false;
                currentLook();
            }

        }

    }




    public void StrongHoldLoungeUI()//3
    {

        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.INVISIBLE);

        Button ngB = (Button) findViewById(R.id.ngbutton);

        int combatChance = (generator.nextInt(100));

        if(combatChance > 75)
        {
            combat = true;
            combatInterface();
        }

        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.VISIBLE);
        B2.setVisibility(View.VISIBLE);
        B3.setVisibility(View.VISIBLE);
        B4.setVisibility(View.VISIBLE);
        B5.setVisibility(View.INVISIBLE);
        B6.setVisibility(View.INVISIBLE);
        B7.setVisibility(View.INVISIBLE);
        B8.setVisibility(View.VISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);

        ngB.setVisibility(View.INVISIBLE);

        story.setText("Lounge");

        miniPic.setImageResource(R.drawable.lounge);

        B1.setText("Character");
        B2.setText("Inventory");
        B3.setText("Journal");

        B4.setText("Search");

        B8.setText("East");
        BU.setText("Save");

    }
    public void StrongHoldLoungeOnClick(View view)
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        Button ngB = (Button) findViewById(R.id.ngbutton);

        int bID = view.getId();

        if(bID == B1.getId())
        {
            characterView(view);
        }
        else if(bID == B2.getId())
        {
            inventoryView(view);
        }
        else if(bID == B3.getId())
        {
            journalView(view);
        }
        else if(bID == B4.getId())
        {

            clickView = true;
            int combatChance = (generator.nextInt(100));
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            story.setText(R.string.LoungeSearch);

            BU.setText("Back");
        }
        else if(bID == B8.getId())//move to Hallo one
        {
            location = 2;
            clickView = false;
            int combatChance = (generator.nextInt(100));
            combatChance = combatChance + 15;
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }

            currentLook();
        }

        else if(bID == BU.getId())
        {
            if(!clickView)
            {
                launchSaveAct(view);
            }
            else
            {
                clickView = false;
                currentLook();
            }

        }

    }



    public void StrongHoldWarRoomUI()//4
    {

        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.INVISIBLE);

        Button ngB = (Button) findViewById(R.id.ngbutton);



        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.VISIBLE);
        B2.setVisibility(View.VISIBLE);
        B3.setVisibility(View.VISIBLE);
        B4.setVisibility(View.VISIBLE);
        B5.setVisibility(View.INVISIBLE);
        B6.setVisibility(View.INVISIBLE);
        B7.setVisibility(View.VISIBLE);
        B8.setVisibility(View.VISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);

        ngB.setVisibility(View.INVISIBLE);

        story.setText("War Room");

        miniPic.setImageResource(R.drawable.war_room);

        B1.setText("Character");
        B2.setText("Inventory");
        B3.setText("Journal");

        B4.setText("Search");

        B7.setText("East");
        B8.setText("South");
        BU.setText("Save");


    }
    public void StrongholdWarRoomOnClick(View view)
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        Button ngB = (Button) findViewById(R.id.ngbutton);

        int bID = view.getId();

        if(bID == B1.getId())
        {
            characterView(view);
        }
        else if(bID == B2.getId())
        {
            inventoryView(view);
        }
        else if(bID == B3.getId())
        {
            journalView(view);
        }
        else if(bID == B4.getId())
        {

            clickView = true;
            int combatChance = (generator.nextInt(100));
            combatChance = combatChance + 15;
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            story.setText(R.string.WarRoomSearch);
            BU.setText("Back");
        }
        else if(bID == B7.getId())//move to treasury
        {
            location = 6;
            clickView = false;
            int combatChance = (generator.nextInt(100));
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }
        }
        else if(bID == B8.getId())//move to hall one
        {
            location = 2;
            clickView = false;
            int combatChance = (generator.nextInt(100));
            combatChance = combatChance + 15;
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }
        }

        else if(bID == BU.getId())
        {
            if(!clickView)
            {
                launchSaveAct(view);
            }
            else
            {
                clickView = false;
                currentLook();
            }

        }

    }


    public void StrongHoldTreasuryUI()//5
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.INVISIBLE);

        Button ngB = (Button) findViewById(R.id.ngbutton);

        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.VISIBLE);
        B2.setVisibility(View.VISIBLE);
        B3.setVisibility(View.VISIBLE);
        B4.setVisibility(View.VISIBLE);
        B5.setVisibility(View.INVISIBLE);
        B6.setVisibility(View.INVISIBLE);
        B7.setVisibility(View.INVISIBLE);
        B8.setVisibility(View.VISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);

        ngB.setVisibility(View.INVISIBLE);

        story.setText("Treasury");

        miniPic.setImageResource(R.drawable.treasury);

        B1.setText("Character");
        B2.setText("Inventory");
        B3.setText("Journal");

        B4.setText("Search");

        B8.setText("West");
        BU.setText("Save");

    }
    public void StrongHoldTreasuryOnClick(View view)
    {

        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        Button ngB = (Button) findViewById(R.id.ngbutton);

        int bID = view.getId();

        if(bID == B1.getId())
        {
            characterView(view);
        }
        else if(bID == B2.getId())
        {
            inventoryView(view);
        }
        else if(bID == B3.getId())
        {
            journalView(view);
        }
        else if(bID == B4.getId())
        {

            clickView = true;
            int combatChance = (generator.nextInt(100));
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            story.setText(R.string.TreasurySearch);
            BU.setText("Back");
        }
        else if(bID == B8.getId())//move to war room
        {
            location = 5;
            clickView = false;
            int combatChance = (generator.nextInt(100));
            combatChance = combatChance + 15;
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }
        }
        else if(bID == BU.getId())
        {
            if(!clickView)
            {
                launchSaveAct(view);
            }
            else
            {
                clickView = false;
                currentLook();
            }

        }
    }



    public void StrongHoldStoreRoomUI()//6
    {

        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.INVISIBLE);

        Button ngB = (Button) findViewById(R.id.ngbutton);


        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.VISIBLE);
        B2.setVisibility(View.VISIBLE);
        B3.setVisibility(View.VISIBLE);
        B4.setVisibility(View.VISIBLE);
        B5.setVisibility(View.INVISIBLE);
        B6.setVisibility(View.INVISIBLE);
        B7.setVisibility(View.VISIBLE);
        B8.setVisibility(View.VISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);

        ngB.setVisibility(View.INVISIBLE);

        story.setText("Store Room");

        miniPic.setImageResource(R.drawable.store_room);

        B1.setText("Character");
        B2.setText("Inventory");
        B3.setText("Journal");

        B4.setText("Search");

        B7.setText("North");
        B8.setText("East");
        BU.setText("Save");


    }
    public void StrongHoldStoreRoomOnClick(View view)
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        Button ngB = (Button) findViewById(R.id.ngbutton);

        int bID = view.getId();
        if(bID == B1.getId())
        {
            characterView(view);
        }
        else if(bID == B2.getId())
        {
            inventoryView(view);
        }
        else if(bID == B3.getId())
        {
            journalView(view);
        }
        else if(bID == B4.getId())
        {

            clickView = true;
            int combatChance = (generator.nextInt(100));
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            story.setText(R.string.StoreRoomSearch);

            BU.setText("Back");
        }
        else if(bID == B7.getId())//move to hallo one
        {
            clickView = true;
            location = 2;

            int combatChance = (generator.nextInt(100));
            combatChance = combatChance + 15;
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }
        }
        else if(bID == B8.getId())//move to hall two
        {
            location = 7;
            clickView = false;
            int combatChance = (generator.nextInt(100));
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }
        }

        else if(bID == BU.getId())
        {
            if(!clickView)
            {
                launchSaveAct(view);
            }
            else
            {
                clickView = false;
                currentLook();
            }

        }
        
    }




    public void StrongHoldHallTwoUI()//7
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.INVISIBLE);

        Button ngB = (Button) findViewById(R.id.ngbutton);


        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.VISIBLE);
        B2.setVisibility(View.VISIBLE);
        B3.setVisibility(View.VISIBLE);
        B4.setVisibility(View.VISIBLE);
        B5.setVisibility(View.INVISIBLE);
        B6.setVisibility(View.VISIBLE);
        B7.setVisibility(View.VISIBLE);
        B8.setVisibility(View.VISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);

        ngB.setVisibility(View.INVISIBLE);

        story.setText("Second Hall");

        miniPic.setImageResource(R.drawable.hall_two);

        B1.setText("Character");
        B2.setText("Inventory");
        B3.setText("Journal");

        B4.setText("Search");

        B6.setText("E. Room");
        B7.setText("W. Room");
        B8.setText("S. Room");
        BU.setText("Save");

    }
    public void StrongHoldHallTwoOnClick(View view)
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        Button ngB = (Button) findViewById(R.id.ngbutton);

        int bID = view.getId();

        if(bID == B1.getId())
        {
            characterView(view);
        }
        else if(bID == B2.getId())
        {
            inventoryView(view);
        }
        else if(bID == B3.getId())
        {
            journalView(view);
        }
        else if(bID == B4.getId())
        {

            clickView = true;
            int combatChance = (generator.nextInt(100));
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            story.setText(R.string.HallTwoSearch);

            BU.setText("Back");
        }
        else if(bID == B6.getId())//move to sitting room
        {
            location = 8;
            clickView = false;
            int combatChance = (generator.nextInt(100));
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }
        }
        else if(bID == B7.getId())//move to Store room
        {
            location = 4;
            clickView = false;
            int combatChance = (generator.nextInt(100));
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }
        }
        else if(bID == B8.getId())//move to courtyard
        {
            location = 9;
            clickView = false;
            if(GuardBossFirst)
            {
                combat = true;
                combatInterface();
            }
            currentLook();
        }
        else if(bID == BU.getId())
        {
            if(!clickView)
            {
                launchSaveAct(view);
            }
            else
            {
                clickView = false;
                currentLook();
            }
        }

    }



    public void StrongHoldSittingRoomUI()//8
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.INVISIBLE);

        Button ngB = (Button) findViewById(R.id.ngbutton);

        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.VISIBLE);
        B2.setVisibility(View.VISIBLE);
        B3.setVisibility(View.VISIBLE);
        B4.setVisibility(View.VISIBLE);
        B5.setVisibility(View.INVISIBLE);
        B6.setVisibility(View.INVISIBLE);
        B7.setVisibility(View.INVISIBLE);
        B8.setVisibility(View.VISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);

        ngB.setVisibility(View.INVISIBLE);

        story.setText("Sitting Room");

        miniPic.setImageResource(R.drawable.sitting_room);

        B1.setText("Character");
        B2.setText("Inventory");
        B3.setText("Journal");

        B4.setText("Search");

        B8.setText("West");
        BU.setText("Save");

    }
    public void StrongHoldSittingRoomOnClick(View view)
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        Button ngB = (Button) findViewById(R.id.ngbutton);

        int bID = view.getId();

        if(bID == B1.getId())
        {
            characterView(view);
        }
        else if(bID == B2.getId())
        {
            inventoryView(view);
        }
        else if(bID == B3.getId())
        {
            journalView(view);
        }
        else if(bID == B4.getId())
        {

            clickView = true;
            int combatChance = (generator.nextInt(100));
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            story.setText(R.string.SittingRoomSearch);

            BU.setText("Back");
        }
        else if(bID == B8.getId())//move to Hall two
        {
            location = 7;
            clickView = false;
            int combatChance = (generator.nextInt(100));
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }
        }
        else if(bID == BU.getId())
        {
            if(!clickView)
            {
                launchSaveAct(view);
            }
            else
            {
                clickView = false;
                currentLook();
            }
        }

    }



    public void StrongHoldCourtYardUI()//9
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.INVISIBLE);

        Button ngB = (Button) findViewById(R.id.ngbutton);

        B1.setVisibility(View.VISIBLE);
        B2.setVisibility(View.VISIBLE);
        B3.setVisibility(View.VISIBLE);
        B4.setVisibility(View.VISIBLE);
        B5.setVisibility(View.INVISIBLE);
        B6.setVisibility(View.VISIBLE);
        B7.setVisibility(View.VISIBLE);
        B8.setVisibility(View.VISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);

        ngB.setVisibility(View.INVISIBLE);

        story.setText("Courtyard");

        miniPic.setImageResource(R.drawable.courtyard);

        B1.setText("Character");
        B2.setText("Inventory");
        B3.setText("Journal");

        B4.setText("Search");

        B6.setText("N. Hall");
        B7.setText("N. Room");
        B8.setText("Gate");
        BU.setText("Save");



    }
    public void StrongHoldCourtYardOnClick(View view)
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        Button ngB = (Button) findViewById(R.id.ngbutton);

        int bID = view.getId();


        if(bID == B1.getId())
        {
            charView = true;
            characterView(view);
        }
        else if(bID == B2.getId())
        {
            packView = true;
            inventoryView(view);
        }
        else if(bID == B3.getId())
        {
            journal = true;
            journalView(view);
        }
        else if(bID == B4.getId())
        {
            story.setText(R.string.CourtyardSearch);
            clickView = true;
            BU.setText("Back");
        }
        else if(bID == B6.getId())//move to hall two
        {
            location = 7;
            clickView = false;
            int combatChance = (generator.nextInt(100));
            if(combatChance > 75)
            {
                combat = true;
                combatInterface();
            }
            else
            {
                currentLook();
            }
        }
        else if(bID == B7.getId())//move to gatehouse
        {
            location = 10;
            clickView = false;
            currentLook();
        }
        else if(bID == B8.getId())
        {
            if (GateC)
            {
                story.setText(R.string.GateView);
                clickView = true;
                BU.setText("Back");
            }
            else
            {
                location = 11;
                currentLook();
            }
        }
        else if(bID == BU.getId())
        {
            if(!clickView)
            {
                launchSaveAct(view);
            }
            else
            {
                clickView = false;
                currentLook();
            }
        }
    }


    public void StrongHoldGateHouseUI()//10
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.INVISIBLE);

        Button ngB = (Button) findViewById(R.id.ngbutton);

        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.VISIBLE);
        B2.setVisibility(View.VISIBLE);
        B3.setVisibility(View.VISIBLE);
        B4.setVisibility(View.VISIBLE);
        B5.setVisibility(View.INVISIBLE);
        B6.setVisibility(View.INVISIBLE);
        B7.setVisibility(View.VISIBLE);
        B8.setVisibility(View.VISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);

        ngB.setVisibility(View.INVISIBLE);

        story.setText("Gate House");

        miniPic.setImageResource(R.drawable.gate_house);

        B1.setText("Character");
        B2.setText("Inventory");
        B3.setText("Journal");

        B4.setText("Search");

        B7.setText("Open Gate");
        B8.setText("South");
        BU.setText("Save");

    }
    public void StrongHoldGateHouseOnClick(View view)
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        Button ngB = (Button) findViewById(R.id.ngbutton);

        int bID = view.getId();

        if(bID == B1.getId())
        {
            characterView(view);
        }
        else if(bID == B2.getId())
        {
            inventoryView(view);
        }
        else if(bID == B3.getId())
        {
            journalView(view);
        }
        else if(bID == B4.getId())
        {
            story.setText(R.string.GateHouseSearch);
            clickView = true;
            BU.setText("Back");
        }
        else if(bID == B7.getId())
        {
            if(GateC)
            {
                story.setText(R.string.OpenTheGate);
                GateC = false;

            }
            else if(!GateC)
            {
                story.setText(R.string.GateAlreadyOpen);
            }

            clickView = true;
            BU.setText("Back");
        }
        else if(bID == B8.getId())//move to courtyard
        {
            location = 9;
            clickView = false;
            currentLook();
        }
        else if(bID == BU.getId())
        {
            if(!clickView)
            {
                launchSaveAct(view);
            }
            else
            {
                clickView = false;
                currentLook();
            }
        }

    }




    public void FreedomCrossroadsUI()//11
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);

        HHP.setVisibility(View.VISIBLE);
        FHP.setVisibility(View.INVISIBLE);

        Button ngB = (Button) findViewById(R.id.ngbutton);

        pIn.setVisibility(View.INVISIBLE);

        B1.setVisibility(View.INVISIBLE);
        B2.setVisibility(View.INVISIBLE);
        B3.setVisibility(View.INVISIBLE);
        B4.setVisibility(View.VISIBLE);
        B5.setVisibility(View.INVISIBLE);
        B6.setVisibility(View.INVISIBLE);
        B7.setVisibility(View.INVISIBLE);
        B8.setVisibility(View.INVISIBLE);
        BU.setVisibility(View.VISIBLE);
        miniPic.setVisibility(View.VISIBLE);

        ngB.setVisibility(View.INVISIBLE);


        miniPic.setImageResource(R.drawable.freedom_view);
        story.setText(R.string.congratulations);
        B4.setText("Stronghold");
        BU.setText("Save");

    }

    public void FreedomCrossRoadsOnClick(View view)
    {
        EditText pIn = (EditText) findViewById(R.id.playerTextInput);
        Button B1 = (Button)findViewById(R.id.button_one);
        Button B2 = (Button)findViewById(R.id.button_two);
        Button B3 = (Button)findViewById(R.id.button_three);
        Button B4 = (Button)findViewById(R.id.button_four);
        Button B5 = (Button)findViewById(R.id.button_five);
        Button B6 = (Button)findViewById(R.id.button_six);
        Button B7 = (Button)findViewById(R.id.button_seven);
        Button B8 = (Button)findViewById(R.id.button_eight);
        Button BU = (Button)findViewById(R.id.utility_button);
        TextView story = (TextView)findViewById(R.id.storyView);
        TextView HHP = (TextView)findViewById(R.id.HeroHP);
        TextView FHP = (TextView)findViewById(R.id.FoeHP);
        ImageView miniPic = (ImageView) findViewById(R.id.mini);


        Button ngB = (Button) findViewById(R.id.ngbutton);

        int bID = view.getId();

        if(bID == B4.getId())
        {
            clickView = false;
            location = 9;
            currentLook();
        }
        else if(bID == BU.getId())
        {
            if(!clickView)
            {
                launchSaveAct(view);
            }
            else
            {
                clickView = false;
                currentLook();
            }
        }

    }

}
