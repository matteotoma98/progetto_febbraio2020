 package it.univpm.dairyapp;

 import java.lang.reflect.InvocationTargetException;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;

import it.univpm.dairyapp.modelli.DairyStatus;
import it.univpm.dairyapp.servizi.Filtri;
import it.univpm.dairyapp.servizi.Statistiche;
import it.univpm.dairyapp.servizi.LettoreTSV;

import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.RestController;


 // TODO: Auto-generated Javadoc
 /**
  * The Class DairyController.
  * @author Matteo e Federico
  */
 @RestController
 public class DairyController {
   /**
    * Gets the data set.
    *
    * @return the data set
    */
   @GetMapping("/dairy")
   public static List < DairyStatus > getDataSet() {
       return LettoreTSV.getDairyList();
   }


   /**
    * Dairy filter.
    *
    * @return the list
    */
   @GetMapping("/dairy/get") //gestisce le richieste di get
   public List < DairyStatus > DairyFilter(@RequestParam(value = "filter", defaultValue = "", required = false) String filter) {
        
       List < DairyStatus > dairyrepo = LettoreTSV.getDairyList();
       List < String > fieldList = new ArrayList < String > ();
       List < String > operatorList = new ArrayList < String > ();
       List < Object > valueList = new ArrayList < Object > ();
       List < String > operatorLog = new ArrayList < String > ();
       List < Object > YearList = new ArrayList < Object > ();
       String[] FilterV = filter.split(":");

       if (filter.contentEquals("")) return dairyrepo; //se non è applicato un filtro ritorna direttamente tutto il dataset
       else {
           for (int i = 0; i < FilterV.length; i++) { 	
               fieldList.add(FilterV[i]);
               if (FilterV[++i].contains("20")) {
                   YearList.add(FilterV[i]);
               } else --i;

               operatorList.add(FilterV[++i]);
               valueList.add(FilterV[++i]);
               if (i + 1 < FilterV.length) { 
                   operatorLog.add(FilterV[++i]);
               }

           }
       }
       return (List < DairyStatus > ) Filtri.select(dairyrepo, fieldList, operatorList, valueList, operatorLog, YearList); 
   }

   /**
    * Filter stats.
    *
    * @param nameStats the name stats
    * @param filter the filter
    * @return the list
    * @throws NoSuchMethodException the no such method exception
    * @throws SecurityException the security exception
    * @throws IllegalAccessException the illegal access exception
    * @throws IllegalArgumentException the illegal argument exception
    * @throws InvocationTargetException the invocation target exception
    */
   @GetMapping("/dairy/stats") //statistiche sui latticini
   public List < Map > filterStats(@RequestParam(value = "nameStats", defaultValue = "", required = false) String nameStats, 
    	
		   
    	@RequestParam(value = "filter", defaultValue = "", required = false) String filter) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

	   
       List < DairyStatus > dairyrepo = LettoreTSV.getDairyList();
       List < String > fieldList = new ArrayList < String > ();
       List < String > operatorList = new ArrayList < String > ();
       List < Object > valueList = new ArrayList < Object > ();
       List < String > operatorLog = new ArrayList < String > ();
       List < Object > YearList = new ArrayList < Object > ();
       String[] FilterV = filter.split(":");
       List < Map > outlist = new ArrayList < > ();

      if (nameStats.contentEquals("")) { 
           outlist.add(Statistiche.getStat("dairyprod", dairyrepo));
           outlist.add(Statistiche.getStat("unit", dairyrepo));
           outlist.add(Statistiche.getStat("geo", dairyrepo));
           for (int i = 2020; i >= 1968; i--) {
               outlist.add(Statistiche.getStat(Integer.toString(i), dairyrepo));
           }
           return outlist;
      }

       if (filter.contentEquals("")) { 
           outlist.add(Statistiche.getStat(nameStats, dairyrepo));
           return outlist;

       } else {

           for (int i = 0; i < FilterV.length; i++) { 
               fieldList.add(FilterV[i]);
               if (FilterV[++i].contains("20")) { 
               YearList.add(FilterV[i]);
               } else --i;
               operatorList.add(FilterV[++i]);
               valueList.add(FilterV[++i]);
               if (i + 1 < FilterV.length) { 
               operatorLog.add(FilterV[++i]);
               }
           }
       }
       outlist.add(Statistiche.getStat(nameStats, (List) 
       Filtri.select(dairyrepo, fieldList, operatorList, valueList, operatorLog, YearList)));
       return outlist;
     }
 }