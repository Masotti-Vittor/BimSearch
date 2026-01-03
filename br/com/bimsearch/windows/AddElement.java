package br.com.bimsearch.windows;
import java.sql.*;
import br.com.bimsearch.dal.DataAccessLayer;
import javax.swing.TransferHandler;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author wasotty
 */
public class AddElement extends javax.swing.JFrame {

	private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddElement.class.getName());
	private File selectedFile;

	Connection conec = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@SuppressWarnings("unused")
	private String currentImagePath = null;

	public AddElement() {
		initComponents();
		setupDragAndDrop();
		conec = DataAccessLayer.connector();
	}


	// Here, the images and file handling were coded with the support of similar programs, youtube videos and AI. I kept it pretty self explaining, but, if you're a beginner like me, I will summarize some lines.
	
	//This method makes the tool "Drag and Drop Image" functional. Surely you've seen this kind of window in some websites. 
	// Many of the methods called here are from libraries focused on working with images and files. Thus, you can look which ones I imported at the start of this file. 
	
	private void setupDragAndDrop() {
		// Now pnlDrag accepts dragged files.
		pnlDrag.setTransferHandler(new TransferHandler() {
			@SuppressWarnings("unchecked")
			@Override
			// If something os dragged to it, this method will be called.
			public boolean importData(TransferHandler.TransferSupport support) {
				try {
					List<File> files = (List<File>) support.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
					// Gets only the first file from the received list.
					File imgFile = files.get(0);
					selectedFile = imgFile;

					// Updates de panel.
					ImageIcon icon = new ImageIcon();
					JLabel label = new JLabel(icon);
					pnlDrag.removeAll();
					pnlDrag.add(label);
					pnlDrag.revalidate();
					pnlDrag.repaint();

					return true;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao importar imagem: " + e.getMessage());
					return false;
				}
			}
			public boolean canImport(TransferHandler.TransferSupport support) {
				return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
			}

		});
	}

	// This one is direct. It sends the given file to the path showed here. 
	// If you desire to change the path where each image will be saved in, please change the 2 lines with the current path.
	private String copyToProjectFolder(File originalFile) throws IOException {
		String relativePath = "src/br/com/bimsearch/windows/images/";
		File directory = new File(relativePath);


		if (!directory.exists()) {
			directory.mkdirs();
		}

		String fileName = System.currentTimeMillis() + "_" + originalFile.getName();
		File destinationFile = new File(directory, fileName);

		Files.copy(originalFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

		return "src/br/com/bimsearch/windows/images/" + fileName;
	}

	// This method will be called when button is clicked. With it, the specs of each element, with the path where its image is saved, will be given and added to the database. 

	private void add(){

		String sql = "insert into images(image_path, numero_conexoes, argola, cabo, conduite, cabo_conduite, equipamento, aereo, tamanho, nameId) VALUES (?,?,?,?,?,?,?,?,?,?)";

		try{
			pst = conec.prepareStatement(sql);
			if (selectedFile != null) {
				String newPath = copyToProjectFolder(selectedFile);
				currentImagePath = newPath;
				pst.setString(1, newPath);
			} else {
				JOptionPane.showMessageDialog(null, "There is no image selected.");
				return; 
			}	

			pst.setString(2, txtNconnections.getText());
			pst.setBoolean(3, rdbArg.isSelected());	
			pst.setBoolean(4, jRadioButton2.isSelected());
			pst.setBoolean(5, jRadioButton3.isSelected());
			pst.setBoolean(6, jRadioButton4.isSelected());
			pst.setBoolean(7, jRadioButton5.isSelected());
			pst.setBoolean(8, jRadioButton6.isSelected());
			pst.setString(9, txtSize.getText());
			pst.setString(10, txtNameElement.getText());
			// Create a column with the project where the element was created. (later).
			// I'll need a second path for a second image if desired. I'd need to change how many files it can hold, probably. 

			if(txtNconnections.getText() != null && txtNameElement.getText() != null && txtSize.getText() != null){
				pst.executeUpdate();

				txtNconnections.setText(null);
				txtNameElement.setText(null);
				txtSize.setText(null);

				rdbArg.setSelected(false);
				jRadioButton2.setSelected(false);
				jRadioButton3.setSelected(false);
				jRadioButton4.setSelected(false);
				jRadioButton5.setSelected(false);
				jRadioButton6.setSelected(false);

				selectedFile = null;

			}
			else{
				JOptionPane.showMessageDialog(null, "Fill every 'obrigatorio' field");
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);

		}

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	
	// <editor-fold defaultstate="collapsed" desc="Generated Code">                        
	private void initComponents() {

		lblNameElement = new javax.swing.JLabel();
		lblProject = new javax.swing.JLabel();
		rdbArg = new javax.swing.JRadioButton();
		jRadioButton2 = new javax.swing.JRadioButton();
		jRadioButton3 = new javax.swing.JRadioButton();
		jRadioButton4 = new javax.swing.JRadioButton();
		jRadioButton5 = new javax.swing.JRadioButton();
		jRadioButton6 = new javax.swing.JRadioButton();
		lblFilter = new javax.swing.JLabel();
		txtNconnections = new javax.swing.JTextField();
		txtSize = new javax.swing.JTextField();
		lblConnections = new javax.swing.JLabel();
		lblSize = new javax.swing.JLabel();
		txtNameElement = new javax.swing.JTextField();
		cmbProject = new javax.swing.JComboBox<>();
		pnlDrag = new javax.swing.JPanel();
		lblDragHere = new javax.swing.JLabel();
		btnCancel = new javax.swing.JButton();
		btnOk = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(new java.awt.Dimension(640, 450));

		lblNameElement.setText("Name:");

		lblProject.setText("Project:");

		rdbArg.setText("Ring");
		rdbArg.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rdbArgActionPerformed(evt);
			}
		});

		jRadioButton2.setText("Cable X Cable");

		jRadioButton3.setText("Conduit X Conduit");

		jRadioButton4.setText("Cable X Conduit");

		jRadioButton5.setText("Equipement");

		jRadioButton6.setText("Overhead");

		lblFilter.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
		lblFilter.setText("Filter");

		lblConnections.setText("Nº Connections:");

		lblSize.setText("Size:");

		cmbProject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		cmbProject.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbProjectActionPerformed(evt);
			}
		});

		pnlDrag.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		lblDragHere.setText("Drag the images here.");
		lblDragHere.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

		javax.swing.GroupLayout pnlDragLayout = new javax.swing.GroupLayout(pnlDrag);
		pnlDrag.setLayout(pnlDragLayout);
		pnlDragLayout.setHorizontalGroup(
				pnlDragLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlDragLayout.createSequentialGroup()
					.addGap(80, 80, 80)
					.addComponent(lblDragHere)
					.addContainerGap(86, Short.MAX_VALUE))
				);
		pnlDragLayout.setVerticalGroup(
				pnlDragLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlDragLayout.createSequentialGroup()
					.addGap(78, 78, 78)
					.addComponent(lblDragHere)
					.addContainerGap(84, Short.MAX_VALUE))
				);

		btnCancel.setText("Cancel");
		btnCancel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelActionPerformed(evt);
			}
		});

		btnOk.setText("Ok");
		btnOk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnOk.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnOkActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(30, 30, 30)
							.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(lblProject)
										.addComponent(lblNameElement))
									.addGap(18, 18, 18)
									.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(txtNameElement)
										.addComponent(cmbProject, 0, 255, Short.MAX_VALUE)))
								.addComponent(pnlDrag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(layout.createSequentialGroup()
							.addGap(112, 112, 112)
							.addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addGap(26, 26, 26)
							.addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
					.addGap(59, 59, 59)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jRadioButton6)
						.addComponent(jRadioButton5)
						.addComponent(jRadioButton4)
						.addComponent(jRadioButton3)
						.addComponent(rdbArg)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
							.addComponent(lblFilter)
							.addComponent(jRadioButton2))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblConnections)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(txtNconnections, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblSize)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(28, Short.MAX_VALUE))
					);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(23, 23, 23)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblNameElement)
						.addComponent(lblFilter)
						.addComponent(txtNameElement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblProject)
						.addComponent(rdbArg)
						.addComponent(cmbProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addComponent(jRadioButton2)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(jRadioButton3)
							.addGap(18, 18, 18)
							.addComponent(jRadioButton4)
							.addGap(18, 18, 18)
							.addComponent(jRadioButton5)
							.addGap(18, 18, 18)
							.addComponent(jRadioButton6)
							.addGap(18, 18, 18)
							.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtNconnections, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblConnections))
							.addGap(18, 18, 18)
							.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSize)))
						.addGroup(layout.createSequentialGroup()
							.addComponent(pnlDrag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addGap(18, 18, 18)
							.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnCancel)
								.addComponent(btnOk))))
								.addContainerGap(59, Short.MAX_VALUE))
								);

		setSize(new java.awt.Dimension(660, 471));
		setLocationRelativeTo(null);
	}// </editor-fold>

	private void cmbProjectActionPerformed(java.awt.event.ActionEvent evt) {                                           
		// TODO add your handling code here:
	}                                          

	private void rdbArgActionPerformed(java.awt.event.ActionEvent evt) {                                       
		// TODO add your handling code here:
	}                                      

	private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {                                      
		add();
	}                                     

	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {                                          
		dispose();
	}                                         

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
		java.awt.EventQueue.invokeLater(() -> new AddElement().setVisible(true));
	}

	// Variables declaration - do not modify                     
	private javax.swing.JButton btnCancel;
	private javax.swing.JButton btnOk;
	private javax.swing.JComboBox<String> cmbProject;
	private javax.swing.JRadioButton jRadioButton2;
	private javax.swing.JRadioButton jRadioButton3;
	private javax.swing.JRadioButton jRadioButton4;
	private javax.swing.JRadioButton jRadioButton5;
	private javax.swing.JRadioButton jRadioButton6;
	private javax.swing.JLabel lblConnections;
	private javax.swing.JLabel lblDragHere;
	private javax.swing.JLabel lblFilter;
	private javax.swing.JLabel lblNameElement;
	private javax.swing.JLabel lblProject;
	private javax.swing.JLabel lblSize;
	private javax.swing.JPanel pnlDrag;
	private javax.swing.JRadioButton rdbArg;
	private javax.swing.JTextField txtNameElement;
	private javax.swing.JTextField txtNconnections;
	private javax.swing.JTextField txtSize;
	// End of variables declaration                   
}

