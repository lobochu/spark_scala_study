package com.lobo.cyberdojo.fizzbuzz

/**
  * Created by Lobo on 2016/6/13.
  */
object FizzBuzz {

  def eval(x: Int): String = (x % 3, x % 5, x)
  match
  {
    case (0, 0, _) => "FizzBuzz"
    case (0, _, _) => "Fizz"
    case (_, 0, _) => "Buzz"
    case _ => "" + x
  }

  def main(args: Array[String]) {
   for (i <- 1 to 100){
      println(eval(i))
    }
  }

}
