package eu.senla.management.utils.table;

import eu.senla.management.common.BaseActions;
import eu.senla.management.common.Wait;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class Table<T> {
    private final WebElement table;
    private final Function<List<WebElement>, T> rowMapper;

    public Table(WebElement table, Function<List<WebElement>, T> rowMapper) {
        this.table = table;
        this.rowMapper = rowMapper;
    }

    private List<String> getHeaders() {
        return BaseActions.getValueAll(By.cssSelector("[role='columnheader']"));

    }

    private List<T> getRows() {
//        return table.await(TBODY)
//                .findElements(TR)
//                .map(tr ->
//                        rowMapper.apply(
//                                tr.findElements(TD).collect(toList())
//                        )
//                ).collect(toList());

        return Wait.waitFPresenceAll(By.cssSelector(".oxd-table-body [role='row']"))
                .stream()
                .map(tr ->
                        rowMapper.apply(
                                tr.findElements(By.cssSelector("[role='cell']"))
                                )
                )
                .collect(Collectors.toList());
    }

    public TableContents<T> getContents() {
        return new TableContents<>(getHeaders(), getRows());
    }
}
