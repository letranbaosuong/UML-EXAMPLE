package com.dhpm11.tuan5;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

public class KetNoiCSDL {
    Connection conn = null;

    public KetNoiCSDL(String dbfilename) {
        String connectionString = "jdbc:sqlserver://localhost:1433;database=" + dbfilename + ";user=sa;password=suong123";
        try {
            conn = DriverManager.getConnection(connectionString);
//            System.out.println("Thanh cong");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Loi sai jdbc: " + e + " - " + dbfilename);
        }
    }

    public void Dong() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
    }

    public boolean Tim(String Khoa) {
        try {
            Statement stmt = conn.createStatement();

            String sql = "select * from NgauNhien where HoanVi = '" + Khoa + "'";

            ResultSet rs = stmt.executeQuery(sql);

            int i = 0;
            while (rs.next()) {
                i++;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void Them(String Khoa, Date dt) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:m:s", Locale.getDefault());
        String TemThoiGian = formatter.format(dt);
        System.out.println("TemThoiGian: " + TemThoiGian);
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO NgauNhien VALUES ('" + Khoa + "','" + TemThoiGian + "')";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        new KetNoiCSDL("MyData");
//    }
}
