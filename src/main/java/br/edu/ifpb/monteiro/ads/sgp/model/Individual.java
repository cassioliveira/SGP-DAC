/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.monteiro.ads.sgp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class to modelling the Individual entity, making heritage with Client class,
 * by JOINED strategy.
 *
 * @author Cássio Oliveira
 * @author Wilde Arruda
 */
@br.edu.ifpb.monteiro.ads.sgp.model.qualifiers.IndividualQlf
@Entity 
@PrimaryKeyJoinColumn(name = "id")
public class Individual extends Client implements Serializable, Identifiable{

    @Id
    @SequenceGenerator(name="individual_id_seq",
                       sequenceName="individual_id_seq",
                       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator="individual_id_seq")
    @Column(name = "id", updatable=false)
    private Long id;

    @Column(name = "client_gender", length = 1)
    private char gender;

    @Column(name = "client_cpf", nullable = false, length = 11, unique = true)
    private char cpf;

    @Column(name = "client_doc_travel_number", nullable = false, length = 15)
    private String docTravelNumber;

    @Column(name = "client_travel_doc_type", nullable = false, length = 10)
    private String travelDocType;

    @Column(name = "client_issuing_body", nullable = false, length = 15)
    private String issuingBoddy;

    @Column(name = "client_profession", length = 50)
    private String profession;

    @Temporal(TemporalType.DATE)
    @Column(name = "client_birthdate")
    private Date birthDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * @return the cpf
     */
    public char getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(char cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the docTravelNumber
     */
    public String getDocTravelNumber() {
        return docTravelNumber;
    }

    /**
     * @param docTravelNumber the docTravelNumber to set
     */
    public void setDocTravelNumber(String docTravelNumber) {
        this.docTravelNumber = docTravelNumber;
    }

    /**
     * @return the travelDocType
     */
    public String getTravelDocType() {
        return travelDocType;
    }

    /**
     * @param travelDocType the travelDocType to set
     */
    public void setTravelDocType(String travelDocType) {
        this.travelDocType = travelDocType;
    }

    /**
     * @return the issuingBoddy
     */
    public String getIssuingBoddy() {
        return issuingBoddy;
    }

    /**
     * @param issuingBoddy the issuingBoddy to set
     */
    public void setIssuingBoddy(String issuingBoddy) {
        this.issuingBoddy = issuingBoddy;
    }

    /**
     * @return the profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * @param profession the profession to set
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Individual)) {
            return false;
        }
        Individual other = (Individual) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifpb.monteiro.ads.sgp.model.Individual[ id=" + id + " ]";
    }

}
