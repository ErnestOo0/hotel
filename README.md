# hotel
tp2 architecture logiciels distribués

## hotel

### Compiler :

``` mvn clean package ```

### Executer l'hôtel par défaut sur le port 8080 :

``` mvn compile exec:exec ```

### Executer l'hotel de notre choix :

``` mvn compile exec:exec -Dhotel.port=[PORT] -Dhotel.nom="[nom]" -Dhotel.ville="[ville]" ```

Les hôtels enregistrés sont le royal, l'ibis et l'hôtel du parc.

## Agence

### Compiler :

Doit être lancé pendant qu'un hôtel tourne

``` mvn clean package ```

### Executer avec les 2 hôtels par défaut :

``` mvn compile exec:exec```

### Executer avec les hôtels de notre choix :

``` mvn compile exec:exec -Dagence.hotels="http://localhost:[PORT1]/hotel?wsdl http://localhost:[PORT2]/hotel?wsdl http://localhost:[PORT3]/hotel?wsdl ..." ```
