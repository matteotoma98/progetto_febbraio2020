package it.univpm.dairyapp.servizi;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import it.univpm.dairyapp.modelli.DairyStatus;

/**
 * The Class TSVReader.
 * @author Matteo e Federico
 */
public class LettoreTSV {

    /** The Constant TAB_DELIMETER. */
    private final static String TAB_DELIMETER = "	"; 

    /** The dairy list. */
    static List < DairyStatus > dairyList = new ArrayList < > ();

    /** The reader. */
    private BufferedReader reader;

    /** The filename. */
    private String filename;

    /**
     * Main constructor of the class that reads the file.
     *
     * @param filename the filename
     */

    public LettoreTSV(String filename) {
        this.filename = filename;

        try {
            reader = new BufferedReader(new FileReader(this.filename));

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + this.filename);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Parsing.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void parsing() throws IOException {
        String temp;
        reader.readLine();
        while ((temp = reader.readLine()) != null) { 
            temp = temp.replace(",", TAB_DELIMETER); 
            temp = temp.replace(":", "-1"); //rimpiazziamo i dati non registrati nel dataset col valore "-1"
            temp = temp.replace("b", ""); //cancellazione dei flag nei dati
            temp = temp.replace("c", "");
            temp = temp.replace("d", "");
            temp = temp.replace("e", "");
            temp = temp.replace("f", "");
            temp = temp.replace("n", "");
            temp = temp.replace("p", "");
            temp = temp.replace("r", "");
            temp = temp.replace("s", "");
            temp = temp.replace("u", "");
            temp = temp.replace("z", "");
            String[] campo = temp.trim().split(TAB_DELIMETER);

            if (campo.length > 1) {
                String dairyprod = campo[0].trim();
                String unit = campo[1].trim();
                String geo = campo[2].trim();
                double[] dairy = new double[625]; //625 colonne nel tsv

                for (int i = 0; i < 236; i++) {
                    dairy[i] = Double.parseDouble(campo[3 + i].trim());
                }


                DairyStatus status = new DairyStatus(dairyprod, unit, geo, dairy);
                if (!dairyprod.equals("TOTAL") && !geo.equals("TOTAL")) {
                    dairyList.add(status);
                }
            }
        }
    }



    /**
     * Gets the year M.
     *
     * @param index the index
     * @return the year M
     */
    static List getYearM(int index) {
        List < Object > yearm = new ArrayList < > ();
        for (DairyStatus dr: dairyList) { 
            if (dr.getDairy()[2020 - index] >= 0) { 
                Object values = dr.getDairy()[2020 - index];
                yearm.add(values); 
            }


        }
        return yearm;
    }



    /**
     * Gets the dairy list.
     *
     * @return the dairy list
     */
    public static List < DairyStatus > getDairyList() {
        return dairyList;
    }
}