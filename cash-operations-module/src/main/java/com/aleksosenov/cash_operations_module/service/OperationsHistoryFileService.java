package com.aleksosenov.cash_operations_module.service;

import com.aleksosenov.cash_operations_module.events.CashOperationEvent;
import com.aleksosenov.cash_operations_module.model.CashOperation;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

@Service
public class OperationsHistoryFileService {

    public static final String FILE_NAME = "classpath:history.txt";

    @EventListener
    public void handleCashOperationEvent(CashOperationEvent event) {
        addOperationToHistory(event);
    }

    private void addOperationToHistory(CashOperationEvent event) {
        String operationString = createStringFromOperation(event.getCashOperation());

        try (FileWriter fileWriter = new FileWriter(String.valueOf(ResourceUtils.getFile(FILE_NAME).toPath()), true);
             BufferedWriter printWriter = new BufferedWriter(fileWriter)
        ) {
            printWriter.append(operationString);
            printWriter.append(System.lineSeparator());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String createStringFromOperation(CashOperation cashOperation) {
        StringBuilder sb = new StringBuilder(cashOperation.getOperationType().toString());
        sb.append("|");
        sb.append(cashOperation.getCurrency());
        sb.append("|");
        cashOperation.getDenominationAmounts().entrySet()
                .forEach(denominationEntry -> {
                    sb.append(denominationEntry.getKey());
                    sb.append("x");
                    sb.append(denominationEntry.getValue());
                    sb.append(",");
                });
        sb.deleteCharAt(sb.length() -1 );
        return sb.toString();
    }
}
