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
                        Discipline.findByUser(request().username()),                // Disciplinas Lecionadas
                        Project.getByTeacherDisciplines(request().username()),      // Todos os projetos das disciplinas lecionadas por um dado professor
                        Milestone.getAllMilestoneByAllDisciplinesTeacher(request().username()), //Todas as etapas de projetos das disciplinas lecionadas por um professor
                        Project.getByTeacher(request().username()),                 // Todos os projetos criados por um professor
                        Milestone.getAllMilestoneByTeacher(request().username()),   // Todas as etapas de projetos criados por um professor
                        StudentMilestone.getLastAvaliations(request().username())
                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result disciplines() {
        return ok(
                views.html.Dashboard.Teacher.disciplines.render(
                        User.findByEmail(request().username()),
                        Discipline.findByUser(request().username()), // Disciplinas Lecionadas
                        Project.getByTeacherDisciplines(request().username()), // Todos os projetos das disciplinas lecionadas por um dado professor
                        Milestone.getAllMilestoneByAllDisciplinesTeacher(request().username()), // Todas as milestones dos projetos das disciplinas
                        Milestone.nextDeliveriesMilestoneByAllDisciplinesTeacher(request().username()) // Próximas milestones dos projetos das disciplinas que leciona

                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result showDiscipline(Long id) {
        return ok(
                views.html.Dashboard.Teacher.discipline.render(
                        User.findByEmail(request().username()),
                        Discipline.getById(id),                                 // Informação da Disciplina
                        Project.getAllByDiscipline(id),                         // Todos os projetos da disciplina
                        Milestone.allMilestonesByDiscipline(id),                // Todas as milestones dos projetos de uma disciplina
                        Milestone.nextDeliveryMilestoneByDiscipline(id)         // Próximas milestones a entregar dos projetos de uma disciplina

                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result projects(Long id_discipline) {
        return ok(
                views.html.Dashboard.Teacher.projects.render(
                        User.findByEmail(request().username()),
                        Project.getAllByDiscipline(id_discipline),              // Todos os projetos da disciplina
                        Milestone.allMilestonesByDiscipline(id_discipline),      // Todas as milestones dos projetos da disciplina
                        Milestone.nextDeliveryMilestoneByDiscipline(id_discipline)         // Próximas milestones a entregar dos projetos de uma disciplina
                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result showProject(Long id_discipline,Long id) {
        return ok(
                views.html.Dashboard.Teacher.project.render(
                        User.findByEmail(request().username()),
                        Project.getById(id),                                    // Vai buscar a informação de um projeto
                        Milestone.allMilestonesByProject(id),                   // Todas as milestones de um projeto
                        Milestone.nextDeliveriesByProject(id)                   // Próximas milestones a entregar de um projeto
                        // TODO: Saber os Grupos e alunos que não entregaram etapas
                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result createProject(Long id_discipline) {
        /*
            TODO:
             - Criar Projeto (Não esquecer de selecionar os alunos ou grupos)
         */
        // Não sei como é para fazer neste método
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteProject(Long id_discipline,Long id) {
        /*
            TODO:
             - Criar Projeto (Não esquecer de selecionar os alunos ou grupos)
         */
        // Não sei como é para fazer neste método
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result milestones(Long id_project) {
        return ok(
                views.html.Dashboard.Teacher.milestones.render(
                        User.findByEmail(request().username()),
                        Project.getById(id),                                    // Vai buscar a informação de um projeto
                        Milestone.allMilestonesByProject(id),                   // Todas as milestones de um projeto
                        Milestone.nextDeliveriesByProject(id)                   // Próximas milestones a entregar de um projeto
                        // TODO: Saber os Grupos e alunos que não entregaram etapas
                        // TODO: Notas Gerais
                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result showMilestone(Long id_project, Long id_milestone) {
        /*
            TODO:
                - Informação da Milestone
                - Alunos/Grupos que já entregaram
                - Alunos/Grupos que faltam entregar
         */
        return ok(
                views.html.Dashboard.Teacher.milestones.render(
                        User.findByEmail(request().username()),
                        Project.getById(id_project),                                    // Vai buscar a informação de um projeto
                        Milestone.getById(id_milestone)                                // Informação da milestone
                        // TODO: Saber os Grupos e alunos que não entregaram esta etapa
                        // TODO: Notas Gerais
                )
        );
    }

    @Security.Authenticated(Secured.class)
    public static Result createMilestone(Long id_project) {
        /*
            TODO:
             - Criar nova Milestone
         */
        // Não sei como é para fazer neste método
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteMilestone(Long id_project, Long id_milestone) {
        /*
            TODO:
             - Apagar Milestone
         */
        // Não sei como é para fazer neste método
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result avaliations(Long id_project,Long id_milestone) {
        /*
            TODO:
                - Mostrar notas da milestone por grupo e aluno
                - Mostrar as datas das entregas

         */
        return ok(
                views.html.Dashboard.Teacher.avaliations.render(
                        User.findByEmail(request().username()),
                        Project.getById(id_project),                                    // Vai buscar a informação de um projeto
                        Milestone.getById(id_milestone),                                // Informação da milestone
                        StudentMilestone.getByMilestone(id_milestone)
                )
        );
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

    @Security.Authenticated(Secured.class)
    public static Result showAvaliationByGroup(Long id_project, Long id_milestone, Long id_group) {
        // Verificar se pertence à milestone
        GroupMilestone gm = GroupMilestone.getByGroupMilestone(id_milestone, id_group);
        User u = User.findByEmail(request().username());
        if(gm != null){
            // TODO: Se calhar é melhor criar outra VIEW
            return ok(
                    views.html.Dashboard.Teacher.avaliation.render(
                            u,
                            gm
                    )
            );
        }

        // TODO: Não sei se é a melhor maneira de tratar este assunto. (Quando não pertence à milestone)
        return badRequest(views.html.notFound.render(u));
    }

    @Security.Authenticated(Secured.class)
    public static Result showAvaliationByStudent(Long id_project, Long id_milestone, Long id_student) {
        // Verificar se pertence à milestone
        StudentMilestone sm = StudentMilestone.getByStudentMilestone(id_milestone, id_student);
        User u = User.findByEmail(request().username());
        if(sm != null){
            // TODO: Se calhar é melhor criar outra VIEW
            return ok(
                    views.html.Dashboard.Teacher.avaliation.render(
                            u,
                            sm
                    )
            );
        }

        // TODO: Não sei se é a melhor maneira de tratar este assunto. (Quando não pertence à milestone)
        return badRequest(views.html.notFound.render(u));
    }
}
