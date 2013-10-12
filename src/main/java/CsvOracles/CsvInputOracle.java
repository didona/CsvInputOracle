package CsvOracles;

import CsvOracles.params.CsvInputOracleParams;
import eu.cloudtm.autonomicManager.commons.EvaluatedParam;
import eu.cloudtm.autonomicManager.commons.ForecastParam;
import eu.cloudtm.autonomicManager.commons.Param;
import eu.cloudtm.autonomicManager.commons.ReplicationProtocol;
import eu.cloudtm.autonomicManager.oracles.InputOracle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import parse.common.CsvParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt Date: 30/09/13
 */
public abstract class CsvInputOracle<C extends CsvParser, P extends CsvInputOracleParams> implements InputOracle {

   protected final static Log log = LogFactory.getLog(CsvInputOracle.class);
   private static final int TPC = 0, PB = 1, TO = 2;
   protected C csvParser;
   private HashMap<ForecastParam, Object> fMap = new HashMap<ForecastParam, Object>();
   private HashMap<EvaluatedParam, Object> eMap = new HashMap<EvaluatedParam, Object>();
   private HashMap<Param, Object> pMap = new HashMap<Param, Object>();

   protected CsvInputOracle(P param) throws Exception {
      log.trace("Building with param " + param);
      csvParser = _buildCsvParser(param);
      log.trace("Built " + csvParser);
      initHash();
   }

   public void setParam(Param p, Object o) {
      this.pMap.put(p, o);
   }

   public void setEvaluatedParam(EvaluatedParam e, Object o) {
      this.eMap.put(e, o);
   }

   public void setForecastParam(ForecastParam f, Object o) {
      this.fMap.put(f, o);
   }

   private void initHash() {
      log.trace("Initing hashMaps");
      Set<Param> p = CsvOracleHelper.csvParams();
      log.trace("Registered params " + p.toString());
      Set<ForecastParam> f = CsvOracleHelper.csvForecastParams();
      log.trace("Registered forecastParams " + f.toString());
      Set<EvaluatedParam> e = CsvOracleHelper.csvEvaluatedParams();
      log.trace("Registered evalParams " + e.toString());
      for (Param pp : p) {
         setParam(pp, _getParam(pp));
         log.trace("Param  " + pp + " set to " + _getParam(pp));
      }
      for (EvaluatedParam ee : e) {
         setEvaluatedParam(ee, _getEvaluatedParam(ee));
         log.trace("Eval  " + ee + " set to " + _getEvaluatedParam(ee));
      }
      for (ForecastParam ff : f) {
         setForecastParam(ff, _getForecastParam(ff));
         log.trace("Forecast  " + ff + " set to " + _getForecastParam(ff));
      }
   }

   public HashMap<ForecastParam, Object> getfMap() {
      return fMap;
   }

   public HashMap<EvaluatedParam, Object> geteMap() {
      return eMap;
   }

   public HashMap<Param, Object> getpMap() {
      return pMap;
   }

   @Override
   public Object getParam(Param p) {
      return this.pMap.get(p);
   }

   @Override
   public Object getEvaluatedParam(EvaluatedParam evaluatedParam) {
      return this.eMap.get(evaluatedParam);
   }

   @Override
   public Object getForecastParam(ForecastParam forecastParam) {
      return this.fMap.get(forecastParam);
   }

   private Object _getParam(Param param) {
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

   private Object _getEvaluatedParam(EvaluatedParam evaluatedParam) {
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

   private Object _getForecastParam(ForecastParam forecastParam) {
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

   protected ReplicationProtocol replicationProtocol() {
      double rp = csvParser.getAvgParam("CurrentProtocolAsInt");
      if (rp == TPC)
         return ReplicationProtocol.TWOPC;
      if (rp == PB)
         return ReplicationProtocol.PB;
      if (rp == TO)
         return ReplicationProtocol.TO;
      //throw new IllegalArgumentException("Replication protocol is " + rp + " !! " + TPC + " = TPC; " + PB + " = PB; " + TO + "+ = TO");
      log.error("Replication protocol is " + rp + " !! " + TPC + " = TPC; " + PB + " = PB; " + TO + "+ = TO" + " Returning " + TPC);
      return ReplicationProtocol.TWOPC;
   }

   protected double getsPerRoXact() {
      return csvParser.getAvgParam("AvgGetsPerROTransaction");
   }

   protected double getsPerWrXact() {
      return csvParser.getAvgParam("AvgGetsPerWrTransaction");
   }

   protected double localUpdateTxLocalServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxLocalServiceTime");
   }

   protected double localUpdateTxPrepareServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxPrepareServiceTime");
   }

   protected double localUpdateTxCommitServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxCommitServiceTime");
   }

   protected double localUpdateTxLocalRollbackServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxLocalRollbackServiceTime");
   }

   protected double localUpdateTxRemoteRollbackServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxRemoteRollbackServiceTime");
   }

   protected double localReadOnlyTxTotalCpuTime() {
      return csvParser.getAvgParam("LocalReadOnlyTxLocalServiceTime");
   }

   protected double remoteGetServiceTime() {
      return csvParser.getAvgParam("RemoteGetServiceTime");
   }

   private double remoteUpdateTxPrepareServiceTime() {
      return csvParser.getAvgParam("RemoteUpdateTxPrepareServiceTime");
   }

   private double remoteUpdateTxCommitServiceTime() {
      return csvParser.getAvgParam("RemoteUpdateTxCommitServiceTime");
   }

   private double remoteUpateTxRollbackServiceTime() {
      return csvParser.getAvgParam("RemoteUpdateTxRollbackServiceTime");
   }

   private double gmuClusterGetCommandServiceTime() {
      return csvParser.getAvgParam("GMUClusteredGetCommandServiceTime");
   }

   protected double writePercentage() {
      return csvParser.getAvgParam("PercentageSuccessWriteTransactions");
   }

   protected abstract double avgTxArrivalRate();

   private double AvgNTCBTime() {
      return csvParser.getAvgParam("AvgNTCBTime");
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

   protected abstract C _buildCsvParser(P param) throws IOException;

   @Override
   public String toString() {
      return "CsvInputOracle{" +
              "csvParser=" + csvParser +
              ", fMap=" + fMap +
              ", eMap=" + eMap +
              ", pMap=" + pMap +
              '}';
   }
}
