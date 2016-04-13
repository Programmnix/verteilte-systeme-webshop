# verteilte-systeme-webshop
This is a project for university cours of shared systems. 

# Projektstruktur

Die unten aufgeführte Projektstruktur beschreibt, welche Orderner für welchen Zweck da sind.

Ordner | Beschreibung
------------ | -------------
*eclipse/WebShop* | Eclipseprojekt zum importieren
*docker/nginx-loadbalancer* | HTTP-Loadbalancer Container basierend auf nginx
*docker/nginx-mysql-loadbalancer* | MYSQL-Loadbalancer Container  basierend auf nginx
*docker/webshop* | WebSop Container basierend auf Tomcat

# Software-Framwork

Die Software basiert auf dem Framework [Vaadin](http://www.vaadin.com), mit dem der WebShop geschrieben ist. Unten sind weitere Komponenten aufgeführt
* Vaadin
* JPA

# High-Availability

Um die HA zu erreichen wird folgende Software eingesetzt:
Software | Beschreibung
------------ | -------------
[Galera](http://galeracluster.com) | Mysql-Cluster
[mysql](https://www.mysql.de/) | Relationale Datenbank
[nginx](https://www.nginx.com/) | Loadbalancer für http und mysql
[tomcat](http://tomcat.apache.org/) | Java-Webserver
[redis](http://redis.io/) | InMemory-Databank für Sessionsharing


# How-To

Kleine Installationsanleitung des Webshops.

**Vorbedingungen**
* docker > 1.10

## Installation

Clonen und wechseln ins Verzeichnis.

```bash
git clone https://github.com/Programmnix/verteilte-systeme-webshop.git
cd verteilte-systeme-webshop/docker
```

Starten des HA-WebShops.

```bash
docker-compose up
```

Aufrufen des Webshops im Browser via *http://localhost* bzw. die IP der **docker-machine**

## Beenden eines Web-Servers
```bash
docker stop docker_webshop0_1
```
oder
```bash
docker stop docker_webshop1_1
```

## Beenden eines Mysql-Server
```bash
docker stop docker_master_1
```
oder
```bash
docker stop docker_node1_1
```
oder
```bash
docker stop docker_node2_1
```
