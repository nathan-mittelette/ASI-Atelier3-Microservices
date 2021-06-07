 docker run -d -p 6001:5432 --name=card-database -e POSTGRES_PASSWORD=root -e POSTGRES_DB=cards postgres
 docker run -d -p 6003:5432 --name=room-database -e POSTGRES_PASSWORD=root -e POSTGRES_DB=cards postgres
 docker run -d -p 6004:5432 --name=user-database -e POSTGRES_PASSWORD=root -e POSTGRES_DB=cards postgres