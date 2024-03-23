create database databaseCache;
use databaseCache

CREATE TABLE WeatherReports (
        id INT IDENTITY(1,1),
    lat float,
    lon float,
    report NVARCHAR(MAX),
    CONSTRAINT PK_WeatherReport PRIMARY KEY (lat, lon)
);

CREATE TABLE AirReports (
        id INT IDENTITY(1,1),
    lat float,
    lon float,
    report NVARCHAR(MAX),
    CONSTRAINT PK_airReport PRIMARY KEY (lat, lon)
);

CREATE TABLE ForecastReports (
        id INT IDENTITY(1,1),
    lat float,
    lon float,
    report NVARCHAR(MAX),
    CONSTRAINT PK_ForeCast PRIMARY KEY (lat, lon)
);

CREATE TABLE Locations ( 
    id INT IDENTITY(1,1),
    cityName NVARCHAR(MAX),
    latitude float,
    longitude float,
    country NVARCHAR(MAX),
    state NVARCHAR(MAX),
        CONSTRAINT PK_locations PRIMARY KEY (latitude, longitude)

);


select * from WeatherReports;

SELECT * from airReports

SELECT * from ForecastReports

select * from locations

TRUNCATE table WeatherReports

TRUNCATE table ForecastREports

TRUNCATE table airReports

truncate table locations

drop table WeatherReports
drop TABLE airReports
drop TABLE forecastreports
drop TABLE locations

