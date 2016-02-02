package shop.products.parameters;

import java.time.LocalDate;

/**
 * Created on 2016-02-01
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public class Promotion {
    private Integer discount;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Promotion(){

    }

    @Override
    public String toString(){
        return discount + "% from " + dateFrom + " to " + dateTo;
    }

    public Promotion(Integer discount, LocalDate dateFrom, LocalDate dateTo) {
        this.discount = discount;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        if(discount < 10 || discount > 70) throw new IllegalArgumentException("Discount must be between 10-70%");
        this.discount = discount;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
