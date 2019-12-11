/*
INSERT INTO containerdata
( containerid,temperature,humidity, internaltime, globaltime) 
VALUES
(1,1,1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);*/
SELECT containerid, temperature, humidity, internaltime, globaltime
	FROM public.containerdata;