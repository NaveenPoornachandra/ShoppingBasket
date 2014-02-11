-- Inserting User Group Data ----
--INSERT INTO shopping_basket.user_group VALUES ('naveen', 'REG_USER');
--INSERT INTO shopping_basket.user_group VALUES ('pavan', 'UN_REG_USER');
--INSERT INTO shopping_basket.user_group VALUES ('smitha', 'UN_REG_USER');
--- Insert User Data -------
--INSERT INTO shopping_basket.user_table  VALUES ('Admin', '1982-05-09', 'naveen_p08@infosys.com', 'admin@123');
--INSERT INTO shopping_basket.user_table  VALUES ('naveen', '1982-05-09', 'naveen_p08@infosys.com', 'infy@123');
--INSERT INTO shopping_basket.user_table  VALUES ('pavan', '1986-04-04', 'vinaycpsail@gmail.com', 'home@123');
--INSERT INTO shopping_basket.user_table VALUES ('smitha', '1986-01-25', 'naveen_p08@infosys.com', 'home@123');
INSERT INTO shopping_basket.user_table VALUES ('Admin', '1982-05-09', 'naveen_p08@infosys.com', 'admin@123');
INSERT INTO shopping_basket.user_table VALUES ('naveen', '1982-05-09', 'naveen_p08@infosys.com', 'infy@123');
INSERT INTO shopping_basket.user_table VALUES ('pavan', '1986-04-04', 'vinaycpsail@gmail.com', 'home@123');
INSERT INTO shopping_basket.user_table VALUES ('smitha', '1986-01-25', 'naveen_p08@infosys.com', 'home@123');

INSERT INTO shopping_basket.group_table VALUES ('AUTHORIZED_USER', 'Admin');
INSERT INTO shopping_basket.group_table VALUES ('REG_USER', 'Admin');
INSERT INTO shopping_basket.group_table VALUES ('UN_AUTHORIZED_USER', 'Admin');
INSERT INTO shopping_basket.group_table VALUES ('UN_REG_USER', 'Admin');
INSERT INTO shopping_basket.group_table VALUES ('ADMIN_USER', 'naveen');

INSERT INTO shopping_basket.user_group VALUES ('ADMIN_USER', 'naveen');
INSERT INTO shopping_basket.user_group VALUES ('UN_REG_USER', 'pavan');
INSERT INTO shopping_basket.user_group VALUES ('REG_USER', 'smitha');
