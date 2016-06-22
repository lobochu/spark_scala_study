package com.lobo.cyberdojo.combined_number

import com.lobo.cyberdojo.combinde_number.CombinedNumber
import org.scalatest.{FunSuite, ShouldMatchers}

/**
  * Created by Lobo on 2016/6/9.
  * Write a function accepting a list of non negative integers,
  * and returning their largest possible combined number
  * as a string. For example
  * *
  * given [50, 2, 1, 9] it returns "95021"    (9 + 50 + 2 + 1)
  * given [5, 50, 56]   it returns "56550"    (56 + 5 + 50)
  * given [420, 42, 423] it returns "42423420" (42 + 423 + 420)
  * Dojo result:
  * http://cyber-dojo.org/kata/edit/511AFBDD0B?avatar=spider
  */
class CombinedNumberTest extends FunSuite with ShouldMatchers {

  test("given [1, 2] , result should returns '21' (2+1)") {
    val combinedNum = CombinedNumber.combine(List(1, 2))
    assert("21" == combinedNum)
  }

  test("given [1, 2, 0] , result should returns '210'  (2+1)") {
    val combinedNum = CombinedNumber.combine(List(1, 2, 0))
    assert("210" == combinedNum)
  }

  test("given [50, 2, 1, 9] , result should returns '95021'  (9+50+2+1)") {
    val combinedNum = CombinedNumber.combine(List(50, 2, 1, 9))
    assert("95021" == combinedNum)
  }

  test("given [420, 42, 423] , result should returns '42423420'  (42+423+420)") {
    val combinedNum = CombinedNumber.combine(List(420, 42, 423))
    assert("42423420" == combinedNum)
  }

  test("given [0, 0] , result should returns '00'  (0+0)") {
    val combinedNum = CombinedNumber.combine(List(0, 0))
    assert("00" == combinedNum)
  }

}
