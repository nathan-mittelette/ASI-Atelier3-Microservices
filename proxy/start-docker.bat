!#bin/sh

docker build . -t reverse-proxy
docker run -p 80:80 reverse-proxy
