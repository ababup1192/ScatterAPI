# --- !Ups

create table cat ("NAME" VARCHAR NOT NULL PRIMARY KEY,"COLOR" VARCHAR NOT NULL);

# --- !Downs

drop table cat;