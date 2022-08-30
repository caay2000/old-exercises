package com.merkleinc.interviewkata.repository;

import static java.util.stream.Collectors.groupingBy;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import com.merkleinc.interviewkata.repository.model.CustomerProduct;

public class DataExtractionUtils {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void checkNumberOfProductPerCustomer() throws IOException {

        CustomerRepository customerRepository = new CustomerRepository();

        Map<String, List<CustomerProduct>> map = customerRepository.getCustomerProducts().stream()
                .collect(groupingBy(e -> e.getCustomerId()));

        for (String key : map.keySet()) {
            System.out.println(MessageFormat.format("customer {0} has {1} products", key, map.get(key).size()));
        }
    }

    @Test
    public void CheckCusotmerProductsWithIncorrectDates() throws Exception {

        CustomerRepository customerRepository = new CustomerRepository();

        List<CustomerProduct> list = new ArrayList<>();
        for (CustomerProduct e : customerRepository.getCustomerProducts()) {
            if (sdf.parse(e.getEffectiveFrom()).after(sdf.parse(e.getEffectiveTo()))) {
                list.add(e);
            }
        }

        for (CustomerProduct customerProduct : list) {
            System.out.println(customerProduct.toString());
        }

        Assert.assertEquals(0, list.size());
    }

    @Test
    public void checkNumberOfProductPerCustomerAndMonth() throws IOException, ParseException {

        CustomerRepository customerRepository = new CustomerRepository();

        Map<String, List<CustomerProduct>> map = customerRepository.getCustomerProducts().stream()
                .collect(groupingBy(e -> e.getCustomerId()));

        LocalDate date = LocalDate.of(2016, Month.JANUARY, 2);
        LocalDate endDate = LocalDate.of(2019, Month.DECEMBER, 31);

        while (date.isBefore(endDate)) {
            System.out.println(date.getMonth() + " " + date.getYear());
            int totalCount = 0;
            for (String key : map.keySet()) {

                List<CustomerProduct> products = map.get(key);
                int count = 0;
                for (CustomerProduct product : products) {
                    if (toLocalDate(product.getEffectiveFrom()).withDayOfMonth(1).isBefore(date) &&
                            toLocalDate(product.getEffectiveTo()).withDayOfMonth(20).isAfter(date)) {
                        count++;
                        totalCount++;
                    }
                }
                if (count > 0) {
                    System.out.println(MessageFormat.format("customer {0} has {1} products", key, count));
                }

            }
            System.out.println(MessageFormat.format("total count {0}", totalCount));
            date = date.plusMonths(1);
        }
    }

    private LocalDate toLocalDate(String string) throws ParseException {
        Date dateParsed = sdf.parse(string);
        return dateParsed.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
