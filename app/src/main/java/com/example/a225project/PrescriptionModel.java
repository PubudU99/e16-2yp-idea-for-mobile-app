package com.example.a225project;

public class PrescriptionModel {
    String TabletName;
    String TabletWeight;
    String TabletAmount;
    String NoOfDays;
    String termsPerDay;

    public PrescriptionModel(String tabletName, String tabletWeight,
                             String tabletAmount, String noOfDays, String termsPerDay) {
        this.TabletName = tabletName;
        this.TabletWeight = tabletWeight;
        this.TabletAmount = tabletAmount;
        this.NoOfDays = noOfDays;
        this.termsPerDay = termsPerDay;
    }

    public String getTabletName() {
        return TabletName;
    }

    public String getTabletWeight() {
        return TabletWeight;
    }

    public String getTabletAmount() {
        return TabletAmount;
    }

    public String getNoOfDays() {
        return NoOfDays;
    }

    public String getTermsPerDay() {
        return termsPerDay;
    }
}
