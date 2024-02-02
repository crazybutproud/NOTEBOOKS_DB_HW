package org.example;

import java.sql.*;
import java.util.*;

public class DBdao implements Idao {

    private final Connection connection;
    private final String DB_url = "jdbc:postgresql://127.0.0.1:5432/notebooks";
    private final String NAME = "postgres";
    private final String PASSWORD = "postgres";

    public DBdao() {
        try {
            this.connection = DriverManager.getConnection(DB_url, NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("no such db");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Notebook> getAll() { // метод для получения всех блокнотов
        return queryByString("SELECT * FROM notebooks"); //отправляем запрос на все данные из бд
    }

    @Override
    public LinkedHashSet<String> getAllCountries() throws SQLException { //метод для получения всех стран производителей блокнотов
        Statement statement = connection.createStatement(); // связь с бд
        ResultSet resultSet = statement.executeQuery("SELECT  country from notebooks"); //итератор

        Set<String> set =new LinkedHashSet<>();

        while (resultSet.next()) { //пока есть следующий пациент
            set.add(resultSet.getString("country"));
        }
        return (LinkedHashSet<String>) set;
    }

    @Override
    public Map<String, Integer> getCountriesAndCountOfNotebooks() throws SQLException {
        Statement statement = connection.createStatement(); // связь с бд
        ResultSet resultSet = statement.executeQuery("SELECT  country from notebooks"); //итератор

        Map<String,Integer> map =new HashMap<>();

        while (resultSet.next()) { //пока есть следующий пациент
            map.put(resultSet.getString("country"));
            if (map.containsKey()) {

            }
        }
        return (Map<String, Integer>) map;

    }

    @Override
    public List<Notebook> queryByString(String query) { //метод для преобразования строки в запрос
        try {
            Statement statement = connection.createStatement(); // связь с бд
            ResultSet resultSet = statement.executeQuery(query); //итератор

            List<Notebook> notebooksList = new ArrayList<>();

            while (resultSet.next()) { //пока есть следующий пациент
                notebooksList.add(getFromResultEntry(resultSet));
            }
            return notebooksList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Notebook getFromResultEntry(ResultSet result) throws SQLException { //метод для получения объекта блокнот из результата бд
        return new Notebook(    //заполняем объект по его конструткору
                Integer.parseInt(result.getString("id")),
                result.getString("firm"),
                result.getString("name"),
                Integer.parseInt(result.getString("page")),
                Integer.parseInt(result.getString("cover_type")),
                result.getString("country"),
                Integer.parseInt(result.getString("page_type"))
        );
    }

    @Override
    public void closeConnection() {

    }
}
