INSERT  INTO customer(id, first_name, last_name, telephone, email) VALUES (2, '', '','','');
INSERT  INTO CUSTOMER_MESSAGE(ID, MESSAGE) VALUES (2, '');
INSERT  INTO ORDERS(ID, CONTRACT, LICENSE_PLATE, START_DATE ,STATUS ,CUST_ID , cust_message_id) VALUES
(2, 'DEU21080V', 'mockLicense', '2016-08-25 09:52:28.415', 'NEW', 2, 2);
INSERT  INTO ORDER_ITEMS(ID, CREATION_TIME, ORDER_ID, ITEM_NAME, CURRENCY_CODE, PRICE, DESCRIPTION, ITEM_TYPE, URGENCY, STATUS)  VALUES
(2, '2016-08-25 09:52:28.415', 2, 'orderItemName', 'EUR', '', 'orderItemDescription', 'orderItemType', 'NOT_URGENT', 'NEW');
INSERT  INTO Media(ID,MEDIA_URL, ORDER_ITEM_ID ) VALUES (2,'http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4',2);


INSERT  INTO customer(id, first_name, last_name, telephone, email) VALUES (3, '', '','','');
INSERT  INTO CUSTOMER_MESSAGE(ID, MESSAGE) VALUES (3, '');
INSERT  INTO ORDERS(ID, CONTRACT, LICENSE_PLATE, START_DATE ,STATUS, CUST_ID, cust_message_id) VALUES
(3, 'contract-1', 'mockLicenseSecond', '2016-08-25 09:52:36.415', 'NEW', 3, 3);
INSERT  INTO ORDER_ITEMS(ID, CREATION_TIME, ORDER_ID, ITEM_NAME, CURRENCY_CODE, DESCRIPTION, ITEM_TYPE, URGENCY, STATUS)  VALUES
(3, '2016-08-25 09:52:28.415', 3, 'orderItemName', 'EUR', 'orderItemDescription', 'orderItemType', 'NOT_URGENT', 'NEW');
INSERT  INTO Media(ID, ORDER_ITEM_ID ) VALUES (3,3);

INSERT  INTO customer(id, first_name, last_name, telephone, email) VALUES (4, '', '','','');
INSERT  INTO CUSTOMER_MESSAGE(ID, MESSAGE) VALUES (4, '');
INSERT  INTO ORDERS(ID, CONTRACT, LICENSE_PLATE, START_DATE ,STATUS, CUST_ID, cust_message_id) VALUES
(4, 'contract-1', 'mockLicenseFirst', '2016-08-25 10:52:36.415', 'NEW', 4, 4);
INSERT  INTO ORDER_ITEMS(ID, CREATION_TIME, ORDER_ID, ITEM_NAME, CURRENCY_CODE, DESCRIPTION, ITEM_TYPE, URGENCY, STATUS)  VALUES
(4, '2016-08-25 09:52:28.415', 4, 'orderItemName', 'EUR', 'orderItemDescription', 'orderItemType', 'NOT_URGENT', 'NEW');
INSERT  INTO Media(ID, ORDER_ITEM_ID ) VALUES (4,4);

INSERT  INTO customer(id, first_name, last_name, telephone, email) VALUES (5, '', '','','');
INSERT  INTO CUSTOMER_MESSAGE(ID, MESSAGE) VALUES (5, '');
INSERT  INTO ORDERS(ID, CONTRACT, LICENSE_PLATE, START_DATE ,STATUS, CUST_ID, cust_message_id) VALUES
(5, 'contract-1', 'mockLicenseThird', '2015-08-25 09:52:36.415', 'NEW', 5, 5);
INSERT  INTO ORDER_ITEMS(ID, CREATION_TIME, ORDER_ID, ITEM_NAME, CURRENCY_CODE, DESCRIPTION, ITEM_TYPE, URGENCY, STATUS)  VALUES
(5, '2016-08-25 09:52:28.415', 5, 'orderItemName', 'EUR', 'orderItemDescription', 'orderItemType', 'NOT_URGENT', 'NEW');
INSERT  INTO Media(ID, ORDER_ITEM_ID ) VALUES (5,5);

INSERT  INTO customer(id, first_name, last_name, telephone, email) VALUES (6, '', '','','');
INSERT  INTO CUSTOMER_MESSAGE(ID, MESSAGE) VALUES (6, '');
INSERT  INTO ORDERS(ID, CONTRACT, LICENSE_PLATE, START_DATE ,STATUS, CUST_ID, cust_message_id) VALUES
(6, 'contract-2', 'mockLicenseForth', '2015-08-25 09:52:36.415', 'NEW', 6, 6);
INSERT  INTO ORDER_ITEMS(ID, CREATION_TIME, ORDER_ID, ITEM_NAME, CURRENCY_CODE, PRICE, DESCRIPTION, ITEM_TYPE, URGENCY, STATUS)  VALUES
(6, '2016-08-25 09:52:28.415', 6, 'orderItemName', 'EUR','', 'orderItemDescription', 'orderItemType', 'NOT_URGENT', 'VIEWED_BY_SERVICE_ADVISOR');
INSERT  INTO ORDER_ITEMS(ID, CREATION_TIME, ORDER_ID, ITEM_NAME, CURRENCY_CODE, PRICE, DESCRIPTION, ITEM_TYPE, URGENCY, STATUS)  VALUES
(7, '2016-08-25 10:02:28.415', 6, 'orderItemName2', 'EUR','', 'orderItemDescription2', 'orderItemType', 'NOT_URGENT', 'VIEWED_BY_SERVICE_ADVISOR');
INSERT  INTO Media(ID, ORDER_ITEM_ID ) VALUES (6,6);
INSERT  INTO Media(ID, ORDER_ITEM_ID ) VALUES (7,7);

/*

INSERT  INTO customer(id, first_name, last_name, telephone, email) VALUES (2, '', '','','');
INSERT  INTO ORDERS(ID, CONTRACT, LICENSE_PLATE, START_DATE, STATUS, CUST_ID) VALUES
(2, 'DEU21080V', 'mockLicense', '2016-08-25 09:52:28.415', 'NEW', 2);
INSERT  INTO ORDER_ITEMS(ID, CREATION_TIME, ORDER_ID, ITEM_NAME, CURRENCY_CODE, PRICE, DESCRIPTION, ITEM_TYPE, URGENCY, STATUS)  VALUES
(2, '2016-08-25 09:52:28.415', 2, 'orderItemName', 'EUR', '', 'orderItemDescription', 'orderItemType', 'NOT_URGENT', 'NEW');
INSERT  INTO Media(ID, ORDER_ITEM_ID ) VALUES (2,2);

*/

-- #### Action required - Notification icon ####
-- update ORDERS set STATUS = 'NEW';
-- update ORDER_ITEMS set STATUS = 'SUBMITTED_BY_CUSTOMER';



--update ORDERS set STATUS = 'VIEWED_BY_CUSTOMER'
--update ORDERS set STATUS = 'VIEWED_BY_SERVICE_ADVISOR'
--update ORDERS set STATUS = 'WAITING'
--update ORDERS set STATUS = 'COMPLETED'
