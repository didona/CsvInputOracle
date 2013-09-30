import eu.cloudtm.autonomicManager.commons.EvaluatedParam;
import eu.cloudtm.autonomicManager.commons.ForecastParam;
import eu.cloudtm.autonomicManager.commons.Param;
import eu.cloudtm.autonomicManager.commons.ReplicationProtocol;
import eu.cloudtm.autonomicManager.oracles.InputOracle;
import parse.common.CsvParser;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 30/09/13
 */
public abstract class CsvInputOracle<C extends CsvParser> implements InputOracle {


   protected C csvParser;

   @Override
   public Object getParam(Param param) {
      switch (param) {
         case NumNodes:
            return (long) numNodes();
         case ReplicationDegree:
            return (long) replicationDegree();
         case AvgPutsPerWrTransaction:
            return putsPerWrXact();
         case AvgPrepareCommandSize:
            return (long) prepareCommandSize();
         case MemoryInfo_used:
            return (long) memory();
         case AvgGetsPerROTransaction:
            return (long) getsPerRoXact();
         case AvgGetsPerWrTransaction:
            return (long) getsPerWrXact();
         case LocalUpdateTxLocalServiceTime:
            return (long) localUpdateTxLocalServiceTime();
         case LocalUpdateTxPrepareServiceTime:
            return (long) localUpdateTxPrepareServiceTime();
         case LocalUpdateTxCommitServiceTime:
            return (long) localUpdateTxCommitServiceTime();
         case LocalUpdateTxLocalRollbackServiceTime:
            return (long) localUpdateTxLocalRollbackServiceTime();
         case LocalUpdateTxRemoteRollbackServiceTime:
            return (long) localUpdateTxRemoteRollbackServiceTime();
         case RemoteGetServiceTime:
            return (long) remoteGetServiceTime();
         case GMUClusteredGetCommandServiceTime:
            return gmuClusterGetCommandServiceTime();
         case RemoteUpdateTxPrepareServiceTime:
            return (long) remoteUpdateTxPrepareServiceTime();
         case RemoteUpdateTxCommitServiceTime:
            return (long) remoteUpdateTxCommitServiceTime();
         case RemoteUpdateTxRollbackServiceTime:
            return (long) remoteUpateTxRollbackServiceTime();
         case ReadOnlyTxTotalCpuTime:
            return localReadOnlyTxTotalCpuTime();
         case PercentageSuccessWriteTransactions:
            return writePercentage();
         // parameter added to make this class DAGS compliant
         case PercentageWriteTransactions:
            return writePercentage();
         case AvgLocalGetTime:
            return (long) avgLocalGetTime();
         case LocalUpdateTxPrepareResponseTime:
            return (long) localUpdateTxPrepareResponseTime();
         case LocalUpdateTxLocalResponseTime:
            return (long) LocalUpdateTxLocalResponseTime();
         case AverageWriteTime:
            return (long) AverageWriteTime();
         //these are not present in csvfile
         case AvgTxArrivalRate:
            return avgTxArrivalRate();
         case AvgNTCBTime:
            return (long) avgNTCBTime();
         case NumberOfEntries:
            return (int) numberOfEntries();
         case AvgClusteredGetCommandReplySize:
            return (long) avgClusteredGetCommandReplySize();
         default:
            throw new IllegalArgumentException("Param " + param + " is not present");
      }

   }

   @Override
   public Object getEvaluatedParam(EvaluatedParam evaluatedParam) {
      switch (evaluatedParam) {
         case MAX_ACTIVE_THREADS:
            return (int) numThreadsPerNode();
         case ACF:
            return acf();
         case CORE_PER_CPU:
            return (int) cpus();
         default:
            throw new IllegalArgumentException("Param " + evaluatedParam + " is not present");
      }
   }

   @Override
   public Object getForecastParam(ForecastParam forecastParam) {
      switch (forecastParam) {
         case ReplicationProtocol:
            return replicationProtocol();
         case ReplicationDegree:
            return (long) replicationDegree();
         case NumNodes:
            return (long) numNodes();
         default:
            throw new IllegalArgumentException("Param " + forecastParam + " is not present");
      }
   }

   /**
    * AD HOC METHODS *
    */

   protected double numNodes() {
      return csvParser.getAvgParam("numNodes");
   }

   private double replicationDegree() {
      return csvParser.getAvgParam("ReplicationDegree");
   }

   private double putsPerWrXact() {
      return csvParser.getAvgParam("AvgNumPutsBySuccessfulLocalTx");
   }

   protected abstract double numThreadsPerNode();

   private double prepareCommandSize() {
      return csvParser.getAvgParam("AvgPrepareCommandSize");
   }

   private double acf() {
      return 1.0D / numberOfEntries();
   }

   protected abstract double memory();

   private double cpus() {
      return 2;
   }

   protected abstract ReplicationProtocol replicationProtocol();

   private double getsPerRoXact() {
      return csvParser.getAvgParam("AvgGetsPerROTransaction");
   }

   private double getsPerWrXact() {
      return csvParser.getAvgParam("AvgGetsPerWrTransaction");
   }

   private double localUpdateTxLocalServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxLocalServiceTime");
   }

   private double localUpdateTxPrepareServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxPrepareServiceTime");
   }

   private double localUpdateTxCommitServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxCommitServiceTime");
   }

   private double localUpdateTxLocalRollbackServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxLocalRollbackServiceTime");
   }

   private double localUpdateTxRemoteRollbackServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxRemoteRollbackServiceTime");
   }

   private double localReadOnlyTxTotalCpuTime() {
      return csvParser.getAvgParam("LocalReadOnlyTxLocalServiceTime");
   }

   private double remoteGetServiceTime() {
      return csvParser.getAvgParam("RemoteGetServiceTime");
   }

   private double remoteUpdateTxPrepareServiceTime() {
      return csvParser.getAvgParam("RemoteUpdateTxPrepareServiceTime");
   }

   private double remoteUpdateTxCommitServiceTime() {
      return csvParser.getAvgParam("RemoteCommitCommandServiceTime");
   }

   private double remoteUpateTxRollbackServiceTime() {
      return csvParser.getAvgParam("RemoteRollbackServiceTime");
   }

   private double gmuClusterGetCommandServiceTime() {
      return csvParser.getAvgParam("GMUClusteredGetCommandServiceTime");
   }

   private double writePercentage() {
      return csvParser.getAvgParam("PercentageSuccessWriteTransactions");
   }

   protected abstract double avgTxArrivalRate();

   private double AvgNTCBTime() {
      return 0D;
   }

   private double avgLocalGetTime() {
      return csvParser.getAvgParam("avgLocalGetTime");
   }

   private double localUpdateTxPrepareResponseTime() {
      return csvParser.getAvgParam("localUpdateTxPrepareResponseTime");
   }

   private double LocalUpdateTxLocalResponseTime() {
      return csvParser.getAvgParam("LocalUpdateTxLocalResponseTime");
   }

   private double AverageWriteTime() {
      return csvParser.getAvgParam("LocalUpdateTxLocalServiceTime");//adapted
   }

   protected abstract double numberOfEntries();

   private double AvgClusteredGetCommandReplySize() {
      return csvParser.getAvgParam("AvgClusteredGetCommandReplySize");
   }

   private double avgNTCBTime() {
      return csvParser.getAvgParam("AvgNTCBTime");
   }

   private double avgClusteredGetCommandReplySize() {
      return csvParser.getAvgParam("AvgClusteredGetCommandReplySize");
   }


}
