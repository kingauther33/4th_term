package example.jdbc.java.generic;

import java.util.ArrayList;
import java.util.List;

public class MyList<H> {

    private List<H> myList = new ArrayList<>();

    public List<H> getObj() {
        return myList;
    }

    public void setObj(List<H> myList) {
        this.myList = myList;
    }

    public void add(H obj) {
        myList.add(obj);
    }

    public int size() {
        return myList.size();
    }
}
