# Users schema
 
# --- !Ups
 
CREATE TABLE Word (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

 
# --- !Downs
 
DROP TABLE Word;