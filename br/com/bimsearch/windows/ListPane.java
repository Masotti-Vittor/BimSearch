package br.com.bimsearch.windows;

import br.com.bimsearch.elements.Connector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ListPane extends javax.swing.JInternalFrame {

	private List<Connector> elementos;
	private ImagesPane imagesPane; 

	// Since the list has the objective of showing buttons with the matches, its constructor reveives a parameter of the type ImagesPane.
	public ListPane(ImagesPane imagesPane) {
		this.imagesPane = imagesPane;
		initComponents();
	}
	
	//This method, called from the SearchWindow class, updates the list with the new matches between the database and filters. 
	//The parameters received are: 
	//A list with elements created in the Connector class. Each element contains its specs and image path.
	//The ImagePane is used to display the image when a button is clicked in the list. 

	public void updateList(List<Connector> elementos, ImagesPane imagesPane) {
		this.elementos = elementos;
		sclPanel.removeAll(); 

		// for each element in his list, create a button that, when clicked, shows the corresponding image.
		for (Connector c : elementos) {
			JButton btn = new JButton(c.getNameId());
			btn.setAlignmentX(Component.CENTER_ALIGNMENT);
			btn.addActionListener(e -> {
				imagesPane.showImage(c.getImagePath());
			}); 
			sclPanel.add(btn);
		}

		this.revalidate();
		this.repaint();
	}


	@SuppressWarnings("unchecked")
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

