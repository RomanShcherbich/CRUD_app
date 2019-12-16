DROP TABLE IF EXISTS containerData;
DROP TABLE IF EXISTS containers;

CREATE TABLE containers
(
    id   SERIAL,
    name VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE containerData
(
    containerId  integer NOT NULL,
    airTemp  integer NOT NULL,
    airHumidity     integer NOT NULL,
    internalTime timestamp,
    globalTime   timestamp,
    CONSTRAINT containerId FOREIGN KEY (containerId)
        REFERENCES containers (id) MATCH SIMPLE
        ON DELETE RESTRICT
);

INSERT INTO containers(id,
                       name)
VALUES (1, 'prototype');

INSERT INTO containerdata
(containerid, airTemp, airHumidity, internaltime, globaltime)
VALUES (1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

SELECT containerid, airTemp, airHumidity, internaltime, globaltime
FROM containerdata;