package com.mehmetozkan.readingisgood.service;

import com.mehmetozkan.readingisgood.entity.Book;
import com.mehmetozkan.readingisgood.entity.Order;
import com.mehmetozkan.readingisgood.exception.EntityNotExistException;
import com.mehmetozkan.readingisgood.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest(classes = {StatisticsService.class})
@ActiveProfiles("dev")
@Import({EntityNotExistException.class})
public class StatisticsServiceTest {

    @Autowired
    private StatisticsService statisticsService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void it_should_get_customer_statistics() {

        Book book = new Book();
        book.setId(1L);
        book.setName("The Silmarillion");
        book.setDescription("The Silmarillion is a collection of mythopoeic stories.");
        book.setWriter("J. R. R. Tolkien");
        book.setPrice(new BigDecimal(15));
        book.setRemainingStock(10L);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        Order order1 = new Order();
        order1.setId(1L);
        order1.setDateCreated(new Date(1995, Calendar.MARCH, 7));
        order1.setTotalPrice(new BigDecimal(15));
        order1.setCustomerId(1L);
        order1.setBookList(bookList);
        order1.setTotalBookCount(1L);

        Order order2 = new Order();
        order2.setId(1L);
        order2.setDateCreated(new Date(1995, Calendar.DECEMBER, 7));
        order2.setTotalPrice(new BigDecimal(15));
        order2.setCustomerId(1L);
        order2.setBookList(bookList);
        order2.setTotalBookCount(1L);

        Order order3 = new Order();
        order3.setId(1L);
        order3.setDateCreated(new Date(1995, Calendar.SEPTEMBER, 7));
        order3.setTotalPrice(new BigDecimal(15));
        order3.setCustomerId(1L);
        order3.setBookList(bookList);
        order3.setTotalBookCount(1L);

        Order order4 = new Order();
        order4.setId(1L);
        order4.setDateCreated(new Date(1995, Calendar.JULY, 7));
        order4.setTotalPrice(new BigDecimal(15));
        order4.setCustomerId(1L);
        order4.setBookList(bookList);
        order4.setTotalBookCount(1L);

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);

        when(orderRepository.findByCustomerId(1L)).thenReturn(orderList);

        statisticsService.getCustomerStatistics(1L);

    }
}
