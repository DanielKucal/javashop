package shop.products;

import java.util.ArrayList;

/**
 * Created on 2016-01-27
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public class Catalog extends ArrayList<Product> {
    private static Catalog instance = new Catalog();

    public static Catalog getInstance(){
        return instance;
    }

    private Catalog(){
        super();
    }
}