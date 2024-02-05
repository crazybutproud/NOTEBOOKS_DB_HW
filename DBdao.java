package org.example;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        Set<String> set = new LinkedHashSet<>();

        while (resultSet.next()) { //пока есть следующий пациент
            set.add(resultSet.getString("country"));
        }
        return (LinkedHashSet<String>) set;
    }

    @Override
    public Map<String, Integer> getCountriesAndCountOfNotebooks() throws SQLException { //метод для получения всех стран и количества блокнотов
        Statement statement = connection.createStatement(); // связь с бд
        ResultSet resultSet = statement.executeQuery("SELECT  country from notebooks");

        Map<String, Integer> stringIntegerMap = new HashMap<>();
        int count = 0;
        while (resultSet.next()) {
            if (stringIntegerMap.containsKey(resultSet.getString("country"))) { //если содержит ключ с названием страны, то перезаписываем с количествои + 1
                stringIntegerMap.put(resultSet.getString("country"), stringIntegerMap.get(resultSet.getString("country")) + 1);
            } else {
                stringIntegerMap.put(resultSet.getString("country"), count);
            }
        }
        return stringIntegerMap;

    }

    @Override
    public void mostAndLeastCountriesNBooks() throws SQLException { //метод получения наибольшего и наименьшего кол-во блокнотов по странам
        Map<String, Integer> stringIntegerMap = getCountriesAndCountOfNotebooks();
        Map<String, Integer> sortedMap = stringIntegerMap.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));

        sortedMap.entrySet().forEach(System.out::println);
        System.out.println("\n");
        String least = String.valueOf(sortedMap.entrySet().iterator().next());
        System.out.println("Меньше всего - " + least);
        String most = String.valueOf(sortedMap.entrySet().toArray()[sortedMap.size() - 1]);
        System.out.println("Больше всего - " + most);
    }

    @Override
    public Map<String, Integer> getFirmsAndCountOfNotebooks() throws SQLException { //метод для получения всех производителей и количества блокнотов
        Statement statement = connection.createStatement(); // связь с бд
        ResultSet resultSet = statement.executeQuery("SELECT  firm from notebooks");

        Map<String, Integer> stringIntegerMap = new HashMap<>();
        int count = 0;
        while (resultSet.next()) {
            if (stringIntegerMap.containsKey(resultSet.getString("firm"))) { //если содержит ключ с названием страны, то перезаписываем с количествои + 1
                stringIntegerMap.put(resultSet.getString("firm"), stringIntegerMap.get(resultSet.getString("firm")) + 1);
            } else {
                stringIntegerMap.put(resultSet.getString("firm"), count);
            }
        }
        return stringIntegerMap;
    }

    @Override
    public void mostAndLeastFirmsNBooks() throws SQLException { //метод получения наибольшего и наименьшего кол-во блокнотов по производителю
        Map<String, Integer> stringIntegerMap = getFirmsAndCountOfNotebooks();
        Map<String, Integer> sortedMap = stringIntegerMap.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));

        sortedMap.entrySet().forEach(System.out::println);
        System.out.println("\n");
        String least = String.valueOf(sortedMap.entrySet().iterator().next());
        System.out.println("Меньше всего - " + least);
        String most = String.valueOf(sortedMap.entrySet().toArray()[sortedMap.size() - 1]);
        System.out.println("Больше всего - " + most);
    }

    @Override
    public void getAllNBooksWithCoverTypeLike() {
        System.out.println("Введите тип нужной обложки: 1-твердая,2-мягкая");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        if (result.contains("1")) {
            List<Notebook> allNotebooks = queryByString("SELECT * FROM notebooks where (cover_type = '1')");
            allNotebooks.forEach(System.out::println);
        } else if (result.contains("2")) {
            List<Notebook> allNotebooks = queryByString("SELECT * FROM notebooks where (cover_type = '2')");
            allNotebooks.forEach(System.out::println);
        } else {
            System.out.println("Цифра введена неверно");
        }
    }

    @Override
    public void getAllNBooksWithCountryLike() {
        System.out.println("Введите нужную страну: 1-Россия,2-Беларусь,3-Украина");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        if (result.contains("1")) {
            List<Notebook> allNotebooks = queryByString("SELECT * FROM notebooks where (country = 'Россия')");
            allNotebooks.forEach(System.out::println);
        } else if (result.contains("2")) {
            List<Notebook> allNotebooks = queryByString("SELECT * FROM notebooks where (country = 'Беларусь')");
            allNotebooks.forEach(System.out::println);
        } else if (result.contains("3")) {
            List<Notebook> allNotebooks = queryByString("SELECT * FROM notebooks where (country = 'Украина')");
            allNotebooks.forEach(System.out::println);
        } else {
            System.out.println("Цифра введена неверно");
        }
    }

    @Override
    public void getAllNBooksWithPAgeTypeLike() {
        System.out.println("Введите тип нужных страниц: 1-клетка,2-линия,3-пустая");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        if (result.contains("1")) {
            List<Notebook> allNotebooks = queryByString("SELECT * FROM notebooks where (page_type = '1')");
            allNotebooks.forEach(System.out::println);
        } else if (result.contains("2")) {
            List<Notebook> allNotebooks = queryByString("SELECT * FROM notebooks where (page_type = '2')");
            allNotebooks.forEach(System.out::println);
        } else if (result.contains("3")) {
            List<Notebook> allNotebooks = queryByString("SELECT * FROM notebooks where (page_type = '3')");
            allNotebooks.forEach(System.out::println);
        } else {
            System.out.println("Цифра введена неверно");
        }
    }

    @Override
    public void getAllNBooksWithPagesCountLike() { //получение блокнотов с определенным кол-вом страниц
        System.out.println("Выберите нужный диапазон кол-ва страниц(от и до)");
        Scanner scanner = new Scanner(System.in);
        int result1 = scanner.nextInt();
        int result2 = scanner.nextInt();
        String query = String.format("select * from notebooks where page between %d and %d;", result1, result2);
        List<Notebook> notebooksList = queryByString(query);
        notebooksList.forEach(System.out::println);
    }

    @Override
    public void addLineToDB() throws SQLException { //метод для добавления нового товара в бд
        System.out.println("Добавление нового товара в бд\n" +
                "Введите данные в порядке:id/фирма/название/кол-во страниц/тип обложки/страна производитель/тип страницы");
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine()); //вынужденный костыль.без него выдавало ошибку
        String firm = scanner1.nextLine();
        String name = scanner.nextLine();
        int page = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите тип нужной обложки: 1-твердая,2-мягкая");
        int cover_type = Integer.parseInt(scanner.nextLine());
        String country = scanner1.nextLine();
        System.out.println("Введите тип нужных страниц: 1-клетка,2-линия,3-пустая");
        int page_type = Integer.parseInt(scanner.nextLine());
        System.out.println("Добавление нового товара...");
        String query = String.format("INSERT INTO notebooks values (%d,'%s','%s',%d,%d,'%s',%d)", id, firm, name, page, cover_type, country, page_type);
        //queryByString(query);
        Statement statement = connection.createStatement();
        int executeUpdate = statement.executeUpdate(query);
        System.out.println(executeUpdate);
        System.out.println("Товар добавлен\n");

    }

    @Override
    public void removeLineFromDB() throws SQLException { //метод для удаления товара по айди
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди удаляемого товара от 1 до 52");
        int id = scanner.nextInt();
        int rows = statement.executeUpdate("DELETE FROM notebooks WHERE Id = " + id);
        System.out.printf("%d товар удален", rows);
    }

    @Override
    public void updateLineInDB() throws SQLException { //метод для изменения данных
        Statement statement = connection.createStatement();
        System.out.println("Что вы хотите изменить?\n 1-фирму\n 2-название\n 3-страну производитель");
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        String result = scanner.nextLine();
        System.out.println("Где вы хотите это изменить?(введите айди от 1 до 52)");
        int id = scanner.nextInt();
        String db_result = "";
        if (result.contains("1")) {
            db_result = "firm";
        } else if (result.contains("2")) {
            db_result = "name";
        } else if (result.contains("3")) {
            db_result = "country";
        } else {
            System.out.println("Введено число не из предложенного списка");
        }
        System.out.println("Введите изменение: \n");
        String update = scanner1.nextLine();
        String query = String.format("UPDATE notebooks SET %s = '%s' WHERE id = %d ", db_result, update, id);
        int rows = statement.executeUpdate(query);
        System.out.printf("Изменено %d строк", rows);
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
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
