package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by NRAM on 01/05/14.
 */
public class Profiles extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return TODO;
    }

    @Security.Authenticated(Secured.class)
    public static Result showProfile(Long id) {
        return TODO;
    }

}
