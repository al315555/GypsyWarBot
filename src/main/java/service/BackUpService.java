package service;

import constant.Constants;
import data.specific.Member;
import persistence.DatabaseConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BackUpService {

    private static final String INSERT_INTO_DEAD_MEMBERS = "INSERT INTO DEAD_MEMBERS(id, member_name, kills) VALUES (?,?,?)";
    private static final String INSERT_INTO_ALIVE_MEMBERS = "INSERT INTO ALIVE_MEMBERS(id, member_name, kills) VALUES (?,?,?)";
    private static final String INSERT_INTO_BODY_TABLE = "INSERT INTO BODY_TABLE(html_body_table) VALUES (?)";
    private static final String INSERT_INTO_BACKUP_EVENTS = "INSERT INTO BACKUP_EVENTS(reset_date, aliveMembers, deadMembers, html_body_table) VALUES (CURRENT_TIMESTAMP     , ?, ?, ?)";
    private static final String DELETE_FROM_BODY_TABLE = "DELETE FROM BODY_TABLE";
    private static final String DELETE_FROM_ALIVE_MEMBERS = "DELETE FROM ALIVE_MEMBERS";
    private static final String DELETE_FROM_DEAD_MEMBERS = "DELETE FROM DEAD_MEMBERS";
    private static final String QUERY_FROM_DEAD_MEMBERS = "SELECT * FROM DEAD_MEMBERS ORDER BY id";
    private static final String QUERY_FROM_ALIVE_MEMBERS = "SELECT * FROM ALIVE_MEMBERS ORDER BY id";
    private static final String QUERY_FROM_BODY_TABLE = "SELECT * FROM BODY_TABLE";
    private static final String QUERY_FROM_BACKUP_EVENTS = "SELECT * FROM BACKUP_EVENTS ORDER BY reset_date DESC";

    public static void toRetrieveData(Map<String,Object> dicToRetrieve){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        String sql = null;
        try {
            final ArrayList<Member> aliveList = new ArrayList<Member>();//(ArrayList<Member>) dicToRetrieve.get(Constants.ALIVES);
            final ArrayList<Member> deadList = new ArrayList<Member>();//(ArrayList<Member>) dicToRetrieve.get(Constants.DEADS);
            final StringBuilder deadString = new StringBuilder();//String) dicToRetrieve.get(Constants.STRING_DEADS);
            final StringBuilder aliveString = new StringBuilder();//(String) dicToRetrieve.get(Constants.STRING_ALIVES);
            String bodyTableHTML = null ;//new StringBuilder();//(String) dicToRetrieve.get(Constants.HTML_BODY_TABLE);
            String historicalTableHTML = null ;//new StringBuilder();//(String) dicToRetrieve.get(Constants.HTML_BODY_TABLE);
            connection = DatabaseConnection.getInstance();

            sql =  QUERY_FROM_DEAD_MEMBERS;
            ps = connection.prepareStatement(sql);
            //query execution
            rs = ps.executeQuery();
            fillList(deadList, rs, deadString);

            sql =  QUERY_FROM_ALIVE_MEMBERS;
            ps = connection.prepareStatement(sql);
            //query execution
            rs = ps.executeQuery();
            fillList(aliveList, rs, aliveString);

            sql =  QUERY_FROM_BODY_TABLE;
            ps = connection.prepareStatement(sql);
            //query execution
            rs = ps.executeQuery();
            //get the result
            while (rs.next()) {
                bodyTableHTML = rs.getString("html_body_table");
            }

            sql =  QUERY_FROM_BACKUP_EVENTS;
            ps = connection.prepareStatement(sql);
            //query execution
            rs = ps.executeQuery();
            //get the result
            SimpleDateFormat formatSimple = new SimpleDateFormat(Constants.DATE_HOUR_FORMAT);
            StringBuilder stringBuilder = new StringBuilder();
            while (rs.next()) {
                Calendar reset_date = Calendar.getInstance();
                reset_date.setTime(rs.getTimestamp("reset_date"));
                String dateFormatted = formatSimple.format(reset_date.getTime());
                String aliveMembers = rs.getString("aliveMembers");
                String deadMembers = rs.getString("deadMembers");
                String html_body_table = rs.getString("html_body_table");
                stringBuilder.append("<div style=\"width: 100%%;\"><h2>"+dateFormatted+"</h2><table cellspacing=\"0\" cellpadding=\"1\" width:\"95%%\" style=\"color:black;background-color:white; font-size:1em;\">"+html_body_table+"</table></div>");
            }

            historicalTableHTML = stringBuilder.toString();

            dicToRetrieve.put(Constants.HTML_BODY_HISTORICAL, historicalTableHTML);
            dicToRetrieve.put(Constants.STRING_ALIVES, aliveString.toString());
            dicToRetrieve.put(Constants.STRING_DEADS, deadString.toString());
            dicToRetrieve.put(Constants.ALIVES, aliveList);
            dicToRetrieve.put(Constants.DEADS, deadList);
            dicToRetrieve.put(Constants.HTML_BODY_TABLE, bodyTableHTML);

        } catch (Exception ex) {
            ex.printStackTrace();
            //throw ex;
        } finally {
            try {
                if (connection != null)
                    connection.close();
            }catch(Exception ex) {
                ex.printStackTrace();
                //throw ex;
            }
        }
        //return
    }

    private static void fillList(final ArrayList<Member> list, final ResultSet rs, final StringBuilder labelString) throws SQLException {
        while (rs.next()) {
            final int id = rs.getInt("id");
            final String name = rs.getString("member_name");
            final int kills = rs.getInt("kills");
            Member member = new Member(id, name );
            member.setKills(kills);
            list.add(member);
            labelString.append(name).append(",");
        }
        /*if(labelString.length() > 1) {
            labelString.replace(labelString.length() - 1, labelString.length(), "");
        }*/
    }

    public static void toBackUpData(Map<String,Object> dicToSave){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer rsInt = null;
        Connection connection = null;
        String sql = null;
        try {
            final ArrayList<Member> alivelist = (ArrayList<Member>) dicToSave.get(Constants.ALIVES);
            final ArrayList<Member> deadlist = (ArrayList<Member>) dicToSave.get(Constants.DEADS);
            /*final String deadString = (String) dicToSave.get(Constants.STRING_DEADS);
            final String aliveString = (String) dicToSave.get(Constants.STRING_ALIVES);*/
            final String bodyTableHTML = (String) dicToSave.get(Constants.HTML_BODY_TABLE);
            connection = DatabaseConnection.getInstance();

            deleteTables(connection);

            sql =  INSERT_INTO_BODY_TABLE;
            ps = connection.prepareStatement(sql);
            ps.setString(1, bodyTableHTML);
            //query execution
            rsInt = ps.executeUpdate();

            for(final Member l_member : alivelist){
                sql =  INSERT_INTO_ALIVE_MEMBERS;
                ps = connection.prepareStatement(sql);
                ps.setInt(1, l_member.getDistinctCode());
                ps.setString(2, l_member.getName());
                ps.setInt(3, l_member.getKills());
                //query execution
                rsInt = ps.executeUpdate();
            }

            for(final Member l_member :  deadlist){
                sql =  INSERT_INTO_DEAD_MEMBERS;
                ps = connection.prepareStatement(sql);
                ps.setInt(1, l_member.getDistinctCode());
                ps.setString(2, l_member.getName());
                ps.setInt(3, l_member.getKills());
                //query execution
                rsInt = ps.executeUpdate();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            // throw ex;
        } finally {
            try {
                if (connection != null)
                    connection.close();
            }catch(Exception ex) {
                ex.printStackTrace();
                // throw ex;
            }
        }
        // return
    }

    private static void deleteTables(final Connection connection) throws SQLException {
        String sql = null;
        PreparedStatement ps = null;
        Integer rsInt = null;

        sql =  DELETE_FROM_BODY_TABLE;
        ps = connection.prepareStatement(sql);
        //query execution
        rsInt = ps.executeUpdate();

        sql =  DELETE_FROM_ALIVE_MEMBERS;
        ps = connection.prepareStatement(sql);
        //query execution
        rsInt = ps.executeUpdate();

        sql =  DELETE_FROM_DEAD_MEMBERS;
        ps = connection.prepareStatement(sql);
        //query execution
        rsInt = ps.executeUpdate();

    }

    public static void toBackUpEvents(){
        PreparedStatement ps = null;
        Map<String,Object> dicToSave;
        ResultSet rs = null;
        Integer rsInt = null;
        Connection connection = null;
        String sql = null;
        try {
            final HashMap<String, Object> backup = new HashMap<>();
            toRetrieveData(backup);
            final ArrayList<Member> alivelist = (ArrayList<Member>) backup.get(Constants.ALIVES);
            final ArrayList<Member> deadlist = (ArrayList<Member>) backup.get(Constants.DEADS);
            final String deadString = (String) backup.get(Constants.STRING_DEADS);
            final String aliveString = (String) backup.get(Constants.STRING_ALIVES);
            final String bodyTableHTML = (String) backup.get(Constants.HTML_BODY_TABLE);
            connection = DatabaseConnection.getInstance();
            sql =  INSERT_INTO_BACKUP_EVENTS;
            ps = connection.prepareStatement(sql);
            ps.setString(1, aliveString);
            ps.setString(2, deadString);
            ps.setString(3, bodyTableHTML);
            //query execution
            rsInt = ps.executeUpdate();
            deleteTables(connection);

        } catch (Exception ex) {
            ex.printStackTrace();
            // throw ex;
        } finally {
            try {
                if (connection != null)
                    connection.close();
            }catch(Exception ex) {
                ex.printStackTrace();
                // throw ex;
            }
        }
        // return
    }


}
