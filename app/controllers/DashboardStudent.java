package controllers;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

/**
 * Created by NRAM on 26/04/14.
 */
public class DashboardStudent extends Controller {

    /**
     * Display the Dashboard
     *
     * @return
     */
    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(
                views.html.Dashboard.Student.index.render(
                        User.findByEmail(request().username()),
                        Discipline.findByUser(request().username()),                // Disciplinas Que frequenta
                        Milestone.nextDeliveryByUser(request().username()),         // Procurar próximas etapas para entrega
                        Project.getByCreatedDate(request().username()),             // Procurar últimos projetos submetidos
                        StudentMilestone.getLastAvaliations(request().username())   // Últimas Avaliações adicionadas
                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result disciplines() {
        return ok(
                views.html.Dashboard.Student.disciplines.render(
                        User.findByEmail(request().username()),                 // User Atual
                        Discipline.findByUser(request().username()),            // Saber as disciplinas de um dado user
                        Project.getByCreatedDate(request().username()),         // Saber os projetos de um dado estudante
                        Milestone.nextDeliveryByUser(request().username()),     // Próximas Entregas (Etapas)
                        Milestone.notDeliveryByUser(request().username())       // Etapas não Entregues
                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result showDiscipline(Long id) {
        return ok(
                views.html.Dashboard.Student.discipline.render(
                        User.findByEmail(request().username()),
                        Discipline.getById(id),                                             // Info da Disciplina
                        Project.getAllByDiscipline(id),                                     // Todos os projetos de uma disciplina
                        Milestone.allMilestoneByUserDiscipline(request().username(), id),   // Todas as Milestones de um estudante por disciplina
                        Milestone.nextDeliveryByUserDiscipline(request().username(), id)    // Proximas Milestones a entregar por um estudante por disciplinaProximas Milestones a entregar por um estudante por disciplina
                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result projects(Long id_discipline) {
        // Julgo que a partir do projeto seja possível ir-se buscar todas as milestones e outras informações.
        return ok(
                views.html.Dashboard.Student.projects.render(
                        User.findByEmail(request().username()),
                        Project.getAllByUserDiscipline(request().username(), id_discipline)     // Lista de todos os projetos da discipline para o estudante
                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result showProject(Long id_discipline, Long id) {
        // Até que faça sentido, o ID da disciplina não será usado
        /*
            TODO:
             - Informação do Projeto
             - Lista de Milestones
             - Próximas entregas
             - Milestones não entregues
         */
        return TODO;
    }

    // O Aluno não pode criar um projeto
    @Security.Authenticated(Secured.class)
    public static Result createProject(Long id_discipline) {
        User u = User.findByEmail(request().username());
        return badRequest(views.html.notFound.render(u));
    }

    // O ALuno não pode apagar um projeto
    @Security.Authenticated(Secured.class)
    public static Result deleteProject(Long id_discipline, Long id) {
        User u = User.findByEmail(request().username());
        return badRequest(views.html.notFound.render(u));
    }

    @Security.Authenticated(Secured.class)
    public static Result milestones(Long id_project) {
        /*
            TODO:
                - Mostrar todas as Milestones do Projeto
                - Proximas entregas
                - Notas gerais do aluno/grupo
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result showMilestone(Long id_project, Long id_milestone) {
        /*
            TODO:
                - Informação da Milestone
         */
        return TODO;
    }

    // O Aluno não pode criar uma milestone
    @Security.Authenticated(Secured.class)
    public static Result createMilestone(Long id_project) {
        User u = User.findByEmail(request().username());
        return badRequest(views.html.notFound.render(u));
    }

    // O Aluno não pode apagar uma milestone
    @Security.Authenticated(Secured.class)
    public static Result deleteMilestone(Long id_project, Long id_milestone) {
        User u = User.findByEmail(request().username());
        return badRequest(views.html.notFound.render(u));
    }

    @Security.Authenticated(Secured.class)
    public static Result avaliations(Long id_project,Long id_milestone) {
        /*
            TODO:
                - Mostrar notas da milestone do grupo e aluno
                - Mostrar as datas das entregas

         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result showAvaliation(Long id_project, Long id_milestone, Long id_avaliation) {
        /*
            TODO:
                - Mostrar informação da avaliação
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result createAvaliation(Long id_project,Long id_milestone) {
        /*
            TODO:
                - Enviar AVALIAçAO?
         */
        return TODO;
    }

    // O Aluno não pode apagar uma avaliação
    @Security.Authenticated(Secured.class)
    public static Result deleteAvaliation(Long id_project,Long id_milestone, Long id_avaliation) {
        User u = User.findByEmail(request().username());
        return badRequest(views.html.notFound.render(u));
    }
}
