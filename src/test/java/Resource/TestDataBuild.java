package Resource;

import lombok.var;
import pojo.CreateBooking;
import pojo.CreateToken;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TestDataBuild {
    public CreateToken createToken(String user, String pass) {
        CreateToken ct = new CreateToken();
        ct.setUsername(user);
        ct.setPassword(pass);
        return ct;
    }

    public CreateBooking createBooking(String firstname, String lastname, int price, String additional) {
        LocalDate today = LocalDate.now();
        Random random = new Random();
        LocalDate checkInDate = today.plusDays(1);
        LocalDate checkOutDate = today.plusDays(random.nextInt(3) + 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        CreateBooking.BookingDates bD = CreateBooking.BookingDates.builder().checkin(checkInDate.format(formatter)).checkout(checkOutDate.format(formatter)).build();
        CreateBooking.CreateBookingBuilder cb = CreateBooking.builder();
        if (!firstname.equalsIgnoreCase("null")) {
            cb = cb.firstname(firstname);
        }
        if (!lastname.equalsIgnoreCase("null")) {
            cb = cb.lastname(lastname);
        }
        if (!additional.equalsIgnoreCase("null")) {
            cb = cb.additionalneeds(additional);
        }
        cb = cb.depositpaid(true);
        cb = cb.bookingdates(bD);
        cb = cb.totalprice(price);
        return cb.build();

    }
}
