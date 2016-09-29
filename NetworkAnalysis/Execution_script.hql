add jar /home/edureka/SAIWS/batches/JNTU/Network/RowAVG.jar;

USE JNTU;

DROP FUNCTION if exists row_avg;
create function row_avg as 'com.jntu.RowAvg';

DROP VIEW CHURN_INFO;

CREATE VIEW IF NOT EXISTS CHURN_INFO
AS
SELECT A.id as ADDRESSID, A.LOCALITY,A.CITY,row_avg(AVG(NE1),AVG(NE2),AVG(NE3),AVG(NE4),AVG(NE5),AVG(NE6),AVG(NE7),AVG(NE8),AVG(NE9),AVG(NE10))  as ra
FROM NETWORK N  JOIN ADDRESS A
ON A.id=N.ADDRESSID
GROUP BY A.LOCALITY,A.CITY,A.id;


DROP VIEW IF EXISTS vbilling;
CREATE VIEW IF NOT EXISTS vbilling
AS
SELECT CUSTOMER_ID, AVG(AMOUNT) as amount,
CASE 
WHEN AVG(AMOUNT) <= 250 THEN 'LOW'
WHEN AVG(AMOUNT) > 250 AND AVG(AMOUNT) <=500 THEN 'MEDIUM'
WHEN AVG(AMOUNT) > 500 THEN 'HIGH'
END AS billGroup
 FROM BILLING GROUP BY CUSTOMER_ID;
 
#select billGroup,count(1) from vbilling group by billGroup;
 
 
INSERT OVERWRITE TABLE JNTU.NETWORK_ANALYSIS 
select "null",c.name, v.amount, locality,city,v.billGroup,
CASE   
WHEN ch.ra<=0.5 THEN 'CHURN'
WHEN ch.ra>0.5 AND ra <=0.60 THEN 'GOOD'
WHEN ch.ra>=0.60 THEN 'CONSUMED'
END,
ra,
FROM_UNIXTIME(UNIX_TIMESTAMP()),
FROM_UNIXTIME(UNIX_TIMESTAMP())
from churn_info ch  JOIN customer c
ON ch.addressid=c.address
JOIN vbilling v
ON v.customer_id=c.customer_id
ORDER BY v.amount desc
;