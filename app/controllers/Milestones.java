package controllers;

import models.Discipline;
import models.Milestone;
import models.Project;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by afv on 25/04/14.
 */
@Security.Authenticated(Secured.class)
public class Milestones extends Controller{

    final static Form<Milestone> milestoneForm = Form.form(Milestone.class);

    public static Result all(){
        return ok(
                views.html.Milestones.index.render(
                        User.findByEmail(request().username()),
                        Milestone.all()
                )
        );
    }

    public static Result show(Long id){
        return ok();
    }

    public static Result create(){
        return ok();
    }

    public static Result delete(Long id){
        try{
            Milestone.delete(id);
            flash("sucesso","Projeto removido com sucesso");
        }catch(Exception e){
            flash("erro", e.getMessage());
        }
        return all();
    }

    public static Milestone updateHandler(Long id) {
        Form<Milestone> filledForm = milestoneForm.fill(Milestone.getById(id)).bindFromRequest();
        if (filledForm.hasErrors()){
            flash("erro", "Ocorreram erros na gravação: " + filledForm.toString());
            return null;
        } else {
            Long projectID = Long.parseLong(filledForm.data().get("projectID"));
            Milestone saveMilestone = Milestone.update(filledForm.get(), projectID);
            flash("sucesso", "Projeto gravado com sucesso.");
            return saveMilestone;
        }
    }

    public static Result update(Long id){

        if(Milestones.updateHandler(id) == null){
            return badRequest();
        }else{
            return redirect(routes.Milestones.all());
        }
    }

    public static Milestone saveHandler() {
        Form<Milestone> filledForm = milestoneForm.bindFromRequest();
        if (filledForm.hasErrors()){
            flash("erro", "Ocorreram erros na gravação: " + filledForm.toString());
            return null;
        } else {
            Long projectID = Long.parseLong(filledForm.data().get("projectID"));
            Milestone saveMilestone = Milestone.create(filledForm.get(), projectID);
            flash("sucesso", "Projeto gravado com sucesso.");
            return saveMilestone;
        }
    }

    public static Result save(){
        if(Milestones.saveHandler() == null){
            return badRequest();
        }else{
            return redirect(routes.Milestones.all());
        }
    }

}
