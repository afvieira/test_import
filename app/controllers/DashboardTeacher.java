package controllers;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by NRAM on 28/04/14.
 */
public class DashboardTeacher extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(
                views.html.Dashboard.Teacher.index.render(
                        // TODO: Página do professor
                        User.findByEmail(request().username()),
                        Discipline.findByUser(request().username()), // Disciplinas Lecionadas
                        Milestone.findByStudent(request().username()),
                        Project.getByCreatedDate(request().username()),
                        StudentMilestone.getLastAvaliations(request().username())
                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result disciplines() {
        /*
            TODO:
             - Mostrar todas as diciplinas do Professor
             - Últimos Projetos adicionados
             - Prazo de entrega das próximas Milestones
             - Alunos que não entregaram projetos
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
             - Alunos que não entregaram projetos
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result projects(Long id_discipline) {
        /*
            TODO:
             - Lista de Projetos
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result showProject(Long id_discipline,Long id) {
        /*
            TODO:
             - Informação do Projeto
             - Lista de Milestones
             - Próximas entregas
             - Alunos que não entregaram
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result createProject(Long id_discipline) {
        /*
            TODO:
             - Criar Projeto (Não esquecer de selecionar os alunos ou grupos)
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteProject(Long id_discipline,Long id) {
        /*
            TODO:
             - Apagar Projeto
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result milestones(Long id_project) {
        /*
            TODO:
                - Mostrar todas as Milestones do Projeto
                - Proximas entregas
                - Notas gerais
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result showMilestone(Long id_project, Long id_milestone) {
        /*
            TODO:
                - Informação da Milestone
                - Alunos/Grupos que já entregaram
                - Alunos/Grupos que faltam entregar
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result createMilestone(Long id_project) {
        /*
            TODO:
             - Criar nova Milestone
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteMilestone(Long id_project, Long id_milestone) {
        /*
            TODO:
             - Apagar Milestone
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result avaliations(Long id_project,Long id_milestone) {
        /*
            TODO:
                - Mostrar notas da milestone por grupo e aluno
                - Mostrar as datas das entregas

         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result showAvaliation(Long id_project, Long id_milestone) {
        /*
            TODO:
                - Mostrar informação da avaliação
         */
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result createAvaliation(Long id_project,Long id_milestone) {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteAvaliation(Long id_project,Long id_milestone) {
        return TODO;
    }
}
