package controllers;

import models.User;
import play.data.*;
import play.mvc.Controller;
import play.mvc.Result;

import static play.data.Form.form;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render("Your new application is ready."));
    }

    // -- Authentication

    public static class Login {

        public String email;
        public String password;

        public String validate() {
            if(User.authenticate(email, password) == null) {
                return "Utilizador inválido ou password errada.";
            }
            return null;
        }
    }

    /**
     * Login page.
     */
    public static Result login() {
        return ok(views.html.login.render(form(Login.class))
        );
    }

    /**
     * Handle login form submission.
     */
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()) {
            return badRequest(views.html.login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(routes.Courses.all());
        }
    }

    /**
     * Logout and clean the session.
     */
    public static Result logout() {
        session().clear();
        flash("Sucesso", "O utilizador efetuou logout.");
        return redirect(routes.Application.login());
    }

}

