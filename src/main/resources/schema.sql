/**
 * Author:  yerlana
 * Created: Apr 24, 2019
 */

CREATE TABLE IF NOT EXISTS feed_item (
   id BIGINT(20) NOT NULL AUTO_INCREMENT,
   author VARCHAR(255) NOT NULL,
   title VARCHAR(255) NOT NULL,
   description CLOB,
   link VARCHAR(255) NOT NULL,
   pub_date TIMESTAMP NOT NULL,
   PRIMARY KEY(id)
);