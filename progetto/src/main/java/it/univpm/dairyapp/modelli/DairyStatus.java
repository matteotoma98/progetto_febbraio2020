package it.univpm.dairyapp.modelli;

// TODO: Auto-generated Javadoc
/**
 * The Class DairyStatus.
 * @author Matteo e Federico
 */
public class DairyStatus {

    /** The dairyprod. */
    private String dairyprod;

    /** The unit. */
    private String unit;

    /** The geo. */
    private String geo; //geo= nazione di provenienza del latticino

    /** The dairy filtered value. */
    private double dairyFilteredValue; 

    /** The dairy. */
    private double[] dairy;

    /**
     * Instantiates a new dairy status.
     */
    public DairyStatus() {
        this.dairyprod = "";
        this.unit = "";
        this.geo = "";
        this.dairy = null;
    }

    /**
     * Instantiates a new dairy status.
     *
     * @param dairyprod the dairyprod
     * @param unit the unit
     * @param geo the geo
     * @param dairy the dairy
     */
    public DairyStatus(String dairyprod, String unit, String geo, double[] dairy) {
        this.dairyprod = dairyprod;
        this.unit = unit;
        this.geo = geo;
        this.dairy = dairy;
    }


    /**
     * Gets the dairy filtered value.
     *
     * @return the dairy filtered value
     */
    public double getDairyFilteredValue() {
        return dairyFilteredValue;
    }



    /**
     * Sets the dairy filtered value.
     *
     * @param dairyFilteredValue the new dairy filtered value
     */
    public void setDairyFilteredValue(double dairyFilteredValue) {
        this.dairyFilteredValue = dairyFilteredValue;
    }

    /**
     * Gets the dairy prod.
     *
     * @return the dairy prod
     */
    public String getDairyProd() {
        return dairyprod;
    }

    /**
     * Sets the dairy prod.
     *
     * @param dairyprod the new dairy prod
     */
    public void setDairyProd(String dairyprod) {
        this.dairyprod = dairyprod;
    }



    /**
     * Gets the unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }


    /**
     * Sets the unit.
     *
     * @param unit the new unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }


    /**
     * Gets the geo.
     *
     * @return the geo
     */
    public String getGeo() {
        return geo;
    }

    /**
     * Sets the geo.
     *
     * @param geo the new geo
     */
    public void setGeo(String geo) {
        this.geo = geo;
    }

    /**
     * Gets the dairy.
     *
     * @return the dairy
     */
    public double[] getDairy() {
        return dairy;
    }


    /**
     * Sets the dairy.
     *
     * @param dairy the new dairy
     */
    public void setDairy(double[] dairy) {
        this.dairy = dairy;
    }


    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() { 
        return "DairyStatus [dairyprod=" + dairyprod + ", unit=" + unit + ", geo=" + geo + ", dairy=" + dairy + "]";

    }



}