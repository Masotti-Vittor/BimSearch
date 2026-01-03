package br.com.bimsearch.windows;
import br.com.bimsearch.elements.Connector;
import br.com.bimsearch.dal.DataAccessLayer;
import br.com.bimsearch.windows.SearchFilter;
import br.com.bimsearch.windows.SizeConnecWindow;
import br.com.bimsearch.windows.User;
import br.com.bimsearch.windows.ListPane;
import br.com.bimsearch.windows.ImagesPane;
import br.com.bimsearch.windows.AddElement;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * @author wasotty
**/

@SuppressWarnings("unused")
public class SearchWindow extends javax.swing.JFrame {
	private boolean argola, cabo, conduite, caboConduite, equipamento, aereo; // TRUE/FALSE of each checkbox.
	private int size = 0, nCon = 0;
	private SizeConnecWindow sc; // Will be used to get the infos/inputs of the user from another window.

	// The list and images variables will be used to access methods from the classes ListPane and ImagesPane.
	// Also, list and images are necessary to trigger/call their windows' elements. 
	private ListPane list, listSearch;
	private ImagesPane images; 
	private Connector c;
	private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SearchWindow.class.getName());

	// Same as in the other classes; we declare these variables to work with de sql database.
	Connection conec = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public SearchWindow() {
		initComponents();
		
		// Here we are connecting our "bridge" between our database and java classes.
		conec = DataAccessLayer.connector();

		// The window where the image will showup belongs inside a main window, our SearchWindow. 
		images = new ImagesPane();
		images.setVisible(true);
		desktop.add(images);

		// Just like above, when SearchWindow runs, these internal windows will be created. An internal Jframe. 
		list = new ListPane(images);
		list.setVisible(true);
		desktopList.add(list);

		// "(this)" is necessary, because the constructor of SizeConnecWindow requires a SearchWindow parameter.
		sc = new SizeConnecWindow(this);

		SearchFilter filterWindow = new SearchFilter(this);
		filterWindow.setVisible(true);
		// lambdas with the same job as the actionslisteners. I made them so each time we use a checkbox, the list updates. 
		jCheckBoxMenuItem1.addActionListener(e -> receiveFilter());
		menFilCaCa.addActionListener(e -> receiveFilter());
		menFilCoCo.addActionListener(e -> receiveFilter());
		menFilCaCo.addActionListener(e -> receiveFilter());
		menFilEqu.addActionListener(e -> receiveFilter());
		menFilAer.addActionListener(e -> receiveFilter());
	}	
	
	// A method that will be called each time the checkboxes are triggered.
	public void receiveFilter(){
		argola = jCheckBoxMenuItem1.isSelected(); 
		cabo = menFilCaCa.isSelected();
		conduite = menFilCoCo.isSelected();
		caboConduite = menFilCaCo.isSelected();
		equipamento = menFilEqu.isSelected();
		aereo = menFilAer.isSelected();
		updateFilteredList();
	}
	
	// Just like above, this method is part of the filter. 
	public void receiveFilterSize(int sizeEl, int nConnect){
		this.size = sizeEl;
		this.nCon = nConnect;
		updateFilteredList();
	}


	// This one verifies and matches the infos of the filter and the table "images" in the database.

	public void updateFilteredList() {
		List<Connector> connectors = new ArrayList<>();

		// Different from the other classes, we're using StringBuilder. 
		// It is because String is immutable, but not a StringBuilder. This allow a more efficient code due to the ifs.
		StringBuilder sql = new StringBuilder("SELECT * FROM images WHERE TRUE=TRUE");
		 
		if (nCon > 0) sql.append(" AND numero_conexoes = ").append(nCon);
		if (size > 0) sql.append(" AND tamanho = ").append(size);
		if (argola) sql.append(" AND argola = TRUE");
		if (cabo) sql.append(" AND cabo = TRUE");
		if (conduite) sql.append(" AND conduite = TRUE");
		if (caboConduite) sql.append(" AND cabo_conduite = TRUE");
		if (equipamento) sql.append(" AND equipamento = TRUE");
		if (aereo) sql.append(" AND aereo = TRUE");

		try {
			pst = conec.prepareStatement(sql.toString()); // .toString() because the prepareStatement requires a string.  
			rs = pst.executeQuery();

			// If there are matches.
			while (rs.next()) {
				Connector conn = new Connector(
						rs.getString("image_path"),
						rs.getInt("numero_conexoes"),
						rs.getBoolean("argola"),
						rs.getBoolean("cabo"),
						rs.getBoolean("conduite"),
						rs.getBoolean("cabo_conduite"),
						rs.getBoolean("equipamento"),
						rs.getBoolean("aereo"),
						rs.getInt("tamanho"),
						rs.getString("nameId")
						);
				connectors.add(conn);
				System.out.println("Image path: " + rs.getString("image_path"));

			}

			list.updateList(connectors, images); // Update the list with the matches.
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar elementos: " + e.getMessage());
		}
	}


	public void searchEl(String nameId){
		String sql = "SELECT * FROM images WHERE nameId=?";
		List<Connector> connectorSearch = new ArrayList<>();
		try {
			pst = conec.prepareStatement(sql);
			pst.setString(1, nameId);
			rs = pst.executeQuery();

			if (rs.next()) {
				Connector c = new Connector(
						rs.getString("image_path"),
						rs.getInt("numero_conexoes"),
						rs.getBoolean("argola"),
						rs.getBoolean("cabo"),
						rs.getBoolean("conduite"),
						rs.getBoolean("cabo_conduite"),
						rs.getBoolean("equipamento"),
						rs.getBoolean("aereo"),
						rs.getInt("tamanho"),
						rs.getString("nameId")
						);
				connectorSearch.add(c);
				System.out.println("Image path: " + rs.getString("image_path"));
			} else {
				System.out.println("No result found.");
			}

			list.updateList(connectorSearch, images);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}


	// The GUI your about to see was created with NetBeans. Here, what I coded are the actionListeners. Not lambdas because the structure was already partially done. In the next update of the code, I may code it with lambdas.
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	// <editor-fold defaultstate="collapsed" desc="Generated Code">                          

	private void initComponents() {

	jScrollPane1 = new javax.swing.JScrollPane();
	desktop = new javax.swing.JDesktopPane();
	desktopList = new javax.swing.JDesktopPane();
	lblId = new javax.swing.JLabel();
	lblProject = new javax.swing.JLabel();
	btnAddEl = new javax.swing.JButton();
	lblBy1 = new javax.swing.JLabel();
	jMenuBar1 = new javax.swing.JMenuBar();
	menSea = new javax.swing.JMenu();
	menSea2 = new javax.swing.JMenuItem();
	menFilArg = new javax.swing.JMenu();
	jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
	menFilCaCa = new javax.swing.JCheckBoxMenuItem();
	menFilCoCo = new javax.swing.JCheckBoxMenuItem();
	menFilCaCo = new javax.swing.JCheckBoxMenuItem();
	menFilEqu = new javax.swing.JCheckBoxMenuItem();
	menFilAer = new javax.swing.JCheckBoxMenuItem();
	menSizCon = new javax.swing.JMenuItem();
	menAdm = new javax.swing.JMenu();
	menAdmAdd = new javax.swing.JMenuItem();
	menInf = new javax.swing.JMenu();
	menInfAbo = new javax.swing.JMenuItem();
	menOpt = new javax.swing.JMenu();
	menOptExi = new javax.swing.JMenuItem();
	menOptHel = new javax.swing.JMenuItem();

	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	setTitle("Bim Search");

	javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
	desktop.setLayout(desktopLayout);
	desktopLayout.setHorizontalGroup(
			desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 350, Short.MAX_VALUE)
			);
	desktopLayout.setVerticalGroup(
			desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE)
			);

	javax.swing.GroupLayout desktopListLayout = new javax.swing.GroupLayout(desktopList);
	desktopList.setLayout(desktopListLayout);
	desktopListLayout.setHorizontalGroup(
			desktopListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 270, Short.MAX_VALUE)
			);
	desktopListLayout.setVerticalGroup(
			desktopListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 571, Short.MAX_VALUE)
			);

	lblId.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
	lblId.setText("ID:");

	lblProject.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
	lblProject.setText("Project:");

	btnAddEl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bimsearch/windows/images/searchIcons/add.png"))); // NOI18N
	btnAddEl.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
	btnAddEl.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			btnAddElAddActionPerformed(evt);
		}
	});

	lblBy1.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
	lblBy1.setText("Add Element");

	menSea.setText("Search");
	menSea2.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			menSea2ActionPerformed(evt);
		}
		});

		jMenuBar1.add(menSea);
		menSea2.setText("Search Option");
		menSea.add(menSea2);

		menFilArg.setText("Filters");
		menSizCon.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menSizConActionPerformed(evt);
			}
		});


		jCheckBoxMenuItem1.setSelected(true);
		jCheckBoxMenuItem1.setText("Ring");
		menFilArg.add(jCheckBoxMenuItem1);

		menFilCaCa.setSelected(true);
		menFilCaCa.setText("Cable x Cable");
		menFilArg.add(menFilCaCa);

		menFilCoCo.setSelected(true);
		menFilCoCo.setText("Conduit x Conduit");
		menFilArg.add(menFilCoCo);

		menFilCaCo.setSelected(true);
		menFilCaCo.setText("Cable x Conduit");
		menFilArg.add(menFilCaCo);

		menFilEqu.setSelected(true);
		menFilEqu.setText("Equipement");
		menFilArg.add(menFilEqu);

		menFilAer.setSelected(true);
		menFilAer.setText("Overhead");
		menFilArg.add(menFilAer);

		menSizCon.setText(" Size & Connections");
		menFilArg.add(menSizCon);

		jMenuBar1.add(menFilArg);

		menAdm.setText("Admin");
		menAdm.setEnabled(false);

		menAdmAdd.setText("Manage Users");
		menAdmAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menAdmAddActionPerformed(evt);
			}
		});
		menAdm.add(menAdmAdd);

		jMenuBar1.add(menAdm);

		menInf.setText("Info / License");
		menInf.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menInfActionPerformed(evt);
			}
		});

		menInfAbo.setText("About");
		menInfAbo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menInfAboActionPerformed(evt);
			}
		});
		menInf.add(menInfAbo);

		jMenuBar1.add(menInf);

		menOpt.setText("Options");

		menOptExi.setText("Exit");
		menOptExi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menOptExiActionPerformed(evt);
			}
		});
		menOpt.add(menOptExi);

		menOptHel.setText("Help");
		menOpt.add(menOptHel);

		jMenuBar1.add(menOpt);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(desktopList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(55, 55, 55)
							.addComponent(lblBy1)
							.addGap(0, 56, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblId)
								.addComponent(lblProject))
							.addContainerGap(178, Short.MAX_VALUE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnAddEl)
							.addGap(93, 93, 93))))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(desktop)
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(lblId)
							.addGap(58, 58, 58)
							.addComponent(lblProject)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblBy1)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(btnAddEl)
							.addGap(62, 62, 62))
						.addComponent(desktopList))
					.addContainerGap())
				);

		setSize(new java.awt.Dimension(900, 639));
		setLocationRelativeTo(null);
	}// </editor-fold>


	private void menOptExiActionPerformed(java.awt.event.ActionEvent evt) {                                          
		int exit = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?","Yo!", JOptionPane.YES_NO_OPTION);
		if(exit == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}                                         

	// methods which will run when its buttoms are pressed. 
	
	private void menInfActionPerformed(java.awt.event.ActionEvent evt) {                                       
		WindowInfo about = new WindowInfo();
		about.setVisible(true);
	}                                      

	private void menInfAboActionPerformed(java.awt.event.ActionEvent evt) {                                          
		WindowInfo about = new WindowInfo();
		about.setVisible(true);
	}                                         

	private void menAdmAddActionPerformed(java.awt.event.ActionEvent evt) {                                          
		User users = new User();
		users.setVisible(true);
	}                                         
	
	private void btnAddElAddActionPerformed(java.awt.event.ActionEvent evt) {                                          
		AddElement element = new AddElement();
		element.setVisible(true);
	}                                 

	private void menSizConActionPerformed(java.awt.event.ActionEvent evt) {                                          
		SizeConnecWindow sc = new SizeConnecWindow(this);
		sc.setVisible(true);
	}                                         

	private void menSea2ActionPerformed(java.awt.event.ActionEvent evt) {                                       
		SearchFilter searchfilter = new SearchFilter(this);
		searchfilter.setVisible(true);
	}                                      


	// Main created by NetBeans. 
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
			logger.log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> new SearchWindow().setVisible(true));
	}

	// Variables declaration - do not modify                     
	private javax.swing.JButton btnAddEl;
	private javax.swing.JDesktopPane desktop;
	private javax.swing.JDesktopPane desktopList;
	private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblBy1;
	private javax.swing.JLabel lblId;
	private javax.swing.JLabel lblProject;
	public static javax.swing.JMenu menAdm;
	private javax.swing.JMenuItem menAdmAdd;
	private javax.swing.JCheckBoxMenuItem menFilAer;
	private javax.swing.JMenu menFilArg;
	private javax.swing.JCheckBoxMenuItem menFilCaCa;
	private javax.swing.JCheckBoxMenuItem menFilCaCo;
	private javax.swing.JCheckBoxMenuItem menFilCoCo;
	private javax.swing.JCheckBoxMenuItem menFilEqu;
	private javax.swing.JMenu menInf;
	private javax.swing.JMenuItem menInfAbo;
	private javax.swing.JMenu menOpt;
	private javax.swing.JMenuItem menOptExi;
	private javax.swing.JMenuItem menOptHel;
	private javax.swing.JMenu menSea;
	private javax.swing.JMenuItem menSea2;
	private javax.swing.JMenuItem menSizCon;
	// End of variables declaration                   
}



