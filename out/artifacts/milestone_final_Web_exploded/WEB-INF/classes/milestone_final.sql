DROP SCHEMA IF EXISTS CS5200Project;
CREATE SCHEMA CS5200Project;
USE CS5200Project;

-- Attribute Table
Create TABLE Attribute(
	attribute_id INT AUTO_INCREMENT,
	strength INT NOT NULL,
	dexterity INT NOT NULL ,
	intelligence INT NOT NULL ,
	mind INT NOT NULL,
	CONSTRAINT pk_Attribute_Attribute_id PRIMARY KEY (attribute_id)
);
INSERT INTO Attribute(strength,dexterity,intelligence,mind) 
VALUES
	(1,1,1,1),
	(2,2,2,2),
    (3,3,3,3),
    (4,4,4,4),
    (5,5,5,5),
    (5,5,5,5),
    (5,5,5,5),
    (5,5,5,5),
    (5,5,5,5),
    (5,5,5,5);
SELECT * FROM Attribute;

-- Item Table
CREATE TABLE Item (
	item_id INT PRIMARY KEY,
	item_name VARCHAR(255) NOT NULL,
	item_max_size INT NOT NULL,
	could_sold_vendor BOOL,
	vendor_price DECIMAL(10, 2)
);
INSERT INTO Item(item_id,item_name,item_max_size,could_sold_vendor,vendor_price)
VALUES
	(1, "Diadochos Jacket of Fending",10,false,1.5),
	(2, "Sapphire Spellblade", 15, true, 2.5),
	(3, "Vial of Liquid Shadows", 20, true, 3.0),
	(4, "Sunfire Gemstone", 12, false, 2.0),
	(5, "Iron Helmet", 30, true, 2.8),
    (6, "Frost Bow", 14, true, 1.8),
    (7, "Arcane Staff", 9, true, 2.2),
    (8, "Flame Dagger", 13, true, 3.5),
    (9, "Healing Potion", 21, true, 6.3),
    (10, "Enchanted Key", 16, true, 1.7);
SELECT * FROM Item;

-- Other_slots Table
CREATE TABLE Other_Slots(
	other_slot_id int AUTO_INCREMENT,
	slot_category varchar(255) UNIQUE
	CHECK ( slot_category 
		IN('head','body','hands','legs','feet',
			'offhand','earring','neck','wrist',
			'leftring','rightring') ),
	item_id INT,
	CONSTRAINT pk_Other_Slots_other_slot_id PRIMARY KEY (other_slot_id),
	CONSTRAINT fk_Other_Slots_item_id FOREIGN KEY (item_id)
		REFERENCES Item(item_id)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO Other_Slots(slot_category,item_id) 
VALUES 
	('head',1),
	('body',2),
    ('hands',3),
    ('legs',4),
    ('feet',5),
    ('earring',6),
    ('neck',7),
    ('wrist',8),
    ('leftring',9),
    ('rightring',10);
SELECT * FROM Other_Slots;

-- EquippedGear Table
CREATE TABLE EquippedGear (
	equippedGear_id INT AUTO_INCREMENT,
	mainHand INT NOT NULL,
	other_slot INT NOT NULL,
	CONSTRAINT pk_EquippedGear_equippedGear_id PRIMARY KEY (equippedGear_id),
	CONSTRAINT fk_EquippedGear_mainHand FOREIGN KEY (MainHand)
		REFERENCES Item(Item_id)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_EquippedGear_other_slot FOREIGN KEY (other_slot)
		REFERENCES other_slots (other_slot_id)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO EquippedGear(mainHand, other_slot) 
VALUES 
	(1,1),
	(2,2),
    (3,3),
    (4,4),
    (5,5),
    (5,5),
    (6,6),
    (7,7),
    (8,8),
    (9,9),
    (10,10);
SELECT * FROM EquippedGear;

-- Currencies Table
CREATE TABLE Currencies (
	currency_id INT AUTO_INCREMENT,
	currency_name CHAR(50) NOT NULL,
	max_cap DECIMAL NOT NULL,
	weekly_cap DECIMAL NOT NULL,
	amount DECIMAL NOT NULL,
	amount_weekly DECIMAL NOT NULL,
	CONSTRAINT pk_Currencies_currency_id PRIMARY KEY (currency_id)
);
INSERT INTO Currencies(currency_id, currency_name, max_cap, weekly_cap, amount, amount_weekly)
VALUES (1,"dollar",1000,100,100000,800),
       (2,"Yen",1000,100,100000,800),
       (3,"Yuan",1000,100,100000,800),
       (4,"name1",1000,100,100000,800),
       (5,"name2",1000,100,100000,800),
       (6,"name3",1000,100,100000,800),
       (7,"name4",1000,100,100000,800),
       (8,"name5",1000,100,100000,800),
       (9,"name6",1000,100,100000,800),
       (10,"name7",1000,100,100000,800);
     
-- Player Table
CREATE TABLE Player (
	UserName VARCHAR(255)  NOT NULL,
	player_name VARCHAR(255),
	email VARCHAR(255),
	address VARCHAR(255),
	phone VARCHAR(20),
	is_active BOOLEAN,
	CONSTRAINT pk_Player_UserName PRIMARY KEY (UserName)
);
INSERT INTO Player(UserName, player_name, email, address, phone, is_active)
VALUES
    ("user1","name1","name1@mail.com","name1 address","111-111-1111",true),
    ("user2","name2","name2@mail.com","name2 address","111-111-1111",false),
    ("user3","name3","name3@mail.com","name3 address","111-111-1111",true),
    ("user4","name4","name4@mail.com","name4 address","111-111-1111",false),
    ("user5","name5","name5@mail.com","name5 address","111-111-1111",true),
    ("user6","name6","name6@mail.com","name6 address","111-111-1111",true),
    ("user7","name7","name7@mail.com","name7 address","111-111-1111",false),
    ("user8","name8","name8@mail.com","name8 address","111-111-1111",false),
    ("user9","name9","name9@mail.com","name9 address","111-111-1111",true),
    ("user10","name10","name10@mail.com","name10 address","111-111-1111",false);

-- Each_Character Table
CREATE TABLE Each_Character (
	character_id INT AUTO_INCREMENT,
	player_name VARCHAR(255) NOT NULL,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	maxHP INT NOT NULL ,
	maxMP INT DEFAULT 10000,
	job_id INT,
	currency_id INT,
	attribute_id INT NOT NULL ,
	equippedGear_id INT NOT NULL ,
	CONSTRAINT pk_Each_Character_character_id PRIMARY KEY (character_id),
	CONSTRAINT fk_Each_Character_currency_id FOREIGN KEY (currency_id)
		REFERENCES Currencies(currency_id)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Each_Character_attribute_id FOREIGN KEY (attribute_id)
		REFERENCES Attribute(attribute_id)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Each_Character_equippedGear_id FOREIGN KEY (equippedGear_id)
		REFERENCES EquippedGear(equippedGear_id)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Each_Character_player_id FOREIGN KEY (player_name)
		REFERENCES Player(UserName)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO Each_Character(character_id, player_name, first_name, last_name, maxHP, job_id, currency_id, attribute_id, equippedGear_id)
VALUES (1,"user1","first1","last1",100,1,1,1,1),
       (2,"user2","first2","last2",10,3,2,2,2),
       (3,"user3","first3","last3",200,4,3,3,3),
       (4,"user4","first4","last4",1100,5,4,4,4),
       (5,"user5","first5","last5",1700,6,5,5,5),
       (6,"user6","first6","last6",1300,7,6,6,6),
       (7,"user7","first7","last7",1000,8,7,7,7),
       (8,"user8","first8","last8",1600,9,8,8,8),
       (9,"user9","first9","last9",1500,10,9,9,9),
       (10,"user10","first10","last10",90,2,10,10,10);


-- Job Table
CREATE TABLE Job (
	job_id INT AUTO_INCREMENT,
	avaliability BOOLEAN NOT NULL,
	category VARCHAR(255) NOT NULL,
	maximum_level INT NOT NULL,
    CONSTRAINT pk_Job_job_id PRIMARY KEY (job_id)
);
INSERT INTO Job(job_id, avaliability, category, maximum_level)
VALUES (1,true,"category1",100),
       (2,false,"category2",100),
       (3,true,"category3",100),
       (4,true,"category4",100),
       (5,false,"category5",100),
       (6,true,"category6",100),
       (7,false,"category7",100),
       (8,false,"category8",100),
       (9,true,"category9",100),
       (10,false,"category10",100);

-- Exp_Needed Table
CREATE TABLE EXP_Needed (
	job_id INT AUTO_INCREMENT,
	current_level INT NOT NULL,
	EXP_needed_for_current_level INT NOT NULL,
	CONSTRAINT pk_EXP_Needed_job_id_current_level PRIMARY KEY (job_id, current_level),
	CONSTRAINT fk_EXP_Needed_job_id FOREIGN KEY (job_id)
		REFERENCES Job(job_id)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO EXP_Needed (job_id, current_level, EXP_needed_for_current_level) 
  VALUES(1,2,100),
		(2,3,100),
        (3,5,60),
        (4,8,35),
        (5,4,40),
        (6,7,120),
		(7,9,300),
        (8,6,110),
        (9,10,500),
        (10,1,20);


-- Job_of_Character Table
CREATE TABLE Job_of_Character (
	job_id INT NOT NULL,
	character_id INT NOT NULL,
	unlocked BOOLEAN NOT NULL,
	current_level int NOT NULL,
	EXP_earned INT,
	CONSTRAINT pk_Job_of_Character_job_id_character_id PRIMARY KEY (job_id, character_id),
	CONSTRAINT fk_Job_of_Character_job_job_id FOREIGN KEY (job_id)
		REFERENCES Job(job_id),
	CONSTRAINT fk_Job_of_Character_character_id FOREIGN KEY (character_id)
		REFERENCES Each_Character(character_id),
	CONSTRAINT fk_Job_of_Character_EXP_Needed_job_id FOREIGN KEY (job_id, current_level)
		REFERENCES EXP_Needed(job_id,current_level)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO Job_of_Character (job_id, character_id, unlocked, current_level, EXP_earned) 
  VALUES(1,1,True, 2,1200),
		(2,2,True,3,988),
        (3,3,False, 5,900),
        (4,4,True,8,600),
        (5,5,False, 4,200),
        (6,6,True, 7,2400),
		(7,7,True,9,1530),
        (8,8,False, 6,850),
        (9,9,True,10,2000),
        (10,10,False, 1,15);


-- Inventory Table
CREATE TABLE Inventory(
	inventory_id INT AUTO_INCREMENT,
	character_id INT NOT NULL,
	number_of_slots INT NOT NULL,
	CONSTRAINT pk_Inventory_inventory_id PRIMARY KEY (inventory_id),
	CONSTRAINT fk_Inventory_characte_id FOREIGN KEY (character_id)
		REFERENCES Each_Character(character_id)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO Inventory(character_id,number_of_slots)
VALUES(1,180),(2,300),(3,200),(4,250),(5, 350),
(6,250),(7,330),(8,500),(9,150),(10, 200);


-- Slot Table
CREATE TABLE Slot(
	slot_id INT AUTO_INCREMENT,
	item_id INT,
	item_amount INT,
	slot_position INT NOT NULL,
	inventory_id INT NOT NULL,
	CONSTRAINT pk_Slot_slot_id PRIMARY KEY (slot_id),
	CONSTRAINT fk_Slot_item_id FOREIGN KEY (item_id)
		REFERENCES Item(item_id),
	CONSTRAINT fk_Slot_inventory_id FOREIGN KEY (inventory_id)
		REFERENCES Inventory(inventory_id)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO Slot(item_id,item_amount,slot_position,inventory_id)
VALUES(1,10,1,1),(2,7,2,2),(3,8,3,3),(4,9,4,4),(5, 13,5,5),
(6,12,6,6),(7,16,7,7),(8,20,8,8),(9,55,9,9),(10, 23,10,10);

-- CharacterCurrency Table
CREATE TABLE CharacterCurrency (
	character_id INT NOT NULL,
	currency_id INT NOT NULL,
	amount_weekly DECIMAL NOT NULL,
	amount_total DECIMAL NOT NULL,
	CONSTRAINT pk_CharacterCurrency_character_id_currency_id PRIMARY KEY (character_id, currency_id),
	CONSTRAINT fk_CharacterCurrency_character_id FOREIGN KEY (character_id)
		REFERENCES Each_Character(character_id)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_CharacterCurrency_currency_id FOREIGN KEY (currency_id)
		REFERENCES Currencies(currency_id)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO CharacterCurrency (character_id, currency_id, amount_weekly, amount_total)
VALUES
(1, 1, 10.00, 100.00),
(2, 2, 20.00, 200.00),
(3, 3, 30.00, 300.00),
(4, 4, 40.00, 400.00),
(5, 5, 50.00, 500.00),
(6, 6, 60.00, 600.00),
(7, 7, 70.00, 700.00),
(8, 8, 80.00, 800.00),
(9, 9, 90.00, 900.00),
(10, 10, 100.00, 1000.00);


-- Gear Table
CREATE TABLE Gear (
	Item_id INT AUTO_INCREMENT,
	gear_level INT NOT NULL,
	required_level INT NOT NULL,
	attribute_bonuses DECIMAL NOT NULL,
	defense_rating INT NOT NULL,
	customerID INT NOT NULL,
	magic_defense_rating INT NOT NULL,
	CONSTRAINT pk_Gear_Gear_id PRIMARY KEY (Item_id),
	CONSTRAINT fk_Gear_Gear_id FOREIGN KEY (Item_id)
		REFERENCES Item(item_id)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO Gear (gear_level, required_level, attribute_bonuses, defense_rating, customerID, magic_defense_rating)
VALUES
(1, 1, 1.0, 10, 1, 5),
(2, 2, 2.0, 20, 2, 10),
(3, 3, 3.0, 30, 3, 15),
(4, 4, 4.0, 40, 4, 20),
(5, 5, 5.0, 50, 5, 25),
(6, 6, 6.0, 60, 6, 30),
(7, 7, 7.0, 70, 7, 35),
(8, 8, 8.0, 80, 8, 40),
(9, 9, 9.0, 90,9,45),
(10, 10, 10.0, 100,10,50),


-- Customized_item Table
CREATE TABLE Customized_item (
	Customized_item_id INT AUTO_INCREMENT,
	item_id INT NOT NULL,
	character_id INT NOT NULL,
	dye_color CHAR(50) NOT NULL,
	high_quality BOOL,
	normal_quality BOOL,
	condition_value DECIMAL NOT NULL,
	CONSTRAINT pk_Customized_item_Customized_item_id PRIMARY KEY (Customized_item_id),
	CONSTRAINT fk_Customized_item_item_id FOREIGN KEY (item_id)
		REFERENCES Gear(Item_id)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Customized_item_character_id FOREIGN KEY (character_id)
		REFERENCES Each_Character(character_id)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO Customized_item (item_id, character_id, dye_color, high_quality, normal_quality, condition_value)
VALUES
(1, 1, 'Red', TRUE, FALSE, 90.0),
(2, 2, 'Blue', FALSE, TRUE, 80.0),
(3, 3, 'Green', TRUE, FALSE, 85.0),
(4, 4, 'Yellow', FALSE, TRUE, 75.0),
(5, 5, 'Purple', TRUE, FALSE, 95.0),
(6, 6, 'Orange', FALSE, TRUE, 70.0),
(7, 7, 'Pink', TRUE, FALSE, 65.0),
(8, 8, 'Black', FALSE, TRUE, 60.0),
(9, 9, 'White', TRUE, FALSE, 55.0),
(10, 10, 'Grey', FALSE, TRUE, 50.0);



-- Weapon Table
CREATE TABLE Weapon (
	weapon_id INT PRIMARY KEY AUTO_INCREMENT,
	item_level INT NOT NULL,
	required_level INT NOT NULL,
	attribute_bonuses DECIMAL(10, 2),
	damage_done DECIMAL(10, 2),
	auto_attack BOOLEAN,
	attack_delay INT NOT NULL,
	CONSTRAINT fk_Weapon_Weapon_id FOREIGN KEY (Weapon_id)
		REFERENCES Item(item_id)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO Weapon (item_level, required_level, attribute_bonuses, damage_done, auto_attack, attack_delay)
VALUES
(1, 1, 1.1, 10.1, TRUE, 1),
(2, 2, 2.2, 20.2, FALSE, 2),
(3, 3, 3.3, 30.3, TRUE, 3),
(4, 4, 4.4, 40.4, FALSE, 4),
(5, 5, 5.5, 50.5, TRUE, 5),
(6, 6, 6.6, 60.6, FALSE, 6),
(7, 7, 7.7, 70.7, TRUE, 7),
(8, 8, 8.8, 80.8, FALSE, 8),
(9, 9, 9.9, 90.9, TRUE, 9),
(10, 10, 10.0, 100.0, FALSE, 10);


-- Job_of_Item Table
CREATE TABLE Job_of_Item (
	Job_ID INT AUTO_INCREMENT,
	Customized_item_id int NOT NULL,
	CONSTRAINT pk_Job_of_Item_Job_ID_Customized_item_id PRIMARY KEY (Job_ID,Customized_item_id),
	CONSTRAINT fk_Job_of_Item_Job_ID FOREIGN KEY (Job_ID)
		REFERENCES Job(Job_ID)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Job_of_Item_Customized_item_id FOREIGN KEY (Customized_item_id)
		REFERENCES Customized_item(Customized_item_id)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO Job_of_Item (Customized_item_id)
VALUES
(1), (2), (3), (4), (5), (6), (7), (8), (9), (10);

-- Consumables Table
CREATE TABLE Consumables (
	consumables_id INT PRIMARY KEY AUTO_INCREMENT,
	item_level INT NOT NULL,
	descriptions TEXT NOT NULL,
	attribute_bonuses DECIMAL(10, 2) NOT NULL,
	CONSTRAINT fk_Consumables_Consumables_id FOREIGN KEY (consumables_id)
		REFERENCES Item(item_id)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO Consumables (item_level, descriptions, attribute_bonuses)
VALUES
(1, 'Health Potion', 1.0),
(2, 'Mana Potion', 2.0),
(3, 'Stamina Potion', 3.0),
(4, 'Elixir of Strength', 4.0),
(5, 'Elixir of Intelligence', 5.0),
(6, 'Speed Boost', 6.0),
(7, 'Invisibility Potion', 7.0),
(8, 'Fire Resistance Potion', 8.0),
(9, 'Ice Resistance Potion', 9.0),
(10, 'Thunder Resistance Potion', 10.0);

-- Miscellaneous table
CREATE TABLE Miscellaneous (
	miscellaneous_id INT PRIMARY KEY AUTO_INCREMENT,
	descriptions TEXT NOT NULL,
	CONSTRAINT fk_Miscellaneous_Miscellaneous_id FOREIGN KEY (miscellaneous_id)
		REFERENCES Item(item_id)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO Miscellaneous (descriptions)
VALUES
('Ancient Scroll'),
('Mystic Crystal'),
('Rare Artifact'),
('Enchanted Feather'),
('Magic Stone'),
('Sacred Charm'),
('Eternal Flame'),
('Frozen Tear'),
('Celestial Shard'),
('Phantom Dust');
