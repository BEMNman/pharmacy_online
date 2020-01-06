package com.epam.finalproject.pharmacy.entity;

import java.util.Date;
import java.util.List;

public class Recipe implements Identifable{
    private final Client doctor;
    private final Client pacient;
    private final Date dateIssue;
    private final Date dateExp;
    private List<Medicament> medicines;

    public Recipe(Client doctor, Client pacient, Date dateIssue, Date dateExp, List<Medicament> medicines) {
        this.doctor = doctor;
        this.pacient = pacient;
        this.dateIssue = dateIssue;
        this.dateExp = dateExp;
        this.medicines = medicines;
    }

    public Client getDoctor() {
        return doctor;
    }

    public Client getPacient() {
        return pacient;
    }

    public Date getDateIssue() {
        return dateIssue;
    }

    public Date getDateExp() {
        return dateExp;
    }

    public List<Medicament> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicament> medicines) {
        this.medicines = medicines;
    }

    @Override
    public long getId() {
        return 0;
    }
}
