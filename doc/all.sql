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
    `record_id` varchar(50) comment '作者id',
    `record_name` varchar(200) comment '作者名称',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='电子书';

insert into `ebook` (id,name,description) values (1,'Spring Boot 入门教程','零基础入门Java开发,企业级应用开发最佳首选框架');
insert into `ebook` (id,name,description) values (2,'Vue 入门教程','零基础入门Vue开发,企业级应用开发最佳首选前端框架');
insert into `ebook` (id,name,description) values (3,'Laravel 入门教程','零基础入门Laravel开发,企业级应用开发最佳首选PHP框架');
insert into `ebook` (id,name,description) values (4,'Python 入门教程','零基础入门Python开发,企业级应用开发最佳首选框架');
insert into `ebook` (id,name,description) values (5,'MySQL 入门教程','零基础入门MySQL开发');

# 分类
drop table if exists `category`;
create table `category` (
  `id` bigint not null comment 'id',
  `parent` bigint not null default 0 comment '父id',
  `name` varchar(50) not null comment '名称',
  `sort` int comment '顺序',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='分类';

insert into `category` (id, parent, name, sort) values (100, 000, '前端开发', 100);
insert into `category` (id, parent, name, sort) values (101, 100, 'Vue', 101);
insert into `category` (id, parent, name, sort) values (102, 100, 'HTML & CSS', 102);
insert into `category` (id, parent, name, sort) values (200, 000, 'Java', 200);
insert into `category` (id, parent, name, sort) values (201, 200, '基础应用', 201);
insert into `category` (id, parent, name, sort) values (202, 200, '框架应用', 202);
insert into `category` (id, parent, name, sort) values (300, 000, 'Python', 300);
insert into `category` (id, parent, name, sort) values (301, 300, '基础应用', 301);
insert into `category` (id, parent, name, sort) values (302, 300, '进阶方向应用', 302);
insert into `category` (id, parent, name, sort) values (400, 000, '数据库', 400);
insert into `category` (id, parent, name, sort) values (401, 400, 'MySQL', 401);
insert into `category` (id, parent, name, sort) values (500, 000, '其它', 500);
insert into `category` (id, parent, name, sort) values (501, 500, '服务器', 501);
insert into `category` (id, parent, name, sort) values (502, 500, '开发工具', 502);
insert into `category` (id, parent, name, sort) values (503, 500, '热门服务端语言', 503);


-- 文档表
drop table if exists `doc`;
create table `doc` (
      `id` bigint not null comment 'id',
      `ebook_id` bigint not null default 0 comment '电子书id',
      `parent` bigint not null default 0 comment '父id',
      `name` varchar(50) not null comment '名称',
      `sort` int comment '顺序',
      `view_count` int default 0 comment '阅读数',
      `vote_count` int default 0 comment '点赞数',
      primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='文档';

insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (1, 2663002662899712, 0, '文档1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (2, 2663002662899712, 1, '文档1.1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (3, 2663002662899712, 0, '文档2', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (4, 2663002662899712, 3, '文档2.1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (5, 2663002662899712, 3, '文档2.2', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (6, 2663002662899712, 5, '文档2.2.1', 1, 0, 0);

-- 文档内容
drop table if exists `content`;
create table `content` (
  `id` bigint not null comment '文档id',
  `content` mediumtext not null comment '内容',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='文档内容';

insert into `user` (id,login_name,name,password) values (1, 'ruanshaoxiang', '软烧香', '7354a1d413535a6c0dc5c209e198d799');
insert into `user` (id,login_name,name,password) values (2, 'chihu', '赤狐', 'e10adc3949ba59abbe56e057f20f883e');
insert into `user` (id,login_name,name,password) values (3, 'aguang', '阿光', 'e10adc3949ba59abbe56e057f20f883e');

-- 用户表
drop table if exists `user`;
create table `user` (
    `id` bigint not null comment 'ID',
    `login_name` varchar(50) not null comment '登录名',
    `name` varchar(50) comment '昵称',
    `password` char(32) not null comment '密码',
    primary key (`id`),
    unique key `login_name_unique` (`login_name`)
) engine=innodb default charset=utf8mb4 comment='用户';

-- 操作记录表
drop table if exists `record`;
create table `record` (
    `record_id` varchar(200) not null comment '用户id',
    `type` int not null comment '类型：1-新增、2-删除、3-更新',
    `desc` varchar(200) not null comment '动作描述',
    `record_time` varchar(200) not null comment '记录时间',
    `opera_url` varchar(200) not null comment '操作路由',
    `method_name` varchar(50) not null comment '请求方法',
    `service_name` varchar(50) not null
) engine=innodb default charset=utf8mb4 comment='操作记录';

-- 用户token表
drop table if exists `user_token`;
create table `user_token` (
    `id` int(11) not null auto_increment,
    `user_id` varchar(255) not null default '' comment '用户id',
    `access_token` varchar(255) not null default '' comment '授权码',
    `expired_at` varchar(10) not null default '0' comment '过期时间',
    `created_at` varchar(10) not null default '0' comment '创建时间',
    `updated_at` varchar(10) not null default '0' comment '更新时间',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='用户token';