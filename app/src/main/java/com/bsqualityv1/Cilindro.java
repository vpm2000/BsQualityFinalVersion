package com.bsqualityv1;

import java.io.Serializable;
import java.util.Date;

public class Cilindro implements Serializable {

    private int id;
    private String nombreCli;
    private Date fecha;
    private String idCil;
    private String marcaCil;
    private Double diamCil;
    private Double medPist;
    private Boolean soldar;
    private String obsv;

    // Generamos el Constructor

    public Cilindro( Date fecha, String nombreCli,  String idCil,  String marcaCil, Double diamCil, Double medPist, Boolean soldar, String obsv) {
        this.nombreCli = nombreCli;
        this.fecha = fecha;
        this.idCil = idCil;
        this.marcaCil = marcaCil;
        this.diamCil = diamCil;
        this.medPist = medPist;
        this.soldar = soldar;
        this.obsv = obsv;
    }

    public Cilindro(int id, String fecha, String nombreCli,  String idCil, String marcaCil, Double diamCil, Double medPist, String soldar, String obsv) {
        this.id = id;
        this.fecha = new Date();//fecha temporal
        this.nombreCli = nombreCli;

        this.idCil = idCil;
        this.marcaCil = marcaCil;
        this.diamCil = diamCil;
        this.medPist = medPist;
        this.soldar = soldar.equals("1");
        this.obsv = obsv;
    }


    // Getter y Setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdCil() {
        return idCil;
    }

    public void setIdCil(String idCil) {
        this.idCil = idCil;
    }

    public String getMarcaCil() {
        return marcaCil;
    }

    public void setMarcaCil(String marcaCil) {
        this.marcaCil = marcaCil;
    }

    public Double getDiamCil() {
        return diamCil;
    }

    public void setDiamCil(Double diamCil) {
        this.diamCil = diamCil;
    }

    public Double getMedPist() {
        return medPist;
    }

    public void setMedPist(Double medPist) {
        this.medPist = medPist;
    }

    public Boolean getSoldar() {
        return soldar;
    }

    public void setSoldar(Boolean soldar) {
        this.soldar = soldar;
    }

    public String getObsv() {
        return obsv;
    }

    public void setObsv(String obsv) {
        this.obsv = obsv;
    }

    // Genero toString


    @Override
    public String toString() {
        return "Cilindro{" +
                "id=" + id +
                ", nombreCli='" + nombreCli + '\'' +
                ", fecha=" + fecha +
                ", idCil='" + idCil + '\'' +
                ", marcaCil='" + marcaCil + '\'' +
                ", diamCil=" + diamCil +
                ", medPist=" + medPist +
                ", soldar=" + soldar +
                ", obsv='" + obsv + '\'' +
                '}';
    }
}
