import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


public class notebook extends JFrame implements ActionListener
{
	public notebook()	
	{
		getContentPane().add(content);
		scrollPane=new JScrollPane(content);
		getContentPane().add(scrollPane,"Center");
		
		for(menu_count=0;menu_count<menu.length;menu_count++)
		{
			nbMenuBar.add(menu[menu_count]);
			menu[menu_count].addActionListener(this);
			while((menu_count==0)&&(menuitem_count<File_Item.length))
			{
				menu[0].add(File_Item[menuitem_count]);
				File_Item[menuitem_count].addActionListener(this);
				menuitem_count++;
			}
			menuitem_count=0;
			while((menu_count==1)&&(menuitem_count<Edit_Item.length))
			{
				menu[1].add(Edit_Item[menuitem_count]);
				Edit_Item[menuitem_count].addActionListener(this);
				menuitem_count++;
			}
			
		}
		
		//Font_Dialog.add
		
		menu[3].add(Abt_Item);
		menu[2].add(Font_Item);
		Abt_Item.addActionListener(this);
		Font_Item.addActionListener(this);
		setJMenuBar(nbMenuBar);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
	

		
	public void actionPerformed(ActionEvent e)
	{
		Object arg=e.getActionCommand();
		if(arg.equals("NoteBook"))
		{
			JOptionPane.showMessageDialog(null,"NoteBook Version 1.0","About NoteBook",JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(arg.equals("New"))
		{
			System.out.println("New");
			content.setVisible(true);

		}
		
		if(arg.equals("Open"))
		{
			System.out.println("Open");
			content.setText(" ");
			try
			{
				open_result=file.showOpenDialog(this);
				if(open_result==JFileChooser.APPROVE_OPTION)
					{
						open_file=new FileReader(file.getSelectedFile().toString());
						buffered_open_file=new BufferedReader(open_file);
						System.out.println(file.getSelectedFile());

						while((data=buffered_open_file.readLine())!=null)
							content_data=content_data+data+" "+"\n";
						content.setVisible(true);
						content.setText(content_data);
						content_data=" ";
						open_file.close();
					}
			}
			
			catch(Exception e1)
			{
				System.out.println(e1.toString());
			}
		}
		
		
		if(arg.equals("Save As"))
		{
			System.out.println("Save As");
			try
			{
				content_data=" ";
				save_result=file.showSaveDialog(this);
				if(save_result==JFileChooser.APPROVE_OPTION)
					{
						//save_file=new File(file.getSelectedFile().toString());
						System.out.println(file.getSelectedFile().toString());
						content_data=content.getText().trim();
						System.out.println(content_data);
					/*	if(save_file.exists())
							{
								overwrite=JOptionPane.showConfirmDialog(this,"File Already Exists!Do you want to overwrite it?","Warning",JOptionPane.WARNING_MESSAGE);
								System.out.println("File Already Exists");
								if(overwrite==JOptionPane.YES_OPTION)
								{
									save_file.delete();*/
									file_oup_save_file=new FileOutputStream(file.getSelectedFile().toString());
									writer_save_file=new PrintStream(file_oup_save_file);
									writer_save_file.println(content_data);
									writer_save_file.close();
									
					/*			}
								
							}*/

							
					}
			}
			
			catch(Exception e2)
			{
				System.out.println(e2.toString());
			}
		}
		
		if(arg.equals("Exit"))System.exit(0);
			
		if(arg.equals("Cut"))content.cut();
		
		if(arg.equals("Paste"))content.paste();
			
		if(arg.equals("Copy"))content.copy();
		
		if(arg.equals("Select All"))content.selectAll();
		
		if(arg.equals("Font Dialog"))
		{
			System.out.println("Font");
		}
	}
	
	public static void main(String args[])
	{
		System.out.println("I will do this");
		JFrame jf=new notebook();
		jf.setTitle("NoteBook");
		jf.setSize(400,400);
		jf.show();
		content.setVisible(false);

	}
	
	private JMenu menu[]={ new JMenu("File"),new JMenu("Edit"),new JMenu("Font"),new JMenu("About")};
	private JMenuItem File_Item[]={new JMenuItem("New"),new JMenuItem("Open"),new JMenuItem("Save"),new JMenuItem("Save As"),new JMenuItem("Backup"),new JMenuItem("Print"),new JMenuItem("Exit")};
	private JMenuItem Edit_Item[]={new JMenuItem("Cut"),new JMenuItem("Copy"),new JMenuItem("Paste"),new JMenuItem("Select All"),new JMenuItem("Search")};
	private JMenuItem Abt_Item=new JMenuItem("NoteBook");
	private JMenuItem Font_Item=new JMenuItem("Font Dialog");
	private int menu_count=0;
	private String key_acc[]={"N","O","S"};
	private static JMenuBar nbMenuBar=new JMenuBar();
	private static JTextArea content=new JTextArea(10,10);
	private JScrollPane scrollPane;
	private int menuitem_count=0;
	private JDialog Font_Dialog;
	private JFileChooser file=new JFileChooser();
	private int open_result=0;
	private int save_result=0;
	private FileReader open_file;
	private BufferedReader buffered_open_file;
	private File save_file;
	private PrintStream writer_save_file;
	private FileOutputStream file_oup_save_file;
	private int overwrite=0;
	private String data="hi ";
	private String content_data=" ";

}	


