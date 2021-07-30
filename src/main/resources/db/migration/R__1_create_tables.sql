CREATE TABLE IF NOT EXISTS users (
  user_id       int          PRIMARY KEY        COMMENT 'ユーザーID',
  mail_address VARCHAR(30)   NOT NULL UNIQUE    COMMENT 'メールアドレス',
  password     VARCHAR(30)   NOT NULL           COMMENT 'パスワード',
  token        VARCHAR(10)                      COMMENT 'トークン',
  delete_flag   int          DEFAULT 0 NOT NULL COMMENT '削除フラグ:0:有効,1:無効'
);

create table if not exists `trip-note`.`memories` (
  id int primary key comment '思い出ID'
  , user_id int not null comment 'ユーザID'
  , hotel_name varchar(30) not null comment 'ホテル名称'
  , hotel_image varchar(100) not null comment 'ホテル写真'
  , impression varchar(30) comment '感想'
  , accommodation_date date not null comment '宿泊日'
  , delete_flag int default 0 not null comment '削除フラグ:0:有効,1:無効'
)