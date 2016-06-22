package com.lobo.spark

import com.holdenkarau.spark.testing.{RDDComparisons, SharedSparkContext}
import org.apache.spark.rdd.RDD
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by Lobo on 2016/6/15.
  */
class WordCountTestWithScalaTestingBase extends FlatSpec with Matchers with SharedSparkContext{


  behavior of "Word Count"

  it should "count words in text" in {
    val text =
      """Test Spark
        |Hello Lobo
        |Hello world
      """.stripMargin
    val inputRDD: RDD[String] = sc.parallelize(List(text))
    val expectedRDD: RDD[(String, Int)] = sc.parallelize(List(("Hello", 2), ("Spark", 1), ("Lobo", 1), ("Test", 1), ("world", 1)))
    val resultRDD: RDD[(String, Int)] = WordCount.count(inputRDD)

    assert(None === RDDComparisons.compare(resultRDD, expectedRDD))

  }

}
