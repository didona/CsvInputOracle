package testing;

import CsvOracles.RadargunCsvInputOracle;
import CsvOracles.params.CsvRgParams;
import eu.cloudtm.autonomicManager.commons.EvaluatedParam;
import eu.cloudtm.autonomicManager.commons.ForecastParam;
import eu.cloudtm.autonomicManager.commons.IsolationLevel;
import eu.cloudtm.autonomicManager.commons.ReplicationProtocol;
import org.apache.log4j.PropertyConfigurator;


/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class RgTest {
   static String test = "data/infinispan4_cloudtm_2.csv";

   public static void main(String[] args) throws Exception {
      PropertyConfigurator.configure("conf/log4j.properties");
      RadargunCsvInputOracle rg = new RadargunCsvInputOracle(new CsvRgParams(test));
      rg.setForecastParam(ForecastParam.NumNodes, 5);
      rg.setForecastParam(ForecastParam.ReplicationDegree, 5);
      rg.setForecastParam(ForecastParam.ReplicationProtocol, ReplicationProtocol.TWOPC);
      rg.setEvaluatedParam(EvaluatedParam.ISOLATION_LEVEL, IsolationLevel.RR);
      //Here you create your oracle
      //Oracle o = new Oracle_IMPL();
      //Invoke
      //OutputOracle_IMPl = o.forecast(rg)
      //Print
      //System.out.println(rg.throughput(0)+ " "+rg.responseTime(1)+ " "+rg.abortRate(1));

   }
}
