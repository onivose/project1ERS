package services;

import models.Reimbursement;
import models.User;
import repositories.ReimbDAO;
import repositories.ReimbDAOImpl;

import java.util.Collections;
import java.util.List;

public class ReimbService {
    ReimbDAO reimbDAO;

    public ReimbService() {
        this.reimbDAO = new ReimbDAOImpl();
    }

    public ReimbService(ReimbDAO reimbDAO) {
        this.reimbDAO = reimbDAO;
    }

    // --------------- EMPLOYEE SERVICES --------------------- \\
    public List<Reimbursement> getAllGivenUser(Integer userId){
        return this.reimbDAO.getReimbursementsGivenUser(userId);
    }

    public Reimbursement createReimbursement (Reimbursement reimbursementToCreate){
        if(this.reimbDAO.createReimbursement(reimbursementToCreate)){
            List<Reimbursement> reimbs = reimbDAO.getReimbursementsGivenUser(reimbursementToCreate.getAuthorId());
            return reimbs.get(reimbs.size()-1);
        }
        return null;
    }

    // --------------- FINANCE MANAGER SERVICES --------------------- \\

    public List<Reimbursement> getAllForAllUsers(User currentUser){

        if (currentUser.isManager()){ // if the user is a manager
            return this.reimbDAO.getReimbursementsForAllUsers();
        } else {
            return null;
        }
    }

    /**
     * Checks if the user is a manager to allows access to change reimbursement status
     * @param currentUser
     * @param reimbId
     * @param newStatusId
     * @return true if status was successfully changed, false otherwise
     */
    public Boolean changeStatus(User currentUser, Integer reimbId, Integer newStatusId){
        if( currentUser == null){
            return false; // to handle null pointer exception
        }
        else if (currentUser.isManager()) {  // if the user is a manager
            this.reimbDAO.changeReimbursementStatus(reimbId, newStatusId);
            return true;
        } else { // user is not a manager and cannot change reimbursement status
            return false;
        }
    }

    public List<Reimbursement> filterByStatusId(User currentUser, Integer statusId){
        if (currentUser.isManager()){  // if the user is a manager
            return this.reimbDAO.getReimbursementsGivenStatusId(statusId);
        } else {
            return null;
        }
    }

    public List<Reimbursement> filterByTypeId(User currentUser, Integer typeId){
        if (currentUser.isManager()){  // if the user is a manager
            return this.reimbDAO.getReimbursementsGivenTypeId(typeId);
        } else {
            return null;
        }
    }

}
