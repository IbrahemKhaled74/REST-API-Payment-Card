package com.vodafone.database;

import com.vodafone.DateConverter;
import com.vodafone.models.Payment;
import com.vodafone.service.CardService;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatabaseSetup {
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private DatabaseSetup() throws SQLException {
        connect();
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(ConfigVariables.DB_URL.getValue(), ConfigVariables.DB_USER.getValue(),
                ConfigVariables.DB_PASSWORD.getValue());
    }

    public static void disconnect() throws SQLException {
        connection.close();
    }

    public static void main(String[] args) throws ParseException {
        //Just Test
        CardService cardService = new CardService();
        Payment paymentList = cardService.getAllCardInfoByCardNumber("5678901234567890");

        java.util.Date pDate = cardService.getAllCardInfoByCardNumber("5678901234567890").getExpireDate();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String strDate = dateFormat.format(pDate);
//        Instant instant = pDate.toInstant();
//        LocalDate strDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate localDate= DateConverter.convertDate(strDate);

        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        String month = localDate.format(monthFormatter);

        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        String year = localDate.format(yearFormatter);


        // System.out.println(ConfigVariables.DB_URL.getValue());
        // String query = "SELECT * FROM Cards";
        // List<Card> cardList = new ArrayList<>();
        // try {
        // DatabaseSetup.connect();
        // PreparedStatement preparedStatement =
        // DatabaseSetup.getConnection().prepareStatement(query);
        // ResultSet resultSet = preparedStatement.executeQuery();
        // while (resultSet.next()) {
        // String cardNumber = resultSet.getString("card_number");
        // double balance = resultSet.getDouble("balance");
        // Card card = new Card(balance, cardNumber);
        // cardList.add(card);
        // }
        // DatabaseSetup.disconnect();
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        // catch (Exception e )
        // {
        // e.printStackTrace();
        // }
        //

        System.out.println(paymentList);
        System.out.println(month);
        System.out.println(year);
//        for (Payment payment : paymentList) {
//            System.out.println("Card Number: " + payment.getCardNumber() + " Balance: " + payment.getBalance());
//
//        }
    }

}
