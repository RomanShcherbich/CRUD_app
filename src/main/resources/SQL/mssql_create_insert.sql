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
    temperature INTEGER NOT NULL,
    humidity INTEGER NOT NULL,
    internalTime DATETIME2,
    globalTime DATETIME2,
    FOREIGN KEY (containerId) REFERENCES containers(id)
)

INSERT INTO containers(
	 name)
	VALUES ( 'prototype');

INSERT INTO containerdata
SELECT 1,1,1,SYSDATETIME(),SYSDATETIME()

SELECT * FROM containerData