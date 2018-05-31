package com.qiyouyou.domain.service;

import com.google.common.base.Joiner;
import com.qiyouyou.dal.model.Customer;
import com.qiyouyou.domain.util.CollenctionUtil;
import com.qiyouyou.helper.DatabaseHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class CustomerService {

    public List<Customer> getCustomerList() {
        return DatabaseHelper.queryList(Customer.class,"SELECT * FROM customer");
    }

    public Customer getById(Integer id) {
        return DatabaseHelper.queryEntity(Customer.class,"SELECT * FROM customer WHERE id=?",id);
    }

    public boolean insertCustomer(Customer customer){
        Map<String,Object> customerMap = CollenctionUtil.ObjectToMap(customer);
        return DatabaseHelper.insertEntity(Customer.class,customerMap);
    }

}
