package com.example.epamproj.unused;

import com.example.epamproj.dao.DBException;

import java.sql.*;

public class DBManager {

    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null) instance = new DBManager();
        return instance;
    }

    private DBManager(){

    }

//    public Role findRole(String roleName) throws SQLException {
//
//            PreparedStatement ps = null;
//            ResultSet rs = null;
//            System.out.println("findRole() -> roleName: " + roleName);
//            ps = con.prepareStatement("SELECT * FROM role WHERE Name= ?");
//            ps.setString(1, roleName);
//            System.out.println("PreparedStatement -> " + ps.toString());
//            rs = ps.executeQuery();                                                   //cannot execute query
//            System.out.println(rs.getInt("roleId"));
//            if (rs.next()) {
//                Role role = new Role();
//                role.setRoleId(rs.getInt("roleId"));
//                role.setName(rs.getString("roleName"));
//
//                System.out.println("Role -> " + role);
//                //rs.close();
//                ps.close();
//                con.close();
//                return role;
//            }
//        ps.close();
//        con.close();
//        return null;
//
//
//    }



    public void insertRole(String roleName) throws DBException {
        Connection con = null;
        PreparedStatement st = null;
        con = DBConnector.connect();
        try {
            st = con.prepareStatement("INSERT INTO role(Name) VALUE (?)");
            st.setString(1, roleName);
            st.executeUpdate();
        } catch (SQLException e) {
            // log exception
            throw new DBException(e.getMessage(), e.getCause());
        }finally {
            assert st != null;
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage(), e.getCause());
            }

        }
    }


    public void insertUser(String role, String name, String surname, String patronymic, String phone, String login, String password) throws DBException {
        Connection con = null;
        PreparedStatement st = null;
        con = DBConnector.connect();
        try {
            st = con.prepareStatement("INSERT INTO user(role, name, surname, patronymic, phone, login, password) VALUES (?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, role);
            st.setString(2, name);
            st.setString(3, surname);
            st.setString(4, patronymic);
            st.setString(5, phone);
            st.setString(6, login);
            st.setString(7, password);
            st.executeUpdate();


        } catch (SQLException e) {
            //log exception
            throw new DBException(e.getMessage(), e.getCause());
        }finally {
            assert st != null;
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage(), e.getCause());
            }

        }

    }

    public void deleteUser(String login) throws DBException {
        Connection con = null;
        System.out.println("login = " + login);
        con = DBConnector.connect();
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("DELETE FROM user WHERE login =?");
            st.setString(1, login);
            System.out.println(st);
            st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e.getCause());
        }finally {
            try {
                assert st != null;
                st.close();
                con.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage(), e.getCause());
            }
        }

    }
}