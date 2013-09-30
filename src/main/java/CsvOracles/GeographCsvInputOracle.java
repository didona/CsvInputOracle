package CsvOracles;

import CsvOracles.params.CsvGeoParams;
import eu.cloudtm.autonomicManager.commons.ReplicationProtocol;
import parse.geograph.GeographCsvParser;

import java.io.IOException;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 30/09/13
 */
public class GeographCsvInputOracle extends CsvInputOracle<GeographCsvParser, CsvGeoParams> {

   public GeographCsvInputOracle(CsvGeoParams param) throws IOException {
      super(param);
   }

   @Override
   protected GeographCsvParser _buildCsvParser(CsvGeoParams param) throws IOException {
     return new GeographCsvParser(param.getPath(), param.getInit(), param.getEnd());
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
