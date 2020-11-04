package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void optionpaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "Ýptal");
		UIManager.put("OptionPane.noButtonText", "Hayýr");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}
	
	public static void showMessage(String str)
	{
		String msg;
		optionpaneChangeButtonText();
		
		switch(str) {
		case "fill":
			msg="Lütfen tüm alanlarý doldurunuz !";
			break;
		case "success":
			msg="Ýþlem baþarýlý !";
			break;		
		case "error":
			msg="Bir hata gerçekleþti !";
			break;
		default:
			msg=str;
		}
		JOptionPane.showMessageDialog(null,msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confim(String str) {
		optionpaneChangeButtonText();
		String msg;
		switch(str) {
		case "sure":
			msg = "Bu iþlemi gerçekleþtirmekten emin misiniz?";
			break;
			default:
				msg = str;
				break;
		}
		int res = JOptionPane.showConfirmDialog(null, msg,"Dikkat !",JOptionPane.YES_NO_OPTION);
		if(res==0) {
			return true;
		}
			else {
				return false;
			}
		}
	}

