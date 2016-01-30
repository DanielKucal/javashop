package shop.products.parameters;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public class Price {
    private double netAmount = 0.00;
    private double tax = 0.23;
    private Currency currency = Currency.EUR;

    public Price(){

    }

    /**
     * Create price from net value
     * @param price price before tax
     */
    public Price(double price){
        this.setNetAmount(price);
    }

    /**
     * Create price
     * @param price price
     * @param addTax should given price be taxed?
     */
    public Price(double price, boolean addTax){
        if(!addTax){
            this.setNetAmount(price / (1.00+tax));
        } else {
            this.setNetAmount(price);
        }
    }

    public String getFullPrice(){
        return this.getFinalPrice() + " " + this.getCurrency().toString();
    }

    public String toString(){
        return this.getFullPrice();
    }

    public double getFinalPrice(){
        return round(this.getNetAmount() * (1.00+this.getTax()), 2);
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        if(netAmount < 0) throw new IllegalArgumentException();

        this.netAmount = netAmount;
    }

    public double getTax() {
        return tax;
    }

    public Price setTax(double tax) {
        if(tax < 0) throw new IllegalArgumentException();

        this.tax = tax;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
