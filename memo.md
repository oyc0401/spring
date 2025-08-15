#Document
https://test.rokafmail.kr/swagger-ui/index.html

# java 실행
./gradlew bootRun

# java 빌드된거 실행
java -jar demo-0.0.1-SNAPSHOT.jar

# 로컬에서 도커 실행
docker run --env-file .env -p 8080:8080 spring-ubuntu-app

# 로컬에서 mysql 실행
brew services start mysql

# 로컬에서 mysql 접속
mysql -u root -p

# Ec2에서 mysql 접속
mysql -h injob-db.c3qasa8yqxzu.ap-northeast-2.rds.amazonaws.com -u admin -p
USE testdb;
DESCRIBE users;
quit;

# 로컬 체크썸 맞추기
./gradlew flywayRepair \
-Dflyway.url=jdbc:mysql://localhost:3306/testdb \
-Dflyway.user=root \
-Dflyway.password=chan0401 \
-Dflyway.locations=filesystem:src/main/resources/db/migration

# Ec2에서 flyway 플래그 지우기
cd /home/ubuntu/spring && \
docker run --rm \
-v $(pwd)/src/main/resources/db/migration:/flyway/sql \
flyway/flyway \
-url="jdbc:mysql://injob-db.c3qasa8yqxzu.ap-northeast-2.rds.amazonaws.com:3306/service" \
-user=admin \
-password=<pw> \
repair

# html 만들기
java -jar schemaspy-6.2.4.jar \
-t mysql \
-host 127.0.0.1 \
-port 3306 \
-db testdb \
-s testdb \
-u root \
-p chan0401 \
-o ./output \
-dp ./mysql-connector-j-9.4.0.jar
