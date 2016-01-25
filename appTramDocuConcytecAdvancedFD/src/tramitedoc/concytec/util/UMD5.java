package tramitedoc.concytec.util;
import java.security.*;
public class UMD5  { 
	  
	   private MessageDigest md = null; 
	   static private UMD5 md5 = null; 
	   private static final char[] hexChars ={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
	  
	   /** 
	    * Constructor is private so you must use the getInstance method 
	    */ 
	   private UMD5() throws NoSuchAlgorithmException 
	   { 
	     md = MessageDigest.getInstance("MD5"); 
	   } 
	  
	  
	   /** 
	   * This returns the singleton instance 
	   */ 
	  public static UMD5 getInstance()throws NoSuchAlgorithmException 
	   { 
	      
	       if (md5 == null) 
	       { 
	         md5 = new UMD5(); 
	  
	     } 
	      
	     return (md5); 
	   } 
	  
	   public String hashData(byte[] dataToHash) 
	  
	 { 
	  
	   return hexStringFromBytes((calculateHash(dataToHash))); 
	   } 
	  
	  
	  
	 private byte[] calculateHash(byte[] dataToHash) 
	  
	 { 
	      md.update(dataToHash, 0, dataToHash.length); 
	  
	     return (md.digest()); 
	  } 
	  
	  
	  
	 public String hexStringFromBytes(byte[] b) 
	  
	 { 
	  
	   String hex = ""; 
	  
	   int msb; 
	  
	   int lsb = 0; 
	     int i; 
	  
	   // MSB maps to idx 0 
	  
	   for (i = 0; i < b.length; i++) 
	  
	   { 
	  
	     msb = ((int)b[i] & 0x000000FF) / 16; 
	  
	     lsb = ((int)b[i] & 0x000000FF) % 16; 
	       hex = hex + hexChars[msb] + hexChars[lsb]; 
	     } 
	     return(hex); 
	   } 
	  
	  
	   /* public static void main(String[] args) 
	     { 
	         try 
	         { 
	     
	        UMD5 md = UMD5.getInstance(); 
	        System.out.println("UMD5.main()");
	        System.out.println("UMD5.main()"+"ABCD".toLowerCase()); 
	        System.out.println("admin="+md.hashData("sgfcadmin".getBytes()).toLowerCase());
	        /*
	        System.out.println("mlamas="+md.hashData("mlamas".getBytes()).toLowerCase());
	        System.out.println("acalagua="+md.hashData("acalagua".getBytes()).toLowerCase());
	        System.out.println("esaavedra="+md.hashData("esaavedra".getBytes()).toLowerCase());
	        System.out.println("gchapelle="+md.hashData("gchapelle".getBytes()).toLowerCase());
	        System.out.println("gmarcello="+md.hashData("gmarcello".getBytes()).toLowerCase());
	        System.out.println("mfernandez="+md.hashData("mfernandez".getBytes()).toLowerCase());
	        System.out.println("vquispe="+md.hashData("vquispe".getBytes()).toLowerCase());
	        System.out.println("oramirez="+md.hashData("oramirez".getBytes()).toLowerCase());
	        System.out.println("rconcha="+md.hashData("rconcha".getBytes()).toLowerCase());
	        System.out.println("ealvarado="+md.hashData("ealvarado".getBytes()).toLowerCase());
	        System.out.println("amontalvo="+md.hashData("amontalvo".getBytes()).toLowerCase());
	        System.out.println("dlino="+md.hashData("dlino".getBytes()).toLowerCase());
	        System.out.println("mcarranza="+md.hashData("mcarranza".getBytes()).toLowerCase());
	        System.out.println("rcandia="+md.hashData("rcandia".getBytes()).toLowerCase());
	        System.out.println("ecarpio="+md.hashData("ecarpio".getBytes()).toLowerCase());
	        System.out.println("dferreyra="+md.hashData("dferreyra".getBytes()).toLowerCase());
	        System.out.println("iparraviccini="+md.hashData("iparraviccini".getBytes()).toLowerCase());
	        System.out.println("jbustamante="+md.hashData("jbustamante".getBytes()).toLowerCase());
	        System.out.println("irecavarren="+md.hashData("irecavarren".getBytes()).toLowerCase());
	        System.out.println("ccarbajal="+md.hashData("ccarbajal".getBytes()).toLowerCase());
	        System.out.println("lmarquez="+md.hashData("lmarquez".getBytes()).toLowerCase());
	        System.out.println("rnavarro="+md.hashData("rnavarro".getBytes()).toLowerCase());
	        System.out.println("cjallo="+md.hashData("cjallo".getBytes()).toLowerCase());
	        System.out.println("amillones="+md.hashData("amillones".getBytes()).toLowerCase());
	        System.out.println("ahinostroza="+md.hashData("ahinostroza".getBytes()).toLowerCase());
	        System.out.println("imondragon="+md.hashData("imondragon".getBytes()).toLowerCase());
            
	        System.out.println("manu="+md.hashData("manu".getBytes()).toLowerCase());
	         
	         } 
	         catch(NoSuchAlgorithmException e) 
	         { System.out.println("hd"); 
	             e.printStackTrace(System.out); 
	         } 
	     } */
	}
