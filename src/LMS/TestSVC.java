package LMS;

import java.sql.*;

public class TestSVC {
    Connection con;

    //초기화 블록, sql드라이버 설치
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("sql 드라이버 설치 오류");
        }
    }

    //root계정 접속
    public void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/lms";
            con = DriverManager.getConnection(url, "root", "1111");
        } catch (SQLException se) {
            System.err.println("sql 계정 접속 오류");
        }
    }

    public Test checkTest(){
        Test test = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connect();
            stmt = con.createStatement();
            String sql = "select SUBNO from testinfo where SUBNO = 1";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String subno = rs.getString("SUBNO");
                test = new Test(subno);
                if(test.getSubno().equals("1")){
                    System.out.println("응시한 시험입니다.");
                }else {
                    System.out.println("dd"); //문제 출력
                }
            }
        } catch (SQLException se) {
            System.err.println("sql 데이터 조회 오류");
            //DB 종료 및 예외 확인
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (Exception e) {
                System.err.println("DB가 정상적으로 종료되지 않음");
                ;
            }
        }
        return test;//

    }
}