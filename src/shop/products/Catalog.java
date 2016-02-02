package shop.products;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.TreeMap;

/**
 * Created on 2016-01-27
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public class Catalog {
    private static final String fileName = "src/shop/resources/xml/catalog.xml";
    private static TreeMap<Integer, Product> products = null;
    private static Boolean loaded = false;
    private static Integer lastProductId = 0;

    public static TreeMap<Integer, Product> getProducts(){
        return products;
    }

    private Catalog(){ }

    public static void load(){
        if(!loaded) {
            System.out.println("Loading product data...");
            try {
                XMLDecoder decoder = new XMLDecoder(
                        new BufferedInputStream(
                                new FileInputStream(getFileName())
                        )
                );
                products = (TreeMap<Integer, Product>) decoder.readObject();
                lastProductId = products.lastEntry().getValue().getId()+1;
                System.out.println(products.size() + " products have been loaded.");
            } catch (Exception e) {
                System.out.println("Can not read saved catalog data. Empty catalog instance will be used instead.");
                e.printStackTrace();
                products = new TreeMap<>();
            }
        }
        loaded = true;
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
            e.printStackTrace();
        }
    }

    public static String getFileName() {
        return fileName;
    }

    public static TreeMap<Integer, Product> setProducts(TreeMap<Integer, Product> newProducts) {
        products = newProducts;
        return products;
    }

    public static int getLastProductId(){
        return lastProductId;
    }

    private static void increaseLastProductId(){
        lastProductId++;
    }

    public static void add(Product p){
        p.setId(Catalog.getLastProductId());
        Catalog.increaseLastProductId();
        products.put(p.getId(), p);
    }

    public static void remove(Integer i){
        products.remove(i);
    }

    public static void remove(Product p){
        remove(p.getId());
    }

    public static void set(Product p) {
        if (p.getId() == null || p.getId() == 0) {
            add(p);
            return;
        }
        products.replace(p.getId(), p);
    }
}
