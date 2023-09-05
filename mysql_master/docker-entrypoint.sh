#!/bin/bash
set -e

/engn001/mysql/bin/mysqld --defaults-file=/engn001/mysql/my.cnf &
## create user
sleep 20

/engn001/mysql/bin/mysql -uroot -p'0000' -S /engn001/mysql/data/mysql.sock -e "create user 'replUser'@'172.16.0.%' identified with mysql_native_password"
/engn001/mysql/bin/mysql -uroot -p'0000' -S /engn001/mysql/data/mysql.sock -e "alter user 'replUser'@'172.16.0.%' identified by '0000'"
/engn001/mysql/bin/mysql -uroot -p'0000' -S /engn001/mysql/data/mysql.sock -e "grant replication slave on *.* to 'replUser'@'172.16.0.%'"

/engn001/mysql/bin/mysql -uroot -p'0000' -S /engn001/mysql/data/mysql.sock -e "flush privileges"
/bin/bash