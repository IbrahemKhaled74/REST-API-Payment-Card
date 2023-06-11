package com.vodafone.database;

import com.vodafone.models.Payment;

import java.sql.*;
import java.util.*;

public class DBHandler {

    private Connection connection;

    // private PreparedStatement preparedStatement;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment", "root", "P@ssw0rd");
    }

    private void disconnect() throws SQLException {
        connection.close();
    }

    public List<Payment> getAllCards() {
        String query = "SELECT * FROM Cards";
        List<Payment> paymentList = new ArrayList<>();
        try {
            this.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String cardNumber = resultSet.getString("card_number");
                double balance = resultSet.getDouble("balance");
//                Date date= resultSet.getDate("expiration");
//                int cvv=resultSet.getInt("cvv");
                Payment payment = new Payment(balance, cardNumber);
                paymentList.add(payment);
            }
            this.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paymentList;
    }

    public static void main(String[] args) {
        DBHandler dbHandler = new DBHandler();

        for (Payment payment : dbHandler.getAllCards()) {
            System.out.println(payment.getAmount() + "   " + payment.getCardNumber());
        }
    }
}