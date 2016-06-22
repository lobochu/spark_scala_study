package com.lobo.cyberdojo.combinde_number

/**
  * Created by Lobo on 2016/6/12.
  */
object CombinedNumber {
  def combine(ints: List[Int]): String = {
    val sorted = ints.sortWith(sortByStringOrder(_, _)).mkString
    sorted
  }

  def sortByStringOrder(i1: Int, i2: Int): Boolean = {
    val s1 = i1.toString
    val s2 = i2.toString
    if ((s1 + s2) > (s2 + s1))
      true
    else
      false
  }
}
