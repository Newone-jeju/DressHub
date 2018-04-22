import javax.crypto.Cipher;
import javax.swing.*;
import java.sql.*;

public class DBSearcher {
    String[] userInput;
    static String[] sqlBlocks;
    static int[] blockInputCounts;

    private DBSearcher(String[] userInput) { this.userInput = userInput; }
    public static DBSearcher getInstance(String[] userInput) {
        blockInputCounts = new int[] {0, 1, 1, 1, 1, 1, 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 8};
        sqlBlocks = new String[] {
                "select SUM(한라산), SUM(오름), SUM(성산일출봉), SUM(섬), SUM(올레길), SUM(폭포), SUM(동굴), " +
                        "SUM(해수욕장), SUM(비자림), SUM(한라수목원), SUM(서귀포자연휴양림), SUM(절물자연후양림)" +
                        ", SUM(용두암), SUM(주상절리대), SUM(한림공원), SUM(국립제주방물관), SUM(도립미술관), " +
                        "SUM(민속자연사박물관), SUM(제주돌문화공원), SUM(제주세계자연유산센터), SUM(이중섭박물관)" +
                        ", SUM(서복전시관), SUM(제주43평화공원), SUM(동문시장), SUM(중앙로지하상가), SUM(바오젠거리)" +
                        ", SUM(제주오일장), SUM(서귀포매일올레시장), SUM(신라면세점), SUM(롯데면세점)" +
                        ", SUM(제주관광공사면세점), SUM(공항JDC면세점), SUM(제주목관아), SUM(항몽유적지)" +
                        ", SUM(성읍민속마을), SUM(삼양동선사유적), SUM(제주추사관), SUM(관덕정), SUM(이중섭거주지)" +
                        ", SUM(하멜기념비), SUM(미로공원), SUM(에코랜드), SUM(제주경마공원), SUM(불교사찰)" +
                        ", SUM(아쿠아플라넷), SUM(테디베어박물관), SUM(소인국테마파크), SUM(잠수함관광)" +
                        ", SUM(신비의도로), SUM(생각하는정원), SUM(드라마촬영지), SUM(제주별빛누리공원)" +
                        ", SUM(유람선), SUM(제주바다체험장), SUM(골프장), SUM(카지노)" +
                        "from trainingOutput " +
                        "JOIN trainingInput ON (trainingInput.key-18009) = trainingOutput.key\n" +
                        "WHERE ",
                "visitTimes=? ",
                "AND birthYear=? ",
                "AND transportaion=? ",
                "AND numOfPeople=? ",
                "AND mainDestination=? ",
                "AND alone=? AND couple=? AND family=? AND collegue=? AND friends=? AND amity=? AND othersCompanion=? ",

                "AND job=? ",
                "AND stayDuration=? ",
                "AND considerReason1=? ",
                "AND triptype=? ",
                "AND month=? ",

                "AND region=? ",

                "AND considerReason2=? ",
                "AND gender=? ",
                "AND education=? ",
                "AND infoGet=? ",
                "AND minor=? ",
                "AND hotel=? AND motel=? AND guestHouse=? AND pension=? AND resort=? AND friendsHouse=? AND noAccomodation=? AND otherAccomodation=? "
        };

        return new DBSearcher(userInput);
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://220.149.42.125:3306/preference?useUnicode=true&characterEncoding=euckr" ,
                "root", "456111");
        return connection;
    }

    public String getOthersSelect() throws SQLException, ClassNotFoundException {

        String result = searchOthers(19);

        return result;

    }

    private String searchOthers(int lastBlock) throws SQLException, ClassNotFoundException {
        String sql = getSQL(sqlBlocks, lastBlock);
        int blockInputCount = getBlockInputCount(lastBlock);

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 1; i <= blockInputCount; i++)
        {
            preparedStatement.setInt(i, Integer.parseInt(userInput[i-1]));
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData meta = resultSet.getMetaData();

//        String result = "";
        String maxField = getMaxField(resultSet, meta);

        if (maxField == null) return searchOthers(lastBlock-1);
//        else result = maxField.substring(4, maxField.length()-1);

        resultSet.close();
        preparedStatement.close();
        connection.close();
//        System.out.println("결과 : " + result);

        return maxField;
    }

    private String getMaxField(ResultSet resultSet, ResultSetMetaData meta) throws SQLException {
        String maxName = null;
        int max = 0;
        int count = meta.getColumnCount();

        while(resultSet.next())
        {
            for (int i = 1; i <= count; i++)
            {
                String name = meta.getColumnName(i);
                int num = resultSet.getInt(name);

                if (num > max)
                {
                    max = num;
//                    maxName = name;
                    maxName = name.substring(4, name.length()-1) + "@";
                }
                else if (num == max)
                {
//                    maxName += "*" + name;
                    maxName += name.substring(4, name.length()-1) + "@";
                }
            }

//            System.out.println("최대 : " + maxName + " -> " + max);
        }

        return maxName;
    }

    private int getBlockInputCount(int lastBlock)
    {
        int result = 0;

        for (int i = 0; i < lastBlock; i++) result += blockInputCounts[i];

        return result;
    }

    private String getSQL(String[] arrSQL, int lastBlock)
    {
        String result = "";

        for (int i = 0; i < lastBlock; i++)
        {
            result += arrSQL[i];
        }

        result += ";";

//        System.out.println(result);

        return result;
    }
}
