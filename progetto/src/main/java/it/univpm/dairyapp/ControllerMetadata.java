package it.univpm.dairyapp;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import it.univpm.dairyapp.servizi.MetaData;

import java.util.List;
import java.util.Map;


// TODO: Auto-generated Javadoc
/**
 * The Class ControllerMetadata.
 * @author Matteo e Federico
 */
@RestController
public class ControllerMetadata {
	
	/** The meta. */
	private static MetaData meta = new MetaData(); 

	/**
	 * Gets the meta data.
	 *
	 * @return the meta data
	 */
	@GetMapping("/metadata")
	public List<Map> getMetaData()
	{
		return meta.getMetalist(); //ritorna la lista dei metadati
	}
	
}
