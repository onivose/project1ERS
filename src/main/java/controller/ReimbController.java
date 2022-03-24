package controller;

import io.javalin.http.Context;
import models.JsonResponse;
import models.Reimbursement;
import models.User;
import services.ReimbService;
import services.UserService;

import java.util.List;

public class ReimbController {
    private ReimbService reimbService;

    public ReimbController(ReimbService reimbService) {
        this.reimbService = reimbService;
    }

    public ReimbController() {
        this.reimbService = new ReimbService();
    }

    /**
     * Gets passed a Reimbursement object from the body of http request as JSON.
     * Creates the reimbursement and returns a success message as a JSON response
     * @param context Handler object parameter for Javalin implementation
     */
    public void createReimbursement (Context context){
        Reimbursement reimbToCreate = context.bodyAsClass(Reimbursement.class);



        Reimbursement reimbFromDb = reimbService.createReimbursement(reimbToCreate);
        if (reimbFromDb == null){
            context.json(new JsonResponse(false, "An error ahas occurred, please try again", null));

        } else{
            context.json(new JsonResponse(true, "Reimbursement successfully created!", reimbFromDb));
        }

    }

    /**
     * Gets passed a User ID from query parameter.
     * If user is a manager, retrieve all past reimbursements for all employees.
     * Else, only retrieve past reimbursements for that specific employee.
     *
     * @param context Handler object parameter for Javalin implementation
     * */
    public void getAll (Context context){
        UserService userService = new UserService();

        //get user id in path param instead of request body
        Integer userId = Integer.parseInt(context.queryParam("userId"));

        // retrieve user given id
        User currentUser = userService.getUserGivenId(userId);

        //check if user is a manager
        if (currentUser.isManager()) {

            context.json(new JsonResponse(true, "All Reimbursements for All Users: ", reimbService.getAllForAllUsers(currentUser)));
        } else {

            context.json(new JsonResponse(true, "All Past Reimbursements for User: " + currentUser.getUsername(), reimbService.getAllGivenUser(currentUser.getId())));
        }



    } // Done

    /**
     * Gets passed a User object from the body of http request as JSON.
     * Gets passed a Reimbursement ID and Status ID as path parameters.
     * If user is a manager, change status of given reimbursement to given status.
     * @param context Handler object parameter for Javalin implementation
     */
    public void changeStatus(Context context) {
        User currentUser = context.bodyAsClass(User.class);
        Integer reimbId = Integer.parseInt(context.pathParam("reimbId"));
        Integer newStatusId = Integer.parseInt(context.pathParam("statusId"));

        if (currentUser.isManager()){
            reimbService.changeStatus(currentUser,reimbId,newStatusId);
            context.json(new JsonResponse(true,"Status updated to " + "_____" + "for reimbursement Id " + reimbId,null));
        }


    }

    /**
     * Gets passed a User object from the body of http request as JSON.
     * Gets passed a Status ID as path parameter.
     * If the user is a manager, retrieve all past reimbursements for all employees filtered by given status.
     * @param context Handler object parameter for Javalin implementation.
     */
    public void filterByStatus(Context context) {
        UserService userService = new UserService();

        //get user id and status id in query param instead of request body
        Integer userId = Integer.parseInt(context.queryParam("userId"));
        Integer statusId = Integer.parseInt(context.queryParam("statusId"));

        // retrieve user given id
        User currentUser = userService.getUserGivenId(userId);

        if (currentUser.isManager()){
            List<Reimbursement> returnedList = reimbService.filterByStatusId(currentUser,statusId);
            context.json(new JsonResponse(true,"All Reimbursements Filtered by Status: ", returnedList));
        }

    }

    /**
     * Gets passed a User object from the body of http request as JSON.
     * Gets passed a Type ID as path parameter.
     * If the user is a manager, retrieve all past reimbursements for all employees filtered by given reimbursement type.
     * @param context Handler object parameter for Javalin implementation.
     */
    public void filterByType(Context context) {
        UserService userService = new UserService();

        //get user id and status id in query param instead of request body
        Integer userId = Integer.parseInt(context.queryParam("userId"));
        Integer typeId = Integer.parseInt(context.queryParam("typeId"));

        // retrieve user given id
        User currentUser = userService.getUserGivenId(userId);


        if (currentUser.isManager()){
            List<Reimbursement> returnedList = reimbService.filterByTypeId(currentUser,typeId);
            context.json(new JsonResponse(true,"All Reimbursements Filtered by Type: ", returnedList));
        }

    }
}