create table payment_message
(
    username      varchar(20)   not null comment '支付方',
    payee         varchar(20)   null comment '收款方',
    paymentAmount int           null comment '支付金额',
    id            int auto_increment
        primary key,
    creat_time    datetime      not null,
    updata_time   datetime      not null,
    state         int default 1 null comment '状态,0代表已被处理属于撤回,1代表未被处理,2代表已被处理属于以接收'
)
    comment '支付信息';

