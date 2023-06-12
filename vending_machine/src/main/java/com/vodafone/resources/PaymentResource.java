package com.vodafone.resources;

import com.vodafone.DateConverter;
import com.vodafone.exception.CardNumberNotFoundException;
import com.vodafone.exception.InsufficientBalanceException;
import com.vodafone.exception.InvalidCardNumberException;
import com.vodafone.models.PaymentMessage;
import com.vodafone.models.PaymentService;
import com.vodafone.service.CardService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Path("/payments")
public class PaymentResource {
    CardService cardService = new CardService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCard() {
        return Response.ok(cardService.getAllCards()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPayment(PaymentService payment) throws ParseException {

        int cardNumberSize = 16;
        Response response = null;

        if (payment.cardNumber.length() != cardNumberSize) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            throw new InvalidCardNumberException("The Card Number " + payment.cardNumber + "is Invalid");
        }

        if (cardService.getCardByCardNumber(payment.cardNumber) == null) {
            response = Response.status(Response.Status.NOT_FOUND).build();
            throw new CardNumberNotFoundException("The Card Number " + payment.cardNumber + " is not found");
        }
        if (cardService.getAllCardInfoByCardNumber(payment.cardNumber).getExpireDate().before(Date.valueOf(LocalDate.now()))) {
            response = Response.status(Response.Status.FORBIDDEN).build();
            throw new InvalidCardNumberException("The Card Number " + payment.cardNumber + " is Invalid");
        }

        double cardAmount = cardService.getAmountOfCard(payment.cardNumber);
        if (cardAmount == 0 || cardAmount < payment.amount) {
            response = Response.status(Response.Status.FORBIDDEN).build();
            throw new InsufficientBalanceException("The Card " + payment.cardNumber + " doesn't have enough balance");

        }

        if (payment.cvv != cardService.getAllCardInfoByCardNumber(payment.cardNumber).getCvv()) {
            System.out.println(payment.getCvv());
            response = Response.status(Response.Status.FORBIDDEN).build();
            throw new InvalidCardNumberException("The Card Number " + payment.cardNumber + " is Invalid");
        }
        //Convert Date To Year And Month
        java.util.Date pDate = cardService.getAllCardInfoByCardNumber(payment.cardNumber).getExpireDate();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String strDate = dateFormat.format(pDate);

        LocalDate localDate = DateConverter.convertDate(strDate);

        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        String month = localDate.format(monthFormatter);

        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        String year = localDate.format(yearFormatter);

        if (!month.equals(payment.month) && !year.equals(payment.year)) {
            response = Response.status(Response.Status.FORBIDDEN).build();
            throw new InvalidCardNumberException("The Card " + payment.cardNumber + " is Invalid");

        } else if (cardService.getAmountOfCard(payment.cardNumber) < payment.amount) {
            response = Response.status(Response.Status.FORBIDDEN).build();
            throw new InsufficientBalanceException("The Card " + payment.cardNumber + " doesn't have enough balance");
        }

        cardService.updateCard(payment.cardNumber, cardAmount - payment.amount);

        PaymentMessage paymentMessage = new PaymentMessage("Pay Successfully", 200);

        response = Response.ok(paymentMessage).build();
        return response;
    }

}