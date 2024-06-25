public class GameVerse 
{
    private String custID, custName,paymentType, platformType, platformID, phoneNum, date, subscription, paymentStatus;
    private int duration;

    //accessor
    public GameVerse(String custID, String custName, String paymentType, String platformType, String platformID, String phoneNum, int duration, String date, String subsrcription, String paymentStatus)
    {
        this.custID = custID;
        this.custName = custName;
        this.paymentType = paymentType;
        this.platformType = platformType;
        this.platformID = platformID;
        this.phoneNum = phoneNum;
        this.duration = duration;
        this.date = date;
        this.subscription = subsrcription;
        this.paymentStatus = paymentStatus;
    }
    //mutator
    public void setCustomer(String cID, String cName,String payment,String pType,String pID,String phone,int du, String dat,String sub, String pStatus)
    {
        custID = cID;
        custName = cName;
        paymentType = payment;
        platformType = pType;
        platformID = pID;
        phoneNum = phone;
        duration = du;
        date = dat;
        subscription = sub;
        paymentStatus = pStatus;
    }

    public void setDate(String dates)
    {
        date = dates;
    }

    //accessor
    public String getCustomerID() {return custID;}

    public String getCustomerName() {return custName;}

    public String getPaymentType() {return paymentType;}

    public String getPlatformType() {return platformType;}

    public String getPlatformID() {return platformID;}

    public String getPhoneNum() {return phoneNum;}

    public int getDuration() {return duration;}

    public String getDate() {return date;}

    public String getSubscription() {return subscription;}

    public String getPaymentStatus(){return paymentStatus;}

    public double calculatePrice()
    {   
        double pricePC = 5;
        double priceCons = 7.5;
        double totalPrice = 0.0;
        
        if (getPlatformType().equalsIgnoreCase("PC Gaming")) 
        {
            totalPrice = pricePC * duration;
        } 
        else if (getPlatformType().equalsIgnoreCase("Console")) 
        {
            totalPrice = priceCons * duration;
        }

        return totalPrice;
    }

    public String toString()
    {
        return String.format("%-14s%-30s%-20s%-15s%-15s%-19s%-10s%-15s%-15s%-15s",
                            custID, custName, paymentType, platformType, platformID, phoneNum, duration, date, subscription, paymentStatus);
    }
}
