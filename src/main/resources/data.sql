INSERT INTO `referee` (`id`,`created_date`,`last_modified_date`,`version`,`email`,`first_name`,`last_name`,`phone`)
VALUES('0a818933-087d-47f2-ad83-2f986ed087eb',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,0,'saheer.abhas@gmail.com','Saheer','Abhas','1234565432');

INSERT INTO `student` (`id`,`created_date`,`last_modified_date`,`version`,`email`,`employer`,`first_name`,`last_name`,`occupation`,`phone_no`,`qualification`,`referee_id`)
VALUES ('722f9450-dbed-11ea-87d0-0242ac130003',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,0,'mahima.chaudhary@email.com','TD Bank','mahima','Chaudhary','Clerk','8765434567','BCom','0a818933-087d-47f2-ad83-2f986ed087eb');