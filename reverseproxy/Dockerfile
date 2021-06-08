FROM nginx:alpine AS builder

# nginx:alpine contains NGINX_VERSION environment variable, like so:
# ENV NGINX_VERSION 1.15.0

# Our auth_request version
ENV auth_request_VERSION 1.5.4

# Download sources
RUN wget "http://nginx.org/download/nginx-${NGINX_VERSION}.tar.gz" -O nginx.tar.gz
RUN wget "https://github.com/nginx/nginx/blob/master/src/http/modules/ngx_http_auth_request_module.c"
RUN tar -zcvf auth_request.tar.gz ngx_http_auth_request_module.c

# For latest build deps, see https://github.com/nginxinc/docker-nginx/blob/master/mainline/alpine/Dockerfile
# RUN apk add --no-cache --virtual .build-deps \
#   gcc \
#   libc-dev \
#   make \
#   openssl-dev \
#   pcre-dev \
#   zlib-dev \
#   linux-headers \
#   curl \
#   gnupg \
#   libxslt-dev \
#   gd-dev \
#   geoip-dev

# Reuse same cli arguments as the nginx:alpine image used to build
RUN CONFARGS=$(nginx -V 2>&1 | sed -n -e 's/^.*arguments: //p')
RUN CONFARGS=${CONFARGS/-Os -fomit-frame-pointer/-Os}
RUN mkdir /usr/src
RUN tar -zxC /usr/src -f nginx.tar.gz
RUN tar -xzvf "auth_request.tar.gz"
RUN auth_requestDIR="$(pwd)/auth_request-${auth_request_VERSION}"
RUN ./usr/src/nginx-${NGINX_VERSION}/configure --with-compat $CONFARGS --add-dynamic-module=$auth_requestDIR
# RUN make modules
# RUN mv ./objs/*.so /


# FROM nginx:alpine
# # Extract the dynamic module auth_request from the builder image
# COPY --from=builder /usr/local/nginx/modules/ngx_http_auth_request_module.so /usr/local/nginx/modules/ngx_http_auth_request_module.so
# RUN rm /etc/nginx/conf.d/default.conf


# COPY nginx.conf /etc/nginx/nginx.conf
# # COPY default.conf /etc/nginx/conf.d/default.conf
# EXPOSE 80
# STOPSIGNAL SIGTERM
# CMD ["nginx", "-g", "daemon off;"]
