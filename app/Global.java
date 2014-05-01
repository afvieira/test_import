import com.avaje.ebean.Ebean;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

import java.util.List;
import java.util.Map;

/**
 * Created by afv on 25/04/14.
 */
public class Global extends GlobalSettings {

    public void onStart(Application app) {
        InitialData.insert(app);
    }

    static class InitialData {

        public static void insert(Application app) {
            if(Ebean.find(User.class).findRowCount() == 0) {


                @SuppressWarnings("unchecked")
                Map<String,List<Object>> all = (Map<String,List<Object>>) Yaml.load("initial-data.yml");

                //Users
                Ebean.save(all.get("users"));

                //turnos
                Ebean.save(all.get("shifts"));

                //disciplines
//                Ebean.save(all.get("disciplines"));
//                for(Object shift: all.get("disciplines")) {
//                    // Insert the shift/student relation
//                    Ebean.saveManyToManyAssociations(shift, "students");
//                }

                //disciplines
                Ebean.save(all.get("disciplines"));

                //Courses
                Ebean.save(all.get("courses"));

                //StudentProjects
                Ebean.save(all.get("studentprojects"));

                // Projects
                Ebean.save(all.get("projects"));

                // Milestones
                Ebean.save(all.get("milestones"));



            }
        }

    }

}

