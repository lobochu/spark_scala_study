package com.lobo.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

/**
  * Created by Lobo on 2016/6/14.
  */
class WordCountTest  extends FlatSpec with Matchers with BeforeAndAfter{

  var sc: SparkContext = _

  before {
    val sparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test-word-count")
    sc = new SparkContext(sparkConf)
  }

  after {
    sc.stop()
  }

  behavior of "Word Count"

  it should "count words in text" in {
    val text =
      """Test Spark
        |Hello Lobo
        |Hello world
      """.stripMargin
    val lines: RDD[String] = sc.parallelize(List(text))
    val wordCounts: RDD[(String, Int)] = WordCount.count(lines)

    wordCounts.collect() should contain allOf(("Hello", 2), ("Spark", 1), ("Lobo", 1), ("Test", 1), ("world", 1))
  }


}
