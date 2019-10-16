import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BestGymEver {
    public static void main(String[] args) {

        List<Customer> gymCustomers = readCustomersFromFile();
        try {
            while (true) {
                String input = JOptionPane.showInputDialog(null, "Vänligen ange namn på person eller personnummer i format ÅÅMMDDXXXX", "Sök person", JOptionPane.PLAIN_MESSAGE);
                if (input == null) {
                    System.exit(0);
                }
                input = input.trim();
                Customer c = findCustomer(input, gymCustomers);
                showUserDialogs(input, c);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ett okänt fel har uppstått.");
        }
    }

    protected static List<Customer> readCustomersFromFile() {
        List<Customer> gymCustomers = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(
                "C:\\Users\\corne\\IdeaProjects\\Lektioner JAVA19_OBJP\\Inlamningsuppgift_2_BestGymEver\\src\\customers.txt"));) {
            Scanner sc = new Scanner(bufferedReader);
            while (sc.hasNext()) {
                sc.useDelimiter("[,\\n]");
                String socialSecurityNumber = sc.next();
                String name = sc.next().trim();
                String date = sc.next();
                LocalDate dateFeePaid = LocalDate.parse(date);
                Customer customer = new Customer(name, socialSecurityNumber, dateFeePaid);
                gymCustomers.add(customer);

            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Tekniskt fel vid inläsning av fil:\n" + e.getMessage());
        }
        return gymCustomers;
    }

    private static void showUserDialogs(String input, Customer customer) {
        if (customer != null) {
            LocalDate dateOneYearAgo = LocalDate.now().minusYears(1);
            if (customer.getDateFeePaid().compareTo(dateOneYearAgo) > 0) {

                String[] options = new String[]{"Registrera besök", "Avbryt"};
                int registerCustomerInput = JOptionPane.showOptionDialog(null, String.format(
                        "Personen är välkommen in!\n\nNamn:  %s\nPersonnummer:  %s\nStatus:  %s\nAvgift betald:  %s\n",
                        customer.getName(), customer.getSocialSecurityNumber(), "AKTIV", customer.getDateFeePaid()), "Kund hittad",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                if (registerCustomerInput == 0) {
                    writeCustomerDataToFile(customer, "C:\\Users\\corne\\IdeaProjects\\Lektioner JAVA19_OBJP\\Inlamningsuppgift_2_BestGymEver\\src\\Besokslogg.txt");
                    JOptionPane.showMessageDialog(null, "Besöket är registrerat!");
                }


            } else if (customer.getDateFeePaid().compareTo(dateOneYearAgo) <= 0) {
                JOptionPane.showMessageDialog(null, String.format("Personen behöver betala en ny årsavgift för att få komma in.\n\n" +
                        "Namn:  %s\nPersonnummer:  %s\nStatus:  %s\nAvgift betald:  %s", customer.getName(), customer.getSocialSecurityNumber(), "INAKTIV", customer.getDateFeePaid()), "Kund hittad", JOptionPane.PLAIN_MESSAGE);
            }


        } else if (input.isEmpty() || input.isBlank()) {
            JOptionPane.showMessageDialog(null, "Inget namn eller personnummer angivet, vänligen testa igen.");
        } else {
            JOptionPane.showMessageDialog(null, "OBEHÖRIG!\n\nPersonen finns inte i systemet.", null, JOptionPane.WARNING_MESSAGE);
        }
    }

    protected static void writeCustomerDataToFile(Customer c, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            DateTimeFormatter visitDateFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
            String informationToLog = String.format("%-20s %-20s Datum besökt: %-20s",
                    c.getName(), c.getSocialSecurityNumber(), LocalDateTime.now().format(visitDateFormat));
            writer.write(informationToLog);
            writer.newLine();
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static Customer findCustomer(String input, List<Customer> list) {
        Customer c = null;
        for (Customer customer : list)
            if (customer.getSocialSecurityNumber().equals(input) || customer.getName().equalsIgnoreCase(input)) {
                c = customer;
            }
        return c;
    }
}
