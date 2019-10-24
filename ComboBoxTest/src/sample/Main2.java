package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main2 extends Application {

    TextField txtSoHoaDon, txtTenKhachHang, txtDiaChi, txtDienThoai, txtTongCong;
    Button btnTaoMoi, btnThemChiTiet, btnLuu, btnDong;
    DatePicker dpkNgayBan;
    ComboBox<String> cboMSKhachHang, cboMSMatHang = new ComboBox<>();
    GridPane gridPane;
    ScrollPane scrollPane;

    KetNoiCSDL kncsdl;
    final ObservableList optionsMSKH = FXCollections.observableArrayList();
    final ObservableList optionsMSMH = FXCollections.observableArrayList();
    List<TextField> txtSTTList = new ArrayList(), txtMSMatHangList = new ArrayList(), txtTenMatHangList = new ArrayList(), txtDVTlist = new ArrayList(), txtSoLuongList = new ArrayList(), txtDonGiaList = new ArrayList(), txtTriGiaList = new ArrayList();
    int nRowsCT = 0;
    String MSKH_Luu = "";
    String MSMH_Luu = "";

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Nhập dữ liệu phiếu bán hàng");
        BorderPane layout = new BorderPane();

        VBox vBoxTop = new VBox();
        HBox hBox1 = new HBox();
        Label lblSoHoaDon = new Label("Số hóa đơn:");
        lblSoHoaDon.setMinWidth(70);
        txtSoHoaDon = new TextField();
        txtSoHoaDon.setMinWidth(100);
        Label lblNgayBan = new Label("Ngày bán:");
        lblNgayBan.setMinWidth(70);
        dpkNgayBan = new DatePicker(LocalDate.now());
        dpkNgayBan.setMinWidth(200);
        hBox1.getChildren().addAll(lblSoHoaDon, txtSoHoaDon, lblNgayBan, dpkNgayBan);
        hBox1.setSpacing(5);
        hBox1.setPadding(new Insets(5));
        vBoxTop.getChildren().add(hBox1);
        HBox hBox2 = new HBox();
        Label lblMSKhachHang = new Label("Mã số khách hàng:");
        lblMSKhachHang.setMinWidth(100);
        cboMSKhachHang = new ComboBox();
        cboMSKhachHang.setMinWidth(100);
        Label lblTenKhachHang = new Label("Tên khách hàng:");
        lblTenKhachHang.setMinWidth(100);
        txtTenKhachHang = new TextField();
        txtTenKhachHang.setMinWidth(400);
        hBox2.getChildren().addAll(lblMSKhachHang, cboMSKhachHang, lblTenKhachHang, txtTenKhachHang);
        hBox2.setSpacing(5);
        hBox2.setPadding(new Insets(5));
        vBoxTop.getChildren().add(hBox2);
        HBox hBox3 = new HBox();
        Label lblDiaChi = new Label("Địa chỉ:");
        lblDiaChi.setMinWidth(50);
        txtDiaChi = new TextField();
        txtDiaChi.setMinWidth(300);
        Label lblDienThoai = new Label("Điện thoại:");
        lblDienThoai.setMinWidth(70);
        txtDienThoai = new TextField();
        txtDienThoai.setMinWidth(200);
        hBox3.getChildren().addAll(lblDiaChi, txtDiaChi, lblDienThoai, txtDienThoai);
        hBox3.setSpacing(5);
        hBox3.setPadding(new Insets(5));
        vBoxTop.getChildren().add(hBox3);
        layout.setTop(vBoxTop);

        VBox vbBoxCenter = new VBox();
        HBox hBoxTitile = new HBox();
        Label lblSTT = new Label("STT");
        lblSTT.setMinWidth(52);
        Label lblMSMatHang = new Label("MS Mặt hàng");
        lblMSMatHang.setMinWidth(102);
        Label lblTenMathang = new Label("Tên mặt hàng");
        lblTenMathang.setMinWidth(202);
        Label lblDVT = new Label("Đơn vị tính");
        lblDVT.setMinWidth(102);
        Label lblSoLuong = new Label("Số lượng");
        lblSoLuong.setMinWidth(102);
        Label lblDonGia = new Label("Đơn giá");
        lblDonGia.setMinWidth(102);
        Label lblTriGia = new Label("Trị giá");
        lblTriGia.setMinWidth(152);
        hBoxTitile.getChildren().addAll(lblSTT, lblMSMatHang, lblTenMathang, lblDVT, lblSoLuong, lblDonGia, lblTriGia);
        hBoxTitile.setPadding(new Insets(5));
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
        scrollPane = new ScrollPane(gridPane);
        HBox hBoxSummary = new HBox();
        Label lblTongCong = new Label("Tổng cộng:");
        lblTongCong.setMinWidth(100);
        txtTongCong = new TextField("0");
        txtTongCong.setMinWidth(100);
        hBoxSummary.getChildren().addAll(lblTongCong, txtTongCong);
        hBoxSummary.setAlignment(Pos.CENTER_RIGHT);
        hBoxSummary.setPadding(new Insets(5));
        vbBoxCenter.getChildren().addAll(hBoxTitile, scrollPane, hBoxSummary);
        layout.setCenter(vbBoxCenter);

        HBox cmdBtnGroup = new HBox();
        btnTaoMoi = new Button("Tạo Mới");
        btnTaoMoi.setMinWidth(100);
        btnTaoMoi.setDisable(true);
        btnThemChiTiet = new Button("Thêm chi tiết");
        btnThemChiTiet.setMinWidth(100);
        btnLuu = new Button("Lưu");
        btnLuu.setMinWidth(100);
        btnLuu.setDisable(true);
        btnDong = new Button("Đóng");
        btnDong.setMinWidth(100);
        btnDong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        cmdBtnGroup.getChildren().addAll(btnTaoMoi, btnThemChiTiet, btnLuu, btnDong);
        cmdBtnGroup.setSpacing(10);
        layout.setBottom(cmdBtnGroup);

        layout.setPadding(new Insets(10));
        stage.setScene(new Scene(layout, 850, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
