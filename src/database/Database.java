package database;


import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static Database instance = new Database();

    private Connection conn = null;

    private Database(){
        openDatabaseConnection();
    }

    private void openDatabaseConnection() {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String db_url = "jdbc:sqlite:database.db" ;

        try {
            conn = DriverManager.getConnection(db_url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        return instance;
    }

    /*

    EXAMPLES

    public ArrayList<Product> getProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();

        Statement stat = conn.createStatement();
        String query = "SELECT Product.idProduct \"id\", Product.name \"name\", Product.description \"description\", Category.name \"category\"\n" +
                "FROM Product, Category WHERE Product.idCategory = Category.idCategory ORDER BY name;";
        ResultSet rs = stat.executeQuery(query);

        while (rs.next()) {
            int id = Integer.parseInt(rs.getString("id"));
            String name = rs.getString("name");
            String desc = rs.getString("description");
            String cat = rs.getString("category");

            products.add(new Product(id,name,desc,cat));

        }
        rs.close();

        return products;
    }

    public ArrayList<Category> getCategories() throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();

        Statement stat = conn.createStatement();
        String query = "SELECT idCategory \"id\", name FROM Category ORDER BY name;";
        ResultSet rs = stat.executeQuery(query);

        while (rs.next()) {
            int id = Integer.parseInt(rs.getString("id"));
            String name = rs.getString("name");

            categories.add(new Category(id,name));

        }
        rs.close();

        return categories;
    }

    public ArrayList<Product> getFeaturedProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();

        Statement stat = conn.createStatement();
        String query = "SELECT Featured.idProduct \"id\", Product.name \"name\", Product.description \"description\", Category.name \"category\"\n" +
                "FROM Featured, Product, Category WHERE Featured.idProduct = Product.idProduct AND Product.idCategory = Category.idCategory\n" +
                "ORDER BY Featured.idFeatured DESC;";
        ResultSet rs = stat.executeQuery(query);

        while (rs.next()) {
            int id;
            id = Integer.parseInt(rs.getString("id"));
            String name = rs.getString("name");
            String desc = rs.getString("description");
            String cat = rs.getString("category");

            products.add(new Product(id,name,desc,cat));

        }
        rs.close();

        return products;

    }
    */

}
