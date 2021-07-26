CREATE TABLE IF NOT EXISTS users (
  user_id       int          PRIMARY KEY        COMMENT 'ユーザーID',
  mail_address VARCHAR(30)   NOT NULL UNIQUE    COMMENT 'メールアドレス',
  password     VARCHAR(100)   NOT NULL           COMMENT 'パスワード',
  token        VARCHAR(10)                      COMMENT 'トークン',
  delete_flag   int          DEFAULT 0 NOT NULL COMMENT '削除フラグ:0:有効,1:無効'
);

CREATE TABLE IF NOT EXISTS memories (
  user_id             int                                 COMMENT 'ユーザID',
  id                  int                                 COMMENT '思い出ID',
  hotel_name          VARCHAR(30)     NOT NULL            COMMENT 'ホテル名称',
  hotel_image         VARCHAR(100)    NOT NULL            COMMENT 'ホテル写真',
  impression          VARCHAR(30)                         COMMENT '感想',
  accommodation_date  DATE            NOT NULL            COMMENT '宿泊日',
  delete_flag         int             DEFAULT 0 NOT NULL  COMMENT '削除フラグ:0:有効,1:無効',
  PRIMARY KEY (user_id, id)
);