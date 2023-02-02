package kr.or.gadget;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;



public class mysqlTest {

	 private static final String DRIVER = "com.mysql.jdbc.Driver";
	 private static final String URL = "jdbc:mysql://database-1.ckl8oivqdkco.ap-northeast-2.rds.amazonaws.com/test?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8"; // jdbc:mysql://127.0.0.1:3306/여러분이 만드신 스키마이름
	 private static final String USER = "admin"; //DB 사용자명
	 private static final String PW = "10041004";   //DB 사용자 비밀번호
	 
	 @Setter(onMethod_ = @Autowired)
	 private DataSource ds;
	 
	 @Test
	 public void testConnection() throws Exception{
		 Class.forName(DRIVER);
		  
		 try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			 System.out.println("성공");
			 System.out.println(con);
		 }catch (Exception e) {
			 System.out.println("에러발생");
			 e.printStackTrace();
		 }
	 }

}

//<bean id="hikariConfig" class="com.zaxxer.hikari.HikariConfig"> 
//<property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
//<property name="jdbcUrl" value="jdbc:mysql://database-1.ckl8oivqdkco.ap-northeast-2.rds.amazonaws.com/[스키마]" />
//<property name="username" value="[아이디]" /> 
//<property name="password" value="[비번]" />       
//</bean>