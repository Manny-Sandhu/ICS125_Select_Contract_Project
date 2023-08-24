package selectcontract08;

/**
 *
 * @author C0397554
 */
public class SelectContract {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ContractView theView = new ContractView();
        ContractModel theModel = new ContractModel();
        ContractController theController;
        theController = new ContractController(theView, theModel);
        theView.setVisible(true);
    }
    
}
