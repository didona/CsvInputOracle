import eu.cloudtm.autonomicManager.commons.ReplicationProtocol;
import parse.radargun.Ispn5_2CsvParser;

import java.io.IOException;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 30/09/13
 */
public class RadargunCsvInputOracle extends CsvInputOracle<Ispn5_2CsvParser> {

   public RadargunCsvInputOracle(String path) throws IOException {
      this.csvParser = new Ispn5_2CsvParser(path);
   }

   @Override
   protected double numThreadsPerNode() {
      return csvParser.numThreads();
   }

   protected double avgTxArrivalRate() {
      return csvParser.usecThroughput() * csvParser.getNumNodes();
   }

   protected double memory() {
      return 1e-6 * csvParser.mem();
   }

   protected double numberOfEntries() {
      return csvParser.numKeys();
   }

   protected ReplicationProtocol replicationProtocol() {
      if (csvParser.getReplicationProtocol().equals("2PC"))
         return ReplicationProtocol.TWOPC;

      return ReplicationProtocol.valueOf(csvParser.getReplicationProtocol());
   }

}
