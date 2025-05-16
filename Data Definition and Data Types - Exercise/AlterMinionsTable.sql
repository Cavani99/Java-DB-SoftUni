ALTER TABLE `minions` 
ADD COLUMN `town_id` INT NULL AFTER `age`,
ADD INDEX `fk_town_idx` (`town_id` ASC),
ADD CONSTRAINT `fk_town`
  FOREIGN KEY (`town_id`)
  REFERENCES `towns` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;