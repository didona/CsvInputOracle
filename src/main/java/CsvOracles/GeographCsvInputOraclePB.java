package CsvOracles;/*
 * INESC-ID, Instituto de Engenharia de Sistemas e Computadores Investigação e Desevolvimento em Lisboa
 * Copyright 2013 INESC-ID and/or its affiliates and other
 * contributors as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

import CsvOracles.params.CsvGeoParams;
import parse.common.NODE_T;

import java.io.IOException;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 30/09/13
 */
public class GeographCsvInputOraclePB extends GeographCsvInputOracle {


   public GeographCsvInputOraclePB(CsvGeoParams param) throws IOException {
      super(param);
   }

   protected double getsPerRoXact() {
      return csvParser.getAvgParam("AvgGetsPerROTransaction", NODE_T.SLAVE);
   }

   protected double getsPerWrXact() {
      return csvParser.getAvgParam("AvgGetsPerWrTransaction", NODE_T.MASTER);
   }

   protected double localUpdateTxLocalServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxLocalServiceTime", NODE_T.MASTER);
   }

   protected double localUpdateTxPrepareServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxPrepareServiceTime", NODE_T.MASTER);
   }

   protected double localUpdateTxCommitServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxCommitServiceTime", NODE_T.MASTER);
   }

   protected double localUpdateTxLocalRollbackServiceTime() {
      return csvParser.getAvgParam("LocalUpdateTxLocalRollbackServiceTime", NODE_T.MASTER);
   }

   protected double localReadOnlyTxTotalCpuTime() {
      return csvParser.getAvgParam("LocalReadOnlyTxLocalServiceTime", NODE_T.SLAVE);
   }

   /**
    * Slaves returns 0, primary returns 1
    * @return
    */
   protected double writePercentage() {
      double primaryThroughput = csvParser.getAvgParam("Throughput",NODE_T.MASTER);
      return csvParser.getAvgParam("PercentageSuccessWriteTransactions");
   }
}
