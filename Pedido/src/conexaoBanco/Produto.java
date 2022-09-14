package conexaoBanco;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class Produto extends JFrame {
	
	private JTextField inputID;
	private JTextField inputDataCadastro;
	private JTextField inputDescricao;
	private JTable tabProduto;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produto frame = new Produto();
					frame.setVisible(true);
					frame.setSize(800, 600);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Produto() {
		getContentPane().setLayout(null);
		
		JLabel labelID = new JLabel("ID");
		labelID.setBounds(10, 80, 21, 20);
		getContentPane().add(labelID);
		
		JLabel labelDataCadastro = new JLabel("Data cadastro");
		labelDataCadastro.setBounds(10, 143, 96, 20);
		getContentPane().add(labelDataCadastro);
		
		JLabel labelDescricao = new JLabel("Descrição");
		labelDescricao.setBounds(10, 112, 72, 20);
		getContentPane().add(labelDescricao);
		
		JLabel labelAcoes = new JLabel("Ações	");
		labelAcoes.setBounds(10, 0, 55, 20);
		getContentPane().add(labelAcoes);
		
		inputID = new JTextField();
		inputID.setEditable(false);
		inputID.setBounds(109, 80, 101, 20);
		getContentPane().add(inputID);
		inputID.setColumns(10);
		
		inputDataCadastro = new JTextField();
		inputDataCadastro.setEditable(false);
		inputDataCadastro.setColumns(10);
		inputDataCadastro.setBounds(109, 143, 101, 20);
		getContentPane().add(inputDataCadastro);
		
		inputDescricao = new JTextField();
		inputDescricao.setEditable(false);
		inputDescricao.setColumns(10);
		inputDescricao.setBounds(109, 112, 101, 20);
		getContentPane().add(inputDescricao);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCriar.setBounds(89, 31, 77, 20);
		getContentPane().add(btnCriar);
		btnCriar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
				criar();

		}
	});
		
		JButton btnPreparoDeletar = new JButton("Preparo");
		btnPreparoDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preparoDeletar();
			}
		});
		btnPreparoDeletar.setBounds(363, 31, 89, 20);
		getContentPane().add(btnPreparoDeletar);
		
		
		JButton btnPreparoAlterar = new JButton("Preparo");
		btnPreparoAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preparoAlterar();
			}
		});
		btnPreparoAlterar.setBounds(189, 31, 83, 20);
		getContentPane().add(btnPreparoAlterar);


		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnDeletar.setBounds(451, 31, 89, 20);
		getContentPane().add(btnDeletar);
		
		tabProduto = new JTable();
		tabProduto.setBounds(10, 174, 566, 127);
		getContentPane().add(tabProduto);
		
		JButton btnPreparoCriar = new JButton("Preparo");
		btnPreparoCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preparoCriar();
			}
		});
		btnPreparoCriar.setBounds(10, 31, 89, 20);
		getContentPane().add(btnPreparoCriar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
		btnAlterar.setBounds(268, 31, 85, 20);
		getContentPane().add(btnAlterar);
		
		try
		{
			listarProduto();
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"conexão não realizada");
		}

	}
	
	private Connection conectar() throws SQLException{
		try {
			Connection conectar = null;
			ConexaoBanco objconexao = new ConexaoBanco();
			conectar = objconexao.connectar();
			if(conectar == null){
				throw new Exception("conexão não realizada");
		    }
			return conectar;
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex);
		}
		return null;
	}
	
	private void desabilitarInputs()
	{
		inputDescricao.setEditable(false);
		inputID.setEditable(false);
		inputDataCadastro.setEditable(false);
		
		inputDescricao.setText(null);
		inputID.setText(null);
		inputDataCadastro.setText(null);
		
	}

	private void listarProduto() throws SQLException{
		try {
			Connection conectar = this.conectar();
		    Statement query = conectar.createStatement();
		    ResultSet result = query.executeQuery("SELECT * FROM db_pedido.produto");
		    
			String[] colunasTabela = new String[]{"ID","Descrição","Pontuação"};
			DefaultTableModel modeloTabela = new DefaultTableModel(null,colunasTabela);
			
			modeloTabela.addRow(new String[]{"ID","DESCRIÇÂO","CADASTRO"});
			
			if(result != null) {
				
				while(result.next()) {
					
					modeloTabela.addRow(new String[] {
						String.valueOf(result.getInt("ID")),
						result.getString("descricao"),
						result.getString("data_cadastro")
					});
					
				}
				
			}
			tabProduto.setModel(modeloTabela);
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex);
		}
		
	}
	private void preparoCriar (){
		desabilitarInputs();
		inputDescricao.setEditable(true);
	}
	private void preparoAlterar (){
		desabilitarInputs();
		inputDescricao.setEditable(true);
		inputID.setEditable(true);
	}
	private void preparoDeletar (){
		desabilitarInputs();
		inputID.setEditable(true);
	}
	
	private void criar(){
		try{
			Connection conectar = this.conectar();
			Statement query = conectar.createStatement();
			
			query.executeUpdate("insert into db_pedido.produto(descricao) values('"+inputDescricao.getText()+"')");
			
			listarProduto();
			desabilitarInputs();
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Não foi possivél gravar."+ ex.getMessage());
		}
		
	}
	private void alterar(){
		try{
			Connection conectar = this.conectar();
			Statement query = conectar.createStatement();
			
			query.executeUpdate("update db_pedido.produto set descricao = '"+inputDescricao.getText()+"' where id = '"+inputID.getText()+"' ");
	
			listarProduto();
			desabilitarInputs();
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Não foi possivél gravar."+ ex.getMessage());
		}
		
	}
	private void deletar(){
		try{
			Connection conectar = this.conectar();
			Statement query = conectar.createStatement();
			
			query.executeUpdate("delete from db_pedido.produto where id = '"+inputID.getText()+"'");
			
			listarProduto();
			desabilitarInputs();
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Não foi possivél gravar."+ ex.getMessage());
		}
		
	}
}
