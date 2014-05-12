package controllers;

import models.Discipline;
import models.Group;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by afv on 12/05/14.
 */
public class Groups extends Controller {

    final static Form<Group> groupForm = Form.form(Group.class);

    public static Result all(){
        return ok(
                views.html.Groups.index.render(
                        User.findByEmail(request().username()),
                        Group.all()
                )
        );
    }

    public static Result show(Long id){
        return ok(
                views.html.Groups.item.render(
                        User.findByEmail(request().username()),
                        Group.getById(id),
                        Discipline.all()
                )
        );
    }

    public static Result create(){
        return ok(
                views.html.Groups.item.render(
                        User.findByEmail(request().username()),
                        null,
                        Discipline.all()
                )
        );
    }

    public static Result delete(Long id){
        try{
            Group.delete(id);
            flash("sucesso","Grupo removido com sucesso");
        }catch(Exception e){
            flash("erro", e.getMessage());
        }
        return all();
    }

    public static Result update(Long id){
        Form<Group> filledForm =  groupForm.fill(Group.getById(id)).bindFromRequest();
        if(filledForm.hasErrors()){
            flash("erro","Ocorreram erros na gravação" + filledForm.toString());
            return badRequest(views.html.Groups.item.render(User.findByEmail(request().username()),Group.getById(id),Discipline.all()));
        }else{
            Long disciplineID = Long.parseLong(filledForm.data().get("disciplinaID"));
            Group.update(filledForm.get(), disciplineID);

            flash("sucesso", "Grupo gravado com sucesso.");
            return redirect(routes.Groups.all());
        }
    }

    public static Result save(){
        Form<Group> filledForm = groupForm.bindFromRequest();
        if(filledForm.hasErrors()){
            flash("erro", "Ocorreram erros na gravação");
            return badRequest(views.html.Groups.item.render(User.findByEmail(request().username()),null,Discipline.all()));
        }else{
            Long disciplineID = Long.parseLong(filledForm.data().get("disciplinaID"));
            Group.create(filledForm.get(), disciplineID);

            flash("sucesso", "Grupo gravado com sucesso.");
            return redirect(routes.Groups.all());
        }
    }
}
