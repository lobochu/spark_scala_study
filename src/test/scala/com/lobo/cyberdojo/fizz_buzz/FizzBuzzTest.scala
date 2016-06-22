package com.lobo.cyberdojo.fizz_buzz

import com.lobo.cyberdojo.fizzbuzz.FizzBuzz
import org.scalatest.FunSuite

/**
  * Created by Lobo on 2016/6/13.
  * Write a program that prints the numbers from 1 to 100.
  * But for multiples of three print "Fizz" instead of the
  * number and for the multiples of five print "Buzz". For
  * numbers which are multiples of both three and five
  * print "FizzBuzz".
  * *
  * Sample output:
  * 1
  * 2
  * Fizz
  * 4
  * Buzz
  * Fizz
  * 7
  * 8
  * Fizz
  * Buzz
  * 11
  * Fizz
  * 13
  * 14
  * FizzBuzz
  * 16
  * 17
  * Fizz
  * 19
  * Buzz
  * ... etc up to 100
  * Dojo result:
  * http://cyber-dojo.org/kata/edit/A608F95353?avatar=owl
  */
class FizzBuzzTest extends FunSuite {

  test("given 1 , result should returns 1") {
    val result = FizzBuzz.eval(1)
    assert("1" == result)
  }

  test("given 3 , result should returns Fizz") {
    val result = FizzBuzz.eval(3)
    assert("Fizz" == result)
  }

  test("given 5 , result should returns Buzz") {
    val result = FizzBuzz.eval(5)
    assert("Buzz" == result)
  }


  test("given 15 , result should returns FizzBuzz") {
    val result = FizzBuzz.eval(15)
    assert("FizzBuzz" == result)
  }
}
