package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;

public class DBUnitSteps {

    private DataSource dataSource;

    public DBUnitSteps(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Before("@CommentsToNews")
    public void loadCommentsTestData() throws Exception {
        loadData();
    }

    @Before("@AllNews")
    public void loadNewsTestData() throws Exception {
        loadData();
    }

    @After("@DeletingListNews")
    public void afterNewsIT() throws Exception {
        cleanup();
    }

    @After("@DeletingComment")
    public void afterCommentsIT() throws Exception {
        cleanup();
    }

    private void loadData() throws Exception {
        IDatabaseConnection connection = getConnection();
        IDataSet dataSet = getDataSet();
        try {
            DatabaseOperation.DELETE_ALL.execute(connection, getDataSet());
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        } finally {
            connection.close();
        }
    }

    private void cleanup() throws Exception {
        IDatabaseConnection connection = getConnection();
        try {
            DatabaseOperation.DELETE_ALL.execute(connection, getDataSet());
        } finally {
            connection.close();
        }
    }

    private IDatabaseConnection getConnection() throws Exception {
        return new DatabaseDataSourceConnection(dataSource);
    }

    private IDataSet getDataSet() throws Exception {
        InputStream is = new FileInputStream("src/test/java/cucumber/resources/data/news-data.xml");
        return new XmlDataSet(is);
    }
}
