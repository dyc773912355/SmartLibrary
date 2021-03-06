package com.smartlibrary.dao;

import com.smartlibrary.domain.*;

import java.util.List;

/**
 * Created by tt on 2017/10/17.
 */
public interface schoolReportDao {
    //年度藏书文献总量
    List<app_resources> getResourceCountBy_year(app_resources n);
    //年度入馆总人次
    List<real_year_counts> getGctrlsCountBy_year(real_year_counts n);
    //按月入馆人次
    List<gctrl> getGctrlsCountBy_month(gctrl n);
    //年度借还总次数，借阅次数，还书次数
    List<Book_Lend> getBook_LendCountBy_year(Book_Lend n);
    //年度各学院借阅人次，还书人次
    List<library_report_academy> getBook_LendCountBy_academyAndyear(library_report_academy n);
    //年度借阅人次前三学院名称
    List<library_report_academy> getLendTop3By_academyAndyear(library_report_academy n);
    //年度人均借阅前3学院名称
    List<library_report_academy> getpreLendTop3By_academyAndyear(library_report_academy n);
    //年度各学院借书比
    List<library_report_academy> getLendratioBy_academyAndyear(library_report_academy n);
    //年度总借阅次数前十学生
    //年度总借阅次数前二十图书
    List<library_report_ranking_book> getop20bookBy_year(library_report_ranking_book n);
    //年度总借阅次数前二十著者
    List<library_report_ranking_author> getop20authorBy_year(library_report_ranking_author n);
    //年度在座时长统计
    List<library_report_IC_duration> geICdurationBy_year(library_report_IC_duration n);
    //年度电子阅览室使用次数统计
    List<IC_Times> geICdurationCountBy_year(IC_Times n);




    //<!--今年读者总借阅次数排名前10（包括学号、读者姓名、所属学院、总借阅次数）（表格）新表-学号-年份-->
    List<reader_report> getReder_booklendCount_Byyear(reader_report n);
    //<!--年度设备平均使用次数统计-->
    List<library_report_hour> getDeviceCount_Byhour(library_report_hour n);
    //<!-- 年度各时段图书平均借出册数及人数（如8:00-9:00借出39册，21人借阅）（折线）-->
    List<library_report_hour> getbooklendPeopleAndCount_Byhour(library_report_hour n);
    // <!--年度全校读者借阅分类统计-->
    List<Book_Lend> getBookkindAllCount_Byyear(Book_Lend n);
    // <!--3. 有借阅记录的教职工读者人数-->
    List<Book_Lend> getTeacherLendCount(Book_Lend n);
    // < <!--教职工读者共借阅图书册数-->
    List<Book_Lend> getTeacherLendCount_Byyear(Book_Lend n);
    //<!--2011年-2017年教职工借阅人数统计（包括：<10册, 11-20册, 21-30册, 31-50册, 51-100册, 101-200册, 201-300册, >300册）（表格）
   // 做起来麻烦点 ，循环一下，lend，back 借用一下做大小值-->
    List<library_report_month_mankinds> getTeacherCount_BycountAndyear(library_report_month_mankinds n);
    // <!--  ?历年各大类图书借书比例趋势图（2011-2017年，包括：I文学、O数理化、F经济、TP计算机、K历史地理、B哲学宗教、D政治法律、H语言文字、其他）（折线图）（top5的类别数据）-->
    List<Book_Lend> getTop5bookkind_Byyear(Book_Lend n);
    /*<!--  6. 年度图书馆进馆读者类型比例（包括本科生、硕士生、教职工、其他）（饼图）
    7. 2011-2017年进馆读者类型比例趋势（包括本科生、硕士生、教职工、其他）
    8. 年度各类型读者借书比例（包括本科生、硕士生、教职工、其他）（柱状图）
    9. 年度各类型读者续借比例（包括本科生、硕士生、教职工、其他）（柱状图）-->*/
    List<library_report_month_mankinds> getmankindCount_Byyear(library_report_month_mankinds n);
    List<library_report_month_mankinds> getmankindCount_Byoneyear(library_report_month_mankinds n);
    List<Print_Times> gettypeprints();
}
