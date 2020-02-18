package it.univpm.progetto_febbraio2020.services;
import it.univpm.progetto_febbraio2020.models.DairyStatus;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// TODO: Auto-generated Javadoc

/**
 * The Class MetaData.
 * @author Matteo e Federico
 */
public class MetaData {

    /** The metalist. */
    private List < Map > metalist = new ArrayList < > ();

    /**
     * Gets the metalist.
     *
     * @return the metalist
     */
    public List < Map > getMetalist() {
        return metalist;
    }

    /**
     * Instantiates a new meta data.
     */
    public MetaData() {

        Field[] fields = DairyStatus.class.getDeclaredFields(); //importa gli attributi dalla classe main
        for (Field i: fields) {
            Map < String, String > map = new HashMap < > ();
            map.put("alias", i.getName());
            if (!i.getName().equals("dairy"))
                map.put("source", i.getName().toUpperCase());
            else map.put("source", "TIME");
            map.put("type", i.getType().getSimpleName());
            metalist.add(map);
            }
    }
}