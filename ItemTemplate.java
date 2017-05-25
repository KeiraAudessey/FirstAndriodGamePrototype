package com.kan_inc.alternateuniverse;

/**
 * Created by Kira on 3/6/2015.
 */
public class ItemTemplate
{
    String Name;
    int IDNum;
    String ItemCategory;
    String Desc;
    String UseDesc;
    int Rarity; // 0 - horrible, 1 - poor, 2 - avg, 3 - good, 4 - great, 5 - amazing

    int Platinum;
    int Gold;
    int Silver;
    int Copper;
    String value;



    boolean DestructOnUse;

    boolean StatEffectsPermanent;

    boolean EffectsOnUse;

    boolean EffectsOnEquip;

    boolean Useable;



    boolean Cursed;

    boolean Tool;

    boolean EnablesCooking;

    boolean EnablesAlchemy;

    boolean EnablesLockPicking;

    boolean EnablesClimbing;

    boolean EnablesDiving;

    boolean EnablesEQRepair;

    boolean EnablesEQCrafting;

    boolean EnablesEnchanting;

    int unlocks;



    boolean Potion;

    boolean Tome;



    boolean Food;

    boolean AlchMag;

    boolean CraftMat;

    boolean Special;

    boolean JunkItem;

    boolean Equipable;

    boolean Stackable;



    boolean Armor;

    boolean Weapon;

    boolean Poison;

    boolean RcvItem;

    boolean EqCraftMat;

    boolean FoodCraftMat;

    boolean PotionCraftMat;

    int DefChange;
    int DmgRdxChange;
    int AtkChange;
    int HitChange;
    int MaxDmgChange;
    int MinDmgChange;
    int PhysiqueChange;
    int DexterityChange;
    int WillpowerChange;
    int IntelligenceChange;
    int AllureChange;
    int CharmChange;
    int RecoveryChange;
    int EXPChange;
    int DiveDurationBoost;
    int RcvMax;
    int RcvMin;
    int RcvOT;
    int PsnMin;
    int PsnMax;
    int PsnOT;


    int[] ItemsRequiredToCraft;


    public ItemTemplate()
    {

    }
    public boolean isStackable()
    {
        return Stackable;
    }

    public int getIDNum()
    {
        return IDNum;
    }

    public boolean isFood()
    {
        return Food;
    }
    public boolean isEquipable()
    {
        return Equipable;
    }
    public boolean isCraftMat()
    {
        return CraftMat;
    }
    public boolean isAlchMag()
    {
     return AlchMag;
    }
    public boolean isTool()
    {
        return Tool;
    }
    public boolean isSpecial()
    {
        return Special;
    }
    public boolean isJunkItem()
    {
        return JunkItem;
    }


}
