/* See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * Esri Inc. licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.esri.gpt.control.webharvest.client.atom;

import com.esri.gpt.control.webharvest.IterationContext;
import com.esri.gpt.control.webharvest.common.CommonResult;
import com.esri.gpt.framework.resource.query.Criteria;
import com.esri.gpt.framework.resource.query.Query;
import com.esri.gpt.framework.resource.query.Result;
import com.esri.gpt.framework.util.Val;
import java.util.logging.Logger;

/**
 * Atom query.
 */
class AtomQuery implements Query {
/** logger */
private static final Logger LOGGER = Logger.getLogger(AtomQuery.class.getCanonicalName());
/** iteration context */
private IterationContext context;
/** service info */
private BaseAtomInfo info;
/** service proxy */
private AtomProxy proxy;
/** query criteria */
private Criteria criteria;

/**
 * Creates instance of the query.
 * @param context iteration context
 * @param info service info
 * @param proxy service proxy
 * @param criteria query criteria
 */
public AtomQuery(IterationContext context, BaseAtomInfo info, AtomProxy proxy, Criteria criteria) {
  if (context==null) throw new IllegalArgumentException("No context provided.");
  if (info==null) throw new IllegalArgumentException("No info provided.");
  if (proxy==null) throw new IllegalArgumentException("No proxy provided.");
  this.context = context;
  this.info = info;
  this.proxy = proxy;
  this.criteria = criteria;
}

  @Override
public Result execute()  {
  LOGGER.finer("Executing query: "+Val.stripControls(this.toString()));
  Result r = new CommonResult(new AtomFolders(context, info, proxy, criteria)) {
    @Override
    public void destroy() {
      info.destroy();
    }
  };
  LOGGER.finer("Completed query execution: "+Val.stripControls(this.toString()));
  return r;
}

@Override
public String toString() {
  return "{protocol: " + proxy + ", criteria:" + criteria + "}";
}
}
