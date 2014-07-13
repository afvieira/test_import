package controllers.API;

import models.*;
import models.API.*;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

public class JSON extends Controller {

    public static Result GetEntities(String entity){
        switch (entity){
            case "courses":
                return GetCourses();
            case "disciplines":
                return GetDisciplines();
            case "groups":
                return GetGroups();
            case "projects":
                return GetProjects();
            case "users":
                return GetUsers();
        }

        return notFound();
    }

    public static Result GetEntity(String entity, Long id){
        switch (entity){
            case "courses":
                return GetCourse(id);
            case "disciplines":
                return GetDiscipline(id);
            case "groups":
                return GetGroup(id);
            case "projects":
                return GetProject(id);
            case "users":
                return GetUser(id);
        }
        return notFound();
    }

    public static Result GetStudents(){
        List<UserView> result = new ArrayList<>();
        for(User p: User.findAllStudents()){
            result.add(new UserView(p));
        }
        return ok(Json.toJson(result));
    }

    //<editor-fold desc="Courses">
    public static Result GetCourses() {
        List<CourseView> result = new ArrayList<>();
        for(Course c: Course.all()){
            result.add(new CourseView(c));
        }
        return ok(Json.toJson(result));
    }

    public static Result GetCourse(Long id) {
        Course course = Course.getById(id);

        if(course == null){
            return notFound();
        }else {
            return ok(Json.toJson(new CourseView(course)));
        }
    }
    //</editor-fold>

    //<editor-fold desc="Disciplines">
    public static Result GetDisciplines() {
        List<DisciplineView> result = new ArrayList<>();
        for(Discipline d: Discipline.all()){
            result.add(new DisciplineView(d));
        }
        return ok(Json.toJson(result));
    }

    public static Result GetDiscipline(Long id) {
        Discipline discipline = Discipline.getById(id);

        if(discipline == null){
            return notFound();
        }else {
            return ok(Json.toJson(new DisciplineView(discipline)));
        }
    }
    //</editor-fold>

    //<editor-fold desc="Groups">
    public static Result GetGroups() {
        List<GroupView> result = new ArrayList<>();
        for(Group g: Group.all()){
            result.add(new GroupView(g));
        }
        return ok(Json.toJson(result));
    }

    public static Result GetGroup(Long id) {
        Group group = Group.getById(id);

        if(group == null){
            return notFound();
        }else {
            return ok(Json.toJson(new GroupView(group)));
        }
    }
    //</editor-fold>

    //<editor-fold desc="Projects">
    public static Result GetProjects() {
        List<ProjectView> result = new ArrayList<>();
        for(Project p: Project.all()){
            result.add(new ProjectView(p));
        }
        return ok(Json.toJson(result));
    }

    public static Result GetProject(Long id) {
        Project p = Project.getById(id);

        if(p == null){
            return notFound();
        }else {
            return ok(Json.toJson(new ProjectView(p)));
        }
    }
    //</editor-fold>

    //<editor-fold desc="Users">
    public static Result GetUsers() {
        List<UserView> result = new ArrayList<>();
        for(User p: User.findAll()){
            result.add(new UserView(p));
        }
        return ok(Json.toJson(result));
    }

    public static Result GetUser(Long id) {
        User u = User.getById(id);

        if(u == null){
            return notFound();
        }else {
            return ok(Json.toJson(new UserView(u)));
        }
    }
    //</editor-fold>

}
