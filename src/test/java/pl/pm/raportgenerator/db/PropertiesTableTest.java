package pl.pm.raportgenerator.db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.pm.raportgenerator.model.Store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PropertiesTableTest {
    PropertiesTable propertiesTable = new PropertiesTable();

    @BeforeAll

    public void openConnectionTest() throws SQLException {
        propertiesTable.startConnection();
        propertiesTable.createDB();

    }

    private List<Store> prepareMockData() {
        List<Store> stores = new ArrayList<>();
        stores.add(new Store("supply", 25));
        stores.add(new Store("buy", 30));
        stores.add(new Store("buy", 12));
        stores.add(new Store("supply", 32));
        stores.add(new Store("other", 30));
        return stores;
    }

    @Before
    public void insertPreparedMockDataToDB() {
        propertiesTable.insertStoreToDB(prepareMockData());
    }

    @Test
    public void should_return_sum_of_buy_operation() throws SQLException {
        Assert.assertEquals(propertiesTable.selectSumOfOperationBuy(), 42);
    }

    @Test
    public void should_return_sum_of_supply_operation() throws SQLException {
        Assert.assertEquals(propertiesTable.selectSumOfOperationSupply(), 57);
    }

    @AfterAll
    public void closeConnectionTest() throws SQLException {
        propertiesTable.closeConnection();
    }

}