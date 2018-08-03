-- 1 School
CREATE TABLE t_school (
	school_id NUMBER (5) NOT NULL,
	school_name VARCHAR2 (50) NOT NULL,
	school_province VARCHAR2 (10),
	school_city VARCHAR2 (20),
	school_create_time DATE,
	CONSTRAINT pk_school_id PRIMARY KEY (school_id)
);

CREATE SEQUENCE seq_school_id START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCYCLE NOCACHE;

insert into t_school
values(seq_school_id.nextval, '¹óÖÝ´óÑ§', '¹óÖÝ', '¹óÑô', to_date('2018-3-4 11:30:10','yyyy-mm-dd hh24:mi:ss'));
insert into t_school
values(seq_school_id.nextval, 'µç×Ó¿Æ¼¼´óÑ§', 'ËÄ´¨', '³É¶¼', to_date('2018-3-5 8:10:2','yyyy-mm-dd hh24:mi:ss'));
select*from t_school;
-- 2 Student
CREATE TABLE t_student (
	stu_name VARCHAR2 (20),
	stu_iden VARCHAR2 (20) NOT NULL,
	stu_school_id NUMBER (5) NOT NULL,
	stu_id VARCHAR2 (20),
	CONSTRAINT pk_stu_iden PRIMARY KEY (stu_iden),
	CONSTRAINT fk_stu_school_id FOREIGN KEY (stu_school_id) REFERENCES t_school (school_id)
);
insert into t_student values('ÐÌÌì','123456789112341234',2,'15008900161');
insert into t_student values('ÖÜºã','123456789112345678',2,'15008900782');
insert into t_student values('ÁÎ·É','123456789167895678',3,'15008901511');
select*from t_student;
-- 3 Location
CREATE TABLE t_location (
	loc_id NUMBER (7) NOT NULL,
	loc_school_id NUMBER (5) NOT NULL,
	loc_name VARCHAR2 (20),
	CONSTRAINT pk_loc_id PRIMARY KEY (loc_id),
	CONSTRAINT fk_loc_school_id FOREIGN KEY (loc_school_id) REFERENCES t_school (school_id)
);

CREATE SEQUENCE seq_loc_id START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCYCLE NOCACHE;

insert into t_location values(seq_loc_id.nextval,2,'¶«Â¥´óÌü');
insert into t_location values(seq_loc_id.nextval,2,'Î÷Â¥´óÌü');
insert into t_location values(seq_loc_id.nextval,3,'±±Â¥´óÌü');

select*from t_location ;
-- 4 Shelf
CREATE TABLE t_shelf (
	shelf_id NUMBER (5) NOT NULL,
	shelf_loc_id NUMBER (7) NOT NULL,
	shelf_create_time DATE,
	CONSTRAINT pk_shelf_id PRIMARY KEY (shelf_id),
	CONSTRAINT fk_shelf_loc_id FOREIGN KEY (shelf_loc_id) REFERENCES t_location (loc_id)
);
CREATE SEQUENCE seq_shelf_id START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCYCLE NOCACHE;
insert into t_shelf values(seq_shelf_id.nextval,2,to_date('2018-6-5 11:20:56','yyyy-mm-dd hh24:mi:ss'));
insert into t_shelf values(seq_shelf_id.nextval,3,to_date('2018-6-5 11:10:10','yyyy-mm-dd hh24:mi:ss'));
insert into t_shelf values(seq_shelf_id.nextval,4,to_date('2018-6-5 11:40:5','yyyy-mm-dd hh24:mi:ss'));
select * from t_shelf;
-- 5 USER
CREATE TABLE t_user (
	user_id NUMBER (7) NOT NULL,
	user_name VARCHAR2 (50) NOT NULL,
	user_pswd VARCHAR2 (20) NOT NULL,
	user_department_id NUMBER (2) NOT NULL,
	user_stu_iden VARCHAR2 (20),
	user_wallet NUMBER (6, 3),
	user_create_time DATE,
	CONSTRAINT pk_user_id PRIMARY KEY (user_id),
	CONSTRAINT fk_user_stu_iden FOREIGN KEY (user_stu_iden) REFERENCES t_student (stu_iden)
);

CREATE SEQUENCE seq_user_id START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCYCLE NOCACHE;
insert into t_user values(seq_user_id.nextval,'xingtian','123',0,'123456789112341234','100',to_date('2018-6-5 11:42:56','yyyy-mm-dd hh24:mi:ss'));
insert into t_user values(seq_user_id.nextval,'zhouheng','456',0,'123456789112341234','150',to_date('2018-6-5 11:42:56','yyyy-mm-dd hh24:mi:ss'));
insert into t_user values(seq_user_id.nextval,'yangxin','789',0,'123456789167895678','90',to_date('2018-6-5 11:42:56','yyyy-mm-dd hh24:mi:ss'));
insert into t_user values(seq_user_id.nextval,'liaofei','L123',1,'123456789112345678','80',to_date('2018-6-5 11:42:56','yyyy-mm-dd hh24:mi:ss'));
insert into t_user values(seq_user_id.nextval,'zhutian','Z123',1,'123456789167895678','100',to_date('2018-6-5 11:42:56','yyyy-mm-dd hh24:mi:ss'));

select*from t_user;
-- 6 Umbrella
CREATE TABLE t_umbrella (
	ubl_id NUMBER (7) NOT NULL,
	ubl_uid VARCHAR2 (8) NOT NULL,
	ubl_type VARCHAR2 (20),
	ubl_attr VARCHAR2 (20),
	ubl_color VARCHAR2 (20),
	ubl_shelf_id NUMBER (5),
	ubl_price_per_day NUMBER (6, 3) NOT NULL,
	ubl_create_time DATE,
	lent_situation NUMBER (2),
	lent_user_id NUMBER (7),
	lent_start_time DATE,
	lent_end_time DATE,
	CONSTRAINT pk_ubl_id PRIMARY KEY (ubl_id),
	CONSTRAINT fk_ubl_shelf_id FOREIGN KEY (ubl_shelf_id) REFERENCES t_shelf (shelf_id),
	CONSTRAINT fk_lent_user_id FOREIGN KEY (lent_user_id) REFERENCES t_user (user_id)
);

CREATE SEQUENCE seq_umbrella_id START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCYCLE NOCACHE;
insert into t_umbrella  values(seq_umbrella_id.nextval,'123','ÓêÉ¡','ÕÛµþÉ¡','ºìÉ«',2,3.2,to_date('2018-6-5 11:30:56','yyyy-mm-dd hh24:mi:ss'),2,2,to_date('2018-6-5 11:30:56','yyyy-mm-dd hh24:mi:ss'),sysdate);
insert into t_umbrella  values(seq_umbrella_id.nextval,'456','ÓêÉ¡','·ÇÕÛµþÉ¡','À¶É«',2,3.2,to_date('2018-6-5 11:30:10','yyyy-mm-dd hh24:mi:ss'),1,null,null,null);
insert into t_umbrella  values(seq_umbrella_id.nextval,'789','ÕÚÑôÉ¡','ÕÛµþÉ¡','ºÚÉ«',2,3.2,to_date('2018-6-5 11:20:20','yyyy-mm-dd hh24:mi:ss'),2,2,to_date('2018-6-5 11:30:56','yyyy-mm-dd hh24:mi:ss'),sysdate);
insert into t_umbrella  values(seq_umbrella_id.nextval,'147','ÓêÉ¡','·ÇÕÛµþÉ¡','×ÏÉ«',3,3.2,to_date('2018-6-5 11:10:20','yyyy-mm-dd hh24:mi:ss'),1,null,null,null);
insert into t_umbrella  values(seq_umbrella_id.nextval,'258','ÕÚÑôÉ¡','ÕÛµþÉ¡','°×É«',3,3.2,to_date('2018-6-5 11:15:56','yyyy-mm-dd hh24:mi:ss'),2,3,to_date('2018-6-5 11:30:56','yyyy-mm-dd hh24:mi:ss'),sysdate);
insert into t_umbrella  values(seq_umbrella_id.nextval,'369','ÓêÉ¡','·ÇÕÛµþÉ¡','»¨É«',3,3.2,to_date('2018-6-5 11:19:56','yyyy-mm-dd hh24:mi:ss'),1,null,null,null);
insert into t_umbrella  values(seq_umbrella_id.nextval,'741','ÕÚÑôÉ¡','ÕÛµþÉ¡','ºìÉ«',4,3.2,to_date('2018-6-5 11:12:56','yyyy-mm-dd hh24:mi:ss'),2,4,to_date('2018-6-5 11:30:56','yyyy-mm-dd hh24:mi:ss'),sysdate);
insert into t_umbrella  values(seq_umbrella_id.nextval,'852','ÓêÉ¡','ÕÛµþÉ¡','ÂÌÉ«',4,3.2,to_date('2018-6-5 11:13:56','yyyy-mm-dd hh24:mi:ss'),2,4,to_date('2018-6-5 11:30:56','yyyy-mm-dd hh24:mi:ss'),sysdate);
insert into t_umbrella  values(seq_umbrella_id.nextval,'963','ÓêÉ¡','·ÇÕÛµþÉ¡','ºìÉ«',4,3.2,to_date('2018-6-5 11:14:56','yyyy-mm-dd hh24:mi:ss'),2,4,to_date('2018-6-5 11:30:56','yyyy-mm-dd hh24:mi:ss'),sysdate);
select * from t_umbrella; 

COMMIT;