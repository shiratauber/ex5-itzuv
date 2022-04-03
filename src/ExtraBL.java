

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

public class ExtraBL implements IBL {
    @Override
    public Product getProductById(long productId) {
      List<Product> p=  DataSource.allProducts.stream().filter(product -> product.getProductId()==productId).collect(Collectors.toList());
        if (p.size()==0)
        {
            return null;
        }
        if (p.size()==1)
        {
            return p.get(0);
        }
      return null;
    }

    @Override
    public Order getOrderById(long orderId) {
        List<Order> p=  DataSource.allOrders.stream().filter(order -> order.getOrderId()==orderId).collect(Collectors.toList());
        if (p.size()==0)
        {
            return null;
        }
        if (p.size()==1)
        {
            return p.get(0);
        }
        return null;    }

    @Override
    public Customer getCustomerById(long customerId) {
        //To do
        List<Customer> p=  DataSource.allCustomers.stream().filter(customer -> customer.getId()==customerId).collect(Collectors.toList());
        if (p.size()==0)
        {
            return null;
        }
        if (p.size()==1)
        {
            return p.get(0);
        }
        return null;    }


    @Override
    public List<Product> getProducts(ProductCategory cat, double price) {
        //To do
        return null;
    }

    @Override
    public List<Customer> popularCustomers() {

        List<Customer> p=  DataSource.allCustomers.
                stream().filter(customer -> customer.getTier()==3 && (DataSource.allOrders.stream().
                        filter(order->order.getCustomrId()==customer.getId())).count()>10).
                collect(Collectors.toList());
        return p;
    }

    @Override
    public List<Order> getCustomerOrders(long customerId) {
        //To do
        return null;
    }

    @Override
    public long numberOfProductInOrder(long orderId) {
        //To do
        return 0;
    }

    @Override
    public List<Product> getPopularOrderedProduct(int orderedtimes) {
        //To do
        List<Product> p=  DataSource.allProducts.stream().filter(product -> (DataSource.allOrderProducts.stream().
                filter(order->order.getProductId()==product.getProductId())).count()>orderedtimes).
                collect(Collectors.toList());

        return p;
    }

    @Override
    public List<Product> getOrderProducts(long orderId)
    {
        //To do
        return null;
    }

    @Override
    public List<Customer> getCustomersWhoOrderedProduct(long productId) {
        //To do
        List<Customer> p=  DataSource.allCustomers.stream().filter(customer -> (DataSource.allOrders.stream().
                        filter(order->order.getCustomrId()==customer.getId() && (DataSource.allOrderProducts.stream().
                                filter(product->product.getOrderId()==order.getOrderId() && productId== product.getProductId()).count()>0)))!=null).
                collect(Collectors.toList());
        return p;
    }

    @Override
    public Product getMaxOrderedProduct() {
        //To do
        Product p = DataSource.allProducts.stream()
                .max((pr,pr2)-> (int)(DataSource.allOrderProducts.stream().
        filter(order->order.getProductId()==pr.getProductId()) ).count()-(int)(DataSource.allOrderProducts.stream().
                        filter(order->order.getProductId()==pr2.getProductId()) ).count() ).orElse(null);

        return p;

    }
    @Override
    public double sumOfOrder(long orderID) {
        //To do
        return 0;
    }

    @Override
    public List<Order> getExpensiveOrders(double price) {
        //To do
        return null;
    }

    @Override
    public List<Customer> ThreeTierCustomerWithMaxOrders() {
        //To do
        return null;

    }

}
