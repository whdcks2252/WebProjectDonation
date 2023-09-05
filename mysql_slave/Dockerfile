FROM mysql_image:slave
MAINTAINER chooh1010

USER root
RUN echo "slave" > slave.txt

ADD my.cnf /engn001/mysql/
ADD docker-entrypoint.sh /usr/local/bin
RUN chmod 640 /engn001/mysql/my.cnf
RUN chmod 755 /usr/local/bin/docker-entrypoint.sh

ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]