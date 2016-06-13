/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.Format;
import com.google.android.exoplayer.extractor.DefaultTrackOutput;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;

/**
 * A base implementation of {@link MediaChunk}, for chunks that contain a single track.
 * <p>
 * Loaded samples are output to a {@link DefaultTrackOutput}.
 */
public abstract class BaseMediaChunk extends MediaChunk {

  private DefaultTrackOutput trackOutput;
  private int firstSampleIndex;

  /**
   * @param dataSource The source from which the data should be loaded.
   * @param dataSpec Defines the data to be loaded.
   * @param format See {@link #format}.
   * @param formatEvaluatorTrigger See {@link #formatEvaluatorTrigger}.
   * @param formatEvaluatorData See {@link #formatEvaluatorData}.
   * @param startTimeUs The start time of the media contained by the chunk, in microseconds.
   * @param endTimeUs The end time of the media contained by the chunk, in microseconds.
   * @param chunkIndex The index of the chunk.
   */
  public BaseMediaChunk(DataSource dataSource, DataSpec dataSpec, Format format,
      int formatEvaluatorTrigger, Object formatEvaluatorData, long startTimeUs, long endTimeUs,
      int chunkIndex) {
    super(dataSource, dataSpec, format, formatEvaluatorTrigger, formatEvaluatorData, startTimeUs,
        endTimeUs, chunkIndex);
  }

  /**
   * Initializes the chunk for loading, setting the {@link DefaultTrackOutput} that will receive
   * samples as they are loaded.
   *
   * @param trackOutput The output that will receive the loaded samples.
   */
  public void init(DefaultTrackOutput trackOutput) {
    this.trackOutput = trackOutput;
    this.firstSampleIndex = trackOutput.getWriteIndex();
  }

  /**
   * Returns the index of the first sample in the output that was passed to
   * {@link #init(DefaultTrackOutput)} that will originate from this chunk.
   */
  public final int getFirstSampleIndex() {
    return firstSampleIndex;
  }

  /**
   * Returns the track output most recently passed to {@link #init(DefaultTrackOutput)}.
   */
  protected final DefaultTrackOutput getTrackOutput() {
    return trackOutput;
  }

}
