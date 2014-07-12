package controllers;

import models.Discipline;
import models.User;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by NRAM on 07/04/14.
 */
@Security.Authenticated(Secured.class)
public class Users extends Controller {

    final static Form<User> userForm = Form.form(User.class);

    public static Result all(){
        return ok(
                views.html.Users.index.render(
                        User.findByEmail(request().username()),
                        User.all()
                )
        );
    }

    public static Result show(Long id){
        return ok(
                views.html.Users.item.render(
                        User.findByEmail(request().username()),
                        User.getById(id)
                )
        );
    }

    public static Result create(){
        return ok(
                views.html.Users.item.render(
                        User.findByEmail(request().username()),
                        null
                )
        );
    }

    public static Result delete(Long id){
        try{
            User.delete(id);
            flash("sucesso","User removido com sucesso");
        }catch(Exception e){
            flash("erro", e.getMessage());
        }
        return all();
    }

    public static Result update(Long id){
        Form<User> filledForm =  userForm.fill(User.getById(id)).bindFromRequest();
        if(filledForm.hasErrors()){
            flash("erro","Ocorreram erros na gravação" + filledForm.toString());
            return badRequest(views.html.Users.item.render(User.findByEmail(request().username()),User.getById(id)));
        }else{
            User u = User.update(filledForm.get());

            flash("sucesso", "User gravado com sucesso.");
            return redirect(routes.Users.show(u.id));
        }
    }

    public static Result save(){
        Form<User> filledForm = userForm.bindFromRequest();
        if(filledForm.hasErrors()){
            flash("erro", "Ocorreram erros na gravação");
            return badRequest(views.html.Users.item.render(User.findByEmail(request().username()),null));
        }else{
            User u = User.create(filledForm.get());

            flash("sucesso", "User gravado com sucesso.");
            return redirect(routes.Users.show(u.id));
        }
    }




    /*public static Result all(Long id_course, Long id_discipline){
        return TODO;
    }

    public static Result show(Long id_course, Long id_discipline, Long id_user){
        return TODO;
    }

    public static Result delete(Long id_course, Long id_discipline, Long id_user){
        return TODO;
    }

    public static Result create(Long id_course, Long id_discipline){
        return TODO;
    }*/

}
