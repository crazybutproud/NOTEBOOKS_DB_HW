package org.example;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public interface Idao {
    List<Notebook> getAll();

    HashSet<String> getAllCountries() throws SQLException;

    Map<String, Integer> getCountriesAndCountOfNotebooks() throws SQLException;

    List<Notebook> queryByString(String query);

    void closeConnection();
}
