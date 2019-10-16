import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Customer {
    private String name;
    private String socialSecurityNumber;
    private LocalDate dateFeePaid;

    public Customer(String name, String socialSecurityNumber, LocalDate dateFeePaid) {
        setName(name);
        setSocialSecurityNumber(socialSecurityNumber);
        setDateFeePaid(dateFeePaid);
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public LocalDate getDateFeePaid() {
        return dateFeePaid;
    }

    public void setDateFeePaid(LocalDate dateFeePaid) {
        this.dateFeePaid = dateFeePaid;
    }


}
