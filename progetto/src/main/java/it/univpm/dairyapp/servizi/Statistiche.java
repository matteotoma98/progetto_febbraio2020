package it.univpm.dairyapp.servizi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.univpm.dairyapp.modelli.DairyStatus;
import it.univpm.dairyapp.servizi.LettoreTSV;

/**
 * The Class Statistics.
 * @author Matteo e Federico
 */
public class Statistiche {

    /**
     * Media.
     *
     * @param lista the lista
     * @return the double
     */
    public static double media(List < Double > lista) {
        return somma(lista) / numeroelementi(lista);
    }

    /**
     * Minimo.
     *
     * @param lista the lista
     * @return the double
     */
    public static double minimo(List < Double > lista) {
        double minimo = lista.get(0);
        for (Double numero: lista) {
            if (numero < minimo) minimo = numero;
        }
        return minimo;
    }

    /**
     * Massimo.
     *
     * @param lista the lista
     * @return the double
     */
    public static double massimo(List < Double > lista) {
        double massimo = lista.get(0);
        for (Double numero: lista) {
            if (numero > massimo) massimo = numero;
        }
        return massimo;
    }

    /**
     * Somma.
     *
     * @param lista the lista
     * @return the double
     */
    public static double somma(List < Double > lista) {
        double somma = 0;
        for (Double valore: lista) {
            somma += valore;
        }
        return somma;
    }

    /**
     * Numeroelementi.
     *
     * @param lista the lista
     * @return the int
     */
    public static int numeroelementi(List lista) {
        return lista.size();
    }

    /**
     * Conteggio elementi unici.
     *
     * @param lista the lista
     * @return the map
     */
    public static Map < Object, Integer > conteggioElementiUnici(List lista) {
        Map < Object, Integer > membro = new HashMap < > ();
        for (Object v: lista) {
            Integer occorrenza = membro.get(v);
            membro.put(v, (occorrenza == null ? 1 : occorrenza + 1));
        }
        return membro;

    }


    /**
     * Deviazionestandard.
     *
     * @param list the list
     * @return the double
     */
    public static double deviazionestandard(List < Double > list) {
        double vm = media(list);
        double varianza = 0;
        double risultato;
        for (Double n: list) {
            varianza += Math.pow(n - vm, 2);
        }
        risultato = Math.sqrt(varianza);
        return risultato;
    }

    /**
     * Gets the stat.
     *
     * @param cmp the name field
     * @param src the src
     * @return the stat
     * @throws NoSuchMethodException the no such method exception
     * @throws SecurityException the security exception
     * @throws IllegalAccessException the illegal access exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws InvocationTargetException the invocation target exception
     */
    public static Map < String, Object > getStat(String cmp, List < DairyStatus > src) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        List values = new ArrayList < > ();
        if (cmp.contains("20") || (cmp.contains("196"))) { 
            values = LettoreTSV.getYearM((Integer.parseInt(cmp)));
            return getStatistiche(cmp, values); 
        } else if (cmp.contains("1968")) {
            values = LettoreTSV.getYearM((Integer.parseInt(cmp)));
            return getStatistiche(cmp, values);
        } else if (cmp.contains("197")) {
            values = LettoreTSV.getYearM((Integer.parseInt(cmp))); 
            return getStatistiche(cmp, values); 
        } else if (cmp.contains("198")) {
            values = LettoreTSV.getYearM((Integer.parseInt(cmp))); 
            return getStatistiche(cmp, values); 
        } else if (cmp.contains("199")) {
            values = LettoreTSV.getYearM((Integer.parseInt(cmp))); 
            return getStatistiche(cmp, values); 
        } else { //string stats

            Object tmp;
            for (DairyStatus item: src) {
                Method m;
                m = item.getClass().getMethod("get" + cmp.substring(0, 1).toUpperCase() + cmp.substring(1), null);
                tmp = m.invoke(item); 
                values.add(tmp);

            }
            return getStatistiche(cmp, values); 
        }

    }


    /**
     * Gets the tutte le statistiche.
     *
     * @param NomeDelCampo the nome del campo
     * @param lista the lista
     * @return the tutte le statistiche
     */
    public static Map < String, Object > getStatistiche(String NomeDelCampo, List lista) {
        Map < String, Object > mappa = new HashMap < > ();
        mappa.put("campo", NomeDelCampo);
        if (NomeDelCampo.contains("20") || (NomeDelCampo.contains("19"))) {

            mappa.put("deviazionestandard", deviazionestandard(lista));
            mappa.put("media", media(lista));
            mappa.put("minimo", minimo(lista));
            mappa.put("massimo", massimo(lista));
            mappa.put("somma", somma(lista));

            return mappa;
        } else { 
            mappa.put("numeroelementi", numeroelementi(lista));
            mappa.put("elementiunici", conteggioElementiUnici(lista));

            return mappa;
        }
    }

}