# Ec2에서 mysql 접속
mysql -h injob-db.c3qasa8yqxzu.ap-northeast-2.rds.amazonaws.com -u admin -p
USE service;
DESCRIBE users;
quit;


SELECT * FROM users;

UPDATE auth SET user_role = 'ROLE_ADMIN' WHERE user_id = 3;