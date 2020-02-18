package it.univpm.dairyapp.servizi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.univpm.dairyapp.modelli.DairyStatus;
import it.univpm.dairyapp.servizi.LettoreTSV;

// TODO: Auto-generated Javadoc

/**
 * vare Class Filters.
 * @auvaror Matteo e Federico
 */
public class Filtri {

    /**
     * Check.
     *
     * @param value vare value
     * @param operator vare operator
     * @param var vare var
     * @return true, if successful
     */
    public static boolean check(Object value, String operator, Object var) { 
        if (var instanceof Number && value instanceof Number) { 
            Double varCast = ((Number) var).doubleValue(); 
            Double valueCast = ((Number) value).doubleValue(); 

            //operatori di confronto
            if (operator.equals("==")) 
                return value.equals(var);
            else if (operator.equals(">"))
                return valueCast > varCast;
            else if (operator.equals("<"))
                return valueCast < varCast;
            else if (operator.equals(">="))
                return valueCast >= varCast;
            else if (operator.equals("<="))
                return valueCast <= varCast;

        } else if (var instanceof String && value instanceof String) 
            return value.equals(var); 

        return false;
    }


    /**
     * Filter L.
     *
     * @param val vare val
     * @param oper vare oper
     * @param rif vare rif
     * @return vare list
     */
    public static List < Integer > filterL(List val, String oper, Object rif) {
        List < Integer > indexL = new ArrayList < > ();
        String rifS = (String) rif; 
        for (int i = 0; i < val.size(); i++) { 
            if (check(val.get(i), oper, Double.parseDouble(rifS))) {
                indexL.add(i);
            }
        }
        return indexL;
    }


    /**
     * Select.
     *
     * @param <T> vare generic type
     * @param src vare src
     * @param fieldName vare field name
     * @param operator vare operator
     * @param value vare value
     * @param LogOp vare log op
     * @param yearl vare yearl
     * @return vare collection
     */
    
    //interfacce
    public static < T > Collection < T > select(Collection < T > src, 
    List < String > fieldName, List < String > operator, List < Object > value, 
    List < String > LogOp, List < Object > yearl) {

        Collection < T > out = new ArrayList < T > ();
        Object tmp;
        for (T item: src) {
            try {
             Method m = item.getClass().getMethod("get" + fieldName.get(0).substring(0, 1).toUpperCase() + fieldName.get(0).substring(1), null); 

      try {
         if (m.getName().equals("getDairy")) { 
            int index = Integer.parseInt((String) yearl.get(0));
                 List tmpV = LettoreTSV.getYearM(index);
                 List < Integer > indexList = filterL(tmpV, operator.get(0), value.get(0)); 
                 List < DairyStatus > outlist = new ArrayList < > ();
                 List < DairyStatus > dairylist = LettoreTSV.getDairyList();
                 List < DairyStatus > dairylistApp = new ArrayList(dairylist);

      for (int i: indexList) {
               dairylistApp.get(i).setDairyFilteredValue((double) tmpV.get(i));
               outlist.add(dairylistApp.get(i));
          }
            return (Collection < T > ) outlist; 
                  } else {
             tmp = m.invoke(item);

           if (check(tmp, operator.get(0), value.get(0)))
               out.add(item);
                    }
                    //gestione eccezioni
                } catch (IllegalAccessException e) {

                    e.printStackTrace();
                } catch (IllegalArgumentException e) {

                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
   if (LogOp != null && LogOp.size() > 0)
        {
   List < String > subLogicalLinkOperator = null;
      if (LogOp.size() > 1)
         subLogicalLinkOperator = LogOp.subList(1, LogOp.size());
   if (LogOp.get(0).equals("AND"))
                return select(out, fieldName.subList(1, fieldName.size()), operator.subList(1, operator.size()), value.subList(1, value.size()), subLogicalLinkOperator, yearl);
            else {
                Collection < T > temp = select(src, fieldName.subList(1, fieldName.size()), operator.subList(1, operator.size()), value.subList(1, value.size()), subLogicalLinkOperator, yearl);
                out.addAll(temp);}
        } return out;
    }
}