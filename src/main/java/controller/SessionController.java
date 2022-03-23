package controller;

import io.javalin.http.Context;
import models.JsonResponse;
import models.User;
import services.UserService;

public class SessionController {
    private UserService userService;

    public SessionController() {
        this.userService = new UserService();
    }

    public void login (Context context){
        JsonResponse jsonResponse;

        User credentials = context.bodyAsClass(User.class);

        User retrievedUser = userService.validateCredentials(credentials.getUsername(), credentials.getPassword());

        if(retrievedUser == null){
            jsonResponse = new JsonResponse(false, "Invalid Username or Password", null);
        }else{
            context.sessionAttribute("logedInUser", retrievedUser);
            jsonResponse = new JsonResponse(true, "Login Successful", retrievedUser);
        }

        context.json(jsonResponse);
    }

    public void checkSession(Context context){
        User logedInUser = context.sessionAttribute("logedInUser");

        if(logedInUser == null){
            context.json(new JsonResponse(false, "no session found", null));
        }else{
            context.json(new JsonResponse(true, "session found", logedInUser));
        }

    }

    public void logout(Context context){
        context.sessionAttribute("logedInUser", null);

        context.json(new JsonResponse(true, "session invalidated", null));
    }

}
