package org.example;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public interface Idao {
    List<Notebook> getAll();

    HashSet<String> getAllCountries() throws SQLException;

    Map<String, Integer> getCountriesAndCountOfNotebooks() throws SQLException;

    Map<String,Integer> getFirmsAndCountOfNotebooks () throws SQLException;

    void mostAndLeastCountriesNBooks() throws SQLException;
    void mostAndLeastFirmsNBooks() throws SQLException;
    void getAllNBooksWithCoverTypeLike();
    void getAllNBooksWithCountryLike ();
    void getAllNBooksWithPAgeTypeLike();
    void getAllNBooksWithPagesCountLike();
    void addLineToDB() throws SQLException;
    void removeLineFromDB() throws SQLException;
    void updateLineInDB() throws SQLException;

    List<Notebook> queryByString(String query);

    void closeConnection() throws SQLException;
}
