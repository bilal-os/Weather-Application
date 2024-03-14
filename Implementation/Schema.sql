create database databaseCache;
use databaseCache

CREATE TABLE WeatherReport (
        id INT IDENTITY,
    lat DECIMAL(8, 6),
    lon DECIMAL(9, 6),
    report NVARCHAR(MAX),
    CONSTRAINT PK_WeatherReport PRIMARY KEY (lat, lon)
);



CREATE TABLE airReport (
        id INT IDENTITY,
    lat DECIMAL(8, 6),
    lon DECIMAL(9, 6),
    report NVARCHAR(MAX),
    CONSTRAINT PK_airReport PRIMARY KEY (lat, lon)
);

CREATE TABLE forecast (
        id INT IDENTITY,
    lat DECIMAL(8, 6),
    lon DECIMAL(9, 6),
    report NVARCHAR(MAX),
    CONSTRAINT PK_ForeCast PRIMARY KEY (lat, lon)
);



select * from WeatherReport;

SELECT * from airReport

SELECT * from forecast