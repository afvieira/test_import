package controllers;

import models.Course;
import models.Discipline;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by NRAM on 07/04/14.
 */
@Security.Authenticated(Secured.class)
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
        Form<Discipline> filledForm = DisciplineForm.fill(Discipline.getById(id)).bindFromRequest();
        if(filledForm.hasErrors()){
            flash("erro","Ocorreram erros na gravação" + filledForm.toString());
            return badRequest(views.html.Disciplines.item.render(User.findByEmail(request().username()),Discipline.getById(id),Course.all()));
        }else{
            Long cursoID = Long.parseLong(filledForm.data().get("cursoID"));
            Discipline.update(filledForm.get(), cursoID);

            flash("sucesso", "Disciplina gravada com sucesso.");
            return redirect(routes.Disciplines.all());
        }
    }

    public static Result save(){
        Form<Discipline> filledForm = DisciplineForm.bindFromRequest();
        if(filledForm.hasErrors()){
            flash("erro", "Ocorreram erros na gravação");
            return badRequest(views.html.Disciplines.item.render(User.findByEmail(request().username()),null,Course.all()));
        }else{
            Long cursoID = Long.parseLong(filledForm.data().get("cursoID"));
            Discipline.create(filledForm.get(), cursoID);
            flash("sucesso", "Disciplina gravada com sucesso.");
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

    public static Result addStudentDiscipline(Long id_discipline, Long id_student){
        try {
            User user = User.getById(id_student);
            Discipline discipline = Discipline.getById(id_discipline);
            discipline.users.add(user);
            discipline.update();
        } catch(Exception ex){}
        return ok();

    }
}
