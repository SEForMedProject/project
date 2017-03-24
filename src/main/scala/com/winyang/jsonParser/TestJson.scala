package com.winyang.jsonParser

import java.io.{File, FileOutputStream, PrintStream, PrintWriter}
import java.sql.{SQLException, Statement}
import java.util

import net.sf.json._

import scala.io.Source
import java.text.{NumberFormat, SimpleDateFormat}
import java.util.Date

import java.sql.Connection
import java.io.{File, PrintWriter}

/**
  * Created by winyang on 3/14/17.
  */
class TestJson {

  private val percentFormat: NumberFormat = java.text.NumberFormat.getPercentInstance()
  private val fail_log_path = "./logs/dataclean/dataClean_mistake.log"
  private val toFile = new PrintStream(new FileOutputStream(fail_log_path, false))
  private val toConsole = System.out
  private val now: Date = new Date()
  private val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
  val outPutPath = "/home/winyang/Documents/develop/python/doctorandill/data/filteredSplitData/"

  case class Doctor(name: String, password: String, title: String, hospital_id: Int, department_id: Int, good_at: String, rating: Float, num_treatment: Int, num_reservation: Int, introduction: String)

  val passWord = "1111"
  //2个不可见字符
  private val a = " "
  private val b = "　"

  val mysqlTool = new MysqlConnector

  private def getDate: String = {
    dateFormat.format(now)
  }


  def setFileLogLevel(): Unit = {
    System.setOut(toFile)
    System.out.println(this.getDate)
  }

  def setConsoleLogLevel(): Unit = {
    System.out.println(this.getDate)
    System.setOut(toConsole)
  }


  def test(): Unit = {
    val path = "/home/winyang/Documents/develop/python/doctorandill/data/splitData/"

    //    val doctor = "doctor_data_1.json"
    //    val illInfo = "ill_data5.json"
    //    val a =
    //      """{"doctor_reservation":"no","doctor_department":"泌尿外科","doctor_treatment":"no","doctor_rate":"no","doctor_title":"主任医师","doctor_hospital_id":22,"doctor_name":"邹晓峰","doctor_introduction":"邹晓峰，男，教授、主任医师、硕士研究生导师，中共赣南医学院第一附属医院党委委员、副院长兼泌尿外科暨江西省尿路结石现代治疗中心主任。是江西省卫生厅首届有突出贡献中青年专家、江西省卫生系统首批学术和技术带头人培养对象、江西省高等学校中青年骨干教师、赣南医学院学科带头人，荣获首届“江西青年科学家提名”、首届“江西省五四青年奖章”和赣州市“十佳医师”称号，享受江西省政府特殊津贴，入选江西省新世纪百千万人才工程。系国际泌尿外科学会（SlU）会员，中国性医学专业委员会委员，中国医学装备协会委员，江西省泌尿外科学会常委，赣州市医学会第二届泌尿外科学专业委员会筹委会主任委员。能把握专业发展动态，积极开展本专业的国际、国内新技术，在尿路结石的现代治疗、泌尿男生殖系外科疾病微创手术治疗领域有较深的造诣，达到较高水平，部分技术国内领先，多项技术达国内先进、省内领先，众多技术在国内或省、市内率先开展；是国内知名泌尿外科专家。首创改良联合腔镜上尿路上皮肿瘤根治术和泌尿外科腹腔镜手术新式入路；在经自然腔道内镜手术（NOTES）、单孔腹腔镜手术（LESS）等领域积极探索并取得突破性进展，在全球率先开展经阴道NOTES辅助腹腔镜肾上腺肿瘤切除术，在国内率先开展经膀胱NOTES技术、经阴道NOTES辅助腹腔镜肾切除术和经脐单孔腹腔镜阴式肾切除术。常规开展腹腔镜根治性膀胱和前列腺切除术。娴熟开展的经皮肾镜取石术和泌尿外科腹腔镜手术积累了省内最大宗病例资料，其中不置肾造瘘管的、即刻二期无管化和小儿微创经皮肾镜取石术，为国内首先报告，并积累了国内最大宗病例资料。积极开展腔内微创技术治疗三聚氰胺所致婴幼儿尿路结石，成功治愈此类特殊结石患者33例。率先在省内开展的经输尿管软镜碎石术治愈患者例数居国内前列。主持完成的同种异体肾移植术创造了赣南肾移植存活时间最长、生存质量最好的历史记录。经常应邀赴省内外医疗单位会诊，指导开展新技术；已为40余家省内外医院培养泌尿外科专业技术人员近百名。为整体提升江西省尿路结石现代治疗及腔内泌尿外科技术水平作出了应有的贡献。主持国家卫生部项目2项，参与国家科技支撑计划项目1项，主持江西省自然科学基金项目和省科技厅科技支撑计划项目各1项、省卫生厅科技计划项目7项（含重大课题1项）、市局级课题6项；6项新技术通过市卫生局验收、5项科技成果通过科技部门鉴定；获江西省政府科技进步三等奖2项，江西省高等学校科技成果一、二等奖各1项，赣州市科技进步二等奖1项、三等奖2项（均为第一完成人）。以第一作者（或通信作者）名义发表SCI收录论文13篇、在《中华泌尿外科杂志》等国家核心学术期刊发表学术论文14篇。多次作为中华泌尿外科学会学术代表团成员赴美国等国家和地区出席全球顶尖学术会议，多次担任国际、国内重大学术会议主席团成员和会场主持人。于2003年主持了江西省泌尿外科界、赣州市卫生界第一个国家级继续医学教育项目“尿路结石的现代治疗”全国学习班；于2007年再次主持江西省泌尿外科界第二个国家级继续医学教育项目“腔内泌尿外科新技术的临床应用”全国学习班。正在培养硕士研究生多名。带领一附院泌尿外科创建了江西省医学领先学科、“江西省尿路结石现代治疗中心”、赣州市首批医学领先专业、赣南医学院重点学科、省级“青年文明号”科室和江西省教育系统“工人先锋号”先进集体。联系电话：0797-82695888283905手机：13097177918E-mail:zouxf2000@tom.com/gyfyurology@126.com/gzmnw2010@126.com医院网站：http://www.gyyfy.com科室网站：http://www.gzmnw.com（赣州泌尿网）","doctor_department_id":27,"doctor_hospital":"赣南医学院第一附属医院","doctor_good_at":["泌尿外科各种相关疾病治疗"]}"""
    //文件读取
    //过滤换行符
    //过滤空格
    //    val doctor_lines = Source.fromFile(path + doctor).getLines().toArray
    //    val ill_lines = Source.fromFile(path + illInfo).getLines().toList.map(x => x.replaceAll(" ", "").replaceAll("\\n", ""))
    // doctor_lines.slice(1, doctor_lines.length - 1).map(x => x.substring(0, x.length - 1)).foreach(x => println(x))

    //    val ill_data = ill_lines.reduce(_ + _).split("]},").map(x => (x + "]}"))

    //对数据进行检查,看格式是否规范
    //    (1 to 104).foreach(x => checkAndPrintlnLogInfo(filter_1(readAndGetArray(path + "doctor_data_" + x + ".json")), "doctor_data_" + x + ".json"))
    //(1 to 104).foreach(x => checkAndPrintlnLogInfo(filter_1(readAndGetArray(outPutPath + "doctor_data_filtered_" + x + ".json")), "doctor_data_filtered_" + x + ".json"))

    //检查并把合理数据写入指定文件下
    //  (1 to 104).foreach(x => checkAndPrintlnLogInfoAndFitered(filter_1(readAndGetArray(path + "doctor_data_" + x + ".json")), "doctor_data_" + x + ".json","doctor_data_filtered_" + x + ".json"))

    //检查并把数据插入数据库
    (1 to 104).foreach(x => checkAndInserIntoMysql(filter_1(readAndGetArray(outPutPath + "doctor_data_filtered_" + x + ".json")), "doctor_data_filtered_" + x + ".json","tb_doctor"))


    //        checkAndPrintlnLogInfo(doctor_data, "ill_data1.json")
    //    checkAndPrintlnLogInfo(ill_data,"ill_data5.json")

    //    println(JSONObject.fromObject(a.replaceAll("\\|", "").replaceAll("\\n", "").replaceAll(" ", "")).get("doctor_reservation"))
    //提取插入预先设定的数据库


  }

  private def readAndGetArray(path: String): Array[String] = {
    Source.fromFile(path).getLines().toArray.map(x => x.replaceAll(a, "").replaceAll(b, ""))
  }

  private def filter_1(doctor_lines: Array[String]): Array[String] = {
    doctor_lines.slice(1, doctor_lines.length - 1).map(x => {
      if (x.endsWith(",")) {
        x.substring(0, x.length - 1)
      } else x
    })
  }


  private def filter_2(good_at:String):String={
    val temp = good_at.slice(1,good_at.length-1)
    if(temp.contains("\"")){
      temp.slice(1,temp.length-1)
    }else{
      temp
    }
  }

  private def noToFloat(str: String): Float = {
    if (str.contains("no")) {
      0.0.toFloat
    } else {
      str.toFloat
    }
  }

  private def noToInt(str: String): Int = {
    if (str.contains("no")) {
      0
    } else {
      str.toInt
    }
  }

  private def checkAndInserIntoMysql(myJsonList: Array[String], fileName: String,tableName:String): Unit = {
    var fail = 0
    var succeed = 0
    val length = myJsonList.length
    var count = 0

    setConsoleLogLevel()
    System.out.println("<--------------begin------------------>")

//    setFileLogLevel()
    myJsonList.foreach(x => try {
      val result = JSONObject.fromObject(x)

      val temp = Doctor(result.getString("doctor_name"), passWord, result.getString("doctor_title"), result.getInt("doctor_hospital_id"), result.getInt("doctor_department_id"), filter_2(result.getString("doctor_good_at")), noToFloat(result.getString("doctor_rate")), noToInt(result.getString("doctor_treatment")), noToInt(result.getString("doctor_reservation")), result.getString("doctor_introduction"))
      //插入数据
//      println(temp.good_at)
      count = insert(mysqlTool.getConn, temp,tableName)
      if(count == 0){
        println(count)
        println(temp.name+","+temp.introduction)
      }
      succeed += count

    } catch {
      case e: JSONException => fail += 1; System.out.println(e.getMessage)
    })
    setConsoleLogLevel()
    System.out.println("fileName : " + fileName)
    System.out.println("failed : " + (length - succeed))
    System.out.println("succeed : " + succeed)
    System.out.println("sucess rate : " + percentFormat.format(succeed.toDouble / (succeed + fail)))
    System.out.println("<--------------end------------------>")
    System.out.println("\n")
  }

  private def checkAndPrintlnLogInfoAndFitered(myJsonList: Array[String], fileName: String, outPutFile: String): Unit = {
    var fail = 0
    var succeed = 0
    var i = 1

    val tempFile = outPutPath + outPutFile
    //创建文件
    val writer = new PrintWriter(new File(tempFile))
    writer.println("""{"data":[""")

    System.out.println("<--------------begin------------------>")
    myJsonList.foreach(x => try {
      val result = JSONObject.fromObject(x);
      if (i == 1) {
        writer.print(x);
        i += 1
      } else {
        writer.print(",\n");
        writer.print(x)
      };
      succeed += 1
    } catch {
      case e: JSONException => fail += 1; System.out.println(e.getMessage)
    })
    writer.print("\n")
    writer.println("""]}""")
    writer.close()

    System.out.println("fileName : " + fileName)
    System.out.println("failed : " + fail)
    System.out.println("succeed : " + succeed)
    System.out.println("sucess rate : " + percentFormat.format(succeed.toDouble / (succeed + fail)))
    System.out.println("<--------------end------------------>")
    System.out.println("\n")
  }

  private def checkAndPrintlnLogInfo(myJsonList: Array[String], fileName: String): Unit = {
    var fail = 0
    var succeed = 0

    System.out.println("<--------------begin------------------>")
    myJsonList.foreach(x => try {
      val result = JSONObject.fromObject(x);
      succeed += 1;
      //      println(transFormAndGet(result))
      succeed += 1
    } catch {
      case e: JSONException => fail += 1; System.out.println(e.getMessage)
    })
    System.out.println("fileName : " + fileName)
    System.out.println("failed : " + fail)
    System.out.println("succeed : " + succeed)
    System.out.println("sucess rate : " + percentFormat.format(succeed.toDouble / (succeed + fail)))
    System.out.println("<--------------end------------------>")
    System.out.println("\n")
  }

  def insert(conn: Connection, doctor: Doctor ,tableName:String): Int = {
    var count = 0
    try {
      val sql: String = "insert into "+ tableName +" (name,password,title,hospital_id,department_id,good_at,rating,num_treatment,num_reservation,introduction) values (" +"""'""" + doctor.name +"""',""" +"""'""" + doctor.password +"""',""" +"""'""" + doctor.title +"""',""" + doctor.hospital_id + "," + doctor.department_id + "," +"""'""" + doctor.good_at +"""',""" + doctor.rating + "," + doctor.num_treatment + "," + doctor.num_reservation + "," +"""'""" + doctor.introduction +"""')"""
      val stmt1: Statement = conn.createStatement()
      // 创建用于执行静态sql语句的Statement对象
      count = stmt1.executeUpdate(sql) // 执行插入操作的sql语句，并返回插入数据的个数
    } catch {
      case e: SQLException => e.printStackTrace()
    }
    count
  }

  def testMysql(conn: Connection): Unit = {
    try {
      val sql: String = "select count(*) from chapter_2"
      // 插入数据的sql语句
      val stmt1: Statement = conn.createStatement()
      // 创建用于执行静态sql语句的Statement对象
      val result = stmt1.executeQuery(sql) // 执行插入操作的sql语句，并返回插入数据的个数
      while (result.next()) {
        println(result.getInt(1))
      }
    } catch {
      case e: SQLException => e.printStackTrace()
    }
  }

  def niMa(): Unit ={
    (92 to 92).foreach(x => checkAndInserIntoMysql(filter_1(readAndGetArray(outPutPath + "doctor_data_filtered_" + x + ".json")), "doctor_data_filtered_" + x + ".json","haha"))
  }

}


object TestJson extends TestJson {
  def main(args: Array[String]): Unit = {
    //setFileLogLevel()
    setConsoleLogLevel()
    test()
    //    testMysql(mysqlTool.getConn)
    //niMa()
    mysqlTool.closeConn()
  }


}