create table user
(
    id                int auto_increment
        primary key,
    username          varchar(20)                  null comment '账号',
    password          varchar(20)                  null comment '密码',
    creat_time        datetime                     null,
    updata_time       datetime                     null,
    identity          varchar(2)                   not null comment '''1''代表普通用户,''2''代表企业群管理人,''3''代表网站管理人',
    name              varchar(10)                  null comment '昵称',
    enterprise        varchar(20) default '无'     not null comment '企业群组',
    location          varchar(20)                  null comment '地址',
    personalFund      int                          not null comment '个人资金',
    useEnterpriseFund int         default 0        not null comment '使用企业资金权限,1表示可以,0表示不可以',
    paymentPassword   varchar(6)  default '041205' not null comment '支付密码',
    bannedState       int         default 1        null comment '是否封禁,0是已封禁,1是正常',
    constraint user_pk_2
        unique (username)
)
    comment '用户信息';

INSERT INTO dp02.user (id, username, password, creat_time, updata_time, identity, name, enterprise, location, personalFund, useEnterpriseFund, paymentPassword, bannedState) VALUES (16, '13692362912', '20041205cai', '2024-04-15 22:57:12', '2024-04-15 22:57:12', '3', '阿华', '无', null, 0, 0, '041205', 1);
INSERT INTO dp02.user (id, username, password, creat_time, updata_time, identity, name, enterprise, location, personalFund, useEnterpriseFund, paymentPassword, bannedState) VALUES (17, '17822735689', '20041205cai', '2024-04-16 12:56:47', '2024-04-16 12:56:47', '2', '小蔡', '小米', '广西', 11447, 1, '123456', 1);
INSERT INTO dp02.user (id, username, password, creat_time, updata_time, identity, name, enterprise, location, personalFund, useEnterpriseFund, paymentPassword, bannedState) VALUES (18, '15017370675', '20041205cai', '2024-04-20 21:45:25', '2024-04-20 21:45:27', '1', '小牛', '小米', '广东', 908, 1, '041205', 1);
INSERT INTO dp02.user (id, username, password, creat_time, updata_time, identity, name, enterprise, location, personalFund, useEnterpriseFund, paymentPassword, bannedState) VALUES (20, '17676714993', '20041205cai', '2024-04-22 19:08:13', '2024-04-22 19:08:13', '1', '大蔡', 'vivo', '北京', 3123, 0, '041205', 1);
INSERT INTO dp02.user (id, username, password, creat_time, updata_time, identity, name, enterprise, location, personalFund, useEnterpriseFund, paymentPassword, bannedState) VALUES (21, '17006819827', '5151sfesf', '2024-04-22 23:29:00', '2024-04-22 23:29:01', '1', '阿瓦达', '小米', '广东', 232, 1, '041205', 1);
INSERT INTO dp02.user (id, username, password, creat_time, updata_time, identity, name, enterprise, location, personalFund, useEnterpriseFund, paymentPassword, bannedState) VALUES (22, '17156532454', 'werwer123', '2024-04-22 23:29:04', '2024-04-22 23:29:05', '1', '阿明', '小米', '上海', 123, 0, '412057', 1);
INSERT INTO dp02.user (id, username, password, creat_time, updata_time, identity, name, enterprise, location, personalFund, useEnterpriseFund, paymentPassword, bannedState) VALUES (23, '18954621315', 'dgewrewter2', '2024-04-22 23:29:07', '2024-04-22 23:29:07', '1', '阿红', '小米', '江西', 231, 1, '821323', 1);
INSERT INTO dp02.user (id, username, password, creat_time, updata_time, identity, name, enterprise, location, personalFund, useEnterpriseFund, paymentPassword, bannedState) VALUES (24, '15684831215', '3dgrdw123', '2024-04-22 23:29:03', '2024-04-22 23:29:10', '1', '李明', '小米', '广东', 53, 0, '412054', 1);
INSERT INTO dp02.user (id, username, password, creat_time, updata_time, identity, name, enterprise, location, personalFund, useEnterpriseFund, paymentPassword, bannedState) VALUES (25, '16545154645', 'fghfhtr213', '2024-04-22 23:29:06', '2024-04-22 23:29:09', '1', '李飞', '小米', '广东', 234, 0, '412051', 1);
INSERT INTO dp02.user (id, username, password, creat_time, updata_time, identity, name, enterprise, location, personalFund, useEnterpriseFund, paymentPassword, bannedState) VALUES (26, '13645454845', 'dfgdfg123', '2024-04-22 23:29:03', '2024-04-22 23:29:09', '1', '子聪', '小米', '广东', 435, 1, '412055', 1);
