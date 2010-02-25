package com.carrotsearch.junitbenchmarks.h2;

import java.io.File;
import java.sql.SQLException;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;

/**
 * Test H2 consumer's chart generation functionality. 
 */
@GenerateHistoryChart()
@BenchmarkOptions(callgc = false)
public class RepeatedTestSlave
{
    static final File dbFile = new File(RepeatedTestSlave.class.getName());
    static final File dbFileFull = new File(dbFile.getName() + ".h2.db");

    private static H2Consumer h2consumer;

    @BeforeClass
    public static void checkFile() throws SQLException
    {
        h2consumer = new H2Consumer(dbFile);
    }

    @Rule
    public MethodRule benchmarkRun = new BenchmarkRule(h2consumer);

    @Test
    public void testMethodA() throws Exception
    {
        Thread.sleep(new Random().nextInt(20));
    }

    @Test
    public void testMethodC() throws Exception
    {
        Thread.sleep(new Random().nextInt(30));
    }

    @AfterClass
    public static void verify() throws Exception
    {
        h2consumer.close();
    }
}
