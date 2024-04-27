package stepsdefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.Date;

// Just for ensuring that features scenarios run parallel
public class Hooks {

    @Before
    public void logStartTime(Scenario scenario) {
        Date startTime = new Date();
        long threadId = Thread.currentThread().getId();
        System.out.println("Thread ID: " + threadId + ", Start Time: " + startTime + ", Scenario: " + scenario.getName());
    }

    @After
    public void logEndTime(Scenario scenario){
        Date EndTime = new Date();
        long threadId = Thread.currentThread().getId();
        System.out.println("Thread ID: " + threadId + ", End Time: " + EndTime + ", Scenario: " + scenario.getName());
    }
}

