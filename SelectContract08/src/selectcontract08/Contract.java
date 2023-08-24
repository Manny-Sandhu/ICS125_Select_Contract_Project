package selectcontract08;

/**
 *
 * @author manin
 */
public class Contract {
    String contractID;
    String originCity;
    String destCity;
    String orderItem;
    public static final int NUMBER_OF_CONTRACT_ATTRIBUTE = 4;
    public static final int INDEX_OF_CONTRACT_ID = 0;
    public static final int INDEX_OF_ORIGIN_CITY = 1;
    public static final int INDEX_OF_DEST_CITY = 2;
    public static final int INDEX_OF_ORDER_ITEM = 3;
    
    public Contract(String contractID, String originCity, String destCity, String orderItem){
        this.contractID = contractID;
        this.originCity = originCity;
        this.destCity = destCity;
        this.orderItem = orderItem;
    }
    // getters and setters //
    public String getContractID(){return this.contractID;}    
    public void setContactID(String val){this.contractID = val;}
    
    public String getOriginCity(){return this.originCity;}    
    public void setOriginCity(String val){this.originCity = val;}
    
    public String getDestCity(){return this.destCity;}    
    public void setDestCity(String val){this.destCity = val;}
    
    public String getOrderItem(){return this.orderItem;}    
    public void setOrderItem(String val){this.orderItem = val;}
    
    // contains method used for originCityList 
    boolean contains(String city) {return city.equals(originCity);}
}
