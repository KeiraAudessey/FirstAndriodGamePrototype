package com.kan_inc.alternateuniverse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.io.*;


public class LoadActivity extends ActionBarActivity
{

    public final static String EXTRA_MESSAGE = "com.kan_inc.AlternateUniverse.NAME";

    String yee;
    String AUstring;

    String SAVE1;
    String SAVE2;
    String SAVE3;
    String SAVE4;
    String SAVE5;
    String SAVE6;
    String SAVE7;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String initialize= "Save 1-Save 2-Save 3-Save 4-Save 5-Save 6-Save 7";
        String fileName = "AUData";
        File AUDataFile = new File(getFilesDir(), fileName);
        FileOutputStream outputStream;

        boolean didThisWork = AUDataFile.delete();
        System.err.println("Delete successful? " + didThisWork);


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


            AUstring = in.readLine();
            System.err.println(AUstring);
            String [] AUvalues = AUstring.split("-");
            for(int i=0; i< AUvalues.length; i++)
                System.err.println("Index " + i + " = " + AUvalues[i]);
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



        setContentView(R.layout.activity_load);
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

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_load, menu);
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


    public void LoadGame(View view)
    {


        RadioGroup saveRadio = (RadioGroup) findViewById(R.id.LoadGameGroup);
        int radioButtonID = saveRadio.getCheckedRadioButtonId();
        View radioButton = saveRadio.findViewById(radioButtonID);
        int idx = saveRadio.indexOfChild(radioButton);

        TextView ErrorWindow = (TextView) findViewById(R.id.Error);

        if(idx == -1)
        {
            ErrorWindow.setText("No Save Game Selected.");
        }
        else if(idx == 0)
        {

            File saveOne = new File(getFilesDir(), "Save_1.txt");
            try
            {
                BufferedReader in = new BufferedReader(new FileReader(saveOne));


                yee = in.readLine();
                ErrorWindow.setText("Sending : " + yee);
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra(EXTRA_MESSAGE, yee);
                startActivity(intent);


            }

            catch (Exception e)
            {
                ErrorWindow.setText("Could Not Load File: " + e);
            }
        }
        else if(idx == 1)
        {
            File saveTwo = new File(getFilesDir(), "Save_2.txt");
            try
            {
                BufferedReader in = new BufferedReader(new FileReader(saveTwo));

                yee = in.readLine();
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra(EXTRA_MESSAGE, yee);
                startActivity(intent);


            }

            catch (Exception e)
            {
                ErrorWindow.setText("Could Not Load File: " + e);
            }
        }
        else if(idx == 2)
        {
            File saveThree = new File(getFilesDir(), "Save_3.txt");
            try
            {
                BufferedReader in = new BufferedReader(new FileReader(saveThree));

                yee = in.readLine();
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra(EXTRA_MESSAGE, yee);
                startActivity(intent);


            }

            catch (Exception e)
            {
                ErrorWindow.setText("Could Not Load File: " + e);
            }

        }
        else if(idx == 3)
        {
            File saveFour = new File(getFilesDir(), "Save_4.txt");
            try
            {
                BufferedReader in = new BufferedReader(new FileReader(saveFour));


                yee = in.readLine();
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra(EXTRA_MESSAGE, yee);
                startActivity(intent);


            }

            catch (Exception e)
            {
                ErrorWindow.setText("Could Not Load File: " + e);
            }

        }
        else if(idx == 4)
        {
            File saveFive = new File(getFilesDir(), "Save_5.txt");
            try
            {
                BufferedReader in = new BufferedReader(new FileReader(saveFive));


                yee = in.readLine();
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra(EXTRA_MESSAGE, yee);
                startActivity(intent);

            }

            catch (Exception e)
            {
                ErrorWindow.setText("Could Not Load File: " + e);
            }

        }
        else if(idx == 5)
        {
            File saveSix = new File(getFilesDir(), "Save_6.txt");
            try
            {
                BufferedReader in = new BufferedReader(new FileReader(saveSix));


                yee = in.readLine();
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra(EXTRA_MESSAGE, yee);
                startActivity(intent);


            }

            catch (Exception e)
            {
                ErrorWindow.setText("Could Not Load File: " + e);
            }

        }
        else if(idx == 6)
        {
            File saveSeven = new File(getFilesDir(), "Save_7.txt");
            try
            {
                BufferedReader in = new BufferedReader(new FileReader(saveSeven));


                yee = in.readLine();
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra(EXTRA_MESSAGE, yee);
                startActivity(intent);


            }

            catch (Exception e)
            {
                ErrorWindow.setText("Could Not Load File: " + e);
            }

        }


    }

    public void DeleteSaveGame(View view)
    {

        TextView ErrorWindow = (TextView) findViewById(R.id.Error);
        RadioGroup saveRadio = (RadioGroup) findViewById(R.id.LoadGameGroup);
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

            }

            catch (Exception e)
            {
                ErrorWindow.setText("You FAILED to Delete " + SAVE6 + " : " + e);
            }

        }

    }
}
