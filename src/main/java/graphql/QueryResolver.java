package graphql;

import business.ModuleBusiness;
import business.UniteEnseignementBusiness;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import entities.Module;
import entities.UniteEnseignement;

import java.util.List;

public class QueryResolver implements GraphQLQueryResolver {

    private UniteEnseignementBusiness ueHelper = new UniteEnseignementBusiness();
    private ModuleBusiness moduleHelper = new ModuleBusiness();

    // ===== UniteEnseignement =====
    public List<UniteEnseignement> allUEs() {
        return ueHelper.getListeUE();
    }

    public UniteEnseignement searchByCode(int code) {
        return ueHelper.getUEByCode(code);
    }

    // ===== Module =====
    public List<Module> getAllModules() {
        return moduleHelper.getAllModules();
    }

    // SEARCH BY DOMAINE
    public List<UniteEnseignement> searchByDomaine(String domaine) {
        return ueHelper.getUEByDomaine(domaine);
    }

    // SEARCH BY SEMESTRE
    public List<UniteEnseignement> searchBySemestre(int semestre) {
        return ueHelper.getUEBySemestre(semestre);
    }

    public Module searchModuleByMatricule(String matricule) {
        return moduleHelper.getModuleByMatricule(matricule);
    }

    public List<Module> searchModulesByType(Module.TypeModule type) {
        return moduleHelper.getModulesByType(type);
    }
}