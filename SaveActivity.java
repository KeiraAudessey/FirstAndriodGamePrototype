package com.kan_inc.alternateuniverse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.*;


public class SaveActivity extends ActionBarActivity
{
    public final static String EXTRA_MESSAGE = "com.kan_inc.AlternateUniverse.NAME";

    String SAVE1;
    String SAVE2;
    String SAVE3;
    String SAVE4;
    String SAVE5;
    String SAVE6;
    String SAVE7;

    String saveName;
    String gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        gameData = intent.getStringExtra(EXTRA_MESSAGE);
        setContentView(R.layout.activity_save);



        String initialize= "Save 1-Save 2-Save 3-Save 4-Save 5-Save 6-Save 7 ";
        String fileName = "AUData";
        File AUDataFile = new File(getFilesDir(), fileName);
        FileOutputStream outputStream;

        //boolean didThisWork = AUDataFile.delete();
        //System.err.println("Delete successful? " + didThisWork);


        if(!(AUDataFile.exists()))
        {
            try
            {
                outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                outputStream.write(initialize.getBytes());
                outputStream.close();

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }//do something
        }




        try
        {
            BufferedReader in = new BufferedReader(new FileReader(AUDataFile));

            String yee = in.readLine();
            //System.err.println(yee);
            String [] AUvalues = yee.split("-");
            //for(int i=0; i< AUvalues.length; i++)
            //System.err.println("Index " + i + " = " + AUvalues[i]);
            SAVE1 = AUvalues[0];
            SAVE2 = AUvalues[1];
            SAVE3 = AUvalues[2];
            SAVE4 = AUvalues[3];
            SAVE5 = AUvalues[4];
            SAVE6 = AUvalues[5];
            SAVE7 = AUvalues[6];

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }





        TextView whee = (TextView) findViewById(R.id.sError);
        whee.setText("Saving : " + gameData);

        String candy1 = String.format(getResources().getString(R.string.Save_one), SAVE1);
        String candy2 = String.format(getResources().getString(R.string.Save_two), SAVE2);
        String candy3 = String.format(getResources().getString(R.string.Save_three), SAVE3);
        String candy4 = String.format(getResources().getString(R.string.Save_four), SAVE4);
        String candy5 = String.format(getResources().getString(R.string.Save_five), SAVE5);
        String candy6 = String.format(getResources().getString(R.string.Save_six), SAVE6);
        String candy7 = String.format(getResources().getString(R.string.Save_six), SAVE7);

        RadioButton r1 = (RadioButton) findViewById(R.id.save_one);
        r1.setText(candy1);
        RadioButton r2 = (RadioButton) findViewById(R.id.save_two);
        r2.setText(candy2);
        RadioButton r3 = (RadioButton) findViewById(R.id.save_three);
        r3.setText(candy3);
        RadioButton r4 = (RadioButton) findViewById(R.id.save_four);
        r4.setText(candy4);
        RadioButton r5 = (RadioButton) findViewById(R.id.save_five);
        r5.setText(candy5);
        RadioButton r6 = (RadioButton) findViewById(R.id.save_six);
        r6.setText(candy6);
        RadioButton r7 = (RadioButton) findViewById(R.id.save_seven);
        r7.setText(candy7);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);
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



    public void SaveGame(View view)
    {
        RadioGroup saveRadio = (RadioGroup) findViewById(R.id.SaveGameGroup);
        int radioButtonID = saveRadio.getCheckedRadioButtonId();
        View radioButton = saveRadio.findViewById(radioButtonID);
        int idx = saveRadio.indexOfChild(radioButton);

        EditText inText = (EditText) findViewById(R.id.SaveGameName);

        saveName = inText.getText().toString();


        TextView ErrorWindow = (TextView) findViewById(R.id.sError);




        if(idx == -1)
        {

            ErrorWindow.setText("No Save Game Selected.");

        }
        else if(idx == 0)
        {
            SAVE1 = saveName;


            File SaveOne = new File(getFilesDir(), "Save_1.txt");
            FileOutputStream outputStream;
            SaveOne.delete();



            if(!(SaveOne.exists()))
            {
                try
                {
                    outputStream = openFileOutput("Save_1.txt", Context.MODE_PRIVATE);
                    outputStream.write(gameData.getBytes());
                    outputStream.close();
                    ErrorWindow.setText("File " + SAVE1 + " Saved");

                }
                catch(Exception e)
                {
                    ErrorWindow.setText("Could Not Save File: " + e);
                }
            }


        }
        else if(idx == 1)
        {
            SAVE2 = saveName;

            File SaveTwo = new File(getFilesDir(), "Save_2.txt");
            FileOutputStream outputStream;
            SaveTwo.delete();



            if(!(SaveTwo.exists()))
            {
                try
                {
                    outputStream = openFileOutput("Save_2.txt", Context.MODE_PRIVATE);
                    outputStream.write(gameData.getBytes());
                    outputStream.close();
                    ErrorWindow.setText("File " + SAVE2 + " Saved");

                }
                catch(Exception e)
                {

                    ErrorWindow.setText("Could Not Save File: " + e);
                }
            }
        }
        else if(idx == 2)
        {
            SAVE3 = saveName;

            File SaveThree = new File(getFilesDir(), "Save_3.txt");
            FileOutputStream outputStream;
            SaveThree.delete();



            if(!(SaveThree.exists()))
            {
                try
                {
                    outputStream = openFileOutput("Save_3.txt", Context.MODE_PRIVATE);
                    outputStream.write(gameData.getBytes());
                    outputStream.close();
                    ErrorWindow.setText("File " + SAVE3 + " Saved");

                }
                catch(Exception e)
                {

                    ErrorWindow.setText("Could Not Save File: " + e);
                }
            }


        }
        else if(idx == 3)
        {
            SAVE4 = saveName;
            File SaveFour = new File(getFilesDir(), "Save_4.txt");
            FileOutputStream outputStream;
            SaveFour.delete();



            if(!(SaveFour.exists()))
            {
                try
                {
                    outputStream = openFileOutput("Save_4.txt", Context.MODE_PRIVATE);
                    outputStream.write(gameData.getBytes());
                    outputStream.close();
                    ErrorWindow.setText("File " + SAVE4 + " Saved");

                }
                catch(Exception e)
                {

                    ErrorWindow.setText("Could Not Save File: " + e);
                }
            }

        }
        else if(idx == 4)
        {
            SAVE5 = saveName;
            File SaveFive = new File(getFilesDir(), "Save_5.txt");
            FileOutputStream outputStream;
            SaveFive.delete();



            if(!(SaveFive.exists()))
            {
                try
                {
                    outputStream = openFileOutput("Save_5.txt", Context.MODE_PRIVATE);
                    outputStream.write(gameData.getBytes());
                    outputStream.close();
                    ErrorWindow.setText("File " + SAVE5 + " Saved");

                }
                catch(Exception e)
                {

                    ErrorWindow.setText("Could Not Save File: " + e);
                }
            }

        }
        else if(idx == 5)
        {
            SAVE6 = saveName;
            File SaveSix = new File(getFilesDir(), "Save_6.txt");
            FileOutputStream outputStream;
            SaveSix.delete();



            if(!(SaveSix.exists()))
            {
                try
                {
                    outputStream = openFileOutput("Save_6.txt", Context.MODE_PRIVATE);
                    outputStream.write(gameData.getBytes());
                    outputStream.close();
                    ErrorWindow.setText("File " + SAVE6 + " Saved");

                }
                catch(Exception e)
                {

                    ErrorWindow.setText("Could Not Save File: " + e);
                }
            }

        }
        setContentView(R.layout.activity_save);
        String candy1 = String.format(getResources().getString(R.string.Save_one), SAVE1);
        String candy2 = String.format(getResources().getString(R.string.Save_two), SAVE2);
        String candy3 = String.format(getResources().getString(R.string.Save_three), SAVE3);
        String candy4 = String.format(getResources().getString(R.string.Save_four), SAVE4);
        String candy5 = String.format(getResources().getString(R.string.Save_five), SAVE5);
        String candy6 = String.format(getResources().getString(R.string.Save_six), SAVE6);
        String candy7 = String.format(getResources().getString(R.string.Save_seven), SAVE7);

        RadioButton r1 = (RadioButton) findViewById(R.id.save_one);
        r1.setText(candy1);
        RadioButton r2 = (RadioButton) findViewById(R.id.save_two);
        r2.setText(candy2);
        RadioButton r3 = (RadioButton) findViewById(R.id.save_three);
        r3.setText(candy3);
        RadioButton r4 = (RadioButton) findViewById(R.id.save_four);
        r4.setText(candy4);
        RadioButton r5 = (RadioButton) findViewById(R.id.save_five);
        r5.setText(candy5);
        RadioButton r6 = (RadioButton) findViewById(R.id.save_six);
        r6.setText(candy6);
        RadioButton r7 = (RadioButton) findViewById(R.id.save_seven);
        r7.setText(candy7);



        String update = SAVE1 + "-" + SAVE2 + "-" + SAVE3 + "-" + SAVE4 + "-" + SAVE5 + "-" + SAVE6
                + SAVE7;

        String fileName = "AUData";
        File AUDataFile = new File(getFilesDir(), fileName);
        FileOutputStream outputStream;

        //boolean didThisWork = AUDataFile.delete();
        //System.err.println("Delete successful? " + didThisWork);


        if((AUDataFile.exists()))
        {
            try
            {
                outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                outputStream.write(update.getBytes());
                outputStream.close();

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }//do something
        }


    }

    public void DeleteSaveGame(View view)
    {

        TextView ErrorWindow = (TextView) findViewById(R.id.sError);
        RadioGroup saveRadio = (RadioGroup) findViewById(R.id.SaveGameGroup);
        int radioButtonID = saveRadio.getCheckedRadioButtonId();
        View radioButton = saveRadio.findViewById(radioButtonID);
        int idx = saveRadio.indexOfChild(radioButton);

        if(idx == -1)
        {
            ErrorWindow.setText("No Save Game Selected.");
        }
        else if(idx == 0)
        {
            ErrorWindow.setText("You Deleted the File: " + SAVE1);


            try
            {
                File saveOne = new File(getFilesDir(), "Save_1.txt");
                saveOne.delete();
                SAVE1 = "Save 1";

            }

            catch (Exception e)
            {
                ErrorWindow.setText("You FAILED to Delete " + SAVE1 + " : " + e);
            }

        }
        else if(idx == 1)
        {
            ErrorWindow.setText("You Deleted the File: " + SAVE2);


            try
            {
                File saveTwo = new File(getFilesDir(), "Save_2.txt");
                saveTwo.delete();
                SAVE2 = "Save 2";

            }

            catch (Exception e)
            {
                ErrorWindow.setText("You FAILED to Delete " + SAVE2 + " : " + e);
            }
        }
        else if(idx == 2)
        {
            ErrorWindow.setText("You Deleted the File: " + SAVE3);


            try
            {
                File saveThree = new File(getFilesDir(), "Save_3.txt");
                saveThree.delete();
                SAVE3 = "Save 3";

            }

            catch (Exception e)
            {
                ErrorWindow.setText("You FAILED to Delete " + SAVE3 + " : " + e);
            }
        }
        else if(idx == 3)
        {
            ErrorWindow.setText("You Deleted the File: " + SAVE4);


            try
            {
                File saveFour = new File(getFilesDir(), "Save_4.txt");
                saveFour.delete();
                SAVE4 = "Save 4";

            }

            catch (Exception e)
            {
                ErrorWindow.setText("You FAILED to Delete " + SAVE4 + " : " + e);
            }

        }
        else if(idx == 4)
        {
            ErrorWindow.setText("You Deleted the File: " + SAVE5);


            try
            {
                File saveFive = new File(getFilesDir(), "Save_5.txt");
                saveFive.delete();
                SAVE5 = "Save 5";

            }

            catch (Exception e)
            {
                ErrorWindow.setText("You FAILED to Delete " + SAVE5 + " : " + e);
            }

        }
        else if(idx == 5)
        {
            ErrorWindow.setText("You Deleted the File: " + SAVE6);


            try
            {
                File saveSix = new File(getFilesDir(), "Save_6.txt");
                saveSix.delete();
                SAVE6 = "Save 6";

            }

            catch (Exception e)
            {
                ErrorWindow.setText("You FAILED to Delete " + SAVE6 + " : " + e);
            }

        }
        else if(idx == 6)
        {
            ErrorWindow.setText("You Deleted the File: " + SAVE7);


            try
            {
                File saveSeven = new File(getFilesDir(), "Save_7.txt");
                saveSeven.delete();
                SAVE7 = "Save 7";

            }

            catch (Exception e)
            {
                ErrorWindow.setText("You FAILED to Delete " + SAVE7 + " : " + e);
            }

        }

        setContentView(R.layout.activity_save);
        String candy1 = String.format(getResources().getString(R.string.Save_one), SAVE1);
        String candy2 = String.format(getResources().getString(R.string.Save_two), SAVE2);
        String candy3 = String.format(getResources().getString(R.string.Save_three), SAVE3);
        String candy4 = String.format(getResources().getString(R.string.Save_four), SAVE4);
        String candy5 = String.format(getResources().getString(R.string.Save_five), SAVE5);
        String candy6 = String.format(getResources().getString(R.string.Save_six), SAVE6);
        String candy7 = String.format(getResources().getString(R.string.Save_seven), SAVE7);

        RadioButton r1 = (RadioButton) findViewById(R.id.save_one);
        r1.setText(candy1);
        RadioButton r2 = (RadioButton) findViewById(R.id.save_two);
        r2.setText(candy2);
        RadioButton r3 = (RadioButton) findViewById(R.id.save_three);
        r3.setText(candy3);
        RadioButton r4 = (RadioButton) findViewById(R.id.save_four);
        r4.setText(candy4);
        RadioButton r5 = (RadioButton) findViewById(R.id.save_five);
        r5.setText(candy5);
        RadioButton r6 = (RadioButton) findViewById(R.id.save_six);
        r6.setText(candy6);
        RadioButton r7 = (RadioButton)findViewById(R.id.save_seven);
        r7.setText(candy7);

    }

    public void returnToGame(View view)
    {
        Intent resumeGame = new Intent(this, GameActivity.class);
        resumeGame.putExtra(EXTRA_MESSAGE, gameData);
        resumeGame.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(resumeGame);
    }

}
