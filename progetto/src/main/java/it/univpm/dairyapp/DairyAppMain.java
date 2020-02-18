package it.univpm.dairyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univpm.dairyapp.servizi.Downloader;
import it.univpm.dairyapp.servizi.MetaData;
import it.univpm.dairyapp.servizi.LettoreTSV;

import java.nio.file.Files;
import java.nio.file.Paths;

// TODO: Auto-generated Javadoc
/**
 * The Class ProgettoFebbraio2020Application.
 * @author Matteo e Federico
 */

@SpringBootApplication
public class DairyAppMain {

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DairyAppMain.class, args);
        String filename = "dairydata.tsv";
        String url = "http://ec.europa.eu/eurostat/estat-navtree-portlet-prod/BulkDownloadListing?file=data/apro_mk_colm.tsv.gz&unzip=true";

        
        if (Files.exists(Paths.get(filename))) { //Controlla se il file esiste già, altrimenti lo scarica
            System.out.println("File già presente nella directory.");
        } else {
            Downloader.download(url, filename);
        }

        LettoreTSV parser = new LettoreTSV(filename); //parser del file tsv
        parser.parsing();
        MetaData metadata = new MetaData(); //crea la mappa per i metadati

    }
}