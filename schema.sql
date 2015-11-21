CREATE TABLE BOOK(
Book_id     varchar(10), 
Title		varchar(255) not null,
CONSTRAINT pk_book primary key (Book_id));
LOAD DATA LOCAL INFILE "C:/Users/srinath/Desktop/DB_Proj/new_csv/book.csv" INTO TABLE library2.book;

CREATE  TABLE  BOOK_AUTHORS(
Book_id		varchar(10),
Author_name varchar(255),
CONSTRAINT pk_book_authors primary key (Book_id,Author_name),
CONSTRAINT fk_BOOK_AUTHORS_BOOK foreign key(Book_id) references BOOK(Book_id) on update cascade on delete 
cascade);
alter table book_authors add column fname varchar(25);
alter table book_authors add column minit varchar(15);
alter table book_authors add column lname varchar(25);
alter table book_authors alter column minit set default null;
LOAD DATA LOCAL INFILE "C:/Users/srinath/Desktop/DB_Proj/new_csv/book_authors.csv" INTO TABLE library2.book_authors;
CREATE TABLE LIBRARY_BRANCH(
Branch_id		int(1),
Branch_name		varchar(255),
Address			varchar(255),
CONSTRAINT pk_library_branch primary key(Branch_id));
LOAD DATA LOCAL INFILE "C:/Users/srinath/Desktop/DB_Proj/new_csv/library_branch.csv" INTO TABLE library2.library_branch;
CREATE TABLE BOOK_COPIES(
Book_id		varchar(10),
Branch_id	int(1),
No_of_copies varchar(1),
CONSTRAINT pk_book_copies primary key(Book_id,Branch_id));
LOAD DATA LOCAL INFILE "C:/Users/srinath/Desktop/DB_Proj/new_csv/book_copies.csv" INTO TABLE library2.book_copies;
CREATE TABLE BORROWER(
Card_no int(4) auto_increment,
Fname		varchar(50) not null,
Lname		varchar(50) not null,
Address		varchar(50) not null,
Phone		varchar(15),
CONSTRAINT pk_borrower primary key(Card_no),
unique (Fname,Lname,Address));
LOAD DATA LOCAL INFILE "C:/Users/srinath/Desktop/DB_Proj/new_csv/borrower.csv" INTO TABLE library2.borrower;
CREATE TABLE BOOK_LOANS(
Loan_id		int PRIMARY KEY auto_increment,
Book_id		varchar(10),
Branch_id	int(1),
Card_no		int(4),
Date_out	date not null,
Due_Date	date,
Date_in		date default null,
CONSTRAINT pk_book_loans primary key(Loan_id) 
#--CONSTRAINT foreign key(Book_id) references BOOK(Book_id) on update cascade on delete 
#--cascade,
#--CONSTRAINT key(Branch_id) references LIBRARY_BRANCH(Branch_id) on update cascade on delete 
#--cascade,
#--ADD CONSTRAINT foreign key(Card_no) references BORROWER(Card_no) on update cascade on delete 
#--cascade
);

Create table FINES
(Loan_id int, 
Fine_amt decimal(4,2), 
Paid boolean ,
CONSTRAINT pk_Fines primary key(Loan_id),
CONSTRAINT fk_Fines foreign key (Loan_id) references BOOK_LOANS(Loan_id) on update cascade on 
delete cascade);
alter table BOOK_COPIES ADD CONSTRAINT foreign key BOOK_COPIES(Book_id) references BOOK(Book_id) on update 
cascade on delete cascade;
alter table BOOK_COPIES ADD CONSTRAINT  foreign key BOOK_COPIES(Branch_id) references LIBRARY_BRANCH(Branch_id) on update 
cascade on delete cascade;
#--LOAD DATA LOCAL INFILE "C:/Users/srinath/Desktop/DB_Proj/new_csv/book_loans.csv" INTO TABLE library2.book_loans;
ALTER TABLE BOOK_LOANS ADD CONSTRAINT foreign key(Book_id) references BOOK(Book_id) on update cascade on delete 
cascade;
ALTER TABLE BOOK_LOANS ADD CONSTRAINT foreign key(Branch_id) references LIBRARY_BRANCH(Branch_id) on update cascade on delete 
cascade;
ALTER TABLE BOOK_LOANS ADD CONSTRAINT foreign key(Card_no) references BORROWER(Card_no) on update cascade on delete 
cascade;