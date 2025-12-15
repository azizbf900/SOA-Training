package graphql;

import business.ModuleBusiness;
import business.UniteEnseignementBusiness;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import entities.Module;
import entities.UniteEnseignement;

public class MutationResolver implements GraphQLMutationResolver {

    private UniteEnseignementBusiness ueHelper = new UniteEnseignementBusiness();
    private ModuleBusiness moduleHelper = new ModuleBusiness();

    // ===== UniteEnseignement =====
    public boolean addUniteEnseignement(int code, String domaine, String responsable, int credits, int semestre) {
        return ueHelper.addUniteEnseignement(
                new UniteEnseignement(code, domaine, responsable, credits, semestre)
        );
    }

    public boolean deleteUniteEnseignement(int code) {
        return ueHelper.deleteUniteEnseignement(code);
    }

    public boolean updateUniteEnseignement(int code, String domaine, String responsable, int credits, int semestre) {
        UniteEnseignement updatedUE =
                new UniteEnseignement(code, domaine, responsable, credits, semestre);
        return ueHelper.updateUniteEnseignement(code, updatedUE);
    }

    // ===== Module =====
    public boolean addModule(String matricule, String nom, int coefficient,
                             int volumeHoraire, Module.TypeModule type, int ueCode) {

        UniteEnseignement ue = new UniteEnseignement();
        ue.setCode(ueCode);

        return moduleHelper.addModule(
                new Module(matricule, nom, coefficient, volumeHoraire, type, ue)
        );
    }

    public boolean updateModule(String matricule, String nom, int coefficient,
                                int volumeHoraire, Module.TypeModule type, int ueCode) {

        UniteEnseignement ue = new UniteEnseignement();
        ue.setCode(ueCode);

        return moduleHelper.updateModule(
                matricule,
                new Module(matricule, nom, coefficient, volumeHoraire, type, ue)
        );
    }

    public boolean deleteModule(String matricule) {
        return moduleHelper.deleteModule(matricule);
    }
}