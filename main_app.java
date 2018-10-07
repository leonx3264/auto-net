package dhcp_app;

import java.io.IOException;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class main_app extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	//window
	@Override
	public void start(Stage Window1)
	{
		//title
		Window1.setTitle("AutoNet Tool");
		
		//buttons
		Button startbutton = new Button("start");
		startbutton.setOnAction(new start_script());
		Button stopbutton = new Button("stop");
		stopbutton.setOnAction(new kill_script());
		Button options = new Button("options");
		options.setOnAction(new option_message());
		
		//text
		String Notes = "\t    **********************************************************************\n"
				+ "\t            Welcome to this network automation program. This program \n\t       deals maintaining the dhcp server and other services."
				+ "Press start to \n\t             boot up the script and press stop to end the script. Pressing \n\t    options will give you more choices. "
				+ "this area can also be used for notes \n\t"
				+ "    **********************************************************************";
		TextArea textArea = new TextArea();
		textArea.setPrefRowCount(20);            
		textArea.setText(Notes);
		
		//main title
		Text title = new Text();
		title.setText("AutoNet Tool");
		title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		//containers 
		HBox buttons = new HBox(25, startbutton, stopbutton, options);
		buttons.setAlignment(Pos.CENTER);
		VBox vertical = new VBox(25, title, textArea, buttons);
		vertical.setAlignment(Pos.CENTER);
		VBox padding = new VBox();
		padding.setAlignment(Pos.BOTTOM_CENTER);
		VBox toppadding = new VBox();
		toppadding.setAlignment(Pos.TOP_CENTER);
		VBox main = new VBox(25, toppadding, vertical, padding);
		
		//scene
		Scene scene1 = new Scene(main, 600, 400);
		Window1.setScene(scene1);
		Window1.show();
	}
	
	//start button
	class start_script implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event)
		{
			String cmd = "./main_script.py";
			try 
			{
				Runtime.getRuntime().exec(cmd);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	//stop button
	class kill_script implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event)
		{
			String cmd = "pkill -f main_script.py";
			try 
			{
				Runtime.getRuntime().exec(cmd);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	//options button
	class option_message implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event)
		{
			String user_choice;
			user_choice = JOptionPane.showInputDialog("Enter a letter \na - Show dhcp table \nb - Restart dhcp service \nc - Reboot Router \n");
			
			//show dhcp table
			if (user_choice.equals("a"))
			{
				String cmd = "gedit table.txt";
				try 
				{
					Runtime.getRuntime().exec(cmd);
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			//restart dhcp service
			else if (user_choice.equals("b"))
			{
				String cmd = "./restart_dhcp.py";
				try 
				{
					Runtime.getRuntime().exec(cmd);
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Dhcp restarting..");
			}
			
			//restart router
			else if (user_choice.equals("c"))
			{
				String cmd = "./reboot_rt.py";
				try 
				{
					Runtime.getRuntime().exec(cmd);
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "router restarting..");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "error");
			}
				

		}
	}

}
