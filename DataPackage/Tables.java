package Network.DataPackage;

import java.io.Serializable;
import java.util.ArrayList;

public class Tables<DT> implements Serializable {
    private ArrayList<DT> table = new ArrayList<>();

    public void addData(DT data){
        table.add(data);
    }
    public void removeData(int index){
        table.remove(index);
    }
    public ArrayList<DT> getTable() {
        return (ArrayList<DT>) table;
    }
    public void setTable(ArrayList<DT> table) {
        this.table = table;
    }
}
