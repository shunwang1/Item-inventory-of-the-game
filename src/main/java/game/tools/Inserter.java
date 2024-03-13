package main.java.game.tools;
import main.java.game.model.*;
import main.java.game.dal.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;




public class Inserter {

    public static void main(String[] args) throws SQLException {
        // DAO instances.
        JobDao jobsDao = JobDao.getInstance();
        Exp_NeededDao exp_NeededDao= Exp_NeededDao.getInstance();
        Job_of_CharacterDao jobs_of_characterDao = Job_of_CharacterDao.getInstance();
        EquippedGearDao equippedGearsDao = EquippedGearDao.getInstance();
        GearDao gearsDao = GearDao.getInstance();
        /*---------------------------------------------------------------*/
        ItemDao itemDao = ItemDao.getInstance();
        InventoryDao inventoryDao = InventoryDao.getInstance();
        CurrenciesDao currenciesDao = CurrenciesDao.getInstance();
        CharacterCurrencyDao characterCurrencyDao = CharacterCurrencyDao.getInstance();
        /*---------------------------------------------------------------*/
        AttributeDao attDao = AttributeDao.getInstance();
        Each_CharacterDao each_characterDao = Each_CharacterDao.getInstance();
        Job_of_ItemDao job_of_itemDao = Job_of_ItemDao.getInstance();
        otherSlotsDao oSlotsDao = otherSlotsDao.getInstance();
        /*-----------------------------------------------------------------*/
        CustomizedItemDao customizedItemDao = CustomizedItemDao.getInstance();
        PlayerDao playerDao = PlayerDao.getInstance();
        /*-----------------------------------------------------------------*/
        SlotDao slotDao = SlotDao.getInstance();
        WeaponDao weaponDao = WeaponDao.getInstance();
        MiscellaneousDao miscellaneousDao = MiscellaneousDao.getInstance();
        ConsumablesDao consumablesDao = ConsumablesDao.getInstance();

        /*-------------------------INSERT DATA----------------------------*/

        Currencies currencies = new Currencies(1,"a", 2000, 450, 585, 20);
        currencies = currenciesDao.create(currencies);
        Currencies currencies1 = new Currencies(2,"b", 2000, 0, 700, 0);
        currencies1 = currenciesDao.create(currencies1);
        Currencies currencies2 = new Currencies(3,"c", 1500, 300, 135, 11);
        currencies2 = currenciesDao.create(currencies2);

        // item
        Item item = new Item(1,"Diadochos Jacket of Fending", 1, true, 10.99);
        item = itemDao.create(item);
        Item item1 = new Item(2,"Sapphire Spellblade", 1, true, 15.88);
        item1 = itemDao.create(item1);
        Item item2 = new Item(3,"Vial of Liquid Shadows", 5, false, 1.33);
        item2 = itemDao.create(item2);
        Item item3 = new Item(4,"Sunfire Gemstone", 99, false, 5.66);
        item3 = itemDao.create(item3);

        // other_slots
        otherSlots slot1 = new otherSlots(1, otherSlots.NAME.LEGS,1);
        oSlotsDao.create(slot1);
        otherSlots slot2 = new otherSlots(2, otherSlots.NAME.BODY,2);
        oSlotsDao.create(slot2);
        otherSlots slot3 = new otherSlots(3, otherSlots.NAME.EARRING,3);
        oSlotsDao.create(slot3);
        otherSlots slot4 = new otherSlots(4, otherSlots.NAME.HEAD,4);
        oSlotsDao.create(slot4);
        otherSlots slot5 = new otherSlots(5, otherSlots.NAME.NECK,4);
        oSlotsDao.create(slot5);

        // EquippedGear
        EquippedGear equippedGear1 = new EquippedGear(1,1,1);
        equippedGear1 = equippedGearsDao.create(equippedGear1);
        EquippedGear equippedGear2 = new EquippedGear(2,2,2);
        equippedGear2 = equippedGearsDao.create(equippedGear2);
        EquippedGear equippedGear3 = new EquippedGear(3,3,3);
        equippedGear3 = equippedGearsDao.create(equippedGear3);
        EquippedGear equippedGear4 = new EquippedGear(4,4,4);
        equippedGear4 = equippedGearsDao.create(equippedGear4);
        EquippedGear equippedGear5 = new EquippedGear(5,5,4);
        equippedGear5 = equippedGearsDao.create(equippedGear5);
        EquippedGear equippedGearToDelete = new EquippedGear(6,6,4);
        equippedGearToDelete = equippedGearsDao.create(equippedGearToDelete);

        Job job1 = new Job(1,true,"Mage", 20);
        job1 = jobsDao.create(job1);
        Job job2 = new Job(2,true,"Fighter",10);
        job2 = jobsDao.create(job2);
        Job job3 = new Job(3,false,"Thief", 9);
        job3 = jobsDao.create(job3);
        Job job4 = new Job(4,false,"Mage",18);
        job4 = jobsDao.create(job4);
        Job job5 = new Job(5,false,"Fighter", 15);
        job5 = jobsDao.create(job5);
        Job job_to_delete = new Job(6,false,"delete", 15);
        job_to_delete = jobsDao.create(job_to_delete);

        // Gear
        Gear gear1 = new Gear(1, "a",10,false,1.5, 1, 5, 10.10, 4, 1, 2);
        gear1 = gearsDao.create(gear1);
        Gear gear2 = new Gear(2, "b", 15, true, 2.5,2, 5, 20.12, 5, 2, 3);
        gear2 = gearsDao.create(gear2);
        Gear gear3 = new Gear(3, "c", 20, true, 3.0, 3, 5, 4.14, 6, 3, 2);
        gear3 = gearsDao.create(gear3);
        Gear gear4 = new Gear(4, "d", 20, true, 3.0, 3, 5, 4.14, 6, 4, 2);
        gear3 = gearsDao.create(gear4);

        Player player1 = new Player("user1", "Leo", "player1@example.com", "Address One", "111-111-1111", true);
        playerDao.create(player1);
        Player player2 = new Player("user2", "Kevin", "player2@example.com", "Address Two", "222-222-2222", true);
        playerDao.create(player2);
        Player player3 = new Player("user3", "Kevin", "player3@example.com", "Address Three", "333-333-3333", false);
        playerDao.create(player3);
        Player player4 = new Player("user4", "Marry", "player4@example.com", "Address Four", "444-444-4444", false);
        playerDao.create(player4);
        Player player5 = new Player("user5", "Marry", "player4@example.com", "Address Four", "444-444-4444", false);
        playerDao.create(player5);

        Attribute attribute1 = new Attribute(1,1, 1, 1, 1);
        attDao.create(attribute1);
        Attribute attribute2 = new Attribute(2,2, 2, 2, 2);
        attDao.create(attribute2);
        Attribute attribute3 = new Attribute(3,3, 3, 3, 3);
        attDao.create(attribute3);
        Attribute attribute4 = new Attribute(4,4, 4, 4, 4);
        attDao.create(attribute4);

        Each_Character each_Character1 = new Each_Character(1,"user1","1","1",100,1,1,1,1);
        each_characterDao.create(each_Character1);
        Each_Character each_Character2 = new Each_Character(2,"user2","2","2",200,2,2,2,2);
        each_characterDao.create(each_Character2);
        Each_Character each_Character3 = new Each_Character(3,"user3","3","3",300,3,3,3,3);
        each_characterDao.create(each_Character3);
        Each_Character each_Character4 = new Each_Character(4,"user4","4","4",400,3,4,4,4);
        each_characterDao.create(each_Character4);

        CustomizedItem customizedItem1 = new CustomizedItem(1, 1, 1, "Red", true, false, 80.5);
        customizedItemDao.create(customizedItem1);
        CustomizedItem customizedItem2 = new CustomizedItem(2, 2, 2, "Blue", false, true, 95.2);
        customizedItemDao.create(customizedItem2);
        CustomizedItem customizedItem3 = new CustomizedItem(3, 3, 3, "Green", true, false, 70.0);
        customizedItemDao.create(customizedItem3);
        CustomizedItem customizedItem4 = new CustomizedItem(4, 4, 4, "Yellow", false, true, 88.7);
        customizedItemDao.create(customizedItem4);

        Date date = new Date();
        Inventory inventory = new Inventory(each_Character2, 70);
        inventory = inventoryDao.create(inventory);
        Inventory inventory1 = new Inventory(each_Character2, 70);
        inventory1 = inventoryDao.create(inventory1);

        Exp_Needed exp_Needed1 = new Exp_Needed(1,2,100);
        exp_Needed1 = exp_NeededDao.create(exp_Needed1);
        Exp_Needed exp_Needed2 = new Exp_Needed(2,3,100);
        exp_Needed2 = exp_NeededDao.create(exp_Needed2);
        Exp_Needed exp_Needed3 = new Exp_Needed(3,5,60);
        exp_Needed3 = exp_NeededDao.create(exp_Needed3);
        Exp_Needed exp_Needed4 = new Exp_Needed(4,8,35);
        exp_Needed4 = exp_NeededDao.create(exp_Needed4);
        Exp_Needed exp_Needed5 = new Exp_Needed(5,4,40);
        exp_Needed5 = exp_NeededDao.create(exp_Needed5);

        CharacterCurrency characterCurrency = new CharacterCurrency(each_Character1, currencies, 585, 20);
        characterCurrency = characterCurrencyDao.create(characterCurrency);
        CharacterCurrency characterCurrency1 = new CharacterCurrency(each_Character2, currencies1, 700, 0);
        characterCurrency = characterCurrencyDao.create(characterCurrency1);
        CharacterCurrency characterCurrency2 = new CharacterCurrency(each_Character3, currencies2, 300, 11);

        //Job_of_Character
        Job_of_Character job_of_character1 = new Job_of_Character(1,1,true, 2,1200);
        job_of_character1 = jobs_of_characterDao.create(job_of_character1);
        Job_of_Character job_of_character2 = new Job_of_Character(2,2,true,3,988);
        job_of_character2 = jobs_of_characterDao.create(job_of_character2);
        Job_of_Character job_of_character3 = new Job_of_Character(3,3,false, 5,900);
        job_of_character3 = jobs_of_characterDao.create(job_of_character3);
        Job_of_Character job_of_character4 = new Job_of_Character(4,4,true,7,500);
        job_of_character4 = jobs_of_characterDao.create(job_of_character4);
        Job_of_Character job_of_character5 = new Job_of_Character(5,5,false, 4,200);
        job_of_character5 = jobs_of_characterDao.create(job_of_character5);

        Job_of_Item joi1 = new Job_of_Item(1,1);
        job_of_itemDao.creat(joi1);
        Job_of_Item joi2 = new Job_of_Item(2,2);
        job_of_itemDao.creat(joi2);
        Job_of_Item joi3 = new Job_of_Item(3,3);
        job_of_itemDao.creat(joi3);
        Job_of_Item joi4 = new Job_of_Item(4,4);
        job_of_itemDao.creat(joi4);

        Weapon weapon1 = new Weapon(1,"name1",10,true,20.1,10,10,10.1,50.2,true,20);
        weapon1 = weaponDao.create(weapon1);
        Weapon weapon2 = new Weapon(2,"name2",10,true,20.1,10,10,10.1,50.2,true,20);
        weapon2 = weaponDao.create(weapon2);
        Weapon weapon3 = new Weapon(3,"name3",10,true,20.1,10,10,10.1,50.2,true,20);
        weapon3 = weaponDao.create(weapon3);

        Slot eachSlot1 = new Slot(1, 1, 10,1,1);
        slotDao.create(eachSlot1);
        Slot eachSlot2 = new Slot(2, 2, 5, 2, 2);
        slotDao.create(eachSlot2);
        Slot eachSlot3 = new Slot(3, 3, 10,5,5);
        slotDao.create(eachSlot3);

        Miscellaneous miscellaneous1 = new Miscellaneous(1,"name1",10,true,20.0,"Map");
        miscellaneousDao.create(miscellaneous1);
        Miscellaneous miscellaneous2 = new Miscellaneous(2,"name2",10,false,25.0,"Key");
        miscellaneousDao.create(miscellaneous2);
        Miscellaneous miscellaneous3 = new Miscellaneous(1,"name1",10,true,20.0, "Key");
        miscellaneousDao.create(miscellaneous3);

        Consumables consumables1 = new Consumables(1,"name1",10,true,20.1,100,"Health",40.25);
        consumables1 = consumablesDao.create(consumables1);
        Consumables consumables2 = new Consumables(2,"name2",10,true,22.1,10,"Mana",50.1);
        consumables2 = consumablesDao.create(consumables2);
        Consumables consumables3 = new Consumables(3,"name3",30,false,22.1,100,"Strength",40.35);
        consumables3 = consumablesDao.create(consumables3);


        Attribute att = attDao.getAttributeByCharacterId(1);
        System.out.println(att.getDexterity());


        // READ.
        Item i1 = itemDao.getItemById(1);
        List<Item> iList1 = itemDao.getItemFromItemName("Sapphire Spellblade");
        System.out.format("Reading item: id:%d in:%s ims:%d csv:%b vp:%.2f \n",
                i1.getItem_id(), i1.getItem_name(), i1.getItem_max_size(), i1.isCould_sold_vendor(), i1.getVendor_price());
        for(Item i : iList1) {
            System.out.format("Looping item: id:%d in:%s ims:%d csv:%b vp:%.2f \n",
                    i.getItem_id(), i.getItem_name(), i.getItem_max_size(), i.isCould_sold_vendor(), i.getVendor_price());
        }

        Inventory in1 = inventoryDao.getInventoryById(1);
        if (in1 != null && in1.getEach_character() != null) {
            System.out.format("Reading inventory: id:%d c:%d nos:%d \n",
                    in1.getInventory_id(), in1.getEach_character().getCharacterId(), in1.getNumber_of_slots());
        } else {
            System.out.println("Inventory or character is null");
        }
        /**
         System.out.format("Reading inventory: id:%d c:%d nos:%d \n",
         in1.getInventory_id(), in1.getEach_character(), in1.getNumber_of_slots());
         **/


        Currencies c1 = currenciesDao.getCurrenciesById(1);
        System.out.format("Reading currency: id:%d cn:%s mc:%.2f wc:%.2f a:%.2f wm%.2f \n",
                c1.getCurrency_id(), c1.getCurrency_name(), c1.getMax_cap(), c1.getWeekly_cap(), c1.getAmount(), c1.getAmount_weekly());

        /**CharacterCurrency cc1 = characterCurrencyDao.getCharacterCurrencyByCharacter_idAndCurrency_id(each_Character2, currencies);
        if (cc1 != null) {
            System.out.format("Reading character currency: id:%d id:%d  a:%.2f wa:%.2f \n",
                    cc1.getEach_character().getCharacterId(), cc1.getCurrencies().getCurrency_id(), cc1.getAmount_weekly(), cc1.getAmount_total());
        } else {
            System.out.println("No character currency found for the specified character id and currency id.");
        }**/



        Job deletedJob = jobsDao.delete(job_to_delete);
        Job getJob4 = jobsDao.getJobByJob_id(4);
        List<Job> getJobFighter = jobsDao.getJobsByCategory("Fighter");

        System.out.format("Reading job4: availability:%s category:%s maximum_level:%s \n",
                getJob4.getAvaliability(), getJob4.getCategory(), getJob4.getMaximum_level());
        for(Job j : getJobFighter) {
            System.out.format("Looping jobs with category Fighter: availability:%s category:%s maximum_level:%s \n",
                    j.getAvaliability(), j.getCategory(), j.getMaximum_level());
        }


        Job_of_Character getJob_of_character2 = jobs_of_characterDao.getJob_of_CharacterByJobAndCharacter(2, each_Character2.getCharacterId());
        System.out.format("Reading job_of_charater2: Job_id:%s Character_id:%s getUnlocked:%s Current_level:%s EXP_earned:%s \n",
                getJob_of_character2.getJob_id(), getJob_of_character2.getCharacter_id(), getJob_of_character2.getUnlocked(), getJob_of_character2.getCurrent_level(), getJob_of_character2.getEXP_earned());
        Job_of_Character updateLevelJob_of_character4 = jobs_of_characterDao.updateJob_of_CharacterLevel(job_of_character4, 8);
        Job_of_Character updateEXPJob_of_character4 = jobs_of_characterDao.updateJob_of_CharacterEXP(job_of_character4, 600);
        System.out.format("Reading new job_of_charater4: Job_id:%s Character_id:%s getUnlocked:%s Current_level:%s EXP_earned:%s \n",
                job_of_character4.getJob_id(),job_of_character4.getCharacter_id(), job_of_character4.getUnlocked(), job_of_character4.getCurrent_level(), job_of_character4.getEXP_earned());


        // Exp_Needed


        Exp_Needed exp_NeededToGet = exp_NeededDao.getExp_NeededByJobOfCharacter(job_of_character1);
        System.out.format("Reading exp_needed of job_of_character1: Job_id:%s Current_level:%s EXP_earned:%s \n",
                exp_NeededToGet.getJob_id(),exp_NeededToGet.getCurrent_level(), exp_NeededToGet.getEXP_needed_for_current_level());


        EquippedGear deletedEquippedGear = equippedGearsDao.delete(equippedGearToDelete);
        EquippedGear getEquippedGear1 = equippedGearsDao.getEquippedGearByEquippedGear_id(1);
        System.out.format("Reading getEquippedGear1: EquippedGear_id:%s MainHand:%s Other_slot:%s\n",
                getEquippedGear1.getEquippedGear_id(),getEquippedGear1.getMainHand(),getEquippedGear1.getOther_slot());
        EquippedGear updateMainHandEquippedGear2 = equippedGearsDao.updateMainHand(equippedGear2, 2);
        EquippedGear updateOther_slotEquippedGear2 = equippedGearsDao.updateOther_slot(equippedGear2, 2);
        System.out.format("Reading new equippedGear2: EquippedGear_id:%s MainHand:%s Other_slot:%s\n",
                equippedGear2.getEquippedGear_id(),equippedGear2.getMainHand(),equippedGear2.getOther_slot());
        //System.out.println(playerDao.getPlayerByUserName("user1").getPlayerName());
    }
}
