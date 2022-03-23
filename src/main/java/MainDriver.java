import controller.ReimbController;
import controller.SessionController;
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
        SessionController sessionController = new SessionController();

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/", Location.CLASSPATH);
        }).start(7220);

        // ---------------------------------- ALL EMPLOYEES ------------------------------------------\\
        // Session Controller Endpoints
        app.post("/login", sessionController::login);
        app.get("/session", sessionController::checkSession);
        app.delete("session",sessionController::logout);

        // User Controller Endpoints
        app.post("/register", userController::createUser); //throws error if username or email taken already

        // Reimbursement Controller Endpoints
        app.get("/reimb", reimbController::getAll);
        app.post("/reimb/new", reimbController::createReimbursement);

        // ----------------------------- FINANCE MANAGERS ONLY ------------------------------------------\\

        app.patch("/reimb/{reimbId}/changestatus/{statusId}", reimbController::changeStatus);
        app.get("/reimb/filter/status/{statusId}",reimbController::filterByStatus);
        app.get("/reimb/filter/type/{typeId}",reimbController::filterByType);

    }
}
