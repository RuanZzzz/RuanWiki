# RuanWiki

# 功能点
后续会持续更新功能点和改造成我喜欢的样子

# 1. 用户管理（TODO）
①、完善用户体系 

②、增加用户的头像上传 

③、完善拦截器存储用户信息、校验token

④、新增接口返回用户信息

# 2. 电子书管理（TODO）

## 2.1. 电子书
①、~~电子书封面图片上传~~——（完成）

②、电子书表还需要一个用户id，用来绑定是哪个用户创建的电子书

## 2.2. 文档及内容
①、完成富文本的插入图片和视频

②、新增【点赞_用户】表

③、删除电子书时，将相关的文档和内容一并删除；删除文档时，将相关的内容一并删除

④、文档删除，需要加一个path，用于后续多级别的删除，可以把子集也删除（与分类一样）

# 3. 分类管理（TODO）
①、分类管理数据表，需要加一个path，用于后续多级别的删除，可以把子集也删除

# 4. 其他（TODO）
①、新增VO和PARAM层

②、完善项目代码和返回体

③、新增编辑功能分开实现

④、将管理员的管理界面和用户界面分开，重新设计用户界面，现有的界面用于管理端（管理端可以是现在这样，但是前端用户端可以参考laravel）

⑤、websocket：谁的文章被点赞就推送给谁

⑥、使用AOP记录操作过程

⑦、创建redis工具类

......