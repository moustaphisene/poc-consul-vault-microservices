
##Micro Services Architecture-Consul-Config- Gateway-Feign-Resilience4J
#1 Commencer par mettre en place consul
consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind=192.168.1.9

http://localhost:8500/ui/dc1/services

Consul is available on this utl https://www.consul.io/

Qu’est-ce qu’une registry de services ?
Une registry permet tout d’abord de faire le travaille d’un dns interne. 
C’est à dire que pour un service donné, un autre service va pouvoir interroger la registry pour savoir où joindre ce service 
(qu’il y ait une ou plusieurs instances) par son ip et son port.



#2 First you need to code config service.

Dans la class application du ms ajouter les annotations nécessaire
@EnableConfigServer

confiuger le serveur port du ms

#3 creer le dossier permettant de stocker l'ensemble des configurations des ms

Soit en repo local ou distant

#4 Informer les ms ou il doit aller chercher les config



