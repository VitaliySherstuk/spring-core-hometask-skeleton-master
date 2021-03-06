package ua.epam.spring.hometask.util;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class DiscountStrategy implements DiscountService{

    private byte amountTicketDiscount;
    private byte numberTicketDiscount;

    public DiscountStrategy(byte amountTicketDiscount, byte numberTicketDiscount)
    {
        this.amountTicketDiscount = amountTicketDiscount;
        this.numberTicketDiscount = numberTicketDiscount;
    }

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {

        int countTickets = user == null ? 0 : user.getTickets().size();
        long allTicket = numberOfTickets + countTickets;
        long numberWithDiscount = (allTicket / numberTicketDiscount) - (countTickets / numberTicketDiscount);
        return (byte) (numberWithDiscount * amountTicketDiscount / numberOfTickets);
    }
}
