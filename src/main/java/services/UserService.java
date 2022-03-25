package services;

import models.User;
import repositories.UserDAO;
import repositories.UserDAOImpl;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public UserService(){
        this.userDAO = new UserDAOImpl();
    }

    /**
     * Returns the current user as a User object if the credentials are valid and null otherwise
     * */
    public User validateCredentials(String username, String password){
        User user = this.userDAO.getUserGivenUsername(username);

        //user wasn't found so return null
        if(user == null)
            return null;

        if(!password.equals(user.getPassword()))
            return null;

        return user;
    }

    /**
     * @param userToCreate passed from user controller
     * @return true if new user successfully created or false if username or email already taken
     */
    public Boolean createUser(User userToCreate) {
        User userByUsername = userDAO.getUserGivenUsername(userToCreate.getUsername());
        User userByEmail = userDAO.getUserGivenEmail(userToCreate.getEmail());

        if (userByUsername == null && userByEmail == null){
            // both the username and email are available (they are not found in the database) -> create the user
            this.userDAO.createUser(userToCreate);
            return true;
        } else {
            // either the username is taken already or the email is taken already
            return false;
        }



        // Alternate logics to try if above doesn't work:

        /*if (userByEmail == null && userByUsername == null){
            this.userDAO.createUser(userToCreate);
            return true;
        } else if (userByUsername.getUsername() == userToCreate.getUsername()){
            return false;
        } else if (userByEmail.getEmail() == userToCreate.getEmail()){
            return false;
        } else {
            this.userDAO.createUser(userToCreate);
            return true;
        }*/


        /*if(userByUsername != null){ // username taken
            return false;
        } else if (userByEmail != null){ // email taken
            return false;
        }
        else { // creates the user
            this.userDAO.createUser(userToCreate);
            return true;
        }*/


    }


    public User getUserGivenId(Integer userId){
        return this.userDAO.getUserGivenId(userId);
    }

}
