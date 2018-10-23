package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class MyListView extends ScrollPane {
    private VBox listBox = new VBox();
    private String title = "";

    public MyListView(){
        final int SPACE = 25;

        listBox.setSpacing(SPACE);
        listBox.setAlignment(Pos.CENTER);
        listBox.setPadding(new Insets(SPACE));
        setContent(listBox);
    }

    public MyListView(String title){
        this();
        setTitle(title);
    }

    protected void setItems(List<String> items){
        listBox.getChildren().clear();

        listBox.getChildren().add(new Label(title));
        items.forEach(item -> listBox.getChildren().add(new Label(item)));
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
