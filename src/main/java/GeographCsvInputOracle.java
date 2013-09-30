import eu.cloudtm.autonomicManager.commons.ReplicationProtocol;
import parse.geograph.GeographCsvParser;
import parse.timestamp.CsvTimestamp;

import java.io.IOException;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 30/09/13
 */
public class GeographCsvInputOracle extends CsvInputOracle<GeographCsvParser> {

   public GeographCsvInputOracle(String path, CsvTimestamp init, CsvTimestamp end) throws IOException {
      this.csvParser = new GeographCsvParser(path, init, end);
   }

   @Override
   protected double numThreadsPerNode() {
      return 1;
   }

   @Override
   protected double avgTxArrivalRate() {
      return csvParser.getAvgParam("AvgTxArrivalRate");
   }

   @Override
   protected double memory() {
      return csvParser.getAvgParam("MemoryInfo.used") * 1e-6;
   }

   @Override
   protected double numberOfEntries() {
      return csvParser.getAvgParam("NumberOfEntries");
   }

   protected ReplicationProtocol replicationProtocol() {
      throw new RuntimeException("TBI");
   }
}
