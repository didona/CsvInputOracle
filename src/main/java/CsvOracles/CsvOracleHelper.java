package CsvOracles;

import eu.cloudtm.autonomicManager.commons.EvaluatedParam;
import eu.cloudtm.autonomicManager.commons.ForecastParam;
import eu.cloudtm.autonomicManager.commons.Param;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt Date: 30/09/13
 */
public class CsvOracleHelper {

   public static Set<Param> csvParams() {
      Set<Param> s = new HashSet<Param>();
      s.add(Param.NumNodes);
      s.add(Param.ReplicationDegree);
      s.add(Param.AvgPutsPerWrTransaction);
      s.add(Param.MemoryInfo_used);
      s.add(Param.AvgGetsPerROTransaction);
      s.add(Param.AvgNumPutsBySuccessfulLocalTx);
      s.add(Param.LocalUpdateTxLocalServiceTime);
      s.add(Param.LocalUpdateTxPrepareServiceTime);
      s.add(Param.LocalUpdateTxCommitServiceTime);
      s.add(Param.LocalUpdateTxLocalRollbackServiceTime);
      s.add(Param.LocalUpdateTxRemoteRollbackServiceTime);
      s.add(Param.LocalUpdateTxRemoteRollbackServiceTime);
      s.add(Param.LocalReadOnlyTxLocalServiceTime);
      s.add(Param.RemoteGetServiceTime);
      s.add(Param.GMUClusteredGetCommandServiceTime);
      s.add(Param.RemoteUpdateTxPrepareServiceTime);
      s.add(Param.RemoteUpdateTxCommitServiceTime);
      s.add(Param.RemoteUpdateTxRollbackServiceTime);
      s.add(Param.ReadOnlyTxTotalCpuTime);
      s.add(Param.PercentageSuccessWriteTransactions);
      s.add(Param.PercentageWriteTransactions);
      s.add(Param.AvgLocalGetTime);
      //s.add(Param.LocalUpdateTxPrepareResponseTime);
      s.add(Param.AverageWriteTime);
      s.add(Param.AvgTxArrivalRate);
      s.add(Param.AvgNTCBTime);
      s.add(Param.NumberOfEntries);
      s.add(Param.AvgClusteredGetCommandReplySize);
      s.add(Param.AvgGetsPerWrTransaction);
      s.add(Param.AvgRemoteGetRtt);
      s.add(Param.AvgCommitAsync);
      s.add(Param.AvgPrepareRtt);
      s.add(Param.AvgPrepareCommandSize);

      return s;
   }

   static Set<ForecastParam> csvForecastParams() {
      Set<ForecastParam> s = new HashSet<ForecastParam>();
      s.add(ForecastParam.NumNodes);
      s.add(ForecastParam.ReplicationProtocol);
      s.add(ForecastParam.ReplicationDegree);
      return s;
   }

   static Set<EvaluatedParam> csvEvaluatedParams() {
      Set<EvaluatedParam> s = new HashSet<EvaluatedParam>();
      s.add(EvaluatedParam.ACF);
      s.add(EvaluatedParam.CORE_PER_CPU);
      s.add(EvaluatedParam.MAX_ACTIVE_THREADS);
      return s;
   }


}
