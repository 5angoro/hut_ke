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

    public Result tenants() {return ok(views.html.tenants.render());}

    public Result register_property() {return ok(views.html.register_property.render());}

    public Result property() {return ok(views.html.property.render());}

    public Result payments() {return ok(views.html.payments.render());}

    public Result payment_tn() {return ok(views.html.payment_tn.render());}

    public Result receipts() {return ok(views.html.receipts.render());}

    public Result receipts_tn() {return ok(views.html.receipts_tn.render());}

    public Result settings() {return ok(views.html.settings.render());}

    public Result settings_tn() {return ok(views.html.settings_tn.render());}

    public Result messages() {return ok(views.html.messages.render());}

    public Result messages_tn() {return ok(views.html.messages_tn.render());}

    public Result reports() {return ok(views.html.reports.render());}

    public Result notification_tn() {return ok(views.html.notification_tn.render());}

    public Result maintenance() {return ok(views.html.maintenance.render());}

    public Result maintenance_tn() {return ok(views.html.maintenance_tn.render());}

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
