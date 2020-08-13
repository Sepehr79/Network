package Network.DataPackage;

import java.io.*;

public class DataBase {
    String dataBasePath;
    private Tables tables;

    //Constructors__________________________________________
    public DataBase(String dataBasePath) {
        this.dataBasePath = dataBasePath;
        try {
            tables = getTables();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            tables = new Tables();
        }
    }
    public DataBase(String dataBasePath, Tables tables){
        this.dataBasePath = dataBasePath;
        this.tables = tables;
    }

    //Sending tables to dataBase___________________________
    public void sendTables(Tables tables) throws IOException {
        this.tables = tables;
        new ObjectOutputStream(new FileOutputStream(dataBasePath)).writeObject(tables);
    }
    public void sendTables() throws IOException {
        new ObjectOutputStream(new FileOutputStream(dataBasePath)).writeObject(this.tables);
    }


    //Getters and setters__________________________________
    public Tables getTables() throws IOException, ClassNotFoundException {
        return (Tables) new ObjectInputStream(new FileInputStream(dataBasePath)).readObject();
    }

    public void setTables(Tables tables) {
        this.tables = tables;
    }


}
