DROP TABLE IF EXISTS containerData;
DROP TABLE IF EXISTS containers;

CREATE TABLE containers (
	id SERIAL,
	name VARCHAR(20),		
	PRIMARY KEY (id)
);

CREATE TABLE containerData 
(
	containerId integer NOT NULL,
	temperature integer NOT NULL,
	humidity integer NOT NULL,
	internalTime time,	
    CONSTRAINT containerId FOREIGN KEY (containerId)
        REFERENCES containers (id) MATCH SIMPLE
        ON DELETE RESTRICT
)