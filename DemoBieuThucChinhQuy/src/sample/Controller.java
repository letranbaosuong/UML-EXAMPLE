package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;

public class Controller {
    public TextField txtCheck;
    public Button btnCheck;

    String pattern = "0[0-9]{9,10}";
    String reHoTen = "[a-zA-Z]+";
    String reCMND = "[0-9]{9}";
    String reDienThoai = "0\\d{9,10}";
    String reXeMaySaiGon = "5\\d-[A-Z]\\d-((\\d{4})|(\\d{3}\\.\\d{2}))";
    String reEmail="\\w+@\\w+\\.\\w{1,3}";

    public void click(ActionEvent actionEvent) {
        String mobile = txtCheck.getText().trim();
        if (mobile.matches("d$")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Kiểm tra");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Đúng");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Kiểm tra");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Sai");
            alert.showAndWait();
//            txtCheck.setText("");
            txtCheck.requestFocus();
        }
    }
}
