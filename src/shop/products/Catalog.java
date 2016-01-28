package shop.products;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

/**
 * Created on 2016-01-27
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public class Catalog {
    private static String fileName = "src/shop/resources/xml/catalog.xml";
    private static ArrayList<Product> products = null;
    private static boolean loaded = false;

    public static ArrayList<Product> getProducts(){
        if(!loaded) {
            load();
            loaded = true;
        }
        return products;
    }

    private Catalog(){ }

    public static void load(){
        try {
            XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(getFileName())
                    )
            );
            products = (ArrayList<Product>) decoder.readObject();
        } catch (Exception e) {
            System.out.println("Can not read saved catalog data. Empty catalog instance will be used instead.");
            products = new ArrayList<>();
        }
    }

    public static void save(){
        try {
            XMLEncoder encoder = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(getFileName())
                    )
            );
            encoder.writeObject(products);
            encoder.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        Catalog.fileName = fileName;
    }

    public static ArrayList<Product> setProducts(ArrayList<Product> newProducts) {
        products = newProducts;
        return products;
    }
}