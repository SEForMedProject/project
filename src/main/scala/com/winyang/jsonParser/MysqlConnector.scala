package com.winyang.jsonParser

import java.sql.{Connection, DriverManager}

/**
  * Created by winyang on 3/19/17.
  */
class MysqlConnector {
  private val driver: String = "com.mysql.jdbc.Driver"
  private val url: String = "jdbc:mysql://172.20.45.88:3306/db_qiuyi?" + "useUnicode=true&characterEncoding=UTF8"
  private val user: String = "medicalteam"
  private val pass: String = "medicalteam_2017"
  Class.forName(driver)
  private val conn: Connection = DriverManager.getConnection(url, user, pass)

  def getConn:Connection={
    this.conn
  }

  def closeConn():Unit={
    conn.close()
  }
}
