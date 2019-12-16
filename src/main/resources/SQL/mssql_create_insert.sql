DROP TABLE IF EXISTS containerData;
DROP TABLE IF EXISTS containers;

CREATE TABLE containers (
                            id INT IDENTITY,
                            name VARCHAR(20),
                            PRIMARY KEY (id)
);

CREATE TABLE containerData
(
    containerId INTEGER NOT NULL,
    airTemp INTEGER,
    airHumidity INTEGER,
    airCo2 INTEGER,
    airVentilation BIT,
    waterPh FLOAT,
    waterEc INTEGER,
    lightGrow BIT,
    lightSeed BIT,
    lightWork BIT,
    internalTime DATETIME2,
    globalTime DATETIME2,
    FOREIGN KEY (containerId) REFERENCES containers(id) ON DELETE NO ACTION
)

INSERT INTO containers(
	 name)
	VALUES ( 'prototype');

INSERT INTO containerdata
(containerId, airTemp, airHumidity, airCo2, airVentilation, waterPh, waterEc, lightGrow, lightSeed, lightWork, internalTime, globalTime)
VALUES
 (1,20,75,1200,1,6.2,900,1,0,1,SYSDATETIME(),SYSDATETIME())
,(1,21,75,1200,1,6.4,900,1,0,1,SYSDATETIME(),SYSDATETIME())
,(1,21,75,1200,1,'6.2',900,1,0,1,SYSDATETIME(),SYSDATETIME())
,(1,22,74,1200,1,'6.2',900,1,0,1,SYSDATETIME(),SYSDATETIME())
,(1,22,73,1200,1,'6.2',900,1,0,1,SYSDATETIME(),SYSDATETIME())