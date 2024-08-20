package com.aleksosenov.cash_operations_module.service;

import com.aleksosenov.cash_operations_module.events.CashBalanceEvent;
import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CashBalanceFileService {
    public static final String FILE_NAME = "classpath:balance.txt";
    public static final String LOGIC_SEPARATOR = "\\|";
    public static final String DENOMINATION_SEPARATOR = ",";
    public static final String AMOUNT_SEPARATOR = "x";

    @EventListener
    public void handleCashBalanceEvent(CashBalanceEvent event) {
        updateCashBalanceFile(event);
    }

    private void updateCashBalanceFile(CashBalanceEvent event) {
        Map<Currency, Map<Denomination, Integer>> currentBalance = event.getCurrentBalance();
        List<String> currencyStrings = currentBalance.entrySet().stream()
                .map(this::createStringForCurrency)
                .toList();
        writeToFile(currencyStrings);
    }

    private void writeToFile(List<String> currencyStrings) {
        try (FileWriter fileWriter = new FileWriter(String.valueOf(ResourceUtils.getFile(FILE_NAME).toPath()));
             PrintWriter printWriter = new PrintWriter(fileWriter)
        ) {
            currencyStrings.forEach(printWriter::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String createStringForCurrency(Map.Entry<Currency, Map<Denomination, Integer>> currencyMapEntry) {
        Integer totalAmount = currencyMapEntry.getValue().entrySet().stream()
                .reduce(0, (subtotal, denominationEntry) -> Integer.sum(subtotal, denominationEntry.getKey().getValue() * denominationEntry.getValue()), Integer::sum);
        StringBuilder sb = new StringBuilder(currencyMapEntry.getKey().toString());
        sb.append("|");
        sb.append(totalAmount);
        sb.append("|");
        currencyMapEntry.getValue().forEach((denomination, amount) -> {
            sb.append(denomination);
            sb.append(AMOUNT_SEPARATOR);
            sb.append(amount);
            sb.append(DENOMINATION_SEPARATOR);
        });
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public Map<Currency, Map<Denomination, Integer>> getBalanceFromFile() {
        try {
            List<String> content = Files.readAllLines(ResourceUtils.getFile(FILE_NAME).toPath());
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
                .collect(Collectors.toMap(array ->
                        Denomination.valueOf(array[0]),
                        array -> Integer.parseInt(array[1])));
    }


}
