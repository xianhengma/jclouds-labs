/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
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
package org.jclouds.azurecompute.xml;

import static org.jclouds.util.SaxUtils.currentOrNull;

import org.jclouds.azurecompute.domain.Role.ResourceExtensionReference.ResourceExtensionParameterValue;
import org.jclouds.http.functions.ParseSax;
import org.xml.sax.Attributes;

public final class ResourceExtensionParameterValueHandler extends ParseSax.HandlerForGeneratedRequestWithResult<ResourceExtensionParameterValue> {
   private String key;
   private String value;
   private String type;

   private StringBuilder currentText = new StringBuilder();

   @Override public void startElement(String uri, String localName, String qName, Attributes attributes) {
   }

   @Override public ResourceExtensionParameterValue getResult() {
      ResourceExtensionParameterValue result = ResourceExtensionParameterValue.create(key, value, type);
      resetState(); // handler is called in a loop.
      return result;
   }

   private void resetState() {
      key = value = type = null;
   }

   @Override public void endElement(String ignoredUri, String ignoredName, String qName) {
      if (qName.equals("Key")) {
         key = currentOrNull(currentText);
      } else if (qName.equals("Value")) {
         value = currentOrNull(currentText);
      } else if (qName.equals("Type")) {
         type = currentOrNull(currentText);
      }
      currentText.setLength(0);
   }

   @Override public void characters(char ch[], int start, int length) {
      currentText.append(ch, start, length);
   }

}
