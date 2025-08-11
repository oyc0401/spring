#Document
https://test.rokafmail.kr/swagger-ui/index.html

#Command
quit;

#Local
./gradlew bootRun

mysql -u root -p

brew services start mysql   


docker run --env-file .env -p 8080:8080 spring-ubuntu-app

# EC2
java -jar demo-0.0.1-SNAPSHOT.jar

mysql -h injob-db.c3qasa8yqxzu.ap-northeast-2.rds.amazonaws.com -u admin -p
USE testdb;
DESCRIBE users;

injob-db.c3qasa8yqxzu.ap-northeast-2.rds.amazonaws.com



# flyway 플래그 지우기
docker run --rm \
-v $(pwd)/src/main/resources/db/migration:/flyway/sql \
flyway/flyway \
-url="jdbc:mysql://injob-db.c3qasa8yqxzu.ap-northeast-2.rds.amazonaws.com:3306/service?useSSL=true&requireSSL=true" \
-user=admin \
-password=<pw> \
repair