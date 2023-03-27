import javax.imageio.IIOException;
import javax.swing.*;                   // Import the Java Swing Package to Create GUI Application
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;      // Import Action Event Class to perform actions
import java.awt.event.ActionListener;   // Import Action Listener Class to Listen the Action
import java.io.*;                       // Import java Input Output Package
import java.lang.*;                     // Import Java Language package

public class TextEditior implements ActionListener {
    JFrame frame;                               // Declaring the frame of Text Editior
    JMenuBar menubar;                           // Declaring a menubar object
    JMenu file,edit;                            // Declaring menu Buttons
    JMenuItem newfile,open,save;                // Declaring file Menu Items
    JMenuItem cut,copy,paste,selectAll,close;   // Declaring Edit menu Items
    JTextArea textArea;                         // Declaring Text Area for Editior
    TextEditior(){
        frame = new JFrame();                           // Declaring the Fram for Editior

        menubar = new JMenuBar();                       // Creating a menubar

        frame.setTitle("Text Editior");                 // Creating a Icon Image Class
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ANKITMAURYA\\IdeaProjects\\TEXT_EDITIOR\\icon.png");
        frame.setIconImage(icon);                       // Set the Icon Into Frame
        
        textArea = new JTextArea();                     // Creating a text Area
        frame.add(textArea);                            // adding Texatarea into frame

        file = new JMenu("File");                     // Addings File menu into Menubar
        edit = new JMenu("Edit");                     // Adding Edit Menu inot Menubar

        newfile = new JMenuItem("New");             // Adding items into File Menu  'New'
        open = new JMenuItem("Open");               // Adding 'Open' Menuitem
        save = new JMenuItem("Save");               // Adding 'Save' MenuItem

        cut = new JMenuItem("Cut");                 // Adding a Cut option in Edit Menu
        copy = new JMenuItem("Copy");               // Adding a Copy Option in Edit Menu
        paste = new JMenuItem("Paste");             // Adding a Paste Option in Edit Menu
        selectAll = new JMenuItem("Select All");    // Adding a selectAll option in Edit Menu
        close= new JMenuItem("Close");

        newfile.addActionListener(this);    // this keyword is used to make newfile button to actionListner
        open.addActionListener(this);       // make open button to a actionListner
        save.addActionListener(this);       // make save button to a actionListener

        file.add(newfile);      // Adding newFile option into File Menu
        file.add(open);         // Adding open option in File Menu
        file.add(save);         // Adding save opion in File Menu

        cut.addActionListener(this);            // make cut button to a actionListner
        copy.addActionListener(this);           // make copy button to a actionListner
        paste.addActionListener(this);          // make paste button to a actionListner
        selectAll.addActionListener(this);      // make selectall button to a actionListner
        close.addActionListener(this);          // make close button to a actionListner

        edit.add(cut);              // Adding cut option into Edit Menu
        edit.add(copy);             // Adding copy option into Edit Menu
        edit.add(paste);            // Adding paste option into Edit Menu
        edit.add(selectAll);        // Adding selectAll option into Edit Menu
        edit.add(close);            // Adding close option into Edit Menu

        menubar.add(file);      // Adding File menuItem into Menubar
        menubar.add(edit);      // Adding Edit menuItem into Menubar

        frame.setJMenuBar(menubar);  // Adding Menubar into Frame

        frame.setBounds(300,150,600,600);  // Define the Width x Height of Frame

        JPanel panel = new JPanel();                    // Create a panel to cover the TextArea
        panel.setBorder(new EmptyBorder(5,5,10,10));    // Set the panel Border
        panel.setLayout(new BorderLayout(0,0));        //Set the Border Layout

        panel.add(textArea,BorderLayout.CENTER);        // Set the TextArea Position

        //Creating a Vertical & Horizental Scroll bar as per Need
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);         //Add the Scroll Bar into panel
        frame.add(panel);               // Add the panel into Frame.
        
                                     // Give the margin to frame from x or y axis
        frame.setVisible(true);     // to make frame visible
        frame.setLayout(null);      //set the layout to null
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){           // When Any Action Performed
        if(actionEvent.getSource()==cut) textArea.cut();            // Calling the Inbuilt Cut Function
        if(actionEvent.getSource()==copy) textArea.copy();          // Calling the Inbuilt Copy Function
        if(actionEvent.getSource()==paste) textArea.paste();        // Calling the Inbuilt Paste Function
        if(actionEvent.getSource()==selectAll) textArea.selectAll();// Calling the Inbuilt Select Function
        if(actionEvent.getSource()==close) System.exit(0);    // Close the Console
        if(actionEvent.getSource()==newfile) {                      // Make a new Object ot TextEditior Class;
            TextEditior newtextEditior = new TextEditior();
        }
        if(actionEvent.getSource()==open){                                      // When open Button Trigger
            JFileChooser fileChooser = new JFileChooser("c:");  // open Filechooser in C Directory
            int choose = fileChooser.showOpenDialog(null);               // make a varibale to to store state of open Dialog box

            if(choose==JFileChooser.APPROVE_OPTION){                            // if choose option is true(user didn't click the cancel button)
                File currFile = fileChooser.getSelectedFile();                  // Select the File
                String path = currFile.getPath();                               // Store the fila path
                try{
                    FileReader fileReader= new FileReader(path);                    // Make a new Filereader Object to read the contant of existing fil content
                    BufferedReader bufferedReader = new BufferedReader(fileReader); // Initialize a BufferReader to read File Line by Line

                    String line = "";               // Make Variable to Store the Line of File
                    String mainString=" ";          // Make main String variable to store complete content of File

                    while((line=bufferedReader.readLine())!=null){      // To read the File
                        mainString+=line+"\n";                          // Add content line by Line into main String
                    }
                    textArea.setText(mainString);                       // In final Show the text in textarea
                } catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();             // Catch the IOException
                }
            }
        }
        if(actionEvent.getSource()==save){
            JFileChooser fileChooser =new JFileChooser("c:"); // Creating a File Chooser Object to choose the file location
            int choose = fileChooser.showSaveDialog(null);              // Creating a Variable to store state of save Dialog Box

            if(choose==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");   //  Create a New File with .txt extension
                try{
                    FileWriter fileWriter = new FileWriter(file);

                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);    // Initialize the bufferWriter

                    textArea.write(bufferedWriter);                             // Write the complete content on that file
                    bufferedWriter.close();                                     // Close the BufferWriter
                }
                catch (IOException ioException){        //Handle the IO Exception
                    ioException.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TextEditior textEditior = new TextEditior();   // Creating Object of TextEditior Class
    }
}
