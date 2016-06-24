package com.lobo.spark.recommend

import java.io.File

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}

/**
  * Created by Lobo on 2016/6/18.
  */
object Recommend {

  def main(args: Array[String]) {
    setLogger
    println("Preparing Phase ")
    val (ratings, movieTitle) = prepareData()
    println("Training Phase")
    print("Using " + ratings.count() + "")
    val model = ALS.train(ratings, 5, 20, 0.1)
    println("Training completed")
    recommend(model, movieTitle)
    println("Done")
  }

  def setLogger = {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("com").setLevel(Level.OFF)
    System.setProperty("spark.ui.showConsoleProgress", "false")
    Logger.getRootLogger().setLevel(Level.OFF)
  }

  def prepareData(): (RDD[Rating], Map[Int, String]) = {
    val sc = new SparkContext(new SparkConf()
      .setAppName("Recommend").setMaster("local[4]"))
    print("Start processing recommendation.")

    val DataDir = "src/test/resources"
    val rawUserData = sc.textFile(new File(DataDir, "u.data").toString)
    val rawRatings = rawUserData.map(_.split("\t").take(3))
    val ratingsRDD = rawRatings.map { case Array(user, movie, rating) =>
      Rating(user.toInt, movie.toInt, rating.toDouble)
    }

    println("Total: " + ratingsRDD.count.toString + " rating records.")


    println("Create Movie ID and Title mapping")
    val itemRDD = sc.textFile(new File(DataDir, "u.item").toString)
    val movieTitle = itemRDD.map(line => line.split("\\|").take(2))
      .map(array => ((array(0).toInt), array(1))).collect().toMap

    println("Display data")
    val numRatings = ratingsRDD.count()
    val numUsers = ratingsRDD.map(_.user).distinct().count()
    val numMovies = ratingsRDD.map(_.product).distinct().count()
    println("Total ratings: " + numRatings + " User: " + numUsers +
      " Movie: " + numMovies)
    return (ratingsRDD, movieTitle)

  }


  def recommend(model: MatrixFactorizationModel, movieTitle: Map[Int, String]) = {
    var choose = ""
    while (choose != "3") {
      print("Please choose recommend type : 1.By User 2.By Movie 3.Exit ? ")
      choose = readLine()
      if (choose == "1") {
        print("Please input user id?")
        val inputUserID = readLine()
        recommendMovies(model, movieTitle, inputUserID.toInt)
      } else if (choose == "2") {
        print(" Please input Movie ID? ")
        val inputMovieID = readLine()
        recommendUsers(model, movieTitle, inputMovieID.toInt)
      }
    }
  }

  def recommendUsers(model: MatrixFactorizationModel, movieTitle: Map[Int, String], movieID: Int) = {
    val recommendedUser = model.recommendUsers(movieID, 10)
    println("Based on MovidID: " + movieID + " movie name: " +
      movieTitle(movieID.toInt) + " remommend to below users: ")
    var i = 1
    recommendedUser.foreach { r =>
      println(i.toString + "User id: " + r.user + " rating: " + r.rating)
      i += 1
    }


  }

  def recommendMovies(model: MatrixFactorizationModel, movieTitle: Map[Int, String], inputUserID: Int): Unit = {
    val recommendedMovie = model.recommendProducts(inputUserID, 10)
    println("Recommend by user ID: " + inputUserID + " movie:")
    recommendedMovie.foreach {
      var i = 1
      r =>
        println(i.toString + "." +
          movieTitle(r.product) + " evaluation: " + r.rating.toString())
        i += 1
    }


  }
}
