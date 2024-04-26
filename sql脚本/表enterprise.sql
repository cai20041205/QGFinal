create table enterprise
(
    id                      int auto_increment
        primary key,
    enterpriseName          varchar(20) not null comment '企业名字',
    NumberOfEnterprise      int         not null comment '企业人数',
    EnterpriseWorkDirection varchar(30) not null comment '企业工作方向',
    state                   varchar(2)  not null comment '是否公开,0是不公开,1是公开',
    enterpriseFund          int         not null comment '企业资金',
    constraint enterprise_pk_2
        unique (enterpriseName)
);

INSERT INTO dp02.enterprise (id, enterpriseName, NumberOfEnterprise, EnterpriseWorkDirection, state, enterpriseFund) VALUES (1, '小米', 8, '做零食', '1', 312312413);
INSERT INTO dp02.enterprise (id, enterpriseName, NumberOfEnterprise, EnterpriseWorkDirection, state, enterpriseFund) VALUES (2, '华为', 12, '造车', '0', 234234234);
INSERT INTO dp02.enterprise (id, enterpriseName, NumberOfEnterprise, EnterpriseWorkDirection, state, enterpriseFund) VALUES (3, 'vivo', 13, '做手机', '1', 23232423);
