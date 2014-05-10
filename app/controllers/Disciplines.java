package controllers;

import models.Course;
import models.Discipline;
import models.User;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by NRAM on 07/04/14.
 */
public class Disciplines extends Controller {

    final static Form<Discipline> DisciplineForm = Form.form(Discipline.class);

    public static Result all(){
        return ok(
                views.html.Disciplines.index.render(
                        User.findByEmail(request().username()),
                        Discipline.all()
                )
        );
    }

    public static Result show(Long id){
        return ok(
                views.html.Disciplines.item.render(
                        User.findByEmail(request().username()),
                        Discipline.getById(id),
                        Course.all()
                )
        );
    }

    public static Result create(){
        return ok(
                views.html.Disciplines.item.render(
                        User.findByEmail(request().username()),
                        null,
                        Course.all()
                )
        );
    }

    public static Result delete(Long id){
        try{
            Discipline.delete(id);
            flash("sucesso","Disciplina removida com sucesso");
        }catch(Exception e){
            flash("erro", e.getMessage());
        }
        return all();
    }

    public static Result update(Long id){
        Form<Discipline> filledForm = DisciplineForm.bindFromRequest();
        if(filledForm.hasErrors()){
            flash("erro","Ocorreram erros na gravação");
            return badRequest(views.html.Disciplines.item.render(User.findByEmail(request().username()),Discipline.getById(id),Course.all()));
        }else{
            filledForm.get().update(id);
            flash("sucesso", "Disciplina gravada com sucesso.");
            return redirect(routes.Disciplines.all());
        }
    }

    public static Result save(){
        StringBuilder erro = new StringBuilder();


        Form<Discipline> filledForm = DisciplineForm.bindFromRequest();
        if(filledForm.hasErrors()){

            erro.append("DATAAA:");

            for(String s1 : filledForm.data().keySet()){
                erro.append("\n\n\n\n\n\n" + s1 + " -> " + filledForm.data().get(s1));
            }

            erro.append("\nname->" + filledForm.name());
            erro.append("\ntoString->" + filledForm.toString());
            erro.append("\nerrorsAsJson->" + filledForm.errorsAsJson().asText());

            erro.append("\nglobalErrors");
            for(ValidationError v : filledForm.globalErrors()){
                erro.append("\n\tmessage->" + v.message());
            }

            flash("erro", "Ocorreram erros na gravação" + erro.toString());
            return badRequest(views.html.Disciplines.item.render(User.findByEmail(request().username()),null,Course.all()));
        }else{
            Long cursoID = Long.parseLong(filledForm.data().get("cursoID"));

            System.out.println(Course.getById(cursoID).toString());

            Discipline.create(filledForm.get(), cursoID);
            return redirect(routes.Disciplines.all());
        }
    }


    public static Result allByCourse(Long id_course){
        return ok(
                views.html.Disciplines.index.render(
                    User.findByEmail(request().username()),
                    Discipline.allByCourse(id_course)
                )
        );
    }

    public static Result showByCourse(Long id_course, Long id_discipline){
        return ok(
                views.html.Disciplines.item.render(
                        User.findByEmail(request().username()),
                        Discipline.getById(id_discipline),
                        Course.all()
                )
        );
    }

    // Não sei como enviar pelo curso... acho que isso já vem desde o momento que se tenta criar a disciplina
    public static Result createByCourse(Long id_course){
        Form<Discipline> filledForm = DisciplineForm.bindFromRequest();
        if(filledForm.hasErrors()){
            return badRequest("BAD");
        }else{
            //Discipline.create(filledForm.get());
            return redirect(routes.Disciplines.all());
        }
    }


}
