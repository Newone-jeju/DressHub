import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserData {
    private Connection connection;
    public void getConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection("jdbc:mysql://220.149.42.125:3306/preference?characterEncoding=utf-8","root","456111");
    }

    public HashMap getData(String userId) throws SQLException {
        HashMap hashMap = new HashMap();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from USERS where ID =?");
        preparedStatement.setString(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String sex = getSex(resultSet);
        String birth = resultSet.getString("BIRTH");
        String education = getEducation(resultSet);
        String job = getJob(resultSet);
        hashMap.put("sex", sex);
        hashMap.put("birth", birth);
        hashMap.put("education", education);
        hashMap.put("job", job);
        return hashMap;

    }

    private String getSex(ResultSet resultSet) throws SQLException {
        String sex = resultSet.getString("SEX");
        String result = null;
        if (sex.equals("남자")){result = "0";}
        else if(sex.equals("여자")){result="1";}
        return result;
    }

    private String getEducation(ResultSet resultSet) throws SQLException {
        String education = resultSet.getString("EDUCATION");
        String result = null;
        if(education.equals("고졸이하")) result = "0";
        else if(education.equals("대학생(휴학생포함)")) result = "1";
        else if(education.equals("대학졸업")) result = "2";
        else if(education.equals("대학원졸업 이상")) result = "3";
        return result;
    }

    private  String getJob(ResultSet resultSet) throws SQLException {
        String job = resultSet.getString("JOB");
        String result = null;
        if(job.equals("관리자")) result = "0";
        else if(job.equals("전문가 및 관련 종사자")) result = "1";
        else if(job.equals("사무종사자")) result = "2";
        else if(job.equals("서비스종사자")) result = "3";
        else if(job.equals("판매종사자")) result = "4";
        else if(job.equals("농림어업숙련 종사자" )) result = "5";
        else if(job.equals("기능원 및 관련 기능종사자")) result = "6";
        else if(job.equals("장치기계조작 및 조립종사자 : ")) result = "7";
        else if(job.equals("단순노무자")) result = "8";
        else if(job.equals("군인/공무원")) result = "9";
        else if(job.equals("학생")) result = "10";
        else if(job.equals("주부")) result = "11";
        else if(job.equals("무직(은퇴자 포함)")) result = "12";
        else result = "13";

        return result;
    }

}
