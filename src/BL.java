/*
authors:
Shira Tauber 213936271
Shvut Lazare 213195977
ex5 itzuv
*/
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

public class BL implements IBL {
    @Override
    public Product getProductById(long productId) {
        List<Product> p=  DataSource.allProducts.stream().filter(product -> product.getProductId()==
                productId).collect(Collectors.toList());
        if (p.size()==0)
        {
            return null;
        }
        if (p.size()==1)
        {
            return p.get(0);
        }
        return null; // for case there is more than one.
    }

    @Override
    public Order getOrderById(long orderId) {
        List<Order> p=  DataSource.allOrders.stream().filter(order -> order.getOrderId()==
                orderId).collect(Collectors.toList());
        if (p.size()==0)
        {
            return null;
        }
        if (p.size()==1)
        {
            return p.get(0);
        }
        return null; // for case there is more than one.
    }

    @Override
    public Customer getCustomerById(long customerId) {
        List<Customer> p=  DataSource.allCustomers.stream().filter(customer -> customer.getId()==
                customerId).collect(Collectors.toList());
        if (p.size()==0)
        {
            return null;
        }
        if (p.size()==1)
        {
            return p.get(0);
        }
        return null; // for case there is more than one.
    }


    @Override
    public List<Product> getProducts(ProductCategory cat, double price) {
        return DataSource.allProducts.stream().filter(pro->pro.getCategory()==cat)
                .filter(pro->pro.getPrice()<=price).sorted(Comparator.comparingLong(Product::getProductId)).collect(toList());
    }

    @Override
    public List<Customer> popularCustomers() {
        return DataSource.allCustomers.
                stream().filter(customer -> customer.getTier()==3 && (DataSource.allOrders.stream().
                        filter(order->order.getCustomrId()==customer.getId())).count()>10).sorted(Comparator.comparingLong(Customer::getId) ).collect(Collectors.toList());

    }

    @Override
    public List<Order> getCustomerOrders(long customerId) {
        return DataSource.allOrders.stream().filter(ord->ord.getCustomrId()==customerId).
                sorted(Comparator.comparingLong(Order::getOrderId))
                .collect(toList());
    }

    @Override
    public long numberOfProductInOrder(long orderId) {
        return DataSource.allOrderProducts.stream().filter(orpr->orpr.getOrderId()==orderId)
                .map(orpr->orpr.getProductId()).distinct().count();
    }

    @Override
    public List<Product> getPopularOrderedProduct(int orderedtimes) {
        return DataSource.allProducts.stream().filter(pro->
                        (DataSource.allOrderProducts.stream().filter(order->
                                (   order.getProductId()==pro.getProductId() )&& (getOrderById(order.getOrderId())!=null)).count())>=orderedtimes).
             sorted(Comparator.comparingLong(Product::getProductId)).collect(toList());


    }

    @Override
    public List<Product> getOrderProducts(long orderId)
    {
        return DataSource.allProducts.stream().filter(pro->DataSource.allOrderProducts.stream()
                .filter(orpr->orpr.getOrderId()==orderId)
                        .filter(orpr->orpr.getProductId()==pro.getProductId()).count()>0)
                . sorted(Comparator.comparingLong(Product::getProductId)).collect(toList());
    }

    @Override
    public List<Customer> getCustomersWhoOrderedProduct(long productId) {
        return DataSource.allCustomers.stream().filter(customer ->
                        (DataSource.allOrders.stream().filter(order->order.getCustomrId()==customer
                                .getId() && (DataSource.allOrderProducts.stream()
                                .filter(product->product.getOrderId()==order.getOrderId() &&
                                        productId== product.getProductId()).count()>0))).count()>0).
                sorted(Comparator.comparingLong(Customer::getId)).
                collect(Collectors.toList());
    }

    @Override
    public Product getMaxOrderedProduct() {
        return DataSource.allProducts.stream()
                .max((pr,pr2)-> (int)(DataSource.allOrderProducts.stream().
                        filter(order->order.getProductId()==pr.getProductId()) ).count()
                        -(int)(DataSource.allOrderProducts.stream().filter(order->order.
                        getProductId()==pr2.getProductId()) ).count() ).orElse(null);

    }
    @Override
    public double sumOfOrder(long orderID) {
        return DataSource.allProducts.stream().map(pro->DataSource.allOrderProducts.stream()
                        .filter(orpr->orpr.getOrderId()==orderID)
                        .filter(orpr->orpr.getProductId()==pro.getProductId())
                        .map(orpr->orpr.getQuantity()*pro.getPrice()).reduce(0.0,(a,b)->a+b))
                .reduce(0.0,(a,b)->a+b);
    }

    @Override
    public List<Order> getExpensiveOrders(double price) {

        return DataSource.allOrders.stream().filter(ord->sumOfOrder(ord.getOrderId())>price) .
                sorted(Comparator.comparingLong(Order::getOrderId)).collect(toList());
    }

    @Override
    public List<Customer> ThreeTierCustomerWithMaxOrders() {

        Customer c= DataSource.allCustomers.
                stream().filter(customer -> customer.getTier()==3).max((pr,pr2)->(int)
                        (DataSource.allOrders.stream().filter(order->order.getCustomrId()==pr.getId())
                                .count())- (int)(DataSource.allOrders.stream().filter(order->order.
                                getCustomrId()==pr2.getId()).count())).orElse(null);

        long sumOrders= DataSource.allOrders.stream().filter(order->order.getCustomrId()==c.getId())
                        .count();
        return DataSource.allCustomers.stream().filter(customer-> customer.getTier()==3&& getCustomerOrders(customer.getId())
                .stream().count()==sumOrders).sorted(Comparator.comparingLong(Customer::getId))
                .collect(toList());



    }
}



