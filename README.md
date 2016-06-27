# imageweb
java图片，文件上传，欢迎指正，im4java + graphicimage，
1.能处理cmyk,ycck,adobemarker,rgb模式的图片
2.将MulitPartFile转换成File
3.项目架构 springmv+mybatis+spring+jsp
4.将继续增加接口调用，http调用 
建表语句
    create database filedb;
    use database filedb;
    create table zhigufile (id BIGINT primary key not null auto_increment,
    userID INTEGER,
    createTime TIMESTAMP,
    fileType BIT,
    uri VARCHAR(100),
    realPath VARCHAR(100),
    imageWidth INTEGER,
    imageHeight INTEGER,
    size BIGINT,
    specs VARCHAR(100),
    deleteFime TIMESTAMP,
    deleteFlag BIT);
