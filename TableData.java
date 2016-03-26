package com.example.tomas.fruitmachine;

/*
Project: Fruit Machine
Class: ShowResults
Purpose: Table data
Author: Tomas Hreha
Date: 25.3.2015
 */


import android.provider.BaseColumns;

public class TableData {
    public TableData() {

    }

    public static abstract class TableInfo implements BaseColumns {
        public static final String DatabaseName = "FruitMachine";

        public static final String FruitMachineResults = "FruitMachineResults";

        public static final String FirstColumnResult = "FirstColumnResult";
        public static final String SecondColumnResult = "SecondColumnResult";
        public static final String ThirdColumnResult = "ThirdColumnResult";
        public static final String Result = "Result";
        public static final String PrimaryKeyDateTime = "PrimaryKeyDateTime";
    }
}
