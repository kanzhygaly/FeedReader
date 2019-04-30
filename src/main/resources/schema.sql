/**
 * Author:  yerlana
 * Created: Apr 24, 2019
 */

CREATE TABLE IF NOT EXISTS feed_item (
   id BIGINT(20) NOT NULL AUTO_INCREMENT,
   title VARCHAR(255) NOT NULL,
   link varchar(255) NOT NULL,
   pub_date TIMESTAMP,
   primary key(id)
);