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

    public Promotion(Integer discount, LocalDate dateFrom, LocalDate dateTo) {
        this.discount = discount;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Promotion setDiscount(Integer discount) {
        if(discount < 10 || discount > 70) throw new IllegalArgumentException("Discount must be between 10-70%");
        this.discount = discount;
        return this;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public Promotion setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public Promotion setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }
}
