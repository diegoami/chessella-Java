/*    */ import ictk.boardgame.chess.net.ics.ICSProtocolHandler;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Font;
/*    */ import java.awt.Frame;
/*    */ import java.awt.TextField;
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.awt.event.WindowAdapter;
/*    */ import java.awt.event.WindowEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandInput
/*    */   extends Frame
/*    */   implements ActionListener
/*    */ {
/*    */   TextField tfCommand;
/*    */   ICSProtocolHandler ics;
/*    */   
/*    */   public CommandInput(String title, ICSProtocolHandler ics)
/*    */   {
/* 41 */     super(title);
/* 42 */     this.ics = ics;
/* 43 */     initComponents();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private void initComponents()
/*    */   {
/* 50 */     this.tfCommand = new TextField();
/* 51 */     this.tfCommand.addActionListener(this);
/*    */     
/* 53 */     addWindowListener(new WindowAdapter() {
/*    */       public void windowClosing(WindowEvent evt) {
/* 55 */         CommandInput.this.ics.disconnect();
/* 56 */         System.exit(0);
/*    */       }
/*    */       
/* 59 */     });
/* 60 */     this.tfCommand.setFont(new Font("Lucida Sans Typewriter", 0, 12));
/* 61 */     this.tfCommand.addActionListener(this);
/*    */     
/* 63 */     add(this.tfCommand, "Center");
/*    */     
/* 65 */     pack();
/* 66 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 67 */     setSize(new Dimension(500, 18));
/*    */     
/* 69 */     setLocation(27, screenSize.height - 60);
/*    */   }
/*    */   
/*    */   public void actionPerformed(ActionEvent evt) {
/* 73 */     this.ics.sendCommand(this.tfCommand.getText());
/* 74 */     this.tfCommand.setText("");
/*    */   }
/*    */ }


/* Location:              D:\projects\chessella\WEB-INF\classes\chessella-classes.jar.zip!\CommandInput.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */