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

public class Main1 extends Application {

    TextField txtSoHoaDon, txtTenKhachHang, txtDiaChi, txtDienThoai, txtTongCong;
    Button btnTaoMoi, btnThemChiTiet, btnLuu, btnDong;
    DatePicker dpkNgayBan;
    ComboBox<String> cboMSKhachHang, cboMSMatHang = new ComboBox<>();
    GridPane gridPane;
    ScrollPane scrollPane;

    KetNoiCSDL kncsdl;
    final ObservableList optionsMSKH = FXCollections.observableArrayList();
    final ObservableList optionsMSMH = FXCollections.observableArrayList();
    List<TextField> txtSTTList = new ArrayList(), txtMSMatHangList = new ArrayList(), txtTenMatHangList = new ArrayList(), txtDVTList = new ArrayList(), txtSoLuongList = new ArrayList(), txtDonGiaList = new ArrayList(), txtTriGiaList = new ArrayList();
    int nRowsCT = 0;
    String MSKH_Luu = "";
    String MSMH_Luu = "";

    @Override
    public void start(Stage stage) throws Exception {
        // them item vao cboMSKhachHang
        fillcboMSKhachHang();
        fillcboMSMatHang();

        stage.setTitle("Nhập dữ liệu phiếu bán hàng");
        BorderPane layout = new BorderPane();

        VBox vBoxTop = new VBox();
        HBox hBox1 = new HBox();
        Label lblSoHoaDon = new Label("Số hóa đơn:");
        lblSoHoaDon.setMinWidth(70);
        txtSoHoaDon = new TextField();
        txtSoHoaDon.setMinWidth(100);
        txtSoHoaDon.setOnKeyReleased(e -> Kiem_Hop_Le());
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
        cboMSKhachHang.setItems(optionsMSKH);
        // su kien cboMSKhachHang
        cboMSKhachHang.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            // System.out.println(newValue);
            fillTenKH_DiaChi_SDT(newValue);
        });
        Label lblTenKhachHang = new Label("Tên khách hàng:");
        lblTenKhachHang.setMinWidth(100);
        txtTenKhachHang = new TextField();
        txtTenKhachHang.setMinWidth(400);
        txtTenKhachHang.setEditable(false);
        hBox2.getChildren().addAll(lblMSKhachHang, cboMSKhachHang, lblTenKhachHang, txtTenKhachHang);
        hBox2.setSpacing(5);
        hBox2.setPadding(new Insets(5));
        vBoxTop.getChildren().add(hBox2);
        HBox hBox3 = new HBox();
        Label lblDiaChi = new Label("Địa chỉ:");
        lblDiaChi.setMinWidth(50);
        txtDiaChi = new TextField();
        txtDiaChi.setMinWidth(300);
        txtDiaChi.setEditable(false);
        Label lblDienThoai = new Label("Điện thoại:");
        lblDienThoai.setMinWidth(70);
        txtDienThoai = new TextField();
        txtDienThoai.setMinWidth(200);
        txtDienThoai.setEditable(false);
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
        txtTongCong.setAlignment(Pos.CENTER_RIGHT);
        txtTongCong.setEditable(false);
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
        cmdBtnGroup.getChildren().addAll(btnTaoMoi, btnThemChiTiet, btnLuu, btnDong);
        cmdBtnGroup.setSpacing(10);
        layout.setBottom(cmdBtnGroup);

        layout.setPadding(new Insets(10));
        stage.setScene(new Scene(layout, 850, 300));
        stage.show();
        KetNoiCSDL();

        btnTaoMoi.setOnAction(e -> TaoMoi());
        btnThemChiTiet.setOnAction(e -> Them_ChiTiet());
        btnLuu.setOnAction(e -> Luu_ToanBo());
        btnDong.setOnAction(e -> stage.close());
    }

    public void setCboMSMH_Click(String s) {
        String id = s.substring(5);
        System.out.println(s + " - " + id);
        MSMH_Luu = cboMSMatHang.getSelectionModel().getSelectedItem().trim();
        System.out.println(MSMH_Luu);
        ResultSet rs = get_TenMH_DVT_DonGia(MSMH_Luu);
        try {
            while (rs.next()) {
//                System.out.println(rs.getString("TenMatHang"));
                txtMSMatHangList.get(Integer.parseInt(id)).setText(MSMH_Luu);
                txtTenMatHangList.get(Integer.parseInt(id)).setText(rs.getString("TenMatHang"));
                txtDVTList.get(Integer.parseInt(id)).setText(rs.getString("DVT"));
                txtDonGiaList.get(Integer.parseInt(id)).setText(rs.getString("DonGia"));
            }
        } catch (SQLException e) {
            System.out.println("Loi setCboMSMH_Click(String s)");
        }
        Kiem_Hop_Le();
    }

    public void focusLost(ComboBox cbo) {
        String id = cbo.getId().substring(5);
        gridPane.getChildren().remove(cbo);
        Kiem_Hop_Le();
    }

    public void MSMH_MouseClick(String s) {
        String id = s.substring(2);
        int row = Integer.parseInt(id);
        cboMSMatHang.setId("cboMH" + id);
        cboMSMatHang.setOnAction(e -> setCboMSMH_Click(cboMSMatHang.getId()));
        cboMSMatHang.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.booleanValue()) {
//                    focusGained(cboMSMatHang);
                } else {
                    focusLost(cboMSMatHang);
                }
            }
        });
        gridPane.add(cboMSMatHang, 1, row);
    }

    private ResultSet get_TenMH_DVT_DonGia(String MSMH) {
        String query = "select TenMatHang, DVT, DonGia from MatHang where MSMatHang = '" + MSMH + "'";
        final String fileName = "CSDLBH1"; // "CSDLBH1";
        kncsdl = new KetNoiCSDL(fileName);
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = kncsdl.conn.prepareStatement(query);
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Loi fillcboMSMatHang()");
        }
        return null;
    }

    public void Them_ChiTiet() {
        TextField txt;
        txt = new TextField();
        txtSTTList.add(txt);
        txt.setPrefWidth(50);
        gridPane.add(txt, 0, nRowsCT);
        TextField txtMSMH = new TextField();
        txtMSMatHangList.add(txtMSMH);
        txtMSMH.setPrefWidth(100);
        txtMSMH.setId("MH" + nRowsCT);
        txtMSMH.setOnMouseClicked(e -> MSMH_MouseClick(txtMSMH.getId()));
        gridPane.add(txtMSMH, 1, nRowsCT);
        TextField txtTenMH = new TextField();
        txtTenMatHangList.add(txtTenMH);
        txtTenMH.setPrefWidth(200);
        txtTenMH.setEditable(false);
        gridPane.add(txtTenMH, 2, nRowsCT);
        TextField txtDVT = new TextField();
        txtDVTList.add(txtDVT);
        txtDVT.setPrefWidth(100);
        txtDVT.setEditable(false);
        gridPane.add(txtDVT, 3, nRowsCT);
        TextField txtSoLuong = new TextField();
        txtSoLuongList.add(txtSoLuong);
        txtSoLuong.setPrefWidth(100);
        gridPane.add(txtSoLuong, 4, nRowsCT);
        txtSoLuong.setOnKeyReleased(e -> TriGia_Change());
        TextField txtDonGia = new TextField();
        txtDonGiaList.add(txtDonGia);
        txtDonGia.setPrefWidth(100);
        txtDonGia.setEditable(false);
        gridPane.add(txtDonGia, 5, nRowsCT);
        txtDonGia.setOnKeyReleased(e -> TriGia_Change());
        TextField txtTriGia = new TextField();
        txtTriGiaList.add(txtTriGia);
        txtTriGia.setPrefWidth(150);
        txtTriGia.setAlignment(Pos.CENTER_RIGHT);
        txtTriGia.setEditable(false);
        gridPane.add(txtTriGia, 6, nRowsCT);
        nRowsCT++;
        txtSTTList.get(nRowsCT - 1).setText(String.valueOf(nRowsCT));
        scrollPane.setVvalue(1);
        // do du lieu vao bang
        cboMSMatHang.setItems(optionsMSMH);
//        cboMSMatHang.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
//            try {
//                ResultSet rs = get_TenMH_DVT_DonGia(newValue);
//                txtMSMH.setText(newValue);
//                while (rs.next()) {
//                    txtTenMH.setText(rs.getString("TenMatHang"));
//                    txtDVT.setText(rs.getString("DVT"));
//                    txtDonGia.setText(String.valueOf(rs.getInt("DonGia")));
//                }
//            } catch (SQLException e) {
//                System.out.println("Loi get_TenMH_DVT_DonGia() == null");
//            }
//        });
        Kiem_Hop_Le();
    }

    public void KetNoiCSDL() {
        final String fileName = "CSDLBH1"; // "MyData";
        kncsdl = new KetNoiCSDL(fileName);
        try {
            kncsdl.DocKhachHang(cboMSKhachHang);
        } catch (Exception e) {
            System.out.println("Không thể đọc bảng khách hàng");
        }
        try {
            kncsdl.DocMatHang(cboMSMatHang);
        } catch (Exception e) {
            System.out.println("Không thể đọc bảng mặt hàng");
        }
    }

    public void Luu_ToanBo() {
        try {
            kncsdl.Them_HoaDon(txtSoHoaDon.getText(), dpkNgayBan.getValue().toString(), MSKH_Luu);
            for (int i = 0; i < nRowsCT; i++) {
                //System.out.println(txtMSMatHangList.get(i).getId());
                // System.out.println(txtTriGiaList.get(i).getText());
                kncsdl.Them_ChiTietHoaDon(txtSoHoaDon.getText(), txtMSMatHangList.get(i).getText(),
                        Float.parseFloat(txtSoLuongList.get(i).getText()),
                        Float.parseFloat(txtTriGiaList.get(i).getText().replace(".", "")));
            }
            btnLuu.setDisable(true);
            btnTaoMoi.setDisable(false);
        } catch (Exception e) {
            System.out.println(e.getMessage() + ". Cần chuyển sang Region Settings là Việt Nam");
        }
    }

    public void TaoMoi() {
        if (nRowsCT > 0) {
            gridPane.getChildren().clear();
            txtSTTList.clear();
            txtMSMatHangList.clear();
            txtTenMatHangList.clear();
            txtDVTList.clear();
            txtSoLuongList.clear();
            txtDonGiaList.clear();
            txtTriGiaList.clear();
            nRowsCT = 0;
            txtSoHoaDon.setText(String.format("%08d", Integer.parseInt(txtSoHoaDon.getText()) + 1));
            btnTaoMoi.setDisable(true);
            TriGia_Change(); // Lệnh mới thêm (30/09/2017)
        }
    }

    public void TriGia_Change() {
        float TongTriGia = 0;
        for (int i = 0; i < nRowsCT; i++) {
            float SoLuong = MyLib.getFloat(txtSoLuongList.get(i).getText());
            float DonGia = MyLib.getFloat(txtDonGiaList.get(i).getText());
            float TriGia = SoLuong * DonGia;
            txtTriGiaList.get(i).setText(String.format("%,12.0f", TriGia));
            TongTriGia += TriGia;
        }
        txtTongCong.setText(String.format("%,12.0f", TongTriGia));
        Kiem_Hop_Le();
    }

    public void Kiem_Hop_Le() {
        boolean HopLe = SHD_HopLe_LucThem() && MSKH_HopLe() && nRowsCT > 0 && MSMH_HopLe() && SL_HopLe();
        System.out.println("HopLe : " + HopLe);
        System.out.println("SHD_HopLe_LucThem() : " + SHD_HopLe_LucThem());
        System.out.println("MSKH_HopLe() : " + MSKH_HopLe());
        System.out.println("nRowsCT > 0 : " + (nRowsCT > 0));
        System.out.println("MSMH_HopLe() : " + MSMH_HopLe());
        System.out.println("SL_HopLe() : " + SL_HopLe());
        btnLuu.setDisable(!HopLe);
    }

    public boolean SL_HopLe() {
        boolean HopLe = true;
        for (int i = 0; i < nRowsCT; i++) {
            HopLe &= MyLib.isInt(txtSoLuongList.get(i).getText());
        }
        return HopLe;
    }

    public boolean SHD_HopLe_LucThem() {
        boolean HopLe = false;
        String SHD = txtSoHoaDon.getText().trim();
        String query = "select SoHoaDon from HoaDon where SoHoaDon = '" + SHD + "'";
        final String fileName = "CSDLBH1"; // "CSDLBH1";
        kncsdl = new KetNoiCSDL(fileName);
        PreparedStatement pst;
        ResultSet rs;
        String tam = "";
        try {
            pst = kncsdl.conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                tam = rs.getString("SoHoaDon");
//                HopLe &= (rs == null); // Không tìm thấy
//                return HopLe;
            }
            if (!tam.equals(SHD) && !SHD.equals("")) HopLe = true;
            else HopLe = false;
            pst.close();
            rs.close();

            return HopLe;
        } catch (SQLException e) {
            System.out.println("SHD_HopLe_LucThem()");
            return false;
        }
    }

    public boolean MSMH_HopLe() {
        boolean HopLe = true;
        List<String> MSMHList = new ArrayList<>();
        for (int i = 0; i < nRowsCT; i++) {
            String MSMH = txtMSMatHangList.get(i).getText();
            HopLe &= !MSMH.equals("");
            HopLe &= !MyLib.isInList(MSMH, MSMHList);
            MSMHList.add(MSMH);
        }
        return HopLe;
    }

    public boolean MSKH_HopLe() {
        return !MSKH_Luu.equals("");
    }

    private void fillcboMSMatHang() {
        String query = "select MSMatHang from MatHang";
        final String fileName = "CSDLBH1"; // "CSDLBH1";
        kncsdl = new KetNoiCSDL(fileName);
        try {
            PreparedStatement pst = kncsdl.conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                optionsMSMH.add(rs.getString("MSMatHang"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Loi fillcboMSMatHang()");
        }
    }

    private void fillTenKH_DiaChi_SDT(Object maKH) {
        String query = "select TenKhachHang, DiaChi, DienThoai from KhachHang where MSKhachHang = '" + maKH + "'";
        MSKH_Luu = maKH.toString().trim();
        System.out.println(MSKH_Luu);
        final String fileName = "CSDLBH1"; // "CSDLBH1";
        kncsdl = new KetNoiCSDL(fileName);
        try {
            PreparedStatement pst = kncsdl.conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                txtTenKhachHang.setText(rs.getString("TenKhachHang"));
                txtDiaChi.setText(rs.getString("DiaChi"));
                txtDienThoai.setText(rs.getString("DienThoai"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Loi fillTenKH_DiaChi_SDT()");
        }
    }

    public void fillcboMSKhachHang() {
        String query = "select MSKhachHang from KhachHang";
        final String fileName = "CSDLBH1"; // "CSDLBH1";
        kncsdl = new KetNoiCSDL(fileName);
        try {
            PreparedStatement pst = kncsdl.conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                optionsMSKH.add(rs.getString("MSKhachHang"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Loi fillcboMSKhachHang()");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
