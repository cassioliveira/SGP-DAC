package br.edu.ifpb.monteiro.ads.sgp.controllers;

import br.edu.ifpb.monteiro.ads.sgp.model.Individual;
import br.edu.ifpb.monteiro.ads.sgp.model.Identifiable;
import br.edu.ifpb.monteiro.ads.sgp.services.IndividualServicesIF;
import br.edu.ifpb.monteiro.ads.sgp.services.ServicesIF;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("individualController")
@SessionScoped
public class IndividualController extends AbstractController<Individual> implements IndividualControllerIF {

    @Inject
    private IndividualServicesIF services;
    
 
    
    @Override
    public Identifiable prepareCreate() {
        setSelected(new Individual());
        initializeEmbeddableKey();
        return getSelected();
    }

    @Override
    protected ServicesIF getServices() {
        return services;
    }


    @FacesConverter(forClass = Individual.class)
    public static class IndividualControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IndividualController controller = (IndividualController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "individualController");
            return controller.getItem(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Individual) {
                Individual o = (Individual) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Individual.class.getName()});
                return null;
            }
        }

    }

}
