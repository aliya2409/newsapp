ALTER SESSION SET "_ORACLE_SCRIPT"=true;
CREATE USER newsapp IDENTIFIED BY newsapp;
GRANT CONNECT TO newsapp;
GRANT CREATE SESSION TO newsapp;
GRANT CREATE TABLE  TO newsapp;
GRANT CREATE SEQUENCE TO newsapp;
GRANT UNLIMITED TABLESPACE TO newsapp;