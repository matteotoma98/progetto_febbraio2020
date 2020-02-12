package it.univpm.progetto_febbraio2020;

import org.apache.catalina.Server;
import org.slf4j.ILoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.number.PercentStyleFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import it.univpm.progetto_febbraio2020.services.Downloader;
import it.univpm.progetto_febbraio2020.services.MetaData;
import it.univpm.progetto_febbraio2020.services.TSVReader;

@SpringBootApplication
public class ProgettoFebbraio2020Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProgettoFebbraio2020Application.class, args);
		String filename = "dataFile.tsv;";
		String url = "http://ec.europa.eu/eurostat/estat-navtree-portlet-prod/BulkDownloadListing?file=data/apro_mk_colm.tsv.gz&unzip=true";
		
		
		if(Files.exists(Paths.get(filename))) {	//Controlla se il file esiste già, altrimenti lo scarica
			System.out.println("File già scaricato.");
		}else {
		Downloader.download(url,filename);
		}
		
		TSVReader parser = new TSVReader(filename); //parser del file tsv
		parser.parsing();
		MetaData metadata = new MetaData(); //crea la mappa per i metadati
		
	}
}



