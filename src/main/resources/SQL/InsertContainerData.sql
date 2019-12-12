INSERT INTO containers(
	id, name)
	VALUES (1, 'prototype');

INSERT INTO containerdata
SELECT 1,22,56,(SELECT CURRENT_TIME),(SELECT CURRENT_TIME)
