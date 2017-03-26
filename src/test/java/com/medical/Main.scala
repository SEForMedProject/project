package com.medical

import com.medical.service.impl.Disease2DoctorServiceImpl
import com.winyang.Repository.DoctorRepository

/**
  * Created by winyang on 3/25/17.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val a = new Disease2DoctorServiceImpl
   println( a.getDoctorByDiseaseName("普通感冒").getData.size())
  }
}
