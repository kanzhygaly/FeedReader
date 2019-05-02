/**
 * Author:  yerlana
 * Created: Apr 30, 2019
 * Init testing data
 */

TRUNCATE TABLE feed_item; -- empty table

ALTER TABLE feed_item ALTER COLUMN id RESTART WITH 1; -- reset id auto_increment

INSERT INTO feed_item(author, title, link, pub_date) VALUES('yerlan', 'stackoverflow', 'https://stackoverflow.com', {ts '2012-09-17 18:47:52.69'});

INSERT INTO feed_item(author, title, link, pub_date) VALUES('yerlan', 'github', 'https://github.com', {ts '2012-09-17 19:47:52.69'});

INSERT INTO feed_item(author, title, link, pub_date) VALUES('yerlan', 'freecodecamp', 'https://medium.freecodecamp.org', {ts '2012-09-17 17:47:52.69'});

INSERT INTO feed_item(author, title, link, pub_date) VALUES('yerlan', 'baeldung', 'https://www.baeldung.com', {ts '2012-09-17 15:47:52.69'});

INSERT INTO feed_item(author, title, link, pub_date) VALUES('yerlan', 'gmail', 'https://mail.google.com', {ts '2012-09-17 16:47:52.69'});

INSERT INTO feed_item(author, title, link, pub_date) VALUES('yerlan', 'h2', 'http://www.h2database.com', {ts '2012-09-17 14:47:52.69'});

INSERT INTO feed_item(author, title, link, pub_date) VALUES('yerlan', 'youtube', 'https://www.youtube.com/', {ts '2012-09-17 13:47:52.69'});

INSERT INTO feed_item(author, title, link, pub_date) VALUES('yerlan', 'gtranslate', 'https://translate.google.com/', {ts '2012-09-17 12:47:52.69'});

INSERT INTO feed_item(author, title, link, pub_date) VALUES('yerlan', 'comptrain', 'https://comptrain.co', {ts '2012-09-17 11:47:52.69'});

INSERT INTO feed_item(author, title, link, pub_date) VALUES('yerlan', 'aerobiccapacity', 'http://aerobiccapacity.com/', {ts '2012-09-17 10:47:52.69'});