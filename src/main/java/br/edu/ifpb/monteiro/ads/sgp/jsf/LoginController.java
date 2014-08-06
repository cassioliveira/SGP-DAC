package br.edu.ifpb.monteiro.ads.sgp.jsf;

import br.edu.ifpb.monteiro.ads.sgp.model.Login;
import br.edu.ifpb.monteiro.ads.sgp.jsf.util.JsfUtil;
import br.edu.ifpb.monteiro.ads.sgp.jsf.util.JsfUtil.PersistAction;
import br.edu.ifpb.monteiro.ads.sgp.dao.LoginDAO;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private br.edu.ifpb.monteiro.ads.sgp.dao.LoginDAO ejbFacade;
    private List<Login> items = null;
    private Login selected;

    public LoginController() {
    }

    public Login getSelected() {
        return selected;
    }

    public void setSelected(Login selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LoginDAO getFacade() {
        return ejbFacade;
    }

    public Login prepareCreate() {
        selected = new Login();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("resources/Bundle").getString("LoginCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("resources/Bundle").getString("LoginUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("resources/Bundle").getString("LoginDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Login> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Login getLogin(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Login> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Login> getItemsAvailableSelectOne() {
        return getFacade().findAll();
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
            return controller.getLogin(getKey(value));
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
