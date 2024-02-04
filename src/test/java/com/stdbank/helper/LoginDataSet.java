package com.stdbank.helper;

import org.testng.annotations.DataProvider;

public class LoginDataSet extends Utils{

    @DataProvider(name="standard_user")
    public Object[][] testDataValidLogin(){
        ReadExcelFile configuration = new ReadExcelFile("./src/main/resources/testdata/testdata.xlsx");
        int rows = configuration.getRowCount(0);
        Object[][]loginCredentials = new Object[rows][2];

        for(int i=0;i<rows;i++)
        {
            loginCredentials[i][0] = configuration.getData(0, i, 0);
            loginCredentials[i][1] = configuration.getData(0, i, 1);
        }
        return loginCredentials;
    }

    @DataProvider(name="locked_out_user")
    public Object[][] lockedOutUserTestData(){
        ReadExcelFile configuration = new ReadExcelFile("./src/main/resources/testdata/testdata.xlsx");
        int rows = configuration.getRowCount(1);
        Object[][]loginCredentials = new Object[rows][2];

        for(int i=0;i<rows;i++)
        {
            loginCredentials[i][0] = configuration.getData(1, i, 0);
            loginCredentials[i][1] = configuration.getData(1, i, 1);
        }
        return loginCredentials;
    }

    @DataProvider(name="problem_user")
    public Object[][] problemUserTestData(){
        ReadExcelFile configuration = new ReadExcelFile("./src/main/resources/testdata/testdata.xlsx");
        int rows = configuration.getRowCount(2);
        Object[][]loginCredentials = new Object[rows][2];

        for(int i=0;i<rows;i++)
        {
            loginCredentials[i][0] = configuration.getData(2, i, 0);
            loginCredentials[i][1] = configuration.getData(2, i, 1);
        }
        return loginCredentials;
    }

    @DataProvider(name="performance_glitch_user")
    public Object[][] performanceUserTestData(){
        ReadExcelFile configuration = new ReadExcelFile("./src/main/resources/testdata/testdata.xlsx");
        int rows = configuration.getRowCount(3);
        Object[][]loginCredentials = new Object[rows][2];

        for(int i=0;i<rows;i++)
        {
            loginCredentials[i][0] = configuration.getData(3, i, 0);
            loginCredentials[i][1] = configuration.getData(3, i, 1);
        }
        return loginCredentials;
    }
    @DataProvider(name="error_user")
    public Object[][] errorUserTestData(){
        ReadExcelFile configuration = new ReadExcelFile("./src/main/resources/testdata/testdata.xlsx");
        int rows = configuration.getRowCount(4);
        Object[][]loginCredentials = new Object[rows][2];

        for(int i=0;i<rows;i++)
        {
            loginCredentials[i][0] = configuration.getData(4, i, 0);
            loginCredentials[i][1] = configuration.getData(4, i, 1);
        }
        return loginCredentials;
    }
    @DataProvider(name="visual_user")
    public Object[][] visualUserTestData(){
        ReadExcelFile configuration = new ReadExcelFile("./src/main/resources/testdata/testdata.xlsx");
        int rows = configuration.getRowCount(5);
        Object[][]loginCredentials = new Object[rows][2];

        for(int i=0;i<rows;i++)
        {
            loginCredentials[i][0] = configuration.getData(5, i, 0);
            loginCredentials[i][1] = configuration.getData(5, i, 1);
        }
        return loginCredentials;
    }

    @DataProvider(name="invalid_user")
    public Object[][] invalidUserTestData(){
        ReadExcelFile configuration = new ReadExcelFile("./src/main/resources/testdata/testdata.xlsx");
        int rows = configuration.getRowCount(6);
        Object[][]loginCredentials = new Object[rows][2];

        for(int i=0;i<rows;i++)
        {
            loginCredentials[i][0] = configuration.getData(6, i, 0);
            loginCredentials[i][1] = configuration.getData(6, i, 1);
        }
        return loginCredentials;
    }

    @DataProvider(name="filter_a_z")
    public Object[][] filterAtoZTestData(){
        ReadExcelFile configuration = new ReadExcelFile("./src/main/resources/testdata/testdata.xlsx");
        int rows = configuration.getRowCount(7);
        Object[][]loginCredentials = new Object[rows][3];

        for(int i=0;i<rows;i++)
        {
            loginCredentials[i][0] = configuration.getData(7, i, 0);
            loginCredentials[i][1] = configuration.getData(7, i, 1);
            loginCredentials[i][2] = configuration.getData(7, i, 2);
        }
        return loginCredentials;
    }

    @DataProvider(name="filter_z_a")
    public Object[][] filterZtoATestData(){
        ReadExcelFile configuration = new ReadExcelFile("./src/main/resources/testdata/testdata.xlsx");
        int rows = configuration.getRowCount(8);
        Object[][]loginCredentials = new Object[rows][3];

        for(int i=0;i<rows;i++)
        {
            loginCredentials[i][0] = configuration.getData(8, i, 0);
            loginCredentials[i][1] = configuration.getData(8, i, 1);
            loginCredentials[i][2] = configuration.getData(7, i, 2);
        }
        return loginCredentials;
    }

    @DataProvider(name="filter_low_high")
    public Object[][] filterLowToHighTestData(){
        ReadExcelFile configuration = new ReadExcelFile("./src/main/resources/testdata/testdata.xlsx");
        int rows = configuration.getRowCount(9);
        Object[][]loginCredentials = new Object[rows][3];

        for(int i=0;i<rows;i++)
        {
            loginCredentials[i][0] = configuration.getData(9, i, 0);
            loginCredentials[i][1] = configuration.getData(9, i, 1);
            loginCredentials[i][2] = configuration.getData(7, i, 2);
        }
        return loginCredentials;
    }

    @DataProvider(name="filter_high_low")
    public Object[][] filterHighToLowTestData(){
        ReadExcelFile configuration = new ReadExcelFile("./src/main/resources/testdata/testdata.xlsx");
        int rows = configuration.getRowCount(10);
        Object[][]loginCredentials = new Object[rows][3];

        for(int i=0;i<rows;i++)
        {
            loginCredentials[i][0] = configuration.getData(10, i, 0);
            loginCredentials[i][1] = configuration.getData(10, i, 1);
            loginCredentials[i][2] = configuration.getData(7, i, 2);
        }
        return loginCredentials;
    }



}


