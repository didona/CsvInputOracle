package CsvOracles;

import CsvOracles.params.CsvRgParams;
import parse.radargun.Ispn5_2CsvParser;

import java.io.IOException;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt Date: 30/09/13
 */
public class RadargunCsvInputOracle extends CsvInputOracle<Ispn5_2CsvParser, CsvRgParams> {

   public RadargunCsvInputOracle(CsvRgParams param) throws IOException {
      super(param);
   }

   @Override
   protected Ispn5_2CsvParser _buildCsvParser(CsvRgParams param) throws IOException {
      log.trace("Ispn5_2CsvParser for " + param.getPath());
      return new Ispn5_2CsvParser(param.getPath());
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
   /*
   protected ReplicationProtocol replicationProtocol() {
      if (csvParser.getReplicationProtocol().equals("2PC"))
         return ReplicationProtocol.TWOPC;
      return ReplicationProtocol.valueOf(csvParser.getReplicationProtocol());
   }
   */

}
