package br.edu.ifpb.monteiro.ads.sgp.controllers;

import br.edu.ifpb.monteiro.ads.sgp.model.Login;
import br.edu.ifpb.monteiro.ads.sgp.services.LoginServicesIF;
import br.edu.ifpb.monteiro.ads.sgp.services.ServicesIF;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("loginController")
@RequestScoped
public class LoginController extends GenericController<Login> implements LoginControllerIF {

    @Inject
    private LoginServicesIF services;
    
    @Inject @br.edu.ifpb.monteiro.ads.sgp.model.qualifiers.Login
    private Login selected;
        
    @Override
    public Login getSelected() {
       return selected;
    }

    @Override
    public void setSelected(Login selected) {
        this.selected = selected;
    }
 

    @Override
    protected ServicesIF getServices() {
        return services;
    }


    @FacesConverter(forClass = Login.class)
    public static class LoginControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LoginController controller = (LoginController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "loginController");
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
            if (object instanceof Login) {
                Login o = (Login) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Login.class.getName()});
                return null;
            }
        }

    }

}
