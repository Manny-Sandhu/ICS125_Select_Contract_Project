package selectcontract08;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author C0397554
 */
class ContractController {

    private ContractView theView;
    private ContractModel theModel;
    
    ContractController(ContractView theView, ContractModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.theView.addMenuListener(new MenuButtonListener());
        this.theView.addViewBidListener(new ViewBidButtonListener());
        this.theView.addPrevListener(new PrevButtonListener());
        this.theView.addBidListener(new BidButtonListener());
        this.theView.addNextListener(new NextButtonListener());
        this.theView.addComboBoxListener(new ComboListener());
        this.theView.setOriginCityList(this.theModel.getOriginCityList());
        setUpDisplay();
    }
    
    private void setUpDisplay(){
        try{
            // adds the contract information to the current view
            if(theModel.getContractCount() != 0){
                Contract c = theModel.getTheContract();
                theView.setContractID(c.getContractID());
                theView.setDestCity(c.getDestCity());
                theView.setOriginCity(c.getOriginCity());
                theView.setOrderItem(c.getOrderItem());
                theView.updateContractViewPanel( theModel.getCurrentCount(),
                                                 theModel.getContractCount());
            // if there is a probel with the contract file or there is missing information 
            }else {
                theView.setContractID("???");
                theView.setDestCity("???");
                theView.setOriginCity("???");
                theView.setOrderItem("???");
            }   // end of try   
        }catch(Exception ex){
            System.out.println(ex);
            theView.displayErrorMessage(
                    "Error: There is a problem setting up a contract \n"
                    + "     Contract number: " + theModel.getCurrentCount());
        }   // end of catch
        // disable and enable butons based on current contract
        if(theModel.getContractCount() == 1){
            theView.getPrev().setEnabled(false);
            theView.getNext().setEnabled(false);
        } else if(theModel.getCurrentCount() == 0){
            theView.getPrev().setEnabled(false);
            theView.getNext().setEnabled(true);
        } else if (theModel.getCurrentCount() == theModel.getContractCount()-1){
            theView.getPrev().setEnabled(true);
            theView.getNext().setEnabled(false);
        } else {
            theView.getPrev().setEnabled(true);
            theView.getNext().setEnabled(true);
        }
    }   // end of setUpDisplay
    
    class PrevButtonListener implements ActionListener {
        // goes to the previous contract
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                theModel.prevContract();
            } catch(Exception ex){
                System.out.println(ex);
                theView.displayErrorMessage(
                        "Error: There is a problem setting a previous contract");
            } // end of try catch
            setUpDisplay(); // sets up the display again
        }
    }   // end of PrevButtonListener Class
    
    class NextButtonListener implements ActionListener {
        // goes to the next contract
        @Override
        public void actionPerformed(ActionEvent e) { 
            try{
                theModel.nextContract();
            } catch(Exception ex){
                System.out.println(ex);
                theView.displayErrorMessage(
                        "Error: There is a problem setting a next contract");
            } // end of try catch
            setUpDisplay(); // sets up the display again
        }
    }   // end of NextButtonListener Class
    
    class BidButtonListener implements ActionListener {
        // opens the ConfirmBid View
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                // create new ConfirmBid view
                ConfirmBid cb;
                cb = new ConfirmBid(theView, true,theModel.getTheContract());
                cb.setLocationRelativeTo(null);
                cb.setVisible(true);
            } catch(Exception ex){
                System.out.println(ex);
                theView.displayErrorMessage(
                        "Error: The numbers entered must be integers");
            } // end of try catch
            setUpDisplay(); // set up display again
        }
    } // end of BidButtonListener Class
    
    class ComboListener implements ItemListener {
        // sets the current view to only have contracts with selected originCity
        @Override
        public void itemStateChanged(ItemEvent e) {
            // item is the combobox Item currently selected
            System.out.println(e.getItem().toString());
            // change if the selected item changes
            if(e.getStateChange() == ItemEvent.SELECTED){
                String selectedCity = e.getItem().toString();
                System.out.println(selectedCity);
                theModel.updateContractList(selectedCity);
                setUpDisplay();
            }
        }
    }   // end of ComboBoxListener Class
    
    class MenuButtonListener implements ActionListener {
        // opens the new contract view when pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                NewContract nc;
                nc = new NewContract(theView, true,theModel.getTheContract());
                nc.setLocationRelativeTo(null);
                nc.setVisible(true);
            } catch(Exception ex){
                System.out.println(ex);
                theView.displayErrorMessage(
                        "Error: The numbers entered must be integers");
            }
            // remakes the contract model with the new contract added
            theModel = new ContractModel();
            setUpDisplay();
        } 
    }   // end of MenuButtonListener Class
    
    class ViewBidButtonListener implements ActionListener {
        // opens the ViewBids view
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                ViewBids vb;
                vb = new ViewBids(theView, true);
                vb.setLocationRelativeTo(null);
                vb.setVisible(true);
            } catch(IOException | ParseException ex){
                System.out.println(ex);
                theView.displayErrorMessage(
                        "Error: The numbers entered must be integers");
            }
        }
    }// end of the ViewBidButtonListener
}// end of the ContractController Class
