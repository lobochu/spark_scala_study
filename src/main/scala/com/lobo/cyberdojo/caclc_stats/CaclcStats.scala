package com.lobo.cyberdojo.caclc_stats

/**
  * Created by Lobo on 2016/6/13.
  */
class CaclcStats(val input: List[Int]) {
  def min() = {
    input.min
  }

  def average() = {
    if (this.sum() == 0) {
      0
    } else {
      val result = input.map(x => x.toDouble).reduce(_ + _)/this.numberOfElements().toDouble
      BigDecimal(result).setScale(6, BigDecimal.RoundingMode.HALF_UP).toDouble
    }
  }

  def numberOfElements() = {
    input.size
  }

  def max(): Int = {
    input.max
  }

  def sum(): Int = {
    input.reduce(_ + _)
  }

}
