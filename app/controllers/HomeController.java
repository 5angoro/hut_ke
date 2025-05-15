package controllers;

import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.*;
import jakarta.inject.Inject;
import models.*;


import java.util.concurrent.CompletionStage;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    private FormFactory formFactory;
    @Inject
    public HomeController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {return ok(views.html.index.render());}

    public Result manager_sp() {return ok(views.html.manager_sp.render());}

    public Result tenant_sp() {return ok(views.html.tenant_sp.render());}

    public Result tenant_ln(Http.Request request) {return ok(views.html.tenant_ln.render(request));}

    public Result manager_ln(Http.Request request) {return ok(views.html.manager_ln.render(request));}

    public Result tenant_dash() {return ok(views.html.tenant_dash.render());}

    public Result manager_dash() {return ok(views.html.manager_dash.render());}

    public Result managerLogin(Http.Request request) {
        DynamicForm data = formFactory.form().bindFromRequest(request);
        String email = data.get("email");
        String password = data.get("password");
        if(email.isEmpty() || password.isEmpty()) {
            return badRequest(views.html.manager_ln.render(request)).flashing("error", "Wrong Username or Password");
        }

        User user = User.findUser(email, password);
        if (user != null) {
                return redirect(routes.HomeController.manager_dash()).addingToSession(request,"user", user.getEmail());
        }

        return redirect(routes.HomeController.manager_ln()).flashing("error", "Wrong Username or Password");
    }


    public Result tenantLogin(Http.Request request) {
        DynamicForm data = formFactory.form().bindFromRequest(request);
        String email = data.get("email");
        String password = data.get("password");
        if(email.isEmpty() || password.isEmpty()) {
            return badRequest(views.html.tenant_ln.render(request)).flashing("error", "Wrong Username or Password");
        }

        User user = User.findUser(email, password);
        if (user != null) {
            return redirect(routes.HomeController.tenant_dash()).addingToSession(request,"user", user.getEmail());
        }

        return redirect(routes.HomeController.tenant_ln()).flashing("error", "Wrong Username or Password");
    }
}
