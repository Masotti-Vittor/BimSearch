package br.com.bimsearch.windows;

import br.com.bimsearch.elements.Connector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import br.com.bimsearch.windows.SearchWindow;
@SuppressWarnings("unused")
public class ListPane extends javax.swing.JInternalFrame {

	private List<Connector> elementos;
	private ImagesPane imagesPane;
	private SearchWindow searchWindow; 
	// Since the list has the objective of showing buttons with the matches, its constructor receives a parameter of the type ImagesPane.

	public ListPane(ImagesPane imgPane, SearchWindow searchWindow) {
        this.imagesPane = imgPane; 
        this.searchWindow = searchWindow;
        initComponents();
    }
	
	//This method, called from the SearchWindow class, updates the list with the new matches between the database and filters. 
	//The parameters received are: 
	//A list with elements created in the Connector class. Each element contains its specs and image path.
	//The ImagePane is used to display the image when a button is clicked in the list. 

	// Remove the extra parameters, use the ones stored in "this"
	public void updateList(List<Connector> elementos) {
		this.elementos = elementos;
		sclPanel.removeAll(); 

		for (Connector c : elementos) {
			JButton btn = new JButton(c.getNameId());
			btn.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			btn.addActionListener(e -> {
				// Use the class-level variables (this.searchWindow and this.imagesPane)
				this.imagesPane.showImage(c.getImagePath());
				searchWindow.lblId.setText("ID: " + c.getNameId());
				searchWindow.lblProject.setText("Project: " + c.getProject());
			}); 
			sclPanel.add(btn);
		}

		this.revalidate();
		this.repaint();
	}



	private void initComponents() {

		sclPane = new javax.swing.JScrollPane();
		sclPanel = new javax.swing.JPanel();

		sclPanel.setLayout(new BoxLayout(sclPanel, BoxLayout.Y_AXIS));

		setMinimumSize(new java.awt.Dimension(270, 564));
		setPreferredSize(new java.awt.Dimension(270, 564));

		sclPane.setViewportView(sclPanel);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(sclPane)
					.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(sclPane)
				);

		pack();
	}

	// Variables declaration - do not modify                     
	private javax.swing.JScrollPane sclPane;
	private javax.swing.JPanel sclPanel;
	// End of variables declaration                   
}

