package com.lobo.cyberdojo.caclc_stats

import org.scalatest.FunSuite

/**
  * Created by Lobo on 2016/6/13Your task is to process a sequence of integer numbers
  * to determine the following statistics:
  * *
  * o) minimum value
  * o) maximum value
  * o) number of elements in the sequence
  * o) average value
  * *
  * For example: [6, 9, 15, -2, 92, 11]
  * *
  * o) minimum value = -2
  * o) maximum value = 92
  * o) number of elements in the sequence = 6
  * o) average value = 21.833333.
  * Dojo result:
  * http://cyber-dojo.org/kata/edit/841B3E7A82?avatar=gopher*/
class CaclcStatsTest extends FunSuite {

  test("given [0,0]   result should returns min:0, max: 0, num of elem: 2, average: 0") {
    val caclc = new CaclcStats(List(0, 0))
    assert(0 == caclc.min())
    assert(0 == caclc.max())
    assert(2 == caclc.numberOfElements())
    assert(0 == caclc.average())
  }

  test("given [1,1]   result should returns min:1, max: 1, num of elem: 2, average: 1") {
    val caclc = new CaclcStats(List(1, 1))
    assert(1 == caclc.min())
    assert(1 == caclc.max())
    assert(2 == caclc.numberOfElements())
    assert(1 == caclc.average())
  }
  test("given [6, 9, 15, -2, 92, 11]   result should returns min:-2, max: 92, num of elem: 6, average: 21.833333") {
    val caclc = new CaclcStats(List(6, 9, 15, -2, 92, 11))
    assert(-2 == caclc.min())
    assert(92 == caclc.max())
    assert(6 == caclc.numberOfElements())
    assert(21.833333 == caclc.average())
  }


}
