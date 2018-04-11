package ru.stqa.ptf.soap;

import com.buzzbuzhome.GeoIP;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test

    public void testMyIp(){

        GeoIP geoIP =  new GeoIPService().getGeoIPServiceSoap12().getGeoIP("185.238.208.18");
        assertEquals(geoIP.getCountryCode);
    }
}
