FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=cat1234!#
ENV MYSQL_DATABASE=cat_db
COPY schema.sql /docker-entrypoint-initdb.d/

EXPOSE 3306