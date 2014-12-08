-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`address` (
  `addressid` INT(255) NOT NULL AUTO_INCREMENT,
  `Street` VARCHAR(255) NULL DEFAULT NULL,
  `Suburb` VARCHAR(255) NULL DEFAULT NULL,
  `City` VARCHAR(255) NULL DEFAULT NULL,
  `Zip` VARCHAR(255) NULL DEFAULT NULL,
  `State/Province` VARCHAR(255) NULL DEFAULT NULL,
  `Country` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`addressid`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mydb`.`food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`food` (
  `foodid` INT(11) NOT NULL AUTO_INCREMENT,
  `foodName` VARCHAR(255) NULL DEFAULT NULL,
  `refCount` INT(11) NULL DEFAULT NULL,
  `imgPath` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(1024) NULL DEFAULT NULL,
  PRIMARY KEY (`foodid`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ingredient` (
  `ingredientid` INT(11) NOT NULL AUTO_INCREMENT,
  `ingredName` VARCHAR(255) NULL DEFAULT NULL,
  `allergen` VARCHAR(255) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `refCount` INT(11) NULL DEFAULT NULL,
  `imgPath` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(1024) NULL DEFAULT NULL,
  PRIMARY KEY (`ingredientid`))
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`food_has_ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`food_has_ingredient` (
  `food_foodid` INT(11) NOT NULL,
  `ingredient_ingredientid` INT(11) NOT NULL,
  PRIMARY KEY (`food_foodid`, `ingredient_ingredientid`),
  INDEX `fk_food_has_ingredient_ingredient1_idx` (`ingredient_ingredientid` ASC),
  INDEX `fk_food_has_ingredient_food1_idx` (`food_foodid` ASC),
  CONSTRAINT `fk_food_has_ingredient_food1`
    FOREIGN KEY (`food_foodid`)
    REFERENCES `mydb`.`food` (`foodid`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_food_has_ingredient_ingredient1`
    FOREIGN KEY (`ingredient_ingredientid`)
    REFERENCES `mydb`.`ingredient` (`ingredientid`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`groups` (
  `groupid` INT(11) NOT NULL AUTO_INCREMENT,
  `groupname` VARCHAR(255) NULL DEFAULT NULL,
  `groupdesc` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`groupid`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mydb`.`menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`menu` (
  `menuid` INT(11) NOT NULL AUTO_INCREMENT,
  `menuName` VARCHAR(255) NULL DEFAULT NULL,
  `imgPath` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(1024) NULL DEFAULT NULL,
  PRIMARY KEY (`menuid`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`menu_has_food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`menu_has_food` (
  `menu_menuid` INT(11) NOT NULL,
  `food_foodid` INT(11) NOT NULL,
  PRIMARY KEY (`menu_menuid`, `food_foodid`),
  INDEX `fk_menu_has_food_food1_idx` (`food_foodid` ASC),
  INDEX `fk_menu_has_food_menu1_idx` (`menu_menuid` ASC),
  CONSTRAINT `fk_menu_has_food_food1`
    FOREIGN KEY (`food_foodid`)
    REFERENCES `mydb`.`food` (`foodid`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_menu_has_food_menu1`
    FOREIGN KEY (`menu_menuid`)
    REFERENCES `mydb`.`menu` (`menuid`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`nutrition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`nutrition` (
  `nutritionid` INT(11) NOT NULL AUTO_INCREMENT,
  `calories` INT(11) NULL DEFAULT NULL,
  `fat` INT(11) NULL DEFAULT NULL,
  `protein` INT(11) NULL DEFAULT NULL,
  `sodium` INT(11) NULL DEFAULT NULL,
  `sugar` INT(11) NULL DEFAULT NULL,
  `carbohydrates` INT(11) NULL DEFAULT NULL,
  `cholesterol` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`nutritionid`),
  CONSTRAINT `keyToIngredient`
    FOREIGN KEY (`nutritionid`)
    REFERENCES `mydb`.`ingredient` (`ingredientid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `userid` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(64) NULL DEFAULT NULL,
  `firstname` VARCHAR(255) NULL DEFAULT NULL,
  `lastname` VARCHAR(255) NULL DEFAULT NULL,
  `birth` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `address_addressid` INT(255) NOT NULL,
  PRIMARY KEY (`userid`),
  INDEX `fk_user_address1` (`address_addressid` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_addressid`)
    REFERENCES `mydb`.`address` (`addressid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mydb`.`user_has_groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user_has_groups` (
  `User_userid` INT(11) NOT NULL,
  `Groups_groupid` INT(11) NOT NULL,
  PRIMARY KEY (`User_userid`, `Groups_groupid`),
  INDEX `fk_User_has_Groups_Groups1` (`Groups_groupid` ASC),
  INDEX `fk_User_has_Groups_User1` (`User_userid` ASC),
  CONSTRAINT `fk_User_has_Groups_Groups1`
    FOREIGN KEY (`Groups_groupid`)
    REFERENCES `mydb`.`groups` (`groupid`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_User_has_Groups_User1`
    FOREIGN KEY (`User_userid`)
    REFERENCES `mydb`.`user` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
