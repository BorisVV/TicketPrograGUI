package com.BorisV.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

public class TicketProgramGUI extends JFrame {
    private JPanel rootPanel;
    private JComboBox<String> priority;
    private JButton addTicket;
    private JTextField issueTextField1;
    private JTextField reportedTextField2;
    private JList<Ticket> addTicketsJList;
    private JButton deleteSelectedButton;
    private JButton resolvedSelectedButton;
    private JList resolvedList1;
    protected static LinkedList<Ticket> allOpenTickets;
    protected static LinkedList<Ticket> resolvedTickets;

    DefaultListModel ticketsModel;
    DefaultListModel resolvedTicketsModel;


    public TicketProgramGUI() {
        super("Tickets Project");
        setPreferredSize(new Dimension(1000,500));
        setContentPane(rootPanel);
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        acctionPriority();
        configurePriorityButton();

        ticketsModel = new DefaultListModel();
        addTicketsJList.setModel(ticketsModel);
        addTicketsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addTicketsJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        resolvedTicketsModel = new DefaultListModel();
        resolvedList1.setModel(resolvedTicketsModel);
        resolvedList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        deleteTickets();
        resolvedTickets();
    }

    private void configurePriorityButton() {
        addTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String issue = issueTextField1.getText();
                String name = reportedTextField2.getText();
                int priorityInt = priority.getSelectedIndex();
                if (issue.isEmpty() || name.isEmpty() || priorityInt == 0) {
                    JOptionPane.showMessageDialog(TicketProgramGUI.this,"Not all data was entered");
                }
                Ticket listOfTicket = new Ticket(issue, priorityInt, name, (new Date()));
                ticketsModel.addElement(listOfTicket);
                issueTextField1.setText(null);
                reportedTextField2.setText(null);
                priority.setSelectedIndex(0);

            }
        });

    }


    public void acctionPriority() {
        priority.addItem(" ");
        for (int i = 1; i <= 5; i++) {
            priority.addItem("" + i);

        }
    }

    public void deleteTickets(){
        deleteSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Ticket toDelete;
                for (int i = 0; i < ticketsModel.size() ; i++) {
                    toDelete = addTicketsJList.getSelectedValue();
                    if (toDelete == null) {
                        JOptionPane.showMessageDialog(TicketProgramGUI.this, "None selected");
                        return;
                    }
                    ticketsModel.removeElement(toDelete);

                    // toDelete = addTicketsJList.clearSelection();
                    addTicketsJList.clearSelection();
                    break;
                }

            }
        });
    }

    public void resolvedTickets() {
        resolvedSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enterSolution = null;
                Ticket toResolve = null;
                FileWriter writer = null;
                try {
                    writer = new FileWriter("File_Resolved_Tickets.txt");
                    for (int i = 0; i < ticketsModel.size(); i++) {
                        toResolve = addTicketsJList.getSelectedValue();
//                toDelete = addTicketsJList.clearSelection();
                        if (toResolve == null) {
                            JOptionPane.showMessageDialog(TicketProgramGUI.this, "None selected");
                            return;
                        }
                        if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(TicketProgramGUI.this, "Are you sure you want to save and exit?", "Exit?", JOptionPane.OK_CANCEL_OPTION)) {
                            //Save all of the data...
                            resolvedTicketsModel.addElement(toResolve);
                            resolvedTicketsModel.addElement("Solution: " + enterSolution);

                            ticketsModel.removeElement(toResolve);
                            addTicketsJList.clearSelection();
                            ResolvedTickets.saveResolvedTickets(toResolve);
                        }
                        break;
                    }
                } catch (IOException ie) {
                    JOptionPane.showMessageDialog(TicketProgramGUI.this, "File not found");
                }


//                String toResolve = String.valueOf(addTicketsJList.getSelectedValue());
//                String solution = JOptionPane.showInputDialog("Enter solution");




            }
        });
    }

}




