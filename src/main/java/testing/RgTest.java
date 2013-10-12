package testing;

import CsvOracles.RadargunCsvInputOracle;
import CsvOracles.params.CsvRgParams;
import org.apache.log4j.BasicConfigurator;



/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class RgTest {
   static String test = "data/infinispan4_cloudtm_2.csv";

   public static void main(String[] args) throws Exception {
      BasicConfigurator.configure();
      RadargunCsvInputOracle rg = new RadargunCsvInputOracle(new CsvRgParams(test));

   }
}
