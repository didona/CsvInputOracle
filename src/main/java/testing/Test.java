package testing;

import CsvOracles.CsvOracleHelper;
import CsvOracles.GeographCsvInputOracle;
import CsvOracles.params.CsvGeoParams;
import eu.cloudtm.autonomicManager.commons.ForecastParam;
import eu.cloudtm.autonomicManager.commons.Param;
import eu.cloudtm.autonomicManager.commons.ReplicationProtocol;
import parse.timestamp.LongTimestamp;

import java.io.IOException;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class Test {


   public static void main(String[] args) throws Exception {

      //PropertyConfigurator.configure("conf/log4j.properties");
      LongTimestamp init = new LongTimestamp(1378566999063L);
      LongTimestamp end = new LongTimestamp(1378569856057L);
      CsvGeoParams param = new CsvGeoParams("data/ps_2pc.csv", init, end);
      GeographCsvInputOracle input = new GeographCsvInputOracle(param);
      for (Param p : CsvOracleHelper.csvParams())

         System.out.println(p.getKey() + " = " + input.getParam(p));

      //To do query, set the "target" configuration
      input.setForecastParam(ForecastParam.NumNodes, 5);
      input.setForecastParam(ForecastParam.ReplicationDegree, 5);
      input.setForecastParam(ForecastParam.ReplicationProtocol, ReplicationProtocol.TO);
      //invoke your oracle
      //...
   }
}
