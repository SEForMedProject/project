package com.winyang.Repository

import java.sql.{Connection, ResultSet, SQLException, Statement}
import java.util.List

import com.medical.model.DoctorForSearch
import com.winyang.jsonParser.MysqlConnector

import scala.collection.JavaConversions._
import scala.collection.mutable.ArrayBuffer

/**
  * Created by winyang on 3/26/17.
  *
  * 重构的时候我只要取连接池中拿一个连接就好了
  */

class DoctorRepository {
  val mysqlTool = new MysqlConnector

 private def findByGoodAtLike(conn: Connection,diseaseName:String):  List[DoctorForSearch]= {
    var doctorForSeachList:ArrayBuffer[DoctorForSearch] = ArrayBuffer[DoctorForSearch]()
    //如果放在这里其实改变的是一个对象doctorForSearch中的值会导致后面出来的结果全都一样
    //var doctorForSearch = new DoctorForSearch
    try {
      val sql: String = "select  name, good_at ,introduction from tb_doctor where good_at like \'%"+diseaseName+"%\'"+" limit 100"
      // 插入数据的sql语句
      val stmt1: Statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
      // 创建用于执行静态sql语句的Statement对象
      val result = stmt1.executeQuery(sql)
      while (result.next()) {
        //这样局部变量创建才可以使得doctorForSearch不一样
        val doctorForSearch = new DoctorForSearch
        doctorForSearch.setName(result.getString(1))
//        doctorForSearch.setGood_at(result.getString(2))
        doctorForSearch.setIntroduction(result.getString(3))
        doctorForSeachList += doctorForSearch
      }
    } catch {
      case e: SQLException => e.printStackTrace()
    }
    doctorForSeachList.toList
  }

  def findBygood_atLike(diseaseName:String):List[DoctorForSearch]={
    findByGoodAtLike(mysqlTool.getConn,diseaseName)
  }


}
