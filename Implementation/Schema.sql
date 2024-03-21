create database databaseCache;
use databaseCache

CREATE TABLE WeatherReports (
        id INT IDENTITY(1,1),
    lat DECIMAL(8, 6),
    lon DECIMAL(9, 6),
    report NVARCHAR(MAX),
    CONSTRAINT PK_WeatherReport PRIMARY KEY (lat, lon)
);

CREATE TABLE AirReports (
        id INT IDENTITY(1,1),
    lat DECIMAL(8, 6),
    lon DECIMAL(9, 6),
    report NVARCHAR(MAX),
    CONSTRAINT PK_airReport PRIMARY KEY (lat, lon)
);

CREATE TABLE ForecastReports (
        id INT IDENTITY(1,1),
    lat DECIMAL(8, 6),
    lon DECIMAL(9, 6),
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


select * from WeatherReport;

SELECT * from airReport

SELECT * from forecast

select * from locations

drop table WeatherReport
drop TABLE airReport
drop TABLE forecast
drop TABLE locations

