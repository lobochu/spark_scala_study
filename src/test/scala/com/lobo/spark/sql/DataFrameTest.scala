package com.lobo.spark.sql

import com.holdenkarau.spark.testing.SharedSparkContext
import org.apache.spark.sql.SQLContext
import org.scalatest.FunSuite

/**
  * Created by Lobo on 2016/7/1.
  */
class DataFrameTest extends FunSuite with SharedSparkContext {

  import org.apache.spark.sql.types.{StructField, StructType}


  test("load data from json should be able to query with data frame") {
    val jsonRDD = sc.parallelize(
      Seq(
        """
        {"name":"Justin", "age":20}
        """,
        """
        {"name":"Michael", "age":18}
        """,
        """
        {"name":"Andy", "age":30}
        """
      )
    )
    val sqlContext = new SQLContext(sc)
    val people = sqlContext.jsonRDD(jsonRDD)
    //    val people = sqlContext.read.json("src/test/resources/people.json")
    people.printSchema()
    people.registerTempTable("jt_people")
    val ageLessThan20 = sqlContext.sql("select name from jt_people where age < 20")
    ageLessThan20.map(t => "Name:" + t(0)).collect().foreach(println)

  }


  test("convert via reflection should able to query with data frame") {

    val peopleText = List(
      "Michael, 29",
      "Andy, 30",
      "Justin, 19"
    )

    val people = sc.parallelize(peopleText)

    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._
    //Note : the Person object must declared out of the test class, else will get task not serializable exceptoin
    val peopleDF = people.map(_.split(",")).map(p => Person(p(0), p(1).trim.toInt)).toDF()
    peopleDF.registerTempTable("people_tb")

    val result = sqlContext.sql("select name from people_tb where age >= 20")
    result.map(t => "Name:" +
      t(0)).collect().foreach(println)
  }

}

case class Person(name: String, age: Int)
