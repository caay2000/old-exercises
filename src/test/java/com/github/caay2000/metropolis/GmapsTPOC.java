package com.github.caay2000.metropolis;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import com.github.caay2000.metropolis.collector.RandomDataMeter;
import com.github.caay2000.metropolis.engine.Position;
import com.github.caay2000.metropolis.event.SystemEventBus;
import com.github.caay2000.metropolis.reporter.SystemReporter;
import com.github.caay2000.metropolis.simulation.Simulation;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;

public class GmapsTPOC {

    private static final String POLYLINE = "mpjyHx`i@VjAVKnAh@BHHX@LZR@Bj@Ml@WWc@]w@bAyAfBmCb@o@pLeQfCsDVa@@ODQR}AJ{A?{BGuAD_@FKb@MTUX]Le@^kBVcAVo@Ta@|EaFh@m@FWaA{DCo@q@mCm@cC{A_GWeA}@sGSeAcA_EOSMa@}A_GsAwFkAiEoAaFaBoEGo@]_AIWW{AQyAUyBQqAI_BFkEd@aHZcDlAyJLaBPqDDeD?mBEiA}@F]yKWqGSkICmCIeZIuZi@_Sw@{WgAoXS{DOcAWq@KQGIFQDGn@Y`@MJEFIHyAVQVOJGHgFRJBBCCSKBcAKoACyA?m@^yVJmLJ{FGGWq@e@eBIe@Ei@?q@Bk@Hs@Le@Rk@gCuIkJcZsDwLd@g@Oe@o@mB{BgHQYq@qBQYOMSMGBUBGCYc@E_@H]DWJST?JFFHBDNBJ?LED?LBv@WfAc@@EDGNK|@e@hAa@`Bk@b@OEk@Go@IeACoA@a@PyB`@yDDc@e@K{Bi@oA_@w@]m@_@]QkBoAwC{BmAeAo@s@uAoB_AaBmAwCa@mAo@iCgAwFg@iDq@}G[uEU_GBuP@cICmA?eI?qCB{FBkCI}BOyCMiAGcAC{AN{YFqD^}FR}CNu@JcAHu@b@_E`@}DVsB^mBTsAQKkCmAg@[YQOIOvAi@[m@e@s@g@GKCKAEJIn@g@GYGIc@ScBoAf@{A`@uAlBfAG`@";

    @Test
    @Ignore
    public void robotTpoc() {

        List<LatLng> latLngs = PolylineEncoding.decode(POLYLINE);

        Robot robot = new Robot(new Position(latLngs.get(0).lat, latLngs.get(0).lng), 100d, new RandomDataMeter(), 15 * 60, new Simulation(), new SystemReporter(), new SystemEventBus());
//        Robot robot2 = new Robot(new Position(latLngs.get(0).lat, latLngs.get(0).lng), 10000000d, new RandomDataMeter(), 15*60);
        for (int i = 1; i < latLngs.size(); i++) {
            robot.moveTo(new Position(latLngs.get(i).lat, latLngs.get(i).lng));
            // robot2.moveTo(new Position(latLngs.get(i).lat, latLngs.get(i).lng));
        }
        Route route = robot.getRoute();
//        Route route2 = robot2.getRoute();

        System.out.println(route);
        System.out.println(route.getSteps().stream().filter(e -> e.getSpeed() < 1d).count());
//        System.out.println(route2);
    }

//    @Test
//    public void testmeters() {
//
//        List<LatLng> latLngs = PolylineEncoding.decode(POLYLINE);
//
//        double total = 0d;
//        double newTotal = 0d;
//        for (int i = 1; i < latLngs.size(); i++) {
//            LatLng origin = latLngs.get(i - 1);
//            LatLng destination = latLngs.get(i);
//
//            Double distance = DistanceCalculator.distanceBetween(new Position(origin.lat, origin.lng), new Position(destination.lat, destination.lng));
//            Double distance2 = DistanceCalculator.distanceBetween(new Position(origin.lat, origin.lng), new Position(destination.lat, destination.lng));
//
//            total = total + distance;
//            newTotal = newTotal + distance2;
//        }
//        System.out.println("total haversine : " + total);
//        System.out.println("total vincenty  : " + newTotal);
//    }
}
