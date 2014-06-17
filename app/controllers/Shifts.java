package controllers;

import models.Discipline;
import models.Shift;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by NRAM on 07/04/14.
 */
@Security.Authenticated(Secured.class)
public class Shifts extends Controller {

    final static Form<Shift> shiftForm = Form.form(Shift.class);

    public static Result all(){
        return ok(
                views.html.Shifts.index.render(
                        User.findByEmail(request().username()),
                        Shift.all()
                )
        );
    }

    public static Result show(Long id){
        return ok(
                views.html.Shifts.item.render(
                        User.findByEmail(request().username()),
                        Shift.getById(id),
                        Discipline.all()
                )
        );
    }

    public static Result create(){
        return ok(
                views.html.Shifts.item.render(
                        User.findByEmail(request().username()),
                        null,
                        Discipline.all()
                )
        );
    }

    public static Result delete(Long id){
        try{
            Shift.delete(id);
            flash("sucesso","Turno removido com sucesso");
        }catch(Exception e){
            flash("erro", e.getMessage());
        }
        return all();
    }

    public static Result update(Long id){
        Form<Shift> filledForm =  shiftForm.fill(Shift.getById(id)).bindFromRequest();
        if(filledForm.hasErrors()){
            flash("erro","Ocorreram erros na gravação" + filledForm.toString());
            return badRequest(views.html.Shifts.item.render(User.findByEmail(request().username()),Shift.getById(id),Discipline.all()));
        }else{
            Long disciplineID = Long.parseLong(filledForm.data().get("disciplinaID"));
            Shift.update(filledForm.get(), disciplineID);

            flash("sucesso", "Turno gravado com sucesso.");
            return redirect(routes.Shifts.all());
        }
    }

    public static Result save(){
        Form<Shift> filledForm = shiftForm.bindFromRequest();
        if(filledForm.hasErrors()){
            flash("erro", "Ocorreram erros na gravação");
            return badRequest(views.html.Shifts.item.render(User.findByEmail(request().username()),null,Discipline.all()));
        }else{
            Long disciplineID = Long.parseLong(filledForm.data().get("disciplinaID"));
            Shift.create(filledForm.get(), disciplineID);

            flash("sucesso", "Turno gravado com sucesso.");
            return redirect(routes.Shifts.all());
        }
    }




    /*public static Result all(Long id_course, Long id_discipline){
        return TODO;
    }

    public static Result show(Long id_course, Long id_discipline, Long id_shift){
        return TODO;
    }

    public static Result delete(Long id_course, Long id_discipline, Long id_shift){
        return TODO;
    }

    public static Result create(Long id_course, Long id_discipline){
        return TODO;
    }*/

}
