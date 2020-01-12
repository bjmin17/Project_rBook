-- 관리자 화면
CREATE TABLESPACE  BOOK_DB
DATAFILE '/bizwork/oracle/data/book.dbf'
SIZE 10M AUTOEXTEND ON NEXT 100K;

CREATE USER book IDENTIFIED BY book
DEFAULT TABLESPACE BOOK_DB;

GRANT DBA TO book;


-- 도서관리 화면입니다.

CREATE TABLE tbl_books(
    B_CODE	VARCHAR2(20)		PRIMARY KEY,
    B_NAME	nVARCHAR2(125)	NOT NULL	,
    B_AUTHER	nVARCHAR2(125)	NOT NULL,	
    B_COMP	nVARCHAR2(125)		,
    B_YEAR	VARCHAR2(10)		,
    B_IPRICE	NUMBER		
);

CREATE TABLE tbl_member(
    M_ID	VARCHAR2(20)	NOT NULL	PRIMARY KEY,
    M_PASSWORD	nVARCHAR2(125)	NOT NULL	,
    M_LOGIN_DATE	nVARCHAR2(10)		,
    M_REM	nVARCHAR2(125)		
);

CREATE TABLE tbl_read_book(
    RB_SEQ	NUMBER	NOT NULL	PRIMARY KEY,
    RB_BCODE	VARCHAR2(20)	NOT NULL	,
    RB_ID VARCHAR2(20) NOT NULL,
    RB_DATE	VARCHAR2(10)	NOT NULL	,
    RB_STIME	VARCHAR2(10)		,
    RB_RTIME	NUMBER(10,3)		,
    RB_SUBJECT	nVARCHAR2(20)		,
    RB_TEXT	nVARCHAR2(400)		,
    RB_STAR	NUMBER		
);
DROP TABLE tbl_read_book;
DROP TABLE bookuser;
CREATE SEQUENCE SEQ_READ_BOOK
START WITH 1 INCREMENT BY 1;

SELECT * FROM bookuser;
SELECT * FROM tbl_member;
SELECT * FROM tbl_books;
SELECT * FROM tbl_read_book;
ALTER TABLE tbl_read_book
ADD CONSTRAINT FK_READ_BOOK FOREIGN KEY (RB_BCODE)
REFERENCES tbl_books(b_code) on DELETE CASCADE;

ALTER TABLE tbl_read_book DROP CONSTRAINT FK_READ_BOOK;
SELECT * FROM tbl_read_book WHERE rb_seq = 3;
SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD') FROM DUAL;
INSERT INTO tbl_read_book(RB_SEQ, RB_BCODE, RB_DATE)
VALUES(seq_read_book,'978-89-7560754-7',(TO_CHAR(SYSDATE, 'YYYY-MM-DD')));