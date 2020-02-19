## Progetto Programmazione ad Oggetti Feb2020
**Creato da:**
 - **Federico Joseph Panackal** *1083626*
 - **Matteo Toma** *1082053*
___ 
***Informazioni per l'avvio del progetto***
Per avviare il progetto potrebbero essere necessari 2 file jar.
JSON Simple 1.1.1: http://www.java2s.com/Code/JarDownload/json-simple/json-simple-1.1.1.jar.zip
Java JSON: http://www.java2s.com/Code/JarDownload/java-json/java-json.jar.zip

___
***Premessa***
 Questo progetto permette di eseguire certe operazioni su un file TSV scaricato dall'url contenuta in un JSON, tramite un applicazione Spring implementata in Java.
___
***Cosa fa questo programma?***
Il file in questione comprende i dati con le quantità di prodotti latticini in diverse nazioni.
L'applicazione una volta eseguita su Eclipse come App di Spring, permette di richiedere alcuni comandi, con eventuali filtri, tramite Postman.
  ___
  ***Contenuto del programma***
Il programma contiene 3 package principali:
**1**. progetto_febbraio2020
**2**. progetto_febbraio2020.models
**3**. progetto_febbraio2020.services
 
 Il **primo** package contiene i 2 controller e la classe principale del progetto.
 Il **secondo** package contiene il file DairyStatus.java che è la classe che rappresenta il modello dairy con il costruttore, i parametri del modello e i metodi *set*, *get* e *tostring*.
 Il **Terzo** package contiene i file necessari per il filtraggio dei dati, il TSV Reader e il TSV Downloader.
 ___
 ***Richieste del programma***
Le richieste GET implementate sono:
 - */dairy* : ritorna l'intero dataset del TSV
 - */metadata* : ritorna la lista degli attributi
 - */stats* : ritorna le statistiche
 - */dairy/stats?nameStats="field/year"*
 -    */get?filters="nomecampo":"anno":"operatore":"valore":"operator":...*  : Ritorna i dati filtrati a seconda dei filtri scelti
-   */stats?nameStats="field/year"&filter="nomecampo":"anno":"operatore":"valore":"operator":...*  ritorna le statistiche a seconda dei filtri scelti
___
***Filtri***
-   **"anno"**  inserimento dell'anno
 -   **"nomecampo"**  campo da analizzare
-    **"valore"**  si intende il riferimento per filtrare i dati
-   **"operatore"**   `>,<,==,>=,<=`
-   **"operator"**  permette di utilizzare più filtri con "AND" o "OR"
___
***Statistiche***
-   Minimo
-   Massimo
-   Media
-   Somma
-   Deviazione standard
-   Conteggio occorrenze uniche
-   Conteggio elementi totali
___
**GET Dairy**
![Get Dairy](https://i.imgur.com/oHunutt.png)
___
**GET Metadata**
![GET Metadata](https://imgur.com/K5NpTYU.png)
___
**GET Stats**
![enter image description here](https://imgur.com/qIA6K1H.png)
___
**Diagramma delle classi**
![Diagramma delle classi](https://i.imgur.com/K8ixPsI.png)
___
***Diagramma dei casi d'uso***
![Diagramma dei casi d'uso](https://imgur.com/CEydlYF.png)
___
**Diagramma delle sequenze per i filtri**
![Diagramma delle sequenze per i filtri](https://imgur.com/1BRIP3l.png)
___
**Diagramma delle sequenze dei metadati**

![Diagramma delle sequenze dei metadati](https://i.imgur.com/LYWvBYk.png)
___
**Diagramma delle sequenze dei Dairy**
![Diagramma delle sequenze dei Dairy](https://imgur.com/7qZbjeU.png)
___
**Diagramma delle sequenze delle statistiche**
![Diagramma delle sequenze delle statistiche](https://imgur.com/2lMCdLx.png)
