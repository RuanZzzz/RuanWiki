# 电子书
drop table if exists `ebook`;
create table `ebook` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    `category_id1` bigint comment '分类1',
    `category_id2` bigint comment '分类2',
    `description` varchar(200) comment '描述',
    `cover` varchar(200) comment '封面',
    `doc_count` int comment '文档数',
    `view_count` int comment '阅读数',
    `vote_count` int comment '点赞数',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='电子书';

insert into `ebook` (id,name,description) values (1,'Spring Boot 入门教程','零基础入门Java开发,企业级应用开发最佳首选框架');
insert into `ebook` (id,name,description) values (2,'Vue 入门教程','零基础入门Vue开发,企业级应用开发最佳首选前端框架');
insert into `ebook` (id,name,description) values (3,'Laravel 入门教程','零基础入门Laravel开发,企业级应用开发最佳首选PHP框架');
insert into `ebook` (id,name,description) values (4,'Python 入门教程','零基础入门Python开发,企业级应用开发最佳首选框架');
insert into `ebook` (id,name,description) values (5,'MySQL 入门教程','零基础入门MySQL开发');