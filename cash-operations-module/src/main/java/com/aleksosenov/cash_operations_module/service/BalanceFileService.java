package com.aleksosenov.cash_operations_module.service;

import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BalanceFileService {
    public static final String FILE_NAME = "balance.txt";
    public static final String LOGIC_SEPARATOR = "\\|";
    public static final String DENOMINATION_SEPARATOR = ",";
    public static final String AMOUNT_SEPARATOR = "x";

    public Map<Currency, Map<Denomination, Integer>> getBalanceFromFile() {
        try {
            List<String> content = Files.readAllLines(ResourceUtils.getFile("classpath:balance.txt").toPath());
            System.out.println(LOGIC_SEPARATOR);
            content.forEach(System.out::println);
            return content.stream()
                    .map(s -> s.split(LOGIC_SEPARATOR))
                    .collect(Collectors.toMap(strings -> Currency.valueOf(strings[0]), this::createDenominationsMap));

        } catch (Exception e) {
            e.printStackTrace();  // Handle the exception or rethrow it
        }
        return null;
    }

    private Map<Denomination, Integer> createDenominationsMap(String[] strings) {
        List<String> denomStrings = Arrays.stream(strings[2].split(DENOMINATION_SEPARATOR)).collect(Collectors.toList());
        return denomStrings.stream().map(s -> s.split(AMOUNT_SEPARATOR))
                .collect(Collectors.toMap(array -> Denomination.findByValue(Integer.parseInt(array[0])), array -> Integer.parseInt(array[1])));
    }


}
