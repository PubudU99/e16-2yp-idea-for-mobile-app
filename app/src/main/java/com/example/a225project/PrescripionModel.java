package com.example.a225project;

public class PrescripionModel  {

    String TabletName;
    String TabletWeight;
    String amount_perMeal;
    String no_of_days;
    String no_of_Meals;

    public PrescripionModel(String tabletName, String tabletWeight,
                            String amount_perMeal, String no_of_days, String no_of_Meals) {
        this.TabletName = tabletName;
        this.TabletWeight = tabletWeight;
        this.amount_perMeal = amount_perMeal;
        this.no_of_days = no_of_days;
        this.no_of_Meals = no_of_Meals;
    }

    public String getTabletName() {
        return TabletName;
    }

    public String getTabletWeight() {
        return TabletWeight;
    }

    public String getAmount_perMeal() {
        return amount_perMeal;
    }

    public String getNo_of_days() {
        return no_of_days;
    }

    public String getNo_of_Meals() {
        return no_of_Meals;
    }
}
