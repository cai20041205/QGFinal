create table applydata
(
    username    varchar(20) not null comment '申请人',
    id          int auto_increment
        primary key,
    creat_time  datetime    null,
    update_time datetime    null,
    enterprise  varchar(20) not null comment '企业名',
    constraint applyData_pk_2
        unique (username)
)
    comment '申请成为企业群负责人信息';

INSERT INTO dp02.applydata (username, id, creat_time, update_time, enterprise) VALUES ('17676714993', 6, '2024-04-22 19:47:58', '2024-04-22 19:47:58', 'vivo');
