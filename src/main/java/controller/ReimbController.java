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

        reimbService.createReimbursement(reimbToCreate);

        context.json(new JsonResponse(true, "Reimbursement successfully created!", reimbToCreate));
    } // Done

    /**
     * Gets passed a User object from the body of http request as JSON.
     * If user is a manager, retrieve all past reimbursements for all employees.
     * Else, only retrieve past reimbursements for that specific employee.
     * If the user cannot be passed from the body, returns "Invalid User" as a JSON.
     * @param context Handler object parameter for Javalin implementation
     * */
    public void getAll (Context context){
        UserService userService = new UserService();

        User currentUser = context.bodyAsClass(User.class);
        User loginAttempt = userService.validateCredentials(currentUser.getUsername(), currentUser.getPassword());

        if (loginAttempt==null){
            context.json(new JsonResponse(false,"Invalid User", null));
        } else {

            if (currentUser.isManager()) {

                context.json(new JsonResponse(true, "All Reimbursements for All Users: ", reimbService.getAllForAllUsers(currentUser)));
            } else {

                context.json(new JsonResponse(true, "All Past Reimbursements for User: " + currentUser.getUsername(), reimbService.getAllGivenUser(currentUser.getId())));
            }
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
        User currentUser = context.bodyAsClass(User.class);
        Integer statusId = Integer.parseInt(context.pathParam("statusId"));

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
        User currentUser = context.bodyAsClass(User.class);
        Integer typeId = Integer.parseInt(context.pathParam("typeId"));

        if (currentUser.isManager()){
            List<Reimbursement> returnedList = reimbService.filterByTypeId(currentUser,typeId);
            context.json(new JsonResponse(true,"All Reimbursements Filtered by Type: ", returnedList));
        }

    }
}