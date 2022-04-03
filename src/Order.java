

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static java.lang.Long.parseLong;

public class Order {

    private long orderId;
    private Date orderDate;
    private Date deliveryDate;
    private OrderStatus status;

    private long customrId;

    public Order(String orderInfo) {
        String[] arr=orderInfo.split(" ");
        orderId=Long.parseLong(arr[1]);
        try{
        orderDate=new SimpleDateFormat("dd/MM/yyyy").parse(arr[4]);
        deliveryDate=new SimpleDateFormat("dd/MM/yyyy").parse(arr[7]);}
        catch(Exception e)
        {
            System.out.println("problem with the date");
        }
        switch (arr[9]){
         case   "Complete":
            {
              status= OrderStatus.Complete;
              break;
            }
            case "Pickup":
            {
                status= OrderStatus.Pickup;
                break;
            }
            case "Processing":
            {
                status= OrderStatus.Processing;
                break;
            }
            case "Cancelled":
            {
                status= OrderStatus.Cancelled;
                break;
            }
            case "AwaitingPayment":
            {
                status= OrderStatus.AwaitingPayment;
                break;
            }
            default:           status= OrderStatus.Complete; break;

        }

        customrId=Long.parseLong(arr[12]);


    }

    public Order(long Oid, Date OorderDate, Date OdeliveryDate, OrderStatus Ostatus, long OcustomrId)
    {
        setOrderId(Oid);
        setOrderDate(OorderDate);
        setDeliveryDate(OdeliveryDate);
        setStatus(Ostatus);
        setCustomrId(OcustomrId);
    }



    public String toString()
    {
        SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
        return "order: "+ getOrderId() + " order date: "+ ft.format(getOrderDate()) +" delivery date: "+ ft.format(getDeliveryDate()) + " status: "+ getStatus() + " customr id: "+ getCustomrId()+"\n";
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public long getCustomrId() {
        return customrId;
    }

    public void setCustomrId(long customrId) {
        this.customrId = customrId;
    }
}