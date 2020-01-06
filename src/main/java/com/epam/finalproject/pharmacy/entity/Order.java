package com.epam.finalproject.pharmacy.entity;

import java.util.List;

public class Order implements Identifable{
    private final Client pacient;
    private List<Medicament> medicines;
    private int totalPrice;
    private boolean paid;

    public Order(Client pacient, List<Medicament> medicines, int totalPrice, boolean paid) {
        this.pacient = pacient;
        this.medicines = medicines;
        this.totalPrice = totalPrice;
        this.paid = paid;
    }

    public Client getPacient() {
        return pacient;
    }

    public List<Medicament> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicament> medicines) {
        this.medicines = medicines;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public long getId() {
        return 0;
    }
}
