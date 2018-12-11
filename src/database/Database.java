package database;


import agents.PostMan;

import java.sql.*;

import static utils.Constants.db_path;

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
        String db_url = "jdbc:sqlite:" + db_path ;

        try {
            conn = DriverManager.getConnection(db_url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        return instance;
    }

    public void insertPostOfficeData(int nrPostMen, int nrOrders) throws SQLException {

        Statement stat = conn.createStatement();
        String query = "INSERT INTO PostOfficeData (nrPostMen, nrOrders) VALUES (" + nrPostMen + "," + nrOrders+ ")";
        stat.executeUpdate(query);


    }

    public void insertPostManData(PostMan postMan, double price) throws SQLException {

        Statement stat = conn.createStatement();
        String query = "SELECT nrPostMen, nrOrders FROM PostOfficeData ORDER BY idPostOfficeData DESC LIMIT 1;";
        ResultSet rs = stat.executeQuery(query);

        int nrPostMen = Integer.parseInt(rs.getString("nrPostMen"));
        int nrOrders = Integer.parseInt(rs.getString("nrOrders"));


        Statement stat1 = conn.createStatement();
        String values = nrOrders + "," + nrPostMen + "," + postMan.getNrOrder() + "," + postMan.getId() + ",";
        values += postMan.getVehicle().getMaximumLoad() +","+ postMan.getVehicle().getCurrentLoad() + "," + postMan.getPersonalGain() + ",";

        double distance = postMan.getPosition().getDistance(postMan.getPostOfficePosition()) +
                postMan.getPendingOrder().getPosition().getDistance(postMan.getPostOfficePosition());

        values += distance + ","+ postMan.getPendingOrder().getEstimatedTime()+","+price;

        String query1 = "INSERT INTO Data(nrOrders,nrPostMen,idOrder,idPostMan,maxLOad,currentLoad,personalGain,distance,estimatedTime,proposal) VALUES (" + values+ ");";
        stat1.executeUpdate(query1);

        rs.close();

    }

    public void updatePostOfficeData(int idOrder, int idPostman, int decision) throws SQLException {

        Statement stat = conn.createStatement();
        String query = "UPDATE Data SET decision = "+ decision +" WHERE idPostMan = "+ idPostman +" AND idOrder = " + idOrder + ";";
        stat.executeUpdate(query);


    }

    public int getPostManId(String name1) throws SQLException{

        Statement stat = conn.createStatement();
        String query = "INSERT INTO PostMan(name) VALUES (\""+ name1+"\");";
        stat.executeUpdate(query);

        String query1 = "SELECT DISTINCT max(idPostMan) as maxId FROM PostMan;";
        ResultSet rs = stat.executeQuery(query1);
        String maxId = rs.getString("maxId");
        rs.close();

        if(maxId == null){
            return 0;
        }
        else return Integer.parseInt(maxId);
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
