package selectcontract08;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author C0397554
 */
class ContractModel {
    private ArrayList<Contract> theContracts;
    private final ArrayList<Contract> theContractsAll;
    private int contractCounter;
    private SortedSet<String> originCityList;
    
    public ContractModel(){
        theContracts = new ArrayList<>();
        contractCounter = 0;
        originCityList = new TreeSet<>();
        try {
            // reads the contracts.xml file and makes a document object for the contractList
            File inputFile = new File("C:\\Users\\manin\\OneDrive - Camosun College\\ICS 125\\selectcontract08\\SelectContract08\\src\\selectcontract08\\contracts.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("contract");
            
            // read each contract in the contracList and add each element to a differnt String
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                   Element eElement = (Element) nNode;
              
                   // assign each element to a differnt String
                   String contractID = eElement.getElementsByTagName("contractID").item(0).getTextContent();
                   String originCity = eElement.getElementsByTagName("originCity").item(0).getTextContent();
                   String destCity = eElement.getElementsByTagName("destCity").item(0).getTextContent();
                   String orderItem = eElement.getElementsByTagName("orderItem").item(0).getTextContent();

                // use the Strings to make a new Conract object and add the contract to theContracts
                Contract dataContract = new Contract(contractID, originCity
                                                       , destCity, orderItem);
                theContracts.add(dataContract);
                originCityList.add(originCity);
                }
            }
            // add all to the originCityList
            originCityList.add("All");
            
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {}
        theContractsAll = theContracts;
        
    }
    
    public boolean foundContracts(){
        return (theContracts.size() >= 1);
    }
    
    public Contract getTheContract() {
        return theContracts.get(contractCounter);
    }
    
    public int getContractCount() {
        return theContracts.size();
    }
    
    public int getCurrentCount(){
        return contractCounter;
    }
    
    public void nextContract() {
        if(contractCounter <= theContracts.size()){
            contractCounter++;
        }
    }
    
    public void prevContract() {
        if(contractCounter > 0){
            contractCounter--;
        }
    }
    
    public String[] getOriginCityList(){
        String[] a;
        a = originCityList.toArray(new String[originCityList.size()]);
        return a;
    }
    
    public void updateContractList(String city){
        theContracts = new ArrayList<>(theContractsAll);
        if(city != "All"){
            theContracts.removeIf(s -> !s.contains(city));
        }
        contractCounter = 0;
    }    
}
