import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField maxBoundField;
	private JTextField minBoundField;
	private JTextField enterGradeField;
	private JTextField deleteGradeField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		JTextArea textArea = new JTextArea();
		DataSet dataSet = new DataSet();
		setTitle("Grade Analytics");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		JPanel settingsPanel = new JPanel();
		tabbedPane.addTab("Settings", null, settingsPanel, null);
		settingsPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel buttonsPanel = new JPanel();
		settingsPanel.add(buttonsPanel);
		GridBagLayout gbl_buttonsPanel = new GridBagLayout();
		gbl_buttonsPanel.columnWidths = new int[]{0, 0, 0, 41, 0, 0, 0};
		gbl_buttonsPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_buttonsPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_buttonsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		buttonsPanel.setLayout(gbl_buttonsPanel);
		
		JLabel lblSetDatasetBoundaries = new JLabel("Enter Dataset Boundaries:");
		GridBagConstraints gbc_lblSetDatasetBoundaries = new GridBagConstraints();
		gbc_lblSetDatasetBoundaries.gridwidth = 5;
		gbc_lblSetDatasetBoundaries.insets = new Insets(0, 0, 5, 5);
		gbc_lblSetDatasetBoundaries.gridx = 0;
		gbc_lblSetDatasetBoundaries.gridy = 0;
		buttonsPanel.add(lblSetDatasetBoundaries, gbc_lblSetDatasetBoundaries);
		
		JLabel lblMax = new JLabel("MAX:");
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblMax.gridx = 0;
		gbc_lblMax.gridy = 1;
		buttonsPanel.add(lblMax, gbc_lblMax);
		
		maxBoundField = new JTextField();
		GridBagConstraints gbc_maxBoundField = new GridBagConstraints();
		gbc_maxBoundField.fill = GridBagConstraints.BOTH;
		gbc_maxBoundField.insets = new Insets(0, 0, 5, 5);
		gbc_maxBoundField.gridx = 1;
		gbc_maxBoundField.gridy = 1;
		buttonsPanel.add(maxBoundField, gbc_maxBoundField);
		maxBoundField.setColumns(10);
		
		JLabel lblMin = new JLabel("MIN:");
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblMin.gridx = 2;
		gbc_lblMin.gridy = 1;
		buttonsPanel.add(lblMin, gbc_lblMin);
		
		minBoundField = new JTextField();
		GridBagConstraints gbc_minBoundField = new GridBagConstraints();
		gbc_minBoundField.fill = GridBagConstraints.BOTH;
		gbc_minBoundField.insets = new Insets(0, 0, 5, 5);
		gbc_minBoundField.gridx = 3;
		gbc_minBoundField.gridy = 1;
		buttonsPanel.add(minBoundField, gbc_minBoundField);
		minBoundField.setColumns(10);
		
		JButton btnSetBounds = new JButton("SET BOUNDARIES");
		btnSetBounds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String result = dataSet.setBoundaries(minBoundField.getText(), maxBoundField.getText());
				textArea.setText(result);
			}
		});
		GridBagConstraints gbc_btnSetBounds = new GridBagConstraints();
		gbc_btnSetBounds.insets = new Insets(0, 0, 5, 5);
		gbc_btnSetBounds.gridx = 4;
		gbc_btnSetBounds.gridy = 1;
		buttonsPanel.add(btnSetBounds, gbc_btnSetBounds);
		
		JLabel lblEnterDataFrom = new JLabel("Enter Data From Keyboard:");
		GridBagConstraints gbc_lblEnterDataFrom = new GridBagConstraints();
		gbc_lblEnterDataFrom.ipady = 5;
		gbc_lblEnterDataFrom.gridwidth = 5;
		gbc_lblEnterDataFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterDataFrom.gridx = 0;
		gbc_lblEnterDataFrom.gridy = 2;
		buttonsPanel.add(lblEnterDataFrom, gbc_lblEnterDataFrom);
		
		JLabel lblEnterAGrade = new JLabel("Enter a Grade:");
		GridBagConstraints gbc_lblEnterAGrade = new GridBagConstraints();
		gbc_lblEnterAGrade.gridwidth = 3;
		gbc_lblEnterAGrade.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterAGrade.gridx = 0;
		gbc_lblEnterAGrade.gridy = 3;
		buttonsPanel.add(lblEnterAGrade, gbc_lblEnterAGrade);
		
		enterGradeField = new JTextField();
		GridBagConstraints gbc_enterGradeField = new GridBagConstraints();
		gbc_enterGradeField.insets = new Insets(0, 0, 5, 5);
		gbc_enterGradeField.fill = GridBagConstraints.BOTH;
		gbc_enterGradeField.gridx = 1;
		gbc_enterGradeField.gridy = 4;
		buttonsPanel.add(enterGradeField, gbc_enterGradeField);
		enterGradeField.setColumns(10);
		
		JButton btnAddGrade = new JButton("ADD GRADE");
		btnAddGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = dataSet.appendSingleValue(enterGradeField.getText());
				textArea.setText(result);
			}
		});
		GridBagConstraints gbc_btnAddGrade = new GridBagConstraints();
		gbc_btnAddGrade.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddGrade.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddGrade.gridx = 3;
		gbc_btnAddGrade.gridy = 4;
		buttonsPanel.add(btnAddGrade, gbc_btnAddGrade);
		
		JLabel lblDeleteAGrade = new JLabel("Delete a Grade:");
		GridBagConstraints gbc_lblDeleteAGrade = new GridBagConstraints();
		gbc_lblDeleteAGrade.gridwidth = 3;
		gbc_lblDeleteAGrade.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeleteAGrade.gridx = 0;
		gbc_lblDeleteAGrade.gridy = 5;
		buttonsPanel.add(lblDeleteAGrade, gbc_lblDeleteAGrade);
		
		deleteGradeField = new JTextField();
		GridBagConstraints gbc_deleteGradeField = new GridBagConstraints();
		gbc_deleteGradeField.insets = new Insets(0, 0, 5, 5);
		gbc_deleteGradeField.fill = GridBagConstraints.BOTH;
		gbc_deleteGradeField.gridx = 1;
		gbc_deleteGradeField.gridy = 6;
		buttonsPanel.add(deleteGradeField, gbc_deleteGradeField);
		deleteGradeField.setColumns(10);
		
		JButton btnDeleteGrade = new JButton("DELETE GRADE");
		btnDeleteGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = dataSet.deleteGrade(deleteGradeField.getText());
				textArea.setText(result);
			}
		});
		GridBagConstraints gbc_btnDeleteGrade = new GridBagConstraints();
		gbc_btnDeleteGrade.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteGrade.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteGrade.gridx = 3;
		gbc_btnDeleteGrade.gridy = 6;
		buttonsPanel.add(btnDeleteGrade, gbc_btnDeleteGrade);
		
		JPanel loadFilePanel = new JPanel();
		GridBagConstraints gbc_loadFilePanel = new GridBagConstraints();
		gbc_loadFilePanel.gridheight = 2;
		gbc_loadFilePanel.gridwidth = 6;
		gbc_loadFilePanel.fill = GridBagConstraints.BOTH;
		gbc_loadFilePanel.gridx = 0;
		gbc_loadFilePanel.gridy = 7;
		buttonsPanel.add(loadFilePanel, gbc_loadFilePanel);
		GridBagLayout gbl_loadFilePanel = new GridBagLayout();
		gbl_loadFilePanel.columnWidths = new int[]{77, 221, 46, 0, 0, 0, 0, 0};
		gbl_loadFilePanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_loadFilePanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_loadFilePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		loadFilePanel.setLayout(gbl_loadFilePanel);
		
		JLabel lblLoadDataFrom = new JLabel("Load Data From a File:");
		GridBagConstraints gbc_lblLoadDataFrom = new GridBagConstraints();
		gbc_lblLoadDataFrom.insets = new Insets(20, 0, 5, 5);
		gbc_lblLoadDataFrom.gridx = 0;
		gbc_lblLoadDataFrom.gridy = 1;
		loadFilePanel.add(lblLoadDataFrom, gbc_lblLoadDataFrom);
		
		JButton btnLoadFile = new JButton("LOAD FILE");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result;
			    JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "TXT & CSV Files", "txt", "csv");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            result = chooser.getSelectedFile().getAbsolutePath();
			            textArea.setText(result);
			            dataSet.createDataFromFile(result);
			    }
			}
		});
		GridBagConstraints gbc_btnLoadFile = new GridBagConstraints();
		gbc_btnLoadFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoadFile.gridwidth = 6;
		gbc_btnLoadFile.insets = new Insets(20, 15, 5, 5);
		gbc_btnLoadFile.gridx = 1;
		gbc_btnLoadFile.gridy = 1;
		loadFilePanel.add(btnLoadFile, gbc_btnLoadFile);
		
		JButton btnAppendFile = new JButton("APPEND FILE");
		btnAppendFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result;
			    JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "TXT & CSV Files", "txt", "csv");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            result = chooser.getSelectedFile().getAbsolutePath();
			            textArea.setText(result);
			            dataSet.appendDataFromFile(result);
			    }
			}
		});
		GridBagConstraints gbc_btnAppendFile = new GridBagConstraints();
		gbc_btnAppendFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAppendFile.gridwidth = 6;
		gbc_btnAppendFile.insets = new Insets(0, 15, 20, 5);
		gbc_btnAppendFile.gridx = 1;
		gbc_btnAppendFile.gridy = 2;
		loadFilePanel.add(btnAppendFile, gbc_btnAppendFile);
		
		JPanel uaWrapper = new JPanel();
		GridBagConstraints gbc_uaWrapper = new GridBagConstraints();
		gbc_uaWrapper.gridwidth = 7;
		gbc_uaWrapper.fill = GridBagConstraints.BOTH;
		gbc_uaWrapper.gridx = 0;
		gbc_uaWrapper.gridy = 3;
		loadFilePanel.add(uaWrapper, gbc_uaWrapper);
		uaWrapper.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel dataAnalysisPanel = new JPanel();
		uaWrapper.add(dataAnalysisPanel);
		GridBagLayout gbl_dataAnalysisPanel = new GridBagLayout();
		gbl_dataAnalysisPanel.columnWidths = new int[] {0, 0};
		gbl_dataAnalysisPanel.rowHeights = new int[] {0, 0, 0, 0, 30, 30, 30, 0};
		gbl_dataAnalysisPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_dataAnalysisPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		dataAnalysisPanel.setLayout(gbl_dataAnalysisPanel);
		
		JLabel lblDataAnalysisOptions = new JLabel("Data Analysis Options:");
		GridBagConstraints gbc_lblDataAnalysisOptions = new GridBagConstraints();
		gbc_lblDataAnalysisOptions.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataAnalysisOptions.gridx = 0;
		gbc_lblDataAnalysisOptions.gridy = 0;
		dataAnalysisPanel.add(lblDataAnalysisOptions, gbc_lblDataAnalysisOptions);
		
		JButton btnCount = new JButton("COUNT");
		btnCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = "" + dataSet.getDataCount();
				textArea.setText(result);
			}
		});
		GridBagConstraints gbc_btnCount = new GridBagConstraints();
		gbc_btnCount.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCount.insets = new Insets(10, 0, 5, 5);
		gbc_btnCount.gridx = 0;
		gbc_btnCount.gridy = 1;
		dataAnalysisPanel.add(btnCount, gbc_btnCount);
		
		JButton btnMean = new JButton("MEAN");
		btnMean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = dataSet.getMean();
				textArea.setText(result);
			}
		});
		GridBagConstraints gbc_btnMean = new GridBagConstraints();
		gbc_btnMean.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMean.insets = new Insets(10, 0, 5, 0);
		gbc_btnMean.gridx = 1;
		gbc_btnMean.gridy = 1;
		dataAnalysisPanel.add(btnMean, gbc_btnMean);
		
		JButton btnMaxGrade = new JButton("MAX GRADE");
		btnMaxGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = dataSet.getMax();
				textArea.setText(result);
			}
		});
		GridBagConstraints gbc_btnMaxGrade = new GridBagConstraints();
		gbc_btnMaxGrade.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMaxGrade.insets = new Insets(0, 0, 5, 5);
		gbc_btnMaxGrade.gridx = 0;
		gbc_btnMaxGrade.gridy = 2;
		dataAnalysisPanel.add(btnMaxGrade, gbc_btnMaxGrade);
		
		JButton btnMedian = new JButton("MEDIAN\r\n");
		btnMedian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = dataSet.getMedian();
				textArea.setText(result);
			}
		});
		GridBagConstraints gbc_btnMedian = new GridBagConstraints();
		gbc_btnMedian.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMedian.insets = new Insets(0, 0, 5, 0);
		gbc_btnMedian.gridx = 1;
		gbc_btnMedian.gridy = 2;
		dataAnalysisPanel.add(btnMedian, gbc_btnMedian);
		
		JButton btnMinGrade = new JButton("MIN GRADE");
		btnMinGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = dataSet.getMin();
				textArea.setText(result);
			}
		});
		GridBagConstraints gbc_btnMinGrade = new GridBagConstraints();
		gbc_btnMinGrade.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMinGrade.insets = new Insets(0, 0, 5, 5);
		gbc_btnMinGrade.gridx = 0;
		gbc_btnMinGrade.gridy = 3;
		dataAnalysisPanel.add(btnMinGrade, gbc_btnMinGrade);
		
		JButton btnMode = new JButton("MODE\r\n");
		btnMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = dataSet.getMode();
				textArea.setText(result);
			}
		});
		GridBagConstraints gbc_btnMode = new GridBagConstraints();
		gbc_btnMode.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMode.insets = new Insets(0, 0, 5, 0);
		gbc_btnMode.gridx = 1;
		gbc_btnMode.gridy = 3;
		dataAnalysisPanel.add(btnMode, gbc_btnMode);
		
		JPanel utilitiesPanel = new JPanel();
		uaWrapper.add(utilitiesPanel);
		GridBagLayout gbl_utilitiesPanel = new GridBagLayout();
		gbl_utilitiesPanel.columnWidths = new int[] {0, 0, 30, 30, 0};
		gbl_utilitiesPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_utilitiesPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_utilitiesPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		utilitiesPanel.setLayout(gbl_utilitiesPanel);
		
		JLabel lblUtilities = new JLabel("Utilities:");
		GridBagConstraints gbc_lblUtilities = new GridBagConstraints();
		gbc_lblUtilities.anchor = GridBagConstraints.WEST;
		gbc_lblUtilities.insets = new Insets(0, 0, 5, 5);
		gbc_lblUtilities.gridx = 3;
		gbc_lblUtilities.gridy = 0;
		utilitiesPanel.add(lblUtilities, gbc_lblUtilities);
		
		JButton btnGenGraph = new JButton("GENERATE GRAPH");
		btnGenGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				// Implementation for drawing graph to canvas
			}
		});
		GridBagConstraints gbc_btnGenGraph = new GridBagConstraints();
		gbc_btnGenGraph.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGenGraph.insets = new Insets(10, 0, 5, 5);
		gbc_btnGenGraph.gridx = 3;
		gbc_btnGenGraph.gridy = 1;
		utilitiesPanel.add(btnGenGraph, gbc_btnGenGraph);
		
		JButton btnDisplayErrors = new JButton("DISPLAY ERROR LOG");
		btnDisplayErrors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = UtilityFunctions.makeListOfStringsOneString(dataSet.getErrorLog());
				textArea.setText(result);
			}
		});
		GridBagConstraints gbc_btnDisplayErrors = new GridBagConstraints();
		gbc_btnDisplayErrors.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDisplayErrors.insets = new Insets(0, 0, 5, 5);
		gbc_btnDisplayErrors.gridx = 3;
		gbc_btnDisplayErrors.gridy = 2;
		utilitiesPanel.add(btnDisplayErrors, gbc_btnDisplayErrors);
		
		JButton btnGenDist = new JButton("GENERATE DISTRIBUTION");
		btnGenDist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				// Take the list and put it next to the corresponding percent range
			}
		});
		GridBagConstraints gbc_btnGenDist = new GridBagConstraints();
		gbc_btnGenDist.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGenDist.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenDist.gridx = 3;
		gbc_btnGenDist.gridy = 3;
		utilitiesPanel.add(btnGenDist, gbc_btnGenDist);
		
		JButton btnDisplayData = new JButton("DISPLAY DATA");
		btnDisplayData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = dataSet.getDataSetAsString();
				textArea.setText(result);
			}
		});
		GridBagConstraints gbc_btnDisplayData = new GridBagConstraints();
		gbc_btnDisplayData.insets = new Insets(0, 0, 0, 5);
		gbc_btnDisplayData.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDisplayData.gridx = 3;
		gbc_btnDisplayData.gridy = 4;
		utilitiesPanel.add(btnDisplayData, gbc_btnDisplayData);
		
		JScrollPane messagePane = new JScrollPane();
		messagePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		settingsPanel.add(messagePane);
		
		// Text area declared at the top
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		messagePane.setViewportView(textArea);
		
		JPanel graphOutputPanel = new JPanel();
		tabbedPane.addTab("Graph", null, graphOutputPanel, null);
		
		JPanel distributionOutputPanel = new JPanel();
		tabbedPane.addTab("Distribution", null, distributionOutputPanel, null);
	}

}
