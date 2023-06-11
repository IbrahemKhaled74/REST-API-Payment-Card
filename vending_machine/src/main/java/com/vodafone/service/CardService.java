package com.vodafone.service;

import com.vodafone.database.DatabaseSetup;
import com.vodafone.models.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardService {

    public CardService() {
    }

    public List<Payment> getAllCards() {

        List<Payment> paymentList = new ArrayList<>();

        String query = "SELECT * FROM Cards";
        try {
            DatabaseSetup.connect();
            PreparedStatement preparedStatement = DatabaseSetup.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String cardNumber = resultSet.getString("card_number");
                double balance = resultSet.getDouble("balance");
                Date date= resultSet.getDate("expiration");
                int cvv=resultSet.getInt("cvv");
                Payment payment = new Payment(balance, cardNumber,date,cvv);
                paymentList.add(payment);
            }
            DatabaseSetup.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paymentList;
    }

    public Payment getCardByCardNumber(String cardNumber) {
        String query = "SELECT * FROM Cards WHERE card_number = ?";
        Payment payment = null;
        try {
            DatabaseSetup.connect();
            PreparedStatement preparedStatement = DatabaseSetup.getConnection().prepareStatement(query);
            preparedStatement.setString(1, cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                int cvv =resultSet.getInt("cvv");
                 payment = new Payment(balance,cvv,cardNumber);

            }
            DatabaseSetup.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }
    public Payment getAllCardInfoByCardNumber(String cardNumber) {
        String query = "SELECT * FROM Cards WHERE card_number = ?";
        Payment payment = null;
        try {
            DatabaseSetup.connect();
            PreparedStatement preparedStatement = DatabaseSetup.getConnection().prepareStatement(query);
            preparedStatement.setString(1, cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                Date date= resultSet.getDate("expiration");
                int cvv=resultSet.getInt("cvv");
                 payment = new Payment(balance, cardNumber,date,cvv);

            }
            DatabaseSetup.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }

    public double getAmountOfCard(String cardNumber) {
        String query = "SELECT balance FROM Cards WHERE card_number = ?";
        double balance = 0;
        try {
            DatabaseSetup.connect();
            PreparedStatement preparedStatement = DatabaseSetup.getConnection().prepareStatement(query);
            preparedStatement.setString(1, cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            }
            DatabaseSetup.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }

    public Payment updateCard(String cardNumber, double amount) {
        String query = "UPDATE Cards SET balance = ? WHERE card_number = ?";
        Payment payment = null;
        try
        {
            DatabaseSetup.connect();
            PreparedStatement preparedStatement = DatabaseSetup.getConnection().prepareStatement(query);
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.executeUpdate();
            payment = getCardByCardNumber(cardNumber);
            DatabaseSetup.disconnect();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return payment;
    }
}
