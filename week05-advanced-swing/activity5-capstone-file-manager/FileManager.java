import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class FileManager {

    private JFrame frame;
    private JTree folderTree;
    private JTable fileTable;
    private DefaultTableModel tableModel;
    private JLabel statusBar;
    private File currentFolder;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            () -> new FileManager().createAndShow()
        );
    }

    private void createAndShow() {

        frame = new JFrame("File Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        // 1. MENU BAR
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem refreshItem = new JMenuItem("Refresh");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(refreshItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);

        // 2. TOOLBAR
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

        JButton refreshBtn = new JButton("Refresh");
        JButton upBtn = new JButton("Up Folder");
        JButton homeBtn = new JButton("Home");

        toolbar.add(homeBtn);
        toolbar.add(upBtn);
        toolbar.addSeparator();
        toolbar.add(refreshBtn);

        frame.add(toolbar, BorderLayout.NORTH);

        // 3. TREE - LEFT SIDE
        File home = new File(System.getProperty("user.home"));

        DefaultMutableTreeNode root = buildFolderTree(home);

        folderTree = new JTree(new DefaultTreeModel(root));

        folderTree.setFont(new Font("Arial", Font.PLAIN, 14));
        folderTree.setRowHeight(22);
        folderTree.expandRow(0);

        folderTree.addTreeSelectionListener(e -> {

            DefaultMutableTreeNode selected =
                (DefaultMutableTreeNode)
                folderTree.getLastSelectedPathComponent();

            if (selected != null &&
                selected.getUserObject() instanceof File) {

                File folder = (File) selected.getUserObject();

                if (folder.isDirectory()) {
                    loadFolderContents(folder);
                }
            }
        });

        // 4. TABLE - RIGHT SIDE
        String[] columns = {
            "Name", "Type", "Size", "Modified"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        fileTable = new JTable(tableModel);

        fileTable.setRowHeight(22);
        fileTable.setFont(new Font("Arial", Font.PLAIN, 13));

        fileTable.getTableHeader().setFont(
            new Font("Arial", Font.BOLD, 13)
        );

        // SPLIT PANE
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            new JScrollPane(folderTree),
            new JScrollPane(fileTable)
        );

        splitPane.setDividerLocation(280);

        frame.add(splitPane, BorderLayout.CENTER);

        // 5. STATUS BAR
        statusBar = new JLabel("Ready");

        statusBar.setBorder(
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );

        frame.add(statusBar, BorderLayout.SOUTH);

        // 6. EVENT HANDLERS

        // Load home directory initially
        loadFolderContents(home);

        // HOME BUTTON
        homeBtn.addActionListener(e -> {

            File homeFolder =
                new File(System.getProperty("user.home"));

            loadFolderContents(homeFolder);
        });

        // UP FOLDER BUTTON
        upBtn.addActionListener(e -> {

            if (currentFolder != null &&
                currentFolder.getParentFile() != null) {

                loadFolderContents(
                    currentFolder.getParentFile()
                );
            }
        });

        // REFRESH BUTTON
        refreshBtn.addActionListener(e -> {

            if (currentFolder != null) {
                loadFolderContents(currentFolder);
            }
        });

        refreshItem.addActionListener(e -> refreshBtn.doClick());

        // EXIT
        exitItem.addActionListener(e -> System.exit(0));

        // ABOUT
        aboutItem.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                frame,
                "File Manager v1.0\nBuilt with Swing\nWeek 5 Capstone Project",
                "About",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        // DOUBLE-CLICK FILE OR FOLDER
        fileTable.addMouseListener(
            new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(
                    java.awt.event.MouseEvent e
                ) {

                    if (e.getClickCount() == 2) {

                        int row = fileTable.getSelectedRow();

                        if (row >= 0) {

                            String fileName =
                                (String) tableModel.getValueAt(row, 0);

                            File file =
                                new File(currentFolder, fileName);

                            if (file.isDirectory()) {

                                loadFolderContents(file);

                            } else {

                                JOptionPane.showMessageDialog(
                                    frame,
                                    "Selected file:\n"
                                    + file.getAbsolutePath(),
                                    "File Selected",
                                    JOptionPane.INFORMATION_MESSAGE
                                );
                            }
                        }
                    }
                }
            }
        );

        frame.setVisible(true);
    }

    // BUILD FOLDER TREE
    private DefaultMutableTreeNode buildFolderTree(File folder) {

        DefaultMutableTreeNode node =
            new DefaultMutableTreeNode(folder);

        File[] children = folder.listFiles();

        if (children != null) {

            for (File child : children) {

                if (child.isDirectory() && !child.isHidden()) {

                    node.add(buildFolderTree(child));
                }
            }
        }

        return node;
    }

    // LOAD FOLDER CONTENTS INTO TABLE
    private void loadFolderContents(File folder) {

        if (!folder.isDirectory()) {
            return;
        }

        currentFolder = folder;

        tableModel.setRowCount(0);

        File[] files = folder.listFiles();

        if (files != null) {

            SimpleDateFormat sdf =
                new SimpleDateFormat("dd MMM yyyy");

            for (File file : files) {

                String type = file.isDirectory()
                    ? "Folder"
                    : getExtension(file.getName());

                String size = file.isDirectory()
                    ? "-"
                    : formatSize(file.length());

                String modified =
                    sdf.format(file.lastModified());

                tableModel.addRow(new Object[] {
                    file.getName(),
                    type,
                    size,
                    modified
                });
            }
        }

        frame.setTitle(
            "File Manager - " + folder.getAbsolutePath()
        );

        statusBar.setText(
            files != null
                ? files.length + " items in " + folder.getName()
                : "Could not read folder"
        );
    }

    // GET FILE EXTENSION
    private String getExtension(String name) {

        int dot = name.lastIndexOf('.');

        return dot >= 0
            ? name.substring(dot + 1).toUpperCase()
            : "File";
    }

    // FORMAT FILE SIZE
    private String formatSize(long bytes) {

        if (bytes < 1024) {
            return bytes + " B";
        }

        if (bytes < 1024 * 1024) {
            return String.format("%.1f KB", bytes / 1024.0);
        }

        return String.format(
            "%.1f MB",
            bytes / (1024.0 * 1024.0)
        );
    }
}
