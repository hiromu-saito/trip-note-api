FROM mysql:8
COPY my.cnf /etc/mysql/conf.d/my.cnf
COPY 01_init_db.sql /docker-entrypoint-initdb.d/01_init_db.sql