package cpsc2150.banking;

import cpsc2150.banking.controllers.IMortgageController;
import cpsc2150.banking.controllers.MortgageGUIController;
import cpsc2150.banking.views.IMortgageView;
import cpsc2150.banking.views.MortgageGUIView;

public class MortgageGUIApp {
    public static void main(String[] args){
        IMortgageView view = new MortgageGUIView();
        IMortgageController controller = new MortgageGUIController(view);
        view.setController(controller);
    }
}
