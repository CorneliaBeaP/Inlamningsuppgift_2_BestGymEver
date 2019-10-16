import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BestGymEverTest {

    @Test
    void readCustomersFromFile() {
        List<Customer> customers = BestGymEver.readCustomersFromFile();
        assertNotNull(customers, "Customers should not be null");
        assertTrue(customers.size() > 0, "Customers size should be more than zero.");
        for (Customer customer : customers
        ) {
            assertNotNull(customer.getName());
            assertNotNull(customer.getSocialSecurityNumber());
            assertNotNull(customer.getDateFeePaid());
        }
    }

    @Test
    void findCustomerByName() {
        List<Customer> customers = new ArrayList<>();
        String customer1Name = "Cornelia Persson";
        customers.add(new Customer(customer1Name, "1212121212", LocalDate.of(2012, 8, 2)));
        customers.add(new Customer("James Gosling", "8888880000", LocalDate.of(2019, 3, 8)));
        Customer customer1 = BestGymEver.findCustomer(customer1Name, customers);

        assertEquals(customer1Name, customer1.getName());

    }

    @Test
    void findCustomerBySocialSecurityNumber() {
        List<Customer> customers = new ArrayList<>();
        String expected = "8888880000";
        customers.add(new Customer("Cornelia Persson", "1212121212", LocalDate.of(2012, 8, 2)));
        customers.add(new Customer("James Gosling", expected, LocalDate.of(2019, 3, 8)));
        Customer customer1 = BestGymEver.findCustomer(expected, customers);

        assertEquals(expected, customer1.getSocialSecurityNumber());

    }

    @Test
    void writeCustomerDataToFile() throws IOException {
        Path p1 = Paths.get("C:\\Users\\corne\\IdeaProjects\\Lektioner JAVA19_OBJP\\Inlamningsuppgift_2_BestGymEver\\src\\BesoksloggTEST");
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(p1), true));
        Customer c1 = new Customer("Angelina Svensson", "196001010000", LocalDate.of(2019, 7, 20));
        Customer c2 = new Customer("Freja Karlsson", "196001010000", LocalDate.of(2019, 2, 17));
        Customer c3 = new Customer("Axel Persson", "196001010000", LocalDate.of(2019, 10, 1));

        BestGymEver.writeCustomerDataToFile(c1, String.valueOf(p1));
        BestGymEver.writeCustomerDataToFile(c2, String.valueOf(p1));
        BestGymEver.writeCustomerDataToFile(c3, String.valueOf(p1));

        assertNotNull(writer);
    }
}