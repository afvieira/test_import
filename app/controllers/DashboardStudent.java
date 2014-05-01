package controllers;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

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
                        Milestone.findByStudent(request().username()),              // Procurar próximas etapas para entrega
                        Project.getByCreatedDate(request().username()),             // Procurar últimos projetos submetidos
                        StudentMilestone.getLastAvaliations(request().username())   // Últimas Avaliações adicionadas
                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result disciplines() {
        /*
            TODO:
             - Mostrar todas as diciplinas do Aluno
             - Últimos Projetos adicionados
             - Prazo de entrega das próximas Milestones
             - Milestones não submetidas
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result showDiscipline(Long id) {
        /*
            TODO:
             - Info do da Disciplina
             - Lista de Projetos
             - Lista de Milestones
             - Prazo de entrega das próximas Milestones
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result projects() {
        /*
            TODO:
             - Lista de Projetos
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result showProject(Long id) {
        /*
            TODO:
             - Informação do Projeto
             - Lista de Milestones
             - Próximas entregas
             - Milestones não entregues
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result createProject() {
        /*
            TODO:
             - Não tem permissões para criar projeto
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteProject(Long id) {
        /*
            TODO:
             - Não tem permissões para Apagar Projeto
         */
        return TODO;
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

    @Security.Authenticated(Secured.class)
    public static Result createMilestone(Long id_project) {
        /*
            TODO:
             - Sem permissões
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteMilestone(Long id_project, Long id_milestone) {
        /*
            TODO:
             - Sem permissões para Apagar Milestone
         */
        return TODO;
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

    @Security.Authenticated(Secured.class)
    public static Result deleteAvaliation(Long id_project,Long id_milestone, Long id_avaliation) {
        /*
            TODO:
                - NAO!!
         */
        return TODO;
    }
}
