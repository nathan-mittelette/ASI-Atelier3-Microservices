FROM nginx:latest

COPY ./nginx.conf /etc/nginx/conf.d/default.conf

COPY ./templates/. /usr/share/nginx/html/.
COPY ./templates/css /usr/share/nginx/html/css
COPY ./templates/js /usr/share/nginx/html/js
COPY ./templates/lib /usr/share/nginx/html/lib

EXPOSE 80
