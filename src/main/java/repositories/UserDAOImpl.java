package repositories;

import models.User;
import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    /**
     * Used to check unique usernames for user creation and validate user credentials for login in
     * @return User - the user or null if the user is not found
     */
    @Override
    public User getUserGivenUsername(String username) { // returns the user or null if the user is not found
        User user = null;

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "select * from ers_users where user_username = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                user = new User(rs.getInt(1),   // userId
                        rs.getString(2),        // username
                        rs.getString(3),        // password
                        rs.getString(4),        // firstname
                        rs.getString(5),        // lastname
                        rs.getString(6),        // email
                        rs.getInt(7)            // role
                        );
            }

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return user;
    } // returns the user or null if the user is not found

    @Override
    public void createUser(User userToCreate) {
        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "insert into ers_users (user_username, user_password, user_first_name, user_last_name, user_email, user_role_id_fk) values (?,?,?,?,?,?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,userToCreate.getUsername());
            ps.setString(2,userToCreate.getPassword());
            ps.setString(3,userToCreate.getFirstname());
            ps.setString(4,userToCreate.getLastname());
            ps.setString(5,userToCreate.getEmail());
            ps.setInt(6,userToCreate.getRoleId());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * Used to check unique emails for user creation
     * @return User - the user or null if the user is not found
     */
    @Override
    public User getUserGivenEmail(String email) {
        User user = null;
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql ="select * from ers_users where user_email = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,email);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                user = new User(rs.getInt(1),   // userId
                        rs.getString(2),        // username
                        rs.getString(3),        // password
                        rs.getString(4),        // firstname
                        rs.getString(5),        // lastname
                        rs.getString(6),        // email
                        rs.getInt(7)            // role
                );
            }


        } catch (SQLException sqle){
            sqle.printStackTrace();
        }

        return user;
    }

}
