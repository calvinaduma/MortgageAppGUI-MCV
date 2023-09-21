package cpsc2150.banking.controllers;

import cpsc2150.banking.models.Customer;
import cpsc2150.banking.models.ICustomer;
import cpsc2150.banking.models.IMortgage;
import cpsc2150.banking.models.Mortgage;
import cpsc2150.banking.views.IMortgageView;

public class MortgageGUIController implements IMortgageController{
    private IMortgageView view;
    int MAX_CREDIT_SCORE = 850;

    public MortgageGUIController(IMortgageView v){
        view = v;
    }

    public void submitApplication(){

        // checks to make sure data user gives is correct
        String name = view.getName();
        double income = view.getYearlyIncome();
        if (income <= 0) {
            view.printToUser("Income must be greater than 0.");
            income = view.getYearlyIncome();
        }
        double monthlyDebt = view.getMonthlyDebt();
        if (monthlyDebt < 0) {
            view.printToUser("Debt must be greater than or equal to 0");
            monthlyDebt = view.getMonthlyDebt();
        }
        int creditScore = view.getCreditScore();
        if (creditScore <= 0 || creditScore >= MAX_CREDIT_SCORE) {
            view.printToUser("Credit Score must be greater than 0 and less than 850.");
            creditScore = view.getCreditScore();
        }
        double houseCost = view.getHouseCost();
        if (houseCost <= 0) {
            view.printToUser("Cost must be greater than 0.");
            houseCost = view.getHouseCost();
        }
        double downPayment = view.getDownPayment();
        if (downPayment <= 0 || downPayment > houseCost) {
            view.printToUser("Down Payment must be greater than 0 and less than the cost of the house.");
            downPayment = view.getDownPayment();
        }
        int years = view.getYears();
        if (years <= 0) {
            view.printToUser("Years must be greater than 0.");
            years = view.getYears();
        }
        // use model layer apply for mortgage
        // creation of customer
        ICustomer customer = new Customer(monthlyDebt, income, creditScore, name);
        // creation of mortgage
        IMortgage customerMortgage = new Mortgage(houseCost, downPayment, years, customer);
        // apply for mortgage
        customer.applyForLoan(downPayment, houseCost, years);
        // say if loan was approved
        if (customerMortgage.loanApproved()){
            view.displayPayment(customer.getMonthlyPay());
            view.displayRate(customer.getRate());
            view.displayApproved(true);
        } else {
            view.displayPayment(0.0);
            view.displayRate(0.0);
            view.displayApproved(false);
        }
        // if yes display rate and mont payments
        // lese display 0 for rate and mont payments


    }
}
