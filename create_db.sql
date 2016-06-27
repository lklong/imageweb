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
