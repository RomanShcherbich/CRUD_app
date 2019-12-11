DROP TABLE IF EXISTS Weather;

CREATE TABLE Weather 
(
	container_id integer NOT NULL,
	temperature integer NOT NULL,
	humidity integer NOT NULL,
	pressure integer NOT NULL,
	created_on time,	
	internal_time time,	
    CONSTRAINT container_id FOREIGN KEY (container_id)
        REFERENCES containers (id) MATCH SIMPLE
        ON DELETE RESTRICT
)