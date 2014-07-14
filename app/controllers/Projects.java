package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by afv on 25/04/14.
 */
@Security.Authenticated(Secured.class)
public class Projects extends Controller {

    final static Form<Project> projectForm = Form.form(Project.class);

    public static Result all() {
        return ok(
                views.html.Projects.index.render(
                        User.findByEmail(request().username()),
                        Project.all()
                )
        );
    }

    public static Result show(Long id) {
        return ok(
                views.html.Projects.item.render(
                        User.findByEmail(request().username()),
                        Project.getById(id),
                        Discipline.all()
                )
        );
    }

    public static Result create() {
        return ok(
                views.html.Projects.item.render(
                        User.findByEmail(request().username()),
                        null,
                        Discipline.all()
                )
        );
    }

    public static Result delete(Long id) {
        try {
            Project.delete(id);
            flash("sucesso", "Projeto removido com sucesso");
        } catch (Exception e) {
            flash("erro", e.getMessage());
        }
        return all();
    }

    public static Project updateHandler(Long id) {
        Form<Project> filledForm = projectForm.fill(Project.getById(id)).bindFromRequest();
        if (filledForm.hasErrors()) {
            flash("erro", "Ocorreram erros na gravação: " + filledForm.toString());
            return null;
        } else {
            Long disciplinaID = Long.parseLong(filledForm.data().get("disciplinaID"));
            Project saveProject = Project.update(filledForm.get(), disciplinaID);
            flash("sucesso", "Projeto gravado com sucesso.");
            return saveProject;
        }
    }

    public static Result update(Long id) {

        if (Projects.updateHandler(id) == null) {
            return badRequest(views.html.Projects.item.render(User.findByEmail(request().username()), Project.getById(id), Discipline.all()));
        } else {
            return redirect(routes.Projects.all());
        }
    }

    public static Project saveHandler() {
        Form<Project> filledForm = projectForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            flash("erro", "Ocorreram erros na gravação: " + filledForm.toString());
            return null;
        } else {
            Long disciplinaID = Long.parseLong(filledForm.data().get("disciplinaID"));
            Project saveProject = Project.create(filledForm.get(), disciplinaID, User.findByEmail(request().username()).email);
            flash("sucesso", "Projeto gravado com sucesso.");
            return saveProject;
        }
    }

    public static Result save() {
        if (Projects.saveHandler() == null) {
            return badRequest(views.html.Projects.item.render(User.findByEmail(request().username()), null, Discipline.all()));
        } else {
            return redirect(routes.Projects.all());
        }
    }

    public static Result addStudent(Long id_project, Long id_student) {
        try {
            User user = User.getById(id_student);
            if (user.getUserType().equals("Student")) {
                Project project = Project.getById(id_project);
                StudentProject sp = StudentProject.getByProjectStudent(id_project, user.getEmail());
                if (sp == null) {
                    sp = new StudentProject(user, project);
                    sp.save();
                    project.addStudent(sp);
                    project.update();
                }
            }
        } catch (Exception ex) {
            System.out.println("Entrou na excepção");
            System.out.print(ex);
        }
        return ok();
    }

    public static Result addAllStudent(Long id_project) {

        try {

            Project project = Project.getById(id_project);
            for (User u : project.discipline.users) {
                if (u.getUserType().equals("Student")) {
                    StudentProject sp = StudentProject.getByProjectStudent(id_project, u.getEmail());
                    if (sp == null) {
                        sp = new StudentProject(u, project);
                        sp.save();
                        project.addStudent(sp);
                        project.update();
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println("Entrou na excepção");
            System.out.println(ex);
        }
        return ok();
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result addUsersGroup(Long id_project){

        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {
                JsonNode students = json.findPath("students");
                if (students == null) {
                    return badRequest("Missing parameter [students]");
                } else {
                    Project project = Project.getById(id_project);
                    Group g = new Group(project.discipline, generateRandomWords(8), "", 5, "", false);
                    Iterator<JsonNode> itStudents = students.elements();
                    while(itStudents.hasNext()){
                        JsonNode sID = (JsonNode) itStudents.next();
                        g.addStudent(User.getById(sID.findPath("id").asLong()));
                    }

                    g.limitNumber = g.students.size();
                    g.projects = new ArrayList<>();

                    GroupProject gp = GroupProject.create(new GroupProject(project));
                    g.addGroup(gp);

                    Group gC = Group.create(g);
                    GroupProject.saveCourse(gp, gC.id);

                    return ok();
                }
            }catch(Exception e){
                return ok();
            }
        }
    }

    public static String generateRandomWords(int numberOfWords)
    {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings.toString();
    }


}
