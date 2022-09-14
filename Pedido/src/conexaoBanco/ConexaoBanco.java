package conexaoBanco;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;


public class ConexaoBanco{
	private Connection connection=null;
	private Statement statement=null;
	//private ResultSet resultset=null;
	public Connection connectar() {
			String servidor ="jdbc:mysql://localhost:3306/db_pedido";
			String usuario="root";
			String senha="1234";
			String driver="com.mysql.cj.jdbc.Driver";
			try {
				Class.forName(driver);
				this.connection=DriverManager.getConnection(servidor,usuario,senha);
				this.statement =this.connection.createStatement();
			}
			catch(Exception e)
			{
				connection=null;
			}
			return connection;
	}
	
	public Connection conectar()
	{ String servidor="jdbc:mysql://localhost:3306/db_pedido";
	String usuario="root";
	String senha="1234";
	String driver="com.mysql.cj.jdbc.Driver";
	try
	{ Class.forName(driver) ;
	this.connection=DriverManager.getConnection(servidor,usuario,senha);
	this.statement=this.connection.createStatement();
	}
	catch(Exception e)
	{ connection=null; }
	return connection;
	}
	
}