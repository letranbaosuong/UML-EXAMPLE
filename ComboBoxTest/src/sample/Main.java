package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    KetNoiCSDL kncsdl = new KetNoiCSDL("CSDLBH1");
    ComboBox<String> cboKhachHang;
    ComboBox<String> cboMatHang;
    TextField txtSoHoaDon, txtTenKhachHang, txtDiaChi, txtDienThoai, txtTongTriGia;
    DatePicker dpkNgayBan;
    GridPane gridPane;
    ScrollPane scrollPane;
    Button btnNew, btnAddCT, btnSave, btnClose;
    int nRowsCT = 0;
    List<TextField> txtSTTList = new ArrayList(), txtMSMatHangList = new ArrayList(), txtTenMatHangList = new ArrayList(), txtDVTlist = new ArrayList(), txtSoLuongList = new ArrayList(), txtDonGiaList = new ArrayList(), txtTriGiaList = new ArrayList();
    String MSKH_Luu = "";

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        primaryStage.setTitle("Nhập dữ liệu phiếu bán hàng");
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        VBox vBoxTop = new VBox();
        HBox hBox1 = new HBox();
        Label lblSoHoaDon = new Label("Số hóa đơn");
        txtSoHoaDon = new TextField();
//        txtSoHoaDon.setOnKeyReleased(e -> Kiem_Hop_Le());
        Label lblNgayBan = new Label("Ngày bán");
        dpkNgayBan = new DatePicker(LocalDate.now());
        hBox1.getChildren().addAll(lblSoHoaDon, txtSoHoaDon, lblNgayBan, dpkNgayBan);
        hBox1.setSpacing(10);
        HBox hBox2 = new HBox();
        Label lblMSKhachHang = new Label("Mã số khách hàng:");
        cboKhachHang = new ComboBox();
        cboKhachHang.setMinWidth(100);
        Label lblTenKhachHang = new Label("Tên khách hàng");
        txtTenKhachHang = new TextField();
        txtTenKhachHang.setMinWidth(300);
        txtTenKhachHang.setEditable(false);
        hBox2.getChildren().addAll(lblMSKhachHang, cboKhachHang, lblTenKhachHang, txtTenKhachHang);
        hBox2.setSpacing(10);
        HBox hBox3 = new HBox();
        Label lblDiaChi = new Label("Địa chỉ:");
        txtDiaChi = new TextField();
        txtDiaChi.setMinWidth(300);
        txtDiaChi.setEditable(false);
        Label lblDienThoai = new Label("Điện thoại");
        txtDienThoai = new TextField();
        txtDienThoai.setEditable(false);
        hBox3.getChildren().addAll(lblDiaChi, txtDiaChi, lblDienThoai, txtDienThoai);
        vBoxTop.getChildren().addAll(hBox1/*, hBox2, hBox3*/);
        layout.setTop(vBoxTop);
        VBox vBoxCenter = new VBox();
        HBox hBoxTitle = new HBox();
        hBoxTitle.setMaxWidth(820);
        Label lblSTT = new Label("STT");
        lblSTT.setMinWidth(50);
        Label lblMSMatHang = new Label("MS Mặt hàng");
        lblMSMatHang.setMinWidth(100);
        Label lblTenMatHang = new Label("Tên mặt hàng");
        lblTenMatHang.setMinWidth(100);
        Label lblDVT = new Label("Đơn vị tính");
        lblDVT.setMinWidth(100);
        Label lblSoLuong = new Label("Số lượng");
        lblSoLuong.setMinWidth(100);
        Label lblDonGia = new Label("Đơn giá");
        lblSoLuong.setMinWidth(100);
        Label lblTriGia = new Label("Trị giá");
        lblSoLuong.setMinWidth(100);
        hBoxTitle.getChildren().addAll(lblSTT, lblMSMatHang, lblTenMatHang, lblDVT, lblSoLuong, lblDonGia, lblTriGia);
        hBoxTitle.setSpacing(10);
        gridPane = new GridPane();
        scrollPane = new ScrollPane(gridPane);
        scrollPane.setMaxWidth(820);
        HBox hBoxSummary = new HBox();
        hBoxSummary.setMaxWidth(820);
        Label lblTongTriGia = new Label();
        lblTongTriGia.setMinWidth(100);
        txtTongTriGia = new TextField("0");
        txtTongTriGia.setAlignment(Pos.CENTER_RIGHT);
        hBoxSummary.getChildren().addAll(lblTriGia, txtTongTriGia);
        hBoxSummary.setAlignment(Pos.CENTER_RIGHT);
        hBoxSummary.setPadding(new Insets(10, 10, 10, 10));
        vBoxCenter.getChildren().addAll(hBoxTitle, scrollPane, hBoxSummary);
        layout.setCenter(vBoxCenter);
        HBox cmdBtnGroup = new HBox();
        btnNew = new Button("Tạo mới");
        btnNew.setDisable(true);
        btnAddCT = new Button("Thêm Chi Tiết");
        btnSave = new Button("Lưu");
        btnSave.setDisable(true);
        btnSave.setMinWidth(100);
        btnClose = new Button("Đóng");
        btnClose.setMinWidth(100);
        cmdBtnGroup.getChildren().addAll(btnNew, btnAddCT, btnSave, btnClose);
        cmdBtnGroup.setSpacing(10);
        layout.setBottom(cmdBtnGroup);
        layout.setPadding(new Insets(10));
        primaryStage.setScene(new Scene(layout, 850, 300));
        primaryStage.show();
//        btnNew.setOnAction(e -> TaoMoi());
//        btnAddCT.setOnAction(e -> Them_ChiTiet());
//        btnSave.setOnAction(e -> Luu_ToanBo());
        btnClose.setOnAction(e -> primaryStage.close());
        cboMatHang = new ComboBox();
        cboMatHang.setMinWidth(100);
        KetNoiCSDL kncsdl;
    }

//    private void Them_ChiTiet() {
//        TextField txt;
//        txt = new TextField();
//        txtSTTList.add(txt);
//        txt.setPrefWidth(50);
//        gridPane.add(txt, 0, nRowsCT);
//        TextField txtMSMH = new TextField();
//        txtTenMatHangList.add(txtMSMH);
//        txtMSMH.setPrefWidth(100);
//        txtMSMH.setId("MH" + nRowsCT);
//        txtMSMH.setOnMouseClicked(e -> MSMH_MouseClick(txtMSMH.getId()));
//        gridPane.add(txtMSMH, 1, nRowsCT);
//        TextField txtTenMH = new TextField();
//        txtTenMatHangList.add(txtTenMH);
//        txtTenMH.setPrefWidth(200);
//        txtTenMH.setEditable(false);
//        gridPane.add(txtTenMH, 2, nRowsCT);
//        TextField txtDVT = new TextField();
//        txtDVTlist.add(txtDVT);
//        txtDVT.setPrefWidth(100);
//        txtDVT.setEditable(false);
//        gridPane.add(txtDVT, 3, nRowsCT);
//        TextField txtSoLuong = new TextField();
//        txtSoLuongList.add(txtSoLuong);
//        txtSoLuong.setPrefWidth(100);
//        gridPane.add(txtSoLuong, 4, nRowsCT);
//        txtSoLuong.setOnKeyReleased(e -> TriGia_Change());
//        TextField txtDonGia = new TextField();
//        txtDonGiaList.add(txtDonGia);
//        txtDonGia.setPrefWidth(100);
//        txtDonGia.setEditable(false);
//        gridPane.add(txtDonGia, 5, nRowsCT);
//        txtDonGia.setOnKeyReleased(e -> TriGia_Change());
//        TextField txtTriGia = new TextField();
//        txtTriGiaList.add(txtTriGia);
//        txtTriGia.setPrefWidth(150);
//        txtTriGia.setAlignment(Pos.CENTER_RIGHT);
//        txtTriGia.setEditable(false);
//        gridPane.add(txtTriGia, 6, nRowsCT);
//        nRowsCT++;
//        txtSTTList.get(nRowsCT - 1).setText(String.valueOf(nRowsCT));
//        scrollPane.setVvalue(1);
//        Kiem_Hop_Le();
//    }
//
//    private void TriGia_Change() {
//        float TongTriGia = 0;
//        for (int i = 0; i < nRowsCT; i++) {
////            float SoLuong = MyLib.getFloat(txtSoLuongList.get(i).getText());
////            float DonGia = MyLib.getFloat(txtDonGiaList.get(i).getText());
//            float SoLuong = MyLib.getInt(txtSoLuongList.get(i).getText());
//            float DonGia = MyLib.getInt(txtDonGiaList.get(i).getText());
//            float TriGia = SoLuong * DonGia;
//            txtTriGiaList.get(i).setText(String.format("%,12.0f", TriGia));
//            TongTriGia += TriGia;
//        }
//        txtTongTriGia.setText(String.format("%,12.0f", TongTriGia));
//        Kiem_Hop_Le();
//    }
//
//    public void KetNoiCSDL() {
//        final String fileName = "CSDLBH1"; // "CSDLBH1";
//        kncsdl = new KetNoiCSDL(fileName);
//        try {
//            kncsdl.DocKhachHang(cboKhachHang);
//        } catch (Exception e) {
//            System.out.println("Không thể đọc bảng khách hàng");
//        }
//        try {
//            kncsdl.DocMatHang(cboMatHang);
//        } catch (Exception e) {
//            System.out.println("Không thể đọc bảng mặt hàng");
//        }
//    }
//
//    public void cboKhacHang_Click() {
//        try {
//            MSKH_Luu = cboKhachHang.getSelectionModel().getSelectedItem().toString();
//            ResultSet rs = kncsdl.Tim_MSKhachHang(MSKH_Luu);
//            if (rs != null) {
//                // System.out.println(rs.getString("TenKhachHang"));
//                txtTenKhachHang.setText(rs.getString("TenKhachHang"));
//                txtDiaChi.setText(rs.getString("DiaChi"));
//                txtDienThoai.setText(rs.getString("DienThoai"));
//            }
//        } catch (Exception e) {
//        }
//        Kiem_Hop_Le();
//    }
//
//    public void setCboMSMH_Click(String s) {
//        try {
//            MSKH_Luu = cboKhachHang.getSelectionModel().getSelectedItem().toString();
//            ResultSet rs = kncsdl.Tim_MSKhachHang(MSKH_Luu);
//            if (rs != null) {
//                // System.out.println(rs.getString("TenKhachHang"));
//                txtTenKhachHang.setText(rs.getString("TenKhachHang"));
//                txtDiaChi.setText(rs.getString("DiaChi"));
//                txtDienThoai.setText(rs.getString("DienThoai"));
//            }
//        } catch (Exception e) {
//        }
//        Kiem_Hop_Le();
//    }
//
//    private void Kiem_Hop_Le() {
//        boolean HopLe = SHD_HopLe_LucThem() && MSKH_HopLe() && nRowsCT > 0 && MSMH_HopLe() && SL_HopLe();
//        btnSave.setDisable(!HopLe);
//    }
//
//    private boolean SL_HopLe() {
//        boolean HopLe = true;
//        for (int i = 0; i < nRowsCT; i++) {
//            HopLe &= MyLib.isInt(txtSoLuongList.get(i).getText());
//        }
//        return HopLe;
//    }
//
//    private boolean MSMH_HopLe() {
//        boolean HopLe = true;
//        List<String> MSMHList = new ArrayList<>();
//        for (int i = 0; i < nRowsCT; i++) {
//            String MSMH = txtMSMatHangList.get(i).getText();
//            HopLe &= !MSMH.equals("");
//            HopLe &= !MyLib.isInList(MSMH, MSMHList);
//            MSMHList.add(MSMH);
//        }
//        return HopLe;
//    }
//
//    private boolean MSKH_HopLe() {
//        return !MSKH_Luu.equals("");
//    }
//
//    private boolean SHD_HopLe_LucThem() {
//        String SHD = txtSoHoaDon.getText();
//        try {
//            ResultSet rs = kncsdl.Tim_SoHoaDon(SHD);
//            boolean HopLe = (!SHD.equals("")) && (rs == null); // Không tìm thấy
//            return HopLe;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private void Luu_ToanBo() {
//        try {
//            kncsdl.Them_HoaDon(txtSoHoaDon.getText(), dpkNgayBan.getValue().toString(), MSKH_Luu);
//            for (int i = 0; i < nRowsCT; i++) {
//                //System.out.println(txtMSMatHangList.get(i).getId());
//                //System.out.println(txtTriGiaList.get(i).getText());
//                kncsdl.Them_ChiTietHoaDon(txtSoHoaDon.getText(), txtMSMatHangList.get(i).getText(),
//                        Float.parseFloat(txtSoLuongList.get(i).getText()),
//                        Float.parseFloat(txtTriGiaList.get(i).getText().replace(".", "")));
//            }
//            btnSave.setDisable(true);
//            btnNew.setDisable(false);
//        } catch (Exception e) {
//            System.out.println(e.getMessage() + ". Cần chuyển sang Region Settings là Việt Nam");
//        }
//    }
//
//    private void TaoMoi() {
//        if (nRowsCT > 0) {
//            gridPane.getChildren().clear();
//            txtSTTList.clear();
//            txtMSMatHangList.clear();
//            txtTenMatHangList.clear();
//            txtDVTlist.clear();
//            txtSoLuongList.clear();
//            txtDonGiaList.clear();
//            txtTriGiaList.clear();
//            nRowsCT = 0;
//            txtSoHoaDon.setText(String.format("%08d", Integer.parseInt(txtSoHoaDon.getText()) + 1));
//            btnNew.setDisable(true);
//            TriGia_Change(); // Lệnh mới thêm (30/09/2017)
//        }
//    }
//
//    public void MSMH_MouseClick(String s) {
//        String id = s.substring(2);
//        int row = Integer.parseInt(id);
//        cboMatHang.setId("cboMH" + id);
//        cboMatHang.setOnAction(e -> setCboMSMH_Click(cboMatHang.getId()));
//        cboMatHang.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                if (newValue.booleanValue()) {
////                    focusGained(cboMatHang);
//                    focusLost(cboMatHang);
//                } else {
//                    focusLost(cboMatHang);
//                }
//            }
//        });
//        gridPane.add(cboMatHang, 1, row);
//    }
//
//    private void focusLost(ComboBox cbo) {
//        String id = cbo.getId().substring(5);
//        gridPane.getChildren().remove(cbo);
//        Kiem_Hop_Le();
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
