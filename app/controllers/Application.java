package controllers;

import models.User;
import play.data.Form;
import play.mvc.*;

import java.io.File;
import java.util.GregorianCalendar;

import static play.data.Form.form;

public class Application extends Controller {

    public static Result index() {
        User u = null;
        if (session("email") != null) {
            u = User.findByEmail(session("email"));
        }
        return ok(views.html.index.render(u));
    }

    final static Form<User> userForm = Form.form(User.class);

    // -- Authentication

    public static class Login {

        public String email;
        public String password;

        public String validate() {
            if (User.authenticate(email, password) == null) {
                return "Utilizador inválido ou password errada.";
            }
            return null;
        }
    }

    /**
     * Login page.
     */
    public static Result login() {
        return ok(views.html.login.render(form(Login.class)));
    }

    /**
     * Handle login form submission.
     */
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(views.html.login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return index();
        }
    }

    public static Result authenticateAfterRegister(Form<User> registerForm) {
        session().clear();
        session("email", registerForm.get().email);
        return redirect(routes.Courses.all());
    }

    /**
     * Logout and clean the session.
     */
    public static Result logout() {
        session().clear();
        flash("Sucesso", "O utilizador efetuou logout.");
        return redirect(routes.Application.login());
    }

    public static Result register() {
        return ok(views.html.Account.register.render(form(User.class)));
    }

    public static Result createUser() {

        Form<User> filledForm = userForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(
                    views.html.Account.register.render(filledForm)
            );
        } else {
            User.create(filledForm.get());
            return authenticateAfterRegister(filledForm);
        }
    }

    public static Result deleteUser(Long id) {
        User.delete(id);
        return redirect(routes.Application.register());
    }

    public static Result upload() {
        if (uploadToPath("UploadFiles/") != null) {
            return ok();
        } else {
            return badRequest();
        }
    }

    public static String uploadToPath(String relativePath) {
        System.out.println("Vai tentar fazer upload do ficheiro");
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart milestoneFile = body.getFile("file");
        if (milestoneFile != null) {
            // Vai buscar o nome do ficheiro
            String fileName = milestoneFile.getFilename();

            // Tipo de conteúdo
            String contentType = milestoneFile.getContentType();

            // Ficheiro
            File file = milestoneFile.getFile();

            // Cria as directorias se necessário
            File createDirectories = new File("Archive/" + relativePath);
            createDirectories.mkdirs();

            // Move o ficheiro para a nova directoria
            File newFile = new File("Archive/" + relativePath + "/" + new GregorianCalendar().getTimeInMillis() + "_" + fileName);

            // Renomeia
            file.renameTo(newFile);
            return newFile.getPath();
        } else {
            return null;
        }
    }

    public static Result API_Help() {
        User u = null;
        if (session("email") != null) {
            u = User.findByEmail(session("email"));
        }

        return ok(views.html.StaticPages.API_help.render(u));
    }

}

