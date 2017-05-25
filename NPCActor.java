package com.kan_inc.alternateuniverse;

import java.util.Random;

/**
 * Created by Kira on 3/6/2015.
 */
public class NPCActor
{
    static String Name;
    static String StringID;
    static String PicID;
    static int HPMax;
    static int HPC;
    static int Physique;
    static int Dexterity;
    static int Willpower;
    static int Intelligence;
    static int Allure;
    static int Charm;
    static int Endurance;
    static int PHit;
    static int PhysMaxDmg;
    static int PhysMinDmg;
    static int Rhit;
    static int RMaxDmg;
    static int RMinDmg;
    static int MHit;
    static int MagMinDmg;
    static int MagMaxDmg;
    static int Defense;//how much defense increases when defending
    static int RcvrMax;//max recovered when missed while defending
    static int RcvrMin;//min recovered when missed while defending
    static int DefPrefBase;//how likely they are to defend normally
    static int DefPrefHurt;//how likely they are to defend when injured
    static int WhatIsHurt;// what counts as hurt

    static boolean isFoe;
    static boolean isRecruitable;
    static boolean hasMagicAtk;
    static boolean hasPhysAtk;
    static boolean hasRPhysAtk;
    static int PhysAtkPref;
    static int MagAtkPref;
    static int RPhysAtkPref;

    static int EXPReward;
    static int PlatinumR;
    static int GoldR;
    static int SilverR;
    static int CopperR;
    static int [] lootTable = {0, 0, 0, 0, 0, 0, 0};
    static int level;

    static int horribleLuck = 0;//0 - 20
    static int poorLuck = 1; //21 - 50
    static int avgLuck = 2; //51 - 70
    static int goodLuck = 3;//71 - 90
    static int greatLuck = 4;//91-100
    static int amazingLuck = 5;//101+

    private final Random rand = new Random();

    String thingy = "";

    public NPCActor()
    {

    }
    public NPCActor(String loadData)
    {
        try
        {
            String [] foeData = loadData.split("@@");

            Name = foeData[1];
            StringID = foeData[3];
            PicID = foeData[5];
            HPMax = new Integer(foeData[7]);
            HPC = new Integer(foeData[9]);
            Physique = new Integer(foeData[11]);
            Dexterity = new Integer(foeData[13]);
            Willpower = new Integer(foeData[15]);
            Intelligence = new Integer(foeData[17]);
            Allure = new Integer(foeData[19]);
            Charm = new Integer(foeData[21]);
            Endurance = new Integer(foeData[23]);
            PHit = new Integer(foeData[25]);
            PhysMaxDmg = new Integer(foeData[27]);
            PhysMinDmg = new Integer(foeData[29]);
            MHit = new Integer(foeData[31]);
            MagMinDmg = new Integer(foeData[33]);
            MagMaxDmg = new Integer(foeData[35]);
            Defense = new Integer(foeData[37]);
            RcvrMax = new Integer(foeData[39]);
            RcvrMin = new Integer(foeData[41]);
            DefPrefBase = new Integer(foeData[43]);
            DefPrefHurt = new Integer(foeData[45]);
            WhatIsHurt = new Integer(foeData[47]);
            isFoe = new Boolean(foeData[49]);
            isRecruitable = new Boolean(foeData[51]);
            hasMagicAtk = new Boolean(foeData[53]);
            hasPhysAtk = new Boolean(foeData[55]);
            hasRPhysAtk = new Boolean(foeData[57]);
            PhysAtkPref = new Integer(foeData[59]);
            MagAtkPref = new Integer(foeData[61]);
            RPhysAtkPref = new Integer(foeData[63]);
            EXPReward = new Integer(foeData[65]);
            PlatinumR = new Integer(foeData[67]);
            GoldR = new Integer(foeData[69]);
            SilverR = new Integer(foeData[71]);
            CopperR = new Integer(foeData[73]);
            level = new Integer(foeData[75]);
            int lootCount = new Integer(foeData[77]);

            if (lootCount > 0)
            {
                //do more stuff with loot
            }
            for(int i = 0; i < foeData.length; i++)
            {
                thingy+=foeData[i];
            }

        }
        catch(Exception e)
        {

            System.err.println("IT'S NOT WORKING: " + e);
            System.err.println("MAYBE CAUSE THE STRINGY THINGY IS : " + thingy);
        }




    }
    public NPCActor(int levelMod)
    {

    }
    public String getPicID()
    {
        return PicID;
    }
    public int getLoot(int luck)
    {
        int loot = -1;

        int baseLuck = rand.nextInt(100) + luck;

        if(baseLuck <= 20)
        {
            loot = lootTable[horribleLuck];
        }
        else if(baseLuck > 20 && baseLuck <= 50)
        {
            loot = lootTable[poorLuck];
        }
        else if(baseLuck > 50 && baseLuck <= 70)
        {
            loot = lootTable[avgLuck];
        }
        else if(baseLuck > 70 && baseLuck <= 90)
        {
            loot = lootTable[goodLuck];
        }
        else if(baseLuck > 90 && baseLuck <= 100)
        {
            loot = lootTable[greatLuck];
        }
        else
        {
            loot = lootTable[amazingLuck];
        }


        return loot;

    }
    public int getHPMax()
    {
        return HPMax;
    }
    public int getHPC()
    {
        return HPC;
    }
    public void setHPC(int change)
    {
        HPC += change;
        if(HPC < 0)
        {
            HPC = 0;
        }
        if(HPC > HPMax)
        {
            HPC = HPMax;
        }
    }
    public int getPHit()
    {
        return PHit;
    }
    public int getPhysMaxDmg()
    {
        return PhysMaxDmg;
    }
    public int getPhysMinDmg()
    {
        return PhysMinDmg;
    }
    public int getMHit()
    {
        return MHit;
    }
    public int getMagMaxDmg()
    {
        return MagMaxDmg;
    }
    public int getMagMinDmg()
    {
        return MagMinDmg;
    }
    public int getDefense()
    {
        return Defense;
    }
    public int getRcvrMax()
    {
        return RcvrMax;
    }
    public int getRcvrMin()
    {
        return RcvrMin;
    }
    public int getDefPrefBase()
    {
        return DefPrefBase;
    }
    public int getDefPrefHurt()
    {
        return DefPrefHurt;
    }
    public int getWhatIsHurt()
    {
        return WhatIsHurt;
    }
    public int getEXPReward()
    {
        return EXPReward;
    }
    public boolean getIsFoe()
    {
        return isFoe;
    }
    public boolean getIsRecruitable()
    {
        return isRecruitable;
    }
    public String getName()
    {
        return Name;
    }
    public String getStringID()
    {
        return StringID;
    }
    public int getPlatinumR()
    {
        return PlatinumR;
    }
    public int getGoldR()
    {
        return GoldR;
    }
    public int getSilverR()
    {
        return SilverR;
    }
    public int getCopperR()
    {
        return CopperR;
    }
    public boolean getHasPhysAtk()
    {
        return hasPhysAtk;
    }
    public boolean getHasMagAtk()
    {
        return hasMagicAtk;
    }
    public boolean getHasRPhysAtk()
    {
        return hasRPhysAtk;
    }
    public int getPhysAtkPref()
    {
        return PhysAtkPref;
    }
    public int getMagAtkPref()
    {
        return MagAtkPref;
    }
    public int getRPhysAtkPref()
    {
        return RPhysAtkPref;
    }

}
