package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private DataHelper(){}

    @Value
    public static class DeliveryInfo{
        String city;
        String date;
        String name;
        String phone;
        private String expectedResult;


        public static DeliveryInfo getDeliveryInfo(int daysToAdd){
            final String dateFormatWithDots = "dd.MM.yyyy";
            final String dateFormatWithoutDots = "ddMMyyyy";
            Faker faker = new Faker(new Locale("ru"));
            LocalDate endDate = LocalDate.now().plusDays(daysToAdd);
            DateTimeFormatter formatWithDots = DateTimeFormatter.ofPattern(dateFormatWithDots);
            DateTimeFormatter formatWithoutDots = DateTimeFormatter.ofPattern(dateFormatWithoutDots);
            String city = getCityName();
            String expectedResult = "Встреча успешно запланирована на " + endDate.format(formatWithDots);
            return new DeliveryInfo(city, endDate.format(formatWithoutDots),
                    faker.name().firstName() + " " + faker.name().lastName(), faker.phoneNumber().cellPhone(),expectedResult);
        }

        private static String getCityName(){
            String [] cities = {"Благовещенск","Архангельск","Астрахань","Белгород","Брянск","Владимир","Волгоград","Вологда","Воронеж","Иваново","Иркутск",
                                "Калининград","Калуга","Кемерово","Киров","Кострома","Курган","Курск","Санкт-Петербург","Липецк","Магадан","Москва"};
            Random generator = new Random();
            int randomIndex = generator.nextInt(cities.length);
            return cities[randomIndex];
        }
    }
}
