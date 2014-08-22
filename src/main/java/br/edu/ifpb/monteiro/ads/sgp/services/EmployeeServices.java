/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.monteiro.ads.sgp.services;

import br.edu.ifpb.monteiro.ads.sgp.dao.EmployeeDaoIF;
import br.edu.ifpb.monteiro.ads.sgp.dao.qualifiers.EmployeeDAO;
import br.edu.ifpb.monteiro.ads.sgp.model.Identifiable;
import br.edu.ifpb.monteiro.ads.sgp.util.jpa.Transactional;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author WitaloCarlos
 */
@SessionScoped
public class EmployeeServices implements EmployeeServicesIF {

    @Inject
    @EmployeeDAO
    private EmployeeDaoIF employeeDao;

    @Override
    public int count() {
        return employeeDao.count();
    }

    @Transactional
    @Override
    public void create(Identifiable entity) {
        
        try{
        this.employeeDao.create(entity);
        }catch (Exception e) {
            System.err.println("Erro no Service: "+e.getMessage());
        }
    }

    @Override
    public void edit(Identifiable entity) {
        try{
        this.employeeDao.edit(entity);
        }catch (Exception e) {
            System.err.println("Erro no Service: "+e.getMessage());
        }
    }

    @Override
    public Identifiable find(Object id) {
        return employeeDao.find(id);
    }

    @Override
    public List<Identifiable> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public List<Identifiable> findRange(int[] range) {
        return employeeDao.findRange(range);
    }

    @Override
    public void remove(Identifiable entity) {
        employeeDao.remove(entity);
    }

}
