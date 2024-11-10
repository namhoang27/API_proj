package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Data
@Builder
public class CreateBooking {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstname;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String additionalneeds;
    @Data
    @Builder
    public static class BookingDates{
        private String checkin;
        private String checkout;
    }
}
