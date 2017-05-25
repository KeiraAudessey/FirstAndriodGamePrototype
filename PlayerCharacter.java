package com.kan_inc.alternateuniverse;

/**
 * Created by Kira on 3/6/2015.
 */
public class PlayerCharacter
{
    static String PlayerName;
    static int level = 1;
    static int exp = 0;
    static int maxEXP = 20;
    static int location = 0;
    static int armor = 0;
    static int weapon = 0;
    static int HPMax = 100;
    static int HPC = 100;
    static int Physique = 12;
    static int Dexterity = 12;
    static int Willpower = 12;
    static int Intelligence = 12;
    static int Allure = 12;
    static int Charm = 12;
    static int Endurance = 12;
    static int PhysHit = 5;
    static int MxPhysDmg = 8;
    static int MnPhysDmg = 2;
    static int MagHit = 0;
    static int MxMagDmg = 0;
    static int MnMagDmg = 0;
    static int RPhysHit = 0;
    static int RMxDmg = 0;
    static int RMnDmg = 0;
    static int Def = 5;
    static int Rcv = 8;
    static int Plat = 0;
    static int Gold = 0;
    static int Silver = 0;
    static int Copper = 0;
    static boolean GateC = true;
    static boolean MaxNew = true;
    static boolean newGame = true;
    static boolean secondGlance = false;
    static boolean GDogRoomOneFirst = true;
    static boolean GDogRoomTwoFirst = true;
    static boolean GuardBossFirst = true;
    static boolean eqRackFirstLook = true;


    static int ItemSlots = 8;
    static int IQty = 2;


    static int[][] FoodPouch = new int[ItemSlots][IQty];
    static int[][] MatsPouch = new int[ItemSlots][IQty];
    static int[][] ToolsPouch = new int[ItemSlots][IQty];
    static int[][] AlchMagPouch = new int[ItemSlots][IQty];
    static int[][] EquipmentPouch = new int[ItemSlots][IQty];
    static int[][] JunkPouch = new int[ItemSlots][IQty];
    static int[][] SpecialPouch = new int[ItemSlots][IQty];


    public PlayerCharacter()
    {

    }

    public PlayerCharacter(String loadData)
    {
        int j;
        try
        {
            String[] tooMuchData = loadData.split("<>");
            for (int i = 0; i < tooMuchData.length - 1; i += 2)
            {
                j = i + 1;

                if (tooMuchData[i].compareToIgnoreCase("PlayerName") == 0)
                {
                    PlayerName = tooMuchData[j];
                } else if (tooMuchData[i].compareToIgnoreCase("level") == 0)
                {
                    level = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("exp") == 0)
                {
                    exp = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("maxEXP") == 0)
                {
                    maxEXP = new Integer(tooMuchData[j]);

                } else if (tooMuchData[i].compareToIgnoreCase("location") == 0)
                {
                    location = new Integer(tooMuchData[j]);

                } else if (tooMuchData[i].compareToIgnoreCase("armor") == 0)
                {
                    armor = new Integer(tooMuchData[j]);

                } else if (tooMuchData[i].compareToIgnoreCase("weapon") == 0)
                {
                    weapon = new Integer(tooMuchData[j]);

                } else if (tooMuchData[i].compareToIgnoreCase("HPMax") == 0)
                {
                    HPMax = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("HPC") == 0)
                {
                    HPC = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("Physique") == 0)
                {
                    Physique = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("Dexterity") == 0)
                {
                    Dexterity = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("Willpower") == 0)
                {
                    Willpower = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("Intelligence") == 0)
                {
                    Intelligence = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("Alure") == 0)
                {
                    Allure = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("Charm") == 0)
                {
                    Charm = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("Endurance") == 0)
                {
                    Endurance = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("PhysHit") == 0)
                {
                    PhysHit = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("MxPhysDmg") == 0)
                {
                    MxPhysDmg = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("MnPhysDmg") == 0)
                {
                    MnPhysDmg = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("MagHit") == 0)
                {
                    MagHit = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("MxMagDmg") == 0)
                {
                    MxMagDmg = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("MnMagDmg") == 0)
                {
                    MnMagDmg = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("RPhysHit") == 0)
                {
                    RPhysHit = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("RMxDmg") == 0)
                {
                    RMxDmg = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("RMnDmg") == 0)
                {
                    RMnDmg = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("Def") == 0)
                {
                    Def = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("Rcv") == 0)
                {
                    Rcv = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("Plat") == 0)
                {
                    Plat = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("Gold") == 0)
                {
                    Gold = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("Silver") == 0)
                {
                    Silver = new Integer(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("Copper") == 0)
                {
                    Copper = new Integer(tooMuchData[j]);

                } else if (tooMuchData[i].compareToIgnoreCase("GateC") == 0)
                {
                    GateC = new Boolean(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("MaxNew") == 0)
                {
                    MaxNew = new Boolean(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("newGame") == 0)
                {
                    newGame = new Boolean(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("secondGlance") == 0)
                {
                    secondGlance = new Boolean(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("GDogRoomOneFirst") == 0)
                {
                    GDogRoomOneFirst = new Boolean(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("GDogRoomTwoFirst") == 0)
                {
                    GDogRoomTwoFirst = new Boolean(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("GuardBossFirst") == 0)
                {
                    GuardBossFirst = new Boolean(tooMuchData[j]);
                } else if (tooMuchData[i].compareToIgnoreCase("eqRackFirstLook") == 0)
                {
                    eqRackFirstLook = new Boolean(tooMuchData[j]);
                }

            }


        } catch (Exception e)
        {


        }
    }

    public void LevelUp()
    {


    }
    public void setPlat(int amnt)
    {
        Plat += amnt;
    }
    public void setGold(int amnt)
    {
        Gold += amnt;
    }
    public void setSilver(int amnt)
    {
        Silver += amnt;
    }
    public void setCopper(int amnt)
    {
        Copper += amnt;
    }

    public void setPlayerName(String Name)
    {
        PlayerName = Name;
    }
    public String getPlayerName()
    {
        return PlayerName;
    }

    public int getFoodItem(int slot)
    {
        return FoodPouch[slot][0];
    }

    public int getFoodItemQty(int slot)
    {
        return FoodPouch[slot][1];
    }

    public boolean addItemToPouch(ItemTemplate item, int Count)
    {
        boolean success = false;
        int slot;
        if (item.isFood())
        {
            slot = 0;
            while (!success && slot < ItemSlots)
            {
                success = setFoodItemAndQty(slot, Count, item);
                slot++;
            }
        } else if (item.isAlchMag())
        {
            slot = 0;
            while (!success && slot < ItemSlots)
            {
                success = setAlchMagAndQty(slot, Count, item);
                slot++;
            }
        } else if (item.isCraftMat())
        {
            slot = 0;
            while (!success && slot < ItemSlots)
            {
                success = setCraftMatItemAndQty(slot, Count, item);
                slot++;
            }
        } else if (item.isJunkItem())
        {
            slot = 0;
            while (!success && slot < ItemSlots)
            {
                success = setJunkItemAndQty(slot, Count, item);
                slot++;
            }

        } else if (item.isEquipable())
        {
            slot = 0;
            while (!success && slot < ItemSlots)
            {
                success = setEQAndQty(slot, Count, item);
                slot++;
            }
        } else if (item.isSpecial())
        {
            slot = 0;
            while (!success && slot < ItemSlots)
            {
                success = setSpecialAndQty(slot, Count, item);
                slot++;
            }

        } else if (item.isTool())
        {
            slot = 0;
            while (!success && slot < ItemSlots)
            {
                success = setToolPresence(slot, item);
                slot++;
            }
        }
        return success;

    }


    public boolean setFoodItemAndQty(int slot, int count, ItemTemplate item)
    {
        boolean success = false;
        if (FoodPouch[slot][0] == 0)
        {
            FoodPouch[slot][0] = item.getIDNum();
            FoodPouch[slot][1] = count;
            success = true;
        } else if (FoodPouch[slot][0] == item.getIDNum())
        {
            FoodPouch[slot][1] += count;
            success = true;
        } else
        {

        }

        return success;
    }

    public boolean setAlchMagAndQty(int slot, int count, ItemTemplate item)
    {
        boolean success = false;
        if (AlchMagPouch[slot][0] == 0)
        {
            AlchMagPouch[slot][0] = item.getIDNum();
            AlchMagPouch[slot][1] = count;
            success = true;
        } else if (AlchMagPouch[slot][0] == item.getIDNum())
        {
            AlchMagPouch[slot][1] += count;
            success = true;
        } else
        {

        }

        return success;
    }

    public boolean setToolPresence(int slot, ItemTemplate item)
    {
        boolean success = false;
        if (AlchMagPouch[slot][0] == 0)
        {
            ToolsPouch[slot][0] = item.getIDNum();
            ToolsPouch[slot][1] = 1;
            success = true;
        } else
        {

        }

        return success;
    }

    public boolean setEQAndQty(int slot, int count, ItemTemplate item)
    {
        boolean success = false;
        if (EquipmentPouch[slot][0] == 0)
        {
            EquipmentPouch[slot][0] = item.getIDNum();
            EquipmentPouch[slot][1] = count;
            success = true;
        } else if (EquipmentPouch[slot][0] == item.getIDNum())
        {
            if (item.isStackable())
            {
                EquipmentPouch[slot][1] += count;
                success = true;
            }
        } else
        {

        }

        return success;
    }

    public boolean setCraftMatItemAndQty(int slot, int count, ItemTemplate item)
    {
        boolean success = false;
        if (MatsPouch[slot][0] == 0)
        {
            MatsPouch[slot][0] = item.getIDNum();
            MatsPouch[slot][1] = count;
            success = true;
        } else if (MatsPouch[slot][0] == item.getIDNum())
        {
            MatsPouch[slot][1] += count;
            success = true;
        } else
        {

        }

        return success;
    }

    public boolean setSpecialAndQty(int slot, int count, ItemTemplate item)
    {
        boolean success = false;
        if (SpecialPouch[slot][0] == 0)
        {
            SpecialPouch[slot][0] = item.getIDNum();
            SpecialPouch[slot][1] = count;
            success = true;
        } else if (SpecialPouch[slot][0] == item.getIDNum())
        {
            if (item.isStackable())
            {
                SpecialPouch[slot][1] += count;
                success = true;
            }
        } else
        {

        }

        return success;
    }

    public boolean setJunkItemAndQty(int slot, int count, ItemTemplate item)
    {
        boolean success = false;
        if (JunkPouch[slot][0] == 0)
        {
            JunkPouch[slot][0] = item.getIDNum();
            JunkPouch[slot][1] = count;
            success = true;
        } else if (JunkPouch[slot][0] == item.getIDNum())
        {
            JunkPouch[slot][1] += count;
            success = true;
        } else
        {

        }

        return success;
    }

    public boolean makeItemGone(ItemTemplate item, int count, int slot)
    {
        boolean success = false;
        if (item.isAlchMag())
        {
            success = makeAlchMagGone(item, count, slot);
        } else if (item.isSpecial())
        {
            success = makeSpecialGone(item, count, slot);
        } else if (item.isFood())
        {
            success = makeFoodGone(item, count, slot);
        } else if (item.isEquipable())
        {
            success = makeEquipmentGone(item, count, slot);
        } else if (item.isTool())
        {
            success = makeToolGone(item, count, slot);
        } else if (item.isCraftMat())
        {
            success = makeCraftMatGone(item, count, slot);
        } else if (item.isJunkItem())
        {
            success = makeJunkGone(item, count, slot);
        }
        return success;
    }

    public boolean makeFoodGone(ItemTemplate item, int count, int slot)
    {
        boolean success = false;
        if (FoodPouch[slot][0] == item.getIDNum())
        {
            FoodPouch[slot][1] -= count;
            if (FoodPouch[slot][1] < 1)
            {
                FoodPouch[slot][0] = 0;
                FoodPouch[slot][1] = 0;
            }
            success = true;
        }
        return success;
    }

    public boolean makeCraftMatGone(ItemTemplate item, int count, int slot)
    {
        boolean success = false;
        if (MatsPouch[slot][0] == item.getIDNum())
        {
            MatsPouch[slot][1] -= count;
            if (MatsPouch[slot][1] < 1)
            {
                MatsPouch[slot][0] = 0;
                MatsPouch[slot][1] = 0;
            }
            success = true;
        }
        return success;
    }

    public boolean makeToolGone(ItemTemplate item, int count, int slot)
    {
        boolean success = false;
        if (ToolsPouch[slot][0] == item.getIDNum())
        {
            ToolsPouch[slot][1] -= count;
            if (ToolsPouch[slot][1] < 1)
            {
                ToolsPouch[slot][0] = 0;
                ToolsPouch[slot][1] = 0;
            }
            success = true;
        }
        return success;

    }

    public boolean makeEquipmentGone(ItemTemplate item, int count, int slot)
    {
        boolean success = false;
        if (EquipmentPouch[slot][0] == item.getIDNum())
        {
            EquipmentPouch[slot][1] -= count;
            if (EquipmentPouch[slot][1] < 1)
            {
                EquipmentPouch[slot][0] = 0;
                EquipmentPouch[slot][1] = 0;
            }
            success = true;
        }
        return success;
    }

    public boolean makeSpecialGone(ItemTemplate item, int count, int slot)
    {
        boolean success = false;
        if (SpecialPouch[slot][0] == item.getIDNum())
        {
            SpecialPouch[slot][1] -= count;
            if (SpecialPouch[slot][1] < 1)
            {
                SpecialPouch[slot][0] = 0;
                SpecialPouch[slot][1] = 0;
            }
            success = true;
        }
        return success;
    }

    public boolean makeJunkGone(ItemTemplate item, int count, int slot)
    {
        boolean success = false;
        if (JunkPouch[slot][0] == item.getIDNum())
        {
            JunkPouch[slot][1] -= count;
            if (JunkPouch[slot][1] < 1)
            {
                JunkPouch[slot][0] = 0;
                JunkPouch[slot][1] = 0;
            }
            success = true;
        }
        return success;
    }

    public boolean makeAlchMagGone(ItemTemplate item, int count, int slot)
    {
        boolean success = false;
        if (AlchMagPouch[slot][0] == item.getIDNum())
        {
            AlchMagPouch[slot][1] -= count;
            if (AlchMagPouch[slot][1] < 1)
            {
                AlchMagPouch[slot][0] = 0;
                AlchMagPouch[slot][1] = 0;
            }
            success = true;
        }
        return success;
    }


    public String wealthToString()
    {
        String Wealth;
        String platS = "";
        if (Plat > 0)
        {
            platS += Plat + "p ";
        }
        String goldS = "";
        if (Gold > 0)
        {
            goldS += Gold + "g ";
        }
        String silverS = "";
        if (Silver > 0)
        {
            silverS += Silver + "s ";
        }
        String copperS = "";
        if (Copper > 0)
        {
            copperS += Copper + "c";
        }
        Wealth = platS + goldS + silverS + copperS;

        return Wealth;

    }
    public int getLocation()
    {
        return location;
    }
    public void setLocation(int local)
    {
        location = local;
    }
    public int getLevel()
    {
        return level;
    }
    public int getExp()
    {
        return exp;
    }
    public void setExp(int amnt)
    {
        exp += amnt;
    }
    public int getMaxEXP()
    {
        return maxEXP;
    }
    public int getHPC()
    {
        return HPC;
    }
    public int getPhysHit()
    {
        return PhysHit;
    }
    public int getHPMax()
    {
        return HPMax;
    }
    public int getDef()
    {
        return Def;
    }
    public void setHPC(int change)
    {
        if(HPC + change < 0)
        {
            HPC = 0;
        }
        else if(HPC + change > HPMax)
        {
            HPC = HPMax;
        }
        else
        {
            HPC += change;
        }
    }
    public boolean getNewGame()
    {
        return newGame;
    }
    public boolean getMaxNew()
    {
        return MaxNew;
    }
    public boolean getSecondGlance()
    {
        return secondGlance;
    }
    public boolean getGDogRoomOneFirst()
    {
        return GDogRoomOneFirst;
    }
    public boolean getGDogRoomTwoFirst()
    {
        return GDogRoomTwoFirst;
    }
    public boolean getGuardBossFirst()
    {
        return GuardBossFirst;
    }
    public boolean getEQackFirstLook()
    {
        return eqRackFirstLook;
    }
    public boolean getGateC()
    {
        return GateC;
    }


    public void setNewGame()
    {
        newGame = false;
    }
    public void setMaxNew()
    {
        MaxNew = false;

    }
    public void setSecondGlance()
    {
        secondGlance = true;

    }
    public void setGDogRoomOneFirst()
    {
        GDogRoomOneFirst = false;

    }
    public void setGDogRoomTwoFirst()
    {
        GDogRoomTwoFirst = false;

    }
    public void setGuardBossFirst()
    {
        GuardBossFirst = false;

    }
    public void setEQackFirstLook()
    {
        eqRackFirstLook = false;

    }
    public void setGateC(boolean switched)
    {
        GateC = switched;

    }


    public int getArmor()
    {
        return armor;
    }
    public int getWeapon()
    {
        return weapon;
    }
    public int getMxPhysDmg()
    {
        return MxPhysDmg;
    }
    public int getMnPhysDmg()
    {
        return MnPhysDmg;
    }
    public int getRcv()
    {
        return Rcv;
    }

    public String getStatsString()
    {
        String StatsString = PlayerName + "\nlvl: " + level + "\nexp: " + exp + "\nHP: " + HPC + "/"
                + HPMax + "\n" + "Hit: " + PhysHit + "\n" + "Dmg: " + MnPhysDmg + "/"
                + MxPhysDmg + "\n" + "Def: " + Def + "\n" + "Rcv: " + Rcv + "\n\n"
                + wealthToString();

        return StatsString;
    }


    public String characterToString()
    {
        String character = "PlayerName<>" + PlayerName +
                "<>level<>" + level +
                "<>exp<>" + exp +
                "<>maxEXP<>" + maxEXP +
                "<>location<>" + location +
                "<>armor<>" + armor +
                "<>weapon<>" + weapon +
                "<>HPMax<>" + HPMax +
                "<>HPC<>" + HPC +
                "<>Physique<>" + Physique +
                "<>Dexterity<>" + Dexterity +
                "<>Willpower<>" +  Willpower +
                "<>Intelligence<>" +  Intelligence +
                "<>Allure<>" +  Allure +
                "<>Charm<>" +  Charm +
                "<>Endurance<>" +  Endurance +
                "<>PhysHit<>" +  PhysHit +
                "<>MxPhysDmg<>" +  MxPhysDmg +
                "<>MnPhysDmg<>" +  MnPhysDmg +
                "<>MagHit<>" +  MagHit +
                "<>MxMagDmg<>" +  MxMagDmg +
                "<>MnMagDmg<>" +  MnMagDmg +
                "<>RPhysHit<>" +  RPhysHit +
                "<>RMxDmg<>" +  RMxDmg +
                "<>RMnDmg<>" +  RMnDmg +
                "<>Def<>" +  Def +
                "<>Rcv<>" +  Rcv +
                "<>Plat<>" +  Plat +
                "<>Gold<>" +  Gold +
                "<>Silver<>" +  Silver +
                "<>Copper<>" +  Copper +
                "<>GateC<>" +  GateC +
                "<>MaxNew<>" +  MaxNew +
                "<>newGame<>" +  newGame +
                "<>secondGlance<>" +  secondGlance +
                "<>GDogRoomOneFirst<>" +  GDogRoomOneFirst +
                "<>GDogRoomTwoFirst<>" +  GDogRoomTwoFirst +
                "<>GuardBossFirst<>" +  GuardBossFirst +
                "<>eqRackFirstLook<>" +  eqRackFirstLook ;

        return character;
    }





















}









