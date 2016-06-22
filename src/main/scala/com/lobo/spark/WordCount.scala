package com.lobo.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Lobo on 2016/6/4.
  */
object WordCount {

  def count(lines: RDD[String]): RDD[(String, Int)] = {
    val wordsCount = lines.flatMap(line => line.split("\\W+"))
      .map(word => (word,1))
      .reduceByKey(_ + _)
    wordsCount
  }


  def main(args: Array[String]): Unit = {

    val sc = new SparkContext(new SparkConf().setAppName("wordCount").setMaster("local[4]"))
    println("read input file")
    val textFile = sc.textFile("src/test/sampleWords.txt")
    val countRDD = count(textFile)
//    val countRDD = {
//      textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
//    }

    try {
      countRDD.saveAsTextFile("out/output.txt")
    } catch {
      case e: Exception => println("Folder already exist")
    }
  }


}
