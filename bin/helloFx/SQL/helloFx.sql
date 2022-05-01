-- 创建本项目数据库
create database bookmanagersystem;
-- 创建关于用户账号密码的数据表
create table UserManager(
    userId int not null auto_increment,
    userName varchar(20) not null,
    userPwd varchar(20) not null,
    primary key ( userId )
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 创建初始账户
insert into UserManager
(userId,userName,userPwd)
values
("0","admin","12345789");
-- 创建用户位置数据表
create table location(
    city varchar(20)  primary key 
);
-- 增加初始几条城市信息
insert into location
(city)
values
("Beijing"),
("Ningbo"),
("Shanghai"),
("Tianjin");
-- 创建用户个人信息数据表
create table UserInfo(
    location varchar(20) not null,
    sex varchar(2) not null,
    userId int not null,
    primary key (userId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 创建图书种类数据表
create table bookSort(
    bookId varchar(20) not null unique,
    mainSort varchar(20) not null,
    subSort varchar(20) not null,
    primary key(bookId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 创建图书信息表
create table bookInfo(
    bookId varchar(20) not null unique,
    bookName varchar(20) not null,
    bookAuthor varchar(20) not null,
    bookNum int not null,
    primary key (bookId)    
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 增加初始图书信息
insert into bookInfo
(bookId,bookName,bookAuthor,bookNum)
values 
("b01","java核心技术","霍斯特奥",50),
("b02","名著-三国演义","罗贯中",40),
("b03","名著-水浒传","施耐庵",30),
("b04","名著-红楼梦","曹雪芹",20);
-- 增加图书分类
insert into bookSort
(bookId,mainSort,subSort)
values
("b01","工具类","软件编程"),
("b02","小说类","历史"),
("b03","小说类","历史"),
("b04","小说类","历史");