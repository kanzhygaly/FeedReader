/**
 * Author:  yerlana
 * Created: Apr 24, 2019
 */

CREATE TABLE IF NOT EXISTS feed_item (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   title varchar(255) not null,
   link varchar(255) not null,
   pub_date date,
   primary key(id)
);