package com.github.caay2000.metropolis;

import java.io.PrintStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.github.caay2000.metropolis.collector.RandomDataMeter;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.SystemEventBus;
import com.github.caay2000.metropolis.reporter.SystemReporter;
import com.github.caay2000.metropolis.simulation.Simulation;

@RunWith(MockitoJUnitRunner.class)
public class RobotIntegrationTest {

    private static final String POLYLINE = "mpjyHx`i@VjAVKnAh@BHHX@LZR@Bj@Ml@WWc@]w@bAyAfBmCb@o@pLeQfCsDVa@@ODQR}AJ{A?{BGuAD_@FKb@MTUX]Le@^kBVcAVo@Ta@|EaFh@m@FWaA{DCo@q@mCm@cC{A_GWeA}@sGSeAcA_EOSMa@}A_GsAwFkAiEoAaFaBoEGo@]_AIWW{AQyAUyBQqAI_BFkEd@aHZcDlAyJLaBPqDDeD?mBEiA}@F]yKWqGSkICmCIeZIuZi@_Sw@{WgAoXS{DOcAWq@KQGIFQDGn@Y`@MJEFIHyAVQVOJGHgFRJBBCCSKBcAKoACyA?m@^yVJmLJ{FGGWq@e@eBIe@Ei@?q@Bk@Hs@Le@Rk@gCuIkJcZsDwLd@g@Oe@o@mB{BgHQYq@qBQYOMSMGBUBGCYc@E_@H]DWJST?JFFHBDNBJ?LED?LBv@WfAc@@EDGNK|@e@hAa@`Bk@b@OEk@Go@IeACoA@a@PyB`@yDDc@e@K{Bi@oA_@w@]m@_@]QkBoAwC{BmAeAo@s@uAoB_AaBmAwCa@mAo@iCgAwFg@iDq@}G[uEU_GBuP@cICmA?eI?qCB{FBkCI}BOyCMiAGcAC{AN{YFqD^}FR}CNu@JcAHu@b@_E`@}DVsB^mBTsAQKkCmAg@[YQOIOvAi@[m@e@s@g@GKCKAEJIn@g@GYGIc@ScBoAf@{A`@uAlBfAG`@";
    private static final double TEST_SIMULATION_FACTOR = 1000d / (60d * 100d);

    @Mock
    private PrintStream printer;

    @Test
    public void test() throws Exception {

        Simulation simualtion = new Simulation(TEST_SIMULATION_FACTOR);
        EventBus eventBus = new SystemEventBus();
        RobotConfiguration robotConfiguration = new RobotConfiguration(new RandomDataMeter(), new SystemReporter(System.out));

        RobotApplication robot = new RobotApplication(simualtion, eventBus, robotConfiguration);

        robot.start(POLYLINE);
        Thread.sleep(20000l);
        robot.stop();
    }
}
