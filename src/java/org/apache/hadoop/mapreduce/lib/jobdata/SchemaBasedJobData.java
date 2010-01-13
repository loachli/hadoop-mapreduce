/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.mapreduce.lib.jobdata;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.serializer.avro.AvroSerialization;
import org.apache.hadoop.mapreduce.JobContext;

/**
 * Methods that configure the use of schema-based serialization mechanisms
 * for intermediate and output types.
 *
 * This is the base for configuration of AvroGenericSerialization
 * and other schema-based serialization mechanisms.
 */
public class SchemaBasedJobData {
  protected SchemaBasedJobData() { }

  /**
   * Get the key schema for the map output data.
   * @return the map output key schema name.
   */
  public static Schema getMapOutputKeySchema(Configuration conf) {
    Map<String, String> metadata = conf.getMap(
        JobContext.MAP_OUTPUT_KEY_METADATA);
    return Schema.parse(metadata.get(AvroSerialization.AVRO_SCHEMA_KEY));
  }

  /**
   * Get the value schema for the map output data.
   * @return the map output value schema.
   */
  public static Schema getMapOutputValueSchema(Configuration conf) {
    Map<String, String> metadata = conf.getMap(
        JobContext.MAP_OUTPUT_VALUE_METADATA);
    return Schema.parse(metadata.get(AvroSerialization.AVRO_SCHEMA_KEY));
  }

  /**
   * Set the key schema for the map output data. This allows the user to
   * specify the map output key schema to be different than the final output
   * key schema.
   *
   * @param schema the map output key schema.
   */
  public static void setMapOutputKeySchema(Configuration conf, Schema schema) {
    Map<String, String> metadata = new HashMap<String, String>();
    metadata.put(AvroSerialization.AVRO_SCHEMA_KEY, schema.toString());
    conf.setMap(JobContext.MAP_OUTPUT_KEY_METADATA, metadata);
    conf.setBoolean(JobContext.MAP_OUTPUT_KEY_METADATA_SET, true);
  }

  /**
   * Set the value schema for the map output data. This allows the user to
   * specify the map output value schema to be different than the final output
   * value schema.
   *
   * @param schema the map output value schema.
   */
  public static void setMapOutputValueSchema(Configuration conf,
      Schema schema) {
    Map<String, String> metadata = new HashMap<String, String>();
    metadata.put(AvroSerialization.AVRO_SCHEMA_KEY, schema.toString());
    conf.setMap(JobContext.MAP_OUTPUT_VALUE_METADATA, metadata);
    conf.setBoolean(JobContext.MAP_OUTPUT_VALUE_METADATA_SET, true);
  }
}