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
    double netAmount = 0.00;
    double tax = 0.23;
    Currency currency = Currency.EUR;

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
     * @param taxed is given price taxed?
     */
    public Price(double price, boolean taxed){
        if(taxed){
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
        return this.getNetAmount() * (1.00+this.getTax());
    }

    public double getNetAmount() {
        return round(netAmount, 2);
    }

    public Price setNetAmount(double netAmount) {
        if(netAmount < 0) throw new IllegalArgumentException();

        this.netAmount = netAmount;
        return this;
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

    public Price setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
