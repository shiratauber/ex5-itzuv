
import java.util.Comparator;

import static java.lang.Long.parseLong;

public class Product
{
    private long ProductId;
    private String name;
    private ProductCategory category;
    private double price;

    public Product(String orderInfo)
    {
        String[] arr=orderInfo.split(" ");
        ProductId= Long.parseLong(arr[1]);
        name=arr[2];
        switch (arr[4]){
            case   "PC":
            {
                category= ProductCategory.PC;
                break;
            }
            case "Printers":
            {
                category= ProductCategory.Printers;
                break;
            }
            case "Displays":
            {
                category= ProductCategory.Displays;
                break;
            }
            case "Network":
            {
                category= ProductCategory.Network;
                break;
            }
            case "Accessories":
            {
                category= ProductCategory.Accessories;
                break;
            }
            case "Software":
            {
                category= ProductCategory.Software;
                break;
            }
            case "Gaming":
            {
                category= ProductCategory.Gaming;
                break;
            }
            case "Storage":
            {
                category= ProductCategory.Storage;
                break;
            }
            case "Cameras":
            {
                category= ProductCategory.Cameras;
                break;
            }

            default:   category= ProductCategory.PC; break;


        }
        price= Double.parseDouble(arr[6]);

    }

    public Product(long PId, String Pname, ProductCategory Pcategory, double Pprice)
    {
        setProductId(PId);
        setName(Pname);
        setCategory(Pcategory);
        setPrice(Pprice);

    }


    public String toString()
    {
        return "Product: "+ getProductId() + " "+ getName() +" category: "+ getCategory() + " price: "+ getPrice()+"\n";
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}