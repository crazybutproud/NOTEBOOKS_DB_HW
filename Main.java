package org.example;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        Idao dao = new DBdao();
        //Отображение всего содержимого бд с блокнотами
//        List<Notebook> allNotebooks = dao.getAll();
//        allNotebooks.forEach(System.out::println);

        //Отображение всех стран производителей
//        HashSet<String> allCountries = dao.getAllCountries();
//        allCountries.forEach(System.out::println);

        //Отображение всех стран и количества блокнотов
//        Map<String,Integer> stringIntegerMap = dao.getCountriesAndCountOfNotebooks();
//        for (String country: stringIntegerMap.keySet()) {
//            String key = country.toString();
//            String value = stringIntegerMap.get(country).toString();
//            System.out.println(key + " " + value);
//        }

        //Отображения всех производителей и кол-во блокнотов
//        Map<String,Integer> stringIntegerMap2 = dao.getFirmsAndCountOfNotebooks();
//        for (String country: stringIntegerMap2.keySet()) {
//            String key = country.toString();
//            String value = stringIntegerMap2.get(country).toString();
//            System.out.println(key + " " + value);
//        }

        //Отображение блокнотов с нужным количеством страниц(от и до)
        //dao.getAllNBooksWithPagesCountLike();

        //Отображение стран с наибольшим и наименьшим кол-вом блокнотов(одним методом)
        //dao.mostAndLeastCountriesNBooks();

        //Отображение производителей с наибольшим и наименьшим кол-вом блокнотов(одним методом)
        //dao.mostAndLeastFirmsNBooks();
        //Отображение всех блокнотов с нужным типом обложки
        //dao.getAllNBooksWithCoverTypeLike();

        //Отображения всех блокнотов из нужной страны
        //dao.getAllNBooksWithCountryLike();

        //Отображение всех блокнотов с нужным типом страниц(не поняла что значит сделать "фильтр",поэтому сделала 2 варианта)
        //1 вариант - метод выводящий все данные о блокнотах только с нужным типом страниц
        //dao.getAllNBooksWithPAgeTypeLike();
        //2 вариант - метод выводящий все данные обо ВСЕХ блокнотах,но данные сгруппированы по типу страниц
//        List<Notebook> allNotebooks = dao.queryByString("SELECT * FROM notebooks order by page_type");
//        allNotebooks.forEach(System.out::println);

        //Добавление товара в бд
        //dao.addLineToDB();

        //Удаление товара из бд
        //dao.removeLineFromDB();

        //Обновление строки в бд
        //dao.updateLineInDB();

        //Закрытие соединения с бд
        //dao.closeConnection();
    }
}