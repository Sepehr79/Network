package Network.DataPackage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


//Example____________________________________________________________________________________

public class Main {
    public static void main(String[] args) {

        //Writing to database
        /*Tables<Person> tables = new Tables<>();
        tables.addData(new Person("ali", 22));
        tables.addData(new Person("sepehr", 20));
        tables.addData(new Person("sahar", 19));

        DataBase dataBase = new DataBase("DataBase.txt");


        try {
            dataBase.sendTables(tables);
        } catch (IOException e) {
            System.err.println("Cant send tables!");
        }*/


        //Reading from database
        try {
            ArrayList<Person> arrayList = new DataBase("DataBase.txt").getTables().getTable();
            System.out.println(arrayList.get(0).name);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

class Person implements Serializable {
    String name;
    int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
}
