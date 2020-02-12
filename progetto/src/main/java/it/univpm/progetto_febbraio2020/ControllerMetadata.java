package it.univpm.progetto_febbraio2020;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import it.univpm.progetto_febbraio2020.models.DairyStatus;
import it.univpm.progetto_febbraio2020.services.MetaData;
import it.univpm.progetto_febbraio2020.services.TSVReader;

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
		return meta.getMetalist(); //return the method from the class MetaData.
	}
	
}
