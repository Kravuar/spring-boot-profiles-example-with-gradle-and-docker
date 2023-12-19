Some notes:

Dev config:
uses profile-dev.gradle profile,
application-dev.yml spring profile
which leverages docker-compose-dev.yml via spring-boot-docker-compose starter
(it also removes the need for datasource configuration in properties as it automatically parses it from docker-compose.yml)

Prod config:
uses profile-prod.gradle profile,
which doesn't contain anything, e.g. docker-compose support that is needed only for dev, 
and it is used in the dev gradle profile, 
start using docker-compose -f docker-compose-full.yml up (which contains mysql and the app itself in the network
(build from Prod.Dockerfile that builds java app with prod gradle profile as stated earlier and prod spring boot profile running configuration)),
db params passed from docker-compose-full.yml to Prod.Dockerfile to env variables that are used within spring boot app.