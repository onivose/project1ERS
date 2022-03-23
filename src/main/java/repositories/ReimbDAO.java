package repositories;

import models.Reimbursement;
import models.User;

import java.util.List;

public interface  ReimbDAO {
    public void createReimbursement (Reimbursement reimbursement);
    public List<Reimbursement> getReimbursementsForAllUsers();
    public List<Reimbursement> getReimbursementsGivenUser(Integer userId);
    public void changeReimbursementStatus(Integer reimbId, Integer newStatusId); // approving or denying requests
    public List<Reimbursement> getReimbursementsGivenStatusId(Integer statusId);
    public List<Reimbursement> getReimbursementsGivenTypeId(Integer statusId);

}
