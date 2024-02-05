create table notebooks 
( id serial primary key,
  firm CHARACTER VARYING(30),
  name CHARACTER VARYING(30),
  page INTEGER,
  cover_type INTEGER,
  country CHARACTER VARYING(30),
  page_type INTEGER
)