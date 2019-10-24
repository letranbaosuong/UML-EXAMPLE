package sample;

import javafx.scene.control.ComboBox;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class KetNoiCSDL {
    Connection conn = null;

    public KetNoiCSDL(String dbfileName) {
        String connectionString = "jdbc:sqlserver://localhost:1433;"
                + "database=" + dbfileName + ";"
                + "user=sa;"
                + "password=suong123;";
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (Exception e) {
            System.out.println("Loi sai jdbc: " + e + dbfileName);
        }
    }

    public static void main(String[] args) {
        new KetNoiCSDL("CSDLBH1");
    }

    public void DocKhachHang(ComboBox cboKhachHang) {

    }

    public ResultSet Tim_MSKhachHang(String mskh_luu) {
        return null;
    }

    public void Them_HoaDon(String shd, String ngayban, String mskh) {
        String query = "insert into HoaDon values (?,?,?)";
        final String fileName = "CSDLBH1"; // "CSDLBH1";
        KetNoiCSDL kncsdl = new KetNoiCSDL(fileName);
        PreparedStatement pstmt;
        try {
            pstmt = kncsdl.conn.prepareStatement(query);
            pstmt.setString(1, shd);
            pstmt.setDate(2, Date.valueOf(ngayban));
            pstmt.setString(3, mskh);

            int rs = pstmt.executeUpdate();
            System.out.println("them vao bang HoaDon + " + rs);
        } catch (SQLException e) {
            System.out.println("Loi Them_HoaDon(String shd, String ngayban, String mskh)");
        }
    }

    public void Them_ChiTietHoaDon(String shd, String msmh, float sl, float trigia) {
        String query = "insert into ChiTietHoaDon values (?,?,?,?)";
        final String fileName = "CSDLBH1"; // "CSDLBH1";
        KetNoiCSDL kncsdl = new KetNoiCSDL(fileName);
        PreparedStatement pstmt;
        try {
            pstmt = kncsdl.conn.prepareStatement(query);
            pstmt.setString(1, shd);
            pstmt.setString(2, msmh);
            pstmt.setInt(3, (int) sl);
            pstmt.setBigDecimal(4, BigDecimal.valueOf(trigia));

            int rs = pstmt.executeUpdate();
            System.out.println("them vao bang ChiTietHoaDon + " + rs);
        } catch (SQLException e) {
            System.out.println("Loi Them_ChiTietHoaDon(String shd, String msmh, float sl, float trigia)");
        }
    }

    public ResultSet Tim_SoHoaDon(String shd) {
        String query = "select SoHoaDon from HoaDon where SoHoaDon = '" + shd + "'";
        final String fileName = "CSDLBH1"; // "CSDLBH1";
        conn = (Connection) new KetNoiCSDL(fileName);
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Tim_SoHoaDon(String shd)");
        }
        return null;
    }

    public void DocMatHang(ComboBox<String> cboMSMatHang) {

    }

    public ResultSet Tim_MSMatHang(String MSMH) {
        String query = "select TenMatHang, DVT, DonGia from MatHang where MSMatHang = '" + MSMH + "'";
        final String fileName = "CSDLBH1"; // "CSDLBH1";
        conn = (Connection) new KetNoiCSDL(fileName);
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Loi Tim_MSMatHang()");
        }
        return null;
    }
}
