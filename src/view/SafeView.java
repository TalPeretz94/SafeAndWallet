package view;


import ood.hw3.Item;
import ood.hw3.Safe;

import java.util.ArrayList;


public class SafeView extends MyListView{

    public SafeView(){
        super("Safe");
    }


    public void showSafe(Safe safe){
        ArrayList<String> strs = new ArrayList<>();

        for (Item item : safe){
            strs.add(item.toString());
        }

        setItems(strs);
    }
}
