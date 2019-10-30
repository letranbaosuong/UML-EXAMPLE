package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.ToggleButton;
import org.json.JSONException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;

public class Controller {
    public ToggleButton btnDo;

//    Node iconOn = new ImageView(new Image("C:\\Users\\Suong\\Desktop\\hoctap\\nam3\\UML\\UML-EXAMPLE\\DemoJavaFX\\src\\images\\icons8_toggle_off_200px.png"));
//    Node iconOff = new ImageView(new Image("C:\\Users\\Suong\\Desktop\\hoctap\\nam3\\UML\\UML-EXAMPLE\\DemoJavaFX\\src\\images\\icons8_toggle_on_200px.png"));

    public void clickDo(ActionEvent actionEvent) {
        ToggleButton btnSelected = (ToggleButton) actionEvent.getSource();
        if (btnSelected.isSelected()) {
//            btnDo.setGraphic(iconOn);
            System.out.println(1);
        } else {
//            btnDo.setGraphic(iconOff);
            System.out.println(0);
        }
    }
}
