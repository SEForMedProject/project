import  com.winyang.Repository.DoctorRepository

/**
  * Created by winyang on 2017/3/26.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val a = new DoctorRepository
    a.findBygood_atLike("感冒").get(1)
  }

}
