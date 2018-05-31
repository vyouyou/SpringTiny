package customer;

import com.qiyouyou.dal.model.Customer;
import com.qiyouyou.domain.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CustomerServiceTest {
    private final CustomerService customerService;

    public CustomerServiceTest(){
        customerService = new CustomerService();
    }

    @Before
    public void init(){

    }

    @Test
    public void getCustomerListTest(){
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2,customerList.size());
    }

    @Test
    public void insertCustomerTest(){
        Customer customer = new Customer();
        customer.setName("youyou1");
        customer.setE_mail("youyou@");
        customer.setTele_phone("131");
        customer.setContract("con");
        customer.setRemark("i am mark");
        customerService.insertCustomer(customer);
    }
}
