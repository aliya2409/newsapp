package cucumber;

import com.github.springtestdbunit.annotation.DatabaseSetup;
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
    @DatabaseSetup(value = "classpath:news-data.xml")
    public void initAllNews() throws Exception {
        System.out.println("fv fvfv");
        IDatabaseConnection connection = getConnection();
        IDataSet dataSet = getDataSet();
        try{
            DatabaseOperation.DELETE_ALL.execute(connection, getDataSet());
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        }finally {
            connection.close();
        }
    }

    @After("@GettingDeletedNews")
    public void afterScenario() throws Exception {
        IDatabaseConnection connection = getConnection();
        try{
            DatabaseOperation.DELETE_ALL.execute(connection, getDataSet());
        }finally {
            connection.close();
        }
    }

    private IDatabaseConnection getConnection() throws Exception{
        return new DatabaseDataSourceConnection(dataSource);
    }

    private IDataSet getDataSet() throws Exception {
        InputStream is = new FileInputStream("src/test/java/cucumber/news-data.xml");
        return new XmlDataSet(is);
    }
}
