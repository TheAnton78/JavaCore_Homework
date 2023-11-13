package geoTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import java.util.function.Function;

public class GeoServiceTest {

    @ParameterizedTest
    @CsvSource(value = {
            "127.0.0.1, null, null, null, 0",
            "172.0.32.11, Moscow, RUSSIA, Lenina, 15",
            "96.44.183.149, New York, USA,  10th Avenue, 32",
            "172., Moscow, RUSSIA, null, 0",
            "96., New York, USA, null, 0"

    })
    void byIpTest(String ip, String city, String country, String street, int building){
        Function<String, Country> convert = str -> {
            if (str.equals("null")) {
                return null;
            }else{
                return Country.valueOf(str);
            }
        };
        Comparator<Country> compare = (location, location1) -> {
            if (location == null & location1 == null){
                return true;
            }else if (location == null & location1 != null) {
                return false;
            }else if (location1 == null) {
                return false;
            }else{
                return location.equals(location1);
            }
        };
        Location location = new Location(city, convert.apply(country) , street, building);
        GeoService geoService = new GeoServiceImpl();
        Location actual = geoService.byIp(ip);
        Assertions.assertTrue(compare.compare(actual.getCountry(),location.getCountry()) &
                String.valueOf(actual.getCity()).equals(location.getCity()) &
                String.valueOf(actual.getStreet()).equals(location.getStreet())&
                (actual.getBuiling() == location.getBuiling()));



    }


}
