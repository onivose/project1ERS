import controller.ReimbController;
import controller.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import models.Reimbursement;
import models.User;
import repositories.ReimbDAO;
import repositories.ReimbDAOImpl;
import repositories.UserDAO;
import repositories.UserDAOImpl;
import services.ReimbService;
import services.UserService;

public class MainDriver {

    public static void main(String[] args) {

        UserController userController = new UserController();
        ReimbController reimbController = new ReimbController();
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/", Location.CLASSPATH);
        }).start(7220);

        // ---------------------------------- ALL EMPLOYEES ------------------------------------------\\

        app.post("/login", userController::login);
        app.post("/register", userController::createUser); //throws error if username or email taken already

        //todo test reimb controller

        app.get("/reimb/", reimbController::getAll); // get all for all employees or get all for one employee
        app.post("/reimb/new", reimbController::createReimbursement);

        // ----------------------------- FINANCE MANAGERS ONLY ------------------------------------------\\

        app.patch("/reimb/{reimbId}/changestatus/{statusId}", reimbController::changeStatus);
        app.get("/reimb/filter/status/{statusId}",reimbController::filterByStatus);
        app.get("/reimb/filter/type/{typeId}",reimbController::filterByType);

    }
}
